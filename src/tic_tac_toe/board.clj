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

(defn- diagonals-left [board]
  (let [step-by (Math/round (Math/sqrt (count board)))]
  (vec (range (- step-by 1) (- (count board) 1) (- step-by 1)))))

(defn- diagonals-right [board]
  (let [step-by (+ 1 (Math/round (Math/sqrt (count board))))]
    (vec (range 0 (count board) step-by))))

(defn- diagonals [board]
  (vector (diagonals-right board) (diagonals-left board)))

(defn- columns [board]
  (let [row-length (Math/round (Math/sqrt (count board)))]
    (vec (map #(vec (range % (count board) row-length)) (range 0 row-length)))))

(defn- rows [board]
  (let [row-length (Math/round (Math/sqrt (count board)))]
    (vec (map #(vec  (range % (+ % row-length) 1)) (range 0 (count board) row-length)))))

(defn- win-positions [board]
  (into (diagonals board) (into (rows board) (columns board))))

(defn- all-cell-same? [board line]
  (cells-the-same? #(get-cells % line) board)) 

(defn- any? [coll] 
  (if (some true? coll) true false))

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
 (any? (map #(all-cell-same? board %) (win-positions board))))

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
