<?php
	mysql_connect("localhost", "ryana3", "sturman");
	mysql_select_db("ryana3_test");
	
	$dorm = $_GET['dorm'];
	$floor = (int)$_GET['floor'];
	$wing = $_GET['wing'];

	$stmt = mysql_query("SELECT r.room_number, r.state, r.date, r.notes
						  FROM dorms d, rooms r
						  WHERE d.dorm_name='$dorm' and d.dorm_id=r.dorm_id
						        and r.floor_num=$floor and r.wing='$wing'");
	
	if (mysql_num_rows($stmt) == 0) {
    		echo "No rows found, nothing to print. Exiting now.";
    		exit;
	}
	
	$rows = array();
	while($r = mysql_fetch_assoc($stmt))
	{
		$state = $r['state'];
		if($state != 4)
			$state = ($state-1 == -1 ? $state=3 : $state-1);
		$r['state'] = $state;
		$rows[] = $r;
	}
	echo json_encode($rows);
?>