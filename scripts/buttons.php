<?php
  session_start();

  echo("<div class=\"btn-group\">");
  if($_SESSION['floor'] > 1)
    echo("<a href=\"?floor=" . ($_SESSION['floor']-1) . "\" class=\"btn btn-danger\">Down Floor</a>");
  else
    echo("<button type\"button\" class=\"btn btn-danger\" disabled=\"disabled\">Down Floor</button>");
  echo("</div>");

  echo("<div class=\"btn-group\">");
  if($_SESSION['floor'] < $_SESSION['max'] && $_SESSION['floor'] >= 1)
    echo("<a href=\"?floor=" . ($_SESSION['floor']+1) . "\" class=\"btn btn-danger\">Up Floor</a>");
  else
    echo("<button type\"button\" class=\"btn btn-danger\" disabled=\"disabled\">Up Floor</button>");
  echo("</div>");

  echo("<div class=\"btn-group\">");
  if($_SESSION['floor'] >= 1)
    echo("<button type=\"button\" class=\"btn btn-danger dropdown-toggle\" data-toggle=\"dropdown\">");
  else
    echo("<button type=\"button\" class=\"btn btn-danger dropdown-toggle\" data-toggle=\"dropdown\" disabled=\"disabled\">");
  echo("More <span class=\"caret\"></span>");
  echo("</button>");
  echo("<ul class=\"dropdown-menu\">");
  echo("<li><a href=\"?rotate=" . !$_SESSION['rotate'] . "\">Rotate Floor</a></li>");
  $w = $_SESSION['wing'];
  if($w == $_SESSION['max_wing'])
    $w = A;
  else
    $w++;
  echo("<li><a href=\"?wing=" . $w . "\">Switch Wings</a></li>");
  echo("<li><a onclick=\"markAll()\">Mark All Rooms</a></li>");
  echo("</ul>");
  echo("</div>");
?>