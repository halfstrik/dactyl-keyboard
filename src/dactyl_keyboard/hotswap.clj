(ns dactyl-keyboard.hotswap
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.dactyl :refer [hotswap-sockets-support-under-caps hotswap-sockets-support-under-thumbcaps hotswap-sockets-support-under-caps-left hotswap-sockets-support-under-thumbcaps-left]]
            [dactyl-keyboard.frame :refer [convert-dactyl-shapes bottom-main-cylinder-inline main-inline bottom-thumbs-spacer-inline main-outline well-sphere]]))


(def hotswap-socket-support-mount
  (difference
    (union
      (difference
        (intersection
          (union
            (->> (cube 40 100 40)
                 (translate [100 60 20]))
            (->> (cube 50 50 50)
                 (translate [38 128 17])
                 (rotate  (/ π 10.5) [0 0 -1])))
          (translate [0 0 0.2]
                     (union
                        bottom-main-cylinder-inline
                        (translate [0 0 18] main-inline)
                        bottom-thumbs-spacer-inline))
          (->> main-outline
               (translate [0 0 -22])))
        (well-sphere 92))
      (difference (translate [0 0 0.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0] )
                                                   (translate [0 130 25.4]))))
                  (translate [0 0 1.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0] )
                                                   (translate [0 130 25.4]))))
                  (translate [150 54 6.2] (cube 13 7 10)) ; cut for S
                  (translate [151 31.5 6.2] (cube 13 6 10)) ; cut for W
                  (translate [150 60 3.1] (cube 300 37 2)) ; IMPORTANT: cut from the up to the flat area
                  )
      (intersection (union
                      (convert-dactyl-shapes hotswap-sockets-support-under-caps)
                      ; connectors
                      ; shift col
                      (translate [188.6 25 8] (rotate (/ π 7) [-1 0 0] (cube 4 22 3.2))) ; "-" to tab
                      (translate [197 25 8] (rotate (/ π 7) [-1 0 0] (cube 4 20 3.2))) ; "-" to tab
                      )
                    (translate [0 0 0.2]
                               (union bottom-main-cylinder-inline
                                      (->> (cube 416 110 30)
                                           (rotate (/ π 22) [1 0 0] )
                                           (translate [0 130 25.4])))))
      (translate [95 2.7 29.6] (cube 35 1 14)))
    (translate [-70 100 100] (cube 280 200 200))
    (translate [150 193 100] (cube 250 160 200))
    (translate [213 65 0] (cube 25 108 200)) ; Cut for left notch
    (translate [100 100 -100] (cube 700 300 200)) ; cut for bottom
    (translate [0 -147.8 0] (cube 500 300 200)) ; cut for rear junk
    (translate [150 -145 0] (cube 67 300.3 200)) ; cut for rear notch
    (translate [198 110 0] (cube 40 40 200)) ; cut for shift pillar
    (translate [155.5 0 6.2] (rotate (/ π 3.5) [0 0 1] (cube 18 20 200))) ; Cut for 2
    (translate [137.5 -1.5 6.2] (rotate (/ π 3.5) [0 0 1] (cube 18 20 200))) ; Cut for 3
    (->> (cube 26.5 14.5 40)
         (translate [195.25 7.9 16.9])) ; cut for top left screw
    (translate [205 20 20.5] (cube 13 15 13)) ; cut for left mount peice
    ))

(def hotswap-socket-support-mount-thumbs
  (difference
    (union
      (difference
        (intersection
          (union
            (->> (cube 40 100 40)
                 (translate [100 60 20]))
            (->> (cube 50 50 50)
                 (translate [38 128 17])
                 (rotate  (/ π 10.5) [0 0 -1])))
          (translate [0 0 0.2]
                     (union
                       bottom-main-cylinder-inline
                       (translate [0 0 18] main-inline)
                       bottom-thumbs-spacer-inline))
          (->> main-outline
               (translate [0 0 -22])))
        (well-sphere 92))
      (difference (translate [0 0 0.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0] )
                                                   (translate [0 130 25.4]))))
                  (translate [0 0 1.2] (union bottom-main-cylinder-inline
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
        (convert-dactyl-shapes hotswap-sockets-support-under-thumbcaps)
        (translate [0 0 0.4] (union bottom-main-cylinder-inline
                                    (->> (cube 416 110 30)
                                         (rotate (/ π 22) [1 0 0])
                                         (translate [0 130 25.4]))))))
    (translate [100 100 -97.1] (cube 700 300 200)) ; cut for bottom
    ))

(spit "things_hotswap/hotswap-socket-support-mount-thumbs.scad"
      (write-scad
        hotswap-socket-support-mount-thumbs
        ))

(spit "things_hotswap/hotswap-socket-support-mount.scad"
      (write-scad hotswap-socket-support-mount
                  hotswap-socket-support-mount-thumbs))

