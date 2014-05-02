<?php
  ob_start();
  session_start();
  include 'auth-class.php';
  include 'database-class.php';
  include_once 'page-class.php';
  include 'user-class.php';

  // connect to database
  $db = new Database();
  $user = new User();
  $db->connect();
  
  // assign username and password variables
  $myusername = $_POST['username']; 
  $mypassword = $_POST['password']; 

  // protect against MySQL injection
  $myusername = strip($myusername);
  $mypassword = strip($mypassword);

  $count = $user->findUserPassword($myusername, $mypassword);

  // check if only one account was returned
  if($count == 1)
  {
    $auth = new Auth();
    $auth->login($myusername);
  }
  else
  {
    $home = new Page("../index.php");
  	$_SESSION['fail_login'] = TRUE;
  	$home->redirect();
  }
?>