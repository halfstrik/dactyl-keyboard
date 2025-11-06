(ns dactyl-keyboard.frame
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.dactyl :refer [thumbcaps caps dactyl-top-right]]))

(defn convert-gen4 [shape]
   (translate [0 58 0] (mirror [0 1 0] shape)))

(defn convert-dactyl-shapes [& shapes]
  (translate [125 58 0]
             (mirror [0 1 0]
                     (rotate (/ π 60) [0 1 0]
                             (list shapes)
                             )
                     )
             )
  )

(def main-outline
  (let [main-sphere (->> (with-fn 300 (sphere 1400))
                         (translate [0 -110 -1326]))
        main-cube-length 420
        main-cube-width 186
        main-cube-heigh 70
        main-cube (->> (cube main-cube-length main-cube-width main-cube-heigh)
                       (translate [0 (/ main-cube-width 2) (/ main-cube-heigh 2)]))]
    (intersection main-sphere main-cube)))

(def main-inline
  (let [main-sphere (->> (with-fn 300 (sphere 1400))
                          (translate [0 -110 (+ -1326 -2)]))
         main-cube-length (- 420 4)
         main-cube-width (- 186 4)
         main-cube-heigh 70
         main-cube (->> (cube main-cube-length main-cube-width main-cube-heigh)
                        (translate [0 (+ (/ main-cube-width 2) 2) (- (/ main-cube-heigh 2) 2)]))]
     (intersection main-sphere main-cube)))

;(spit "things_frame/base.scad"
;      (write-scad (difference main-outline main-inline)))

(def main-box
  (difference main-outline main-inline))

(def well-sphere
  (->> (with-fn 150 (sphere 78))
       (translate [103 60 86])
       (scale [1.75 1.1 1.3])
       (rotate (/ π 30) [1 0 0])))

(def main-box-minus-well-sphere
  (difference main-box well-sphere))

(def keys-well
  (let [inner-sphere (->> (with-fn 150 (sphere 76))
                    (translate [103 60 86])
                    (scale [1.75 1.1 1.3])
                    (rotate (/ π 30) [1 0 0]))]
    (intersection (difference well-sphere inner-sphere) main-outline)))

(spit "things_frame/base_well.scad"
      (write-scad (union main-box-minus-well-sphere keys-well (convert-dactyl-shapes thumbcaps caps))))
