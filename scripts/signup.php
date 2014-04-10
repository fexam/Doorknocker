<?php
  ob_start();
  session_start();
  include 'auth-class.php';
  include 'database-class.php';
  include_once 'page-class.php';

  // Connect to database
  $db = new Database();
  $db->connect();
  
  // assign username and password variables
  $myusername = strtolower($_POST['username']); 
  $mypassword = $_POST['password']; 

  // protect against MySQL injection
  $myusername = stripslashes($myusername);
  $mypassword = stripslashes($mypassword);
  $myusername = mysql_real_escape_string($myusername);
  $mypassword = mysql_real_escape_string($mypassword);

  // create and execute query
  $sql = "SELECT * FROM members WHERE username='$myusername';";
  $results = mysql_query($sql);
  $count = mysql_num_rows($results);

  // check if no accounts were returned
  $auth = new Auth();
  $sign = new Page("../signup.php");
  $salt = 'hello_1m_@_SaLT';
  $password = hash('sha256', $mypassword . $salt);
  
  if($count == 0 && $myusername != "" && $mypassword != "")
  {
    // login and add to the database
    $auth->login($myusername, $mypassword);
    $command = "INSERT INTO members (username, password) VALUES ('$myusername', '$password');";
    mysql_query($command);
  }
  else
  {
  	$_SESSION['fail_signup'] = TRUE;
  	$sign->redirect();
  }
?>