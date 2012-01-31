(ns ^{:doc "Problem: http://www.4clojure.com/problem/104"}
    denlab-4clojure-exercices.problem-104
    (:use [midje.sweet]))

;; Write Roman Numerals
 
;; Difficulty:	Medium
;; Topics:	strings math


;; This is the inverse of Problem 92M, but much easier. Given an
;; integer smaller than 4000, return the corresponding roman numeral
;; in uppercase, adhering to the subtractive principleN.


(def m (zipmap "IVXLCDM" [1 5 10 50 100 500 1000]))


(unfinished )

(defn transcod
  [x] 
  ({1  "I" , 4   "IV", 7     "VII" , 8   "VIII", 9    "IX", 20 "XX", 30 "XXX", 40 "XL",
    90 "CL", 100 "C" , 800   "DCCC", 900 "CM"  , 3000 "MMM"}
   x))

(fact "transcod"
  (transcod 3000) => "MMM"
  (transcod 900) => "CM")

(defn decomp
  [n] 
  (map #(* (first %) )
       (remove #(= \0 (first %))
               (map vector (str n) (range)))))

(defn f
  [[curr d o]] (let [r (rem curr d)]
                 [(- curr r)
                  (* 10 d)
                  (cons r o)]))

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
    (decomp :n) => [:e1 :e2]
    (transcod :e1) => "R1"
    (transcod :e2) => "R2"))


(future-fact
 (g 1) => "I" )

(future-fact
 (g 30) => "XXX" )

(future-fact
 (g 4) => "IV" )

(future-fact
 (g 140) => "CXL" )

(future-fact
 (g 827) => "DCCCXXVII" )

(future-fact
 (g 3999) => "MMMCMXCIX" )

(future-fact
 (g 48) => "XLVIII" )

{1 "I", 2 "II", 3 "III", 4 "IV", 5 "V", "VI" , "VII", "VIII", "IV"}

{10 "X", 11 "XI", 12 "XII", 13 "XIII", 14 "XIV", 15 "XV" , 16 "XVI", 17 "XVII", 18 "XVIII", 19 "IX"}
