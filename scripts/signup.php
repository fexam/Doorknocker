<?php
  ob_start();
  session_start();
  include 'auth-class.php';

  // Connect to database
  mysql_connect("localhost", "ryana3", "sturman");
  mysql_select_db("ryana3_test")or die("cannot select DB");
  
  // assign username and password variables
  $myusername = strtolower($_POST['username']); 
  $mypassword = $_POST['password']; 

  // Protect against MySQL injection
  $myusername = stripslashes($myusername);
  $mypassword = stripslashes($mypassword);
  $myusername = mysql_real_escape_string($myusername);
  $mypassword = mysql_real_escape_string($mypassword);

  // Create and execute query
  $sql = "SELECT * FROM members WHERE username='$myusername';";
  $results = mysql_query($sql);
  $count = mysql_num_rows($results);

  $auth = new Auth();
  if($count == 0 && $myusername != "" && $mypassword != "")
  {
    $auth->login($myusername, $mypassword);
    $command = "INSERT INTO members (username, password) VALUES ('$myusername', '$mypassword');";
    mysql_query($command);
  }
  else
  {
  	$_SESSION['fail_signup'] = TRUE;
  	header("location:../signup.php");
  }
?>