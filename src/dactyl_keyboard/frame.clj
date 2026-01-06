(ns dactyl-keyboard.frame
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.dactyl :refer [thumbcaps caps caps-combined-outline dactyl-top-right]]))

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
(def half-divide-cube-left
  (->> (cube 210 186 70)
       (translate [-105 93 35])))

(def third-divide-cube-inner
  (->> (cube 184.5 189 70)
       (translate [0 94 35]))) ; TODO

(def third-divide-cube-outer
  (->> (cube 117.75 189 70)
       (translate [(+ (/ 184.5 2) (/ 117.75 2)) 94 35])))

(def main-outline
  (let [main-sphere (->> (with-fn 300 (sphere 1400))
                         (translate [0 -110 -1330]))
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
                          (translate [0 -110 (+ -1330 -2)]))
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
       (translate [125 66 87])
       (scale [1.1 1 1.3])
       (rotate (/ π 30) [1 0 0])))

(defn well-sphere2 [radius]
  (->> (with-fn 150 (sphere radius))
       (translate [145 71 84])
       (scale [1.27 1 1.3])
       (rotate (/ π 30) [1 0 0])))

(defn well-sphere3 [radius]
    (->> (with-fn 150 (sphere radius))

       (scale [0.72 0.7 1.1])
       (translate [140 53 87])))

(defn well-sphere [radius]
  (union
    (hull
      (well-sphere1 radius)
      (well-sphere2 radius))
    (intersection
      (well-sphere3 radius)
      (translate [135 10 47] (cube 55 30 30))
      )
  ))

(def main-box-minus-well-sphere
  (difference main-box-right (well-sphere 78)))

(def main-box-minus-well-sphere-top
  (difference main-box-minus-well-sphere (translate [0 0 -11] main-box-minus-well-sphere)))

(def keys-well
    (intersection (difference (well-sphere 78) (well-sphere 76)) main-outline))

(def support-pillar-shift-up
  (->> (cube 25.5 24 30)
       (translate [195.25 108 39])))
(def support-pillar-shift-up-negative
  (->> (cube 26.5 25 31)
       (translate [195.25 108 39])))

(def support-pillar-shift-well
  (->> (cube 25.5 24 19)
       (translate [195.25 108 14.4])))

(def support-pillar-plus-up
  (->> (cube 25.5 14 30)
       (translate [195.25 9 42])))
(def support-pillar-plus-up-negative
  (->> (cube 26 18 31)
       (translate [195.25 9 42])))

(def support-pillar-plus-well
  (->> (cube 25.5 14 20)
       (translate [195.25 9 16.9])))

(def support-pillar-five-up
  (difference
    (->> (cube 21 9.5 30)
         (translate [115 4 61.3]))
    (->> (cube 20 10 10)
         (rotate (/ π 12) [0 -1 0])
         (translate [120 4 43]))
    ))
(def support-pillar-five-up-negative
  (->> (cube 22 11 31)
       (translate [115 4 61.3])))

(def support-pillar-five-well
  (->> (cube 21 9.5 9)
       (translate [115 4 41.6])))

(def support-pillar-home-up
  (union
    (->> (cube 14 14 30)
         (rotate (/ π 2.45) [0 0 1])
         (translate [36 125 54.5]))
    (difference
      (->> (cube 25 14 20)
           (rotate (/ π 5.45) [0 1 0])
           (translate [25.5 127 52.5]))
      (->> (cube 40 40 10)
           (translate [25.5 127 34.5]))
      ))
    )

(def support-pillar-home-up-negative
  (->> (cube 20.3 18.8 31)
       (rotate (/ π 2.45) [0 0 1])
       (translate [29 110 58.5])))

(def support-pillar-home-well
  (difference
    (->> (cube 16 20 35.5)
       (rotate (/ π 2.45) [0 0 1])
       (translate [38.21 124.35 25]))
    (->> (cube 14.5 14.5 30)
         (rotate (/ π 2.45) [0 0 1])
         (translate [36 125 54.5]))
    ))

(def support-pillar-command-up
  (->> (cube 20 18.5 30)
       (rotate (/ π 2.45) [0 0 1])
       (translate [90 151.2 48.5])))

(def top-plate-mount-top
  (->> (cube 15 15 44.8)
       (translate [75 12 25])))
(def screw-cut-top-plate-mount-top
  (union
    (->> (cylinder 5, 47)
         (translate [75 12 15])) ; Indent for a wooden screw:)
    (->> (cylinder 1.3, 52)
         (translate [75 12 25]))
    )
  )

