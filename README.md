# nextjournal.simple-http-server

A thin dependency-free simple http static server built on
`SimpleWebServer` from JDK 18+ [JEP 408](https://openjdk.org/jeps/408).

## Usage

From the Clojure REPL:

``` clojure
(require '[nextjournal.simple-http-server :as http])

(http/serve! {})

(http/serve! {:port 7890 :path "test/public" :browse true :output-level :verbose})
```

Use it from your shell using `clojure -X`:

``` shell
clj -Sdeps '{:deps {io.github.nextjournal/simple-http-server {:git/sha "96213f283796aa3a06ca9a8f455aaab058d3f105"}}}' \
 -X nextjournal.simple-http-server/serve! :browse true
```




