(defn- inside-container? []
  (.exists (clojure.java.io/file "/.dockerenv")))


(def figwheel-opts
  (when (inside-container?)
    (let [specified-host (if-let [x (System/getenv "FIGWHEEL_HOST")]
                           (if (clojure.string/blank? (clojure.string/trim x)) nil x))
          host (or specified-host "192.168.99.100")]
      (when (nil? specified-host)
        (println (str "***\n"
                      "*** You did not specify a FIGWHEEL_HOST environment variable.\n"
                      "*** Using the default: " host "\n"
                      "***")))
      {:websocket-url (str "ws://" host ":3449/figwheel-ws")})))


(def figwheel-server-opts
  (when (inside-container?)
    {:hawk-options {:watcher :polling}}))


(defproject {{name}} "0.0.1"
  :dependencies
  [
   [dmohs/react "0.2.11"]
   [org.clojure/clojure "1.7.0"]
   [org.clojure/clojurescript "1.7.228"]
   ]
  :plugins [[lein-cljsbuild "1.1.2"] [lein-figwheel "0.5.0-6"] [lein-resource "15.10.2"]]
  :profiles {:dev {:dependencies [[binaryage/devtools "0.4.1"] [devcards "0.2.1-2"]]
                   :cljsbuild
                   {:builds {:client {:source-paths ["src/cljs-dev"]
                                      :compiler
                                      {:main "{{namespace}}.main-dev"
                                       :optimizations :none
                                       :source-map true
                                       :source-map-timestamp true}
                                      :figwheel ~figwheel-opts}}}
                   :figwheel ~figwheel-server-opts}
             :devcards {:cljsbuild
                        {:builds {:client {:figwheel ~(merge figwheel-opts
                                                             {:devcards true})}}}}
             :deploy {:cljsbuild
                      {:builds {:client {:source-paths ["src/cljs-prod"]
                                         :compiler
                                         {:main "{{namespace}}.main"
                                          :optimizations :simple
                                          :pretty-print false}}}}}}
  :cljsbuild {:builds {:client {:source-paths ["src/cljs"]
                                :compiler {:output-dir "target/build"
                                           :asset-path "build"
                                           :output-to "target/compiled.js"}}}}
  :resource {:resource-paths ["src/static"] :skip-stencil [#".*"]})
