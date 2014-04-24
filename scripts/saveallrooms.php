<?php
	ob_start();
	session_start();
	include 'database-class.php';
	
	// connect to database
  	$db = new Database();
  	$db->connect();

	$room = $_POST['room'];
	$date = $_POST['date'];
	$dorm = $_SESSION['dorm'];

	// query to find 'id' needed to update the specfifc room 	
  	$sql = "SELECT * FROM dorms WHERE dorm_name='$dorm';";
  	$result = mysql_query($sql);
  	$row = mysql_fetch_assoc($result);
  	$dorm_id = $row['dorm_id'];

  	$sql = "SELECT * FROM rooms WHERE dorm_id=$dorm_id AND room_number=$room;";
  	$result = mysql_query($sql);
  	$row = mysql_fetch_assoc($result);
  	$id = $row['id'];

  	// state number for a red room
  	$state = 3;

  	// query to update the room given a specific 'id'
  	$sql = "UPDATE rooms SET state=$state, date='$date' WHERE id=$id;";
	mysql_query($sql);
?>