(ns dactyl-keyboard.logo
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.frame :refer [well-sphere main-box-minus-well-sphere-top keys-well]]))

(def letters
  (union
    (->> (text "JetBrains Mono" 11 "Code")
         (mirror [-1 0 0])
         (extrude-linear {:height 3 :twist 0 :convexity 0})
         (rotate (/ π 25) [-1 0.3 0])
         (translate [71 52 59.6]))
    (->> (text "JetBrains Mono" 7 "reimagined")
         (mirror [-1 0 0])
         (extrude-linear {:height 3 :twist 0 :convexity 0})
         (rotate (/ π 25) [-1 0.3 0])
         (translate [71 59 58.7]))))

(spit "things_logo/logo_plate.scad"
      (write-scad
        (difference
            (translate [53 52 58.3] (rotate (/ π 25) [-1 0.3 0]
                                            (hull
                                              (translate [0 0 0]
                                                         (cube 49.8 19.8 0.1))
                                              (translate [0 0 0.6]
                                                         (cube 48.9 18.9 0.1)))))
            (well-sphere 76)
            letters)
        ))

(spit "things_logo/logo_letters.scad"
      (write-scad
        (intersection
          (difference
          ; cut for logo plate insert
          (translate [53 52 58.3] (rotate (/ π 25) [-1 0.3 0]
                                          (hull
                                            (translate [0 0 0]
                                                       (cube 49.8 19.8 0.1))
                                            (translate [0 0 0.6]
                                                       (cube 48.9 18.9 0.1)))))
          (well-sphere 76))
          letters)))

(spit "things_logo/logo_back-serial.scad"
      (write-scad
        (union
          (->>
            (difference
              (hull
                (translate [0 0 0] (cube 132.9 8.9 0.1))
                (translate [0 0 0.6] (cube 133.8 9.8 0.1)))
              (translate [66.5 0 0] (cube 1 10 5))

              (->> (text "JetBrains Mono" 7 "CodeReimagined•Frederick, MD USA")
                   (extrude-linear {:height 3 :twist 0 :convexity 0})
                   (translate [-64 2.5 -1.2])))
            (translate [0 0 0]))

          (->>
            (difference
              (hull
                (translate [0 0 0] (cube 132.9 8.9 0.1))
                (translate [0 0 0.6] (cube 133.8 9.8 0.1)))
              (translate [66.5 0 0] (cube 1 10 5))

              (->> (text "JetBrains Mono" 7 "SN: CR26-0001")
                   (extrude-linear {:height 3 :twist 0 :convexity 0})
                   (translate [-25 2.5 -1.2])))
            (translate [0 10 0]))

          (->>
            (difference
              (hull
                (translate [0 0 0] (cube 132.9 8.9 0.1))
                (translate [0 0 0.6] (cube 133.8 9.8 0.1)))
              (translate [66.5 0 0] (cube 1 10 5))

              (->> (text "JetBrains Mono" 7 "setup.codereimagined.com")
                   (extrude-linear {:height 3 :twist 0 :convexity 0})
                   (translate [-45 2.5 -1.2])))
            (translate [0 20 0]))
          )))

(spit "things_logo/logo_back-serial-letters.scad"
      (write-scad
        (union
          (->>
            (intersection
              (difference
                (hull
                  (translate [0 0 0] (cube 132.9 8.9 0.1))
                  (translate [0 0 0.6] (cube 133.8 9.8 0.1)))
                (translate [66.5 0 0] (cube 1 10 5)))

              (->> (text "JetBrains Mono" 7 "CodeReimagined•Frederick, MD USA")
                   (extrude-linear {:height 3 :twist 0 :convexity 0})
                   (translate [-64 2.5 -1.2])))
            (translate [0 0 0]))

          (->>
            (intersection
              (difference
                (hull
                  (translate [0 0 0] (cube 132.9 8.9 0.1))
                  (translate [0 0 0.6] (cube 133.8 9.8 0.1)))
                (translate [66.5 0 0] (cube 1 10 5)))

              (->> (text "JetBrains Mono" 7 "SN: CR26-0001")
                   (extrude-linear {:height 3 :twist 0 :convexity 0})
                   (translate [-25 2.5 -1.2])))
            (translate [0 10 0]))

          (->>
            (intersection
              (difference
                (hull
                  (translate [0 0 0] (cube 132.9 8.9 0.1))
                  (translate [0 0 0.6] (cube 133.8 9.8 0.1)))
                (translate [66.5 0 0] (cube 1 10 5)))

              (->> (text "JetBrains Mono" 7 "setup.codereimagined.com")
                   (extrude-linear {:height 3 :twist 0 :convexity 0})
                   (translate [-45 2.5 -1.2])))
            (translate [0 20 0]))
          )))