(ns aoc.day01-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day01 :as day01]))

(def input "1")

(deftest parser
  (testing "test parser"
    (let [expected 1]
      (is (= (day01/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected 1]
      (is (= (day01/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected 1]
      (is (= (day01/part2 input) expected)))))
