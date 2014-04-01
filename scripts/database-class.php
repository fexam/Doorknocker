<?php

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
}

if(isset($_POST['connect']))
{
	$db = new Database();
	$db->testConnect();
}

?>