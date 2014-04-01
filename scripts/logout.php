<?php
  session_start();
  include 'auth-class.php';
  
  $auth = new Auth();
  $auth->logout();
?>