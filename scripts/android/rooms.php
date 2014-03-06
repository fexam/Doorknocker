<?php
	mysql_connect("localhost", "ryana3", "sturman");
	mysql_select_db("ryana3_test");
	
	$dorm = $_GET['dorm'];
	$floor = (int)$_GET['floor'];
	$wing = $_GET['wing'];
	$rotate = $_GET['rotate'];

	if($rotate == "true")
	{
		$stmt = mysql_query("SELECT r.room_number, r.state, r.id
							  FROM dorms d, rooms r
							  WHERE d.dorm_name='$dorm' and d.dorm_id=r.dorm_id
							        and r.floor_num=$floor and r.wing='$wing'
							   ORDER BY r.id DESC");
	}
	else
	{
		$stmt = mysql_query("SELECT r.room_number, r.state, r.id
							 FROM dorms d, rooms r
							 WHERE d.dorm_name='$dorm' and d.dorm_id=r.dorm_id
							       and r.floor_num=$floor and r.wing='$wing'
							 ORDER BY r.id ASC");
	}
	
	if (mysql_num_rows($stmt) == 0) {
    		echo "No rows found, nothing to print so am exiting";
    		exit;
	}
	
	$rows = array();
	while($r = mysql_fetch_assoc($stmt))
	{
		$rows[] = $r;
	}
	print json_encode($rows);
?>