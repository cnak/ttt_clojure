(ns tic-tac-toe.play-game) 
(use '[tic-tac-toe.console])
(use '[tic-tac-toe.board])

(defn -main []
 (print-welcome-message) (print-empty-board) (ask-for-move) (print-board (make-move ["-" "-" "-" "-" "-" "-" "-" "-" "-"] (get-move) "X")))

