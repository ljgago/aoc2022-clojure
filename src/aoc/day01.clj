(ns aoc.day01
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn parse [input]
  (->>
   (s/split input #"\n\n")
   (mapv #(s/split-lines %))
   (mapv (fn [calories] (mapv #(Integer/parseInt %) calories)))))

;; part one
(defn part1 [input]
  (->>
    (parse input)
    (map #(reduce + %))
    (apply max)))

;; part two
(defn part2 [input]
  (->>
    (parse input)
    (map #(reduce + %))
    (sort >)
    (take 3)
    (reduce +)))

;; main
(defn -main []
  (let [input (core/read-file "day01.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
