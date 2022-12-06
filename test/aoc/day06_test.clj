(ns aoc.day06-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day06 :as day06]))

(def input-a "mjqjpqmgbljsphdztnvjfqwrcgsmlb")
(def input-b "bvwbjplbgvbhsrlpgdmjqwftvncz")
(def input-c "nppdvjthqldpwncqszvftbrmjlhg")
(def input-d "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg")
(def input-e "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw")

(deftest part1
  (testing "test part 1"
    (let [expected-a 7
          expected-b 5
          expected-c 6
          expected-d 10
          expected-e 11]
      (is (= (day06/part1 input-a) expected-a))
      (is (= (day06/part1 input-b) expected-b))
      (is (= (day06/part1 input-c) expected-c))
      (is (= (day06/part1 input-d) expected-d))
      (is (= (day06/part1 input-e) expected-e)))))

(deftest part2
  (testing "test part 2"
    (let [expected-a 19
          expected-b 23
          expected-c 23
          expected-d 29
          expected-e 26]
      (is (= (day06/part2 input-a) expected-a))
      (is (= (day06/part2 input-b) expected-b))
      (is (= (day06/part2 input-c) expected-c))
      (is (= (day06/part2 input-d) expected-d))
      (is (= (day06/part2 input-e) expected-e)))))
