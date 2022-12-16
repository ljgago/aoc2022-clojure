(ns aoc.day14
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn parse-line [line]
  (->>
   (re-seq #"\d+" line)
   (flatten)
   (mapv #(Integer/parseInt %))
   (partition 2)))

(defn parse [input]
  (->>
   (s/split-lines input)
   (mapv #(parse-line %))))

;; part one
(defn dist-add [v1 v2]
  (mapv + v1 v2))

(defn correct-order [p1 p2]
  (let [x1 (first p1)
        x2 (first p2)
        y1 (second p1)
        y2 (second p2)]
    (cond
      (and (<= x1 x2) (<= y1 y2)) (vector x1 x2 y1 y2)
      (and (>= x1 x2) (<= y1 y2)) (vector x2 x1 y1 y2)
      (and (<= x1 x2) (>= y1 y2)) (vector x1 x2 y2 y1)
      :else (vector x2 x1 y2 y1))))

(defn draw-line [line]
  (loop [line line
         points #{}]
    (let [p1 (first line)
          p2 (second line)]
      (cond
        (nil? p2) points
        :else (let [[x1 x2 y1 y2] (correct-order p1 p2)]
                ; (println "x:" x1 x2 "y:" y1 y2)
                (->>
                 (for [x (range x1 (inc x2)) y (range y1 (inc y2))] [x y])
                 (apply conj points)
                 (recur (next line))))))))

(defn gen-cave [input]
  (reduce #(apply conj %1 (draw-line %2)) #{} input))

(defn limit? [point rocks sands floor]
  (cond
    (> floor 0) (and (false? (contains? rocks point)) (false? (contains? sands point)) (> floor (second point)))
    :else (and (false? (contains? rocks point)) (false? (contains? sands point)))))

(defn sort-sand [point rocks sands floor]
  (loop [point point
         n 10000]
    ; (println "p:" point "s:" sands)
    (let [[down left right] [[0 1] [-1 1] [1 1]]
          point-down (dist-add point down)
          point-left (dist-add point left)
          point-right (dist-add point right)]
      (cond
        (< n 0) [:break sands]
        (contains? sands point) [:break sands]
        (limit? point-down rocks sands floor) (recur point-down (dec n))
        (limit? point-left rocks sands floor) (recur point-left (dec n))
        (limit? point-right rocks sands floor) (recur point-right (dec n))
        :else [:continue (conj sands point)]))))

(defn drop-sand [start-point floor rocks]
  (loop [sands #{}]
    (let [[state sands] (sort-sand start-point rocks sands floor)]
      (cond
        (= state :continue) (recur sands)
        (= state :break) sands
        :else sands))))

(defn part1 [input]
  (->>
   (parse input)
   (gen-cave)
   (drop-sand [500 0] 0)
   (count)))

;; part two
(defn find-floor [rocks]
  (->>
   (map #(second %) rocks)
   (apply max)
   (+ 2)))

(defn part2 [input]
  (let [rocks (->> (parse input)
                   (gen-cave))]
    (->>
     (drop-sand [500 0] (find-floor rocks) rocks)
     (count))))

;; main
(defn -main []
  (let [input (core/read-file "day14.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
