(ns dactyl-keyboard.glue_support
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.frame :refer [bottom-main-cylinder main-outline bottom-hand-rest-outline bottom-thumbs-spacer]]))

(spit "things_support/support_bottom_middle.scad"
      (write-scad
        (difference
          (union
            (->> (cube 184 192 26)
                 (translate [0 95 8]))
            (->> (cube 184 100 46)
                 (translate [0 44 18])))
          (difference
            (union
              (intersection
                bottom-main-cylinder
                (difference
                  (->> main-outline
                       (translate [0 0 -11]))
                  (translate [0 0 -10] (cube 500 500 20)))) ; cut below 0z)
              bottom-hand-rest-outline
              bottom-thumbs-spacer)))))

(spit "things_support/support_bottom_side.scad"
      (write-scad
        (difference
          (union
            (->> (cube 100 146 20)
                 (translate [170 90 5]))
            (->> (cube 10 186 40)
                 (translate [215 95 20])))
          (difference
            (union
              (intersection
                bottom-main-cylinder
                (difference
                  (->> main-outline
                       (translate [0 0 -11]))
                  (translate [0 0 -10] (cube 500 500 20)))) ; cut below 0z)
              bottom-hand-rest-outline
              bottom-thumbs-spacer)))))

(spit "things_support/support_bottom_side_left.scad"
      (write-scad
        (mirror [1 0 0]
                (import "../things_support/support_bottom_side.stl"))))

(spit "things_support/all_combined.scad"
      (write-scad
        (union
          (import "../things_frame/base_bottom_common.stl")
          (import "../things_support/support_bottom_middle.stl")
          (import "../things_support/support_bottom_side.stl"))))
