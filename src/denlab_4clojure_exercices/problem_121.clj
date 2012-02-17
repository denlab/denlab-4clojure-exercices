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

(defn f
  [x] (if (sequential? x)
        (do
          (prn "->1 " "first x=" (first x) "(rest x)=" (rest x))
          (let [frst (first x)
                mm   (doall (map f (rest x)))]
            (do (prn "fsrt=" frst "mm=" mm)
                (prn "(apply frst mm) => " "(apply " (resolve frst) mm ")")
                (apply (resolve frst) mm))))
        (do (prn "->2" "x=" x) x)))

(fact
  (f '(/ 16 8)) => 2)

(fact
   (f '(+ 2 4 2)) => 8)

(def g
  #(fn [m] (let [expr (clojure.walk/prewalk (fn [x] (if-let [v (m x)] v x)) %)]
            (do (prn "expr=" expr)
                (f expr)))))

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
