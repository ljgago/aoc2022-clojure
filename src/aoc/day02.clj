(ns aoc.day02
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn parse [input]
  (s/split-lines input))

;; part one
(def strategy1 {"A X" 4 "A Y" 8 "A Z" 3
                "B X" 1 "B Y" 5 "B Z" 9
                "C X" 7 "C Y" 2 "C Z" 6})

(defn part1 [input]
  (->>
   (parse input)
   (map #(get strategy1 %))
   (reduce +)))

;; part two
(def strategy2 {"A X" 3 "A Y" 4 "A Z" 8
                "B X" 1 "B Y" 5 "B Z" 9
                "C X" 2 "C Y" 6 "C Z" 7})

(defn part2 [input]
  (->>
   (parse input)
   (map #(get strategy2 %))
   (reduce +)))

;; main
(defn -main []
  (let [input (core/read-file "day02.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
