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

(deftest retrieve-language
  (testing "return proper two letter code of the language"
    (let [input "-w:5 -l:pl -s:. -p:2"
          expected {:language "pl"}]
      (is (= expected (get-language input))))))

(deftest retrieve-number-of-words-per-password
  (testing "return required password lenght in words"
    (let [num (rand-int 255)
          input (str "-w:" num)
          expected {:words num}]
      (is (= expected (get-words-count input)))))
  (testing "return error if no words count provided"
    (let [input "-w:"
          expected {:error "no password length (in words) provided"}]
      (is (= expected (get-words-count input)))))
  (testing "return error if words count < 1"
    (let [input "-w:0"
          expected {:error "password should be at least 1 and max 255 words long"}]
      (is (= expected (get-words-count input)))))
  (testing "return error if words count > 255"
    (let [input "-w:256"
          expected {:error "password should be at least 1 and max 255 words long"}]
      (is (= expected (get-words-count input))))))

(deftest retrieve-password-count
  (testing "return 1 by default"
    (let [input ""
          expected {:passwords 1}]
      (is (= expected (get-passwords-count input)))))
  (testing "return 2 for -p:2"
    (let [input "-p:2"
          expected {:passwords 2}]
      (is (= expected (get-passwords-count input)))))
  (testing "return 10 for -p:10"
    (let [input "-p:10"
          expected {:passwords 10}]
      (is (= expected (get-passwords-count input)))))
  (testing "return X for -p:X"
    (let [num (rand-int 9999)
          input (str "-p:" num)
          expected {:passwords num}]
      (is (= expected (get-passwords-count input))))))

(deftest retrieve-separator
  (testing "return '-' by default"
    (let [input ""
          expected {:separator "-"}]
      (is (= expected (get-separator input)))))
  (testing "return one char as separator for input"
    (let [input "-s:*"
          expected {:separator "*"}]
      (is (= expected (get-separator input)))))
  (testing "return single char as separator (random input)"
    (let [character (str (char (rand-int 99)))
          input (str "-s:" character)
          expected {:separator character}]
      )))
