(ns dpgclj.core
  (:gen-class))

(def polish_list (slurp "diceware-pl.txt"))
(def english_list (slurp "diceware-en.txt"))

(defn get-all-lines [source]
  (clojure.string/split-lines source))

(defn -main
  [& args]
  (def all_lines_polish (get-all-lines polish_list))
  (def all_lines_english (get-all-lines english_list))
  (def first_english (first all_lines_english))
  (def first_polish (first all_lines_polish))
  (println "first polish word:")
  (println first_polish)
  (println "first english word:")
  (println first_english))
