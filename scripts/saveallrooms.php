<?php
	ob_start();
	session_start();

	// Connect to database
  	mysql_connect("localhost", "ryana3", "sturman") or die("bad");
	mysql_select_db("ryana3_test") or die("bad");

	$room = $_POST['room'];
	$date = $_POST['date'];
	$dorm = $_SESSION['dorm'];

	$sql = "SELECT r.id FROM rooms r, dorms d WHERE d.dorm_name='$dorm' and r.room_number=$room;";
  	$result = mysql_query($sql);
  	$row = mysql_fetch_assoc($result);
  	
  	$state = 3;

	$id = $row['id'];
  	$sql = "UPDATE rooms SET state=$state, date='$date' WHERE id=$id;";
	mysql_query($sql);
?>