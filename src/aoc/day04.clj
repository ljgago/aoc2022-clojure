(ns aoc.day04
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn parse [input]
  (->>
   (s/split-lines input)
   (map #(s/split % #"[,-]"))
   (mapv (fn [pairs] (mapv #(Integer/parseInt %) pairs)))))

;; part one
(defn fully-contains
  "[la-lb,ra-rb]
  la: left a, lb: left b, ra: right a, rb: right b"
  [[la lb ra rb]]
  (cond
    (and (<= la ra) (>= lb rb)) 1
    (and (>= la ra) (<= lb rb)) 1
    :else 0))

(defn part1 [input]
  (->>
   (parse input)
   (map #(fully-contains %))
   (reduce +)))

;; part two
(defn overlap-all
  "[la-lb,ra-rb]
  la: left a, lb: left b, ra: right a, rb: right b"
  [[la lb ra rb]]
  (cond
    (and (>= lb ra) (<= la rb)) 1
    :else 0))

(defn part2 [input]
  (->>
   (parse input)
   (map #(overlap-all %))
   (reduce +)))

;; main
(defn -main []
  (let [input (core/read-file "day04.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
