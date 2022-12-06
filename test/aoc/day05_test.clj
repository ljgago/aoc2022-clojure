(ns aoc.day05-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day05 :as day05]))

(def input "    [D]\n[N] [C]\n[Z] [M] [P]\n 1   2   3\n\nmove 1 from 2 to 1\nmove 3 from 1 to 3\nmove 2 from 2 to 1\nmove 1 from 1 to 2")
(def parsed-stacks {"1" ["Z" "N"] "2" ["M" "C" "D"] "3" ["P"]})
(def parsed-actions [[1 "2" "1"] [3 "1" "3"] [2 "2" "1"] [1 "1" "2"]])

(deftest parser
  (testing "test parser"
    (let [expected [parsed-stacks parsed-actions]]
      (is (= (day05/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected "CMZ"]
      (is (= (day05/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected "MCD"]
      (is (= (day05/part2 input) expected)))))
