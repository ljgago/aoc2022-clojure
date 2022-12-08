(ns aoc.day07-test
  (:require [clojure.test :refer [deftest is testing]])
  (:require [aoc.day07 :as day07]))

(def input
  "$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k")

(def parse-expected [["cd" "/"]
                     ["ls"]
                     ["dir" "a"]
                     ["14848514" "b.txt"]
                     ["8504156" "c.dat"]
                     ["dir" "d"]
                     ["cd" "a"]
                     ["ls"]
                     ["dir" "e"]
                     ["29116" "f"]
                     ["2557" "g"]
                     ["62596" "h.lst"]
                     ["cd" "e"]
                     ["ls"]
                     ["584" "i"]
                     ["cd" ".."]
                     ["cd" ".."]
                     ["cd" "d"]
                     ["ls"]
                     ["4060174" "j"]
                     ["8033020" "d.log"]
                     ["5626152" "d.ext"]
                     ["7214296" "k"]])

(deftest parser
  (testing "test parser"
    (let [expected parse-expected]
      (is (= (day07/parse input) expected)))))

(deftest part1
  (testing "test part 1"
    (let [expected 95437]
      (is (= (day07/part1 input) expected)))))

(deftest part2
  (testing "test part 2"
    (let [expected 24933642]
      (is (= (day07/part2 input) expected)))))
