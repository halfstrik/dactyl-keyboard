(ns dactyl-keyboard.frame
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.dactyl :refer [thumbcaps caps caps-combined-outline dactyl-top-right dactyl-top-left hotswap-sockets-support-under-caps hotswap-sockets-support-under-thumbcaps]]))

(defn convert-dactyl-shapes [& shapes]
  (translate [125 58 0]
             (mirror [0 1 0]
                     (rotate (/ π 60) [0 1 0]
                             (list shapes)
                             )
                     )
             )
  )

(defn convert-dactyl-shapes-left [& shapes]
  (translate [-125 58 0]
             (mirror [0 1 0]
                     (rotate (/ π 60) [0 -1 0]
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
       (translate [0 94 35])))

(def third-divide-cube-outer
  (->> (cube 117.95 189 70) ; used to be 117.75, but due to two glue points, had to shorten middle part by 0.13
       (translate [(+ (/ 184.5 2) (/ 117.75 2)) 94 35]))) ; if still back will pop, then cut a bit more

(defn rounded-z-cube
  [[x y z] r]
  (let [dx (- (/ x 2) r)
        dy (- (/ y 2) r)]
    (extrude-linear {:height z}
                    (hull
                      (for [ix [-1 1] iy [-1 1]]
                        (translate [(* dx ix) (* dy iy) 0]
                                   (circle r)))))))

(def main-outline
  (let [main-sphere (->> (with-fn 300 (sphere 1400))
                         (translate [0 -110 -1330]))
        main-cube-length 420
        main-cube-width 186
        main-cube-heigh 70
        main-cube (->> (rounded-z-cube [main-cube-length main-cube-width main-cube-heigh] 4)
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
         main-cube (->> (rounded-z-cube [main-cube-length main-cube-width main-cube-heigh] 4)
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
  (let [main-cube-length (- 420 2)
        main-cube-width (- 186 2)
        main-cube-heigh 70
        main-cube (->> (rounded-z-cube [main-cube-length main-cube-width main-cube-heigh] 4)
                       (translate [0 (+ (/ main-cube-width 2) 1) (- (/ main-cube-heigh 2) 2)]))]
  (difference main-box-minus-well-sphere
              (translate [0 0 -14] main-box-minus-well-sphere)
              (difference (translate [0 0 -11] main-box-minus-well-sphere)
                          main-cube)
              (translate [200 6 13.2] (cube 30 20 20)))))

(spit "things_frame/main-box-minus-well-sphere-top.scad"
       (write-scad
         main-box-minus-well-sphere-top))

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
  (difference
    (->> (cube 25.5 14 20)
         (translate [195.25 9 16.9]))
    (->> (cube 10 10 10) ; cut outer support for curved corner
         (rotate (/ π 4) [0 0 1]) ; TODO: double check that this is correct
         (translate [213 2 24.5]))))

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

(def support-pillar-home-well
  (difference
    (->> (cube 35 17 49)
       (rotate (/ π 2.45) [0 0 1])
       (translate [37.21 124.35 28]))
    (->> (cube 14.5 14.5 30)
         (rotate (/ π 2.45) [0 0 1])
         (translate [36 125 54.5])))
    )

(def bottom-plate-mount-shift
  (->> (cube 35 21 45)
       (translate [195 177 27])))

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

(def middle-glue-reinforcement-up
  (difference
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
        main-inline))
    (->> (cube 10 30 30)
         (translate [77.5 184.5 30]))
    ))

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
    (->> (cylinder 3.7 25)
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

(def bottom-main-cylinder-inline-cut-rgb
  (intersection
    (->> (with-fn 50 (cylinder 91 416))
         (rotate (/ π 2) [0 1 0])
         (translate [0 63 89]))
    (->> (cube 78 86 40)
         (translate [158 53 21]))))

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
    (translate [0 0 0.5] ; Move objects slightly up, so we will have a tiny gap, otherwise sending required
      (union bottom-main-cylinder-inline
             bottom-hand-rest-inline
             bottom-thumbs-spacer-inline))))

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
            (->> (with-fn 50 (cylinder 1.7, 35))
                 (translate [193 112 (+ 19.4 8.4 -15)])))
          (difference
            (intersection
              support-pillar-plus-up
              main-inline)
            (well-sphere 78)
            (->> (with-fn 50 (cylinder 1.7, 25))
                 (translate [193 9 (+ 19.4 8.4 -9)])))
          (difference
            (intersection
              support-pillar-five-up
              main-inline
              )
            (well-sphere 78)
            (->> (with-fn 50 (cylinder 1.7, 25))
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

(spit "things_frame/base_left_most_up.scad"
      (write-scad
        (->> (import "case_right_most_up.stl")
             (mirror [1 0 0]))))

(spit "things_frame/base_middle_up_final.scad"
      (let [
              well-mount
                (difference
                  (intersection
                    support-pillar-home-up
                    main-inline
                    )
                  (->> (cylinder 1.7, 35)
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
                  (->> (cylinder 1.7, 35)
                       (with-fn 50)
                       (translate [75 177 15])))]
      (write-scad
        (union
          (import "base_middle_up.stl")
          well-mount
          (mirror [1 0 0] well-mount)

          rear-mount

          front-mount
          (mirror [1 0 0] front-mount)

          ;(import "case_right_bottom.stl")
          ))))

(def well-right
  (union
    (->> (cube 3 36 2) ; to close right border
         (translate [202 61 7]))
    (->> (cube 3 35 2)
         (translate [202 22 25.8])
         (rotate (/ π -7) [1 0 0]))
    (->> (cube 3 26 2)
         (translate [202 86 -20.5])
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

      (->> (cube 19 5 5) ; cut for "3" key, so keycap won't stick
           (rotate (/ π 10) [0 1 0])
           (rotate (/ π 10) [-1 0 0])
           (rotate (/ π 27) [0 0 1])
           (translate [131.4 10.4 30]))
      (->> (cube 19 19 5) ; cut for "5" key, so keycap won't stick
           (rotate (/ π 6) [0 1 0])
           (rotate (/ π 7) [-1 0 0])
           (rotate (/ π 14) [0 0 1])
           (translate [96.2 18.5 44.6]))
      (->> (cube 5 5 5) ; cut top most corner so it won't hinder middle plate
           (translate [90 13 58.5]))
      support-pillar-shift-up-negative
      support-pillar-five-up-negative
      support-pillar-plus-up-negative
      (well-sphere 78)
      (->> (cylinder 2.2, 25)
           (translate [193 9 (+ 19.4 8.4 -4)]))
      (->> (cylinder 6 25)
           (translate [203 8 10]))
      (->> (cylinder 1.7, 27)
           (translate [203 8 13]))
      (difference ; Top corner middle glue plate cut
        (->> (cube 40.2 10.2 34.2)
             (translate [118 7 15])
             (rotate (/ π 7) [0 -1 0]))
        (->> main-inline
             (translate [0 2 -2]))
        (well-sphere1 78))
      (->> (cube 2.2 46.2 10.2) ; Right-most mount plate cut
           (translate [207 63 7]))
     )

    (difference
      support-pillar-shift-well
      bottom-negative-inline
      (translate [193 112 (+ 19.4 8.4 -17)] ; Indent for a wooden screw:)
                 (cylinder 4.5, 15))
      (->> (cylinder 2, 25)
           (translate [193 112 (+ 19.4 8.4 -1)]))
      (->> (cylinder 1.7, 25)
           (rotate (/ π 20) [1 0 0])
           (translate [203 112 10])))
    (difference
      support-pillar-plus-well
      bottom-negative-inline
      (translate [193 9 (+ 19.4 8.4 -13)] ; Indent for a wooden screw:)
                 (cylinder 4.5, 15))
      (->> (cube 22 2 17) ; Cut for switch to be able to insert
           (translate [190 15 16.4]))
      (->> (cylinder 2, 25)
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
      (->> (cylinder 2, 25)
           (translate [108 5.5 (+ 19.4 18.4)])))
    (difference
      (intersection
        support-pillar-home-well
        main-inline)
      bottom-negative-inline
      (->> (cylinder 2, 45)
           (translate [36 125 (+ 19.4 12.4)]))
      (->> (cylinder 3.7, 35)
           (translate [36 125 17]))
      (->> (cylinder 1.7, 25)
           (rotate (/ π 20) [1 0 0])
           (translate [42.7 125 10]))
      (difference ; Cut for tilted connection for 3d print optimization
        (->> (cube 25 16 20)
             (rotate (/ π 5.45) [0 1 0])
             (translate [25.5 127 52.5]))
        (->> (cube 40 40 10)
             (translate [25.5 127 34.5]))))))

(spit "things_frame/well_right.scad"
      (write-scad
        well-right))

(spit "things_frame/well_left.scad"
      (write-scad
          (union
            (->> (cube 3 36 2) ; to close right border
                 (translate [202 61 7])
                 (mirror [1 0 0]))
            (->> (cube 3 35 2)
                 (translate [202 22 25.8])
                 (rotate (/ π -7) [1 0 0])
                 (mirror [1 0 0]))
            (->> (cube 3 26 2)
                 (translate [202 86 -20.5])
                 (rotate (/ π 9) [1 0 0])
                 (mirror [1 0 0]))
            (mirror [1 0 0] (difference
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
              (well-sphere1 78)))

            (difference
              (intersection
                (convert-dactyl-shapes-left dactyl-top-left)
                main-inline)

              (->> (cube 19 5 5) ; cut for "3" key, so keycap won't stick
                   (rotate (/ π 10) [0 1 0])
                   (rotate (/ π 10) [-1 0 0])
                   (rotate (/ π 27) [0 0 1])
                   (translate [131.4 10.4 30])
                   (mirror [1 0 0]))
              (->> (cube 19 19 5) ; cut for "5" key, so keycap won't stick
                   (rotate (/ π 6) [0 1 0])
                   (rotate (/ π 7) [-1 0 0])
                   (rotate (/ π 14) [0 0 1])
                   (translate [96.2 18.5 44.6])
                   (mirror [1 0 0]))
              (->> (cube 5 5 5) ; cut top most corner so it won't hinder middle plate
                   (translate [90 13 58.5])
                   (mirror [1 0 0]))
              (mirror [1 0 0] support-pillar-shift-up-negative)
              (mirror [1 0 0] support-pillar-five-up-negative)
              (mirror [1 0 0] support-pillar-plus-up-negative)
              (mirror [1 0 0] (well-sphere 78))
              (->> (cylinder 2.2, 25)
                   (translate [193 9 (+ 19.4 8.4 -4)])
                   (mirror [1 0 0]))
              (->> (cylinder 6 25)
                   (translate [203 8 10])
                   (mirror [1 0 0]))
              (->> (cylinder 1.7, 27)
                   (translate [203 8 13])
                   (mirror [1 0 0]))
              (mirror [1 0 0] (difference ; Top corner middle glue plate cut
                (->> (cube 40.2 10.2 34.2)
                     (translate [118 7 15])
                     (rotate (/ π 7) [0 -1 0]))
                (->> main-inline
                     (translate [0 2 -2]))
                (well-sphere1 78)))
              (->> (cube 2.2 46.2 10.2) ; Right-most mount plate cut
                   (translate [207 63 7])
                   (mirror [1 0 0]))
              )

            (mirror [1 0 0] (difference
              support-pillar-shift-well
              bottom-negative-inline
              (translate [193 112 (+ 19.4 8.4 -17)] ; Indent for a wooden screw:)
                         (cylinder 4.5, 15))
              (->> (cylinder 2, 25)
                   (translate [193 112 (+ 19.4 8.4 -1)]))
              (->> (cylinder 1.7, 25)
                   (rotate (/ π 20) [1 0 0])
                   (translate [203 112 10]))))
            (mirror [1 0 0] (difference
              support-pillar-plus-well
              bottom-negative-inline
              (translate [193 9 (+ 19.4 8.4 -13)] ; Indent for a wooden screw:)
                         (cylinder 4.5, 15))
              (->> (cube 22 2 17) ; Cut for switch to be able to insert
                   (translate [190 15 16.4]))
              (->> (cylinder 2, 25)
                   (translate [193 9 (+ 19.4 8.4 -4)]))
              (->> (cylinder 6 25)
                   (translate [203 8 10]))
              (->> (cylinder 1.7, 27)
                   (translate [203 8 13]))))
            (mirror [1 0 0] (difference
              (intersection
                support-pillar-five-well
                main-inline)
              (well-sphere 78)
              (->> (cylinder 2, 25)
                   (translate [108 5.5 (+ 19.4 18.4)]))))
            (mirror [1 0 0] (difference
              (intersection
                support-pillar-home-well
                main-inline)
              bottom-negative-inline
              (->> (cylinder 2, 45)
                   (translate [36 125 (+ 19.4 12.4)]))
              (->> (cylinder 3.7, 35)
                   (translate [36 125 17]))
              (->> (cylinder 1.7, 25)
                   (rotate (/ π 20) [1 0 0])
                   (translate [42.7 125 10]))
              (difference ; Cut for tilted connection for 3d print optimization
                (->> (cube 25 16 20)
                     (rotate (/ π 5.45) [0 1 0])
                     (translate [25.5 127 52.5]))
                (->> (cube 40 40 10)
                     (translate [25.5 127 34.5]))))))))

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
    (->> (cylinder 3 4)
         (with-fn 50)
         (rotate (/ π 2) [1 0 0])
         (translate [3, 31, 25]))
    (->> (cylinder 3 4)
         (with-fn 50)
         (rotate (/ π 2) [1 0 0])
         (translate [3, 31, 25])
         (mirror [1 0 0]))))

