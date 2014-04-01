<?php
  session_start();
  include 'scripts/auth-class.php';
  include_once 'scripts/page-class.php';

  $auth = new Auth();
  $home = new Page("main.php");

  // If logged in, go to the main page
  if($auth->isLoggedIn())
  {
    $home->redirect();
  }
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DoorKnocker Signup</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/signin.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <div class="container">
      <form class="form-signin" role="form" action='scripts/signup.php' method='post'>
        <h2 class="form-signin-heading">Welcome!</h2>
        <?php
          // Display error message if login has failed
          if(isset($_SESSION['fail_signup']) && $_SESSION['fail_signup'] == TRUE)
          {
            unset($_SESSION['fail_signup']);
            echo('<h5>Sorry, Username Already in Use</h5>');
          }
        ?>
        <input name="username" type="text" class="form-control" placeholder="Username" required autofocus>
        <input name="password" type="password" class="form-control" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
        <p style="margin: 10px 0 0"><a href="../index.php">&laquo Back to Login</a></p>
      </form>
    </div>
  </body>
</html>
