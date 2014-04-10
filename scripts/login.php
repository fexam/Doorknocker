<?php
  ob_start();
  session_start();
  include 'auth-class.php';
  include 'database-class.php';
  include_once 'page-class.php';

  // connect to database
  $db = new Database();
  $db->connect();
  
  // assign username and password variables
  $myusername = $_POST['username']; 
  $mypassword = $_POST['password']; 

  // protect against MySQL injection
  $myusername = stripslashes($myusername);
  $mypassword = stripslashes($mypassword);
  $myusername = mysql_real_escape_string($myusername);
  $mypassword = mysql_real_escape_string($mypassword);

  $salt = 'hello_1m_@_SaLT';
  $password = hash('sha256', $mypassword . $salt);
  
  // create and execute query
  $sql = "SELECT * FROM members WHERE username='$myusername' and password='$password';";
  $results = mysql_query($sql);
  $count = mysql_num_rows($results);

  // check if only one account was returned
  $auth = new Auth();
  $home = new Page("../index.php");
  if($count == 1)
  {
    $auth->login($myusername, $mypassword);
  }
  else
  {
  	$_SESSION['fail_login'] = TRUE;
  	$home->redirect();
  }
?>