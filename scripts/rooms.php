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
			$stmt = $this->db->prepare("SELECT r.room_number, r.state
											 FROM dorms d, rooms r
											 WHERE d.dorm_name='$dorm' and d.dorm_id=r.dorm_id
											       and r.floor_num=$floor and r.wing='$wing'");
			$stmt->execute();
			$stmt->bind_result($number, $state);
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
						echo("lsurround\">");
						echo("<a data-toggle=\"modal\" href=\"#myModal\" room=\"$number\">");
						echo("<span>$number</span></a>");
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
						echo("rsurround\">");
						echo("<a data-toggle=\"modal\" href=\"#myModal\" room=\"$number\">");
						echo("<span>$number</span></a>");
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