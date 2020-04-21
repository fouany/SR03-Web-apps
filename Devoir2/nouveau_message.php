<?php
session_start();
$statut=$_SESSION["connected_user"]["profil_user"];
$id_from=$_SESSION["connected_user"]["id_user"];
?>
Choisissez le destinataire dans la liste:
<br>
<FORM Method="post" action="envoi.php" id='message'>
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
				    $req="SELECT * FROM users WHERE profil_user='EMPLOYE' AND id_user!='$id_from'";
					}
					else
					{
					$req="SELECT * FROM users WHERE id_user!='$id_from'";
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
 
<option value="<?php echo $users['id_user'];?>"><?php echo $users['prenom']; echo' '; echo $users['nom'];?></option>
 
<?php
}
				}
?>
</br>
<input type='hidden' name='id_from' value="<?php echo $id_from;?>"/>
</br>
<input type="text" name='sujet' placeholder='Sujet du message'>
</br>
<textarea name="corps" form="message" cols='70' rows='30'>Entrez votre message ici</textarea>
</br><input type="submit" value="Envoyer le message">

</input></p>
 
</select>
</FORM>

</br></br><a href="vue_compte.php">retourner sur mon compte</a>