(ns aoc.day05
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn parse-stacks [stacks]
  (let [matrix (pop (s/split-lines stacks))
        x-size (count (re-seq #"\d" (last (s/split-lines stacks))))]
    (->>
     matrix
     ;; gets characters in the positions 1, 5, 9, 13, etc
     (mapv (fn [row] (mapv #(nth row (inc (* % 4)) \space) (range 0 x-size))))
     ;; transposes the matrix and changes chars to strings
     (apply mapv #(mapv str %&))
     ;; removes the whitespaces and reverses the vector
     (mapv (fn [row] (apply vector (reverse (remove #(= " " %) row)))))
     ;; creates a hash-map with the index as key
     (map-indexed (fn [i row] (hash-map (str (inc i)) row)))
     ;; creates an unique hash-map
     (reduce #(conj %1 %2) {}))))

(defn parse-actions [actions]
  (->>
   (s/split-lines actions)
   (map #(apply vector (re-seq #"\d+" %)))
   (mapv #(vector (Integer/parseInt (nth % 0)) (nth % 1) (nth % 2)))))

(defn parse [input]
  (let [[stacks actions] (s/split input #"\n\n")]
    [(parse-stacks stacks) (parse-actions actions)]))

;; part one
(defn update-stack-1 [action stacks]
  (let [[amount from to] action
        stack-from (get stacks from)
        stack-to (get stacks to)
        updated-stack-from (apply vector (drop-last amount stack-from))
        updated-stack-to (apply conj stack-to (reverse (take-last amount stack-from)))
        updated-stacks (conj stacks (hash-map from updated-stack-from to updated-stack-to))]
    updated-stacks))

(defn get-message [stacks]
  (let [size (count stacks)]
    (reduce #(str %1 (last (get stacks (str %2)))) "" (range 1 (inc size)))))

(defn part1 [input]
  (let [[stacks actions] (parse input)]
    (->>
     (reduce #(update-stack-1 %2 %1) stacks actions)
     (get-message))))

;; part two
(defn update-stack-2 [action stacks]
  (let [[amount from to] action
        stack-from (get stacks from)
        stack-to (get stacks to)
        updated-stack-from (apply vector (drop-last amount stack-from))
        updated-stack-to (apply conj stack-to (take-last amount stack-from))
        updated-stacks (conj stacks (hash-map from updated-stack-from to updated-stack-to))]
    updated-stacks))

(defn part2 [input]
  (let [[stacks actions] (parse input)]
    (->>
     (reduce #(update-stack-2 %2 %1) stacks actions)
     (get-message))))

;; main
(defn -main []
  (let [input (core/read-file "day05.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
