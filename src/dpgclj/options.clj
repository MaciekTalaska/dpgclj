(ns dpgclj.options
  (:require [clojure.core.match :refer [match]]))

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
  (let [words-option (re-matches #"(.*)(-w:)([0-9]*)(.*)" input)
        words (words-option 3)]
    (if (or
          (nil? words-option)
          (empty? words-option)
          (empty? words))
      {:error "no password length (in words) provided"}
      (if (or
            (> (read-string words)  255)
            (< (read-string words) 1))
        {:error "password should be at least 1 and max 255 words long"}
        {:words (read-string words)}))))

(defn get-option-or-error [input regex error-message]
  (let [option (re-matches regex input)]
    (if (or
         (nil? option)
         (empty? option)
         (empty? (option 3)))
      {:error error-message}
      {:ok (option 3)})))

(defn get-option-or-default [input regex default]
  (let [option (re-matches regex input)]
    (if (or
         (nil? option)
         (empty? option)
         (empty? (option 3)))
      {:ok default}
      {:ok (option 3)})))

(defn get-passwords-count [input]
  (let [option (get-option-or-default input #"(.*)(-p:)([0-9]*)(.*)" "1")]
    {:passwords (read-string (:ok option))}
    ))

(defn get-language[input]
  (let [option (get-option-or-error input #"(.*)(-l:)([a-z]{2})(.*)" "no language specified")]
    (match option
      {:ok _} {:language (:ok option)}
      option)))

(defn get-separator [input]
  (let [option (get-option-or-default input #"(.*)(-s:)(.)(.*)" "-")]
    {:separator (:ok option)}))

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
