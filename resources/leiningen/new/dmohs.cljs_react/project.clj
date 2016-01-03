(defn- get-websocket-host []
  (if-let [docker-host (System/getenv "DOCKER_HOST")]
    (let [[_ ip] (re-matches #"^tcp://([^:]+):\d+$" docker-host)]
      {:websocket-host ip})
    {}))


(defproject {{name}} "0.0.1"
  :dependencies
  [
   [dmohs/react "0.2.9"]
   [org.clojure/clojure "1.7.0"]
   [org.clojure/clojurescript "1.7.48"]
   [cljsjs/moment "2.10.6-0"]
   ]
  :plugins [[lein-cljsbuild "1.0.6"] [lein-figwheel "0.3.7"]]
  :hooks [leiningen.cljsbuild]
  :profiles {:dev {:dependencies [[binaryage/devtools "0.4.1"] [devcards "0.2.1-2"]]
                   :cljsbuild
                   {:builds {:client {:source-paths ["src/cljs-dev"]
                                      :compiler
                                      {:main "{{namespace}}.dev"
                                       :optimizations :none
                                       :source-map true
                                       :source-map-timestamp true}
                                      :figwheel ~(merge {:devcards true}
                                                        (get-websocket-host))}}}}
             :minimized {:cljsbuild
                         {:builds {:client {:compiler
                                            {:main "{{namespace}}.main"
                                             :optimizations :advanced
                                             :pretty-print false
                                             :closure-defines {"goog.DEBUG" false}}}}}}}
  :cljsbuild {:builds {:client {:source-paths ["src/cljs"]
                                :compiler {:output-dir "target/build"
                                           :asset-path "build"
                                           :output-to "target/compiled.js"}}}})
