(ns tic-tac-toe.play-game) 
(use '[tic-tac-toe.console :as console])
(use '[tic-tac-toe.board :as board])

(defn -main []
 (print-welcome-message) (print-empty-board) (ask-for-move) (print-board (board/make-move ["-" "-" "-" "-" "-" "-" "-" "-" "-"] (get-move) "X")))

