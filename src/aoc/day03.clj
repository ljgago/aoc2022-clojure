(ns aoc.day03
  (:require [aoc.core :as core])
  (:require [clojure.string :as s])
  (:require [clojure.set :as c-set]))

;; parse data
(defn parse [input]
  (s/split-lines input))

;; part one
(defn priority
  "Get the priority from a char value"
  [val]
  (cond
    (re-find #"[a-z]" (str val)) (- (int val) 96)
    (re-find #"[A-Z]" (str val)) (- (int val) 38)))

(defn part1 [input]
  (->>
   (parse input)
   (map #(partition (/ (count %) 2) %))
   (map (fn [[p1 p2]] (c-set/intersection (set p1) (set p2))))
   (map #(priority (apply get % (seq %))))
   (reduce +)))

;; part two
(defn part2 [input]
  (->>
   (parse input)
   (partition 3)
   (map (fn [[p1 p2 p3]] (c-set/intersection (set p1) (set p2) (set p3))))
   (map #(priority (apply get % (seq %))))
   (reduce +)))

;; main
(defn -main []
  (let [input (core/read-file "day03.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
