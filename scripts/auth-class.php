<?php

class Auth
{
  public function __construct()
  {
    // nothing to initialize
  }

  public function isLoggedIn()
  {
    // make sure a username and password exists
    if(isset($_SESSION['username']) && isset($_SESSION['password']))
      return true;
    else
      return false;
  }

  public function login($username, $password)
  {
    // set the global username and password
    $_SESSION['username'] = $username;
    $_SESSION['password'] = $password;
    header("location:../main.php");
  }

  public function logout()
  {
    // erase all saved global data
    session_destroy();
    header("location:../index.php");
  }
}

?>