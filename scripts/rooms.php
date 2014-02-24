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
						echo("left\">$number");
					else
						echo("none\"");
					if($state < 4)
					{
						echo("<p>");
						echo("<a data-toggle=\"modal\" href=\"#myModal\" class=\"btn btn-default\" href=\"#\" role=\"button\" room=$number>Details »</a>");
						echo("</p>");
					}
					echo("</td>");
					echo("<td></td>");
					$left = 0;
				}
				else
				{
					if($state < 4)
						echo("right\">$number");
					else
						echo("none\"");
					if($state < 4)
					{
						echo("<p>");
						echo("<a data-toggle=\"modal\" href=\"#myModal\" class=\"btn btn-default\" href=\"#\" role=\"button\" room=$number>Details »</a>");
						echo("</p>");
					}
					echo("</td>");
					echo("</tr>");
					$first_time = 1;
					$left = 1;
				}
			}
			$stmt->close;
		}
	}
	$rooms = new FindRooms;
	$rooms->makeTable();
?>