(ns dpgclj.options-test
  (:require [clojure.test :refer :all]
            [dpgclj.options :refer :all]))

(deftest simple-test-options
  (testing "simple-test-options"
    (is (= 10 10))))

;(deftest simple-test
;  (testing "something"
;    (with-redefs-fn [exit-with-message (fn [code message] constantly "weh")]
;      (is (= "weh" (check-required-options ["-x"]))))))

;(deftest simple-test-2
;  (testing "something different"
;    (with-redefs [exit-now1! (constantly "weh")]
;      (is (= "weh" (check-required-options ["-x"]))))))

;(deftest simple-test-2
;  (testing "something different"
;    (with-redefs [exit-now! (fn [code] (str "weh"))]
;      (is (= "weh" (check-required-options ["-x"]))))))

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
            ;returned (check-required-options ["-x"])
            ;returned-code (:code returned)
            ;returned-message (:message returned)
            expected {:code expected-code :message expected-message}
            ]
        (is (= expected (check-required-options ["-x"])))))))

;        (is (= {:code expected-code :message expected-message} {:code returned-code :message returned-message})))))
;        )
;      (is (= {:code 1 :message "both '-l' and '-w' have to be provided!"} {:code 1 :message "both '-l' and '-w' have to be provided!"})))))
;      (is (= {:code 1 :message "both '-l' and '-w' have to be provided!"} (check-required-options ["-x"]))))))
