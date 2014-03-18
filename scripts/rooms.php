<?php
	session_start();
	class FindRooms {
		private $db;
		
		// Constructor
		function __construct() {
			$this->db = new mysqli('localhost', 'ryana3', 'sturman', 'ryana3_test');
			$this->db->autocommit(false);
		}
		
		// Desctructor
		function __destruct() {
			$this->db->close();
		}
		
		function makeTable() {
			$dorm = $_SESSION['dorm'];
			$floor = $_SESSION['floor'];
			$wing = $_SESSION['wing'];
			$rotate = $_SESSION['rotate'];

			if($rotate == true)
			{
				$stmt = $this->db->prepare("SELECT r.id, r.room_number, r.state, r.notes, r.date, r.state1, r.state2, r.state3
											 FROM dorms d, rooms r
											 WHERE d.dorm_name='$dorm' and d.dorm_id=r.dorm_id
											       and r.floor_num=$floor and r.wing='$wing'
											 ORDER BY r.id DESC");
			}
			else
			{
				$stmt = $this->db->prepare("SELECT r.id, r.room_number, r.state, r.notes, r.date, r.state1, r.state2, r.state3
											 FROM dorms d, rooms r
											 WHERE d.dorm_name='$dorm' and d.dorm_id=r.dorm_id
											       and r.floor_num=$floor and r.wing='$wing'
											 ORDER BY r.id ASC");
			}
			$stmt->execute();
			$stmt->bind_result($id, $number, $state, $note, $date, $state1, $state2, $state3);
			$first_time = 1;
			$left = 1;

			while($stmt->fetch())
			{
				if($first_time == 1)
				{
					$first_time = 0;
					echo ("<tr>\n");
				}
				if($state == 1)
					echo("<td class=\"success ");
				elseif($state == 2)
					echo("<td class=\"warning ");
				elseif($state == 3)
					echo("<td class=\"danger ");
				else
					echo("<td class=\"info ");
				
				if($left == 1)
				{
					if($state < 4)
					{
						echo("lsurround\" id=\"$number\" note=\"$note\" date=\"$date\">");
						echo("<a data-toggle=\"modal\" href=\"#myModal\" onclick=\"setModal($number)\">");
						echo("<span>$number</span></a>");
						echo("<span class=\"previous ");
						if($state1 == 0) echo("empty\"></span>");
						else if($state1 == 1) echo("green\"></span>");
						else if($state1 == 2) echo("yellow\"></span>");
						else if($state1 == 3) echo("red\"></span>");

						echo("<span class=\"previous ");
						if($state2 == 0) echo("empty\"></span>");
						else if($state2 == 1) echo("green\"></span>");
						else if($state2 == 2) echo("yellow\"></span>");
						else if($state2 == 3) echo("red\"></span>");
						
						echo("<span class=\"previous ");
						if($state3 == 0) echo("empty\"></span>");
						else if($state3 == 1) echo("green\"></span>");
						else if($state3 == 2) echo("yellow\"></span>");
						else if($state3 == 3) echo("red\"></span>");
					}
					else
						echo("lnone\">");

					echo("</td>");
					echo("<td class=\"hall\"></td>");
					$left = 0;
				}
				else
				{
					if($state < 4)
					{
						echo("rsurround\" id=\"$number\" note=\"$note\" date=\"$date\">");
						echo("<a data-toggle=\"modal\" href=\"#myModal\" onclick=\"setModal($number)\">");
						echo("<span>$number</span></a>");
						echo("<span class=\"previous ");
						if($state1 == 0) echo("empty\"></span>");
						else if($state1 == 1) echo("green\"></span>");
						else if($state1 == 2) echo("yellow\"></span>");
						else if($state1 == 3) echo("red\"></span>");

						echo("<span class=\"previous ");
						if($state2 == 0) echo("empty\"></span>");
						else if($state2 == 1) echo("green\"></span>");
						else if($state2 == 2) echo("yellow\"></span>");
						else if($state2 == 3) echo("red\"></span>");
						
						echo("<span class=\"previous ");
						if($state3 == 0) echo("empty\"></span>");
						else if($state3 == 1) echo("green\"></span>");
						else if($state3 == 2) echo("yellow\"></span>");
						else if($state3 == 3) echo("red\"></span>");
					}
					else
						echo("rnone\">");

					echo("</td>");
					echo("</tr>");
					$first_time = 1;
					$left = 1;
				}
			}
			$stmt->close();
		}
	}
	$rooms = new FindRooms;
	$rooms->makeTable();
?>