(spit "things_frame/base_bottom_common.scad"
      (write-scad
        (union
          (difference
            (union
              (intersection
                bottom-main-cylinder
                bottom-main-outline)
              bottom-hand-rest-outline
              bottom-thumbs-spacer)
            (union
              bottom-main-cylinder-inline
              ;bottom-main-cylinder-inline-cut-rgb
              (difference (->> (difference main-inline
                                           (translate [0 0 -13] main-inline))
                               (translate [0 0 -5]))
                          (->> (cube 420 140 80)
                               (translate [0 70 30])))
              bottom-thumbs-spacer-inline

              usb-hole-cut
              half-divide-cube-left
              base-right-up))
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
            (translate [0 0.2 -18] main-inline))
          ; rear
          (intersection
            (->> (cube 50 2 25)
                 (rotate (/ π 7) [0 1 0])
                 (translate [150 3 21]))
            bottom-main-cylinder-inline)
          )))

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
               (translate [42.7 125 7.9]))
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
          (->> (cube 20 200 70)
               (translate [19 100 35]))
          (->> (cylinder [4.5 1.4] 3) ; In rear cylinder
               (with-fn 50)
               (translate [-203 8 21]))
          (->> (cylinder [4.5 1.4] 3) ; Side front
               (with-fn 50)
               (translate [-185 174 6.5]))
          (->> (cylinder [4.5 1.4] 3) ; Middle front
               (with-fn 50)
               (translate [-75 177 15.5]))
          (->> (cylinder [4.5 1.4] 3) ; well shift
               (with-fn 50)
               (translate [-203 112 6]))
          (->> (cylinder [4.5 1.4] 3) ; well home
               (with-fn 50)
               (translate [-45 125 7.9]))
          (->> (cylinder [4.5 1.4] 3) ; Middle hole in glue mount
               (with-fn 50)
               (translate [-5 9 40.3])))))

