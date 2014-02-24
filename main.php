<?php
  session_start();
  if(!isset($_SESSION['username']) || !isset($_SESSION['password']))
    header("location:index.php");
?>
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
          <img src="images/door.png" width="40" height="45">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand">DoorKnocker - Rooms</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="scripts/logout.php">Sign Out</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cary Hall <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Floor 1 - (101 - 115)</a></li>
                  <li><a href="#">Floor 1 - (114 - 130)</a></li>
                  <li><a href="#">Floor 2 - (201 - 215)</a></li>
                  <li><a href="#">Floor 2 - (214 - 231)</a></li>
                  <li><a href="#">Floor 3 - (301 - 315)</a></li>
                  <li><a href="#">Floor 3 - (314 - 331)</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Crockett Hall <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Floor 1 - (101 - 115)</a></li>
                  <li><a href="#">Floor 1 - (114 - 130)</a></li>
                  <li><a href="#">Floor 2 - (201 - 215)</a></li>
                  <li><a href="#">Floor 2 - (214 - 229)</a></li>
                  <li><a href="#">Floor 3 - (301 - 315)</a></li>
                  <li><a href="#">Floor 3 - (314 - 331)</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Hall Hall <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Floor 1 - (101 - 115)</a></li>
                  <li><a href="#">Floor 1 - (114 - 130)</a></li>
                  <li><a href="#">Floor 2 - (201 - 215)</a></li>
                  <li><a href="#">Floor 2 - (214 - 231)</a></li>
                  <li><a href="#">Floor 3 - (301 - 315)</a></li>
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
              <h3>Floor Name</h3>
              <div class="btn-group btn-group-justified" style="padding-bottom:18px">
                <div class="btn-group">
                  <button type="button" class="btn btn-danger">Down Floor</button>
                </div>
                <div class="btn-group">
                  <button type="button" class="btn btn-danger">Up Floor</button>
                </div>
                <div class="btn-group">
                  <button type="button" class="btn btn-danger">Blacklist</button>
                </div>
              </div>
              <table class="table">
              	<?php include 'scripts/rooms.php'; ?>
              </table>
              <button type="button" class="btn btn-block btn-danger">Switch Wings</button>
            </div>
          </div><!--/span-->
        </div><!--/row-->
      </div><!--/span-->
      <footer>
        <p>&copy; DoorKnocker 2014</p>
      </footer>
    </div><!--/container-->

	<div class="modal in fv-modal-stack" id="myModal" aria-hidden="false" style="display: none; z-index: 1050;">
	  <div class="modal-dialog">
	    <div class="modal-content">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h4 class="modal-title">Room Number Here</h4>
		  </div>
		    <div class="container"></div>
		    <div class="modal-body">
		      <div class="radio">
		        <label>
		          <input type="radio" name="optionsRadios" id="radio1" value="green" checked>
		          Green - Considering
		        </label>
		      </div>
		      <div class="radio">
		        <label>
		          <input type="radio" name="optionsRadios" id="radio2" value="yellow">
		          Yellow - Neutral
		        </label>
		      </div>
		      <div class="radio">
		        <label>
		          <input type="radio" name="optionsRadios" id="radio3" value="red">
		          Red - Not Interested
		        </label>
		      </div>
		      <div class="radio">
		        <label>
		          <input type="radio" name="optionsRadios" id="radio4" value="gray">
		          Gray - Needs Visiting
		        </label>
		      </div>
		      <br>
		      <p>Last Modified: 3/12/14 2:01PM</p>
		      <p>User Modified: Matt</p>
		    </div>
		    <div class="modal-footer">
		      <a href="#" data-dismiss="modal" class="btn btn-default">Close</a>
		      <a href="#" data-dismiss="modal" class="btn btn-danger">Save changes</a>
		    </div>
		  </div>
		</div>
	</div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/offcanvas.js"></script>
  </body>
</html>