(ns tic-tac-toe.console)
(use '[clojure.java.shell :only [sh]])

(defn- display-to-console [message]
  (print message))

(defn print-empty-board []
  (display-to-console "- - -\n- - -\n- - -\n"))

(defn- print-row [board row splits]
  (apply println (nth (partition splits board) row)))

(defn- print3x3 [board]
  (print-row board 0 3)
  (print-row board 1 3)
  (print-row board 2 3))

(defn- print4x4 [board]
  (print-row board 0 4)
  (print-row board 1 4)
  (print-row board 2 4)
  (print-row board 3 4)) 

(defn print-board [board] 
  (if (= 9 (count board))
    (print3x3 board)
    (print4x4 board)))

(def ask-for-move-message "Enter a move\n")
(defn ask-for-move []
  (display-to-console ask-for-move-message))

(defn get-move-choice []
  (- (read-string (flush) (read-line)) 1))

(def welcome-message "Tic Tac Toe")
(defn print-welcome-message []
  (display-to-console (:out (sh "artii" welcome-message))))

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

(def board-size-question "\nWhich board size?\n1. 3x3\n2. 4x4\n")
(defn  ask-board-size []
  (display-to-console board-size-question))

(def invalid-option-message "Invalid option! Try again!")

(defn invalid-board-size [ask-board-size]
  (display-to-console invalid-option-message)
  (ask-board-size))

(defn get-board-size-choice [] 
  (ask-board-size)
  (let [choice (read-console)]
    (cond 
      (= 1 choice) :3x3 
      (= 2 choice) :4x4
      :else 
      (invalid-board-size get-board-size-choice)
      )))

(defn- get-game-choice []
  (let [choice (read-console)]
    (cond 
      (= 1 choice) [:human :human]
      (= 2 choice) [:human :computer]
      (= 3 choice) [:computer :computer])))

(defn invalid-game-type [ask-game-type]
  (display-to-console invalid-option-message)
  (print-menu)
  (ask-game-type))

(defn get-game-type []
  (let [game-type (get-game-choice)]
    (if (= nil game-type) 
      (invalid-game-type get-game-type)
      (let [board-size (get-board-size-choice)]
        (conj [] game-type board-size)))))
