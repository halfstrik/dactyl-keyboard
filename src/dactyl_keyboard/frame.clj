(ns dactyl-keyboard.frame
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [dactyl-keyboard.dactyl :refer [thumbcaps caps]]))

(defn convert-gen4 [shape]
   (translate [0 58 0] (mirror [0 1 0] shape)))

(defn convert-caps [thumbcaps caps]
  (translate [120 58 0] (mirror [0 1 0] [thumbcaps caps])))

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

(def main-inline
  (let [main-sphere (->> (with-fn 300 (sphere 1400))
                          (translate [0 -110 (+ -1326 -2)]))
         main-cube-length (- 420 4)
         main-cube-width (- 186 4)
         main-cube-heigh 70
         main-cube (->> (cube main-cube-length main-cube-width main-cube-heigh)
                        (translate [0 (+ (/ main-cube-width 2) 2) (- (/ main-cube-heigh 2) 2)]))]
     (intersection main-sphere main-cube)
     ))

(spit "things_frame/base.scad"
      (write-scad (difference main-outline main-inline)))

(def well-sphere
  (let [sphere (->> (with-fn 150 (sphere 82))
                    (translate [103 57 94])
                    (scale [1.75 1 1.3]))]
    (difference (difference main-outline main-inline) sphere)
    )) ; TODO: need to align with keys/caps

(spit "things_frame/base_well.scad"
      (write-scad (union well-sphere (convert-caps thumbcaps caps))))
