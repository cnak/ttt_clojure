(ns tic-tac-toe.game
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.board :as board]))

(def a-empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])

(defn play-turn [board]
  (let [next-state-board (board/make-move board (console/get-move-choice) 
                                          (board/current-player board))]
    (console/print-board next-state-board)
    (if (board/game-over? next-state-board)
      next-state-board 
      (recur next-state-board))))

(defn start []
  (console/print-welcome-message)
  (console/print-menu)
  (console/get-game-choice)
  )

(defn play-game []
  (console/print-board a-empty-board)
  (console/ask-for-move)
  (console/print-result (board/winner (play-turn a-empty-board))) 
  )
