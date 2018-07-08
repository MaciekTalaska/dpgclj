(ns dpgclj.core
  (:gen-class)
  (:require [dpgclj.diceware :as dw])
  (:require [dpgclj.password :as pass])
  )


(defn -main
  [& args]
  (def repository (dw/create-repositories))

  ;; according to https://stackoverflow.com/a/8536695
  ;; doseq should be used instead of map/for when dealing with side-effects
  ;; (doseq [r repository] (dw/diceware-info r))
  (println "single random word:\n"
           (pass/get-random-word "pl" repository))
  (println "single 5-word password:\n"
           (pass/get-random-words "pl" repository 5))
  (println "5 word password with . as separator:\n" (pass/create-password "pl" repository 5 "."))
  (println "3 5-words passwords using . as separator:\n" (pass/create-all-passwords "pl" repository 5 "." 3))
  )
