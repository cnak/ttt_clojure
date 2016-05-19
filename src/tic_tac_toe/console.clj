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

(defn print-result [result]
  (if (= result "-")
    (display-to-console "\nDraw!\n")
    (if (= result "X")
      (display-to-console "\nX wins\n")
      (display-to-console "\nO wins\n"))))

(def menu (str "\n1. Human vs Human\n" "\n2. Human vs Computer\n" "\n3. Computer vs Computer\n"))
(defn print-menu [] 
  (display-to-console menu))

(defn- read-console []
  (read-string (flush) (read-line)))
(defn get-game-choice [] 
  (let [choice (read-console)]
    (cond 
      (= 1 choice) :humanVhuman 
      (= 2 choice) :humanVcomputer 
      (= 3 choice) :computerVcomputer)))

(def board-size-question "\nWhich board size? \n 1. 3x3\n 2. 4x4")
(defn  ask-board-size []
  (display-to-console board-size-question))

(defn get-board-size-choice [] 
  (let [choice (read-console)]
    (cond 
      (= 1 choice) :3x3 
      (= 2 choice) :4x4)))
