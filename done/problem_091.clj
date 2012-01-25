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

(def neigboors
  (fn [n g] (filter #(some (partial = n) %) g)))

(fact
  (neigboors :b [[:a :b] [:b :c]]) => [[:a :b] [:b :c]]
  (neigboors :a [[:a :b] [:b :c]]) => [[:a :b]]
  (neigboors :c [[:a :b] [:b :c]]) => [[:b :c]])

(defn extract-node
  [n e] ({(e 0) (e 1)} n (e 0)))

(fact
  (extract-node :a [:a :b]) => :b
  (extract-node :b [:a :b]) => :a)

(def remove-first "Like remove but for only one element"
  (fn [i s] (let [[f e] (split-with #(not= i %) s)]
             (concat f (next e))))) 

(fact
  (remove-first :b [:a :b :c :b :d]) => [:a :c :b :d])

(def f
  (fn [g]
    (letfn
        [(remove-one    [i s] (let [[f e] (split-with #(not= i %) s)]
                                (concat f (next e))))
         (get-node  [n [a b]] (if (= n a) b a))
         (children    [[n g]] (map #(vector (get-node n %) (remove-one % g))
                                   (filter #(some (partial = n) %) g)))
         (tour? [[n g :as r]] (cond (empty? r) false
                                    (empty? g) true
                                    :else      (some tour? (children r))))] 
      (true? (some #(tour? [% g]) 
                   (distinct (flatten g)))))))

(fact
  (f [[:a :b]]) => truthy)

(fact
  (f [[:a :a] [:b :b]]) => false )

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



