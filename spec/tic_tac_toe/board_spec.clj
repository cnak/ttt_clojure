(ns tic-tac-toe.board-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.board :refer :all]))

(describe "a board"
  (it "returns an empty board"
    (should= '(- - - - - - - -) (empty-board) )))
