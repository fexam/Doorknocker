<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>DoorKnocker</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/offcanvas.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
    <div class="navbar navbar-fixed-top navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">DoorKnocker - Rooms</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cary Hall <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Floor 1 - (101 - 113)</a></li>
                  <li><a href="#">Floor 1 - (114 - 130)</a></li>
                  <li><a href="#">Floor 2 - (201 - 213)</a></li>
                  <li><a href="#">Floor 2 - (214 - 231)</a></li>
                  <li><a href="#">Floor 3 - (301 - 313)</a></li>
                  <li><a href="#">Floor 3 - (314 - 331)</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Crockett Hall <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Floor 1 - (101 - 113)</a></li>
                  <li><a href="#">Floor 1 - (114 - 130)</a></li>
                  <li><a href="#">Floor 2 - (201 - 213)</a></li>
                  <li><a href="#">Floor 2 - (214 - 229)</a></li>
                  <li><a href="#">Floor 3 - (301 - 313)</a></li>
                  <li><a href="#">Floor 3 - (314 - 331)</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Hall Hall <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Floor 1 - (101 - 113)</a></li>
                  <li><a href="#">Floor 1 - (114 - 130)</a></li>
                  <li><a href="#">Floor 2 - (201 - 213)</a></li>
                  <li><a href="#">Floor 2 - (214 - 231)</a></li>
                  <li><a href="#">Floor 3 - (301 - 313)</a></li>
                  <li><a href="#">Floor 3 - (314 - 331)</a></li>
                </ul>
              </li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </div><!-- /.navbar -->

    <div class="container">
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
          <div class="row">
            <div class="col-6 col-sm-6 col-lg-4">
              <table class="table">
              	<?php include 'scripts/rooms.php'; ?>
              </table>
            </div><!--/span-->
          </div><!--/row-->
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; DoorKnocker 2014</p>
      </footer>

    </div><!--/.container-->



    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/offcanvas.js"></script>
  </body>
</html>