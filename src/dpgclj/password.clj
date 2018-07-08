(ns dpgclj.password
  (:require [dpgclj.diceware :as dw])
  (:require [dpgclj.crypto :as crypto])
  )

(defn get-random-word [language, repositories]
  (def repo (dw/get-repo-by-language language repositories))
  (def index (crypto/secure-rand-int (dec (repo :length))))
  (def word (nth (get repo :words) index))
  word
  )

(defn get-random-words [language, repositories, count]
  (def output (range count))
  (def words (mapv (fn [x] (get-random-word language, repositories)) output))
  words
  )

(defn create-password [language, repositories, count, separator]
  (def words (get-random-words language repositories count))
  (clojure.string/join separator words)
  )

(defn create-all-passwords [language, repositories, count, separator, passwords-count]
  (def all-passwords (range passwords-count))
  (def passwords (mapv (fn [x] (create-password language repositories count separator)) all-passwords))
  (clojure.string/join "\n" passwords)
  )
