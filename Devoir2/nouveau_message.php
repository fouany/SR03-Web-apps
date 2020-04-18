<?php
session_start();
$statut=$_SESSION["connected_user"]["profil_user"];
$id_from=$_SESSION["connected_user"]["id_user"];
?>

<FORM Method="post" action="envoi.php">
<input type="hidden" name="action" value="connexion">
<select name="destinataire" >
<?php
 
$db_connection_array = parse_ini_file("config/config.ini");
$mysqli=mysqli_connect($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
  if ($mysqli->connect_error) {
				echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
				}
				else {
					if($statut=='CLIENT')
					{
				    $req='SELECT * FROM users';
					}
				if (!$result = $mysqli->query($req)) {
				echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
			       } 
					else{
					$result=$mysqli->query($req);
					}
				while($users=$result->fetch_assoc())
				{
?>
 
<option value="<?php echo $users['nom'];?>"><?php echo $users['prenom'];?></option>
 
<?php
}
				}
?>
<p><input type="submit" value="Afficher la fiche client">

</input></p>
 
</select>
</FORM>
