(ns tic-tac-toe.board)

(defn empty-cell? [cell]
  (= "-" cell))

(defn- get-cells [board positions]
  (mapv board positions))

(defn- contains-blank-cell? [board]
  (if (some #(= "-" %) board) true false))

(defn- cells-the-same? [row board]
  (if (contains-blank-cell? (row board)) 
    false
    (apply = (row board))))

(defn top-row-the-winner? [board]
  (cells-the-same? #(get-cells % [0 1 2])board))

(defn middle-row-the-winner? [board]
  (cells-the-same? #(get-cells % [3 4 5])board))

(defn- bottom-row-the-winner? [board]
  (cells-the-same? #(get-cells % [6 7 8])board))

(defn- left-column-winner? [board]
  (cells-the-same? #(get-cells % [0 3 6])board))

(defn- middle-column-winner? [board]
  (cells-the-same? #(get-cells % [1 4 7])board)) 

(defn- right-column-winner? [board]
  (cells-the-same? #(get-cells % [2 5 8])board)) 

(defn diagonal-top-left-winner? [board]
  (cells-the-same? #(get-cells % [0 4 8])board))

(defn diagonal-top-right-winner? [board]
  (cells-the-same? #(get-cells % [2 4 6])board))

(defn- board-empty? [board]
  (every? #{"-"} board))

(defn- valid-location? [location board] 
  (<= location (- (count board) 1)))

(defn make-move [board location mark]
  (if (valid-location? location board)
    (assoc board location mark) board))

(defn empty-board [] (repeat 9 "-"))

(defn game-won? [board]
  (or (top-row-the-winner? board) (middle-row-the-winner? board) (bottom-row-the-winner? board) 
      (left-column-winner? board) (middle-column-winner? board) (right-column-winner? board) 
      (diagonal-top-left-winner? board)
      (diagonal-top-right-winner? board)
      ))

(defn game-over? [board]
  (if (board-empty? board)
    false
    (game-won? board)))

(defn board-full? [board]
  (not-any? empty-cell? board)
  )

(defn game-drawn? [board]
  (or (game-won? board) (board-full? board)))

