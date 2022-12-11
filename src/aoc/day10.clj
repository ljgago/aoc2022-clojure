(ns aoc.day10
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn parse [input]
  (->>
   (s/split-lines input)
   (mapv #(s/split % #"\s"))
   (mapv (fn [[cmd v]] (vector cmd (if (nil? v) v (Integer/parseInt v)))))))

;; part one
(defn compute-strengths [n x mid cycles strengths commands]
  (let [[cmd v] (first commands)
        c (first cycles)]
    (cond
      (nil? cycles) strengths
      (= n (first cycles)) (recur n x mid (next cycles) (conj strengths (* x c)) commands)
      (true? mid) (recur (inc n) (+ x v) false cycles strengths (next commands))
      (= cmd "noop") (recur (inc n) x false cycles strengths (next commands))
      (= cmd "addx") (recur (inc n) x true cycles strengths commands))))

(defn part1 [input]
  (->>
   (parse input)
   (compute-strengths 1 1 false [20 60 100 140 180 220] [])
   (reduce +)))

;; part two
(defn write-draw [n x draw]
  (let [start (dec x)
        end (inc x)
        index (mod n 40)]
    (if (and (>= index start) (<= index end))
      (conj draw "#")
      (conj draw "."))))

(defn compute-draw [n x mid cycles draw commands]
  (let [[cmd v] (first commands)
        draw (write-draw n x draw)]
    (cond
      (>= n (dec cycles)) draw
      (true? mid) (recur (inc n) (+ x v) false cycles draw (next commands))
      (= cmd "noop") (recur (inc n) x false cycles draw (next commands))
      (= cmd "addx") (recur (inc n) x true cycles draw commands))))

(defn part2 [input]
  (->>
   (parse input)
   (compute-draw 0 1 false 240 [])
   (partition 40)
   (reduce #(str %1 (apply str %2) "\n") "")))

;; main
(defn -main []
  (let [input (core/read-file "day10.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (printf "Result:\n%s" (part2 input))))
