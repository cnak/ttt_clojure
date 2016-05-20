(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board]
            ))

(defn get-computer-move [board]
  (player/get-move {:type-of-player :computer} board))

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

(describe "final-score"
  (it "scores 10 for a winning move"
    (should= 10 (score winning-board "X")))
  (it "scores 0 for a draw game"
    (should= 0 (score drawn-board "X")))
  (it "scores -10 for a lost game for player X"
    (should= -10 (score lost-board "X"))))

(def possible-winning-board 
  ["X" "X" "-"
   "O" "O" "X"
   "X" "O" "O"
   ])

(def possible-losing-board
  ["X" "X" "O"
   "X" "X" "-"
   "O" "-" "O"
   ])

(def possible-drawn-board
  ["-" "-" "X"
   "X" "O" "O"
   "O" "X" "X"
   ])

(describe "ai picks best move"
  (it "scores incomplete winning board"
    (should= 10 (inter-score possible-winning-board "X")))
  (it "scores incomplete losing board"
    (should= -10 (inter-score possible-losing-board "X")))
  (xit "scores incomplete drawn board"
    (should= 0 (inter-score possible-drawn-board "X")))
  )

(describe "current mark"
  (it "return O as current player"
    (should= "O" (current-mark [1 2])))
  (it "return X as current player"
    (should= "X" (current-mark [1])))
  )

