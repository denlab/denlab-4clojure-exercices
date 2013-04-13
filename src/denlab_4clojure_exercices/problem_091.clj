(ns denlab-4clojure-exercices.problem-091
  (:require
                        [clojure.test    :as t]
                        [clojure.string  :as str])
  (:use [clojure
         [pprint              :only [pprint pp print-table              ]]
         [repl                :only [doc find-doc apropos               ]]
         [inspector           :only [inspect-tree inspect inspect-table ]]]
        [clojure.java.javadoc :only [javadoc                            ]]
        [clojure.tools.trace  :only [trace deftrace trace-forms trace-ns
                                     untrace-ns trace-vars              ]]))


;; # Graph Connectivity
;;
;; - Difficulty:	Hard
;; - Topics:	graph-theory
;;
;;
;; Given a graph, determine whether the graph is connected. A connected
;; graph is such that a path exists between any two given nodes.
;;
;; - Your function must return true if the graph is connected and false
;;   otherwise.
;; - You will be given a set of tuples representing the edges of a
;;   graph. Each member of a tuple being a vertex/node in the graph.
;; - Each edge is undirected (can be traversed either direction).


(defn f [g])

(t/deftest p91-tests
  (t/are [in ex] (= (f in) ex)
         #{[:a :a]}                                                 true
         #{[:a :b]}                                                 true
         #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4]}                     false
         #{[1 2] [2 3] [3 1] [4 5] [5 6] [6 4] [3 4]}               true
         #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e]}         false
         #{[:a :b] [:b :c] [:c :d] [:x :y] [:d :a] [:b :e] [:x :a]} true))

