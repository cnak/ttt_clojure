(defproject tic-tac-toe "0.1.0-SNAPSHOT"
  :description "Tic Tac Toe"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0-RC2"]]
  :main ^:skip-aot tic-tac-toe.play-game
  :profiles {:dev {:dependencies [[speclj "3.3.1"]]}}
  :plugins [
            [speclj "3.3.1"]
            [lein-codox "0.9.5"]
            ]
  :test-paths ["spec"])
