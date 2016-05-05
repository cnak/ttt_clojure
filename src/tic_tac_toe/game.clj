(ns tic-tac-toe.game
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.board :as board]))

(def a-empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])

(defn play-turn [board]
  (let [next-state-board (board/make-move board (console/get-move-choice) 
                                          (board/current-player board))]
    (if (board/game-over? next-state-board)
      next-state-board
      (recur next-state-board))))

(defn play-game []
  (console/print-welcome-message)
  (console/ask-for-move)
  (console/print-board (play-turn a-empty-board))
  )

