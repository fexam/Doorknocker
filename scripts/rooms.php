<?php
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
			$stmt = $this->db->prepare('SELECT * FROM room_test');
			$stmt->execute();
			$stmt->bind_result($number, $state, $left);
			$first_time = 1;
			
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
					echo("left\">$number</td>\n");
					echo("<td></td>");
				}
				else
				{
					echo("right\">$number</td>\n");
					echo("</tr>\n");
					$first_time = 1;
				}
			}
			$stmt->close;
		}
	}
	$rooms = new FindRooms;
	$rooms->makeTable();
?>