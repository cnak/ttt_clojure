(ns tic-tac-toe.computer-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.computer-player :refer :all]
            [tic-tac-toe.player :as player]
            [tic-tac-toe.board :as board]))

(defn get-computer-move [board]
  (player/get-move {:type-of-player :computer} board))

(def block-move-board
   ["X" "X" "O"
    "-" "O" "-"
    "X" "-" "-"] )

(def win-board
   ["X" "X" "-"
    "O" "O" "-"
    "X" "O" "X"])

(def center-move-board
   ["X" "-" "-"
    "-" "-" "-"
    "-" "-" "-"])

(describe "ai picks best move"
  (it "picks winning move"
    (should= 5 (get-computer-move win-board)))
  (it "blocks move 3"
    (should= 3 (get-computer-move block-move-board)))
  (it "returns move in the center"
    (should= 4 (get-computer-move center-move-board)))) 
