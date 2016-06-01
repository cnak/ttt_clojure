(ns tic-tac-toe.game-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game :refer :all]
            [tic-tac-toe.console :as console]))

(def empty-board ["-" "-" "-" "-" "-" "-" "-" "-" "-"])
(def empty-board-4by4 ["-" "-" "-" "-"
                       "-" "-" "-" "-"
                       "-" "-" "-" "-"
                       "-" "-" "-" "-"
                       ])

(defn create-input [inputs]
  (apply str (interleave inputs (repeat "\n"))))

(defn drawn-game-input [] (create-input '("1" "2" "3" "5" "8" "4" "6" "9" "7")))
(defn O-winner-game-input [] (create-input '("1" "2" "3" "5" "4" "8")))
(defn X-winner-game-input [] (create-input '("1" "2" "3" "4" "5" "6" "7")))
(defn X-winner-game-input-4by4 [] (create-input '("1" "8" "2" "9" "3" "7" "4")))

(def humanVhuman-players [[:human :human] :3x3])
(def humanVcomputer-players [[:human :computer] :3x3])
(def computerVcomputer-players [[:computer :computer] :3x3])

(def humanVhuman-players-4by4 [[:human :human] :4x4])

(def human-players [:human :human])
(def players [:human :computer])
(def stub-board [:human :computer])
(describe "start of app"
  (it "welcomes the user" 
    (with-in-str "1\n2\n"
      (with-redefs [play-game (stub players)]
        (should-invoke console/print-welcome-message {:times 1} (start))))))

(describe "a multiple turn game"
  (it "asks the user for move" 
    (with-in-str (X-winner-game-input)
      (should-invoke console/ask-for-move {:times 1} (play-game humanVhuman-players)))))

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
                              )))))
(describe "game setup"
  (it "prints game options"
    (with-in-str (create-input '("1" "1"))
      (with-redefs [play-game (stub players)]
        (should-invoke console/print-menu {:times 1} (start))))) 
  (it "asks for game type" 
    (with-in-str "1\n"
      (with-redefs [play-game (stub players)]
        (should-invoke console/get-game-type {:times 1} (start))))))

(describe "a 4x4 game"
  (it "prints X as winner 4by4" 
    (with-in-str (X-winner-game-input-4by4)
      (should (boolean (re-find #"\nX wins\n"
                                (str (with-out-str (play-game humanVhuman-players-4by4)))))))))

