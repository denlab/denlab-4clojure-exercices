(ns ^{:doc "Problem: http://www.4clojure.com/problem/85"}
    denlab-4clojure-exercices.problem-085
    (:use [midje.sweet])
    (:use [clojure.pprint :only [pprint]]))

;; Power Set
 
;; Difficulty:	Medium
;; Topics:	set-theory

;; Write a function which generates the power setM of a given set. The
;; power set of a set x is the set of all subsets of x, including the
;; empty set and x itself.

(defn children
  [[g v]] (map
           (fn [x] [
                   (disj g x)
                   (conj v x)])
           g))

(fact
  (children [#{:a :b :c} #{} #{}]) => [[#{:b :c} #{:a} ]
                                       [#{:a :b} #{:c}]
                                       [#{:a :c} #{:b}]])

(fact
  (children [#{:b :c} #{:a}]) => [[#{:b} #{:a :c}]
                                  [#{:c} #{:a :b}]]) 

(defn ff
  [{:keys [c p]}]  (let [d (mapcat children c)]
                     {:c d
                      :p (into p
                               (map
                                first c))}))

(fact
  (ff {:c [[#{:a :b} #{}]]
       :p #{:prevs}}) => {:c [[#{:b} #{:a}] [#{:a} #{:b}]]
                          :p #{:prevs #{:a :b}}})

(defn end?
  [gc] )

(future-fact
  (end? ))

(defn gg
  ([gc] (if (seq (:c gc))
          (gg (ff gc))
          (:p gc))))

(defn f [x] (gg {:c [[x #{}]] :p #{}}))

(fact
  (f #{1 :a}) => #{#{1 :a} #{:a} #{} #{1}})
(fact
  (f #{}) => #{#{}})
(fact
  (f #{1 2 3}) =>    #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
(future-fact
  (count (f (into #{} (range 10)))) => 1024)

(defn cnt
  [s] (if s
        (inc (cnt (next s)))
        0))

(fact
  (cnt [:a :b]) => 2)

(defn j
  [s]
  (if (second s)
    (do  (set (doall (map #(do
        
                                        (conj (j (disj s %)) %))
                                     s))))
    (do  #{s})))

(future-fact
  (j #{:a :b :c #{}}) => #{#{}
                       #{:a} #{:b} #{:c}
                       #{:a :b}  #{:a :c}  #{:b :c}
                       #{:a :b :c}})

(defn combin
  [n s]  (set (map #(conj % n) s)))

(fact
  (combin :a #{#{:b} #{:c}}) => #{#{:a :b} #{:a :c}})

(defn p
  [s]

  (if (seq s)
    (do 
        (let [f (first s)
              prev (p (disj s f))]
          (into (conj prev #{f}) (combin f prev))))
    (do 
        #{#{}})))


(fact
  (p #{:a :b :c}) => #{#{}
                       #{:a} #{:b} #{:c}
                       #{:a :b}  #{:a :c}  #{:b :c}
                       #{:a :b :c}})

(fact
  (p #{1 :a}) => #{#{1 :a} #{:a} #{} #{1}})
(fact
  (p #{}) => #{#{}})
(fact
  (p #{1 2 3}) =>    #{#{} #{1} #{2} #{3} #{1 2} #{1 3} #{2 3} #{1 2 3}})
(fact
  (count (p (into #{} (range 10)))) => 1024)
