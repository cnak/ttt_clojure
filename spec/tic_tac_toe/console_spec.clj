(ns tic-tac-toe.console-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console :refer :all]))

(describe "printing board"
  (it "prints an empty board"
    (should= "---\n---\n---\n" 
             (with-out-str (print-empty-board) )))
  (it "asks for a move"
    (should= "Enter a move" 
             (with-out-str (ask-for-move))))
  (it "gets player's move"
    (should= "3" 
             (with-in-str "3"
                            (get-move))))) 
