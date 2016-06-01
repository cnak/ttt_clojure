(ns tic-tac-toe.computer-player
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as logic]))

(defn- switch-player [mark]
  (if (= mark "X") "O" "X"))

(defn- score [board depth player]
  (let [winner (logic/winner board)]
    (cond 
      (= winner player) [-1 (- 100 depth)] 
      (= winner "-") [-1 0]
      (= winner (switch-player player)) [-1 (- -100 depth)])))

(defn- get-best-results [moves-scores maximize-player?]
  (if maximize-player?  
    (first (sort-by second > moves-scores))
    (first (sort-by second < moves-scores))))

(defn- minimax [board current-player depth max-player? perspective-player]
  (if (logic/game-over? board)
    (score board depth perspective-player)
    (let [possible-moves   (logic/remaining-moves board)
          results           (map #(minimax (logic/make-move board %1 (logic/current-player board)) (switch-player current-player) (inc depth) (not max-player?) perspective-player) possible-moves)
          scores (map second results)
          move-scores (zipmap possible-moves scores)]
      (get-best-results move-scores max-player?))))

(defmethod player/get-move :computer [_ board]
  (first (minimax board (logic/current-player board) 0 true (logic/current-player board))))
