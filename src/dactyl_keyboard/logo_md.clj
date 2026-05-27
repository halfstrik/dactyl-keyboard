(ns dactyl-keyboard.logo_md
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [unicode-math.core :refer :all]
            [dactyl-keyboard.frame :refer [well-sphere]]))

(defn calvert-quarter [w h color]
  (let [sw (/ w 6)
        bars (for [i (range 6)]
               (translate [(+ (* i sw) (/ sw 2)) (/ h 2) 0]
                          (cube sw h 2)))
        gold-bars (apply union (map (partial nth bars) [0 2 4]))
        black-bars (apply union (map (partial nth bars) [1 3 5]))
        angle (- (Math/atan2 h w))
        bend-len (Math/sqrt (+ (* w w) (* h h)))
        bend (->> (cube bend-len sw 2)
                  (rotate angle [0 0 1])
                  (translate [(/ w 2) (/ h 2) 0]))]
    (cond
      (= color :yellow)
      (union (difference gold-bars bend) (intersection black-bars bend))
      (= color :black)
      (union (difference black-bars bend) (intersection gold-bars bend))
      :else ())))

(defn crossland-quarter [w h color]
  (let [f1 (translate [(/ w 4) (* h 0.75) 0] (cube (/ w 2) (/ h 2) 2))
        f2 (translate [(* w 0.75) (* h 0.75) 0] (cube (/ w 2) (/ h 2) 2))
        f3 (translate [(/ w 4) (/ h 4) 0] (cube (/ w 2) (/ h 2) 2))
        f4 (translate [(* w 0.75) (/ h 4) 0] (cube (/ w 2) (/ h 2) 2))
        white-field (union f1 f4)
        red-field (union f2 f3)
        cw (/ w 8)
        ch (/ h 4)
        v-bar (translate [(/ w 2) (/ h 2) 0] (cube cw (* h 0.8) 2))
        h-bar (translate [(/ w 2) (/ h 2) 0] (cube (* w 0.8) ch 2))
        lobe-r (* cw 0.6)
        lobe (cylinder lobe-r 2)
        lobes (fn [x y rot]
                (->> (union (translate [0 lobe-r 0] lobe)
                            (translate [(- lobe-r) 0 0] lobe)
                            (translate [lobe-r 0 0] lobe))
                     (rotate rot [0 0 1])
                     (translate [x y 0])))
        all-lobes (union (lobes (/ w 2) (* h 0.9) 0)
                         (lobes (/ w 2) (* h 0.1) π)
                         (lobes (* w 0.1) (/ h 2) (/ π 2))
                         (lobes (* w 0.9) (/ h 2) (- (/ π 2))))
        cross (union v-bar h-bar all-lobes)]
    (cond
      (= color :white)
      (union (difference white-field cross) (intersection red-field cross))
      (= color :red)
      (union (difference red-field cross) (intersection white-field cross))
      :else ())))

(defn maryland-flag [w h color]
  (let [qw (/ w 2) qh (/ h 2)]
    (union
      (translate [0 qh 0] (calvert-quarter qw qh color))
      (translate [qw qh 0] (crossland-quarter qw qh color))
      (translate [0 0 0] (crossland-quarter qw qh color))
      (translate [qw 0 0] (calvert-quarter qw qh color)))))

(def plate-shape-1layer
  (translate [24.4 9.9 0]
             (hull
               (translate [0 0 0] (cube 49.8 19.8 0.1))
               (translate [0 0 0.2] (cube 49.5 19.5 0.1)))))

(def plate-shape-23layer
  (translate [24.4 9.9 0]
  (hull
    (translate [0 0 0.2] (cube 49.5 19.5 0.1))
    (translate [0 0 0.6] (cube 48.9 18.9 0.1)))))

(defn maryland-color-part [color]
  (difference
  (intersection
    plate-shape-23layer
    (maryland-flag 49.8 19.8 color))
  (translate [-28.5 -46.5 -52] (well-sphere 76))))

(spit "things_logo/logo_maryland_yellow.scad" (write-scad (maryland-color-part :yellow)))
(spit "things_logo/logo_maryland_black.scad" (write-scad (maryland-color-part :black)))
(spit "things_logo/logo_maryland_white.scad" (write-scad (union (maryland-color-part :white) plate-shape-1layer)))
(spit "things_logo/logo_maryland_red.scad" (write-scad (maryland-color-part :red)))
