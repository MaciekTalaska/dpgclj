(ns dpgclj.core
  (:gen-class)
  (:require [dpgclj.diceware :as dw])
  (:require [dpgclj.password :as pass])
  (:require [dpgclj.options :as opt]))


(defn -main
  [& args]

  (if-not (empty? args)
    (let [options (opt/get-options args)]
       (opt/exit-if-invalid-options options)
       (let [repository (dw/create-repositories)]
         (println (pass/create-all-passwords
                   (options :language)
                   repository
                   (options :words)
                   (options :separator)
                   (options :passwords)))))
    (opt/print-help)))
