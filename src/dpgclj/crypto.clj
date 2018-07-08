(ns dpgclj.crypto
  (:import [java.security SecureRandom]))

(defn secure-rand-int
  [max-value]
  "returns crypto secure integer, range: 0-maxvalue (exclusive)"
  (def secure-random(doto (java.security.SecureRandom.)))
  (.nextInt secure-random max-value)
  )
