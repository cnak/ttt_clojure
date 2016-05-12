(ns tic-tac-toe.game-setup-spec
  (:require [speclj.core :refer :all]
            [tic-tac-toe.game-setup :refer :all]))

(describe "setup players"
  (it "setups player 1"
    (should= {:player1 :human :player2 :human} (setup-game-type :humanVhuman)))
  (it "setups human v computer"
    (should= {:player1 :human :player2 :computer} (setup-game-type :humanVcomputer)))
  (it "setups computer v computer"
    (should= {:player1 :computer :player2 :computer} (setup-game-type :computerVcomputer)))
  )

