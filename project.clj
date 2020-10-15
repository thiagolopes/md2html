(defproject experiment "0.1.0"
  :description "Convert markdown to html styled with bootstrap"
  :url "https://github.com/thiagolopes/"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [markdown-clj "1.10.5"]
                 [hiccup-bridge "1.0.1"]]
  :main ^:skip-aot md2html.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :plugins [[lein-cljfmt "0.7.0"]]
  )
