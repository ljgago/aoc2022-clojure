(ns aoc.core
  (:require [clojure.java.io :as io])
  (:require [clj-http.lite.client :as http]))

(defn read-file
  "Read the puzzle content from resources folder"
  [filename]
  (slurp (io/resource filename)))

(defn read-http
  "Read the puzzle content from http request"
  [year day]
  (let [session (System/getenv "AOC_SESSION")]
    (->
     (http/get
      (str "https://adventofcode.com/" year "/day/" day "/input")
      {:headers {"cookie" (str "session=" session)}})
     (:body))))
