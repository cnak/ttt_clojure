(ns tic-tac-toe.game-setup)

(defn setup-players [game-type]
   (cond 
     (= game-type :humanVhuman) [:human :human]
     (= game-type :humanVcomputer) [:human :computer]
     (= game-type :computerVcomputer) [:computer :computer]))

