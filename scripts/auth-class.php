<?php

include_once 'page-class.php';

class Auth
{
  private $main;
  private $home;

  public function __construct()
  {
    $this->main = new Page("../main.php");
    $this->home = new Page("../index.php");
  }

  public function isLoggedIn()
  {
    // make sure a username and password exists
    if(isset($_SESSION['username']))
      return true;
    else
      return false;
  }

  public function login($username)
  {
    // set the global username and password
    $_SESSION['username'] = $username;
    $this->main->redirect();
  }

  public function logout()
  {
    // erase all saved global data
    session_destroy();
    $this->home->redirect();
  }
}

?>