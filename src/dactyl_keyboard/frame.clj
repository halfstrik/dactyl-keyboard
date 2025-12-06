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

(def half-divide-cube
  (->> (cube 210 186 70)
       (translate [105 93 35])))

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

(def main-box-right
    (intersection main-box half-divide-cube))

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
  (difference main-box-right (well-sphere 78)))

(def main-box-minus-well-sphere-top
  (difference main-box-minus-well-sphere (translate [0 0 -10] main-box-minus-well-sphere)))

(def keys-well
    (intersection (difference (well-sphere 78) (well-sphere 76)) main-outline))

(def support-pillar-shift-up
  (->> (cube 21 24 30)
       (translate [193 108 43])))

(def support-pillar-shift-well
  (->> (cube 21 24 16.8)
       (translate [193 108 19.4])))

(def support-pillar-plus-up
  (->> (cube 21 14 30)
       (translate [193 8 43])))

(def support-pillar-plus-well
  (->> (cube 21 14 5.6)
       (translate [193 8 25])))

(def support-pillar-five-up
  (->> (cube 21 9.5 30)
       (translate [115 4 61.3])))

(def support-pillar-five-well
  (->> (cube 21 9.5 9)
       (translate [115 4 41.6])))

(def support-pillar-home-up
  (->> (cube 20 20 30)
       (rotate (/ π 2.45) [0 0 1])
       (translate [32 110 61.5])))

(def support-pillar-home-well
  (->> (cube 20 20 9)
       (rotate (/ π 2.45) [0 0 1])
       (translate [32 110 41.6])))

(spit "things_frame/base_well.scad"
      (write-scad
        (union
          (difference
            (union main-box-minus-well-sphere-top keys-well)
            ;(minkowski (sphere 1.8) ; uncomment on the final render, takes time
            (translate [0 0 -2] ; to fully erase remaining of the sphere
                       (convert-dactyl-shapes caps-combined-outline)))
          ;(convert-dactyl-shapes caps thumbcaps)

          ;half-divide-cube
          ;(translate [0 0 -10] main-outline)

          (difference
            (intersection
              support-pillar-shift-up
              main-inline)
            (well-sphere 78))
          (difference
            (intersection
              support-pillar-plus-up
              main-inline)
            (well-sphere 78))
          (difference
            (intersection
              support-pillar-five-up
              main-inline)
            (well-sphere 78))
          (intersection
            support-pillar-home-up
            main-inline)

          ;(convert-dactyl-shapes dactyl-top-right)
          ;(difference
          ;  support-pillar-shift-well
          ;  (well-sphere 78))
          ;(intersection
          ;  support-pillar-plus-well
          ;  main-inline)
          ;(difference
          ;  (intersection
          ;    support-pillar-five-well
          ;    main-inline)
          ;  (well-sphere 78))
          ;(intersection
          ;  support-pillar-home-well
          ;  main-inline)
          )
        )
    )
