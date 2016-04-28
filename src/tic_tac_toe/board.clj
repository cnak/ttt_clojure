(ns tic-tac-toe.board)

(def board
  ["-" "-" "-" "-" "-" "-" "-" "-" ])

(defn empty-board []
  board)

(defn make-move [location mark]
  (assoc board location mark))