(def bottom-plate-mount-shift
  (->> (cube 35 21 45)
       (translate [195 177 27])))

(def top-plate-mount-shift
  (->> (cube 15 15 25)
       (translate [185 174 5.9])))
(def screw-cut-top-plate-mount-shift
    (->> (with-fn 50 (cylinder 1.7, 32))
         (translate [185 174 5.9])))

(def bottom-plate-mount-home
  (union
    (->> (cube 14 14 42)
       (translate [75 177 31]))
    (difference
      (->> (cube 45 14 75)
           (rotate (/ π 4) [0 -1 0])
           (translate [58 177 57]))
      (->> (cube 40 14 55)
           (translate [88 177 55])))))

(def top-plate-mount-home
  (->> (cube 15 15 25)
       (translate [25 174 17.4])))
(def screw-cut-top-plate-mount-home
  (union
    (->> (cylinder 5, 13)
         (translate [25 174 17.4])) ; Indent for a wooden screw:)
    (->> (cylinder 1.3, 32)
         (translate [25 174 17.4]))
    )
  )

(def middle-glue-reinforcement-up
  (union
    (intersection
      (difference
        (->> (cube 62 40 30)
             (translate [118 164 14])
             (rotate (/ π 12) [0 -1 0]))
        (->> main-inline
             (translate [0 -2 1])))
      main-inline)
    (intersection
      (difference
        (->> (cube 52 40 30)
             (translate [115 160 57])
             (rotate (/ π 12) [-0.8 -1 0]))
        (->> main-inline
             (translate [0 1 -2])))
      main-inline)
    (intersection
      (difference
        (->> (cube 40 10 34)
             (translate [118 7 15])
             (rotate (/ π 7) [0 -1 0]))
        (->> main-inline
             (translate [0 2 -2]))
        (well-sphere1 78))
      main-inline)))

(def base-right-up
  (union
    (difference
      (union main-box-minus-well-sphere-top keys-well)
      (translate [0 -0.3 -2.9] ; to fully erase remaining of the sphere
                 (convert-dactyl-shapes caps-combined-outline)))
    ))

(def bottom-plate-mount-top
  (difference
    (union
        (->> (cube 14 14 30)
             (translate [-7 9 60]))
      (->> (cube 20 14 50.5)
           (rotate (/ π 4) [0 -1 0])
           (translate [-24.8 9 70])))
    (->> (cube 10.4 2.4 30)
         (translate [-5.2 3.2 60]))
    )
  )

(spit "things_frame/base_right_up.scad"
      (write-scad
          base-right-up))

(spit "things_frame/base_middle_up.scad"
      (write-scad
        (union
          (difference
           (import "base_right_up.stl")
            third-divide-cube-outer)
          (mirror [1 0 0] (difference
                            (import "base_right_up.stl")
                            third-divide-cube-outer)))))

; Bottom plate
; bottom outline WIP

(def bottom-main-outline
  (difference
    (->> (difference main-outline (well-sphere 78))
         (translate [0 0 -11]))
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
    (->> (with-fn 150 (cylinder 92 420))
         (rotate (/ π 2) [0 1 0])
         (translate [0 63 89]))
    (->> (with-fn 100 (cylinder 110 30)) ; cut for a cable
         (rotate (/ π 2) [1 0 0])
         (translate [0 15 -70]))
    (->> (cylinder 4.5 25)
         (translate [203 8 7]))
    ))

(def bottom-main-cylinder-inline
  (difference
    (->> (with-fn 50 (cylinder 90 416))
         (rotate (/ π 2) [0 1 0])
         (translate [0 63 89]))
    (->> (with-fn 50 (cylinder 112 32)) ; cut for a cable
         (rotate (/ π 2) [1 0 0])
         (translate [0 16 -70]))
    (->> (cylinder 5.8 25)
         (translate[203 8 9.8]))
    (->> (cube 416 186 2) ; Bottom limiter
         (translate [0 93 1]))
    (->> (cube 416 2 70) ; Rear side limiter
         (translate [0 1 35]))))

(def bottom-hand-rest-outline
  (intersection
    (difference (->> (difference main-outline (well-sphere 78))
                     (translate [0 0 -11]))
                (->> (difference main-outline (well-sphere 78))
                     (translate [0 0 -22]) ))
    (->> (cube 420 200 60)
         (translate [0 210 30]))
    ))

