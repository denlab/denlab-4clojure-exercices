(ns ^{:doc "Problem: http://www.4clojure.com/problem/153"}
    denlab-4clojure-exercices.problem-153
    (:use
     [clojure.repl   :only [doc]]
     [clojure.pprint :only [pprint]]
     [midje.sweet]))

;; Pairwise Disjoint Sets
 
;; Difficulty:	Easy
;; Topics:	set-theory


;; Given a set of sets, create a function which returns true if no two
;; of those sets have any elements in common1 and false
;; otherwise. Some of the test cases are a bit tricky, so pay a little
;; more attention to them.

;; 1Such sets are usually called pairwise disjoint or mutually
;; disjoint.
 
(defn g
  [s] (< (count (reduce clojure.set/intersection s)) 2))

(defn not-intersect-any
  [s ss] (every? empty? (map #(clojure.set/intersection s %) ss)))

(defn not-intersect-any
  [s ss] (not (some s (apply clojure.set/union ss))))

(comment #{#{(#(-> *)) + (quote mapcat)}
           #{'+ '* mapcat}
           #{set contains? nil?}
           #{}})

(fact "not-intersect-any"
      (not-intersect-any #{} 
                         [#{'+ '* mapcat}
                          #{set contains? nil?}
                          #{(#(-> *)) + (quote mapcat)}])
      => true) 

(fact "not-intersect-any"
      (not-intersect-any #{(#(-> *)) + (quote mapcat)}
                         [#{'+ '* mapcat}
                          #{set contains? nil?}
                          #{}]) => true) 

(fact "not-intersect-any"
      (not-intersect-any #{:a :e} [#{:b :c} #{:c :d}]) => true
      (not-intersect-any #{:a :e} [#{:b :c} #{:c :a}]) => false)

(defn g
  [s] (if (< 1 (count s))
        (if (not-intersect-any (first s) (rest s))
          (g (rest s))
          false) 
        true))

(defn g
  [s] (let [f (first s) r (next s)]
        (if r
          (if (some (first s) (apply clojure.set/union r))
            false
            (g r)) 
          true)))

(defn g
  [s] (if-let [r (next s)]
        (if (every? empty?
                    (map #(clojure.set/intersection (first s) %) 
                                r)) 
          (g r)
          false) 
        true))

(defn g
  [s] (if-let [r (next s)]
        (if (some (first s) (apply clojure.set/union r)) 
          false
          (g r)) 
        true))

(defn g
  [s] (if-let [r (next s)]
        (if (some #(contains? (first s) %) (apply clojure.set/union r)) 
          false
          (g r)) 
        true))

(defn g
  [s] (if-let [r (next s)]
        (if (some (partial contains? (first s)) (apply clojure.set/union r)) 
          false
          (g r)) 
        true))

(defn g
  [s] (= (count (set (mapcat identity s)))
         (reduce + (map count s))))

(defn g
  [s] (= (count (reduce into s)) (reduce + (map count s))))

(def g
  #(= (count (reduce into %)) (reduce + (map count %))))

(fact
  (g #{#{\U} #{\s} #{\e \R \E} #{\P \L} #{\.}}) => true)

 (fact (g #{#{:a :b :c :d :e}
            #{:a :b :c :d}
            #{:a :b :c}
            #{:a :b}
            #{:a}}) => 
            false)

 (fact (g #{#{[1 2 3] [4 5]}
            #{[1 2] [3 4 5]}
            #{[1] [2] 3 4 5}
            #{1 2 [3 4] [5]}}) => 
            true)

 (fact (g #{#{'a 'b}
            #{'c 'd 'e}
            #{'f 'g 'h 'i}
            #{''a ''c ''f}}) => 
            true)

 (fact (g #{#{'(:x :y :z) '(:x :y) '(:z) '()}
            #{#{:x :y :z} #{:x :y} #{:z} #{}}
            #{'[:x :y :z] [:x :y] [:z] [] {}}}) => 
            false)
 (fact (g #{#{(= "true") false}
            #{:yes :no}
            #{(class 1) 0}
            #{(symbol "true") 'false}
            #{(keyword "yes") ::no}
            #{(class '1) (int \0)}}) => 
            false)

 (fact (g #{#{distinct?}
            #{#(-> %) #(-> %)}
            #{#(-> %) #(-> %) #(-> %)}
            #{#(-> %) #(-> %) #(-> %)}}) => 
            true)

 (fact (g #{#{(#(-> *)) + (quote mapcat) #_ nil}
            #{'+ '* mapcat (comment mapcat)}
            #{(do) set contains? nil?}
            #{, , , #_, , empty?}}) => 
            false)

