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
     (opt/exit-if-invalid-options options)

     (def repository (dw/create-repositories))

     (println (pass/create-all-passwords
              (options :language)
              repository
              (options :words)
              (options :separator)
              (options :passwords)))
     ; TODO: investigate why uncommenting the line below
     ; results in run-time error when proper parameters are passed
     ; The error occrus at the very end, when all processing is done,
     ; and the generated password is already printed to the console.
     ; Note: this is linked to calling opt/exit-if-invalid-options -
     ; if the mentioned function is not called, line below could
     ; be omitted
     (System/exit 0)
     )
    (
     (opt/print-help)
     (System/exit 1))
    )
  )
