(ns tic-tac-toe.game-setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game-setup :refer :all]))

(describe "setup players"
  (it "setups player 1"
    (should= [:human :human] (setup-game-type :humanVhuman)))
  (it "setups human v computer"
    (should= [:human :computer] (setup-game-type :humanVcomputer)))
  (it "setups computer v computer"
    (should= [:computer :computer] (setup-game-type :computerVcomputer)))
  )

