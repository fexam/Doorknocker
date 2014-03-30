<?php
	ob_start();
	session_start();

	// connect to database
  	mysql_connect("localhost", "ryana3", "sturman") or die("bad");
	mysql_select_db("ryana3_test") or die("bad");

	$room = $_POST['room'];
	$date = $_POST['date'];
	$dorm = $_SESSION['dorm'];

	// query to find 'id' needed to update the specfifc room
	$sql = "SELECT r.id FROM rooms r, dorms d WHERE d.dorm_name='$dorm' and r.room_number=$room;";
  	$result = mysql_query($sql);
  	$row = mysql_fetch_assoc($result);
  	
  	// state number for a red room
  	$state = 3;

  	// query to update the room given a specific 'id'
	$id = $row['id'];
  	$sql = "UPDATE rooms SET state=$state, date='$date' WHERE id=$id;";
	mysql_query($sql);
?>