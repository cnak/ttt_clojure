(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board]))

(defn get-computer-move [board]
  (player/get-move {:type-of-player :computer} board))

(def second-move-board
   ["X" "-" "-"
    "-" "-" "-"
    "-" "-" "-"])

(def block-move-board
   ["X" "X" "O"
    "-" "O" "-"
    "X" "-" "-"] )

(def win-board2
   ["X" "X" "-"
    "O" "O" "-"
    "X" "O" "X"])

(def winning-board
   ["X" "X" "-"
    "O" "O" "X"
    "X" "O" "O"] )

(def drawn
   ["X" "X" "-"
    "O" "O" "-"
    "X" "O" "X"])

(describe "best move"
  (it "picks winning move"
    (should= 2 (get-computer-move winning-board)))
  (it "picks winning move"
    (should= 5 (get-computer-move win-board2)))
  (it "blocks move 3"
    (should= 3 (get-computer-move block-move-board)))
  (it "returns move in the center"
    (should= 4 (get-computer-move second-move-board)))) 

(describe "get best"
  (it "returns best tuple for maximazing player"
    (should= [2 100] (get-best-results [[2 100]] true)))
  (it "returns worst tuple for minimizing player"
        (should= [1 -100] (get-best-results [[1 -100] [2 100]] false))))
