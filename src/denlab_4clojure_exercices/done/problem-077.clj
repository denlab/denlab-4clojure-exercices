(ns ^{:doc "Problem: http://www.4clojure.com/problem/77"}
    denlab-4clojure-exercices.problem-077
    (:use [midje.sweet]
          [clojure.repl :only [doc]]))

;; Anagram Finder
 
;; Difficulty:	Medium
;; Topics:	


;; Write a function which finds all the anagrams in a vector of words. A word x is an anagram of word y if all the letters in x can be rearranged in a different order to form y. Your function should return a set of sets, where each sub-set is a group of words which are anagrams of each other. Each sub-set should have at least two words. Words without any anagrams should not be included in the result.

(defn f
  [s] (set (filter (fn [s] (< 1 (count s))) (map set (vals (group-by #(set %) s))))))

(comment (defn f
   [s] (reduce (fn [r [k v]] (if (< 1 (count v))
                              (conj (set v) r) r))
               #{}
               (group-by #(set %) s))))

(defn f
  [s] (set (mapcat (fn [v] (if (< 1 (count v)) [(set v)])) (vals (group-by #(set %) s)))))

(defn f
  [s] (set (mapcat (fn [v] (if (second v) [(set v)]))
                   (vals (group-by #(set %) s)))))

(defn f
  [s] (set (mapcat #(if (second %) [(set %)]) (vals (group-by #(set %) s)))))

(def f
  (fn [s] (set (mapcat #(if (second %) [(set %)]) (vals (group-by #(set %) s))))))

(fact
  (f ["meat" "mat" "team" "mate" "eat"]) =>    #{#{"meat" "team" "mate"}})

(fact
  (f ["veer" "lake" "item" "kale" "mite" "ever"]) =>    #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})
