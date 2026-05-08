(ns dactyl-keyboard.logo
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.frame :refer [main-inline]]))

(spit "things_leds/leds_mount.scad"
  (write-scad
    (intersection
      main-inline
      (union
        ;(translate [-57 34 50] (with-fn 200 (cylinder 0.7 30)))
        ;(translate [-57 (+ 34 16.7) 50] (with-fn 200 (cylinder 0.7 30)))
        ;(translate [-57 (+ 34 16.7 16.7) 50] (with-fn 200 (cylinder 0.7 30)))

        (translate [-57 34 55]
                   (difference (cube 6.5 6.5 10)
                               (cube 5.5 5.5 10)))
        (translate [-57 (+ 34 16.7) 55]
                   (difference (cube 6.5 12.5 10)
                               (cube 5.5 5.5 10)))
        (translate [-57 (+ 34 16.7 16.7) 55]
                   (difference (cube 6.5 6.5 10)
                               (cube 5.5 5.5 10)))

        (translate [-53.5 (+ 34 16.7) 55] (cube 1 40 10))
        (translate [-60 (+ 34 16.7) 55] (cube 1 40 10))
    ))))
