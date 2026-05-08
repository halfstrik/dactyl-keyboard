difference () {
  translate ([0, 33, 25]) {
    cube ([28, 2.6, 10], center=true);
  }
  union () {
    translate ([0, 66.5, 19.3]) {
      cube ([45, 70, 2], center=true);
    }
    translate ([0, 67.1, 23.1]) {
      cube ([23.3, 70, 1.4], center=true);
    }
    translate ([8, 35, 22]) {
      cube ([4, 3.4, 7], center=true);
    }
    translate ([-8, 35, 22]) {
      cube ([4, 3.4, 7], center=true);
    }
    difference () {
      translate ([0, 31.5, 25]) {
        cube ([50, 2, 50], center=true);
      }
      hull () {
        translate ([3, 31, 25]) {
          rotate (a=90.0, v=[1, 0, 0]) {
            cylinder ($fn=50, h=4, r=3, center=true);
          }
        }
        mirror ([1, 0, 0]) {
          translate ([3, 31, 25]) {
            rotate (a=90.0, v=[1, 0, 0]) {
              cylinder ($fn=50, h=4, r=3, center=true);
            }
          }
        }
      }
    }
    translate ([0, 23, 25]) {
      rotate (a=90.0, v=[-1, 0, 0]) {
        minkowski () {
          linear_extrude (height=20, center=true){
            polygon (points=[[-3.55, -1.05], [3.55, -1.05], [2.6, 1.05], [-2.6, 1.05]]);
          }
          cylinder ($fn=25, h=0.1, r=0.3, center=true);
        }
      }
    }
    translate ([0, 42.2, 25]) {
      rotate (a=90.0, v=[-1, 0, 0]) {
        minkowski () {
          linear_extrude (height=20, center=true){
            polygon (points=[[-3.95, -1.25], [3.95, -1.25], [2.9, 1.05], [-2.9, 1.05]]);
          }
          cylinder ($fn=25, h=0.1, r=0.3, center=true);
        }
      }
    }
  }
}
