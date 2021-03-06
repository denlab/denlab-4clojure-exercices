(ns ^{:doc "Problem: http://www.4clojure.com/problem/131"}
    denlab-4clojure-exercices.problem-131
    (:use [midje.sweet]
          [clojure.pprint :only [pprint]]))

;; Sum Some Set Subsets
 
;; Difficulty:	Medium
;; Topics:	math


;; Given a variable number of sets of integers, create a function
;; which returns true iff all of the sets have a non-empty subset with
;; an equivalent summation.

(def p
  (comp set
        (fn p [[f & r]] (if f (concat (p r) [#{f}] (map #(conj % f) (p r)))
                           [#{}]))
        seq))

(def g
  (let [p (comp set
                (fn p [[f & r]] (if f (concat (p r) [#{f}] (map #(conj % f) (p r)))
                                   [#{}]))
                seq)]
    (fn [& s] (not (empty? (apply clojure.set/intersection
                                 (map (fn [x]
                                        (set (map #(reduce + %)
                                                  (remove empty? x))))
                                      (map p s))))))))

(fact
  (g #{-1 1 99}
             #{-2 2 888}
             #{-3 3 7777}) => truthy  ) ; ex. all sets have a subset which sums to zero
(fact
  (g #{1}
     #{2}
     #{3}
     #{4}) => falsey )

(fact
  (g #{1}) => truthy  )

(fact
  (g #{1 -3 51 9}
     #{0}
     #{9 2 81 33}) => falsey )

(fact
  (g #{1 3 5}
     #{9 11 4}
     #{-3 12 3}
     #{-3 4 -2 10}) => truthy  )

(fact
  (g #{-1 -2 -3 -4 -5 -6}
     #{1 2 3 4 5 6 7 8 9}) => falsey )

(fact
  (g #{1 3 5 7}
     #{2 4 6 8}) => truthy  )

(fact
  (g #{-1 3 -5 7 -9 11 -13 15}
     #{1 -3 5 -7 9 -11 13 -15}
     #{1 -1 2 -2 4 -4 8 -8}) => truthy  )

(fact
  (g #{-10 9 -8 7 -6 5 -4 3 -2 1}
     #{10 -9 8 -7 6 -5 4 -3 2 -1}) => truthy  )
