(ns dpgclj.core
  (:gen-class))

(def diceware_file_polish (slurp "diceware-pl.txt"))
(def diceware_file_english (slurp "diceware-en.txt"))

(defstruct diceware-repository :words :length :dices :language)
(defstruct repository :en :pl)

(defn extract-words [lines]
  (def words (for [w lines :let [words (last (clojure.string/split w #"\t"))]] words))
  words
  )

(defn extract-words-from-file [file]
  (def lines (clojure.string/split-lines file))
  (if (clojure.string/includes? (first lines) "\t")
    (extract-words lines)
    lines
    )
  )

(defn create-sub-repository [language, words-list]
  (def sub-repository (struct-map diceware-repository
                                  :words words-list
                                  :length (count words-list)
                                  :dices 0
                                  :language language
                                  ))
  sub-repository)

(defn create-repository [language, file]
  (def words (extract-words-from-file file))
  (def repository (create-sub-repository language words))
  repository
  )

(defn create-repositories []
  (def repository
    (vector
     (create-repository "pl" diceware_file_polish)
     (create-repository "en" diceware_file_english)))
  repository
  )

(defn get-repo-by-language [language]
                      (println "this is not implemented yet!"))

(defn diceware-info [diceware]
  (println "---[ repository info ]---")
  (println "language: " (str (get diceware :language)))
  (println "first word: " (str(first(get diceware :words))))
  (println "dices: " (str (get diceware :dices)))
  (println "lenght: " (str (get diceware :length)))
  )

(defn -main
  [& args]
  (def repository (create-repositories))
  (diceware-info (first repository))
  (diceware-info (last repository))
  )
