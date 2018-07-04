(ns dpgclj.core
  (:gen-class))

(def diceware_file_polish (slurp "diceware-pl.txt"))
(def diceware_file_english (slurp "diceware-en.txt"))

(defn get-all-lines [source]
  (clojure.string/split-lines source))

(defstruct diceware-repository :words :length :dices :language)
(defstruct repository :en :pl)

(defn extract-words [words-list]
  (def lines (clojure.string/split-lines words-list))
  (for [w lines :let [words (last (clojure.string/split w #"\t"))]] words)
  ;; (for [w (extract-words english_list) :let [words (last (clojure.string/split w #"\t"))]] words)
  ;; (for [w (extract-words english_list) :let [words (last (clojure.string/split w #"\t"))]] words)
  )

(defn create-repository []
  (def lines_pl (get-all-lines diceware_file_polish))
  (def lines_en (get-all-lines diceware_file_english))
  ;;(def first_english (first lines_en))
  ;;(def first_polish (first lines_pl))
  ;;(print "first polish word: ")
  ;;(println first_polish)
  ;;(print "first english word: ")
  ;;(println first_english)
  (def polish-repository (struct-map diceware-repository
                                     :words lines_pl
                                     :length (count lines_pl)
                                     :dices 0
                                     :language "pl"))
  ;;(def english-repository (struct-map diceware-repository
  ;;                                    :words (extract-words lines_en)
  ;;                                    :length (count lines_en)
  ;;                                    :dices 0
  ;;                                    :language "en"))
  ;;(vector polish-repository english-repository)
  (def repository (vector polish-repository))
  repository
  )

(defn get-repo-by-language [language]
                      (println "this is not implemented yet!"))

(defn diceware-info [diceware]
  (println "language: " (str (get diceware :language)))
  (println "first word: " (
                           str(first(get diceware :words))))
  (println "dices: " (str (get diceware :dices)))
  (println "lenght: " (str (get diceware :length)))
  )


(defn -main
  [& args]
  (def repository (create-repository))
  (diceware-info (first repository))
  (diceware-info (last repository))
  )
