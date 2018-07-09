(ns dpgclj.options)

(defstruct dpg-options
  :language
  :words-count
  :separator
  :passwords-count)

(defn print-help []
  (println "Diceware Password Generator (Clojure version)")
  (println "2018 by Maciek Talaska")
  (println "sources: https://github.com/MaciekTalaska/dpgclj")
  (println "")
  (println "options:")
  (println "-l:language - specifiy language list (en/pl)")
  (println "-w:words - number of words password should be build of")
  (println "-s:separator - char to be used as words separator")
  (println "-p:passwords - number of passwords to generate")
  )

(defn get-language [input]
  (def language-option(re-matches #"(.*)(-l:)([a-z]{2})(.*)" input))
  {:language (language-option 3)}
  )

(defn get-words-count [input]
  (def words-option (re-matches #"(.*)(-w:)([0-9]*)(.*)" input))
  {:words (read-string (words-option 3))}
  )

(defn get-passwords-count [input]
  (def passwords-option (re-matches #"(.*)(-p:)([0-9*])(.*)" input))
  {:passwords (read-string(passwords-option 3))}
  )

(defn get-separator [input]
  (def separator-option (re-matches #"(.*)(-s:)(.)(.*)" input))
  {:separator (separator-option 3)}
  )

(defn get-options [& args]
  (def args-string (clojure.string/join " " args))
  (merge
   (get-language args-string)
   (get-words-count args-string)
   (get-separator args-string)
   (get-passwords-count args-string)
   ()
   )
 )
