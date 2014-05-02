<?php
	ob_start();
	session_start();
	include 'database-class.php';
  include 'floor-class.php';
	
	// connect to database
  $db = new Database();
  $floor = new Floor();
  $db->connect();

	$room = $_POST['room'];
	$date = $_POST['date'];
	$dorm = $_SESSION['dorm'];

	// query to find 'id' needed to update the specfifc room 	
  $dorm_id = $floor->findDormID($dorm);
  $id = $floor->findRoomID($dorm_id, $room);

	// state number for a red room
	$state = 3;

	$floor->saveState($id, $state, $date);
?>