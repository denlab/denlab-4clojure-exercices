(defproject denlab-4clojure-exercices "1.0.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :profiles {:dev
             {:dependencies
              [[midje                   "1.3.1"]
               [org.clojure/tools.trace "0.7.3"]
               [com.intelie/lazytest    "1.0.0-SNAPSHOT" :exclusions [swank-clojure]]
               [org.clojure/tools.trace "0.7.3"]]}}
  :dependencies [[org.clojure/clojure "1.2.1"]]
  :description "FIXME: write description")
