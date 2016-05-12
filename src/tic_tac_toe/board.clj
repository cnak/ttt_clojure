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

(def winning-positions [[0 1 2] 
                        [3 4 5] 
                        [6 7 8]
                        [1 4 7]
                        [2 5 8]
                        [0 3 6]
                        [0 4 8]
                        [2 4 6]])

(defn- all-cell-same? [board line] (cells-the-same? #(get-cells % line) board)) 

(defn winner? [board]
  (loop [my-board board position-size (count winning-positions) result false]
    (if (= result true) result
    (if (= position-size 0) result
      (recur my-board (dec position-size) (all-cell-same? my-board (nth winning-positions (- position-size 1))))))))

(defn- board-empty? [board]
  (every? #{empty-mark} board))

(defn- valid-location? [location board] 
  (<= location (- (count board) 1)))

(defn- board-full? [board]
  (not-any? empty-cell? board))

(defn make-move [board location mark]
  (if (valid-location? location board)
    (assoc board location mark) board))

(defn empty-board [] (repeat 9 empty-mark))

(defn remaining-moves [board]
  (let [predicate #(= "-" %) newboard board] (keep-indexed (fn [i x] (when (predicate x) i))newboard)))

(defn game-won? [board]
  (winner? board))

(defn game-drawn? [board]
  (if (game-won? board) false
    (if (board-full? board) true false)))

(defn game-over? [board]
  (or (game-won? board) (game-drawn? board)))

(defn- number-of-moves-made [board]
  (count (filter #(not= % empty-mark) board)))

(defn- player-one-turn? [board]
  (even? (number-of-moves-made board)))

(defn current-player [board]
  (if (player-one-turn? board) "X" "O"))

(defn winner [board]
  (if (game-drawn? board)
    "-"
    (if (= (current-player board) "O") "X" "O")))

