(ns aoc.day06
  (:require [aoc.core :as core]))

;; part one
(defn start-packet-at [size counter input]
  (let [characters (->> (take size input)
                        (apply hash-set)
                        (count))]
    (if (= characters size)
      (+ counter size)
      (start-packet-at size (inc counter) (rest input)))))

(defn part1 [input]
  (start-packet-at 4 0 input))

;; part two
(defn part2 [input]
  (start-packet-at 14 0 input))

;; main
(defn -main []
  (let [input (core/read-file "day06.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
