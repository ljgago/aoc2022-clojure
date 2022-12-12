(ns aoc.day11
  (:require [aoc.core :as core])
  (:require [clojure.string :as s]))

;; parse data
(defn assign-data [data]
  {:items (mapv #(Integer/parseInt %) (vec (re-seq #"\d+" (nth data 0))))
   :operation (vec (re-seq #"old|\+|\*|\d+" (nth data 1)))
   :do-test (Integer/parseInt (first (re-seq #"\d+" (nth data 2))))
   :is-true (Integer/parseInt (first (re-seq #"\d+" (nth data 3))))
   :is-false (Integer/parseInt (first (re-seq #"\d+" (nth data 4))))})

(defn parse-monkey [monkey]
  (->>
   (s/split monkey #"\n")
   (next)
   (assign-data)))

(defn parse [input]
  (->>
   (s/split input #"\n\n")
   (mapv #(parse-monkey %))))

;; part one
(defn update-monkeys-items [index monkeys items]
  (let [monkey (get monkeys index)
        new-monkey (assoc monkey :items items)]
    (assoc monkeys index new-monkey)))

(defn update-item [dest-item monkeys]
  (let [[dest item] dest-item
        monkey (get monkeys dest)
        old-items (:items monkey)
        new-items (conj old-items item)
        new-monkey (assoc monkey :items new-items)]
    (assoc monkeys dest new-monkey)))

(defn do-operation [operation item]
  (let [[op1 f op2] operation
        [op1 f op2] (vector
                     (if (= op1 "old") item (Integer/parseInt op1))
                     (if (= f "*") * +)
                     (if (= op2 "old") item (Integer/parseInt op2)))]
    (f op1 op2)))

(defn get-supermod [monkeys]
  (reduce #(* %1 (:do-test %2)) 1 (pop monkeys)))

(defn compute-monkey-by-item [item monkey monkeys]
  (let [result-operation (do-operation (:operation monkey) item)
        supermod (get-supermod monkeys)
        ; new-item (mod (int (/ result-operation 3)) supermod)] ; part 1
        new-item (mod result-operation supermod)] ; part 2
    (if (zero? (mod new-item (:do-test monkey)))
      [(:is-true monkey) new-item]
      [(:is-false monkey) new-item])))

(defn compute-monkey [monkey monkeys]
  (let [items (:items monkey)]
    (mapv #(compute-monkey-by-item % monkey monkeys) items)))

(defn update-counters [index monkeys]
  (let [counters (last monkeys)
        monkey (get monkeys index)
        counter (count (:items monkey))
        zeros (vec (repeat (dec (count monkeys)) 0))
        counters (mapv + counters (assoc zeros index counter))]
    (assoc monkeys (dec (count monkeys)) counters)))

(defn compute-game [index monkeys]
  (if (>= index (dec (count monkeys)))
    monkeys
    (let [monkeys (update-counters index monkeys)
          monkey (get monkeys index)
          dest-items (compute-monkey monkey monkeys)
          monkeys (reduce #(update-item %2 %1) monkeys dest-items)]
      (->>
       (update-monkeys-items index monkeys [])
       (recur (inc index))))))

(defn compute-cycles [cycles monkeys]
  (if (<= cycles 0)
    monkeys
    (->>
     (compute-game 0 monkeys)
     (recur (dec cycles)))))

(defn add-zeros [monkeys]
  (let [size (count monkeys)
        zeros (vec (repeat size 0))]
    (conj monkeys zeros)))

(defn part1 [input]
  (->>
   (parse input)
   (add-zeros)
   (compute-cycles 20)
   (last)
   (sort >)
   (take 2)
   (reduce *)))

;; part two
(defn part2 [input]
  (->>
   (parse input)
   (add-zeros)
   (compute-cycles 10000)
   (last)
   (sort >)
   (take 2)
   (reduce *)))

;; main
(defn -main []
  (let [input (core/read-file "day11.txt")]
    (println "--- Part One ---")
    (println "Result:" (part1 input))
    (println "--- Part Two ---")
    (println "Result:" (part2 input))))
