(ns tic-tac-toe.player)

(defmulti get-move (fn [type-of-player board]
                         (type-of-player :type-of-player)))
