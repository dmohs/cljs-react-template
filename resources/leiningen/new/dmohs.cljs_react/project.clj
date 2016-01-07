(defproject {{name}} "0.0.1"
  :dependencies
  [
   [dmohs/react "0.2.11"]
   [org.clojure/clojure "1.7.0"]
   [org.clojure/clojurescript "1.7.48"]
   ]
  :plugins [[lein-cljsbuild "1.0.6"] [lein-figwheel "0.3.7"] [lein-resource "15.10.2"]]
  :profiles {:dev {:dependencies [[binaryage/devtools "0.4.1"] [devcards "0.2.1-2"]]
                   :cljsbuild
                   {:builds {:client {:source-paths ["src/cljs-dev"]
                                      :compiler
                                      {:optimizations :none
                                       :source-map true
                                       :source-map-timestamp true}}}}}
             :figwheel {:cljsbuild
                        {:builds {:client {:source-paths ["src/cljs-figwheel"]
                                           :compiler {:main "{{namespace}}.main-figwheel"}
                                           :figwheel {}}}}}
             :devcards {:cljsbuild
                        {:builds {:client {:compiler {:main "{{namespace}}.main-devcards"}
                                           :figwheel {:devcards true}}}}}
             :minimized {:cljsbuild
                         {:builds {:client {:source-paths ["src/cljs-prod"]
                                            :compiler
                                            {:optimizations :advanced
                                             :pretty-print false}}}}}}
  :cljsbuild {:builds {:client {:source-paths ["src/cljs"]
                                :compiler {:main "{{namespace}}.main"
                                           :output-dir "target/build"
                                           :asset-path "build"
                                           :output-to "target/compiled.js"}}}}
  :resource {:resource-paths ["src/static"] :skip-stencil [#".*"]})
