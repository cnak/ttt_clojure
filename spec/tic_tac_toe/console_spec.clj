(ns tic-tac-toe.console-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console :refer :all]))

(describe "printing board"
  (it "prints an empty board"
    (should= "---\n---\n---\n" 
      (with-out-str (print-empty-board) ))))
