(ns tic-tac-toe.human-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.human-player :refer :all]
            [tic-tac-toe.player :as player]))

(def an-empty-board 
      ["-" "-" "-" "-" "-" "-" "-" "-" "-"])
(defn get-human-move []
  (player/get-move {:type-of-player :human} an-empty-board))

(describe "a placed move"
  (it "picks 5 as an input and returns 4"
    (should= 4 
    (with-in-str "5" (get-human-move)))))

