<?php

class Page
{
  private $pageName;

  public function __construct($page)
  {
    $this->pageName = $page;
  }

  public function redirect()
  {
    // redirects to specified page
    $name = "Location:" . $this->pageName;
    header($name);
  }
}

?>