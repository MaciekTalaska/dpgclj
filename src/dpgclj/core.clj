(ns dpgclj.core
  (:gen-class)
  (:require [dpgclj.diceware :as dw]))

(defn -main
  [& args]
  (def repository (dw/create-repositories))

  ;; according to https://stackoverflow.com/a/8536695
  ;; doseq should be used instead of map/for when dealing with side-effects
  (doseq [r repository] (dw/diceware-info r))
  )
