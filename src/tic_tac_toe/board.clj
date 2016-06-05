(ns tic-tac-toe.board)

(def empty-mark "-")

(defn- empty-cell? [cell]
  (= empty-mark cell))

(defn- get-cells [board positions]
  (mapv board positions))

(defn- contains-blank-cell? [board]
  (if (some #(= empty-mark %) board) true false))

(defn- cells-the-same? [row board]
  (if (contains-blank-cell? (row board)) 
    false
    (apply = (row board))))

(defn- gen-diagonals-left [board]
  (let [step-by (Math/round (Math/sqrt (count board)))]
  (vec (range (- step-by 1) (- (count board) 1) (- step-by 1)))))

(defn- gen-diagonals-right [board]
  (let [step-by (+ 1 (Math/round (Math/sqrt (count board))))]
    (vec (range 0 (count board) step-by))))

(defn- gen-diagonals [board]
  (vector (gen-diagonals-right board) (gen-diagonals-left board)))

(defn- gen-columns [board]
  (let [row-length (Math/round (Math/sqrt (count board)))]
    (vec (map #(vec (range % (count board) row-length)) (range 0 row-length)))))

(defn- gen-rows [board]
  (let [row-length (Math/round (Math/sqrt (count board)))]
    (vec (map #(vec  (range % (+ % row-length) 1)) (range 0 (count board) row-length)))))

(defn- win-positions [board]
  (into (gen-diagonals board) (into (gen-rows board) (gen-columns board))))

(defn- all-cell-same? [board line]
  (cells-the-same? #(get-cells % line) board)) 

(defn winner? [board positions]
  (loop [my-board board 
         position-size (count positions)
         result false]
    (if result result
      (if (= position-size 0) result
        (recur my-board 
               (dec position-size)
               (all-cell-same? my-board (nth positions (- position-size 1))))))))

(defn- board-empty? [board]
  (every? #{empty-mark} board))

(defn- out-of-bounds  [location board]
  (and (<= location (- (count board) 1)) (>= location 0)))

(defn- board-full? [board]
  (not-any? empty-cell? board))

(defn valid-location? [location board] 
  (if (number? location) 
    (and (out-of-bounds location board)
         (empty-cell? (nth board location)))
    false))

(defn make-move [board location mark]
  (if (valid-location? location board)
    (assoc board location mark) 
    board))

(defn empty-board
  ([] (repeat 9 empty-mark)) 
  ([dimension] (repeat (* dimension dimension) empty-mark))) 

(defn remaining-moves [board]
  (let [predicate #(= empty-mark %) newboard board] (keep-indexed (fn [i x] (when (predicate x) i))newboard)))

(defn game-won? [board]
    (winner? board (win-positions board)))

(defn game-drawn? [board]
  (if (game-won? board) false
    (if (board-full? board) true false)))

(defn game-over? [board]
  (or (game-won? board) (game-drawn? board)))

(defn number-of-moves-made [board]
  (count (filter #(not= % empty-mark) board)))

(defn player-one-turn? [board]
  (even? (number-of-moves-made board)))

(defn current-player [board]
  (if (player-one-turn? board) "X" "O"))

(defn winner [board]
  (if (game-drawn? board)
    "-"
    (if (= (current-player board) "O")
      "X"
      "O")))
