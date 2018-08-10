(ns dpgclj.crypto-test
  (:require [clojure.test :refer :all]
            [dpgclj.crypto :refer :all]))

(deftest random-value-should-be-smaller-than-max
  (testing "(secure-rand-int 1) must return 0"
    (is (= (secure-rand-int 1) 0)))
  (testing "(secure-rand-int 2) must return 0 or 1"
    (is (< (secure-rand-int 2) 2)))
  (testing "(secure-rand-int X) must return at most X-1"
    (let [max (secure-rand-int 255)]
      (is (< (secure-rand-int max) max)))))

