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
  (if (or
           (nil? passwords-option)
           (empty? (passwords-option 3))
           )
    {:passwords 1}
    {:passwords (read-string (passwords-option 3))}
    )
  )

(defn get-separator [input]
  (def separator-option (re-matches #"(.*)(-s:)(.)(.*)" input))
  (if (or
       (nil? separator-option)
       (empty? (separator-option 3)))
    {:separator "-"}
    {:separator (separator-option 3)}
    )
  )

(defn check-required-options [args]
  (if-not (and
           (clojure.string/includes? args "-l:")
           (clojure.string/includes? args "-w:"))
    ((println "error: not enough arguments provided!\n")
     (print-help)
     (System/exit 1))
    ()))

(defn get-options [& args]
  (def str-args (clojure.string/join " " args))
  (check-required-options str-args)
  (merge
   (get-language str-args)
   (get-words-count str-args)
   (get-separator str-args)
   (get-passwords-count str-args)))

