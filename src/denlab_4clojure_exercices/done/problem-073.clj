(ns ^{:doc "Problem: http://www.4clojure.com/problem/73"}
    denlab-4clojure-exercices.problem-073
    (:use [midje.sweet]))

(fn [m]
  (first
   (first
    (filter #(and (not= (first %) :e) (apply = %))
            (concat m (apply map vector m)
                    [(map #(get-in m [% %])        (range 3))
                     (map #(get-in m [% (- 2  %)]) (range 3))])))))
