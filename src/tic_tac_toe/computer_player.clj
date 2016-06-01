(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board-logic]))

(defn- move [board]
  (first (board-logic/remaining-moves board)))

(defn- switch-player [mark]
  (if (= mark "X") "O" "X"))

(defn score [board player depth move]
  (let [winner (board-logic/winner board)]
    (cond 
      (= winner player) (move (- 100 depth)) 
      (= winner "-") [move 0]
      (= winner (switch-player player)) [move (- -100 depth)])))

(defn get-best-results [moves-scores maximize-player?]
  (if maximize-player?  
     (first (sort-by second > moves-scores))
     (first (sort-by second < moves-scores))))

(defn minimax [board current-player depth move perspective]
  (if (board-logic/game-over? board)
      (score board current-player depth move)
  (let [possible-moves   (board-logic/remaining-moves board)
        scores           (map #(minimax (board-logic/make-move board %1 (board-logic/current-player board)) (switch-player current-player) (inc depth) %1 (not perspective)) possible-moves)
        ]
    (get-best-results scores true))))

(defmethod player/get-move :computer [_ board]
  (minimax board (board-logic/current-player board) 0 -1 true))
