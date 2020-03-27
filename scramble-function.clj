(ns clojure.robins10.scramble
   (:gen-class))

(require '[clojure.string :refer :all])

(defn scramble [s1 s2]
  "Scramble a string. It returns True if a portion of a first character string
  parameter (a set of characters) can be rearranged to match with a second
  character string parameter, otherwise, return False"
    (if (and 
          (> (count s1) 0)
          (> (count s2) 0))
      (let [search-char (fn [] (includes? s1 s2))
            search-first (fn [] (includes? s1 (subs s2 0 1)))]
        (if (= (count s2) 1)
          (search-char)
          (if (search-first)
            (recur s1 (subs s2 1 (count s2)))
            false)))))

; Tests
(def str1 "rwodlw")
(def str2 "world")
(println (str "Does \""str1"\" contain \""str2"\"? TRUE"))
(assert (scramble str1 str2))

(def str1 "rwodlw")
(def str2 "worlx")
(println (str "Does \""str1"\" contain \""str2"\"? FALSE"))
(assert (not (scramble str1 str2)))

(def str1 "rwodlw")
(def str2 "dworl")
(println (str "Does \""str1"\" contain \""str2"\"? TRUE"))
(assert (scramble str1 str2))

(def str1 "w")
(def str2 "wo-rld")
(println (str "Does \""str1"\" contain \""str2"\"? FALSE"))
(assert (not (scramble str1 str2)))

(def str1 "rwodlw")
(def str2 "-world")
(println (str "Does \""str1"\" contain \""str2"\"? FALSE"))
(assert (not (scramble str1 str2)))

(def str1 "r")
(def str2 "r")
(println (str "Does \""str1"\" contain \""str2"\"? TRUE"))
(assert (scramble str1 str2))

(def str1 "r")
(def str2 "x")
(println (str "Does \""str1"\" contain \""str2"\"? FALSE"))
(assert (not (scramble str1 str2)))
