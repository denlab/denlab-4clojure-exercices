(ns ^{:doc "Problem: http://www.4clojure.com/problem/101"}
  denlab-4clojure-exercices.problem-101
  (:use [midje.sweet])
  (:use [clojure.pprint :only [pprint pp]])
  (:use [clojure.tools.trace :only [trace deftrace trace-forms trace-ns untrace-ns trace-vars]]))

;; Levenshtein Distance

;; Difficulty:	Hard
;; Topics:	seqs


;; Given two sequences x and y, calculate the Levenshtein distance of x
;; and y, i. e. the minimum number of edits needed to transform x into
;; y. The allowed edits are:

;; - insert a single item
;; - delete a single item
;; - replace a single item with another item

;; WARNING: Some of the test cases may timeout if you write an
;; inefficient solution!

(defn g
  [a b]
  (get-in (reduce (fn [r [j i]] (assoc-in r [j i] (if (= (nth a (dec j)) (nth b (dec i)))
                                                   (get-in r [(dec j) (dec i)])
                                                   (inc (apply min (map (fn [ji] (get-in r ji))
                                                                        [[(dec j) (dec i)]
                                                                         [     j  (dec i)]
                                                                         [(dec j)      i]]))))))
                  (vec (map (fn [j] (vec (map (fn [i] (if (zero? j) i j))
                                             (range (inc (count b))))))
                            (range (inc (count a)))))
                  (for [j (range 1 (inc (count a)))
                        i (range 1 (inc (count b)))]
                    [j i]))
          [(count a) (count b)]))

(defn g
  [a b]
  (let [m (count a) n (count b)]
    (get-in (reduce (fn [r [j i]] (assoc-in r [j i] (if (= (nth a (dec j)) (nth b (dec i)))
                                                     (get-in r [(dec j) (dec i)])
                                                     (inc (apply min (map (fn [ji] (get-in r ji))
                                                                          [[(dec j) (dec i)]
                                                                           [     j  (dec i)]
                                                                           [(dec j)      i]]))))))
                    (vec (map (fn [j] (vec (map (fn [i] (if (zero? j) i j))
                                               (range (inc n)))))
                              (range (inc m))))
                    (for [j (range 1 (inc m))
                          i (range 1 (inc n))]
                      [j i]))
            [m n])))

(defn g
  [a b]
  (let [m (count a) n (count b)]
    (get-in (reduce (fn [r [j i]] (assoc-in r [j i] (cond (zero? j) i
                                                         (zero? i) j
                                                         :else     (if (= (nth a (dec j)) (nth b (dec i)))
                                                                     (get-in r [(dec j) (dec i)])
                                                                     (inc (apply min (map (fn [ji] (get-in r ji))
                                                                                          [[(dec j) (dec i)]
                                                                                           [     j  (dec i)]
                                                                                           [(dec j)      i]])))))))
                    {}
                    (for [j (range (inc m)) i (range (inc n))] [j i]))
            [m n])))

(defn g
  [a b]
  (let [m (count a) n (count b)]
    (get-in (reduce
             (fn [r [j i]] (assoc-in r [j i]
                                    (cond (= 0 j) i
                                          (= 0 i) j
                                          :else  (let [k (dec j) l (dec i)]
                                                   (if (= (nth a k) (nth b l))
                                                     (get-in r [k l])
                                                     (inc (apply min (map (fn [ji] (get-in r ji))
                                                                          [[k l] [j l] [k i]]))))))))
             {}
             (for [j (range (inc m)) i (range (inc n))] [j i]))
            [m n])))

(defn g
  [a b]
  (let [m (count a) n (count b)]
    (get-in (reduce
             (fn [r [j i]] (assoc-in r [j i]
                                    (cond (= 0 j) i
                                          (= 0 i) j
                                          :else  (let [k (dec j) l (dec i)]
                                                   (if (= (nth a k) (nth b l))
                                                     (get-in r [k l])
                                                     (inc (apply min (map (fn [x] (get-in r x))
                                                                          [[k l] [j l] [k i]]))))))))
             {}
             (for [j (range (inc m)) i (range (inc n))] [j i]))
            [m n])))

(defn g
  [a b]
  (let [m (count a) n (count b)]
    (get-in (reduce
             (fn [r [j i]] (assoc-in r [j i]
                                    (cond (= 0 j) i
                                          (= 0 i) j
                                          :else  (let [k (dec j) l (dec i)]
                                                   (if (= (nth a k) (nth b l))
                                                     (get-in r [k l])
                                                     (inc (apply min (map #(get-in r %)
                                                                          [[k l] [j l] [k i]]))))))))
             {}
             (for [j (range (inc m)) i (range (inc n))] [j i]))
            [m n])))

(defn g
  [a b]
  (let [m (count a) n (count b)]
    (((reduce
       (fn [r [j i]] (assoc-in r [j i]
                              (cond (= 0 j) i
                                    (= 0 i) j
                                    :else  (let [k (dec j) l (dec i)]
                                             (if (= (nth a k) (nth b l))
                                               (get-in r [k l])
                                               (inc (apply min (map #(get-in r %)
                                                                    [[k l] [j l] [k i]]))))))))
       {}
       (for [j (range (inc m)) i (range (inc n))] [j i])) m) n)))

(defn g
  [a b]
  (let [m (count a) n (count b)]
    (((reduce
       (fn [r [j i]] (assoc-in r [j i]
                              (cond (= 0 j) i
                                    (= 0 i) j
                                    :else  (let [k (- j 1) l (- i 1)]
                                             (if (= (nth a k) (nth b l))
                                               ((r k) l)
                                               (inc (apply min (map #(get-in r %)
                                                                    [[k l] [j l] [k i]]))))))))
       {}
       (for [j (range (+ m 1)) i (range (+ n 1))] [j i])) m) n)))

(def g
  (fn [a b]
    (let [m (count a) n (count b)]
      (((reduce
         (fn [r [j i]] (assoc-in r [j i]
                                (cond (= 0 j) i
                                      (= 0 i) j
                                      :else  (let [k (- j 1) l (- i 1)]
                                               (if (= (nth a k) (nth b l))
                                                 ((r k) l)
                                                 (inc (apply min (map #(get-in r %)
                                                                      [[k l] [j l] [k i]]))))))))
         {}
         (for [j (range (+ m 1)) i (range (+ n 1))] [j i])) m) n))))


 (fact
  (g "ttttattttctg" "tcaaccctaccat") =>  10)

(fact
  (g "kitten" "sitting") =>  3)
 (fact
  (g "clojure" "closure") => 1)
 (fact
  (g "closure" "clojure") => 1)
 (fact
  (g "xyx" "xyyyx") =>  2)
 (fact
  (g "" "123456") =>  6)
 (fact
  (g "Clojure" "Clojure") => 0)
 (fact
  (g "" "") => 0)
 (fact
  (g [] []) => 0)
 (fact
  (g [1 2 3 4] [0 2 3 4 5]) =>  2)
 (fact
  (g '(:a :b :c :d) '(:a :d)) =>  2)
 (fact
  (g "gaattctaatctc" "caaacaaaaaattt") =>  9)
