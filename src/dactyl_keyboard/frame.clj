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
       (translate [134 66 87])
       (scale [1 1.0 1.3])
       (rotate (/ π 30) [1 0 0])))

(defn well-sphere2 [radius]
  (->> (with-fn 150 (sphere radius))
       (translate [145 71 84])
       (scale [1.27 1.0 1.3])
       (rotate (/ π 30) [1 0 0])))

(defn well-sphere3 [radius]
    (->> (with-fn 150 (sphere radius))
       (scale [0.5 0.7 1.1])
       (translate [134 53 87])))

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
  (->> (cube 22 25 31)
       (translate [193 108 43])))

(def support-pillar-shift-well
  (->> (cube 21 24 16.8)
       (translate [193 108 19.4])))

(def support-pillar-plus-up
  (->> (cube 25.5 14 30)
       (translate [195.25 9 42])))
(def support-pillar-plus-up-negative
  (->> (cube 22 18 31)
       (translate [193 8 43])))

(def support-pillar-plus-well
  (->> (cube 21 14 5.6)
       (translate [193 8 25])))

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
  (->> (cube 20 20 9)
       (rotate (/ π 2.45) [0 0 1])
       (translate [32 110 41.6])))

(def bottom-plate-mount-top
  (union
    (->> (cube 14 14 55)
         (translate [70 9 35]))
    (difference
      (->> (cube 43.3 14 61)
           (rotate (/ π 4) [0 -1 0])
           (translate [57 9 59]))
      (->> (cube 40 14 55)
           (translate [88 9 61])))
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
  ;(union
  ;  (->> (cylinder 5, 13)
  ;       (translate [185 174 5.9])) ; Indent for a wooden screw:)
    (->> (with-fn 50 (cylinder 1.3, 32))
         (translate [185 174 5.9]))
    ;)
  )

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
      (translate [0 0 -2.9] ; to fully erase remaining of the sphere
                 (convert-dactyl-shapes caps-combined-outline)))
    ))

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

(def well-right
  (union
    ;(import "well_right.stl")
    ;(import "case_right_up.stl")
    (->> (cube 3 40 2) ; to close right border
         (translate [202 60 9]))
    (->> (cube 3 40 2)
         (translate [202 22 27])
         (rotate (/ π -7) [1 0 0]))
    (->> (cube 3 40 2)
         (translate [202 94 -28])
         (rotate (/ π 7) [1 0 0]))
    (intersection
      (difference
        (convert-dactyl-shapes dactyl-top-right)

        support-pillar-shift-up-negative
        support-pillar-home-up-negative
        support-pillar-five-up-negative
        support-pillar-plus-up-negative
        (well-sphere 78))
      main-inline)

    (difference
      support-pillar-shift-well
      (well-sphere 78)
      (translate [193 112 (+ 19.4 8.4 -15)] ; Indent for a wooden screw:)
                 (cylinder 5, 15))
      (->> (cylinder 1.3, 25)
           (translate [193 112 (+ 19.4 8.4 -1)]))
      )
    (difference
      (intersection
        support-pillar-plus-well
        main-inline)
      (->> (cylinder 1.3, 25)
           (translate [193 9 (+ 19.4 8.4 -4)])))
    (difference
      (intersection
        support-pillar-five-well
        main-inline)
      (well-sphere 78)
      (->> (cylinder 1.3, 25)
           (translate [108 5.5 (+ 19.4 18.4)])))
    (difference
      (intersection
        support-pillar-home-well
        main-inline)
      (->> (cylinder 1.3, 35)
           (translate [33 110 (+ 19.4 12.4)])))
    )
  )

(spit "things_frame/well_right.scad"
      (write-scad
        well-right))

(spit "things_frame/well_left.scad"
      (write-scad
        (->> well-right
             (mirror [1 0 0]))))

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
    ;(->> (with-fn 100 (cylinder 70 30)) ; cut for a cable
    ;     (rotate (/ π 2) [1 0 0])
    ;     (translate [0 15 -30]))
    ))

(def bottom-main-cylinder-inline
  (difference
    (->> (with-fn 50 (cylinder 90 416))
         (rotate (/ π 2) [0 1 0])
         (translate [0 63 89]))
    (->> (with-fn 50 (cylinder 112 32)) ; cut for a cable
         (rotate (/ π 2) [1 0 0])
         (translate [0 16 -70]))))

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
            third-divide-cube-inner
            )
          ;(convert-dactyl-shapes (import "../things/dactyl-top-right.stl"))
          ;(import "case_right_bottom.stl")
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
                  (->> (cylinder 1.3, 35)
                       (with-fn 50)
                       (translate [70 9 19])))
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
          ;(convert-dactyl-shapes (import "../things/dactyl-top-right.stl"))
          well-mount
          (mirror [1 0 0] well-mount)

          rear-mount
          (mirror [1 0 0] rear-mount)

          front-mount
          (mirror [1 0 0] front-mount)
          ))))

