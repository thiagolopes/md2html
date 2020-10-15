(ns md2html.core-test
  (:require [clojure.test :refer :all]
            [md2html.core :refer :all]))

(deftest test-html-footer
  (let [date-now (java.util.Date.)]
    (is (=
         (html-footer date-now)
         [:footer [:p (str "Generate at: " date-now)]]))))

(deftest test-change-file-format
  (is (= (change-file-format "new_file" :clj) "new_file.clj")))

(deftest test-md2hiccup
  (is (= (md->hiccup "# This is a head\n\n-item one")
         [:html [:body [:h1 "This is a head"] [:p "-item one"]]])))

(deftest test-html-body
  (is (= (html-body [:class "container"] [:p "simple text"] (html-footer "NOW"))
         [:body [:class "container"] [:p "simple text"] [:footer [:p "Generate at: NOW"]]])))
