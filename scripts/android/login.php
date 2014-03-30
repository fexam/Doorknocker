<?php
  // connect to database
  mysql_connect("localhost", "ryana3", "sturman");
  mysql_select_db("ryana3_test");
  
  // assign username and password variables
  $myusername = $_GET['username']; 
  $mypassword = $_GET['password']; 

  // protect against MySQL injection
  $myusername = stripslashes($myusername);
  $mypassword = stripslashes($mypassword);
  $myusername = mysql_real_escape_string($myusername);
  $mypassword = mysql_real_escape_string($mypassword);

  // create and execute query
  $sql = "SELECT * FROM members WHERE username='$myusername' and password='$mypassword';";
  $results = mysql_query($sql);
  $count = mysql_num_rows($results);

  // make sure only one user was found and return 'true'
  if($count == 1)
  {
    echo("true");
  }
  else
  {
  	echo("false");
  }
?>