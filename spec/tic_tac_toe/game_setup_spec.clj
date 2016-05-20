(ns tic-tac-toe.game-setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game-setup :refer :all]))

(describe "setup players"
  (it "setups player 1"
    (should= [:human :human] (setup-players :humanVhuman)))
  (it "setups human v computer"
    (should= [:human :computer] (setup-players :humanVcomputer)))
  (it "setups computer v computer"
    (should= [:computer :computer] (setup-players :computerVcomputer)))
  )

