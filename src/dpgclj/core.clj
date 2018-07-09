(ns dpgclj.core
  (:gen-class)
  (:require [dpgclj.diceware :as dw])
  (:require [dpgclj.password :as pass])
  (:require [dpgclj.options :as opt])
  )


(defn -main
  [& args]

  (if-not (empty? args)
    (
     (def options (opt/get-options args))
     (def repository (dw/create-repositories))

     (println (pass/create-all-passwords
              (options :language)
              repository
              (options :words)
              (options :separator)
              (options :passwords))))
    (
     (opt/print-help)
     (System/exit 1))
    )
  )
