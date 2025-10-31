(ns dactyl-keyboard.frame
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]))

(def main-outline
  (let [main-sphere (->> (with-fn 300 (sphere 1400))
                         (translate [0 -110 -1326]))
        main-cube-length 420
        main-cube-width 186
        main-cube-heigh 70
        main-cube (->> (cube main-cube-length main-cube-width main-cube-heigh)
                       (translate [0 (/ main-cube-width 2) (/ main-cube-heigh 2)]))]
    (intersection main-sphere main-cube)
    ))

(spit "things_frame/base.scad"
      (write-scad main-outline))
