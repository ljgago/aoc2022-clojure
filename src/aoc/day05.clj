(ns aoc.day05
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn gen-map
  "Generate the final hash-map of the stacks"
  [matrix]
  (->>
   (mapv (fn [i] (mapv (fn [j] (str (get-in matrix [j i]))) (range 0 (count matrix)))) (range 0 (count (get matrix 0))))
   (map-indexed (fn [i row] (hash-map (str (inc i)) (apply vector (reverse (filterv #(not (= % " ")) row))))))
   (reduce #(conj %1 %2) {})))

(defn parse-stacks [stacks]
  (let [matrix (pop (s/split-lines stacks))
        x-size (count (re-seq #"\d" (last (s/split-lines stacks))))]
    (->>
     matrix
     (mapv (fn [line] (mapv #(nth line (inc (* % 4)) \space) (range 0 x-size))))
     (gen-map))))

(defn parse-actions [actions]
  (->>
   (s/split-lines actions)
   (map #(apply vector (re-seq #"\d+" %)))
   (mapv #(vector (Integer/parseInt (nth % 0)) (nth % 1) (nth % 2)))))

(defn parse [input]
  (let [[stacks actions] (s/split input #"\n\n")]
    [(parse-stacks stacks) (parse-actions actions)]))

;; part one
(defn update-stack1 [action stacks]
  (let [[amount orig dest] action
        stack-orig (get stacks orig)
        stack-dest (get stacks dest)]
    (if (> amount 0)
      (let [updated-stack-orig (pop stack-orig)
            updated-stack-dest (conj stack-dest (last stack-orig))
            updated-stacks (conj stacks (hash-map orig updated-stack-orig dest updated-stack-dest))]
        (update-stack1 [(dec amount) orig dest] updated-stacks))
      stacks)))

(defn get-message [stacks]
  (let [size (count stacks)]
    (reduce #(str %1 (last (get stacks (str %2)))) "" (range 1 (inc size)))))

(defn part1 [input]
  (let [[stacks actions] (parse input)]
    (->>
     (reduce #(update-stack1 %2 %1) stacks actions)
     (get-message))))

;; part two
(defn update-stack2 [action stacks]
  (let [[amount orig dest] action
        stack-orig (get stacks orig)
        stack-dest (get stacks dest)
        updated-stack-orig (apply vector (drop-last amount stack-orig))
        updated-stack-dest (apply conj stack-dest (take-last amount stack-orig))
        updated-stacks (conj stacks (hash-map orig updated-stack-orig dest updated-stack-dest))]
    updated-stacks))

(defn part2 [input]
  (let [[stacks actions] (parse input)]
    (->>
     (reduce #(update-stack2 %2 %1) stacks actions)
     (get-message))))

;; main
(defn -main []
  (let [input (core/read-file "day05.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
