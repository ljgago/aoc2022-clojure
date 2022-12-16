(ns aoc.day14-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day14 :as day14]))

(def input "498,4 -> 498,6 -> 496,6\n503,4 -> 502,4 -> 502,9 -> 494,9")
(def parse-expected [[[498 4] [498 6] [496 6]] [[503 4] [502 4] [502 9] [494 9]]])

(deftest parser
  (testing "test parser"
    (let [expected parse-expected]
      (is (= (day14/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected 24]
      (is (= (day14/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected 93]
      (is (= (day14/part2 input) expected)))))
