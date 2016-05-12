(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]
            [tic-tac-toe.console :as console]))

(def empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])

(defn create-input [inputs]
  (apply str (interleave inputs (repeat "\n"))))

(defn drawn-game-input [] (create-input '("1" "2" "3" "5" "8" "4" "6" "9" "7")))
(defn O-winner-game-input [] (create-input '("1" "2" "3" "5" "4" "8")))
(defn X-winner-game-input [] (create-input '("1" "2" "3" "4" "5" "6" "7")))

(def humanVhuman-players [:human :human])
(def humanVcomputer-players [:human :computer])
(def computerVcomputer-players [:computer :computer])

(describe "start of app"
  (it "welcomes the user" 
    (with-in-str (create-input '("1" "2" "3" "4" "5" "6" "7"))
      (should-invoke console/print-welcome-message {:times 1} (start))  
      ))
  (it "prints game options"
    (with-in-str "1\n"
      (should-invoke console/print-menu {:times 1} (start)))))

(describe "a multiple turn game"
  (it "asks the user for move" 
    (with-in-str (create-input '("1" "2" "3" "4" "5" "6" "7"))
      (should-invoke console/ask-for-move {:times 1} (play-game humanVhuman-players))))
  (it "prints X as winner" 
    (with-in-str (X-winner-game-input)
      (should (boolean (re-find #"\nX wins\n"
                                (str (with-out-str (play-game humanVhuman-players))))))))
  (it "prints O as winner" 
    (with-in-str (O-winner-game-input)
      (should (boolean (re-find #"\nO wins\n"
                                (str (with-out-str (play-game humanVhuman-players))))))))
  (it "prints Draw! for drawn game" 
    (with-in-str (drawn-game-input)
      (should (boolean (re-find #"\nDraw!\n"
                                (str (with-out-str (play-game humanVhuman-players))))))))
  (it "prints board on each turn"
    (with-in-str (create-input '("1" "2" "3" "4" "5" "6" "8" "7" "9"))
      (should (boolean (re-find #"X O -"
                                (str (with-out-str (play-turn empty-board humanVhuman-players)))
                                ))))))
(describe "game setup"
  (it "asks for game type" 
    (with-in-str "1\n"
      (should-invoke console/get-game-choice {:times 1} (start))))
  (it "plays a human v computer game" 
    (with-in-str (create-input '("1" "3" "5" "7"))
      (should (boolean (re-find #"\nX wins\n"
                                (str (with-out-str (play-game humanVcomputer-players)))))))
   (it "plays a computer v computer game" 
      (should (boolean (re-find #"\nO wins\n"
                                (str (with-out-str (play-game computerVcomputer-players)))))))
    
    ))