(spit "things_frame/top_raspberry_pi_pico_mount.scad"
      (write-scad
        (union
          (difference
            (intersection
               (union
                 (difference
                   (->> (cube 48 71 20)
                        (translate [0 67 10]))
                   (->> (cube 32 65 10)
                        (translate [0 64 17]))
                   (->> (cube 45 70 3) ; make .5mm bit deeper, we can adjust height with washers if needed
                        (translate [0 66.5 19]))
                   ; Screw holes
                   (->> (cylinder 1, 20)
                        (with-fn 25)
                        (translate [20, 34, 20]))
                   (->> (cylinder 1, 20)
                        (with-fn 25)
                        (translate [-20, 34, 20]))
                   (->> (cylinder 1, 20)
                        (with-fn 25)
                        (translate [20, (+ 34 65.5), 20]))
                   (->> (cylinder 1, 20)
                        (with-fn 25)
                        (translate [-20, (+ 34 65.5), 20]))

                   (->>
                     (intersection
                       (union
                         (import "base_bottom_common.stl")
                         (mirror [1 0 0] (import "base_bottom_common.stl"))
                         (translate [0 0 1.5] (import "base_bottom_common.stl"))
                         (translate [0 0 1.5] (mirror [1 0 0] (import "base_bottom_common.stl")))
                         )

                       (cube 30 300 300)
                     )
                     (translate [0 0 1])
                     )
                   )
                 )
              (union
                bottom-thumbs-spacer-inline
                bottom-main-cylinder-inline))))))

