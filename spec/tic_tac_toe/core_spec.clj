(ns tic-tac-toe.core-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.core :refer :all]))

(describe "Displays a board"
  (it "retuns an empty board"
    (should= {:top-left "-"
              :top-center "-" 
              :top-right "-" 
              :middle-left "-" 
              :middle-center "-" 
              :middle-right "-" 
              :bottom-left "-" 
              :bottom-center "-" 
              :bottom-right "-"} (empty-board) )))
