# nextjournal.simple-http-server

A thin dependency-free simple http static server built on
`SimpleWebServer` from JDK 18+ [JEP 408](https://openjdk.org/jeps/408).

Usage:

``` clojure
(require '[nextjournal.simple-http-server :as http])

(http/serve! {})

(http/serve! {:port 7890 :path "test/public" :browse true :output-level :verbose})
```


