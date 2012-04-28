(ns ^{:doc "Problem: http://www.4clojure.com/problem/94"}
    denlab-4clojure-exercices.problem-094
    (:use [midje.sweet])
        (:use [clojure.repl :only [doc]]))

;; Game of Life
 
;; Difficulty:	Hard
;; Topics:	game


;; The game of life is a cellular automaton devised by mathematician John Conway.

;; The 'board' consists of both live (#) and dead ( ) cells. Each cell interacts with its eight neighbours (horizontal, vertical, diagonal), and its next state is dependent on the following rules:

;; 1) Any live cell with fewer than two live neighbours dies, as if caused by under-population.
;; 2) Any live cell with two or three live neighbours lives on to the next generation.
;; 3) Any live cell with more than three live neighbours dies, as if by overcrowding.
;; 4) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

;; Write a function that accepts a board, and returns a board representing the next generation of cells.

(defn log-dec
  [f] (fn [& args] (println f args) (apply f args)))

(defn life-get
  [[y x] m] (if (and (< -1 y (count m)) (< -1 x (count (first m))))
              (nth (m y) x)
              \space))

(comment (def life-get (log-dec life-get)))

(fact
  (life-get [3 5] ["#######"
                   "#     #"
                   "#  M  #"
                   "#    C#"
                   "#######"]) => \C)

(defn life-put
  [[y x] m v] (update-in m [y] #(reduce str (assoc (vec %) x v))))

(comment (def life-put (log-dec life-put)))

(defn life-nb-neig
  [[y x] b] (reduce (fn [r [y0 x0]] (if (= (life-get [(+ y y0) (+ x x0)] b) \#)
                                     (inc r)
                                     r))
                    0
                    (remove #(= [0 0] %) (for [y (range -1 2) x (range -1 2)] [y x]))))

(defn allyx
  [m] (for [y (range 0 (count m)) x (range 0 (count (first m)))] [y x]))

(fact "life-nb-neig"
  (life-nb-neig [1 1] [" # "
                       "# #"
                       "   "]) => 3)

(fact "life-nb-neig"
  (life-nb-neig [1 1] [" # "
                       "###"
                       "   "]) => 3)

(fact
  (life-put [3 5] ["#######"
                   "#     #"
                   "#  M  #"
                   "#    C#"
                   "#######"] \M) => ["#######"
                                   "#     #"
                                   "#  M  #"
                                   "#    M#"
                                   "#######"])

(defn new-board
  [b] (map (fn [_] (map (constantly \space)
                                              (range 0 (count (first b)))))
                                        (range 0 (count b))))

(fact
  (new-board ["123"
              "456"]) => [[\space \space \space]
                          [\space \space \space]])

(comment
  (reduce (fn [r i] (let [c (life-nb-neig i b)]
                     (if (= (life-get i b) \#)
                       (if (or (< c 2) (< 3 c))
                         (life-put i r \space)
                         r)
                       (if (= 3 c)
                         (life-put i r \#)
                         r))))
          b
          (allyx b)))


(defn g
  [b] (reduce (fn [r i] (let [c (life-nb-neig i b)]
                         (if (= (life-get i b) \#)
                           (if (or (< c 2) (< 3 c))
                             (life-put i r \space)
                             r)
                           (if (= 3 c)
                             (life-put i r \#)
                             r))))
              b
              (allyx b)))

(def g
  (fn [b] (reduce (fn [r i] (let [c (life-nb-neig i b)]
                            (if (= (life-get i b) \#)
                              (if (<= 2 c 3)
                                r
                                (life-put i r \space))
                              (if (= 3 c)
                                (life-put i r \#)
                                r))))
                 b
                 (allyx b))))

(def g
  (fn [b] (reduce (fn [r i] (let [c (life-nb-neig i b)
                                cell (if (= (life-get i b) \#)
                                       (if (<= 2 c 3) \# \space)
                                       (if (= 3 c)    \# \space))]
                            (life-put i r cell)))
                 b
                 (allyx b))))

(def g
  (letfn [(yx   [m        ] (for [y (range 0 (count m)) x (range 0 (count (first m)))] [y x]))
          (lget [[y x] m  ] (if (and (< -1 y (count m)) (< -1 x (count (first m))))
                              (nth (m y) x)
                              \space))
          (lput [[y x] m v] (update-in m [y] #(reduce str (assoc (vec %) x v))))
          (lnb [[y x] b   ] (reduce (fn [r [y0 x0]] (if (= (lget [(+ y y0) (+ x x0)] b) \#)
                                                     (inc r)
                                                     r))
                                    0
                                    (remove #(= [0 0] %) (for [y (range -1 2) x (range -1 2)] [y x]))))]
    (fn [b] (reduce (fn [r i] (let [c (lnb i b)]
                              (lput i r (if (= (lget i b) \#)
                                          (if (<= 2 c 3) \# \space)
                                          (if (= 3 c)    \# \space)))))
                   b
                   (yx b)))))

(comment (fact "g: birth"
   (g ["      " 
       " ###  "]) => 
   ["  #   " 
    " ###  "])

         (fact "g: survive because has two neigboors"
           (g ["  #   " 
               " # #  "
               "  #   "]) => 
               ["  #   " 
                " # #  "
                "  #   "])

         (fact "g: death by surpopulation"
           (g ["  #   " 
               " ###  "
               "  #   "]) => 
               ["  #   " 
                " # #  "
                "  #   "]))


;; 4clj ;;
 (fact (g ["      " 
           " ##   "
           " ##   "
           "   ## "
           "   ## "
           "      "]) => 
           ["      " 
            " ##   "
            " #    "
            "    # "
            "   ## "
            "      "])
 (fact (g ["     "
           "     "
           " ### "
           "     "
           "     "]) => 
           ["     "
            "  #  "
            "  #  "
            "  #  "
            "     "])
 (fact (g ["      "
           "      "
           "  ### "
           " ###  "
           "      "
           "      "]) => 
           ["      "
            "   #  "
            " #  # "
            " #  # "
            "  #   "
            "      "])
(comment 


)
