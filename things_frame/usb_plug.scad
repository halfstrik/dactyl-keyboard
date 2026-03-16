difference () {
  translate ([0, 33, 25]) {
    cube ([25, 2.6, 10], center=true);
  }
  union () {
    translate ([0, 66.5, 19.2]) {
      cube ([45, 70, 2], center=true);
    }
    translate ([0, 67.1, 23.2]) {
      cube ([21.3, 70, 1], center=true);
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
      translate ([3, 35, 24.9]) {
        rotate (a=90.0, v=[1, 0, 0]) {
          cylinder ($fn=50, h=6, r=1.3, center=true);
        }
      }
      mirror ([1, 0, 0]) {
        translate ([3, 35, 24.9]) {
          rotate (a=90.0, v=[1, 0, 0]) {
            cylinder ($fn=50, h=6, r=1.3, center=true);
          }
        }
      }
    }
    hull () {
      translate ([3, 30, 24.9]) {
        rotate (a=90.0, v=[1, 0, 0]) {
          cylinder ($fn=50, h=6, r=1.28, center=true);
        }
      }
      mirror ([1, 0, 0]) {
        translate ([3, 30, 24.9]) {
          rotate (a=90.0, v=[1, 0, 0]) {
            cylinder ($fn=50, h=6, r=1.28, center=true);
          }
        }
      }
    }
  }
}
