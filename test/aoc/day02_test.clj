(ns aoc.day02-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day02 :as day02]))

(def input "A Y\nB X\nC Z")
(def parse-expected ["A Y" "B X" "C Z"])

(deftest parser
  (testing "test parser"
    (let [expected parse-expected]
      (is (= (day02/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected 15]
      (is (= (day02/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected 12]
      (is (= (day02/part2 input) expected)))))
