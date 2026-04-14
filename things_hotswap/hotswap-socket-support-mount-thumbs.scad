difference () {
  union () {
    difference () {
      translate ([0, 0, 0.4]) {
        union () {
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
          translate ([0, 130, 25.4]) {
            rotate (a=8.181818181818182, v=[1, 0, 0]) {
              cube ([416, 110, 30], center=true);
            }
          }
        }
      }
      translate ([0, 0, 1.4]) {
        union () {
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
          translate ([0, 130, 25.4]) {
            rotate (a=8.181818181818182, v=[1, 0, 0]) {
              cube ([416, 110, 30], center=true);
            }
          }
        }
      }
      translate ([50, 0, 0]) {
        cube ([600, 150, 300], center=true);
      }
      translate ([50, 215, 0]) {
        cube ([600, 150, 300], center=true);
      }
      translate ([165, 100, 0]) {
        cube ([120, 200, 300], center=true);
      }
      translate ([-125, 100, 0]) {
        cube ([320, 200, 300], center=true);
      }
      translate ([50, 140, 50]) {
        cube ([30, 30, 30], center=true);
      }
      translate ([0, 0, -14]) {
        cube ([500, 500, 30], center=true);
      }
      translate ([37.21, 124.35, 28]) {
        rotate (a=73.46938775510203, v=[0, 0, 1]) {
          cube ([60, 18, 50], center=true);
        }
      }
      translate ([87, 134, 28]) {
        rotate (a=73.46938775510203, v=[0, 0, 1]) {
          cube ([24, 50, 50], center=true);
        }
      }
    }
    intersection () {
      union () {
        translate ([125, 58, 0]) {
          mirror ([0, 1, 0]) {
            rotate (a=3.0, v=[0, 1, 0]) {
              union () {
                translate ([-52, -53, 37]) {
                  rotate (a=7.499999999999999, v=[1, -0.1, 0]) {
                    rotate (a=16.875, v=[0, 0, 1]) {
                      translate ([17.4, 0, 0]) {
                        translate ([0, 0, 1597.5641385291783]) {
                          rotate (a=0.0, v=[0, 1, 0]) {
                            translate ([0, 0, -1597.5641385291783]) {
                              translate ([0, 0, 1516.0762963369527]) {
                                rotate (a=-0.3515625, v=[1, 0, 0]) {
                                  translate ([0, 0, -1516.0762963369527]) {
                                    rotate (a=90.0, v=[0, 0, 1]) {
                                      union () {
                                        difference () {
                                          union () {
                                            translate ([1.7, 5.75, -3.5]) {
                                              cube ([11, 5, 2], center=true);
                                            }
                                            translate ([-3.7, 3.15, -3.5]) {
                                              cube ([5, 3, 2], center=true);
                                            }
                                            translate ([4.2, 2.2, -3.8]) {
                                              cube ([4, 5, 1.6], center=true);
                                            }
                                          }
                                          union () {
                                            translate ([0.5, 5, -3.12]) {
                                              cube ([11.7, 4.5, 1.5], center=true);
                                            }
                                            translate ([4.2, 2.8, -3.12]) {
                                              cube ([4, 4.5, 1.5], center=true);
                                            }
                                          }
                                          translate ([-6.3, 5.5, -3.5]) {
                                            rotate (a=10.0, v=[1, 0, 0]) {
                                              cube ([2, 2, 10], center=true);
                                            }
                                          }
                                          translate ([7.3, 3.5, -3.5]) {
                                            rotate (a=10.0, v=[-1, 0, 0]) {
                                              cube ([2, 2, 10], center=true);
                                            }
                                          }
                                          translate ([8.1, 9.1, -3.5]) {
                                            rotate (a=22.5, v=[1, -1, 0]) {
                                              rotate (a=45.0, v=[0, 0, 1]) {
                                                cube ([4, 4, 10], center=true);
                                              }
                                            }
                                          }
                                        }
                                        translate ([0, 0, -54.5]) {
                                          linear_extrude (height=100, center=true){
                                            projection (cut = false) {
                                              difference () {
                                                union () {
                                                  translate ([1.7, 5.75, -3.5]) {
                                                    cube ([11, 5, 2], center=true);
                                                  }
                                                  translate ([-3.7, 3.15, -3.5]) {
                                                    cube ([5, 3, 2], center=true);
                                                  }
                                                  translate ([4.2, 2.2, -3.8]) {
                                                    cube ([4, 5, 1.6], center=true);
                                                  }
                                                }
                                                union () {
                                                  translate ([0.5, 5, -3.12]) {
                                                    cube ([11.7, 4.5, 1.5], center=true);
                                                  }
                                                  translate ([4.2, 2.8, -3.12]) {
                                                    cube ([4, 4.5, 1.5], center=true);
                                                  }
                                                }
                                                translate ([-6.3, 5.5, -3.5]) {
                                                  rotate (a=10.0, v=[1, 0, 0]) {
                                                    cube ([2, 2, 10], center=true);
                                                  }
                                                }
                                                translate ([7.3, 3.5, -3.5]) {
                                                  rotate (a=10.0, v=[-1, 0, 0]) {
                                                    cube ([2, 2, 10], center=true);
                                                  }
                                                }
                                                translate ([8.1, 9.1, -3.5]) {
                                                  rotate (a=22.5, v=[1, -1, 0]) {
                                                    rotate (a=45.0, v=[0, 0, 1]) {
                                                      cube ([4, 4, 10], center=true);
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                translate ([-52, -53, 37]) {
                  rotate (a=7.499999999999999, v=[1, -0.1, 0]) {
                    rotate (a=16.875, v=[0, 0, 1]) {
                      translate ([17.4, 0, 0]) {
                        translate ([0, 0, 1597.5641385291783]) {
                          rotate (a=0.703125, v=[0, 1, 0]) {
                            translate ([0, 0, -1597.5641385291783]) {
                              translate ([0, 0, 1516.0762963369527]) {
                                rotate (a=-0.3515625, v=[1, 0, 0]) {
                                  translate ([0, 0, -1516.0762963369527]) {
                                    rotate (a=90.0, v=[0, 0, 1]) {
                                      union () {
                                        difference () {
                                          union () {
                                            translate ([1.7, 5.75, -3.5]) {
                                              cube ([11, 5, 2], center=true);
                                            }
                                            translate ([-3.7, 3.15, -3.5]) {
                                              cube ([5, 3, 2], center=true);
                                            }
                                            translate ([4.2, 2.2, -3.8]) {
                                              cube ([4, 5, 1.6], center=true);
                                            }
                                          }
                                          union () {
                                            translate ([0.5, 5, -3.12]) {
                                              cube ([11.7, 4.5, 1.5], center=true);
                                            }
                                            translate ([4.2, 2.8, -3.12]) {
                                              cube ([4, 4.5, 1.5], center=true);
                                            }
                                          }
                                          translate ([-6.3, 5.5, -3.5]) {
                                            rotate (a=10.0, v=[1, 0, 0]) {
                                              cube ([2, 2, 10], center=true);
                                            }
                                          }
                                          translate ([7.3, 3.5, -3.5]) {
                                            rotate (a=10.0, v=[-1, 0, 0]) {
                                              cube ([2, 2, 10], center=true);
                                            }
                                          }
                                          translate ([8.1, 9.1, -3.5]) {
                                            rotate (a=22.5, v=[1, -1, 0]) {
                                              rotate (a=45.0, v=[0, 0, 1]) {
                                                cube ([4, 4, 10], center=true);
                                              }
                                            }
                                          }
                                        }
                                        translate ([0, 0, -54.5]) {
                                          linear_extrude (height=100, center=true){
                                            projection (cut = false) {
                                              difference () {
                                                union () {
                                                  translate ([1.7, 5.75, -3.5]) {
                                                    cube ([11, 5, 2], center=true);
                                                  }
                                                  translate ([-3.7, 3.15, -3.5]) {
                                                    cube ([5, 3, 2], center=true);
                                                  }
                                                  translate ([4.2, 2.2, -3.8]) {
                                                    cube ([4, 5, 1.6], center=true);
                                                  }
                                                }
                                                union () {
                                                  translate ([0.5, 5, -3.12]) {
                                                    cube ([11.7, 4.5, 1.5], center=true);
                                                  }
                                                  translate ([4.2, 2.8, -3.12]) {
                                                    cube ([4, 4.5, 1.5], center=true);
                                                  }
                                                }
                                                translate ([-6.3, 5.5, -3.5]) {
                                                  rotate (a=10.0, v=[1, 0, 0]) {
                                                    cube ([2, 2, 10], center=true);
                                                  }
                                                }
                                                translate ([7.3, 3.5, -3.5]) {
                                                  rotate (a=10.0, v=[-1, 0, 0]) {
                                                    cube ([2, 2, 10], center=true);
                                                  }
                                                }
                                                translate ([8.1, 9.1, -3.5]) {
                                                  rotate (a=22.5, v=[1, -1, 0]) {
                                                    rotate (a=45.0, v=[0, 0, 1]) {
                                                      cube ([4, 4, 10], center=true);
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                translate ([-52, -53, 40]) {
                  rotate (a=7.499999999999999, v=[1, -0.1, 0]) {
                    rotate (a=16.875, v=[0, 0, 1]) {
                      translate ([17.4, 0, 0]) {
                        translate ([0, 0.8, 1597.5641385291783]) {
                          rotate (a=0.703125, v=[0, 1, 0]) {
                            translate ([0, 0, -1597.5641385291783]) {
                              translate ([0, 0, 1516.0762963369527]) {
                                rotate (a=0.703125, v=[1, 0, 0]) {
                                  translate ([0, 0, -1516.0762963369527]) {
                                    rotate (a=90.0, v=[0, 0, 1]) {
                                      union () {
                                        difference () {
                                          union () {
                                            translate ([1.7, 5.75, -3.5]) {
                                              cube ([11, 5, 2], center=true);
                                            }
                                            translate ([-3.7, 3.15, -3.5]) {
                                              cube ([5, 3, 2], center=true);
                                            }
                                            translate ([4.2, 2.2, -3.8]) {
                                              cube ([4, 5, 1.6], center=true);
                                            }
                                          }
                                          union () {
                                            translate ([0.5, 5, -3.12]) {
                                              cube ([11.7, 4.5, 1.5], center=true);
                                            }
                                            translate ([4.2, 2.8, -3.12]) {
                                              cube ([4, 4.5, 1.5], center=true);
                                            }
                                          }
                                          translate ([-6.3, 5.5, -3.5]) {
                                            rotate (a=10.0, v=[1, 0, 0]) {
                                              cube ([2, 2, 10], center=true);
                                            }
                                          }
                                          translate ([7.3, 3.5, -3.5]) {
                                            rotate (a=10.0, v=[-1, 0, 0]) {
                                              cube ([2, 2, 10], center=true);
                                            }
                                          }
                                          translate ([8.1, 9.1, -3.5]) {
                                            rotate (a=22.5, v=[1, -1, 0]) {
                                              rotate (a=45.0, v=[0, 0, 1]) {
                                                cube ([4, 4, 10], center=true);
                                              }
                                            }
                                          }
                                        }
                                        translate ([0, 0, -54.5]) {
                                          linear_extrude (height=100, center=true){
                                            projection (cut = false) {
                                              difference () {
                                                union () {
                                                  translate ([1.7, 5.75, -3.5]) {
                                                    cube ([11, 5, 2], center=true);
                                                  }
                                                  translate ([-3.7, 3.15, -3.5]) {
                                                    cube ([5, 3, 2], center=true);
                                                  }
                                                  translate ([4.2, 2.2, -3.8]) {
                                                    cube ([4, 5, 1.6], center=true);
                                                  }
                                                }
                                                union () {
                                                  translate ([0.5, 5, -3.12]) {
                                                    cube ([11.7, 4.5, 1.5], center=true);
                                                  }
                                                  translate ([4.2, 2.8, -3.12]) {
                                                    cube ([4, 4.5, 1.5], center=true);
                                                  }
                                                }
                                                translate ([-6.3, 5.5, -3.5]) {
                                                  rotate (a=10.0, v=[1, 0, 0]) {
                                                    cube ([2, 2, 10], center=true);
                                                  }
                                                }
                                                translate ([7.3, 3.5, -3.5]) {
                                                  rotate (a=10.0, v=[-1, 0, 0]) {
                                                    cube ([2, 2, 10], center=true);
                                                  }
                                                }
                                                translate ([8.1, 9.1, -3.5]) {
                                                  rotate (a=22.5, v=[1, -1, 0]) {
                                                    rotate (a=45.0, v=[0, 0, 1]) {
                                                      cube ([4, 4, 10], center=true);
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                union () {
                  translate ([-52, -53, 37]) {
                    rotate (a=7.499999999999999, v=[1, -0.1, 0]) {
                      rotate (a=16.875, v=[0, 0, 1]) {
                        translate ([17.4, 0, 0]) {
                          translate ([0, -0.5, 1597.5641385291783]) {
                            rotate (a=1.40625, v=[0, 1, 0]) {
                              translate ([0, 0, -1597.5641385291783]) {
                                translate ([0, 0, 1516.0762963369527]) {
                                  rotate (a=-0.703125, v=[1, 0, 0]) {
                                    translate ([0, 0, -1516.0762963369527]) {
                                      rotate (a=90.0, v=[0, 0, 1]) {
                                        union () {
                                          difference () {
                                            union () {
                                              translate ([1.7, 5.75, -3.5]) {
                                                cube ([11, 5, 2], center=true);
                                              }
                                              translate ([-3.7, 3.15, -3.5]) {
                                                cube ([5, 3, 2], center=true);
                                              }
                                              translate ([4.2, 2.2, -3.8]) {
                                                cube ([4, 5, 1.6], center=true);
                                              }
                                            }
                                            union () {
                                              translate ([0.5, 5, -3.12]) {
                                                cube ([11.7, 4.5, 1.5], center=true);
                                              }
                                              translate ([4.2, 2.8, -3.12]) {
                                                cube ([4, 4.5, 1.5], center=true);
                                              }
                                            }
                                            translate ([-6.3, 5.5, -3.5]) {
                                              rotate (a=10.0, v=[1, 0, 0]) {
                                                cube ([2, 2, 10], center=true);
                                              }
                                            }
                                            translate ([7.3, 3.5, -3.5]) {
                                              rotate (a=10.0, v=[-1, 0, 0]) {
                                                cube ([2, 2, 10], center=true);
                                              }
                                            }
                                            translate ([8.1, 9.1, -3.5]) {
                                              rotate (a=22.5, v=[1, -1, 0]) {
                                                rotate (a=45.0, v=[0, 0, 1]) {
                                                  cube ([4, 4, 10], center=true);
                                                }
                                              }
                                            }
                                          }
                                          translate ([0, 0, -54.5]) {
                                            linear_extrude (height=100, center=true){
                                              projection (cut = false) {
                                                difference () {
                                                  union () {
                                                    translate ([1.7, 5.75, -3.5]) {
                                                      cube ([11, 5, 2], center=true);
                                                    }
                                                    translate ([-3.7, 3.15, -3.5]) {
                                                      cube ([5, 3, 2], center=true);
                                                    }
                                                    translate ([4.2, 2.2, -3.8]) {
                                                      cube ([4, 5, 1.6], center=true);
                                                    }
                                                  }
                                                  union () {
                                                    translate ([0.5, 5, -3.12]) {
                                                      cube ([11.7, 4.5, 1.5], center=true);
                                                    }
                                                    translate ([4.2, 2.8, -3.12]) {
                                                      cube ([4, 4.5, 1.5], center=true);
                                                    }
                                                  }
                                                  translate ([-6.3, 5.5, -3.5]) {
                                                    rotate (a=10.0, v=[1, 0, 0]) {
                                                      cube ([2, 2, 10], center=true);
                                                    }
                                                  }
                                                  translate ([7.3, 3.5, -3.5]) {
                                                    rotate (a=10.0, v=[-1, 0, 0]) {
                                                      cube ([2, 2, 10], center=true);
                                                    }
                                                  }
                                                  translate ([8.1, 9.1, -3.5]) {
                                                    rotate (a=22.5, v=[1, -1, 0]) {
                                                      rotate (a=45.0, v=[0, 0, 1]) {
                                                        cube ([4, 4, 10], center=true);
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                  translate ([-52, -53, 37]) {
                    rotate (a=7.499999999999999, v=[1, -0.1, 0]) {
                      rotate (a=16.875, v=[0, 0, 1]) {
                        translate ([17.4, 0, 0]) {
                          translate ([0, 0.4, 1597.5641385291783]) {
                            rotate (a=1.40625, v=[0, 1, 0]) {
                              translate ([0, 0, -1597.5641385291783]) {
                                translate ([0, 0, 1516.0762963369527]) {
                                  rotate (a=0.0, v=[1, 0, 0]) {
                                    translate ([0, 0, -1516.0762963369527]) {
                                      rotate (a=90.0, v=[0, 0, 1]) {
                                        union () {
                                          difference () {
                                            union () {
                                              translate ([1.7, 5.75, -3.5]) {
                                                cube ([11, 5, 2], center=true);
                                              }
                                              translate ([-3.7, 3.15, -3.5]) {
                                                cube ([5, 3, 2], center=true);
                                              }
                                              translate ([4.2, 2.2, -3.8]) {
                                                cube ([4, 5, 1.6], center=true);
                                              }
                                            }
                                            union () {
                                              translate ([0.5, 5, -3.12]) {
                                                cube ([11.7, 4.5, 1.5], center=true);
                                              }
                                              translate ([4.2, 2.8, -3.12]) {
                                                cube ([4, 4.5, 1.5], center=true);
                                              }
                                            }
                                            translate ([-6.3, 5.5, -3.5]) {
                                              rotate (a=10.0, v=[1, 0, 0]) {
                                                cube ([2, 2, 10], center=true);
                                              }
                                            }
                                            translate ([7.3, 3.5, -3.5]) {
                                              rotate (a=10.0, v=[-1, 0, 0]) {
                                                cube ([2, 2, 10], center=true);
                                              }
                                            }
                                            translate ([8.1, 9.1, -3.5]) {
                                              rotate (a=22.5, v=[1, -1, 0]) {
                                                rotate (a=45.0, v=[0, 0, 1]) {
                                                  cube ([4, 4, 10], center=true);
                                                }
                                              }
                                            }
                                          }
                                          translate ([0, 0, -54.5]) {
                                            linear_extrude (height=100, center=true){
                                              projection (cut = false) {
                                                difference () {
                                                  union () {
                                                    translate ([1.7, 5.75, -3.5]) {
                                                      cube ([11, 5, 2], center=true);
                                                    }
                                                    translate ([-3.7, 3.15, -3.5]) {
                                                      cube ([5, 3, 2], center=true);
                                                    }
                                                    translate ([4.2, 2.2, -3.8]) {
                                                      cube ([4, 5, 1.6], center=true);
                                                    }
                                                  }
                                                  union () {
                                                    translate ([0.5, 5, -3.12]) {
                                                      cube ([11.7, 4.5, 1.5], center=true);
                                                    }
                                                    translate ([4.2, 2.8, -3.12]) {
                                                      cube ([4, 4.5, 1.5], center=true);
                                                    }
                                                  }
                                                  translate ([-6.3, 5.5, -3.5]) {
                                                    rotate (a=10.0, v=[1, 0, 0]) {
                                                      cube ([2, 2, 10], center=true);
                                                    }
                                                  }
                                                  translate ([7.3, 3.5, -3.5]) {
                                                    rotate (a=10.0, v=[-1, 0, 0]) {
                                                      cube ([2, 2, 10], center=true);
                                                    }
                                                  }
                                                  translate ([8.1, 9.1, -3.5]) {
                                                    rotate (a=22.5, v=[1, -1, 0]) {
                                                      rotate (a=45.0, v=[0, 0, 1]) {
                                                        cube ([4, 4, 10], center=true);
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                  translate ([-52, -53, 40]) {
                    rotate (a=7.499999999999999, v=[1, -0.1, 0]) {
                      rotate (a=16.875, v=[0, 0, 1]) {
                        translate ([17.4, 0, 0]) {
                          translate ([0, 0.8, 1597.5641385291783]) {
                            rotate (a=1.40625, v=[0, 1, 0]) {
                              translate ([0, 0, -1597.5641385291783]) {
                                translate ([0, 0, 1516.0762963369527]) {
                                  rotate (a=0.703125, v=[1, 0, 0]) {
                                    translate ([0, 0, -1516.0762963369527]) {
                                      rotate (a=90.0, v=[0, 0, 1]) {
                                        union () {
                                          difference () {
                                            union () {
                                              translate ([1.7, 5.75, -3.5]) {
                                                cube ([11, 5, 2], center=true);
                                              }
                                              translate ([-3.7, 3.15, -3.5]) {
                                                cube ([5, 3, 2], center=true);
                                              }
                                              translate ([4.2, 2.2, -3.8]) {
                                                cube ([4, 5, 1.6], center=true);
                                              }
                                            }
                                            union () {
                                              translate ([0.5, 5, -3.12]) {
                                                cube ([11.7, 4.5, 1.5], center=true);
                                              }
                                              translate ([4.2, 2.8, -3.12]) {
                                                cube ([4, 4.5, 1.5], center=true);
                                              }
                                            }
                                            translate ([-6.3, 5.5, -3.5]) {
                                              rotate (a=10.0, v=[1, 0, 0]) {
                                                cube ([2, 2, 10], center=true);
                                              }
                                            }
                                            translate ([7.3, 3.5, -3.5]) {
                                              rotate (a=10.0, v=[-1, 0, 0]) {
                                                cube ([2, 2, 10], center=true);
                                              }
                                            }
                                            translate ([8.1, 9.1, -3.5]) {
                                              rotate (a=22.5, v=[1, -1, 0]) {
                                                rotate (a=45.0, v=[0, 0, 1]) {
                                                  cube ([4, 4, 10], center=true);
                                                }
                                              }
                                            }
                                          }
                                          translate ([0, 0, -54.5]) {
                                            linear_extrude (height=100, center=true){
                                              projection (cut = false) {
                                                difference () {
                                                  union () {
                                                    translate ([1.7, 5.75, -3.5]) {
                                                      cube ([11, 5, 2], center=true);
                                                    }
                                                    translate ([-3.7, 3.15, -3.5]) {
                                                      cube ([5, 3, 2], center=true);
                                                    }
                                                    translate ([4.2, 2.2, -3.8]) {
                                                      cube ([4, 5, 1.6], center=true);
                                                    }
                                                  }
                                                  union () {
                                                    translate ([0.5, 5, -3.12]) {
                                                      cube ([11.7, 4.5, 1.5], center=true);
                                                    }
                                                    translate ([4.2, 2.8, -3.12]) {
                                                      cube ([4, 4.5, 1.5], center=true);
                                                    }
                                                  }
                                                  translate ([-6.3, 5.5, -3.5]) {
                                                    rotate (a=10.0, v=[1, 0, 0]) {
                                                      cube ([2, 2, 10], center=true);
                                                    }
                                                  }
                                                  translate ([7.3, 3.5, -3.5]) {
                                                    rotate (a=10.0, v=[-1, 0, 0]) {
                                                      cube ([2, 2, 10], center=true);
                                                    }
                                                  }
                                                  translate ([8.1, 9.1, -3.5]) {
                                                    rotate (a=22.5, v=[1, -1, 0]) {
                                                      rotate (a=45.0, v=[0, 0, 1]) {
                                                        cube ([4, 4, 10], center=true);
                                                      }
                                                    }
                                                  }
                                                }
                                              }
                                            }
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        translate ([48, 115, 8]) {
          rotate (a=17.142857142857142, v=[-1, 0, -1]) {
            cube ([2, 40, 35], center=true);
          }
        }
        translate ([66, 105, 8]) {
          rotate (a=17.142857142857142, v=[-1, 0, -1]) {
            cube ([2, 28, 35], center=true);
          }
        }
        translate ([68, 115, 8]) {
          rotate (a=17.142857142857142, v=[-1, 0, -1]) {
            cube ([40, 2, 35], center=true);
          }
        }
        translate ([57, 87, 10]) {
          rotate (a=17.142857142857142, v=[-1, 0, -1]) {
            cube ([30, 2, 40], center=true);
          }
        }
        translate ([94, 105, 2]) {
          rotate (a=17.142857142857142, v=[-1, 0, -1]) {
            cube ([20, 2, 30], center=true);
          }
        }
        translate ([85, 97, 8]) {
          rotate (a=18.0, v=[0.5, 1, 0]) {
            cube ([2, 34, 30], center=true);
          }
        }
      }
      translate ([0, 0, 0.4]) {
        union () {
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
          translate ([0, 130, 25.4]) {
            rotate (a=8.181818181818182, v=[1, 0, 0]) {
              cube ([416, 110, 30], center=true);
            }
          }
        }
      }
    }
  }
  translate ([100, 100, -97.1]) {
    cube ([700, 300, 200], center=true);
  }
}