(def hotswap-socket-support-mount-left
  (difference
    (union
      (difference
        (intersection
          (union
            (->> (cube 40 100 40)
                 (translate [100 60 20]))
            (->> (cube 50 50 50)
                 (translate [38 128 17])
                 (rotate  (/ π 10.5) [0 0 -1])))
          (translate [0 0 0.2]
                     (union
                       bottom-main-cylinder-inline
                       (translate [0 0 18] main-inline)
                       bottom-thumbs-spacer-inline))
          (->> main-outline
               (translate [0 0 -22])))
        (well-sphere 92))
      (difference (translate [0 0 0.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0] )
                                                   (translate [0 130 25.4]))))
                  (translate [0 0 1.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0] )
                                                   (translate [0 130 25.4]))))
                  (translate [149.5 54 6.2] (cube 14 8 10)) ; cut for L
                  (translate [150 60 3.1] (cube 300 37 2)) ; IMPORTANT: cut from the up to the flat area
                  )
      (intersection (union
                      (convert-dactyl-shapes hotswap-sockets-support-under-caps-left)
                      ; connectors
                      ; shift col
                      (translate [188.1 25 8] (rotate (/ π 7) [-1 0 0] (cube 4 20 3.2))) ; "-" to tab
                      (translate [196 25 8] (rotate (/ π 7) [-1 0 0] (cube 4 20 3.2))) ; "-" to tab
                      )
                    (translate [0 0 0.4]
                               (union bottom-main-cylinder-inline
                                      (->> (cube 416 110 30)
                                           (rotate (/ π 22) [1 0 0] )
                                           (translate [0 130 25.4])))))
      (translate [95 2.7 29.6] (cube 35 1 14)))
    (translate [-70 100 100] (cube 280 200 200))
    (translate [150 193 100] (cube 250 160 200))
    (translate [100 100 -100] (cube 700 300 200)) ; cut for bottom
    (translate [0 -147.8 0] (cube 500 300 200)) ; cut for rear junk
    (translate [198 110 0] (cube 40 40 200)) ; cut for shift pillar
    (translate [213 65 0] (cube 25 108 200)) ; Cut for left notch
    (->> (cube 26.5 14.5 40)
         (translate [195.25 7.9 16.9])) ; cut for top left screw
    (translate [155.5 -2 6.2] (rotate (/ π 3.5) [0 0 1] (cube 18 20 200))) ; Cut for 2
    (translate [136.5 -3.5 6.2] (rotate (/ π 3.5) [0 0 1] (cube 18 20 200))) ; Cut for 3
    (translate [185 20 21.9] (cube 3 15 13)) ; cut for left mount peice
    ))

(def hotswap-socket-support-mount-thumbs-left
  (difference
    (union
      (difference
        (intersection
          (union
            (->> (cube 40 100 40)
                 (translate [100 60 20]))
            (->> (cube 50 50 50)
                 (translate [38 128 17])
                 (rotate  (/ π 10.5) [0 0 -1])))
          (translate [0 0 0.2]
                     (union
                       bottom-main-cylinder-inline
                       (translate [0 0 18] main-inline)
                       bottom-thumbs-spacer-inline))
          (->> main-outline
               (translate [0 0 -22])))
        (well-sphere 92))
      (difference (translate [0 0 0.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0])
                                                   (translate [0 130 25.4]))))
                  (translate [0 0 1.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0])
                                                   (translate [0 130 25.4]))))
                  (translate [50 0 0] (cube 600 150 300))
                  (translate [50 215 0] (cube 600 150 300))
                  (translate [165 100 0] (cube 120 200 300))
                  (translate [-125 100 0] (cube 320 200 300))
                  (translate [50 140 50] (cube 30 30 30)) ; cut artefact garbage

                  (->> (cube 60 18 50)
                       (rotate (/ π 2.45) [0 0 1])
                       (translate [37.21 124.35 28]))
                  (->> (cube 24 50 50)
                       (rotate (/ π 2.45) [0 0 1])
                       (translate [87 134 28]))
                  )
      (intersection
        (convert-dactyl-shapes hotswap-sockets-support-under-thumbcaps-left)
        (translate [0 0 0.4] (union bottom-main-cylinder-inline
                                    (->> (cube 416 110 30)
                                         (rotate (/ π 22) [1 0 0])
                                         (translate [0 130 25.4]))))
        ))
    (translate [100 100 -97.1] (cube 700 300 200)) ; cut for bottom
    ))

(spit "things_hotswap/hotswap-socket-support-mount-left.scad"
      (write-scad
        (mirror [1 0 0] (union hotswap-socket-support-mount-left
                               hotswap-socket-support-mount-thumbs-left))
        ))
