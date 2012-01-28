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
  (prn)
  (prn (str "(j " s " )"))
  (if (second s)
    (do (prn "->1") (set (doall (map #(do
                                        (prn (str "(conj (j (disj " s % ")) " % ")"))
                                        (prn (str "(conj (j " (disj s % ) ") " % ")"))
                                        (conj (j (disj s %)) %))
                                     s))))
    (do (prn "->2" "returning " #{s}) #{s})))

(future-fact
  (j #{:a :b :c #{}}) => #{#{}
                       #{:a} #{:b} #{:c}
                       #{:a :b}  #{:a :c}  #{:b :c}
                       #{:a :b :c}})

(defn combin
  [n s] (prn (str "(combin " n " " s ")")) (set (map #(conj % n) s)))

(fact
  (combin :a #{#{:b} #{:c}}) => #{#{:a :b} #{:a :c}})

(defn p
  [s]
  (prn)
  (prn (str "(p " s ")") )
  (if (seq s)
    (do (prn "->1")
        (let [f (first s)
              prev (p (disj s f))]
          (into (conj prev #{f}) (combin f prev))))
    (do (prn "->2")
        #{#{}})))


(fact
  (p #{:a :b :c}) => #{#{}
                       #{:a} #{:b} #{:c}
                       #{:a :b}  #{:a :c}  #{:b :c}
                       #{:a :b :c}})
