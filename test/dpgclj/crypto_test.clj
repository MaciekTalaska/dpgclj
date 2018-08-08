(ns dpgclj.crypto-test
  (:require [clojure.test :refer :all]
            [dpgclj.crypto :refer :all]))

(deftest random-value-should-be-smaller-than-max
  (testing "returned value is not smaller than max!"
    (is (< (secure-rand-int 17) 17))))
