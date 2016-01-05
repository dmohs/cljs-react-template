(ns ^:figwheel-always {{namespace}}.main-figwheel
  (:require
   [devtools.core :as devtools]
   {{namespace}}.dev
   [{{namespace}}.core :as core]
   ))


(core/render-application true)
