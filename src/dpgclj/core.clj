(ns dpgclj.core
  (:gen-class)
  (:require [dpgclj.diceware :as dw])
  (:require [dpgclj.dices :as dices])
  )


(defn -main
  [& args]
  (def repository (dw/create-repositories))

  ;; according to https://stackoverflow.com/a/8536695
  ;; doseq should be used instead of map/for when dealing with side-effects
  ;; (doseq [r repository] (dw/diceware-info r))
  ;; (println (dices/get-random-word "pl" repository))
  ;; (println (dices/get-random-words "pl" repository 5))
  (println (dices/create-password "pl" repository 5 "."))
  )
