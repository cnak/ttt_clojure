(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]
            [tic-tac-toe.board :as board]   
            [tic-tac-toe.console :as console])) 

(def empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])
(def winning-board "X O X\nO X O\nX - -\n")

(defn create-input [inputs]
  (apply str (interleave inputs (repeat "\n"))))

(describe "a multiple turn game"
  (it "welcomes and asks for move" 
    (with-in-str (create-input '("1" "2" "3" "4" "5" "6" "7"))
      (should= (str console/welcome-message console/ask-for-move-message winning-board)
               (with-out-str (play-game)))))

  (it "plays multiple turns to win game"
    (with-in-str (create-input '("1" "2" "3" "4" "5" "6" "7"))
      (should (= ["X" "O" "X"
                  "O" "X" "O"
                  "X" "-" "-"]
                 (play-turn empty-board))
              ))))
