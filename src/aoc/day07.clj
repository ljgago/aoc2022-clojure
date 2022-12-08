(ns aoc.day07
  (:require [aoc.core :as core])
  (:require [clojure.string :as s])
  (:require [clojure.java.io :as io]))

;; parse data
(defn parse [input]
  (->>
   (s/split-lines input)
   (map #(apply str (re-seq #"\w.+" %)))
   (mapv #(s/split % #"\s"))))

;; part one
(defn path-expand [path dir]
  (case dir
    "/" dir
    ".." (.getParent (io/file path))
    (.getAbsolutePath (io/file path dir))))

(defn get-content [tree path]
  (get tree path []))

(defn add-content [tree path content]
  (let [old-content (get-content tree path)
        new-content (conj old-content content)]
    (assoc tree path new-content)))

(defn build-tree
  ([lines] (build-tree {} "/" lines))
  ([tree path lines]
   (if (nil? lines)
     tree
     (let [[cmd dir] (first lines)]
       (case cmd
         "cd" (build-tree tree (path-expand path dir) (next lines))
         "ls" (build-tree tree path (next lines))
         (build-tree (add-content tree path (first lines)) path (next lines)))))))

(defn sum-content [content]
  (->>
   (map (fn [[v _]] (if (= v "dir") 0 (Integer/parseInt v))) content)
   (reduce +)))

(defn compute-dir-sizes [tree]
  (->>
   (map (fn [[path content]] (vector path (sum-content content))) (vec tree))
   (flatten)
   (apply hash-map)))

(defn sum-match-path [root-path path-sizes]
  (->>
   (vec path-sizes)
   (map (fn [[path size]] (if (s/includes? path root-path) size 0)))
   (reduce +)))

(defn compute-full-dir-sizes [path-sizes]
  (->>
   (keys path-sizes)
   (map #(vector % (sum-match-path % path-sizes)))
   (flatten)
   (apply hash-map)))

(defn sum-minor-then [limit path-sizes]
  (->>
   (vals path-sizes)
   (filter #(< % limit))
   (reduce +)))

(defn part1 [input]
  (->>
   (parse input)
   (build-tree)
   (compute-dir-sizes)
   (compute-full-dir-sizes)
   (sum-minor-then 100000)))

;; part two
(def total-space 70000000)
(def update-space 30000000)

(defn space-to-remove [path-sizes]
  (let [free-space (- total-space (get path-sizes "/"))]
    (->>
     (vec path-sizes)
     (map (fn [[_path size]] size))
     (filter #(> (+ free-space %) update-space))
     (apply min))))

(defn part2 [input]
  (->>
   (parse input)
   (build-tree)
   (compute-dir-sizes)
   (compute-full-dir-sizes)
   (space-to-remove)))

;; main
(defn -main []
  (let [input (core/read-file "day07.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
