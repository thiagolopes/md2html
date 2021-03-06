(ns md2html.core
  (:gen-class)
  (:use markdown.core)
  (:require [clojure.string :as str]
            [hiccup-bridge.core :as hicv]
            [hiccup.page :refer [include-css include-js]]
            [hiccup.core]))

(def bootstrap-css "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")
(def bootstrap-js "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js")
(def base-head
  [:head (map first (list (include-css bootstrap-css)
                          (include-js bootstrap-js)))])

(defn html-footer [time]
  [:footer [:p (str "Generate at: " time)]])

(defn html-body [attrs & content-body]
  (reduce conj [:body attrs] content-body))

(defn change-file-format [file-name fmt]
  (str (first (str/split file-name #"\.")) "." (name fmt)))

(defn md->hiccup [markdown]
  (first (hicv/html->hiccup (md-to-html-string markdown))))

(defn -main [file-name]
  (let [new-file (change-file-format file-name :html)
        markdown-content (md->hiccup (slurp file-name))
        attribute-bootstrap {:class "container"}
        html-footer (html-footer (str (java.util.Date.)))]
    (spit new-file
          (hiccup.core/html
           [:html base-head
            (html-body attribute-bootstrap markdown-content html-footer)]))))
