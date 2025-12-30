union () {
  difference () {
    import ("base_right_up.stl");
    translate ([151.125, 94, 35]) {
      cube ([117.75, 189, 70], center=true);
    }
  }
  mirror ([1, 0, 0]) {
    difference () {
      import ("base_right_up.stl");
      translate ([151.125, 94, 35]) {
        cube ([117.75, 189, 70], center=true);
      }
    }
  }
}
