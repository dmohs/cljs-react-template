(ns {{namespace}}.core
  (:require
   [dmohs.react :as react]
   [{{namespace}}.utils :as u])
  (:require-macros
   [devcards.core :refer [defcard]]))


(react/defc ClickyDiv
  {:get-initial-state
   (fn []
     {:click-count 0})
   :render
   (fn [{:keys [props state]}]
     [:div {}
      "Hello, " (:name props) "!"
      [:div {} [:button {:onClick (fn [e] (swap! state update-in [:click-count] inc))} "Clicky"]]
      [:div {} "The above button has been clicked " (:click-count @state) " times."]
      [:div {} "Today's magic word is: \"" (:word props) "\""]
      [:dev {} [:button {:onClick (fn [e] ((:on-word-request props)))} "Request New Word"]]])})


(defn- get-random-word []
  (rand-nth ["filthy" "morose" "flippant" "silly" "thorn"]))


(defcard clicky-div
  (fn [data-atom owner]
    (react/create-element
     u/DevcardsComponent @data-atom
     [ClickyDiv (merge {:name "nice person"
                        :word (:word @data-atom)
                       :on-word-request (fn [] (swap! data-atom assoc :word (get-random-word)))}
                       {:on-state-change #(swap! data-atom assoc :state %)})]))
  {:word (get-random-word)}
  {:inspect-data true})


(def word-database ["happy" "sad" "scared" "angry" "elephant" "cat" "giraffe" "lion" "tiger"])


(defn render-application [& [hot-reload?]]
  (react/render
   (react/create-element
    ClickyDiv
    {:name "Application"
     :word (rand-nth word-database)
     :on-word-request #(js/alert "Not yet implemented.")})
   (.. js/document (getElementById "app"))
   nil
   hot-reload?))
