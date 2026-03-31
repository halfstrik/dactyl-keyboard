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
    hull () {
      translate ([3, 35, 25.3]) {
        rotate (a=90.0, v=[1, 0, 0]) {
          cylinder ($fn=50, h=6, r=1.6, center=true);
        }
      }
      mirror ([1, 0, 0]) {
        translate ([3, 35, 25.3]) {
          rotate (a=90.0, v=[1, 0, 0]) {
            cylinder ($fn=50, h=6, r=1.6, center=true);
          }
        }
      }
    }
    hull () {
      translate ([3, 30, 25.3]) {
        rotate (a=90.0, v=[1, 0, 0]) {
          cylinder ($fn=50, h=6, r=1.4, center=true);
        }
      }
      mirror ([1, 0, 0]) {
        translate ([3, 30, 25.3]) {
          rotate (a=90.0, v=[1, 0, 0]) {
            cylinder ($fn=50, h=6, r=1.4, center=true);
          }
        }
      }
    }
    union () {
      translate ([7.62, 34.4, 18.5]) {
        cube ([4, 2, 10], center=true);
      }
      translate ([-7.62, 34.4, 18.5]) {
        cube ([4, 2, 10], center=true);
      }
    }
  }
}
