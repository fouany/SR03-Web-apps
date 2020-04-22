<?php
session_start();
 if ((isset($_SESSION["connected_user"]))  && ($_SESSION["connected_user"]["profil_user"]=='EMPLOYE'))
 {
?>

<p>Sélectionnez un client dans la liste pour voir sa fiche personnelle.
</br></br>

<FORM Method="post" action="fiche_client_virement.php">
<input type="hidden" name="action" value="connexion">
<select name="client" >
 
<option>--------Client--------</option>
 
<?php
 
$db_connection_array = parse_ini_file("config/config.ini");
$mysqli=mysqli_connect($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
  if ($mysqli->connect_error) {
				echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
				}
				else {
				$req='SELECT * FROM users';
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

</br></br><a href="vue_compte.php">retourner sur mon compte</a>
<?php
 }
 else{
 ?>
 <p>Vous ne devriez pas être la sans être connecté, ou si vous n'êtes pas un employé veuillez quitter cette page</p>
 </br></br><a href="index.php">Se connecter</a>
 </br></br><a href="vue_compte.php">Revenir à mon compte</a>
 <?php
 }
 ?>