(spit "things_frame/led_right_mount.scad"
      (write-scad
        (difference
          (intersection
            (union
              (->> (cube 40 100 40)
                   (translate [100 60 20]))
              (->> (cube 50 50 50)
                   (translate [38 128 17])
                   (rotate  (/ π 10.5) [0 0 -1]))
              )
            (union
              bottom-main-cylinder-inline
              (translate [0 0 18] main-inline)
              bottom-thumbs-spacer-inline)
              (->> main-outline
                   (translate [0 0 -20])))
          (well-sphere 92)
        )
        ;(convert-dactyl-shapes (import "../things/dactyl-top-right.stl"))
        ))

(spit "things_frame/led_left_mount.scad"
      (write-scad
        (mirror [1 0 0] (import "led_right_mount.stl"))))

(def usb-female-socket
  (hull
    (->> (cylinder 1.6 6)
         (with-fn 50)
         (rotate (/ π 2) [1 0 0])
         (translate [3, 35, 25.3]))
    (->> (cylinder 1.6 6)
         (with-fn 50)
         (rotate (/ π 2) [1 0 0])
         (translate [3, 35, 25.3])
         (mirror [1 0 0]))))

(def usb-male-socket-cut
  (hull
    (->> (cylinder 1.4 6)
         (with-fn 50)
         (rotate (/ π 2) [1 0 0])
         (translate [3, 30, 25.3]))
    (->> (cylinder 1.4 6)
         (with-fn 50)
         (rotate (/ π 2) [1 0 0])
         (translate [3, 30, 25.3])
         (mirror [1 0 0]))))

