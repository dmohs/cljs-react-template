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
             ["src/cljs/core/{{source-path}}/utils.clj" (render "utils.clj" data)]
             ["src/cljs/core/{{source-path}}/utils.cljs" (render "utils.cljs" data)]
             ["src/cljs/core/{{source-path}}/core.cljs" (render "core.cljs" data)]
             ["src/cljs/devtools/{{source-path}}/install_devtools.cljs"
              (render "install_devtools.cljs" data)]
             ["src/cljs/figwheel/{{source-path}}/main.cljs" (render "figwheel_main.cljs" data)]
             ["src/cljs/devcards/{{source-path}}/devcards.cljs" (render "devcards.cljs" data)]
             ["src/cljs/deploy/{{source-path}}/main.cljs" (render "deploy_main.cljs" data)]
             ["src/cljs/deploy/devcards/core.clj" (render "deploy_devcards_core.clj" data)]
             ["src/cljs/deploy/devcards/core.cljs" (render "deploy_devcards_core.cljs" data)]
             ["src/static/index.html" (render "index.html" data)])))
