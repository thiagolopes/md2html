(ns md2html.core
  (:gen-class)
  (:use markdown.core)
  (:require [clojure.string :as str]
            [hiccup-bridge.core :as hicv]
            [hiccup.core]
            [hiccup.page]))

(defn style [s]
  (str/join ";" (map #(str (name %) ":" ((keyword %) s)) (keys s))))

(def attributes-base {:style (style {:display          "block"
                                     :flex-wrap        "wrap"
                                     :color            "white"
                                     :background-color "black"})})

(defn footer [time]
  [:footer [:p (str "Generate at: " time)]])

(defn new-file-format [file-name fmt]
  (str (first (str/split file-name #"\.")) "." (name fmt)))

(defn md->hiccup [markdown]
  (first (hicv/html->hiccup
          (md-to-html-string markdown))))

(def head [:head
           (first (hiccup.page/include-css
                   "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"))
           (first (hiccup.page/include-js
                   "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"))])

(defn body [rest]
  [:body {:class "container"} rest])

(defn new-base-html [html]
  (let [[_ & rest] (second html)]
    [:html head (conj (body rest))]))

(defn -main [file-name]
  (let
   [new-file (new-file-format file-name :html)
    content (slurp file-name)]
    (spit
     new-file
     (hiccup.core/html (new-base-html (md->hiccup content))))))
