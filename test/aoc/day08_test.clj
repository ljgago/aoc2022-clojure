(ns aoc.day08-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day08 :as day08]))

(def input
  "30373
25512
65332
33549
35390")

(def parse-expected [[3 0 3 7 3]
                     [2 5 5 1 2]
                     [6 5 3 3 2]
                     [3 3 5 4 9]
                     [3 5 3 9 0]])

(deftest parser
  (testing "test parser"
    (let [expected parse-expected]
      (is (= (day08/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected 21]
      (is (= (day08/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected 8]
      (is (= (day08/part2 input) expected)))))
