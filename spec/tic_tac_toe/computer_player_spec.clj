(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board]
            ))

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
                                   ]))))
(def winning-board
  ["O" "X" "X" 
   "O" "O" "X"
   "-" "-" "X"])

(def drawn-board
  ["O" "X" "O" 
   "O" "O" "X"
   "X" "O" "X"])

(def lost-board
  ["O" "X" "X" 
   "X" "O" "-"
   "-" "-" "O"])

(describe "score"
  (it "scores 10 for a winning move"
    (should= 10 (score winning-board "X")))
  (it "scores 0 for a draw game"
    (should= 0 (score drawn-board "X")))
  (it "scores -10 for a lost game for player X"
    (should= -10 (score lost-board "X"))))

