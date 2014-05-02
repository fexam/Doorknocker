<?php

function strip($name)
{
	$name = stripslashes($name);
	$name = mysql_real_escape_string($name);

	return $name;
}

class User
{
	public function __construct()
	{
		// nothing to intialize
	}

	public function findUser($username)
	{
		// create and execute query
		$sql = "SELECT * FROM members WHERE username='$username';";
		$results = mysql_query($sql);
		$count = mysql_num_rows($results);

		return $count;
	}

	public function findUserPassword($username, $password)
	{
		$salt = 'hello_1m_@_SaLT';
		$password = hash('sha256', $password . $salt);

		$sql = "SELECT * FROM members WHERE username='$username' and password='$password';";
		$results = mysql_query($sql);
		$count = mysql_num_rows($results);

		return $count;
	}
	
	public function addUser($username, $password)
	{
		$salt = 'hello_1m_@_SaLT';
		$password = hash('sha256', $password . $salt);

		$command = "INSERT INTO members (username, password) VALUES ('$username', '$password');";
		mysql_query($command);
	}

	public function removeUser($username)
	{
		$command = "DELETE FROM members WHERE username='$username';";
		mysql_query($command);
	}
}

?>