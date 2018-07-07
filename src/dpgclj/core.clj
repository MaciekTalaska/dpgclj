(ns dpgclj.core
  (:gen-class)
  (:require [dpgclj.diceware :as dw]))

(defn -main
  [& args]
  (def repository (dw/create-repositories))

  ;; according to https://stackoverflow.com/a/8536695
  ;; doseq should be used instead of map/for when dealing with side-effects
  (doseq [r repository] (dw/diceware-info r))
  ;; (println (dw/hello))
  ;; (def r(dw/create-repositories))
  ;; (println (str "repo type: " (type r)))
  ;; (println "repo size: " (str (count r))) 
  ;; (for [x r] (println x))
  ;; (map dw/diceware-info r)
  ;; (dw/diceware-info (first r))
  ;; (dw/diceware-info (second r))
  ;; (println "first map & diceware-info")
  ;; (map (fn [x] (dw/diceware-info x)) r)
  ;; (println "iterating over results using for...")
  ;; (for [x r ] (dw/diceware-info x) )
  ;; (println r)
  ;; (println "second map & diceware-info")
  ;; (map (fn [x] 
  ;;        (
  ;;         (println (get x :language))
  ;;         (println (get x :length))
  ;;         (println (get x :dices))
  ;;         (println (take 5 (get x :words)))
  ;;         )) r)
  )
