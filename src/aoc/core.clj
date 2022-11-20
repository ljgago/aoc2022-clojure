(ns aoc.core
  (:require [clojure.java.io :as io])
  (:require [clj-http.lite.client :as http]))

(defn read-file [filename]
  (slurp (io/resource filename)))

(defn read-http [year day]
  (let [session (System/getenv "AOC_SESSION")]
    (->
     (http/get
      (str "https://adventofcode.com/" year "/day/" day "/input")
      {:headers {"cookie" (str "session=" session)}})
     (:body))))
