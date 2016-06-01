(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(def a-empty-board 
  ["-" "-" "-" "-" "-" "-" "-" "-" "-"])

(def a-4x4-empty-board 
  ["-" "-" "-" "-"
   "-" "-" "-" "-"
   "-" "-" "-" "-"
   "-" "-" "-" "-"])

(describe "remaining moves"
  (it "returns 0 as remaining move"
    (should= '(0 1 2 3 4 5 6 7 8) (remaining-moves a-empty-board)))
  (it "returns 4 as remaining move"
    (should= '(4) (remaining-moves ["O" "O" "X" 
                                    "X" "-" "O"
                                    "O" "O" "X"])))
  (it "returns no remaining move"
    (should= '() (remaining-moves ["O" "O" "X" 
                                   "X" "X" "O"
                                   "O" "O" "X"])))
  )

(describe "a 4x4 board"
  (it "returns an 4x4 empty board"
    (should= a-4x4-empty-board
             (empty-board 4) 
             ))
  (it "makes a move in the bottom right"
    (should= ["-" "-" "-" "-"
              "-" "-" "-" "-"
              "-" "-" "-" "-"
              "-" "-" "-" "O"]
             (make-move ["-" "-" "-" "-"
                         "-" "-" "-" "-"
                         "-" "-" "-" "-"
                         "-" "-" "-" "-"]
                        15 "O"))))

(describe "board 4x4 game won"
  (it "should return true when game is won on top row"
    (should= true
             (game-won? ["X" "X" "X" "X" 
                         "-" "-" "-" "-"
                         "-" "-" "-" "-"
                         "-" "-" "-" "-"])))
  (it "should return false when game is not four in a row on top row"
    (should= false
             (game-won? ["X" "X" "X" "-" 
                         "-" "-" "-" "X"
                         "-" "-" "-" "X"
                         "-" "-" "-" "-"])))
  (it "should return true middle row as winner"
    (should= true
             (game-won? 
               ["-" "X" "X" "-" 
                "O" "O" "O" "O"
                "-" "-" "-" "-"
                "-" "-" "-" "-"])))
  (it "should return true middle-bottom row as winner"
    (should= true
             (game-won?
               ["-" "X" "X" "-"
                "-" "O" "O" "-"
                "O" "O" "O" "O"
                "X" "X" "-" "X"])))
  (it "should return true bottom row as winner"
    (should= true
             (game-won?
               ["-" "X" "X" "-"
                "-" "O" "O" "-"
                "-" "O" "O" "-"
                "X" "X" "X" "X"])))
  (it "should return win for left column"
    (should= true
             (game-won? 
               ["X" "-" "-" "-" 
                "X" "-" "-" "-"
                "X" "-" "-" "-"
                "X" "-" "-" "-"])))
  (it "should return win for left middle column"
    (should= true
             (game-won? 
               ["-" "X" "-" "-" 
                "-" "X" "-" "-"
                "-" "X" "-" "-"
                "-" "X" "-" "-"])))
  (it "should return win for right middle column"
    (should= true
             (game-won? 
               ["-" "-" "X" "-" 
                "-" "-" "X" "-"
                "-" "-" "X" "-"
                "-" "-" "X" "-"])))
  (it "should return win for right column"
    (should= true
             (game-won? 
               ["-" "-" "-" "X" 
                "-" "-" "-" "X"
                "-" "-" "-" "X"
                "-" "-" "-" "X"])))
  (it "should return win for diagonal top-left"
    (should= true
             (game-won? 
               ["X" "-" "-" "-"
                "-" "X" "-" "-"
                "-" "-" "X" "-"
                "-" "-" "-" "X"
                ])))
  (it "should return win for diagonal top-right"
    (should= true
             (game-won? 
               ["-" "-" "-" "X"
                "-" "-" "X" "-"
                "-" "X" "-" "-"
                "X" "-" "-" "-"]))))


(describe "a board"
  (it "returns an empty board"
    (should= a-empty-board
             (empty-board) ))
  (it "makes a move in the center"
    (should= ["-" "-" "-" "-" "X" "-" "-" "-" "-"]
             (make-move ["-" "-" "-" "-" "-" "-" "-" "-" "-" ] 4 "X") ))
  (it "makes a move in the bottom right"
    (should= ["-" "-" "-"
              "-" "X" "-"
              "-" "-" "O"]
             (make-move ["-" "-" "-" "-" "X" "-" "-" "-" "-" ] 8 "O") ))
  (it "doesn't make an invalid out of bound move"
    (should= ["-" "-" "-" "-" "X" "-" "-" "-" "-"]
             (make-move ["-" "-" "-"
                         "-" "X" "-"
                         "-" "-" "-"] 9 "O"))))

