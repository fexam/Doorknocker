<?php
	// connect to database
	mysql_connect("localhost", "ryana3", "sturman");
	mysql_select_db("ryana3_test");
	
	// given data from the URL
	$dorm = $_GET['dorm'];
	$floor = (int)$_GET['floor'];
	$wing = $_GET['wing'];

	// create and execute query
	$stmt = mysql_query("SELECT r.room_number, r.state, r.date, r.notes
						  FROM dorms d, rooms r
						  WHERE d.dorm_name='$dorm' and d.dorm_id=r.dorm_id
						        and r.floor_num=$floor and r.wing='$wing'");
	
	// make sure some data was found
	if (mysql_num_rows($stmt) == 0) {
    		echo "No rows found, nothing to print. Exiting now.";
    		exit;
	}
	
	// build data into array
	$rows = array();
	while($r = mysql_fetch_assoc($stmt))
	{
		$state = $r['state'];
		if($state != 4)
			$state = ($state-1 == -1 ? $state=3 : $state-1);
		$r['state'] = $state;
		$rows[] = $r;
	}

	// return data in JSON format for android app
	echo json_encode($rows);
?>