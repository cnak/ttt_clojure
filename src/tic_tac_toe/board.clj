(ns tic-tac-toe.board)

(defn- valid-location? [location board] 
  (<= location (- (count board) 1)))

(defn make-move [board location mark]
  (if (valid-location? location board)
    (assoc board location mark) board))

(defn empty-board [] (repeat 9 "-"))

