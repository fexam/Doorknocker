<?php
  ob_start();
  session_start();
  include 'auth-class.php';

  // connect to database
  mysql_connect("localhost", "ryana3", "sturman");
  mysql_select_db("ryana3_test")or die("cannot select DB");
  
  // assign username and password variables
  $myusername = $_POST['username']; 
  $mypassword = $_POST['password']; 

  // protect against MySQL injection
  $myusername = stripslashes($myusername);
  $mypassword = stripslashes($mypassword);
  $myusername = mysql_real_escape_string($myusername);
  $mypassword = mysql_real_escape_string($mypassword);

  // create and execute query
  $sql = "SELECT * FROM members WHERE username='$myusername' and password='$mypassword';";
  $results = mysql_query($sql);
  $count = mysql_num_rows($results);

  // check if only one account was returned
  $auth = new Auth();
  if($count == 1)
  {
    $auth->login($myusername, $mypassword);
  }
  else
  {
  	$_SESSION['fail_login'] = TRUE;
  	header("location:../index.php");
  }
?>