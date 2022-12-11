(ns aoc.day10-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.core :as core])
  (:require [aoc.day10 :as day10]))

(def input "noop\naddx 3\naddx -5")
(def parse-expected [["noop" nil] ["addx" 3] ["addx" -5]])

(deftest parser
  (testing "test parser"
    (let [expected parse-expected]
      (is (= (day10/parse input) expected)))))

(def input-long (core/read-file "day10_test.txt"))

(deftest part1
  (testing "test part 1"
    (let [expected 13140]
      (is (= (day10/part1 input-long) expected)))))

(def part2-expected
"##..##..##..##..##..##..##..##..##..##..
###...###...###...###...###...###...###.
####....####....####....####....####....
#####.....#####.....#####.....#####.....
######......######......######......####
#######.......#######.......#######.....
")

(deftest part2
  (testing "test part 2"
    (let [expected part2-expected]
      (is (= (day10/part2 input-long) expected)))))
