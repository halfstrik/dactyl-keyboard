(ns dactyl-keyboard.hotswap
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.dactyl :refer [hotswap-sockets-support-under-caps hotswap-sockets-support-under-thumbcaps hotswap-sockets-support-under-caps-left hotswap-sockets-support-under-thumbcaps-left]]
            [dactyl-keyboard.frame :refer [convert-dactyl-shapes bottom-main-cylinder-inline]]))


(def hotswap-socket-support-mount
  (difference
    (union
      (difference (translate [0 0 0.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0] )
                                                   (translate [0 130 25.4]))))
                  (translate [0 0 1.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0] )
                                                   (translate [0 130 25.4]))))
                  ;(translate [193.3 60 7.2] (cube 15 8 10)) ; cut for caps
                  ;(translate [172 61.3 6.2] (cube 13 5 10)) ; cut for A
                  (translate [150 54 6.2] (cube 13 8 10)) ; cut for S
                  (translate [151 31.5 6.2] (cube 13 7 10)) ; cut for W
                  (translate [150 60 3.1] (cube 300 37 2)) ; IMPORTANT: cut from the up to the flat area
                  ;(translate [152.7 10 6.2] (cube 14.2 7 35)) ; cut for 2
                  ;(translate [132 7.5 6.2] (cube 14.4 7 35)) ; cut for 3
                  )
      (intersection (union
                      (convert-dactyl-shapes hotswap-sockets-support-under-caps)
                      ; connectors
                      ; shift col
                      (translate [188.6 25 8] (rotate (/ π 7) [-1 0 0] (cube 4 22 3.2))) ; "-" to tab
                      (translate [197 25 8] (rotate (/ π 7) [-1 0 0] (cube 4 20 3.2))) ; "-" to tab
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
                    (translate [0 0 0.2]
                               (union bottom-main-cylinder-inline
                                      (->> (cube 416 110 30)
                                           (rotate (/ π 22) [1 0 0] )
                                           (translate [0 130 25.4])))))
      (translate [95 2.7 29.6] (cube 35 1 14)))
    (translate [-70 100 100] (cube 280 200 200))
    (translate [150 193 100] (cube 250 160 200))
    (translate [100 100 -100] (cube 700 300 200)) ; cut for bottom
    (translate [0 -147.8 0] (cube 500 300 200)) ; cut for rear junk
    (translate [150 -145 0] (cube 67 300.3 200)) ; cut for rear notch
    (translate [198 110 0] (cube 40 40 200)) ; cut for shift pillar
    ;(translate [72 110 0] (cube 40 40 200))
    ;(translate [209 65 0] (cube 25 108 200)) ; Cut for left notch
    (translate [155.5 0 6.2] (rotate (/ π 3.5) [0 0 1] (cube 18 20 200))) ; Cut for 2
    (translate [137.5 -1.5 6.2] (rotate (/ π 3.5) [0 0 1] (cube 18 20 200))) ; Cut for 3
    (->> (cube 26.5 14.5 40)
         (translate [195.25 7.9 16.9])) ; cut for top left screw
    ;(->> (cube 68 7 35) ; cut for top left numbers
    ;     (translate [150 3 21]))
    ;(->> (cube 20 13 35) ; cut for number 1
    ;     (translate [148 3 21]))
    ))