(def bottom-corner-leg
  (difference
    (->> (cylinder 7.5 2)
         (translate [100 49 0])
         (with-fn 50))
    (->> (cylinder 6.4 5)
         (translate [100 49 0])
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

(def middle-glue-reinforcement-bottom-right
  (union
    (difference
      (->> (cube 16 45.5 25) ; main-cube-width / 2
           (translate [0 27 50])
           (intersection bottom-main-cylinder-inline))
      (translate [0 0 2] bottom-main-cylinder-inline))
    (difference
      (->> (cube 16 45.5 25) ; main-cube-width / 2
           (translate [0 50 5])
           (intersection (intersection bottom-main-cylinder-inline bottom-main-inline)))
      (translate [0 0 2] (intersection bottom-main-cylinder-inline bottom-main-inline)))
    (difference
      (->> (cube 16 45.5 25) ; main-cube-width / 2
           (translate [0 130 5])
           (intersection bottom-thumbs-spacer-inline))
      (translate [0 0 2] bottom-thumbs-spacer-inline))
    ))
(def middle-glue-reinforcement-bottom-left
  (union
    (difference
      (->> (cube 16 2 16) ; main-cube-width / 2
           (translate [0 33 15])
           (intersection bottom-main-cylinder-inline))
      (translate [0 2 0] bottom-main-cylinder-inline))
    (difference
      (->> (cube 16 33.5 35) ; main-cube-width / 2
           (translate [0 90 5])
           (intersection (union (intersection bottom-main-cylinder-inline bottom-main-inline) bottom-thumbs-spacer-inline)))
      (translate [0 0 2] (union (intersection bottom-main-cylinder-inline bottom-main-inline) bottom-thumbs-spacer-inline)))
    (difference
      (->> (cube 16 30 40) ; main-cube-width / 2
           (translate [0 168.3 5])
           (intersection bottom-thumbs-spacer-inline))
      (translate [0 0 2] bottom-thumbs-spacer-inline))
    ))

(spit "things_frame/base_bottom_common.scad"
      (write-scad
        (union
           bottom-corner-leg
           bottom-middle-leg
          ;(intersection
          ;  top-plate-mount-top
          ;  bottom-main-cylinder-inline
          ;  )
          ;(intersection
          ;  top-plate-mount-shift
          ;  bottom-hand-rest-inline
          ;  )
          ;(intersection
          ;  top-plate-mount-home
          ;  bottom-thumbs-spacer-inline
          ;  )

          ; Border holders
          ; right
          ;(->> (cube 2 32 8)
          ;     (translate [207 64 7]))
          ;(->> (cube 2 15 12)
          ;     (translate [207 18 21]))
          ;(->> (cube 2 30 15)
          ;     (translate [207 120 23]))
          ;(->> (cube 2 20 15)
          ;     (translate [207 174 13]))
          ; front
          ;(->> (cube 30 2 15)
          ;     (translate [191 183 13]))
          ;(->> (cube 30 2 15)
          ;     (translate [111 183 23]))
          ;(->> (cube 30 2 15)
          ;     (translate [25 183 28]))
          ; back
          ;(->> (cube 30 2 13)
          ;     (translate [25 3 48]))
          ;(->> (cube 30 2 15)
          ;     (translate [167 3 30]))
          )

        (difference ; very slow render :(
          (union
            (intersection
              bottom-main-cylinder
              bottom-main-outline
              )
            bottom-hand-rest-outline
            bottom-thumbs-spacer
            )
          (union
            (intersection
              bottom-main-cylinder-inline
              bottom-main-inline)
            bottom-hand-rest-inline
            bottom-thumbs-spacer-inline
            half-divide-cube-left
            )
          )
        ))

(spit "things_frame/base_right_bottom.scad"
      (write-scad
        (->> (difference
               (import "base_bottom_common.stl")
               screw-cut-top-plate-mount-top
               screw-cut-top-plate-mount-shift
               screw-cut-top-plate-mount-home
               ))
        middle-glue-reinforcement-bottom-right
        ))

(spit "things_frame/base_left_bottom.scad"
      (write-scad
        (->> (difference
               (import "base_bottom_common.stl")
               screw-cut-top-plate-mount-top
               screw-cut-top-plate-mount-shift
               screw-cut-top-plate-mount-home
               )
             (mirror [1 0 0]))
        middle-glue-reinforcement-bottom-left
        ))

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
