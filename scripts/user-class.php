<?php

// make sure given data is database safe
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

	// return count of users found (should be 1 or 0)
	public function findUser($username)
	{
		// create and execute query
		$sql = "SELECT * FROM members WHERE username='$username';";
		$results = mysql_query($sql);
		$count = mysql_num_rows($results);

		return $count;
	}

	// find user based on a username and password (should be 1 or 0)
	public function findUserPassword($username, $password)
	{
		$salt = 'hello_1m_@_SaLT';
		$password = hash('sha256', $password . $salt);

		$sql = "SELECT * FROM members WHERE username='$username' and password='$password';";
		$results = mysql_query($sql);
		$count = mysql_num_rows($results);

		return $count;
	}
	
	// add user to members database
	public function addUser($username, $password)
	{
		$salt = 'hello_1m_@_SaLT';
		$password = hash('sha256', $password . $salt);

		$command = "INSERT INTO members (username, password) VALUES ('$username', '$password');";
		mysql_query($command);
	}

	// remove user from members database
	public function removeUser($username)
	{
		$command = "DELETE FROM members WHERE username='$username';";
		mysql_query($command);
	}
}

?>