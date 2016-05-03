(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "a board"
  (it "returns an empty board"
    (should= ["-" "-" "-" "-" "-" "-" "-" "-" "-"]
             (empty-board) ))
  (it "makes a move in the center"
    (should= ["-" "-" "-" "-" "X" "-" "-" "-" "-"]
             (make-move ["-" "-" "-" "-" "-" "-" "-" "-" "-" ] 4 "X") ))
  (it "makes a move in the top right"
    (should= ["-" "-" "-" "-" "X" "-" "-" "-" "O"]
             (make-move ["-" "-" "-" "-" "X" "-" "-" "-" "-" ] 8 "O") ))
  (it "doesn't make an invalid move"
    (should= ["-" "-" "-" "-" "X" "-" "-" "-" "-"]
             (make-move ["-" "-" "-" "-" "X" "-" "-" "-" "-" ] 9 "O") ) )
  )

(describe "Game over"
  (it "should not be game over on start"
    (should= false
             (game-over? ["-" "-" "-" "-" "-" "-" "-" "-" "-"])))
  )

(describe "Game won"
  (it "should return true when game is won on top row"
    (should= true
             (game-won? ["X" "X" "X" 
                        "-" "-" "-"
                        "-" "-" "-"])))
  (it "should return false when game is not three in a row on top row"
    (should= false
             (game-won? ["-" "X" "X" 
                        "-" "-" "-"
                        "-" "-" "-"])))
  (it "should return true middle row as winner"
    (should= true
             (middle-row-the-same? 
                       ["-" "X" "X" 
                        "O" "O" "O"
                        "-" "-" "-"])))
  (xit "should return true bottom row as winner"
    (should= true
             (middle-row-the-same? 
                       ["-" "X" "X" 
                        "-" "O" "O"
                        "X" "X" "X"])))
  (it "should return false middle row has blank "
    (should= false
             (middle-row-the-same? 
                       ["-" "X" "X" 
                        "-" "-" "-"
                        "-" "-" "-"])))
  )

