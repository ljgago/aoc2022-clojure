(ns aoc.day13-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day13 :as day13]))

(def input
  "[1,1,3,1,1]
[1,1,5,1,1]

[[1],[2,3,4]]
[[1],4]

[9]
[[8,7,6]]

[[4,4],4,4]
[[4,4],4,4,4]

[7,7,7,7]
[7,7,7]

[]
[3]

[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]")

(def parse-expected [[[1 1 3 1 1] [1 1 5 1 1]]
                     [[[1] [2 3 4]] [[1] 4]]
                     [[9] [[8 7 6]]]
                     [[[4 4] 4 4] [[4 4] 4 4 4]]
                     [[7 7 7 7] [7 7 7]]
                     [[] [3]]
                     [[[[]]] [[]]]
                     [[1 [2 [3 [4 [5 6 7]]]] 8 9] [1 [2 [3 [4 [5 6 0]]]] 8 9]]])

(deftest parser
  (testing "test parser"
    (let [expected parse-expected]
      (is (= (day13/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected 13]
      (is (= (day13/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected 140]
      (is (= (day13/part2 input) expected)))))
