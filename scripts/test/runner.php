<?php
  ob_start();

  include '../database-class.php';
  include '../user-class.php';
  include '../floor-class.php';
  include_once 'config.php';

  $db = new Database();
  $user = new User();
  $floor = new Floor();
  $db->connect();
  $db->seedData($dorm_id, $dorm_name);

  echo("<br>===== Testing adding a user =====");
  echo("<br>Looking for username [" . $username . "] with " . $user->findUser($username) . " results --> should be 0");
  $user->addUser($username, $password);
  echo("<br>Looking for username [" . $username . "] with " . $user->findUser($username) . " result --> should be 1");
  $user->removeUser($username);
  echo("<br>Looking for username [" . $username . "] with " . $user->findUser($username) . " results --> should be 0");
  
  echo("<br><br>===== Testing saving a room =====");
  $id = $floor->findRoomID($dorm_id, 101);
  echo("<br>Looking for room [101] with id " . $id . " --> should be 1000");
  $state = $floor->getState($id);
  echo("<br>Room 101 has state [" . $state . "] --> should be 0");
  $floor->saveState($id, 2, '10/2/2014');
  $state = $floor->getState($id);
  echo("<br>Room 101 changed state [" . $state . "] --> should be 2");
  $db->removeData($dorm_id);
?>