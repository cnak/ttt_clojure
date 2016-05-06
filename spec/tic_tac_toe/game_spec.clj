(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]))

(def empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])
(def welcome-message "\nWelcome to Tic Tac Toe\n" )
(def empty-board-message "- - -\n- - -\n- - -\n")
(def enter-move-message "Enter a move\n" )
(def winning-sequence "X - -\n- - -\n- - -\nX O -\n- - -\n- - -\nX O X\n- - -\n- - -\nX O X\nO - -\n- - -\nX O X\nO X -\n- - -\nX O X\nO X O\n- - -\nX O X\nO X O\nX - -\nX O X\nO X O\nX - -\n")

(def entire-game-sequence (str welcome-message empty-board-message enter-move-message winning-sequence))

(defn create-input [inputs]
  (apply str (interleave inputs (repeat "\n"))))

(describe "a multiple turn game"
  (it "welcomes the user" 
    (with-in-str (create-input '("1" "2" "3" "4" "5" "6" "7"))
      (should (boolean (re-find #"\nWelcome to Tic Tac Toe\n"
                                (str (with-out-str (play-game))))))))
  (it "asks the user for move" 
    (with-in-str (create-input '("1" "2" "3" "4" "5" "6" "7"))
      (should (boolean (re-find #"\nEnter a move\n"
                                (str (with-out-str (play-game))))))))

  (it "prints X as winner" 
    (with-in-str (create-input '("1" "2" "3" "4" "5" "6" "7"))
      (should (boolean (re-find #"\nX wins\n"
                                (str (with-out-str (play-game))))))))
  (it "prints O as winner" 
    (with-in-str (create-input '("1" "2" "3" "5" "4" "8"))
      (should (boolean (re-find #"\nO wins\n"
                                (str (with-out-str (play-game))))))))

  (it "prints board on each turn"
    (with-in-str (create-input '("1" "2" "3" "4" "5" "6" "7"))
      (should (boolean (re-find #"X O -"
                                (str (with-out-str (play-turn empty-board)))
                                ))))))

