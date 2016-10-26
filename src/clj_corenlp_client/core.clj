(ns clj-corenlp-client.core
  (:require [clj-http.client :as client]
            [cemerick.url :as cemerick-url]
            [clojure.data.json :as json]
            [clojure.string :as str])
  (:gen-class))

(defn create-query-string [annotators]
  (let [properties {:annotators (str/join "," annotators)
                    :outputFormat "json"}]
    {:properties (json/write-str properties)}))

(defn create-url
  [host port annotators]
  (-> (cemerick.url.URL. "http"
                         nil
                         nil
                         (if host host "localhost")
                         (if port port 9000)
                         "/"
                         (create-query-string annotators)
                         nil)
       str))

(defn annotate [text annotators & [host port]]
  (let [response (client/post (create-url host port annotators)
                              {:body text})]
    (when (not= (:status response) 200)
      (throw (Exception. "Annotating Error")))
    (json/read-str (:body response)
                   :key-fn keyword)))

(defn tokenize [text & [host port]]
  (annotate text ["tokenize" "ssplit"] host port))

(defn tag-pos [text & [host port]]
  (annotate text ["tokenize" "ssplit" "pos"] host port))

(defn dep-parse [text & [host port]]
  (annotate text ["tokenize" "ssplit" "pos" "depparse"] host port))
