(ns tic-tac-toe.board)

(defn middle-row [board]
  (take 3 (drop 3 board)))

(defn bottom-row [board]
  (take 3 (drop 6 board)))

(defn contains-blank-cell? [board]
  (if (some #(= "-" %) board) 
    true
    false
    ))

(defn middle-row-the-same? [board]
  (if (contains-blank-cell? (middle-row board)) 
    false
    (apply = (middle-row board)) 
        ))

(defn- board-empty? [board]
  (every? #{"-"} board))

(defn- valid-location? [location board] 
  (<= location (- (count board) 1)))

(defn make-move [board location mark]
  (if (valid-location? location board)
    (assoc board location mark) board))

(defn empty-board [] (repeat 9 "-"))

(defn game-over? [board]
  (if (board-empty? board)
    false)
  )

(defn game-won? [board]
  (or (apply = (take 3 board))))

