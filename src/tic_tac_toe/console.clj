(ns tic-tac-toe.console)

(defn- display-to-console [message]
  (print message))

(defn- print-new-line []
  (print "\n"))

(defn print-empty-board []
  (display-to-console "---\n---\n---\n"))

(defn print-board [board]
  (display-to-console (board 0))
  (display-to-console (board 1))
  (display-to-console (board 2))
  (print-new-line)
  (display-to-console (board 3))
  (display-to-console (board 4))
  (display-to-console (board 5))
  (print-new-line)
  (display-to-console (board 6))
  (display-to-console (board 7))
  (display-to-console (board 8))
  (print-new-line)
  )

(defn ask-for-move []
  (display-to-console "Enter a move\n"))

(defn get-move []
  (- (read-string (flush) (read-line)) 1))

(defn print-welcome-message []
  (display-to-console "\nWelcome to Tic Tac Toe\n"))
