<?php
session_start();
$id=$_SESSION["connected_user"]["id_user"];
?>
Liste des messages:
</br>
 
<?php
 
$db_connection_array = parse_ini_file("config/config.ini");
$mysqli=mysqli_connect($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
  if ($mysqli->connect_error) {
				echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
				}
				else {
				$req="SELECT * FROM messages WHERE id_user_to='$id'";
				if (!$result = $mysqli->query($req)) {
				echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
			       } 
					else{
					$result=$mysqli->query($req);
					}
				while($mess=$result->fetch_assoc())
				{
					echo('Message de: ');
					echo($mess['id_user_from']);
					echo('</br>');
					echo('Sujet message: ');
					echo($mess['sujet_msg']);
					echo('</br>');
					echo('Contenu du message: ');
					echo($mess['corps_msg']);
					echo('</br>');
					echo('</br>');
				}
				}
?>


</br></br>
<a href="nouveau_message.php">Envoyer un message</a>