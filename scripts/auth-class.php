<?php

class Auth
{
  public function __construct()
  {
    // nothing to initialize
  }

  public function isLoggedIn()
  {
    if(isset($_SESSION['username']) && isset($_SESSION['password']))
      return true;
    else
      return false;
  }

  public function login($username, $password)
  {
    $_SESSION['username'] = $username;
    $_SESSION['password'] = $password;
    header("location:../main.php");
  }

  public function logout()
  {
    session_destroy();
    header("location:../index.php");
  }
}

?>