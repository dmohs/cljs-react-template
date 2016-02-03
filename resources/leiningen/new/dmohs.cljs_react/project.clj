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
                                      :figwheel {}}}}}
             :devcards {:cljsbuild
                        {:builds {:client {:figwheel {:devcards true}}}}}
             :deploy {:cljsbuild
                      {:builds {:client {:source-paths ["src/cljs-prod"]
                                         :compiler
                                         {:main "{{namespace}}.main"
                                          :optimizations :advanced
                                          :pretty-print false}}}}}}
  :cljsbuild {:builds {:client {:source-paths ["src/cljs"]
                                :compiler {:output-dir "target/build"
                                           :asset-path "build"
                                           :output-to "target/compiled.js"}}}}
  :resource {:resource-paths ["src/static"] :skip-stencil [#".*"]})
