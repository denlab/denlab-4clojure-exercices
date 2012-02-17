(ns ^{:doc "Problem: http://www.4clojure.com/problem/121"}
  denlab-4clojure-exercices.problem-121
  (:use [midje.sweet]))
 
;; Difficulty:	Medium
;; Topics:	functions


;; Given a mathematical formula in prefix notation, return a function
;; that calculates the value of the formula. The formula can contain
;; nested calculations using the four basic mathematical operators,
;; numeric constants, and symbols representing variables. The returned
;; function has to accept a single parameter containing the map of
;; variable names to their values.

(defn fx
  [x] (if (sequential? x)
        (apply (resolve (first x)) (map fx (rest x)))
        x))

(fact
  (fx '(/ 16 8)) => 2)

(fact
   (fx '(+ 2 4 2)) => 8)

(def g
  #(fn [m] (letfn [(f [x] (if (sequential? x)
                           (apply (resolve (first x)) (map f (rest x)))
                           (if-let [v (m x)] v x)))] 
            (f %))))

(fact
  ((g '(/ a b))
      '{b 8 a 16}) => 2)

(fact
  ((g '(+ a b 2))
      '{a 2 b 4}) => 8)

(fact
  (map (g '(* (+ 2 a)
                  (- 10 b)))
            '[{a 1 b 8}
              {b 5 a -2}
              {a 2 b 11}]) => [6 0 -4])

(fact
  ((g '(/ (+ x 2)
              (* 3 (+ y 1))))
      '{x 4 y 1}) => 1)
