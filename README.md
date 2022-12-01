# Advent of Code 2022 solutions in Clojure

## Usage

The project is configured for use with [clojure](https://github.com/clojure/clojure) or with [babashka](https://github.com/babashka/babashka).
The tests work only with clojure, but you can run them using a babashka task.

For example:

    # run the day01 with clj
    $ clj -M -m aoc.day01

    # run the day01 with babashka
    $ bb -m aoc.day01

    # only test the day01 with clj
    $ clj -M:test -n aoc.day01-test

    # only test the day01 with babashka (uses clj internaly)
    $ bb test -n aoc.day01-test

    # all test with clj
    $ clj -M:test

    # all test with babashka (use clj internaly)
    $ bb test

Happy coding!

## License

[MIT License](LICENSE)
