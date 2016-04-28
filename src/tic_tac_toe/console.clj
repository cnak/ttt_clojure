(ns tic-tac-toe.console)

(defn print-empty-board []
  (print "---\n---\n---\n"))

(defn ask-for-move []
  (print "Enter a move"))

(defn get-move []
  (read-line))
