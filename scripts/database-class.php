<?php
include_once 'config.php';

class Database
{
	private $username = "ryana3";
	private $password = "sturman";
	private $db = "ryana3_test";

	public function __construct()
	{
		// nothing to intialize
	}

	public function connect()
	{
		// check if database is online or return 'bad'
		mysql_connect("localhost", $this->username, $this->password) or die("bad");
		mysql_select_db($this->db)or die("cannot select DB");
	}

	public function testConnect()
	{
		// check if database is online or return 'bad'
		mysql_connect("localhost", $this->username, $this->password) or die("bad");
	}

	public function seedData($dorm_id, $dorm_name)
    {
      // Connect to database
      $db = new Database();
      $db->connect();

      // create and execute query
      echo("id: " . $dorm_id . " with name: " . $dorm_name);
      $sql = "INSERT INTO `dorms`(`dorm_id`, `dorm_name`) VALUES ($dorm_id, '$dorm_name');";
      mysql_query($sql);

      $sql = "INSERT INTO `rooms`(`id`, `room_number`, `state`, `dorm_id`, `floor_num`, `wing`, `date`, `notes`, `state1`, `state2`, `state3`) VALUES
      (1000, 101, 0, $dorm_id, 1, 'A', '', '', 0, 0, 0);";
      mysql_query($sql);

      echo("<br>Built database<br>");
    }

    public function removeData($dorm_id)
    {
      $sql = "DELETE FROM `dorms` WHERE dorm_id=$dorm_id;";
      mysql_query($sql);

      $sql = "DELETE FROM `rooms` WHERE dorm_id=$dorm_id;";
      mysql_query($sql);
      echo("<br><br>Cleaned database");
    }
}

if(isset($_POST['connect']))
{
	$db = new Database();
	$db->testConnect();
}

?>