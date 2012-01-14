(ns ^{:doc "Problem: http://www.4clojure.com/problem/78"}
    denlab-4clojure-exercices.problem-078
    (:use [midje.sweet]))

;; Reimplement Trampoline
 
;; Difficulty:	Medium
;; Topics:	core-functions


;; Reimplement the function described in "Intro to Trampoline"M.

(defn tr
  ([f & a] (let [r (if a (apply f a) (f))]
             (if (fn? r) (tr r) r))))

(def tr
  (fn t [f & a] (let [r (if a (apply f a) (f))]
                 (if (fn? r) (t r) r))))

(fact
  (tr + 1 2) => 3)

(fact
   (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
           (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
     (tr my-even? 11)) =>   false )

(fact
  (letfn [(triple [x] #(sub-two (* 3 x)))
          (sub-two [x] #(stop?(- x 2)))
          (stop? [x] (if (> x 50) x #(triple x)))]
    (tr triple 2)) =>   82)

(fact
  (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
          (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
    (map (partial tr my-even?) (range 6))) =>   [true false true false true false])

