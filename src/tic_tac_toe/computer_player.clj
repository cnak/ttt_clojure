(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board-logic]))

(defn- move [board]
  (first (board-logic/remaining-moves board)))

(defn score [board player]
  (let [winner (board-logic/winner board)]
    (if (= winner player) 10
      (if (board-logic/game-drawn? board) 0 -10))))

(defn- switch-player [mark]
  (if (= mark "X") "O" "X"))

(defn score-inter [board player]
  (let [winner (board-logic/winner board)]
    (cond 
      (= winner player) 10 
      (= winner "-") 0
      (= winner (switch-player player)) -10)))

(defn- get-board [board-score]
 (nth board-score 1))

(defn- get-score  [board-score]
 (nth board-score 0))

(defn score-multiple-moves [boards mark]
  (assoc {} (get-score boards) (score-inter (get-board boards) mark)))

(defn generate-possible-boards [board current-player other-player first-move]
  (loop [next-board board moves first-move]
    (if (board-logic/game-over? next-board)
      (into [] [first-move next-board]) 
      (let [open-spots (board-logic/remaining-moves next-board)]
        (recur (board-logic/make-move next-board (first open-spots) (board-logic/current-player next-board)) (first open-spots)))
      )))

(defn first-move [board]
  (first (board-logic/remaining-moves board)))

(defn inter-score [board mark]
  (if (board-logic/game-over? board)
    (score board mark)
    (score-multiple-moves (generate-possible-boards board mark (switch-player mark) (first-move board)) mark)))




(defmethod player/get-move :computer [_ board]
  (move board))
