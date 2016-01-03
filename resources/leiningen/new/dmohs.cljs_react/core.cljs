(ns {{namespace}}.core
  (:require
   clojure.string
   [dmohs.react :as react]
   [{{namespace}}.utils :as u])
  (:require-macros
   [devcards.core :as dc :refer [defcard]]))


(react/defc ClickyDiv
  {:get-initial-state
   (fn []
     {:click-count 0})
   :render
   (fn [{:keys [props state]}]
     [:div {}
      "Hello, " (:name props) "!"
      [:div {} [:button {:onClick (fn [e] (swap! state update-in [:click-count] inc))} "Clicky"]]
      [:div {} "The above button has been clicked " (:click-count @state) " times."]])})


(defcard clicky-div
  (fn [data-atom owner]
    (react/create-element
     u/DevcardsComponent @data-atom
     [ClickyDiv (merge {:name "nice person"}
                       {:initial-state-override (:state @data-atom)
                        :on-state-change #(swap! data-atom assoc :state %)})]))
  nil
  {:inspect-data true})
