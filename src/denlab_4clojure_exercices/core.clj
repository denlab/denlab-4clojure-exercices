(ns ^{:doc "Problem: http://www.4clojure.com/problem/@@@n@@@"}
    denlab-4clojure-exercices.core
  (:use [midje.sweet]))

(def *template* "(ns ^{:doc \"Problem: http://www.4clojure.com/problem/@@@n-web@@@\"}
    denlab-4clojure-exercices.problem-@@@n-clj@@@
  (:use [midje.sweet]))")

(def *src-dir* "/home/denis/tmp/git-test/denlab-4clojure-exercices/src/denlab_4clojure_exercices")

(defn- leftpadzero
  [s] (case (count (str s))
        1 (str "00" s)
        2 (str "0" s)
        (str s)))

(fact "leftpadzero"
  (leftpadzero 1) => "001"
  (leftpadzero 10) => "010"
  (leftpadzero 100) => "100")

(defn- fill-template
  [n] (-> *template*
          (.replaceAll "@@@n-web@@@" (str n))
          (.replaceAll "@@@n-clj@@@" (leftpadzero n))))

(fact "fill-template"
  (fill-template 1) => "(ns ^{:doc \"Problem: http://www.4clojure.com/problem/1\"}
    denlab-4clojure-exercices.problem-001
  (:use [midje.sweet]))")

(defn make-ns "Create ns files for 4clojure problems"
  [n] (spit (str *src-dir* "/problem-" (leftpadzero n) ".clj")
            (fill-template n)))

(defn make-nses "Create ns files for 4clojure problems"
  [] (doseq [n (range 1 200)]
       (spit (str *src-dir* "/problem-" (leftpadzero n) ".clj")
             (fill-template n))))

