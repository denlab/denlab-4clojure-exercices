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

(defn neigboors
  [n g] (filter #(some (partial = n) %) g))

(fact
  (neigboors :b [[:a :b] [:b :c]]) => [[:a :b] [:b :c]]
  (neigboors :a [[:a :b] [:b :c]]) => [[:a :b]]
  (neigboors :c [[:a :b] [:b :c]]) => [[:b :c]])

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
                  (vector nxt (remove-first % g)))
               (neigboors n g)))
(fact
  (transv2 [:b [[:a :b] [:b :c]]]) => [[:a [[:b :c]]],
                                       [:c [[:a :b]]]]
  (transv2 [:a [[:b :c]]])         => []
  (transv2 [:c [[:a :b]]])         => [])

(fact
 (transv2 [:a [[:a :b] [:b :c]]]) => [[:b [[:b :c]]]]
 (transv2 [:b [[:b :c]]])         => [[:c []]])

(defn adv-seq2
  [[n g :as r]] (if (empty? r)
                  false
                  (if (empty? g)
                    true
                    (let [childs (transv2 r)]
                      (some adv-seq2
                            childs)))))

(fact
 (adv-seq2 [1 [[1 2] [2 3] [3 4] [4 1]]]) => truthy)

(fact
 (adv-seq2 [:b [[:a :b] [:b :c]]]) => falsey)

(fact
 (adv-seq2 [:a [[:a :b] [:b :c]]]) => truthy)

(defn f
  [g] (some #(adv-seq2 [% g])
       (distinct (flatten g))))

(fact
  (f [[:a :b]]) => truthy)

(fact
  (f [[:a :a] [:b :b]]) => falsey )

(fact
  (f [[:a :b] [:a :b] [:a :c] [:c :a]
               [:a :d] [:b :d] [:c :d]]) => falsey )

(fact
  (f [[1 2] [2 3] [3 4] [4 1]]) => truthy )

(fact
  (f [[:a :b] [:a :c] [:c :b] [:a :e]
              [:b :e] [:a :d] [:b :d] [:c :e]
              [:d :e] [:c :f] [:d :f]]) => true )

(fact
  (f [[1 2] [2 3] [2 4] [2 5]]) => falsey)

