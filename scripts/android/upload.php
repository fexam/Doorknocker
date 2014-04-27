<?php
  if(isset($_POST["json"]))
  {    
    $dorm = $_GET['dorm'];
    $json = json_decode($_POST['json'], true);

    // connect to database
    mysql_connect("localhost", "ryana3", "sturman");
    mysql_select_db("ryana3_test");

    // query to find 'id' needed to update the specfifc room
	$sql = "SELECT * FROM dorms WHERE dorm_name='$dorm';";
	$result = mysql_query($sql);
	$row = mysql_fetch_assoc($result);
	$dorm_id = $row['dorm_id'];

    foreach($json as $row)
    {
      $room = $row['room_number'];
      $s = $row['state'];
      if($s == "0")
        $state = 1;
      else if($s == "1")
        $state = 2;
      else if($s == "2")
        $state = 3;
      else if($s == "3")
        $state = 0;
      $date = $row['date'];
      $notes = $row['notes'];     

	  $sql = "SELECT * FROM rooms WHERE dorm_id=$dorm_id AND room_number=$room;";
	  $result = mysql_query($sql);
	  $row = mysql_fetch_assoc($result);
	  $id = $row['id'];

      $sql = "UPDATE rooms SET state=$state, notes='$note', date='$date' WHERE id=$id;";
      mysql_query($sql);
    }

    echo("Success");
  }
?>