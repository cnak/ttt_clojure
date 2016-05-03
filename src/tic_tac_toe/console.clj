(ns tic-tac-toe.console)

(defn- display-to-console [message]
  (print message))

(defn print-empty-board []
  (display-to-console "- - -\n- - -\n- - -\n"))

(defn- print-row [board row]
  (apply println (row (partition 3 board))))

(defn print-board [board] 
  (print-row board first) (print-row board second) (print-row board last))

(defn ask-for-move []
  (display-to-console "Enter a move\n"))

(defn get-move []
  (- (read-string (flush) (read-line)) 1))

(defn print-welcome-message []
  (display-to-console "\nWelcome to Tic Tac Toe\n"))