(def plastic-pin-cuts
  (union
    (->> (cube 4 2 10)
         (translate [7.62 34.4 18.5]))
    (->> (cube 4 2 10)
         (translate [-7.62 34.4 18.5]))))

(spit "things_frame/usb_plug.scad"
      (write-scad
        (difference
          (->> (cube 28 2.6 10)
               (translate [0 33 25]))
          (union
            (->> (cube 45 70 2) ; PCB
                 (translate [0 66.5 19.3]))
            (->> (cube 23.3 70 1.4) ; pico
                 (translate [0 67.1 23.1]))
            (difference
              (->> (cube 50 2 50)
                   (translate [0 31.5 25]))
              ; back plate
              usb-hole-cut)
            usb-female-socket
            usb-male-socket-cut
            plastic-pin-cuts
            ))
        ))

(def micro-usb-male-socket
  (->> (minkowski
         (->> (polygon [[-3.55 -1.05] [3.55 -1.05] [2.6 1.05] [-2.6 1.05]])
              (extrude-linear {:height 20}))
         ;; The "rounding" tool
         (with-fn 25 (cylinder 0.3 0.1)))
       (rotate (/ π 2) [-1 0 0])
       (translate [0 23 25])))

(def micro-usb-female-socket
  (->> (minkowski
         (->> (polygon [[-3.95 -1.25] [3.95 -1.25] [2.9 1.05] [-2.9 1.05]])
              (extrude-linear {:height 20}))
         ;; The "rounding" tool
         (with-fn 25 (cylinder 0.3 0.1)))
       (rotate (/ π 2) [-1 0 0])
       (translate [0 42.2 25])))

(spit "things_frame/micro_usb_plug.scad"
      (write-scad
        (difference
          (->> (cube 28 2.6 10)
               (translate [0 33 25]))
          (union
            (->> (cube 45 70 2) ; PCB
                 (translate [0 66.5 19.3]))
            (->> (cube 23.3 70 1.4) ; pico
                 (translate [0 67.1 23.1]))
            (difference
              (->> (cube 50 2 50)
                   (translate [0 31.5 25]))
              ; back plate
              usb-hole-cut)
            micro-usb-male-socket
            micro-usb-female-socket
            plastic-pin-cuts
            ))
        ))

