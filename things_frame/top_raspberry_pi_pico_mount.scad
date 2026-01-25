union () {
  difference () {
    intersection () {
      translate ([0, 0, 4.5]) {
        union () {
          difference () {
            translate ([0, 78, 50]) {
              cube ([48, 91, 50], center=true);
            }
            translate ([0, 60, 27]) {
              cube ([32, 55, 10], center=true);
            }
            translate ([0, 67.5, 90]) {
              cube ([45, 70, 2], center=true);
            }
            translate ([0, 67.5, 26]) {
              cube ([45, 70, 2], center=true);
            }
            translate ([20, 35, 28]) {
              cylinder ($fn=25, h=20, r=1.3, center=true);
            }
            translate ([-20, 35, 28]) {
              cylinder ($fn=25, h=20, r=1.3, center=true);
            }
            translate ([20, 100.5, 28]) {
              cylinder ($fn=25, h=20, r=1.3, center=true);
            }
            translate ([-20, 100.5, 28]) {
              cylinder ($fn=25, h=20, r=1.3, center=true);
            }
          }
        }
      }
      intersection () {
        translate ([0, -110, -1332]) {
          sphere ($fn=300, r=1400);
        }
        translate ([0, 93, 33]) {
          cube ([416, 182, 70], center=true);
        }
        translate ([0, 310, -787]) {
          sphere ($fn=300, r=900);
        }
      }
    }
    difference () {
      translate ([25.5, 127, 52.5]) {
        rotate (a=33.027522935779814, v=[0, 1, 0]) {
          cube ([25, 14, 20], center=true);
        }
      }
      translate ([25.5, 127, 34.5]) {
        cube ([40, 40, 10], center=true);
      }
    }
    mirror ([1, 0, 0]) {
      difference () {
        translate ([25.5, 127, 52.5]) {
          rotate (a=33.027522935779814, v=[0, 1, 0]) {
            cube ([25, 14, 20], center=true);
          }
        }
        translate ([25.5, 127, 34.5]) {
          cube ([40, 40, 10], center=true);
        }
      }
    }
  }
}
