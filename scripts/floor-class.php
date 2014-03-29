<?php

class Floor
{
  public function __construct()
  {
    // nothing to initialize
  }

  public function initialize()
  {
    $_SESSION['dorm'] = "No Dorm Selected";
    $_SESSION['floor'] = -1;
    $_SESSION['max'] = 0;
    $_SESSION['wing'] = "Z";
    $_SESSION['rotate'] = false;
    $_SESSION['max_wing'] = "Z";
  }

  public function switchFloors($dorm, $floor, $max, $wing, $max_wing)
  {
    $_SESSION['dorm'] = $dorm;
    $_SESSION['floor'] = $floor;
    $_SESSION['max'] = $max;
    $_SESSION['wing'] = $wing;
    $_SESSION['max_wing'] = $max_wing;
    header("location:main.php");
  }

  public function setFloor($floor)
  {
    $_SESSION['floor'] = $floor;
    header("location:main.php");
  }

  public function setWing($wing)
  {
    $_SESSION['wing'] = $wing;
    header("location:main.php");
  }

  public function setRotate($rotate)
  {
    $_SESSION['rotate'] = $rotate;
    header("location:main.php");
  }

  public function printFloor()
  {
    if($_SESSION['dorm'] != "No Dorm Selected")
      return true;
    else
      return false;
  }
}

?>