<?php
  session_start();
  if(!isset($_SESSION['username']) || !isset($_SESSION['password']))
    header("location:index.php");
  elseif(isset($_GET['switch']))
  {
    $_SESSION['dorm'] = $_GET['dorm'];
    $_SESSION['floor'] = $_GET['floor'];
    $_SESSION['max'] = $_GET['max'];
    $_SESSION['wing'] = $_GET['wing'];
    $_SESSION['max_wing'] = $_GET['max_wing'];
    header("location:main.php");
  }
  elseif(isset($_GET['floor']))
  {
  	$_SESSION['floor'] = $_GET['floor'];
  	header("location:main.php");
  }
  elseif(isset($_GET['wing']))
  {
  	$_SESSION['wing'] = $_GET['wing'];
  	header("location:main.php");
  }
  elseif(isset($_GET['rotate']))
  {
  	$_SESSION['rotate'] = $_GET['rotate'];
  	header("location:main.php");
  }
  elseif(!isset($_SESSION['dorm']))
  {
  	// Give it default values
  	$_SESSION['dorm'] = "No Dorm Selected";
  	$_SESSION['floor'] = -1;
  	$_SESSION['max'] = 0;
  	$_SESSION['wing'] = "Z";
  	$_SESSION['rotate'] = false;
  	$_SESSION['max_wing'] = "Z";
  }
?>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/offcanvas.css" rel="stylesheet">
    <link href="css/room.layout.css" rel="stylesheet">
    <title>DoorKnocker</title>

    <script type="text/javascript">
    	function setModal(number, state)
	    {
	    	var element = document.getElementById("popover_room");
	    	element.innerText = "Room " + number;

	    	if(state == 0)
				document.getElementById("radio4").checked = true;
	    	else if(state == 1)
	    		document.getElementById("radio1").checked = true;
	    	else if(state == 2)
				document.getElementById("radio2").checked = true;
	    	else if(state == 3)
				document.getElementById("radio3").checked = true;
	    }
    </script>
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
          <button class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <p class="navbar-brand" data-toggle="collapse" data-target=".navbar-collapse">DoorKnocker</p>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="scripts/logout.php">Sign Out</a></li>
            <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cary Hall <b class="caret"></b></a>
	          <ul class="dropdown-menu">
	            <li><a href="?switch=true&dorm=Cary%20Hall&floor=1&max=3&wing=A&max_wing=B">Floor 1 - (101 - 115)</a></li>
	            <li><a href="?switch=true&dorm=Cary%20Hall&floor=1&max=3&wing=B&max_wing=B">Floor 1 - (114 - 130)</a></li>
	            <li><a href="?switch=true&dorm=Cary%20Hall&floor=2&max=3&wing=A&max_wing=B">Floor 2 - (201 - 215)</a></li>
	            <li><a href="?switch=true&dorm=Cary%20Hall&floor=2&max=3&wing=B&max_wing=B">Floor 2 - (214 - 231)</a></li>
	            <li><a href="?switch=true&dorm=Cary%20Hall&floor=3&max=3&wing=A&max_wing=B">Floor 3 - (301 - 315)</a></li>
	            <li><a href="?switch=true&dorm=Cary%20Hall&floor=3&max=3&wing=B&max_wing=B">Floor 3 - (314 - 331)</a></li>
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
        </div><!-- /.nav-header -->
      </div><!-- /.container -->
    </div><!-- /.navbar -->

    <div class="container">
      <div class="row row-offcanvas row-offcanvas-right">
        <div class="col-xs-12 col-sm-9">
          <div class="row">
            <div class="col-6 col-sm-6 col-lg-4">
              <?php
              	echo("<h3>" . $_SESSION['dorm'] . "</h3>");
              ?>
              <div class="btn-group btn-group-justified" style="padding-bottom:15px">
                <div class="btn-group">
                  <?php
                    if($_SESSION['floor'] > 1)
                  	  echo("<a href=\"?floor=" . ($_SESSION['floor']-1) . "\" class=\"btn btn-danger\">Down Floor</a>");
                    else
                      echo("<button type\"button\" class=\"btn btn-danger\" disabled=\"disabled\">Down Floor</button>");
                  ?>
                </div>
                <div class="btn-group">
                  <?php
                    if($_SESSION['floor'] < $_SESSION['max'] && $_SESSION['floor'] >= 1)
                  	  echo("<a href=\"?floor=" . ($_SESSION['floor']+1) . "\" class=\"btn btn-danger\">Up Floor</a>");
                  	else
                  	  echo("<button type\"button\" class=\"btn btn-danger\" disabled=\"disabled\">Up Floor</button>");
                  ?>
                </div>
                <div class="btn-group">
                  <?php
                    if($_SESSION['floor'] >= 1)
                   	  echo("<button type=\"button\" class=\"btn btn-danger dropdown-toggle\" data-toggle=\"dropdown\">");
                   	else
                   	  echo("<button type=\"button\" class=\"btn btn-danger dropdown-toggle\" data-toggle=\"dropdown\" disabled=\"disabled\">");
                  ?>
                  	More
                  	<span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu">
                  	<?php
                  	  echo("<li><a href=\"?rotate=" . !$_SESSION['rotate'] . "\">Rotate Floor</a></li>");
                  	?>
                  	<li><a href="#">Mark All Rooms</a></li>
                  </ul>
                </div>
              </div>
              <table class="table">
              	<?php include 'scripts/rooms.php'; ?>
              </table>
              <?php
              	if($_SESSION['floor'] >= 1)
              	{
              	  $w = $_SESSION['wing'];
              	  if($w == $_SESSION['max_wing'])
              	  	$w = A;
              	  else
              	  	$w++;
                  echo("<a href=\"?wing=" . $w . "\" class=\"btn btn-block btn-danger\">Switch Wings</a>");
              	}
                else
              	  echo("<button type=\"button\" class=\"btn btn-block btn-danger\" disabled=\"disabled\">Switch Wings</button>");
              ?>
            </div>
          </div><!--/span-->
        </div><!--/row-->
      </div><!--/span-->
      <footer>
        <p>&copy; DoorKnocker 2014</p>
      </footer>
    </div><!--/container-->

	<div class="modal fade" id="myModal" aria-hidden="false" style="display: none; z-index: 1050;">
	  <div class="modal-dialog">
	    <div class="modal-content">
		  <div class="modal-header">
		    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		    <h4 class="modal-title" id="popover_room">Room Number Here</h4>
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
		    <label>Notes:</label>
			<input name="notes" type="text" id="notes">
	        <br>
	        <br>
	        <p id="modified">Last Modified: Matt - 3/12/14</p>
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