(def hotswap-socket-support-mount
  (difference
    (union
      (difference (translate [0 0 0.3] (union bottom-main-cylinder-inline
                                               (->> (cube 416 110 30)
                                                    (rotate (/ π 22) [1 0 0] )
                                                    (translate [0 130 25.4]))))
                  (translate [0 0 0.9] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0] )
                                                   (translate [0 130 25.4]))))
                  (translate [197 60 7.2] (cube 13 8 10)) ; cut for caps
                  (translate [172 62 6.2] (cube 13 5 10)) ; cut for A
                  (translate [151 54 6.2] (cube 11 8 10)) ; cut for S
                  (translate [152 34 6.2] (cube 11 8 10)) ; cut for W
                  (translate [152.7 10 6.2] (cube 14.2 7 35)) ; cut for 2
                  (translate [132.7 7.5 6.2] (cube 12.4 7 35)) ; cut for 3
                  )
      (intersection (union
                      (convert-dactyl-shapes hotswap-sockets-support-under-caps)
                      ; connectors
                      ; shift col
                      (translate [190 25 8] (rotate (/ π 7) [-1 0 0] (cube 2 20 3.2))) ; "-" to tab
                      ;(translate [190 48 1.2] (rotate (/ π 18.5) [-1 0 0] (cube 2 20 5))) ; tab to caps
                      ;(translate [190 71 0.4] (rotate (/ π 90.5) [1 0 0] (cube 2 20 5))) ; caps to shift
                      ; 1 col
                      (translate [165 23 7] (rotate (/ π 6.5) [-1 0 0] (cube 2 20 6))) ; 1 to q
                      ;(translate [165 46 0] (rotate (/ π 18.5) [-1 0 0] (cube 2 20 10))) ; q to a
                      ;(translate [165 71 -0.6] (rotate (/ π 90.5) [1 0 0] (cube 2 20 10))) ; a to z
                      (translate [165 96 2.4] (rotate (/ π 10.5) [1 0 0] (cube 2 20 10))) ; z to `
                      ; 2 col
                      (translate [145 18 7] (rotate (/ π 6.5) [-1 0 0] (cube 2 20 10))) ; 2 to w
                      ;(translate [143 40 0] (rotate (/ π 22.5) [-1 0 0] (cube 2 20 10))) ; w to s
                      ;(translate [142 65 -1.6] (rotate (/ π 90.5) [1 0 0] (cube 2 20 10))) ; s to x
                      (translate [143 90 2] (rotate (/ π 10.5) [1 0 0] (cube 2 20 10))) ; x to ;
                      ; 3 col
                      (translate [124 14 9.5] (rotate (/ π 6.5) [-1 0 0] (cube 2 20 10))) ; 3 to e
                      (translate [121 37 1] (rotate (/ π 15.5) [-1 0 0] (cube 2 20 10))) ; e to d
                      (translate [120 63 0] (rotate (/ π 90.5) [1 0 0] (cube 2 20 10))) ; d to c
                      (translate [120 88 2] (rotate (/ π 10.5) [1 1 0] (cube 2 23 10))) ; x to ;
                      ; 4 col
                      (translate [104 14 14.5] (rotate (/ π 6.5) [-1 0 0] (cube 2 25 12))) ; 4 to r
                      (translate [102 40 7] (rotate (/ π 15.5) [-1 0 0] (cube 2 25 12))) ; r to f
                      (translate [102 67 5] (rotate (/ π 90.5) [1 0 0] (cube 2 26 12))) ; f to v
                      (translate [102 93 8] (rotate (/ π 8.5) [1 1 0] (cube 2 27 12))) ; v to left
                      ; 5 col
                      (translate [84 13 18.5] (rotate (/ π 6) [-1 0.6 0] (cube 2 29 21))) ; 5 to t
                      (translate [82 40 10] (rotate (/ π 13.5) [-1 1.4 0] (cube 2 25 21))) ; t to g
                      (translate [80 67 8] (rotate (/ π 70.5) [1 12 0] (cube 2 26 21))) ; g to b
                      ; few rows for structure
                      (translate [88 53 10] (rotate (/ π 10.5) [1 12 0] (cube 20 2 15))) ; g to f
                      (translate [110 51.5 0] (rotate (/ π 10.5) [1 12 0] (cube 20 2 15))) ; f to d
                      (translate [88 79 10] (rotate (/ π 10.5) [1 12 0] (cube 20 2 15))) ; b to v
                      (translate [111 100 10] (rotate (/ π 10.5) [1 12 0] (cube 20 2 15))) ; right to left
                      (translate [132 100 4] (rotate (/ π 10.5) [1 12 0] (cube 20 2 15))) ; left to ;
                      (translate [158 103 4] (rotate (/ π 20.5) [1 1 31] (cube 20 2 15))) ; ; to ~
                      )
                    (translate [0 0 0.3]
                               (union bottom-main-cylinder-inline
                                      (->> (cube 416 110 30)
                                           (rotate (/ π 22) [1 0 0] )
                                           (translate [0 130 25.4])))))
      (translate [95 2.7 29.6] (cube 35 1 14)))
    (translate [-70 100 100] (cube 280 200 200))
    (translate [150 195 100] (cube 250 160 200))
    (translate [100 100 -99] (cube 700 300 200))
    (translate [0 -147.8 0] (cube 500 300 200))
    (translate [198 110 0] (cube 40 40 200))
    (translate [72 110 0] (cube 40 40 200))
    (translate [210 65 0] (cube 20 68 200)) ; Cut for left notch
    (translate [156 8 6.2] (cube 10 10 200)) ; Cut for 2
    (translate [133.5 6 6.2] (cube 5 6.5 35)) ; Cut for 3
    (->> (cube 25.5 14 40)
         (translate [195.25 7.8 16.9]))
    (->> (cube 66 2 35)
         (translate [150 3 21]))))

(def hotswap-socket-support-mount-thumbs
  (union

    (difference (translate [0 0 0.3] (union bottom-main-cylinder-inline
                                             (->> (cube 416 110 30)
                                                  (rotate (/ π 22) [1 0 0] )
                                                  (translate [0 130 25.4]))))
                (translate [0 0 0.9] (union bottom-main-cylinder-inline
                                            (->> (cube 416 110 30)
                                                 (rotate (/ π 22) [1 0 0] )
                                                 (translate [0 130 25.4]))))
                (translate [50 0 0] (cube 600 150 300))
                (translate [50 215 0] (cube 600 150 300))
                (translate [165 100 0] (cube 120 200 300))
                (translate [-125 100 0] (cube 320 200 300))
                (translate [50 140 50] (cube 30 30 30)) ; cut artefact garbage
                (translate [0 0 -14] (cube 500 500 30)) ; cut artefact garbage
                (->> (cube 60 18 50)
                     (rotate (/ π 2.45) [0 0 1])
                     (translate [37.21 124.35 28]))
                (->> (cube 24 50 50)
                     (rotate (/ π 2.45) [0 0 1])
                     (translate [87 134 28]))
                )
    (intersection
      (union (convert-dactyl-shapes hotswap-sockets-support-under-thumbcaps)
             ; connectors
             (translate [48 115 8] (rotate (/ π 10.5) [-1 0 -1] (cube 2 40 35))) ; right most
             (translate [66 105 8] (rotate (/ π 10.5) [-1 0 -1] (cube 2 28 35))) ; middle two
             (translate [68 115 8] (rotate (/ π 10.5) [-1 0 -1] (cube 40 2 35))) ; space - cmd - outer
             (translate [57 87 10] (rotate (/ π 10.5) [-1 0 -1] (cube 30 2 40)))
             (translate [94 105 2] (rotate (/ π 10.5) [-1 0 -1] (cube 20 2 30)))
             (translate [85 97 8] (rotate (/ π 10) [0.5 1 0] (cube 2 34 30))) ; g to b
             )
      (translate [0 0 0.3] (union bottom-main-cylinder-inline
                                          (->> (cube 416 110 30)
                                               (rotate (/ π 22) [1 0 0] )
                                               (translate [0 130 25.4]))))
      )
  ))

(spit "things_frame/hotswap-socket-support-mount-thumbs.scad"
      (write-scad
        hotswap-socket-support-mount-thumbs
        ))

(spit "things_frame/hotswap-socket-support-mount.scad"
      (write-scad hotswap-socket-support-mount
                  hotswap-socket-support-mount-thumbs))


(spit "things_frame/all_combined.scad"
      (write-scad
        (union
          (import "case_right_bottom.stl")
          (import "hotswap-socket-support-mount.stl")
          (import "well_right.stl")
          )))