(def hotswap-socket-support-mount-thumbs
  (difference
    (union
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
        (union (convert-dactyl-shapes hotswap-sockets-support-under-thumbcaps)
               ; connectors
               (translate [48 115 8] (rotate (/ π 10.5) [-1 0 -1] (cube 2 40 35))) ; right most
               (translate [66 105 8] (rotate (/ π 10.5) [-1 0 -1] (cube 2 28 35))) ; middle two
               (translate [68 115 8] (rotate (/ π 10.5) [-1 0 -1] (cube 40 2 35))) ; space - cmd - outer
               (translate [57 87 10] (rotate (/ π 10.5) [-1 0 -1] (cube 30 2 40)))
               (translate [94 105 2] (rotate (/ π 10.5) [-1 0 -1] (cube 20 2 30)))
               (translate [85 97 8] (rotate (/ π 10) [0.5 1 0] (cube 2 34 30))) ; g to b
               )
        (translate [0 0 0.4] (union bottom-main-cylinder-inline
                                    (->> (cube 416 110 30)
                                         (rotate (/ π 22) [1 0 0])
                                         (translate [0 130 25.4]))))
        ))
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
      (difference (translate [0 0 0.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0] )
                                                   (translate [0 130 25.4]))))
                  (translate [0 0 1.2] (union bottom-main-cylinder-inline
                                              (->> (cube 416 110 30)
                                                   (rotate (/ π 22) [1 0 0] )
                                                   (translate [0 130 25.4]))))
                  (translate [193.3 60 7.2] (cube 15 8 10)) ; cut for "
                  (translate [172 60 6.2] (cube 14 8 10)) ; cut for ;
                  (translate [149.5 54 6.2] (cube 14 8 10)) ; cut for L
                  (translate [150 31.3 6.2] (cube 14 7 10)) ; cut for I
                  (translate [152.7 10 6.2] (cube 14.2 7 35)) ; cut for 9
                  (translate [132 7.5 6.2] (cube 14.4 7 35)) ; cut for 8
                  (translate [194 81 6.2] (cube 14.4 4 35)) ; cut for shift
                  (translate [149 76 6.2] (cube 14.4 5 35)) ; cut for .
                  (translate [127 73 6.2] (cube 14.4 3 35)) ; cut for ,
                  (translate [127 49 6.2] (cube 14.4 4 35)) ; cut for k
                  (translate [127 26 6.2] (cube 14.4 4 35)) ; cut for i
                  )
      (intersection (union
                      (convert-dactyl-shapes hotswap-sockets-support-under-caps-left)
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
                    (translate [0 0 0.4]
                               (union bottom-main-cylinder-inline
                                      (->> (cube 416 110 30)
                                           (rotate (/ π 22) [1 0 0] )
                                           (translate [0 130 25.4])))))
      (translate [95 2.7 29.6] (cube 35 1 14)))
    (translate [-70 100 100] (cube 280 200 200))
    (translate [150 193 100] (cube 250 160 200))
    (translate [100 100 -97.1] (cube 700 300 200)) ; cut for bottom
    (translate [0 -147.8 0] (cube 500 300 200)) ; cut for rear junk
    (translate [198 110 0] (cube 40 40 200)) ; cut for shift pillar
    ;(translate [72 110 0] (cube 40 40 200))
    (translate [208 65 0] (cube 20 108 200)) ; Cut for left notch
    (translate [153 8 6.2] (cube 14 10 200)) ; Cut for 2
    (translate [131 8 6.2] (cube 10 7 35)) ; Cut for 3
    (->> (cube 25.5 15.8 40)
         (rotate (/ π 10) [-1 0 0])
         (translate [195.25 7.9 16.9])) ; cut for top left screw
    (->> (cube 68 8.5 35) ; cut for top left numbers
         (translate [150 3 21]))
    (->> (cube 20 14.2 35) ; cut for number 1
         (translate [148 3 21]))
    (translate [194 61 6.2] (cube 8 4 35)) ; cut for '
    ))

(def hotswap-socket-support-mount-thumbs-left
  (difference
    (union
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
        (union (convert-dactyl-shapes hotswap-sockets-support-under-thumbcaps-left)
               ; connectors
               (translate [48 115 8] (rotate (/ π 10.5) [-1 0 -1] (cube 2 40 35))) ; right most
               (translate [66 105 8] (rotate (/ π 10.5) [-1 0 -1] (cube 2 28 35))) ; middle two
               (translate [68 115 8] (rotate (/ π 10.5) [-1 0 -1] (cube 40 2 35))) ; space - cmd - outer
               (translate [57 87 10] (rotate (/ π 10.5) [-1 0 -1] (cube 30 2 40)))
               (translate [94 105 2] (rotate (/ π 10.5) [-1 0 -1] (cube 20 2 30)))
               (translate [85 97 8] (rotate (/ π 10) [0.5 1 0] (cube 2 34 30))) ; g to b
               )
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
