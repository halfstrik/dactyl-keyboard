difference () {
  union () {
    translate ([0, 90, 8]) {
      cube ([200, 200, 26], center=true);
    }
    translate ([0, 40, 18]) {
      cube ([200, 100, 46], center=true);
    }
  }
  difference () {
    union () {
      intersection () {
        difference () {
          translate ([0, 63, 89]) {
            rotate (a=90.0, v=[0, 1, 0]) {
              cylinder ($fn=150, h=420, r=92, center=true);
            }
          }
          translate ([0, 15, -70]) {
            rotate (a=90.0, v=[1, 0, 0]) {
              cylinder ($fn=100, h=30, r=110, center=true);
            }
          }
          translate ([203, 8, 7]) {
            cylinder (h=25, r=3.7, center=true);
          }
        }
        difference () {
          translate ([0, 0, -11]) {
            intersection () {
              translate ([0, -110, -1330]) {
                sphere ($fn=300, r=1400);
              }
              translate ([0, 93, 35]) {
                linear_extrude (height=70, center=true){
                  hull () {
                    translate ([-206, -89, 0]) {
                      circle (r = 4);
                    }
                    translate ([-206, 89, 0]) {
                      circle (r = 4);
                    }
                    translate ([206, -89, 0]) {
                      circle (r = 4);
                    }
                    translate ([206, 89, 0]) {
                      circle (r = 4);
                    }
                  }
                }
              }
              translate ([0, 310, -785]) {
                sphere ($fn=300, r=900);
              }
            }
          }
          translate ([0, 0, -10]) {
            cube ([500, 500, 20], center=true);
          }
        }
      }
      intersection () {
        difference () {
          translate ([0, 0, -11]) {
            difference () {
              intersection () {
                translate ([0, -110, -1330]) {
                  sphere ($fn=300, r=1400);
                }
                translate ([0, 93, 35]) {
                  linear_extrude (height=70, center=true){
                    hull () {
                      translate ([-206, -89, 0]) {
                        circle (r = 4);
                      }
                      translate ([-206, 89, 0]) {
                        circle (r = 4);
                      }
                      translate ([206, -89, 0]) {
                        circle (r = 4);
                      }
                      translate ([206, 89, 0]) {
                        circle (r = 4);
                      }
                    }
                  }
                }
                translate ([0, 310, -785]) {
                  sphere ($fn=300, r=900);
                }
              }
              union () {
                hull () {
                  rotate (a=6.0, v=[1, 0, 0]) {
                    scale ([1.1, 1, 1.3]) {
                      translate ([125, 66, 87]) {
                        sphere ($fn=150, r=78);
                      }
                    }
                  }
                  rotate (a=6.0, v=[1, 0, 0]) {
                    scale ([1.27, 1, 1.3]) {
                      translate ([145, 71, 84]) {
                        sphere ($fn=150, r=78);
                      }
                    }
                  }
                }
                intersection () {
                  translate ([140, 53, 87]) {
                    scale ([0.72, 0.7, 1.1]) {
                      sphere ($fn=150, r=78);
                    }
                  }
                  translate ([135, 10, 47]) {
                    cube ([55, 30, 30], center=true);
                  }
                }
              }
            }
          }
          translate ([0, 0, -22]) {
            difference () {
              intersection () {
                translate ([0, -110, -1330]) {
                  sphere ($fn=300, r=1400);
                }
                translate ([0, 93, 35]) {
                  linear_extrude (height=70, center=true){
                    hull () {
                      translate ([-206, -89, 0]) {
                        circle (r = 4);
                      }
                      translate ([-206, 89, 0]) {
                        circle (r = 4);
                      }
                      translate ([206, -89, 0]) {
                        circle (r = 4);
                      }
                      translate ([206, 89, 0]) {
                        circle (r = 4);
                      }
                    }
                  }
                }
                translate ([0, 310, -785]) {
                  sphere ($fn=300, r=900);
                }
              }
              union () {
                hull () {
                  rotate (a=6.0, v=[1, 0, 0]) {
                    scale ([1.1, 1, 1.3]) {
                      translate ([125, 66, 87]) {
                        sphere ($fn=150, r=78);
                      }
                    }
                  }
                  rotate (a=6.0, v=[1, 0, 0]) {
                    scale ([1.27, 1, 1.3]) {
                      translate ([145, 71, 84]) {
                        sphere ($fn=150, r=78);
                      }
                    }
                  }
                }
                intersection () {
                  translate ([140, 53, 87]) {
                    scale ([0.72, 0.7, 1.1]) {
                      sphere ($fn=150, r=78);
                    }
                  }
                  translate ([135, 10, 47]) {
                    cube ([55, 30, 30], center=true);
                  }
                }
              }
            }
          }
        }
        translate ([0, 210, 30]) {
          cube ([420, 200, 60], center=true);
        }
      }
      intersection () {
        translate ([0, 130, 23.4]) {
          rotate (a=8.181818181818182, v=[1, 0, 0]) {
            cube ([420, 110, 30], center=true);
          }
        }
        difference () {
          translate ([0, 0, -11]) {
            difference () {
              intersection () {
                translate ([0, -110, -1330]) {
                  sphere ($fn=300, r=1400);
                }
                translate ([0, 93, 35]) {
                  linear_extrude (height=70, center=true){
                    hull () {
                      translate ([-206, -89, 0]) {
                        circle (r = 4);
                      }
                      translate ([-206, 89, 0]) {
                        circle (r = 4);
                      }
                      translate ([206, -89, 0]) {
                        circle (r = 4);
                      }
                      translate ([206, 89, 0]) {
                        circle (r = 4);
                      }
                    }
                  }
                }
                translate ([0, 310, -785]) {
                  sphere ($fn=300, r=900);
                }
              }
              union () {
                hull () {
                  rotate (a=6.0, v=[1, 0, 0]) {
                    scale ([1.1, 1, 1.3]) {
                      translate ([125, 66, 87]) {
                        sphere ($fn=150, r=78);
                      }
                    }
                  }
                  rotate (a=6.0, v=[1, 0, 0]) {
                    scale ([1.27, 1, 1.3]) {
                      translate ([145, 71, 84]) {
                        sphere ($fn=150, r=78);
                      }
                    }
                  }
                }
                intersection () {
                  translate ([140, 53, 87]) {
                    scale ([0.72, 0.7, 1.1]) {
                      sphere ($fn=150, r=78);
                    }
                  }
                  translate ([135, 10, 47]) {
                    cube ([55, 30, 30], center=true);
                  }
                }
              }
            }
          }
          translate ([0, 0, -10]) {
            cube ([500, 500, 20], center=true);
          }
        }
      }
    }
  }
}
