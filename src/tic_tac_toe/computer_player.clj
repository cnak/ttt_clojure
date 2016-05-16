(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board-logic]))

(defn- move [board]
  (first (board-logic/remaining-moves board)))

(defn score [board player]
  (let [winner (board-logic/winner board)]
    (if (= winner player) 10
      (if (board-logic/game-drawn? board) 0 -10))))

(defmethod player/get-move :computer [_ board]
  (move board))