(describe "Game over"
  (it "should be game over when game is drawn"
    (should= true
             (game-over? ["O" "O" "X" 
                          "X" "X" "O"
                          "O" "O" "X"])))
  (it "should be game over game is won"
    (should= true
             (game-over? ["X" "X" "X" 
                          "-" "-" "-"
                          "-" "-" "-"])))
  (it "should not be game over on start"
    (should= false
             (game-over? a-empty-board)))
  (it "should be game over game is won"
    (should= true
             (game-over? ["X" "-" "-"
                          "X" "-" "-"
                          "X" "-" "-"])))
  (it "should not be game over when remaining moves left"
    (should= false
             (game-over? ["X" "X" "-"
                          "O" "O" "-"
                          "X" "O" "O"])))
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
             (game-won? 
               ["-" "X" "X" 
                "O" "O" "O"
                "-" "-" "-"])))
  (it "should return true top row as winner"
    (should= true
             (game-won?
               ["X" "X" "X" 
                "-" "O" "O"
                "X" "-" "X"])))
  (it "should return true middle row has blank "
    (should= false
             (game-won? 
               ["-" "X" "X" 
                "-" "-" "-"
                "-" "-" "-"])))
  (it "should return true bottom row as winner"
    (should= true
             (game-won?
               ["-" "X" "X" 
                "-" "O" "O"
                "X" "X" "X"])))
  (it "should return false middle row has blank "
    (should= false
             (game-won? 
               ["-" "X" "X" 
                "-" "-" "-"
                "-" "-" "-"])))
  (it "should return win for left column"
    (should= true
             (game-won? 
               ["X" "-" "-" 
                "X" "-" "-"
                "X" "-" "-"])))
  (it "should return win for middle column"
    (should= true
             (game-won? 
               ["-" "X" "-" 
                "-" "X" "-"
                "-" "X" "-"])))
  (it "should return win for right column"
    (should= true
             (game-won? 
               ["-" "-" "O" 
                "-" "-" "O"
                "-" "-" "O"])))
  (it "should return win for diagonal top-left"
    (should= true
             (game-won? 
               ["X" "-" "-" 
                "-" "X" "-"
                "-" "-" "X"])))
  (it "should return win for diagonal top-right"
    (should= true
             (game-won? 
               ["-" "-" "X" 
                "-" "X" "-"
                "X" "-" "-"])))
  )

(describe "Drawn game"
  (it "should return false when remaining moves"
    (should= false
             (game-drawn? 
               a-empty-board
               )))
  (it "should return true when no remaining moves"
    (should= true
             (game-drawn? 
               ["X" "O" "X" 
                "O" "X" "O"
                "O" "X" "O"])))
  (it "should return false when game is won"
    (should= false
             (game-drawn? 
               ["X" "O" "X" 
                "O" "X" "O"
                "-" "-" "X"]))))

(describe "Switch player"
  (it "current player X on first move"
    (should= "X"
             (current-player 
               ["-" "-" "-" 
                "-" "-" "-"
                "-" "-" "-"])))
  (it "should switch current player O on second move"
    (should= "O"
             (current-player 
               ["-" "-" "X" 
                "-" "-" "-"
                "-" "-" "-"]))))

(describe "Game Result"
  (it "returns X as winner on a 4x4"
    (should= "O"
             (winner 
               ["X" "X" "X" "-" 
                "O" "O" "O" "O"
                "-" "-" "X" "-"
                "-" "-" "-" "-"])))
  (it "returns X as winner"
    (should= "X"
             (winner 
               ["X" "X" "X" 
                "-" "-" "-"
                "-" "-" "-"])))
  (it "returns O as winner"
    (should= "O"
             (winner 
               ["X" "X" "O" 
                "X" "O" "-"
                "O" "-" "-"])))
  (it "returns - for draw"
    (should= "-"
             (winner 
               ["X" "X" "O" 
                "O" "O" "X"
                "X" "X" "O"])))
  )
