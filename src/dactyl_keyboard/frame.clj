(ns dactyl-keyboard.frame
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.dactyl :refer [thumbcaps caps caps-combined-outline dactyl-top-right]]))

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
                       (translate [0 (/ main-cube-width 2) (/ main-cube-heigh 2)]))
        main-back-sphere (->> (with-fn 300 (sphere 900))
                              (translate [0 310 -785]))]
    (intersection main-sphere main-cube main-back-sphere)))

(def main-inline
  (let [main-sphere (->> (with-fn 300 (sphere 1400))
                          (translate [0 -110 (+ -1326 -2)]))
         main-cube-length (- 420 4)
         main-cube-width (- 186 4)
         main-cube-heigh 70
         main-cube (->> (cube main-cube-length main-cube-width main-cube-heigh)
                        (translate [0 (+ (/ main-cube-width 2) 2) (- (/ main-cube-heigh 2) 2)]))
        main-back-sphere (->> (with-fn 300 (sphere 900))
                              (translate [0 310 (+ -785 -2)]))]
     (intersection main-sphere main-cube main-back-sphere)))

;(spit "things_frame/base.scad"
;      (write-scad (difference main-outline main-inline)))

(def main-box
  (difference main-outline main-inline))

(defn well-sphere1 [radius]
  (->> (with-fn 150 (sphere radius))
       (translate [138 66 87])
       (scale [1 1.0 1.3])
       (rotate (/ π 30) [1 0 0])))

(defn well-sphere2 [radius]
  (->> (with-fn 150 (sphere radius))
       (translate [145 71 84])
       (scale [1.27 1.0 1.3])
       (rotate (/ π 30) [1 0 0])))

(defn well-sphere [radius]
  (hull
    (well-sphere1 radius)
    (well-sphere2 radius)))

(def main-box-minus-well-sphere
  (difference main-box (well-sphere 78)))

(def keys-well
    (intersection (difference (well-sphere 78) (well-sphere 76)) main-outline))

(def support-pillar-shift-up
  (->> (cube 21 24 30)
       (translate [193 108 43])))

(def support-pillar-shift-well
  (->> (cube 21 24 16.8)
       (translate [193 108 19.4])))

(spit "things_frame/base_well.scad"
      (write-scad
        (union
          ;(difference
          ;  (union main-box-minus-well-sphere keys-well)
          ;  ;(minkowski (sphere 1.8) ; uncomment on the final render, takes time
          ;  (translate [0 0 -2] ; to fully erase remaining of the sphere
          ;             (convert-dactyl-shapes caps-combined-outline)))
          ;(convert-dactyl-shapes caps thumbcaps)

          (difference
            (intersection
              support-pillar-shift-up
              main-inline)
            (well-sphere 78))

          (convert-dactyl-shapes dactyl-top-right)
          (difference
            support-pillar-shift-well
            (well-sphere 78))
          )
        )
    )
