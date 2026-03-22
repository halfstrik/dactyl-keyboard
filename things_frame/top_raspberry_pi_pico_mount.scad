union () {
  difference () {
    intersection () {
      union () {
        difference () {
          translate ([0, 67, 10]) {
            cube ([48, 71, 20], center=true);
          }
          translate ([0, 64, 17]) {
            cube ([32, 65, 10], center=true);
          }
          translate ([0, 66.5, 19]) {
            cube ([45, 70, 3], center=true);
          }
          translate ([20, 34, 20]) {
            cylinder ($fn=25, h=20, r=1, center=true);
          }
          translate ([-20, 34, 20]) {
            cylinder ($fn=25, h=20, r=1, center=true);
          }
          translate ([20, 99.5, 20]) {
            cylinder ($fn=25, h=20, r=1, center=true);
          }
          translate ([-20, 99.5, 20]) {
            cylinder ($fn=25, h=20, r=1, center=true);
          }
          translate ([0, 0, 1]) {
            intersection () {
              union () {
                import ("base_bottom_common.stl");
                mirror ([1, 0, 0]) {
                  import ("base_bottom_common.stl");
                }
                translate ([0, 0, 1.5]) {
                  import ("base_bottom_common.stl");
                }
                translate ([0, 0, 1.5]) {
                  mirror ([1, 0, 0]) {
                    import ("base_bottom_common.stl");
                  }
                }
              }
              cube ([30, 300, 300], center=true);
            }
          }
        }
      }
      union () {
        intersection () {
          translate ([0, 130, 25.4]) {
            rotate (a=8.181818181818182, v=[1, 0, 0]) {
              cube ([416, 110, 30], center=true);
            }
          }
          difference () {
            translate ([0, 0, -9]) {
              intersection () {
                translate ([0, -110, -1332]) {
                  sphere ($fn=300, r=1400);
                }
                translate ([0, 93, 33]) {
                  linear_extrude (height=70, center=true){
                    hull () {
                      translate ([-204, -87, 0]) {
                        circle (r = 4);
                      }
                      translate ([-204, 87, 0]) {
                        circle (r = 4);
                      }
                      translate ([204, -87, 0]) {
                        circle (r = 4);
                      }
                      translate ([204, 87, 0]) {
                        circle (r = 4);
                      }
                    }
                  }
                }
                translate ([0, 310, -787]) {
                  sphere ($fn=300, r=900);
                }
              }
            }
            translate ([0, 0, -8]) {
              cube ([500, 500, 20], center=true);
            }
          }
        }
        difference () {
          translate ([0, 63, 89]) {
            rotate (a=90.0, v=[0, 1, 0]) {
              cylinder ($fn=50, h=416, r=90, center=true);
            }
          }
          translate ([0, 16, -70]) {
            rotate (a=90.0, v=[1, 0, 0]) {
              cylinder ($fn=50, h=32, r=112, center=true);
            }
          }
          translate ([203, 8, 9.8]) {
            cylinder (h=25, r=5.8, center=true);
          }
          translate ([0, 93, 1]) {
            cube ([416, 186, 2], center=true);
          }
          translate ([0, 1, 35]) {
            cube ([416, 2, 70], center=true);
          }
        }
      }
    }
  }
}
