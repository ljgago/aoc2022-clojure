(ns aoc.day01-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day01 :as day01]))

(def input "1000\n2000\n3000\n\n4000\n\n5000\n6000\n\n7000\n8000\n9000\n\n10000")
(def parse-expected [[1000 2000 3000] [4000] [5000 6000] [7000 8000 9000] [10000]])

(deftest parser
  (testing "test parser"
    (let [expected parse-expected]
      (is (= (day01/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected 24000]
      (is (= (day01/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected 45000]
      (is (= (day01/part2 input) expected)))))
