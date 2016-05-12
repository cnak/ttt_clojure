(ns tic-tac-toe.human-player
  (:require [tic-tac-toe.player :as player]
            [tic-tac-toe.console :as console]))

(defmethod player/get-move :human [_ _]
  (console/get-move-choice))
