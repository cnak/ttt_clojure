(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]
            [tic-tac-toe.player :as player]))

(defn get-computer-move [board]
  (player/get-move {:player_type :computer} board))

(describe "random move"
  (it "picks a last remaining space for a move"
    (should= 9 (get-computer-move ["X" "O" "X"
                                   "O" "X" "O"
                                   "O" "X" "-"
                                   ])))
  (it "picks a first available space for a move"
    (should= 2 (get-computer-move ["X" "-" "-"
                                   "-" "-" "-"
                                   "-" "-" "-"
                                   ])))
  )

