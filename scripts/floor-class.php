<?php
include_once 'page-class.php';

class Floor
{
  private $page;

  public function __construct()
  {
    $this->page = new Page("main.php");
  }

  public function initialize()
  {
    // initialize global parameters
    $_SESSION['dorm'] = "No Dorm Selected";
    $_SESSION['floor'] = -1;
    $_SESSION['max'] = 0;
    $_SESSION['wing'] = "Z";
    $_SESSION['rotate'] = false;
    $_SESSION['max_wing'] = "Z";
  }

  public function switchFloors($dorm, $floor, $max, $wing, $max_wing)
  {
    // set all global parameters
    $_SESSION['dorm'] = $dorm;
    $_SESSION['floor'] = $floor;
    $_SESSION['max'] = $max;
    $_SESSION['wing'] = $wing;
    $_SESSION['max_wing'] = $max_wing;
    $this->page->redirect();
  }

  public function setFloor($floor)
  {
    // set global floor parameter
    $_SESSION['floor'] = $floor;
    $this->page->redirect();
  }

  public function setWing($wing)
  {
    // set global wing parameter
    $_SESSION['wing'] = $wing;
    $this->page->redirect();
  }

  public function setRotate($rotate)
  {
    // set global rotate parameter
    $_SESSION['rotate'] = $rotate;
    $this->page->redirect();
  }

  public function printFloor()
  {
    // check if a floor has been selected
    if($_SESSION['dorm'] != "No Dorm Selected")
      return true;
    else
      return false;
  }

  public function findDormID($dorm)
  {
    $sql = "SELECT * FROM dorms WHERE dorm_name='$dorm';";
    $result = mysql_query($sql);
    $row = mysql_fetch_assoc($result);
    $dorm_id = $row['dorm_id'];

    return $dorm_id;
  }

  public function findRoomID($dorm_id, $room)
  {
    $sql = "SELECT * FROM rooms WHERE dorm_id=$dorm_id AND room_number=$room;";
    $result = mysql_query($sql);
    $row = mysql_fetch_assoc($result);
    $id = $row['id'];

    return $id;
  }

  public function saveRoom($id, $state, $notes, $date, $state1, $state2, $state3)
  {
    // query to update the room given a specific 'id'
    $sql = "UPDATE rooms SET state=$state, notes='$note', date='$date', state1='$state1',
                 state2='$state2', state3='$state3' WHERE id=$id;";
    mysql_query($sql);
  }

  public function saveState($id, $state, $date)
  {
    // query to update the room given a specific 'id'
    $sql = "UPDATE rooms SET state=$state, date='$date' WHERE id=$id;";
    mysql_query($sql);
  }

  public function getState($id)
  {
    // query to update the room given a specific 'id'
    $sql = "SELECT * FROM rooms WHERE id=$id;";
    $result = mysql_query($sql);
    $row = mysql_fetch_assoc($result);

    return $row['state'];
  }
}

?>