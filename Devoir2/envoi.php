<?php
session_start();
$id_from= $_REQUEST['id_from'];
$id_to= $_REQUEST['destinataire'];
$sujet= $_REQUEST['sujet'];
$corps= $_REQUEST['corps'];
?>
<?php


$db_connection_array = parse_ini_file("config/config.ini");
$mysqli=mysqli_connect($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
  if ($mysqli->connect_error) {
				echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
				}
				else {
				$req="INSERT INTO messages (id_user_to, id_user_from, sujet_msg, corps_msg) VALUES ('$id_to', '$id_from', '$sujet', '$corps');";
				if (!$result = $mysqli->query($req)) {
				echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
			       }
				  else{
					  ?>
					  <p>Message bien envoyé, vous allez être redirigé vers votre messagerie!</p>
					  <?php
				  }
				}				
				      $mysqli->close();
					  
 header("Refresh:5; messagerie.php");


 ?>