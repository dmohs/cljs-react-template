(ns leiningen.new.dmohs.cljs-react
  (:require [leiningen.new.templates :refer [renderer name-to-path sanitize-ns ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "dmohs.cljs-react"))

(defn dmohs.cljs-react
  [name]
  (let [data {:name name
              :namespace (sanitize-ns name)
              :source-path (name-to-path name)}]
    (main/info "Generating fresh 'lein new' dmohs/cljs-react project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["src/cljs/{{source-path}}/utils.clj" (render "utils.clj" data)]
             ["src/cljs/{{source-path}}/utils.cljs" (render "utils.cljs" data)]
             ["src/cljs/{{source-path}}/core.cljs" (render "core.cljs" data)]
             ["src/cljs-dev/{{source-path}}/main_dev.cljs" (render "main_dev.cljs" data)]
             ["src/cljs-prod/{{source-path}}/main.cljs" (render "main.cljs" data)]
             ["src/cljs-prod/devcards/core.clj" (render "prod_devcards_core.clj" data)]
             ["src/static/index.html" (render "index.html" data)])))
