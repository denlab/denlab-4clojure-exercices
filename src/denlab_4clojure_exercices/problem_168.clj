(ns ^{:doc "Problem: http://www.4clojure.com/problem/168"}
    denlab-4clojure-exercices.problem-168
  (:use [midje.sweet]))

;; Infinite Matrix

;; Difficulty:	Medium
;; Topics:	seqs recursion math


;; In what follows, m, n, s, t denote nonnegative integers, f denotes a function that accepts two arguments and is defined for all nonnegative integers in both arguments.

;; In mathematics, the function f can be interpreted as an infinite matrix with infinitely many rows and columns that, when written, looks like an ordinary matrix but its rows and columns cannot be written down completely, so are terminated with ellipses. In Clojure, such infinite matrix can be represented as an infinite lazy sequence of infinite lazy sequences, where the inner sequences represent rows.

;; Write a function that accepts 1, 3 and 5 arguments

;;     with the argument f, it returns the infinite matrix A that has the entry in the i-th row and the j-th column equal to f(i,j) for i,j = 0,1,2,...;
;;     with the arguments f, m, n, it returns the infinite matrix B that equals the remainder of the matrix A after the removal of the first m rows and the first n columns;
;;     with the arguments f, m, n, s, t, it returns the finite s-by-t matrix that consists of the first t entries of each of the first s rows of the matrix B or, equivalently, that consists of the first s entries of each of the first t columns of the matrix B.

;; Special Restrictions
;; for
;; range
;; iterate
;; repeat
;; cycle
;; drop


(defn g
  ([f m n] (drop m (map (partial drop n)
                        (g f))))
  ([f]     (map (fn [j] (map (partial f j)
                            (range)))
                (range))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; 4 clj tests
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(fact
 (take 5 (map #(take 6 %) (g str)))
 =>
 [["00" "01" "02" "03" "04" "05"]
  ["10" "11" "12" "13" "14" "15"]
  ["20" "21" "22" "23" "24" "25"]
  ["30" "31" "32" "33" "34" "35"]
  ["40" "41" "42" "43" "44" "45"]])

(fact
 (take 6 (map #(take 5 %) (g str 3 2)))
 =>
 [["32" "33" "34" "35" "36"]
  ["42" "43" "44" "45" "46"]
  ["52" "53" "54" "55" "56"]
  ["62" "63" "64" "65" "66"]
  ["72" "73" "74" "75" "76"]
  ["82" "83" "84" "85" "86"]])



(future-fact


 (fact
  (g * 3 5 5 7)
  =>
  [[15 18 21 24 27 30 33]
   [20 24 28 32 36 40 44]
   [25 30 35 40 45 50 55]
   [30 36 42 48 54 60 66]
   [35 42 49 56 63 70 77]])

 (fact
  (g #(/ % (inc %2)) 1 0 6 4)
  =>
  [[1/1 1/2 1/3 1/4]
   [2/1 2/2 2/3 1/2]
   [3/1 3/2 3/3 3/4]
   [4/1 4/2 4/3 4/4]
   [5/1 5/2 5/3 5/4]
   [6/1 6/2 6/3 6/4]])

 (fact
  (g (juxt bit-or bit-xor))       => (class (lazy-seq)))

 (fact
  (g (juxt quot mod) 13 21)       => (class (lazy-seq)))


 (fact
  (nth (g (constantly 10946)) 34) => (class (lazy-seq)))

 (fact
  (nth (g (constantly 0) 5 8) 55) => (class (lazy-seq)))

 (fact (let [m 377 n 610 w 987
             check (fn [f s] (every? true? (map-indexed f s)))
             row (take w (nth (g vector) m))
             column (take w (map first (g vector m n)))
             diagonal (map-indexed #(nth %2 %) (g vector m n w w))]
         (and (check #(= %2 [m %]) row)
              (check #(= %2 [(+ m %) n]) column)
              (check #(= %2 [(+ m %) (+ n %)]) diagonal)))
       => true))
