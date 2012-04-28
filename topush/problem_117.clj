(ns ^{:doc "Problem: http://www.4clojure.com/problem/117"}
    denlab-4clojure-exercices.problem-117
    (:use [midje.sweet]))

 
;; Difficulty:	Hard
;; Topics:	game


;; A mad scientist with tenure has created an experiment tracking mice in a maze. Several mazes have been randomly generated, and you've been tasked with writing a program to determine the mazes in which it's possible for the mouse to reach the cheesy endpoint. Write a function which accepts a maze in the form of a collection of rows, each row is a string where:

;;     spaces represent areas where the mouse can walk freely
;;     hashes (#) represent walls where the mouse can not walk
;;     M represents the mouse's starting point
;;     C represents the cheese which the mouse must reach

;; The mouse is not allowed to travel diagonally in the maze (only up/down/left/right), nor can he escape the edge of the maze. Your function must return true iff the maze is solvable by the mouse.

(defn maze-get
  [[y x] m] (if (and (< -1 y (count m))
                   (< -1 x (count (first m))))
            (nth (m y) x)
            \#))

(fact
  (maze-get [3 5] ["#######"
                 "#     #"
                 "#  M  #"
                 "#    C#"
                 "#######"]) => \C)

(fact
  (maze-get [-1 5] ["#######"
                 "#     #"
                 "#  M  #"
                 "#    C#"
                 "#######"]) => \#)

(defn maze-put
  [[y x] m] (if (not= \# (maze-get [y x] m))
            (update-in m [y] #(reduce str (assoc (vec %) x \M)))
            m))

(fact
  (maze-put [3 5] ["#######"
                   "#     #"
                   "#  M  #"
                   "#    C#"
                   "#######"]) => ["#######"
                                   "#     #"
                                   "#  M  #"
                                   "#    M#"
                                   "#######"])

(fact
  (maze-put [0 0] ["#######"
                 "#     #"
                 "#  M  #"
                 "#    C#"
                 "#######"]) => ["#######"
                                 "#     #"
                                 "#  M  #"
                                 "#    C#"
                                 "#######"])
(fact
  (maze-put [-1 -1] ["#######"
                 "#     #"
                 "#  M  #"
                 "#    C#"
                 "#######"]) => ["#######"
                                 "#     #"
                                 "#  M  #"
                                 "#    C#"
                                 "#######"])

(defn paint
  [m [y x]] (if (= (maze-get [y x] m) \M)
              (reduce (fn [r [y0 x0]] (maze-put [(+ y y0) (+ x x0)] r))
                      m
                      [[-1 0] [1 0] [0 -1] [0 1]])
              m))

(fact "paint"
  (paint  ["#######"
           "#     #"
           "#  M  #"
           "#    C#"
           "#######"]
          [2 3])
  => ["#######"
      "#  M  #"
      "# MMM #"
      "#  M C#"
      "#######"])

(defn allyx
  [m] (for [y (range 0 (count m)) x (range 0 (count (first m)))] [y x]))

(defn paint-all
  [m] (reduce paint
              m
              (allyx m)))

(fact "paint-all"
  (paint-all ["#######"
              "#  #M #"
              "#  #  #"
              "#  # C#"
              "#######"])
  => ["#######"
      "#  #MM#"
      "#  #MM#"
      "#  #MM#"
      "#######"])

(def g
  (letfn [(maze-get [[y x] m]
            (if (and (< -1 y (count m))
                     (< -1 x (count (first m))))
              (nth (m y) x)
              \#))
          (maze-put [[y x] m]
            (if (not= \# (maze-get [y x] m))
              (update-in m [y] #(reduce str (assoc (vec %) x \M)))
              m))
          (paint [m [y x]]
            (if (= (maze-get [y x] m) \M)
              (reduce (fn [r [y0 x0]] (maze-put [(+ y y0) (+ x x0)] r))
                      m
                      [[-1 0] [1 0] [0 -1] [0 1]])
              m))
          (paint-all [m]
            (reduce paint
                    m
                    (for [y (range 0 (count m)) x (range 0 (count (first m)))] [y x])))]
    (fn [m]
      (let [painted (ffirst (drop-while #(apply not= %) (partition 2 (iterate paint-all m))))]
        (not (.contains (reduce str painted) "C"))))))

(fact (g ["M   C"]) =>  true  )
 (fact (g ["M # C"]) =>  false )
 (fact (g ["#######"
           "#     #"
           "#  #  #"
           "#M # C#"
           "#######"]) =>  true  )
 (fact (g ["########"
           "#M  #  #"
           "#   #  #"
           "# # #  #"
           "#   #  #"
           "#  #   #"
           "#  # # #"
           "#  #   #"
           "#  #  C#"
           "########"]) =>  false )
 (fact (g ["M     "
           "      "
           "      "
           "      "
           "    ##"
           "    #C"]) =>  false )
 (fact (g ["C######"
           " #     "
           " #   # "
           " #   #M"
           "     # "]) =>  true  )
 (fact (g ["C# # # #"
           "        "
           "# # # # "
           "        "
           " # # # #"
           "        "
           "# # # #M"]) =>  true  )
(future-fact 
)
