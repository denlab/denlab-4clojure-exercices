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






;; ---------------<ng>---------------------

(comment

  "abcdefg"

  "...abc....."   "d"    "....efg..."
  [0 seg1-right seg2-left seg2-right]

  )

(defn ng-next
  [n] (let [s           (str n)
            seg2-right  (.length s)
            seg1-right  (quot seg2-right 2)
            seg1        (.substring s 0 seg1-right)]
        (if (= "" (.replaceAll s "9" ""))
          (+ 2 n)
          (if (even? seg2-right)
            (let [seg1-ng (str (inc (Long/valueOf seg1)))]
              (Long/valueOf (str seg1-ng (clojure.string/reverse seg1-ng))))
            (let [seg1-ng (str (inc (Long/valueOf (str seg1 (.substring s seg1-right (inc seg1-right))))))
                  seg2-ng (clojure.string/reverse (.substring seg1-ng 0 seg1-right))]
              (Long/valueOf (str seg1-ng seg2-ng)))))))

(fact "ng-next even"
  (ng-next 1221) => 1331)

(fact "ng-next even limit"
  (ng-next 9999) => 10001)

(fact "ng-next odd"
  (ng-next 12321) => 12421)

(fact "ng-next odd limit"
  (ng-next 99999) => 100001)

(defn ng-norm
  [n] (let [s           (str n)
            seg2-right  (.length s)
            s-even?     (even? seg2-right)
            seg1-right  (quot seg2-right 2)
            seg2-left   (+ seg1-right (if s-even? 0 1))
            seg1        (.substring s 0 seg1-right)
            cmp         (.compareTo seg1 (clojure.string/reverse (.substring s seg2-left seg2-right)))]
        (cond (zero? cmp) n
              (pos?  cmp) (Long/valueOf (str seg1 (.substring s seg1-right seg2-left) (clojure.string/reverse seg1)))
              :else       (if s-even?
                            (let [seg1-ng (str (inc (Long/valueOf seg1)))]
                              (Long/valueOf (str seg1-ng (clojure.string/reverse seg1-ng))))
                            (let [seg1-ng (str (inc (Long/valueOf (str seg1 (.substring s seg1-right seg2-left)))))
                                  seg2-ng (clojure.string/reverse (.substring seg1-ng 0 seg1-right))]
                              (Long/valueOf (str seg1-ng seg2-ng)))))))

(fact "ng-norm not pal odd high"
      (ng-norm 12322) => 12421)

(fact "ng-norm not pal even high"
      (ng-norm 1222) => 1331)
 
(fact "ng-norm not pal odd low"
      (ng-norm 12320) => 12321)
 
(fact "ng-norm not pal even low"
      (ng-norm 1220) => 1221)
 
(fact "ng-norm pal odd"
      (ng-norm 12321) => 12321)
 
(fact "ng-norm pal even"
      (ng-norm 1221) => 1221)
 

(def g
  (letfn [(ng-next [n]
            (let [s           (str n)
                  seg2-right  (.length s)
                  seg1-right  (quot seg2-right 2)
                  seg1        (.substring s 0 seg1-right)]
              (if (= "" (.replaceAll s "9" ""))
                (+ 2 n)
                (if (even? seg2-right)
                  (let [seg1-ng (str (inc (Long/valueOf seg1)))]
                    (Long/valueOf (str seg1-ng (clojure.string/reverse seg1-ng))))
                  (let [seg1-ng (str (inc (Long/valueOf (str seg1 (.substring s seg1-right (inc seg1-right))))))
                        seg2-ng (clojure.string/reverse (.substring seg1-ng 0 seg1-right))]
                    (Long/valueOf (str seg1-ng seg2-ng)))))))
          (ng-norm [n] (let [s           (str n)
                             seg2-right  (.length s)
                             s-even?     (even? seg2-right)
                             seg1-right  (quot seg2-right 2)
                             seg2-left   (+ seg1-right (if s-even? 0 1))
                             seg1        (.substring s 0 seg1-right)
                             cmp         (.compareTo seg1 (clojure.string/reverse (.substring s seg2-left seg2-right)))]
                         (cond (zero? cmp) n
                               (pos?  cmp) (Long/valueOf (str seg1 (.substring s seg1-right seg2-left) (clojure.string/reverse seg1)))
                               :else       (if s-even?
                                             (let [seg1-ng (str (inc (Long/valueOf seg1)))]
                                               (Long/valueOf (str seg1-ng (clojure.string/reverse seg1-ng))))
                                             (let [seg1-ng (str (inc (Long/valueOf (str seg1 (.substring s seg1-right seg2-left)))))
                                                   seg2-ng (clojure.string/reverse (.substring seg1-ng 0 seg1-right))]
                                               (Long/valueOf (str seg1-ng seg2-ng)))))))]
    (fn [n] (if (< n 10)
             (concat (range n 10) (iterate ng-next 11))
             (iterate ng-next (ng-norm n))))))

;; ---------------</ng>---------------------



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
  (take 6 (g 1234550000))
   [1234554321 1234664321 1234774321
    1234884321 1234994321 1235005321])

(fact
 (first (g (* 111111111 111111111))) => 
 (* 111111111 111111111))

(fact
   (apply < (take 6666 (g 9999999))) => true)

(fact
   (nth (g 0) 10101) => 9102019)

;; on big big
;; orig: 70ms
;; newgen: 10ms
;; newgen: 8ms
(fact (set (take 199 (g 0))) => 
      (set (map #(first (g %))
                (range 0 10000))))

(fact
  (nth (g 0) 10101) => 9102019)

;; -------------------- 4 clj tests --------------------

(comment 







)


