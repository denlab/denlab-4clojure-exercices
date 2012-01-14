(ns ^{:doc "Problem: http://www.4clojure.com/problem/91"}
    denlab-4clojure-exercices.problem-091
    (:use [midje.sweet])
    (:use [clojure.pprint :only [pprint]])
    (:use [clojure.repl   :only [doc]])
)

;; Difficulty:	Hard
;; Topics:	graph-theory


;; Starting with a graph you must write a function that returns true
;; if it is possible to make a tour of the graph in which every edge
;; is visited exactly once.

;; The graph is represented by a vector of tuples, where each tuple
;; represents a single edge.

;; The rules are:

;; - You can start at any node.
;; - You must visit each edge exactly once.
;; - All edges are undirected.

(defn all "All nodes in a seq graph"
  [g] (distinct (flatten g)))

(fact
  (all [[:a :b] [:b :c]]) => [:a :b :c])

(defn nb "neigboors"
  [n g] #_(all (filter #((set %) n)
                     g)))

#_(future-fact
 (nb :b
     [[:a :b]
      [:b :c]
      [:a :d]]) =>
      [:a :c])

(defn as-graph "Seq graph to map graph"
  [g] (reduce (fn [r [n1 n2]] (if-let [v (r n1)]
                               (assoc r n1 (conj v n2))
                               (assoc r n1 #{n2})))
              {}
              (concat g (map rseq g))))

(fact "Note: There can't be orphan in a seq graph"
  (as-graph [[:a :b] [:b :c] [:a :d] [:e :e]]) => {:a #{:b :d},
                                                   :b #{:a :c},
                                                   :c #{:b},
                                                   :d #{:a},
                                                   :e #{:e},})

(defn adv
  [g tovisit visited?] 
  (cond (= (count g) (count visited?)) true
        (not tovisit)                  false
        :else        (some #(adv g (clojure.set/difference (g %) visited?)
                                 (conj visited? %))
                           tovisit)))
 
(fact
  (adv {:b #{:a :c}, :a #{:b}, :c #{:b}}
       [:b]
       #{}) => falsey)

(fact
  (adv {:b {:a #{:b}, :b #{:a :c}, :c #{:b}}}
       [:a]
       #{}) => truthy)

(defn neigboors
  [n g] (filter #(some (partial = n) %) g))

(fact
  (neigboors :b [[:a :b] [:b :c]]) => [[:a :b] [:b :c]]
  (neigboors :a [[:a :b] [:b :c]]) => [[:a :b]]
  (neigboors :c [[:a :b] [:b :c]]) => [[:b :c]])

(defn transv
  [n g] (let [f #(some (partial = n) %)]
          [(filter (complement f) g) (filter f g)])) 

(fact
  (transv :b [[:a :b] [:b :c]]) => [[]
                                    [[:a :b] [:b :c]]]
  (transv :a [[:a :b] [:b :c]]) => [[[:b :c]]
                                    [[:a :b]]]
  (transv :c [[:a :b] [:b :c]]) => [[[:a :b]]
                                    [[:b :c]]])

(defn extract-node
  [n [a b]] (if (= n a) b a))

(fact
  (extract-node :a [:a :b]) => :b
  (extract-node :b [:a :b]) => :a)

(defn remove-first "Like remove but for only one element"
  [i s] (let [[f s] (split-with (partial not= i) s)]
          (concat f (next s)))) 

(fact
  (remove-first :b [:a :b :c :b :d]) => [:a :c :b :d])


(defn transv2
  [[n g]] (map #(let [nxt (extract-node n %)]
                  (vector nxt (remove (partial =)) ))
               (neigboors n g)))
(future-fact
  (transv2 [:b [[:a :b] [:b :c]]]) => [[:a [[:b :c]]],
                                       [:c [[:a :b]]]]
  (transv2 [:a [[:b :c]]])         => []
  (transv2 [:c [[:a :b]]])         => [])

(future-fact
  (transv2 [:a [[:a :b] [:b :c]]]) => [:b [:b :c]]
  (transv2 [:b [:b :c]])           => [:c []])



(defn adv-seq
  ([n g] (do (println "(*) n=" n "g=" g) (adv-seq n (transv n g) [] g)))
  ([n [g tovisit] visited g-orig]
     (do (println "n=" n "g=" g "tovisit=" tovisit "remaining-count=" visited )
         (cond (empty? tovisit)        (do (println "->1") (= (count visited) (count g-orig)))  
               (not tovisit)           (do (println "->2") false)
               :else                   (do (println "->3") (some #(let [nxt (extract-node n %)]
                                                                    (adv-seq nxt (transv nxt g) (conj visited %) g-orig))
                                                                 tovisit))))))

(future-fact
  (adv-seq 1 [[1 2] [2 3] [3 4] [4 1]]) => truthy)

(defn f
  [g] (some #(adv-seq % g)
       (distinct (flatten g))))

(future-fact
  (f [[:a :b]]) => truthy )

(future-fact
  (f [[:a :a] [:b :b]]) => falsey )

(future-fact
  (f [[:a :b] [:a :b] [:a :c] [:c :a]
               [:a :d] [:b :d] [:c :d]]) => falsey )

(future-fact
  (f [[1 2] [2 3] [3 4] [4 1]]) => truthy )

(future-fact
  (f [[:a :b] [:a :c] [:c :b] [:a :e]
              [:b :e] [:a :d] [:b :d] [:c :e]
              [:d :e] [:c :f] [:d :f]]) => true )

(future-fact
  (f [[1 2] [2 3] [2 4] [2 5]]) => false )

