(ns aoc.day04-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day04 :as day04]))

(def input "2-4,6-8\n2-3,4-5\n5-7,7-9\n2-8,3-7\n6-6,4-6\n2-6,4-8")
(def parse-expected [[2 4 6 8] [2 3 4 5] [5 7 7 9] [2 8 3 7] [6 6 4 6] [2 6 4 8]])

(deftest parser
  (testing "test parser"
    (let [expected parse-expected]
      (is (= (day04/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected 2]
      (is (= (day04/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected 4]
      (is (= (day04/part2 input) expected)))))
