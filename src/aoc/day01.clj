(ns aoc.day01
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn parse [input]
  (->>
   (s/split-lines input)
   (map #(Integer/parseInt %))
   (reduce +)))

;; part one
(defn part1 [input]
  (parse input))

;; part two
(defn part2 [input]
  (parse input))

;; main
(defn -main []
  (let [input (core/read-file "day01.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
