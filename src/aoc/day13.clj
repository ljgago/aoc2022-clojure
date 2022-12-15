(ns aoc.day13
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn parse [input]
  (->>
   (->
    (s/replace input #"," " ")
    (s/split #"\n\n"))
   (mapv #(s/split-lines %))
   (mapv (fn [line] (mapv #(load-string %) line)))))

;; part one
(defn do-compare
  "0 equal-to | 1 greater-than | -1 less-than"
  [l1 l2]
    (cond
      (= l1 l2) 0
      (and (int? l1) (int? l2) (< l1 l2)) -1
      (and (int? l1) (int? l2) (> l1 l2)) 1
      (and (sequential? l1) (int? l2)) (recur l1 (vector l2))
      (and (int? l1) (sequential? l2)) (recur (vector l1) l2)
      (and (empty? l1) (< 0 (count l2))) -1
      (and (< 0 (count l1)) (empty? l2)) 1
      (= (do-compare (first l1) (first l2)) 0) (recur (vec (next l1)) (vec (next l2)))
      :else (recur (first l1) (first l2))))

(defn part1 [input]
  (->>
   (parse input)
   (map-indexed #(vector (inc %1) (apply do-compare %2)))
   (filter #(not= 1 (second %)))
   (map #(first %))
   (reduce +)))

;; part two
(defn part2 [input]
  (let [item (vector [[2]] [[6]])]
  (->>
    (parse input)
    (concat (vector item))
    (reduce #(concat %2 %1) [])
    (sort do-compare)
    (map-indexed #(list (inc %1) %2))
    (filter #(or (= (first item) (second %)) (= (second item) (second %))))
    (map #(first %))
    (reduce *))))

;; main
(defn -main []
  (let [input (core/read-file "day13.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
