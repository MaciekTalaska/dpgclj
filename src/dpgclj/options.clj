(ns dpgclj.options)

(defn print-help []
  (println "Diceware Password Generator (Clojure version)")
  (println "2018 by Maciek Talaska")
  (println "sources: https://github.com/MaciekTalaska/dpgclj")
  (println "")
  (println "options:")
  (println "-l:language - specifiy language list (en/pl)")
  (println "-w:words - number of words password should be build of")
  (println "-s:separator - char to be used as words separator")
  (println "-p:passwords - number of passwords to generate"))

(defn print-error [messages]
  (print "error: ")
  (println messages)
  (println "\nuse `-h` for help"))

(defn get-words-count [input]
  (let [words-option (re-matches #"(.*)(-w:)([0-9]*)(.*)" input)]
    (let [words (words-option 3)]
      (if (or
           (nil? words-option)
           (empty? words-option)
           (empty? words))
        {:error "no password length (in words) provided"}
        (if (or
             (> (read-string words)  255)
             (< (read-string words) 1))
          {:error "password should be at least 1 and max 255 words long"}
          {:words (read-string words)})))))

;; TODO: get-passwords-count, get-language and get-separator should/could be unified:
;; - first parameter should be regex exrepssion
;; - second parameter should be default value (or error as for get-language if there is no value provided)
(defn get-passwords-count [input]
  (let [passwords-option (re-matches #"(.*)(-p:)([0-9*])(.*)" input)]
    (if (or
         (nil? passwords-option)
         (empty? (passwords-option 3))
         )
      {:passwords 1}
      {:passwords (read-string (passwords-option 3))})))

(defn get-language [input]
  (let [language-option(re-matches #"(.*)(-l:)([a-z]{2})(.*)" input)]
    (if (or
         (nil? language-option)
         (empty? language-option)
         )
      {:error "no language specified"}
      {:language (language-option 3)})))

(defn get-separator [input]
  (let [separator-option (re-matches #"(.*)(-s:)(.)(.*)" input)]
    (if (or
         (nil? separator-option)
         (empty? (separator-option 3)))
      {:separator "-"}
      {:separator (separator-option 3)})))

(defn check-required-options [args]
  (if-not (and
           (clojure.string/includes? args "-l:")
           (clojure.string/includes? args "-w:"))
    ((print-error "both '-l' and '-w' have to be provided!")
     (System/exit 1))))

(defn check-if-help [args]
  (if (and
       (= (count args) 1)
       (= (first args) "-h"))
    (
     (print-help)
     (System/exit 0)
     )
    )
  )

(defn get-options [args]
  (let [str-args (clojure.string/join " " args)]
    (check-if-help args)
    (check-required-options str-args)
    (merge
     (get-language str-args)
     (get-words-count str-args)
     (get-separator str-args)
     (get-passwords-count str-args))))

(defn exit-if-invalid-options [options]
  (if (contains? options :error)
    ; if there is :error in map - print message & exit
    ((print-error (options :error))
     (System/exit 1))))
