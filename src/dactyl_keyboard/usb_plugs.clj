(ns dactyl-keyboard.usb_plugs
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.frame :refer [usb-hole-cut]]))

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

(spit "things_frame/usb_plug.scad"
      (write-scad
        ;(->> (cube 4 3.4 7)
        ;     (translate [8 35 22]))
        (difference
          (->> (cube 28 2.6 10)
               (translate [0 33 25]))
          (union
            (->> (cube 45 70 2) ; PCB
                 (translate [0 66.5 19.3]))
            (->> (cube 23.3 70 1.4) ; pico
                 (translate [0 67.1 23.1]))
            (->> (cube 4 3.4 7) ; cut to fit
                 (translate [8 35 22]))
            (->> (cube 4 3.4 7) ; cut to fit
                 (translate [-8 35 22]))
            (difference
              (->> (cube 50 2 50)
                   (translate [0 31.5 25]))
              ; back plate
              usb-hole-cut)
            usb-female-socket
            usb-male-socket-cut
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
        ;micro-usb-female-socket
        (difference
          (->> (cube 28 2.6 10)
               (translate [0 33 25]))
          (union
            (->> (cube 45 70 2) ; PCB
                 (translate [0 66.5 19.3]))
            (->> (cube 23.3 70 1.4) ; pico
                 (translate [0 67.1 23.1]))
            (->> (cube 4 3.4 7) ; cut to fit
                 (translate [8 35 22]))
            (->> (cube 4 3.4 7) ; cut to fit
                 (translate [-8 35 22]))
            (difference
              (->> (cube 50 2 50)
                   (translate [0 31.5 25]))
              ; back plate
              usb-hole-cut)
            micro-usb-male-socket
            micro-usb-female-socket
            ))
        ))
