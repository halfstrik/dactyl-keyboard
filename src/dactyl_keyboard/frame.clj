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
  (difference main-box-minus-well-sphere (translate [0 0 -13] main-box-minus-well-sphere)))

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

(def bottom-plate-mount-top
  (->> (cube 15 15 35)
       (translate [75 12 65])))

(def bottom-plate-mount-shift
  (->> (cube 15 15 35)
       (translate [185 174 36])))

(def bottom-plate-mount-home
  (->> (cube 15 15 35)
       (translate [25 174 51])))

(def middle-glue-reinforcement-right
  (union
    (difference
      (->> (cube 20 5 15)
           (translate [0 2.5 50])
           (intersection main-inline))
      (translate [0 2 0] main-inline))
    (difference
      (->> (cube 20 45.5 25) ; main-cube-width / 2
           (translate [0 22.25 55])
           (intersection main-inline))
      (translate [0 0 -2] main-inline))
    (difference
      (->> (cube 20 45.5 25) ; main-cube-width / 2
           (translate [0 (+ 91 22.25) 55])
           (intersection main-inline))
      (translate [0 0 -2] main-inline))))

(def middle-glue-reinforcement-left
  (union
    (difference
      (->> (cube 20 5 20)
           (translate [0 (- 186 2.5) 32])
           (intersection main-inline))
      (translate [0 -2 0] main-inline))
    (difference
      (->> (cube 20 45.5 45) ; main-cube-width / 2
           (translate [0 (+ 22.25 45.5) 45])
           (intersection main-inline))
      (translate [0 0 -2] main-inline))
    (difference
      (->> (cube 20 49.5 45) ; main-cube-width / 2
           (translate [0 (+ 91 24.25 45.5) 45])
           (intersection main-inline))
      (translate [0 0 -2] main-inline))))

(def base-right-up
  (union
    (difference
      (union main-box-minus-well-sphere-top keys-well)
      ;(minkowski (sphere 1.8) ; uncomment on the final render, takes time
      (translate [0 0 -2] ; to fully erase remaining of the sphere
                 (convert-dactyl-shapes caps-combined-outline)))
    ;(convert-dactyl-shapes caps thumbcaps)

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

    (intersection
      bottom-plate-mount-top
      main-inline)
    (intersection
      bottom-plate-mount-shift
      main-inline)
    (intersection
      bottom-plate-mount-home
      main-inline)))

(spit "things_frame/base_right_up.scad"
      (write-scad
        (union
          base-right-up
          middle-glue-reinforcement-right)))

(spit "things_frame/base_left_up.scad"
      (write-scad
        (union
          (->> base-right-up
               (mirror [1 0 0]))
          middle-glue-reinforcement-left)))

(def well-right
  (union
    (convert-dactyl-shapes dactyl-top-right)
    (difference
      support-pillar-shift-well
      (well-sphere 78)
      (translate [193 108 (+ 19.4 8.4 -15)] ; Indent for a wooden screw:)
                 (cylinder 5, 15)))
    (intersection
      support-pillar-plus-well
      main-inline)
    (difference
      (intersection
        support-pillar-five-well
        main-inline)
      (well-sphere 78))
    (intersection
      support-pillar-home-well
      main-inline)
    ))

;(spit "things_frame/well_right.scad"
;      (write-scad
;        well-right))

;(spit "things_frame/well_left.scad"
;      (write-scad
;        (->> well-right
;             (mirror [1 0 0]))))

; Bottom plate
; bottom outline WIP

(def bottom-main-outline
  (difference
    (->> (difference main-outline (well-sphere 78))
         (translate [0 0 -13]))
    (translate [0 0 -10] (cube 500 500 20)) ; cut below 0z
    ))

(def bottom-main-inline
  (difference
    (->> main-inline
         (translate [0 0 -9]))
    (translate [0 0 -8] (cube 500 500 20)) ; cut below 2z
    ))

(def bottom-main-cylinder
  (difference
    (->> (with-fn 150 (cylinder 100 420))
         (rotate (/ π 2) [0 1 0])
         (translate [0 63 98]))
    (->> (with-fn 100 (cylinder 40 30)) ; cut for a cable
         (rotate (/ π 2) [1 0 0])
         (translate [0 15 0]))))

(def bottom-main-cylinder-inline
  (difference
    (->> (with-fn 50 (cylinder 98 416))
         (rotate (/ π 2) [0 1 0])
         (translate [0 63 98]))
    (->> (with-fn 50 (cylinder 42 32)) ; cut for a cable
         (rotate (/ π 2) [1 0 0])
         (translate [0 16 0]))))

(def bottom-hand-rest-outline
  (intersection
    (difference (->> (difference main-outline (well-sphere 78))
                     (translate [0 0 -13]))
                (->> (difference main-outline (well-sphere 78))
                     (translate [0 0 -26]) ))
    (->> (cube 420 200 60)
         (translate [0 210 30]))
    ))

(def bottom-hand-rest-inline
  (intersection
    (difference (->> (difference main-inline (well-sphere 78))
                     (translate [0 0 -11]))
                (->> (difference main-outline (well-sphere 78))
                     (translate [0 0 -24])))
    (->> (cube 420 200 60)
         (translate [0 210 30]))))

(def bottom-thumbs-spacer
  (intersection
    (->> (cube 420 110 30)
         (rotate (/ π 22) [1 0 0] )
         (translate [0 130 23.4]))
    bottom-main-outline))

(def bottom-thumbs-spacer-inline
  (intersection
    (->> (cube 416 110 30)
         (rotate (/ π 22) [1 0 0] )
         (translate [0 130 25.4]))
    bottom-main-inline))

(def bottom-corner-leg
  (difference
    (->> (cylinder 7.5 2)
         (translate [100 51 0])
         (with-fn 50))
    (->> (cylinder 6.4 5)
         (translate [100 51 0])
         (with-fn 50))
    )
  )

(def bottom-middle-leg
  (union
    (difference
      (->> (cylinder 7.5 2)
           (translate [200 176 0])
           (with-fn 50))
      (->> (cylinder 6.4 5)
           (translate [200 176 0])
           (with-fn 50))
      )
    (difference
      (->> (cylinder [7.5 9] 8)
           (translate [200 176 4])
           (with-fn 50))
      bottom-hand-rest-outline)
    )
  )

(spit "things_frame/base_right_bottom.scad"
      (write-scad
        (union
          (import "base_right_bottom.stl") ; to speed up loading
          (import "base_right_up.stl")
           bottom-corner-leg
           bottom-middle-leg
          )

        ;(difference ; very slow render :(
        ;  (union
        ;    (intersection
        ;      ;base-right-up
        ;      bottom-main-cylinder
        ;      bottom-main-outline
        ;      )
        ;    bottom-hand-rest-outline
        ;    bottom-thumbs-spacer
        ;    )
        ;  (union
        ;    (intersection
        ;      bottom-main-cylinder-inline
        ;      bottom-main-inline)
        ;    bottom-hand-rest-inline
        ;    bottom-thumbs-spacer-inline
        ;    )
        ;  )
        ))
