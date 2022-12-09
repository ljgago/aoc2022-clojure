(ns aoc.day08
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn parse [input]
  (->>
   (s/split-lines input)
   (mapv #(mapv str (char-array %)))
   (mapv (fn [c] (mapv #(Integer/parseInt %) c)))))

;; part one
(defn check-up-1? [v i j matrix]
  (cond
    (< i 0) true
    (<= v (get-in matrix [i j])) false
    :else (check-up-1? v (dec i) j matrix)))

(defn check-down-1? [v i j matrix]
  (let [i-max (dec (count matrix))]
    (cond
      (> i i-max) true
      (<= v (get-in matrix [i j])) false
      :else (check-down-1? v (inc i) j matrix))))

(defn check-left-1? [v i j matrix]
  (cond
    (< j 0) true
    (<= v (get-in matrix [i j])) false
    :else (check-left-1? v i (dec j) matrix)))

(defn check-right-1? [v i j matrix]
  (let [j-max (dec (count (first matrix)))]
    (cond
      (> j j-max) true
      (<= v (get-in matrix [i j])) false
      :else (check-right-1? v i (inc j) matrix))))

(defn visible-1? [i j matrix]
  (let [v (get-in matrix [i j])
        up (check-up-1? v (dec i) j matrix)
        down (check-down-1? v (inc i) j matrix)
        left (check-left-1? v i (dec j) matrix)
        right (check-right-1? v i (inc j) matrix)]
    (or up down left right)))

(defn compute-visibles-1 [matrix]
  (let [y-size (count matrix)
        x-size (count (first matrix))]
    (->>
     (for [i (range 0 y-size) j (range 0 x-size)] (visible-1? i j matrix))
     (filter #(true? %))
     (count))))

(defn part1 [input]
  (->>
   (parse input)
   (compute-visibles-1)))

;; part two
(defn check-up-2? [v i j matrix acc]
  (cond
    (< i 0) acc
    (<= v (get-in matrix [i j])) (inc acc)
    :else (check-up-2? v (dec i) j matrix (inc acc))))

(defn check-down-2? [v i j matrix acc]
  (let [i-max (dec (count matrix))]
    (cond
      (> i i-max) acc
      (<= v (get-in matrix [i j])) (inc acc)
      :else (check-down-2? v (inc i) j matrix (inc acc)))))

(defn check-left-2? [v i j matrix acc]
  (cond
    (< j 0) acc
    (<= v (get-in matrix [i j])) (inc acc)
    :else (check-left-2? v i (dec j) matrix (inc acc))))

(defn check-right-2? [v i j matrix acc]
  (let [j-max (dec (count (first matrix)))]
    (cond
      (> j j-max) acc
      (<= v (get-in matrix [i j])) (inc acc)
      :else (check-right-2? v i (inc j) matrix (inc acc)))))

(defn visible-2? [i j matrix]
  (let [v (get-in matrix [i j])
        up (check-up-2? v (dec i) j matrix 0)
        down (check-down-2? v (inc i) j matrix 0)
        left (check-left-2? v i (dec j) matrix 0)
        right (check-right-2? v i (inc j) matrix 0)]
    (* up down left right)))

(defn compute-visibles-2 [matrix]
  (let [y-size (count matrix)
        x-size (count (first matrix))]
    (->>
     (for [i (range 0 y-size) j (range 0 x-size)] (visible-2? i j matrix))
     (apply max))))

(defn part2 [input]
  (->>
   (parse input)
   (compute-visibles-2)))

;; main
(defn -main []
  (let [input (core/read-file "day08.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
