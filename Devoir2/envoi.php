<?php
session_start();

 if (isset($_SESSION["connected_user"]))
 {
	 $id_from= $_REQUEST['id_from'];
$id_to= $_REQUEST['destinataire'];
$sujet= $_REQUEST['sujet'];
$corps= $_REQUEST['corps'];
?>
<?php
if(ctype_digit($id_from) && ctype_digit($id_to))
{
	$sujet_mod=htmlspecialchars($sujet);
	$corps_mod=htmlspecialchars($corps);

$db_connection_array = parse_ini_file("config/config.ini");
$mysqli=mysqli_connect($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
  if ($mysqli->connect_error) {
				echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
				}
				else {
				$req="INSERT INTO messages (id_user_to, id_user_from, sujet_msg, corps_msg) VALUES ('$id_to', '$id_from', '$sujet_mod', '$corps_mod');";
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
					  
 //header("Refresh:5; messagerie.php");


 ?>
<?php
 }
 else
 {
		  ?>
 <p>Une erreur est survenue, veuillez réessayer</p>
  </br></br><a href="vue_compte.php">Revenir à mon compte</a>
 <?php 
 }
 }
 else{
 ?>
 <p>Vous ne devriez pas être la sans être connecté veuillez vous connecter</p>
 </br></br><a href="index.php">Se connecter</a>
 <?php
 }
 ?>