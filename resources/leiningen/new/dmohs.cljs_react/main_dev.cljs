(ns {{namespace}}.main-dev
  (:require
   [devtools.core :as devtools]
   [{{namespace}}.core :as core]
   ))


;; See https://github.com/binaryage/cljs-devtools
;; With these tools installed and "Enable custom formatters" checked in Chrome's devtools' settings,
;; Clojure data structures are much easier to inspect in the console and debugging windows.
(defonce devcards-installed?
  (do (devtools/set-pref! :install-sanity-hints true)
      (devtools/install!)
      true))


(when-not (.-devcards js/window)
  (core/render-application true))
