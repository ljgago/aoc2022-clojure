(ns aoc.day03-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day03 :as day03]))

(def input "vJrwpWtwJgWrhcsFMMfFFhFp\njqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\nPmmdzqPrVvPwwTWBwg\nwMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\nttgJtRGJQctTZtZT\nCrZsJsPPZsGzwwsLwLmpwMDw")
(def parse-expected ["vJrwpWtwJgWrhcsFMMfFFhFp"
                     "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL"
                     "PmmdzqPrVvPwwTWBwg"
                     "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn"
                     "ttgJtRGJQctTZtZT"
                     "CrZsJsPPZsGzwwsLwLmpwMDw"])

(deftest parser
  (testing "test parser"
    (let [expected parse-expected]
      (is (= (day03/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected 157]
      (is (= (day03/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected 70]
      (is (= (day03/part2 input) expected)))))