(def bottom-hand-rest-inline
  (intersection
    (difference (->> (difference main-inline (well-sphere 78))
                     (translate [0 0 -5])) ; does it matter how low it gets?
                (->> (difference main-outline (well-sphere 78))
                     (translate [0 0 -18])))
    (->> (cube 420 200 60)
         (translate [0 210 30]))))

(def bottom-thumbs-spacer
  (intersection
    (->> (cube 420 110 30)
         (rotate (/ π 22) [1 0 0])
         (translate [0 130 23.4]))
    bottom-main-outline))

(def bottom-thumbs-spacer-inline
  (intersection
    (->> (cube 416 110 30)
         (rotate (/ π 22) [1 0 0] )
         (translate [0 130 25.4]))
    bottom-main-inline))

(def bottom-negative-inline
  (difference
    bottom-main-inline
    (union bottom-main-cylinder-inline
           bottom-hand-rest-inline
           bottom-thumbs-spacer-inline)))

(spit "things_frame/base_right_most_up.scad"
      (write-scad
        (union
          (difference
            (import "base_right_up.stl")
            third-divide-cube-inner)
          (difference
            (intersection
              support-pillar-shift-up
              main-inline)
            (well-sphere 78)
            (->> (with-fn 50 (cylinder 1.3, 35))
                 (translate [193 112 (+ 19.4 8.4 -15)])))
          (difference
            (intersection
              support-pillar-plus-up
              main-inline)
            (well-sphere 78)
            (->> (with-fn 50 (cylinder 1.3, 25))
                 (translate [193 9 (+ 19.4 8.4 -9)])))
          (difference
            (intersection
              support-pillar-five-up
              main-inline
              )
            (well-sphere 78)
            (->> (with-fn 50 (cylinder 1.3, 25))
                 (translate [108 5.5 (+ 19.4 18.4)])))
          (difference
            (intersection
              bottom-plate-mount-shift
              main-inline)
            screw-cut-top-plate-mount-shift
            bottom-negative-inline)
          ; Glue connections
          middle-glue-reinforcement-up
          )
        ))

(spit "things_frame/base_middle_up_final.scad"
      (let [well-mount
              (difference
                (intersection
                  support-pillar-home-up
                  main-inline
                  )
                (->> (cylinder 1.3, 35)
                     (with-fn 50)
                     (translate [36 125 29])))
              rear-mount
                (difference
                  (intersection
                    bottom-plate-mount-top
                    main-inline)
                  bottom-negative-inline
                  (->> (cylinder 1.7, 35)
                       (with-fn 50)
                       (translate [-5 9 39])))
              front-mount
                (difference
                  (intersection
                    bottom-plate-mount-home
                    main-inline)
                  bottom-negative-inline
                  (->> (cylinder 1.3, 35)
                       (with-fn 50)
                       (translate [75 177 15])))]
      (write-scad
        (union
          (import "base_middle_up.stl")
          well-mount
          (mirror [1 0 0] well-mount)

          rear-mount
          ;(mirror [1 0 0] rear-mount)

          front-mount
          (mirror [1 0 0] front-mount)

          ;(import "case_right_bottom.stl")
          ))))

