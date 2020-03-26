(defn search-char [i s c]
  "Search a char into a string"
  (if (and (not= (get s i) c) (not= i (count s)))
    (do (recur (inc i) s c))
    (do 
      (if (= i (count s))
        false
        true))))

(defn scramble [s1 s2 f]
  "Scramble a string. It returns True if a portion of a first character string
  parameter (a set of characters) can be rearranged to match with a second
  character string parameter, otherwise, return False"
  (let [i 0]
    (loop [j 0]
      (if (and (< j (count s2)) (f i s1 (get s2 j)))
        (do (recur (inc j)))
        (do
          (if (= j (count s2))
            true
            false))))))

(println "Does \"rqodlw\" contain \"world\"? TRUE")
(assert (scramble "rqodlw" "world" search-char))

(println "Does \"rqodlw\" contain \"worlx\"? FALSE")
(assert (not (scramble "rqodlw" "worlx" search-char)))

(println "Does \"rqodlw\" contain \"dworl\"? TRUE")
(assert (scramble "rqodlw" "dworl" search-char))

(println "Does \"w\" contain \"wo-rl\"? FALSE")
(assert (not (scramble "w" "wo-rl" search-char)))

(println "Does \"r\" contain \"r\"? TRUE")
(assert (scramble "r" "r" search-char))
