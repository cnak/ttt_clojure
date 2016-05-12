(ns tic-tac-toe.human-player-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.human-player :refer :all]
            [tic-tac-toe.player :as player]))

(def a-empty-board 
      ["-" "-" "-" "-" "-" "-" "-" "-" "-"])
(defn get-human-move []
  (player/get-move {:player_type :human} a-empty-board))

(describe "a placed move"
  (it "picks 5 as an input and returns 4"
    (should= 4 
    (with-in-str "5" (get-human-move)))
  ))