(def well-right
  (union
    (->> (cube 3 35 2) ; to close right border
         (translate [202 60 6]))
    (->> (cube 3 35 2)
         (translate [202 22 25])
         (rotate (/ π -7) [1 0 0]))
    (->> (cube 3 28 2)
         (translate [202 86 -21.5])
         (rotate (/ π 9) [1 0 0]))
    (difference
       (intersection
        (union
          (->> (cube 2.5 23 22) ; to support middle connection to thumb isle
               (rotate (/ π 10) [0 0 -1])
               (translate [103 105 49]))
          (->> (cube 18 4 22)
               (translate [91.5 92 46]))
          (->> (cube 4.5 18 22)
               (rotate (/ π 10) [0 0 -1])
               (translate [78.3 86.3 46])))
        main-inline)
       (well-sphere1 78))

    (difference
      (intersection
       (convert-dactyl-shapes dactyl-top-right)
       ;(convert-dactyl-shapes (import "../things/dactyl-top-right.stl"))
       main-inline)

      support-pillar-shift-up-negative
      support-pillar-five-up-negative
      support-pillar-plus-up-negative
      (well-sphere 78)
      (->> (cylinder 1.3, 25)
           (translate [193 9 (+ 19.4 8.4 -4)]))
      (->> (cylinder 6 25)
           (translate [203 8 10]))
      (->> (cylinder 1.7, 27)
           (translate [203 8 13])))

    (difference
      support-pillar-shift-well
      bottom-negative-inline
      (translate [193 112 (+ 19.4 8.4 -17)] ; Indent for a wooden screw:)
                 (cylinder 4.5, 15))
      (->> (cylinder 1.3, 25)
           (translate [193 112 (+ 19.4 8.4 -1)]))
      (->> (cylinder 1.7, 25)
           (rotate (/ π 20) [1 0 0])
           (translate [203 112 10])))
    (difference
      support-pillar-plus-well
      bottom-negative-inline
      (translate [193 9 (+ 19.4 8.4 -13)] ; Indent for a wooden screw:)
                 (cylinder 4.5, 15))
      (->> (cylinder 1.3, 25)
           (translate [193 9 (+ 19.4 8.4 -4)]))
      (->> (cylinder 6 25)
           (translate [203 8 10]))
      (->> (cylinder 1.7, 27)
           (translate [203 8 13])))
    (difference
      (intersection
        support-pillar-five-well
        main-inline)
      (well-sphere 78)
      (->> (cylinder 1.3, 25)
           (translate [108 5.5 (+ 19.4 18.4)])))
    (difference
        support-pillar-home-well
        bottom-negative-inline
      (->> (cylinder 1.3, 45)
           (translate [36 125 (+ 19.4 12.4)]))
      (->> (cylinder 5, 35)
           (translate [36 125 19])) ; Indent for a wooden screw:)
      (->> (cylinder 1.7, 25)
           (rotate (/ π 20) [1 0 0])
           (translate [45 125 10])))
    )
  )

(spit "things_frame/well_right.scad"
      (write-scad
        well-right))

(spit "things_frame/well_left.scad"
      (write-scad
        (->> ; well-right
             (import "well_right.stl")
             (mirror [1 0 0]))))

(def bottom-corner-leg
  (difference
    (->> (cylinder 7.5 2)
         (translate [100 49 0])
         (with-fn 50))
    (->> (cylinder 6.4 5)
         (translate [100 49 0])
         (with-fn 50))))

(def bottom-middle-leg
  (union
    (difference
      (->> (cylinder 7.5 2)
           (translate [200 176 0])
           (with-fn 50))
      (->> (cylinder 6.4 5)
           (translate [200 176 0])
           (with-fn 50)))
    (difference
      (->> (cylinder [7.5 9] 8)
           (translate [200 176 4])
           (with-fn 50))
      bottom-hand-rest-outline)))

(def middle-glue-reinforcement-bottom-right
  (union
    (difference
      (->> (cube 24 46 25) ; main-cube-width / 2
           (rotate (/ π 6) [0 1 0])
           (translate [0 27 34])
           (intersection bottom-main-cylinder-inline))
      (translate [0 0 2] bottom-main-cylinder-inline))
    (difference
      (->> (cube 23 45.5 15) ; main-cube-width / 2
           (rotate (/ π 6) [0 1 0])
           (translate [0 50 -1])
           (intersection (intersection bottom-main-cylinder-inline bottom-main-inline)))
      (translate [0 0 2] (intersection bottom-main-cylinder-inline bottom-main-inline)))
    (difference
      (->> (cube 23 45.5 25) ; main-cube-width / 2
           (rotate (/ π 6) [0 1 0])
           (translate [0 130 2])
           (intersection bottom-thumbs-spacer-inline))
      (translate [0 0 2] bottom-thumbs-spacer-inline))
    (difference
      (->> (cube 34 2 20) ; main-cube-width / 2
           (rotate (/ π 6) [0 1 0])
           (translate [0 3 38]))
      (->> (with-fn 50 (cylinder 112 32)) ; cut for a cable
           (rotate (/ π 2) [1 0 0])
           (translate [0 16 -70])))))

(def middle-glue-reinforcement-bottom-left
  (union
    (difference
      (->> (cube 16 2 14) ; main-cube-width / 2
           (rotate (/ π 26) [0 0 1])
           (translate [0 32 14])
           (intersection bottom-main-cylinder-inline))
      (translate [0 2 0] bottom-main-cylinder-inline))
    (difference
      (->> (cube 24 33.5 25) ; main-cube-width / 2
           (rotate (/ π 6) [0 -1 0])
           (translate [0 90 -1.5])
           (intersection (union (intersection bottom-main-cylinder-inline bottom-main-inline) bottom-thumbs-spacer-inline)))
      (translate [0 0 2] (union (intersection bottom-main-cylinder-inline bottom-main-inline) bottom-thumbs-spacer-inline)))
    (difference
      (->> (cube 24 30 40) ; main-cube-width / 2
           (rotate (/ π 6) [0 -1 0])
           (translate [5 168.3 0])
           (intersection bottom-thumbs-spacer-inline))
      (translate [0 0 2] bottom-thumbs-spacer-inline))
    (difference
      (->> (cube 44 2 15)
           (rotate (/ π 7) [0 -11 0])
           (translate [-4 183 20]))
      (translate [0 0 0] bottom-negative-inline))))

