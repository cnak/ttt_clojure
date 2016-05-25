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
   "X" "O" "O"])

(def possible-losing-board
  ["X" "X" "O"
   "X" "X" "-"
   "O" "-" "O"])

(def possible-drawn-board
  ["-" "-" "X"
   "X" "O" "O"
   "O" "X" "X"])

(def winning-board-with-best-move 
  [2 ["X" "X" "X"
   "O" "O" "X"
   "X" "O" "O"]])

(describe "score-multiple"
  (it "returns winning score multiples"
    (should= {2 10} (score-multiple-moves winning-board-with-best-move "X"))))

(describe "generate board with moves"
  (it "returns 2 and final board"
    (should= [2 ["X" "X" "X"  "O" "O" "X" "X" "O" "O"]]
    (generate-possible-boards possible-winning-board "X" "O" 2))))

(describe "ai picks best move"
  (it "scores incomplete winning board"
    (should= {2 10} (inter-score possible-winning-board "X")))
  (it "scores incomplete losing board"
    (should= {5 -10} (inter-score possible-losing-board "X")))
  (it "scores incomplete drawn board"
    (should= {0 0} (inter-score possible-drawn-board "X")))
  )

