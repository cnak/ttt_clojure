(ns tic-tac-toe.game-setup)

(defn setup-game-type [game-type]
   (cond 
     (= game-type :humanVhuman) {:player1 :human :player2 :human}
     (= game-type :humanVcomputer) {:player1 :human :player2 :computer}
     (= game-type :computerVcomputer) {:player1 :computer :player2 :computer}))

