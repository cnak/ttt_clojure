(ns tic-tac-toe.board)

(defn- cells-the-same? [board positions]
  (mapv board positions)
  )

(defn- top-row [board]
  (cells-the-same? board [0 1 2]))

(defn- middle-row [board]
  (cells-the-same? board [3 4 5]))

(defn- bottom-row [board]
  (cells-the-same? board [6 7 8]))

(defn- left-column [board]
  (cells-the-same? board [0 3 6]))

(defn- middle-column [board]
  (cells-the-same? board [1 4 7]))

(defn- right-column [board]
  (cells-the-same? board [2 5 8]))

(defn any-winning-positions? [board]
  (cells-the-same? board))

(defn- contains-blank-cell? [board]
  (if (some #(= "-" %) board) true false))

(defn- row-the-same [row board]
  (if (contains-blank-cell? (row board)) 
    false
    (apply = (row board))))

(defn top-row-the-winner? [board]
  (row-the-same top-row board))

(defn middle-row-the-winner? [board]
  (row-the-same middle-row board))

(defn- bottom-row-the-winner? [board]
  (row-the-same bottom-row board))

(defn- left-column-winner? [board]
  (row-the-same left-column board))

(defn- middle-column-winner? [board]
  (row-the-same middle-column board))

(defn- right-column-winner? [board]
  (row-the-same right-column board))

(defn- board-empty? [board]
  (every? #{"-"} board))

(defn- valid-location? [location board] 
  (<= location (- (count board) 1)))

(defn make-move [board location mark]
  (if (valid-location? location board)
    (assoc board location mark) board))

(defn empty-board [] (repeat 9 "-"))

(defn game-won? [board]
  (or (top-row-the-winner? board) (middle-row-the-winner? board) (bottom-row-the-winner? board) (left-column-winner? board) (middle-column-winner? board) (right-column-winner? board)))

(defn game-over? [board]
  (if (board-empty? board)
    false
    (game-won? board)))

