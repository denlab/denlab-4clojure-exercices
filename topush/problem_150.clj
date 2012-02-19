(ns ^{:doc "Problem: http://www.4clojure.com/problem/150"}
    denlab-4clojure-exercices.problem-150
    (:use
     [clojure.repl   :only [doc]]
     [clojure.pprint :only [pprint]]
     [midje.sweet]))

;; Palindromic Numbers
 
;; Difficulty:	Medium
;; Topics:	seqs math


;; A palindromic number is a number that is the same when written
;; forwards or backwards (e.g., 3, 99, 14341).

;; Write a function which takes an integer n, as its only argument,
;; and returns an increasing lazy sequence of all palindromic numbers
;; that are not less than n.

;; The most simple solution will exceed the time limit!

(unfinished )

(comment (def rev "Reverse a string"
           #(reduce str "" (reverse %)))

         (fact "rev"
           (rev "abc") => "cba"
           (rev "a") => "a")

         (def dstr "'Destructure' a number for palindromic manipulation"
           #(let [s (str %)
                  a (count s)
                  b (/ a 2)
                  c (+ b (if (even? a) 0 1))]
              (map (fn [x y] (.substring s x y)) [0 b c] [b c a])))

         (fact "dstr"
           (dstr 1) => ["" "1" ""]
           (dstr 1234) => ["12" "" "34"]
           (dstr 12345) => ["12" "3" "45"])

         (def pal?
           #(let [[l _ r] (dstr %)]
              (= l (rev r))))

         (fact "pal??"
           (pal? 121) => truthy
           (pal? 2) => truthy
           (pal? 22) => truthy
           (pal? 23) => falsey)

         (def np
           #(cond (re-find #"^9*$" (str %)) (+ 2 %)
                  (< % 10)                  (inc %)
                  :else
                  (let [[l m r] (dstr %)
                        ct (.compareTo l (rev r))
                        nz (bigint (str l m (rev l)))]
                    (cond  
                     (zero? ct) (let [ll (inc (bigint (str l m)))]
                                  (bigint (str ll
                                               (rev (str (quot ll (if (seq m) 10 1)))))))
                     (pos? ct) nz
                     :else     (np nz)))))

         (fact "np NOT palind right upper"
           (np 12322) => 12421)

         (fact "np NOT palind right lower"
           (np 12320) => 12321)

         (fact "np palind odd"
           (np 12321) => 12421)

         (fact "np palind even"
           (np 1221) => 1331)

         (fact "np size one"
           (np 1) => 2)

         (fact "np about to inc"
           (np 9) => 11
           (np 99) => 101
           (np 999) => 1001))

(def g
  (fn [n] (let [rev  #(reduce str "" (reverse %))
               dstr #(let [s (str %)
                           a (count s)
                           b (/ a 2)
                           c (+ b (if (even? a) 0 1))]
                       (map (fn [x y] (.substring s x y)) [0 b c] [b c a]))
               pal? #(let [[l _ r] (dstr %)]
                       (= l (rev r)))
               np (fn np [m] (cond (re-find #"^9*$" (str m)) (+ 2 m)
                                  (< m 10)                  (inc m)
                                  :else
                                  (let [[l m r] (dstr m)
                                        ct (.compareTo l (rev r))
                                        nz (bigint (str l m (rev l)))]
                                    (cond  
                                     (zero? ct) (let [ll (inc (bigint (str l m)))]
                                                  (bigint (str ll
                                                               (rev (str (quot ll (if (seq m) 10 1)))))))
                                     (pos? ct) nz
                                     :else     (np nz)))))]
           (iterate np
                    (if (pal? n) n (np n))))))

(comment (defn g
   [n] (iterate np
                (if (pal? n) n (np n)))))

(fact "g"
  (first (g 0))  => 0
  (first (g 10)) => 11)

;; -------------------- 4 clj tests --------------------

(fact
  (take 26 (g 0)) => 
  [0 1 2 3 4 5 6 7 8 9
   11 22 33 44 55 66 77 88 99
   101 111 121 131 141 151 161])

(fact
  (take 16 (g 162)) => 
  [171 181 191 202
   212 222 232 242
   252 262 272 282
   292 303 313 323])

(fact
  (first (g (* 111111111 111111111))) => 
  (* 111111111 111111111))

(fact (set (take 199 (g 0))) => 
             (set (map #(first (g %)) (range 0 10000))))

(fact
  (apply < (take 6666 (g 9999999))) => true)

(fact
  (nth (g 0) 10101) => 9102019)

