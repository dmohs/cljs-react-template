(ns ^:figwheel-always {{namespace}}.dev
  (:require
   [{{namespace}}.core :as core]
   [devtools.core :as devtools]
   [dmohs.react :as react]
   )
  (:require-macros
   [devcards.core :as dc :refer [defcard]]))


;; See https://github.com/binaryage/cljs-devtools
;; With these tools installed and "Enable custom formatters" checked in Chrome's devtools' settings,
;; Clojure data structures are much easier to inspect in the console and debugging windows.
(defonce devtools-installed?
  (do
    (devtools/set-pref! :install-sanity-hints true)
    (devtools/install!)
    nil))
