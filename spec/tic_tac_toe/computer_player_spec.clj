(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]
            [tic-tac-toe.player :as player]))

(defn get-computer-move [board]
  (player/get-move {:type-of-player :computer} board))

(describe "random move"
  (it "picks a last remaining space for a move"
    (should= 8 (get-computer-move ["X" "O" "X"
                                   "O" "X" "O"
                                   "O" "X" "-"
                                   ])))
  (it "picks a first available space for a move"
    (should= 1 (get-computer-move ["X" "-" "-"
                                   "-" "-" "-"
                                   "-" "-" "-"
                                   ])))
  )

