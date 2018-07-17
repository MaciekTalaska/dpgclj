(ns dpgclj.diceware
 ;;(:gen-class) 
 )

(defn diceware-file? [filename] (clojure.string/includes? filename "diceware-"))

(defstruct language-and-file :filename :language)

(defn get-all-diceware-files []
  (def all-files
    (mapv str(filter #(.isFile %) (file-seq (clojure.java.io/file ".")))))
  (def diceware-files (filter diceware-file? all-files))
  diceware-files
  )

(defn extract-language-from-filename [filename]
  (def language (nth
                 (re-matches #"(.*-)([a-z]{2})(\..*)" filename)
    2))
  (def pair (struct-map language-and-file
                        :filename filename
                        :language language
                        ))
  pair
  )

(defn get-files-with-languages []
  (def files (get-all-diceware-files))
  (mapv extract-language-from-filename files)
  )


(defstruct diceware-repository :words :length :dices :language)
(defstruct repository :en :pl)

(defn extract-words [lines]
  (def words (for [w lines :let [words (last (clojure.string/split w #"\t"))]] words))
  words
  )

(defn extract-words-from-file [file]
  (def lines (clojure.string/split-lines file))
  (if (clojure.string/includes? (first lines) "\t")
    (into [] (extract-words lines))
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
  (let [words (extract-words-from-file file)]
    (create-sub-repository language words)))

(defn create-repositories []
  (let [all-files (get-files-with-languages)]
    (mapv (fn [file-language]
            (create-repository
             (file-language :language)
             (slurp (file-language :filename)))
            ) all-files)))

(defn get-repo-by-language [language, repositories]
  (if (= language ((first repositories) :language))
    (first repositories)
    (last repositories)))

(defn diceware-info [diceware]
  (println "---[ repository info ]---")
  (println "  type: " (str (type diceware)))
  (println "  language: " (str (diceware :language)))
  (println "  first words: " (clojure.string/join "-" (take 10 (diceware :words))))
  ;; (println "  dices: " (str (diceware :dices)))
  (println "  lenght: " (str (diceware :length)))
  )
