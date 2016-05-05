(ns tic-tac-toe.console)

(defn- display-to-console [message]
  (print message))

(defn print-empty-board []
  (display-to-console "- - -\n- - -\n- - -\n"))

(defn- print-row [board row]
  (apply println (row (partition 3 board))))

(defn print-board [board] 
  (print-row board first) (print-row board second) (print-row board last))

(def ask-for-move-message "Enter a move\n")
(defn ask-for-move []
  (display-to-console ask-for-move-message))

(defn get-move-choice []
  (- (read-string (flush) (read-line)) 1))

(def welcome-message "\nWelcome to Tic Tac Toe\n")
(defn print-welcome-message []
  (display-to-console welcome-message))
