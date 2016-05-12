(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board-logic]))

(defn- move [board]
  (first (board-logic/remaining-moves board)))

(defmethod player/get-move :computer [_ board]
  (move board))
