(ns tic-tac-toe.console-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.console :refer :all]))
(use '[clojure.java.shell :only [sh]])

(def board-four-by-four 
  (repeat 16 "-")) 

(defn art-welcome-message []
  (:out (sh "artii" "Tic Tac Toe")))

(describe "printing board"
  (it "prints a 4x4 empty board"
    (should= "- - - -\n- - - -\n- - - -\n- - - -\n" 
             (with-out-str (print-board board-four-by-four ))))

  (it "prints an empty board"
    (should= "- - -\n- - -\n- - -\n" 
             (with-out-str (print-empty-board))))
  (it "prints a board with a move"
    (should= (str row-line "- - -\n- X -\n- - -\n" row-line)
             (with-out-str (print-board ["-" "-" "-" 
                                         "-" "X" "-"
                                         "-" "-" "-"]))))
  (it "asks for a move"
    (should= "Enter a move\n" 
             (with-out-str (ask-for-move))))
  (it "gets player's move"
    (should= 2 
             (with-in-str "3"
               (get-move-choice)))) 
  (it "gets player's move"
    (should= (str invalid-move-message ask-for-move-message) 
             (with-out-str (with-in-str "NotNumber"
               (get-move-choice))))))
(describe "start game"
  (it "prints a welcome message"
    (should= (art-welcome-message)
             (with-out-str (print-welcome-message) ))))

(describe "print result"
  (it "prints X as the winner"
    (should= "\nX wins\n" 
             (with-out-str (print-result "X"))))
  (it "prints O as the winner"
    (should= "\nO wins\n" 
             (with-out-str (print-result "O"))))
  (it "prints draw result"
    (should= "\nDraw!\n" 
             (with-out-str (print-result "-")))))

(describe "menu options"
  (it "prints menu options"
    (should= (str "\n1. Human vs Human\n" "\n2. Human vs Computer\n" "\n3. Computer vs Computer\n")
             (with-out-str (print-menu))))
  (it "gets a human v human game with 3x3 board"
    (should= [[:human :human] :3x3] 
             (with-in-str "1\n1\n"
               (get-game-type))))
  (it "gets a computer v computer game choice with 3x3"
    (should= [[:computer :computer] :3x3] 
             (with-in-str "3\n1\n"
               (get-game-type))))
  (it "gets a human v computer game choice with 4x4 board"
    (should= [[:human :computer] :4x4] 
             (with-in-str "2\n2\n"
               (get-game-type)))))

(describe "valid game choice"
  (it "prints invalid option message when invalid game choice and display menu"
    (should= (str "Invalid option! Try again!" menu board-size-question)
             (with-out-str (with-in-str "0\n1\n1\n" (get-game-type)))))
  (it "asks for game type after invalid move "
    (should= [[:human :human] :3x3]
             (with-in-str "0\n1\n1\n" (get-game-type)))))

(describe "invalid board options"
  (it "prints invalid message for invalid board size"
    (should= (str board-size-question invalid-option-message board-size-question)
             (with-out-str (with-in-str "0\n1\n" (get-board-size-choice)))))
  (it "prints invalid message and asks for board size again"
    (should= :3x3
             (with-in-str "0\n1\n" (get-board-size-choice)))))

(describe "board size options"
  (it "asks the board size"
    (should= "\nWhich board size?\n1. 3x3\n2. 4x4\n"
             (with-in-str "1"
               (with-out-str (get-board-size-choice)))))
  (it "takes board size choice for 4x4"
    (should= :3x3 
             (with-in-str "1"
               (get-board-size-choice))))
  (it "takes board size choice for 4x4"
    (should= :4x4 
             (with-in-str "2"
               (get-board-size-choice)))))
