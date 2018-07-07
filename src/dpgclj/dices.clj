(ns dpgclj.dices
  (:require [dpgclj.diceware :as dw]))

(defn get-random-word [language, repositories]
  (def repo (dw/get-repo-by-language language repositories))
  (def index (rand-int (dec (get repo :length))))
  (def word (nth (get repo :words) index))
  word
  )

(defn get-random-words [language, repositories, count]
  (println "this function is not implemented yet")
  )

(defn create-password [language, repositories, count, separator]
  (println "this function is not implemented yet")
  )

(defn create-all-passwords [language, repositories, count, separator, passwords-count]
  (println "this function is not implemented yet")
  )
