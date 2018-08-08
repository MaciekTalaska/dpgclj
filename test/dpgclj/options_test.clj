(ns dpgclj.options-test
  (:require [clojure.test :refer :all]
            [dpgclj.options :refer :all]))

(deftest simple-test-options
  (testing "simple-test-options"
    (is (= 10 10))))

;(deftest l1ack-of-required-option-results-in-error-message
(deftest first-test
  (testing "first"
    (with-redefs [exit-with-message! (fn [code message] (str message))]
      (is (= "both '-l' and '-w' have to be provided!" (check-required-options ["-x"]))))))

(deftest second-test
  (testing "second-test"
    (with-redefs [exit-with-message! (fn [code message] {:code code :message message})]
      (let [expected-code 1
            expected-message "both '-l' and '-w' have to be provided!"
            expected {:code expected-code :message expected-message}]
        (is (= expected (check-required-options ["-x"])))))))
