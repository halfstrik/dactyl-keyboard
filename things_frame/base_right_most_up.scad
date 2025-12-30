union () {
  difference () {
    import ("base_right_up.stl");
    translate ([0, 94, 35]) {
      cube ([184.5, 189, 70], center=true);
    }
  }
  difference () {
    intersection () {
      translate ([195.25, 108, 39]) {
        cube ([25.5, 24, 30], center=true);
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
    union () {
      hull () {
        rotate (a=6.0, v=[1, 0, 0]) {
          scale ([1, 1.0, 1.3]) {
            translate ([134, 66, 87]) {
              sphere ($fn=150, r=78);
            }
          }
        }
        rotate (a=6.0, v=[1, 0, 0]) {
          scale ([1.27, 1.0, 1.3]) {
            translate ([145, 71, 84]) {
              sphere ($fn=150, r=78);
            }
          }
        }
      }
      intersection () {
        translate ([134, 53, 87]) {
          scale ([0.5, 0.7, 1.1]) {
            sphere ($fn=150, r=78);
          }
        }
        translate ([135, 10, 47]) {
          cube ([55, 30, 30], center=true);
        }
      }
    }
    translate ([193, 112, 12.799999999999997]) {
      cylinder ($fn=50, h=35, r=1.3, center=true);
    }
  }
  difference () {
    intersection () {
      translate ([195.25, 9, 42]) {
        cube ([25.5, 14, 30], center=true);
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
    union () {
      hull () {
        rotate (a=6.0, v=[1, 0, 0]) {
          scale ([1, 1.0, 1.3]) {
            translate ([134, 66, 87]) {
              sphere ($fn=150, r=78);
            }
          }
        }
        rotate (a=6.0, v=[1, 0, 0]) {
          scale ([1.27, 1.0, 1.3]) {
            translate ([145, 71, 84]) {
              sphere ($fn=150, r=78);
            }
          }
        }
      }
      intersection () {
        translate ([134, 53, 87]) {
          scale ([0.5, 0.7, 1.1]) {
            sphere ($fn=150, r=78);
          }
        }
        translate ([135, 10, 47]) {
          cube ([55, 30, 30], center=true);
        }
      }
    }
    translate ([193, 9, 18.799999999999997]) {
      cylinder ($fn=50, h=25, r=1.3, center=true);
    }
  }
  difference () {
    intersection () {
      difference () {
        translate ([115, 4, 61.3]) {
          cube ([21, 9.5, 30], center=true);
        }
        translate ([120, 4, 43]) {
          rotate (a=14.999999999999998, v=[0, -1, 0]) {
            cube ([20, 10, 10], center=true);
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
    union () {
      hull () {
        rotate (a=6.0, v=[1, 0, 0]) {
          scale ([1, 1.0, 1.3]) {
            translate ([134, 66, 87]) {
              sphere ($fn=150, r=78);
            }
          }
        }
        rotate (a=6.0, v=[1, 0, 0]) {
          scale ([1.27, 1.0, 1.3]) {
            translate ([145, 71, 84]) {
              sphere ($fn=150, r=78);
            }
          }
        }
      }
      intersection () {
        translate ([134, 53, 87]) {
          scale ([0.5, 0.7, 1.1]) {
            sphere ($fn=150, r=78);
          }
        }
        translate ([135, 10, 47]) {
          cube ([55, 30, 30], center=true);
        }
      }
    }
    translate ([108, 5.5, 37.8]) {
      cylinder ($fn=50, h=25, r=1.3, center=true);
    }
  }
  difference () {
    intersection () {
      translate ([195, 177, 27]) {
        cube ([35, 21, 35], center=true);
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
    translate ([185, 174, 5.9]) {
      cylinder ($fn=50, h=32, r=1.3, center=true);
    }
  }
}
