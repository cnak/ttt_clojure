(ns tic-tac-toe.console-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console :refer :all]))

(describe "printing board"
  (it "prints an empty board"
    (should= "- - -\n- - -\n- - -\n" 
             (with-out-str (print-empty-board) )))
  (it "prints a board with a move"
    (should= "- - -\n- X -\n- - -\n" 
             (with-out-str (print-board ["-" "-" "-" "-" "X" "-" "-" "-" "-"]) )))
  (it "asks for a move"
    (should= "Enter a move\n" 
             (with-out-str (ask-for-move))))
  (it "gets player's move"
    (should= 2 
             (with-in-str "3"
               (get-move-choice))))
  ) 
(describe "start game"
  (it "prints a welcome message"
    (should= "\nWelcome to Tic Tac Toe\n" 
             (with-out-str (print-welcome-message) )))
  )

(describe "print result"
  (it "prints X as the winner"
    (should= "\nX wins\n" 
             (with-out-str (print-result "X") )))
  (it "prints O as the winner"
    (should= "\nO wins\n" 
             (with-out-str (print-result "O") )))
  (it "prints draw result"
    (should= "\nDraw!\n" 
             (with-out-str (print-result "-") )))
  )

