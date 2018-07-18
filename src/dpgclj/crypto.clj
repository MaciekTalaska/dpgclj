(ns dpgclj.crypto
  (:import [java.security SecureRandom]))

(defn secure-rand-int
  " returns crypto secure integer, range: 0-maxvalue (exclusive)"
  [max-value]
  (let [secure-random(doto (java.security.SecureRandom.))]
    (.nextInt secure-random max-value)))
