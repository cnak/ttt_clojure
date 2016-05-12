(ns tic-tac-toe.game
  (:require [tic-tac-toe.console :as console]
            [tic-tac-toe.board :as board]
            [tic-tac-toe.human-player :as human]
            [tic-tac-toe.computer-player :as computer]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.game-setup :as setup-game]
            ))

(def a-empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])

(defn get-player-move [player1 player2 board]
  (if (board/player-one-turn? board) 
  (player/get-move {:type-of-player player1} board) 
  (player/get-move {:type-of-player player2} board)))

(defn play-turn [board players]
  (let [player1 (nth players 0) 
        player2 (nth players 1)
        next-state-board (board/make-move board 
                                          (get-player-move player1 player2 board) 
                                          (board/current-player board))]
    (console/print-board next-state-board)
    (if (board/game-over? next-state-board)
      next-state-board 
      (recur next-state-board players))))

(defn play-game [players]
  (console/print-board a-empty-board)
  (console/ask-for-move)
  (console/print-result (board/winner (play-turn a-empty-board players))) 
  )

(defn start []
  (console/print-welcome-message)
  (console/print-menu)
  (play-game (setup-game/setup-players (console/get-game-choice)))
  )
