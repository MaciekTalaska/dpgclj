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

(defn create-repository []
  (def words-pl (extract-words-from-file diceware_file_polish))
  (def words-en (extract-words-from-file diceware_file_english))
  (def polish-repository (create-sub-repository "pl" words-pl))
  (def english-repository (create-sub-repository "en" words-en))
  (def repository (vector polish-repository english-repository))
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
  (def repository (create-repository))
  (diceware-info (first repository))
  (diceware-info (last repository))
  )
