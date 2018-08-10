(ns dpgclj.options-test
  (:require [clojure.test :refer :all]
            [dpgclj.options :refer :all]))

(deftest check-if-required-options-have-been-provided
  (testing "lack of -w or -l results in error message"
    (with-redefs [exit-with-message! (fn [code message] (str message))]
      (is (= "both '-l' and '-w' have to be provided!" (check-required-options ["-x"])))))
  (testing "lack of requred options result in error message and exit with specific code"
    (with-redefs [exit-with-message! (fn [code message] {:code code :message message})]
      (let [expected-code 1
            expected-message "both '-l' and '-w' have to be provided!"
            expected {:code expected-code :message expected-message}]
        (is (= expected (check-required-options ["-x"])))))))