(def usb-hole-cut
  (hull
    (->> (cylinder 1.7 4)
         (with-fn 50)
         (rotate (/ π 2) [1 0 0])
         (translate [4, 31, 25]))
    (->> (cylinder 1.7 4)
         (with-fn 50)
         (rotate (/ π 2) [1 0 0])
         (translate [4, 31, 25])
         (mirror [1 0 0]))))

(spit "things_frame/base_bottom_common.scad"
      (write-scad
        (union
          (difference
            (union
              (intersection
                bottom-main-cylinder
                bottom-main-outline
                )
              bottom-hand-rest-outline
              bottom-thumbs-spacer
              )
            (union
              bottom-main-cylinder-inline
              (difference (->> (difference main-inline
                                           (translate [0 0 -13] main-inline))
                               (translate [0 0 -5]))
                          (->> (cube 420 140 80)
                               (translate [0 70 30])))
              bottom-thumbs-spacer-inline

              usb-hole-cut
              half-divide-cube-left))
          bottom-corner-leg
          bottom-middle-leg
          ; right
          (->> (cube 2 46 10)
               (translate [207 63 7]))
          (->> (cube 2 30 12)
               (translate [207 144 18]))
          ; front
          (difference
            (->> (cube 44 2 25)
                 (rotate (/ π 7) [0 1 0])
                 (translate [141 183 7]))
            (translate [0 0 -18] main-inline)))
        ))

(spit "things_frame/base_right_bottom.scad"
      (write-scad
        (difference
          (union
            (import "base_bottom_common.stl")
            middle-glue-reinforcement-bottom-right)
          ; TODO: add holes for screws once wells are ready
          (->> (cube 20 200 70)
               (translate [-19 100 36]))
          (->> (cylinder [4.5 1.4] 3) ; In rear cylinder
               (with-fn 50)
               (translate [203 8 21]))
          (->> (cylinder [4.5 1.4] 3) ; Side front
               (with-fn 50)
               (translate [185 174 6.5]))
          (->> (cylinder [4.5 1.4] 3) ; Middle front
               (with-fn 50)
               (translate [75 177 15.5]))
          (->> (cylinder [4.5 1.4] 3) ; well shift
               (with-fn 50)
               (translate [203 112 6]))
          (->> (cylinder [4.5 1.4] 3) ; well home
               (with-fn 50)
               (translate [45 125 7.9]))
          ;203 8 13
          ;203 112 10
          ;45 125
          (->> (cylinder 1.7 10) ; Middle hole in glue mount
               (with-fn 50)
               (translate [-5 9 40]))
          )))

(spit "things_frame/base_left_bottom.scad"
      (write-scad
        (difference
          (union
            (mirror [1 0 0]
                    (import "base_bottom_common.stl"))
            middle-glue-reinforcement-bottom-left)
          ; TODO: add holes for screws once wells are ready
          (->> (cube 20 200 70)
               (translate [19 100 35])))))

(spit "things_frame/top_raspberry_pi_pico_mount.scad"
      (write-scad
        (union
          ;(import "case_left_up.stl")
          ;(import "case_left_bottom.stl")
          ;middle-glue-reinforcement-bottom-left
          (intersection
            (difference
              (->> (cube 40 70 50)
                   (translate [0 67.5 50]))
              support-pillar-home-up-negative
              (mirror [1 0 0] support-pillar-home-up-negative)
              (->> (cube 26 60 10)
                   (translate [0 60 27])))
              main-inline)
            )
          )
        )

(spit "things_frame/all_combined.scad"
      (write-scad
        (union
          ;(import "base_right_up.stl")
          ;(convert-dactyl-shapes caps thumbcaps)
          ;(convert-dactyl-shapes caps-combined-outline)
          ;(import "base_middle_up.stl")
          ;(import "well_right.stl")
          (import "case_right_bottom.stl")
          ;(intersection
          ;  bottom-main-cylinder
          ;  bottom-main-outline
          ;  )
          )))
