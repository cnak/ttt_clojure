(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board-logic]))

(defn- move [board]
  (first (board-logic/remaining-moves board)))

(defn score [board player]
  (let [winner (board-logic/winner board)]
    (if (= winner player) 10
      (if (board-logic/game-drawn? board) 0 -10))))

(defn switch-player [mark]
  (if (= mark "X") "O" "X"))

(defn score-inter [board player]
  (let [winner (board-logic/winner board)]
    (cond 
      (= winner player) -10 
      (= winner "-") 0
      (= winner (switch-player player)) 10)))

(defn score-multiple [boards mark]
  (map score-inter (vec boards) mark ))

(defn generate-possible-boards [board current-player other-player]
  (loop [next-board board]
    (if (board-logic/game-over? next-board)
      (score next-board current-player)   
      (let [open-spots (board-logic/remaining-moves next-board)]
        (recur (board-logic/make-move next-board (first open-spots) (board-logic/current-player board))))
      )))

(defn inter-score [board mark]
  (if (board-logic/game-over? board)
    (score board mark)
    ; (score-multiple (generate-possible-boards board mark (switch-player mark)) mark)))
    (generate-possible-boards board mark (switch-player mark))))

(defmethod player/get-move :computer [_ board]
  (move board))
