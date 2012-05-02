(ns ^{:doc "Problem: http://www.4clojure.com/problem/116"}
    denlab-4clojure-exercices.problem-116
  (:use [midje.sweet]))

;; Number Maze
 
;; Difficulty:	Hard
;; Topics:	numbers


;; Given a pair of numbers, the start and end point, find a path
;; between the two using only three possible operations:

;;     double
;;     halve (odd numbers cannot be halved)
;;     add 2

;; Find the shortest path through the "maze". Because there are
;; multiple shortest paths, you must return the length of the shortest
;; path, not the path itself

(defn log-dec
  [f] (fn [& args] (println f args) (apply f args)))

(defn childs
  [{:keys [curr depth] :as m}]
  (map (fn [o] (-> m
                  (assoc :curr  (o curr 2))
                  (assoc :depth (inc depth))))
       (if (even? curr) [* / +] [* +])))

(fact
  (childs {:curr 4 :depth 0 :and :more}) => [{:curr 8 :depth 1 :and :more}
                                             {:curr 2 :depth 1 :and :more}
                                             {:curr 6 :depth 1 :and :more}])

(defn childs-v
  [[curr dst depth]] (map (fn [o] [(o curr 2) dst (inc depth)])
                          (if (even? curr) [* / +] [* +])))

(fact
  (childs-v [4 :dst 0]) => [[8 :dst 1] [2 :dst 1] [6 :dst 1]]
  (childs-v [3 :dst 0]) => [[6 :dst 1]            [5 :dst 1]])

(defn found?
  [m] (if (= (m :curr) (m :dst)) (:depth m)))

(fact
  (found? {:curr 1 :dst 2 :depth 9}) => falsey
  (found? {:curr 1 :dst 1 :depth 9}) => 9)

(defn g
  ([a b] (g [{:curr a :dst b :depth 1}]))
  ([s] (if-let [f (found? (first s))]
         f
         (g (concat (rest s) (childs (first s)))))))

(def g
  (fn [a b] (loop [s [{:curr a :dst b :depth 1}]]
             (if-let [f (found? (first s))]
               f
               (recur (concat (rest s) (childs (first s))))))))

(def g
  (fn [a b] (loop [[f & r] [{:curr a :dst b :depth 1}]]
             (if (= (:curr f) (:dst f))
               (:depth f)
               (recur (concat r (childs f)))))))

(def g
  (fn [a b] (loop [[f & r] [[a b 1]]]
             (if (= (f 0) (f 1))
               (f 2)
               (recur (concat r (childs-v f)))))))

(def g
  (fn [a b] (loop [[f & r] [[a b 1]]]
             (if (= (f 0) (f 1))
               (f 2)
               (recur (concat r (map (fn [o] [(o (f 0) 2) (f 1) (inc (f 2))])
                                     (if (even? (f 0)) [* / +] [* +]))))))))

(def g
  (fn [a b] (loop [[f & r] [[a b 1]]]
             (let [c (f 0) d (f 1) p (f 2)]
               (if (= c d)
                 p
                 (recur (concat r (map (fn [o] [(o c 2) d (inc p)])
                                       (if (even? c) [* / +] [* +])))))))))

(def g
  (fn [a b] (loop [[[c d p] & r] [[a b 1]]]
             (if (= c d)
               p
               (recur (concat r (map (fn [o] [(o c 2) d (inc p)])
                                     (if (even? c) [* / +] [* +]))))))))

(def g
  #(loop [[[c d p] & r] [[% %2 1]]]
     (if (= c d)
       p
       (recur (concat r (map (fn [o] [(o c 2) d (inc p)])
                             (if (even? c) [* / +] [* +])))))))


;; 4clj ;;
 (fact (g 1 1)  =>  1 )                ; 1
 (fact (g 3 12) =>  3 ) ; 3 6 12
 (fact (g 12 3) =>  3 ) ; 12 6 3
 (fact (g 5 9)  =>  3 )  ; 5 7 9
 (fact (g 9 12) =>  5 ) ; 9 11 22 24 12
  (fact (g 9 2)  =>  9 )  ; 9 18 20 10 12 6 8 4 2
(future-fact


) 

