<?php
  session_start();
  include 'scripts/auth-class.php';
  $auth = new Auth();
  
  // If logged in, go to the main page
  if($auth->isLoggedIn())
  {
    header("location:main.php");
  }
?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DoorKnocker Signin</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/signin.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <div class="container">
      <form class="form-signin" role="form" action='scripts/login.php' method='post'>
        <h2 class="form-signin-heading">Please sign in</h2>
        <?php
          // Display error message if login has failed
          if(isset($_SESSION['fail_login']) && $_SESSION['fail_login'] == TRUE)
          {
            unset($_SESSION['fail_login']);
            echo('<h5>Sorry, Invalid Login Credentials</h5>');
          }
        ?>
        <input name="username" type="text" class="form-control" placeholder="Username" required autofocus>
        <input name="password" type="password" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-danger btn-block" type="submit">Log In</button>
      </form>
      <form class="form-signup">
        <a href="signup.php" class="btn btn-lg btn-primary btn-block">Sign Up</a>
	    </form>
    </div>
  </body>
</html>
