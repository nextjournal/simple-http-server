(ns nextjournal.simple-http-server
  "A thin dependency-free simple http static server built on `SimpleWebServer` from JDK 18+.

  Useable via `clojure -X`

  See https://openjdk.org/jeps/408."
  (:import (com.sun.net.httpserver SimpleFileServer SimpleFileServer$OutputLevel)
           (java.net InetSocketAddress)))

(defonce !server (atom nil))

(defn serve!
  "Creates a SimpleFileServer and starts it.

  Options:

  - `:port` number. defaults to `7777`
  - `:output-level`: `:none`, `:info` (default) or `:verbose`
  - `:root-path` defaults to `\"public\"`"
  [{:keys [port output-level path browse]
    :or {port 8888
         output-level :info
         path "public"}}]
  (when-let [running-server @!server]
    (.stop running-server 0))
  (reset! !server (doto (SimpleFileServer/createFileServer (InetSocketAddress. port)
                                                           (.toAbsolutePath (.toPath (java.io.File. path)))
                                                           (case output-level
                                                             :none SimpleFileServer$OutputLevel/NONE
                                                             :info SimpleFileServer$OutputLevel/INFO
                                                             :verbose SimpleFileServer$OutputLevel/VERBOSE))
                    .start))
  (when browse
    (@(requiring-resolve 'clojure.java.browse/browse-url) (str "http://localhost:" port)))
  @!server)

(comment
  (serve! {})
  (serve! {:path "test/public" :browse true})
  (serve! {:path "test/public" :port 8899 :browse true}))
