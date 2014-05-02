<?php
  ob_start();
  session_start();
  include 'auth-class.php';
  include 'database-class.php';
  include_once 'page-class.php';
  include 'user-class.php';

  // Connect to database
  $db = new Database();
  $user = new User();
  $db->connect();
  
  // assign username and password variables
  $myusername = strtolower($_POST['username']); 
  $mypassword = $_POST['password']; 

  // protect against MySQL injection
  $myusername = strip($myusername);
  $mypassword = strip($mypassword);
  $count = $user->findUser($myusername);

  // check if no accounts were returned
  if($count == 0 && $myusername != "" && $mypassword != "")
  {
    // login and add to the database
    $auth = new Auth();
    $auth->login($myusername);
    $user->addUser($myusername, $mypassword);
  }
  else
  {
    $sign = new Page("../signup.php");
  	$_SESSION['fail_signup'] = TRUE;
  	$sign->redirect();
  }
?>