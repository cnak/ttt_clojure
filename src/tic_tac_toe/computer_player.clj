(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board-logic]))

(defn- move [board]
  (first (board-logic/remaining-moves board)))

(defn score [board player]
  (let [winner (board-logic/winner board)]
    (if (= winner player) 10
      (if (board-logic/game-drawn? board) 0 -10))))

(defn score-multiple [boards mark]
  (score (first boards) mark)
  )

(defn generate-possible-boards [board mark]
  (let [open-spots (board-logic/remaining-moves board)]
    (for [open-spot open-spots]
      (board-logic/make-move board open-spot mark))
    ))

(defn inter-score [board mark]
  (if (board-logic/game-over? board)
    (score board mark)
    (score-multiple (generate-possible-boards board mark) mark)
    ))

(defmethod player/get-move :computer [_ board]
  (move board))
