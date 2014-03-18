<?php
	ob_start();
	session_start();

	// Connect to database
  	mysql_connect("localhost", "ryana3", "sturman") or die("bad");
	mysql_select_db("ryana3_test") or die("bad");

	$room = $_POST['room'];
	$radio = $_POST['optionsRadios'];
	$note = $_POST['notes'];
	$date = $_POST['date'];
	$s1 = $_POST['s1'];
	$s2 = $_POST['s2'];
	$s3 = $_POST['s3'];
	$dorm = $_SESSION['dorm'];

	$sql = "SELECT r.id FROM rooms r, dorms d WHERE d.dorm_name='$dorm' and r.room_number=$room;";
  	$result = mysql_query($sql);
  	$row = mysql_fetch_assoc($result);
  	
  	if($radio == 'green') $state = 1;
	else if($radio == 'yellow') $state = 2;
	else if($radio == 'red') $state = 3;
	else if($radio == 'gray') $state = 0;

	if($s1 == "empty") $state1 = 0;
	else if($s1 == "green") $state1 = 1;
	else if($s1 == "yellow") $state1 = 2;
	else if($s1 == "red") $state1 = 3;

	if($s2 == "empty") $state2 = 0;
	else if($s2 == "green") $state2 = 1;
	else if($s2 == "yellow") $state2 = 2;
	else if($s2 == "red") $state2 = 3;

	if($s3 == "empty") $state3 = 0;
	else if($s3 == "green") $state3 = 1;
	else if($s3 == "yellow") $state3 = 2;
	else if($s3 == "red") $state3 = 3;

	$id = $row['id'];
  	$sql = "UPDATE rooms SET state=$state, notes='$note', date='$date', state1='$state1',
  							 state2='$state2', state3='$state3' WHERE id=$id;";
	mysql_query($sql);
?>