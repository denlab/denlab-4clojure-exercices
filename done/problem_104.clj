(ns ^{:doc "Problem: http://www.4clojure.com/problem/104"}
    denlab-4clojure-exercices.problem-104
    (:use [midje.sweet]))

;; Write Roman Numerals
 
;; Difficulty:	Medium
;; Topics:	strings math

;; This is the inverse of Problem 92M, but much easier. Given an
;; integer smaller than 4000, return the corresponding roman numeral
;; in uppercase, adhering to the subtractive principleN.

(unfinished )

(defn transcod
  [x] 
  ({1  "I" , 4  "IV", 7   "VII", 8   "VIII", 9   "IX", 20   "XX", 30 "XXX",
    40 "XL", 90 "XC", 100 "C"  , 800 "DCCC", 900 "CM", 3000 "MMM"}
   x))

(fact "transcod"
  (transcod 3000) => "MMM"
  (transcod 900) => "CM")

(defn decomp
  [n] (loop [curr n d 10 o []]
        (if (zero? curr) o
            (let [r (rem curr d)]
              (recur (- curr r) (* 10 d) (cons r o)))))) 

(fact "decomp" 
  (decomp 3909) => [3000 900 0 9])

(defn g 
  [n] (reduce str (map transcod (decomp n))))

(fact
  (g :n) => "R1R2"
  (provided
    (decomp :n)    => [:e1 :e2]
    (transcod :e1) => "R1"
    (transcod :e2) => "R2"))

(def final
  #(reduce str
           (map {1    "I"   , 4   "IV"  , 7   "VII",
                 8    "VIII", 9   "IX"  , 20  "XX" ,
                 30   "XXX" , 40  "XL"  , 90  "XC" ,
                 100  "C"   , 800 "DCCC", 900 "CM" ,
                 3000 "MMM"}
                (loop [c % d 10 o []]
                  (if (zero? c) o
                      (let [r (rem c d)]
                        (recur (- c r) (* 10 d) (cons r o))))))))

(fact
 (final 1)    => "I")

(fact
 (final 30)   => "XXX")

(fact
 (final 4)    => "IV")

(fact
 (final 140)  => "CXL")

(fact
 (final 827)  => "DCCCXXVII")

(fact
 (final 3999) => "MMMCMXCIX")

(fact
 (final 48)   => "XLVIII")
