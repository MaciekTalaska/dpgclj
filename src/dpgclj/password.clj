(ns dpgclj.password
  (:require [dpgclj.diceware :as dw])
  (:require [dpgclj.crypto :as crypto]))

(defn get-random-word [language, repositories]
  (let [repo (dw/get-repo-by-language language repositories)]
    (let [index (crypto/secure-rand-int (dec (repo :length)))]
      (nth (get repo :words) index))))

(defn get-random-words [language, repositories, count]
  (mapv (fn [x] (get-random-word language, repositories)) (range count)))

(defn create-password [language, repositories, count, separator]
  (clojure.string/join separator (get-random-words language repositories count)))

(defn create-all-passwords [language, repositories, count, separator, passwords-count]
  (let [all-passwords (range passwords-count)]
    (let [passwords (mapv (fn [x] (create-password language repositories count separator)) all-passwords)]
      (clojure.string/join "\n" passwords))))
