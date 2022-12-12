(ns aoc.day11-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day11 :as day11]))

(def input
  "Monkey 0:
  Starting items: 79, 98
  Operation: new = old * 19
  Test: divisible by 23
    If true: throw to monkey 2
    If false: throw to monkey 3

Monkey 1:
  Starting items: 54, 65, 75, 74
  Operation: new = old + 6
  Test: divisible by 19
    If true: throw to monkey 2
    If false: throw to monkey 0

Monkey 2:
  Starting items: 79, 60, 97
  Operation: new = old * old
  Test: divisible by 13
    If true: throw to monkey 1
    If false: throw to monkey 3

Monkey 3:
  Starting items: 74
  Operation: new = old + 3
  Test: divisible by 17
    If true: throw to monkey 0
    If false: throw to monkey 1")

(def parse-expected [{:items [79 98], :operation ["old" "*" "19"], :do-test 23, :is-true 2, :is-false 3}
                     {:items [54 65 75 74], :operation ["old" "+" "6"], :do-test 19, :is-true 2, :is-false 0}
                     {:items [79 60 97], :operation ["old" "*" "old"], :do-test 13, :is-true 1, :is-false 3}
                     {:items [74], :operation ["old" "+" "3"], :do-test 17, :is-true 0, :is-false 1}])

(deftest parser
  (testing "test parser"
    (let [expected parse-expected]
      (is (= (day11/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected 10605]
      (is (= (day11/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected 2713310158]
      (is (= (day11/part2 input) expected)))))
