(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board-logic]))

(defn- move [board]
  (first (board-logic/remaining-moves board)))

(defn- switch-player [mark]
  (if (= mark "X") "O" "X"))

(defn score [board player depth move real]
  (let [winner (board-logic/winner board)]
    (cond 
      (= winner real) [-1 (- 100 depth)] 
      (= winner "-") [-1 0]
      (= winner (switch-player real)) [-1 (- -100 depth)])))

(defn get-best-results [moves-scores maximize-player?]
    (if maximize-player?  
       (first (sort-by second > moves-scores))
       (first (sort-by second < moves-scores))))

(defn minimax [board current-player depth move perspective real]
  (if (board-logic/game-over? board)
      (score board current-player depth move real)
  (let [possible-moves   (board-logic/remaining-moves board)
        results           (map #(minimax (board-logic/make-move board %1 (board-logic/current-player board)) (switch-player current-player) (inc depth) %1 (not perspective) real) possible-moves)
        scores (map second results)
        move-scores (zipmap possible-moves scores)
        ]
    (get-best-results move-scores perspective))))

(defmethod player/get-move :computer [_ board]
  (first (minimax board (board-logic/current-player board) 0 -1 true (board-logic/current-player board))))
