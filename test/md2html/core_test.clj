(ns md2html.core-test
  (:require [clojure.test :refer :all]
            [md2html.core :refer :all]))

(deftest core-test
  (testing "new-file-format create clojure file name"
    (is (=
         (new-file-format "new_file" :clj)
         "new_file.clj")))
  (testing "from markdown generate hiccup html"
    (is (=
         (md->hiccup "# This is a head\n\n-item one")
         [:html [:body [:h1 "This is a head"] [:p "-item one"]]])))
  (testing "new-base-html"
    (is (=
         (new-base-html (md->hiccup "# This is a head\n\n-item one"))
         [:html
          head
          (body [[:h1 "This is a head"] [:p "-item one"]])])))
  (testing "footer"
    (is (=
         (footer "NOW")
         [:footer [:p "Generate at: NOW"]])))
  (testing "body"
    (is (=
         (body [[:h1 "This is a head"] [:p "-item one"]])
         [:body {:class "container"} [[:h1 "This is a head"] [:p "-item one"]]]))))
