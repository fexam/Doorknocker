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

		function buildString($s)
		{
			$this->builder = $this->builder . $s . "\n";
		}

		function addToString($before, $s)
		{
			if($before == FALSE)
				$this->string = $this->string . $s;
			else
				$this->string = $s . $this->string;

			$this->builder = "";
		}

		function printString()
		{
			echo($this->string);
			$this->string = "";
		}

		function makeTable()
		{
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
				if($make_row == 1)
				{
					$make_row = 0;
					echo ("<tr>\n");
				}
				if($state == 1)
					$this->buildString("<td class=\"success room ");
				elseif($state == 2)
					$this->buildString("<td class=\"warning room ");
				elseif($state == 3)
					$this->buildString("<td class=\"danger room ");
				else
					$this->buildString("<td class=\"info room ");
				
				if($state < 4)
				{
					if($left == 1)
						$this->buildString("lsurround\" id=\"$number\" note=\"$note\" date=\"$date\">");
					else
						$this->buildString("rsurround\" id=\"$number\" note=\"$note\" date=\"$date\">");

					$this->buildString("<a data-toggle=\"modal\" href=\"#myModal\" onclick=\"setModal($number)\">");
					$this->buildString("<span>$number</span></a>");
					$this->buildString("<span class=\"previous ");
					if($state1 == 0) $this->buildString("empty\"></span>");
					else if($state1 == 1) $this->buildString("green\"></span>");
					else if($state1 == 2) $this->buildString("yellow\"></span>");
					else if($state1 == 3) $this->buildString("red\"></span>");

					$this->buildString("<span class=\"previous ");
					if($state2 == 0) $this->buildString("empty\"></span>");
					else if($state2 == 1) $this->buildString("green\"></span>");
					else if($state2 == 2) $this->buildString("yellow\"></span>");
					else if($state2 == 3) $this->buildString("red\"></span>");
					
					$this->buildString("<span class=\"previous ");
					if($state3 == 0) $this->buildString("empty\"></span>");
					else if($state3 == 1) $this->buildString("green\"></span>");
					else if($state3 == 2) $this->buildString("yellow\"></span>");
					else if($state3 == 3) $this->buildString("red\"></span>");
				}
				else
				{
					if($left == 1)
						$this->buildString("lnone\">");
					else
						$this->buildString("rnone\">");
				}

				$this->buildString("</td>");
				if($counter == 0)
				{
					$this->addToString(FALSE, $this->builder);
					$this->addToString($rotate , "<td class=\"hall\"></td>");
					if($left == 0)
						$left=1;
					else
						$left = 0;
					$counter = 1;
				}
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