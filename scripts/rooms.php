<?php
	session_start();
	class FindRooms {
		private $db;
		private $builder;
		private $string;

		// Constructor
		function __construct()
		{
			$this->db = new mysqli('localhost', 'ryana3', 'sturman', 'ryana3_test');
			$this->db->autocommit(false);
			$this->builder = "";
			$this->string = "";
		}
		
		// Desctructor
		function __destruct()
		{
			$this->db->close();
		}

		// builds a row of the table in string form
		function buildString($s)
		{
			$this->builder = $this->builder . $s;
		}

		// adds the row to the beginning or end of the current string
		// and resets the builder string
		function addToString($before, $s)
		{
			if($before == FALSE)
				$this->string = $this->string . $s;
			else
				$this->string = $s . $this->string;

			$this->builder = "";
		}

		// print string and reset to an empty string
		function printString()
		{
			echo($this->string);
			$this->string = "";
		}

		// main function to build the table
		function makeTable()
		{
			// query all data to a specific dorm, floor, and wing
			$dorm = $_SESSION['dorm'];
			$floor = $_SESSION['floor'];
			$wing = $_SESSION['wing'];
			$rotate = $_SESSION['rotate'];

			$stmt = $this->db->prepare("SELECT r.id, r.room_number, r.state, r.notes, r.date, r.state1, r.state2, r.state3
										 FROM dorms d, rooms r
										 WHERE d.dorm_name='$dorm' and d.dorm_id=r.dorm_id
										       and r.floor_num=$floor and r.wing='$wing'");

			$stmt->execute();
			$stmt->bind_result($id, $number, $state, $note, $date, $state1, $state2, $state3);
			$make_row = 1;
			$counter = 0;
			if($rotate == TRUE)
				$left = 0;
			else
				$left = 1;

			while($stmt->fetch())
			{
				// print html row beginning character
				if($make_row == 1)
				{
					$make_row = 0;
					echo ("<tr>\n");
				}

				// determin green, yellow, red, or grey color
				if($state == 1)
					$this->buildString("<td class=\"success room ");
				elseif($state == 2)
					$this->buildString("<td class=\"warning room ");
				elseif($state == 3)
					$this->buildString("<td class=\"danger room ");
				else
					$this->buildString("<td class=\"info room ");
				
				// this is a room
				if($state < 4)
				{
					// determine left or right of the wing
					if($left == 1)
						$this->buildString("lsurround\" id=\"$number\" note=\"$note\" date=\"$date\">");
					else
						$this->buildString("rsurround\" id=\"$number\" note=\"$note\" date=\"$date\">");

					// determine the color for the three history circles
					$this->buildString("<a data-toggle=\"modal\" href=\"#myModal\" onclick=\"setModal($number)\">");
					$this->buildString("<span>$number</span></a>\n");
					$this->buildString("<span class=\"previous ");
					if($state1 == 0) $this->buildString("empty\"></span>\n");
					else if($state1 == 1) $this->buildString("green\"></span>\n");
					else if($state1 == 2) $this->buildString("yellow\"></span>\n");
					else if($state1 == 3) $this->buildString("red\"></span>\n");

					$this->buildString("<span class=\"previous ");
					if($state2 == 0) $this->buildString("empty\"></span>\n");
					else if($state2 == 1) $this->buildString("green\"></span>\n");
					else if($state2 == 2) $this->buildString("yellow\"></span>\n");
					else if($state2 == 3) $this->buildString("red\"></span>\n");
					
					$this->buildString("<span class=\"previous ");
					if($state3 == 0) $this->buildString("empty\"></span>\n");
					else if($state3 == 1) $this->buildString("green\"></span>\n");
					else if($state3 == 2) $this->buildString("yellow\"></span>\n");
					else if($state3 == 3) $this->buildString("red\"></span>\n");
				}
				// this is an empty room or bathroom
				else
				{
					if($left == 1)
						$this->buildString("lnone\">");
					else
						$this->buildString("rnone\">");
				}

				// print html column ending character
				$this->buildString("</td>\n");

				// (left -> right) print the room then the hall
				// (right -> left) print the hall then the room
				if($counter == 0)
				{
					$this->addToString(FALSE, $this->builder);
					$this->addToString($rotate , "<td class=\"hall\"></td>\n");
					if($left == 0)
						$left=1;
					else
						$left = 0;
					$counter = 1;
				}
				// finished row and add it to the whole string
				else
				{
					$this->addToString($rotate, $this->builder);
					$this->printString();
					echo("</tr>");
					$make_row = 1;
					if($left == 0)
						$left=1;
					else
						$left = 0;
					$counter = 0;
				}
			}
			$stmt->close();
		}
	}
	$rooms = new FindRooms;
	$rooms->makeTable();
?>