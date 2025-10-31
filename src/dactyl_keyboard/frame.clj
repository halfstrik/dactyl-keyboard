(ns dactyl-keyboard.frame
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]))

(def main-outline
  (let [main-sphere (->> (with-fn 200 (sphere 900))
                         (translate [0 5 -850]))
        main-cube-length 400
        main-cube-width 200
        main-cube-heigh 50
        main-cube (->> (cube main-cube-length main-cube-width main-cube-heigh)
                       (translate [0 (/ main-cube-width 2) (/ main-cube-heigh 2)]))]
    (intersection main-sphere main-cube)
    ))

(spit "things_frame/base.scad"
      (write-scad main-outline))
