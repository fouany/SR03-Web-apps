<?php
session_start();
require_once('include.php');
require_once('config/config.php');
if ((isset($_SESSION["connected_user"]))  && ($_SESSION["connected_user"]["profil_user"]=='EMPLOYE'))
{
	?>

	<!doctype html>
	<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Fiche client</title>
		<link rel="stylesheet" type="text/css" media="all"  href="css/mystyle.css" />
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	</head>
	<link rel="stylesheet" type="text/css" media="all"  href="css/mystyle.css" />

	<p>Sélectionnez un client dans la liste pour voir sa fiche personnelle.</p>

	<FORM class="form-fiche-client" Method="post" action="fiche_client_virement.php">
		<input type="hidden" name="action" value="connexion">
		<select name="client" >

			<option>--------Client--------</option>

			<?php

			$mysqli=mysqli_connect(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);
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
			<p><input type="submit" value="Afficher la fiche client"> </input></p>

		</select>
	</FORM>

</br></br><a href="vue_compte.php">Retourner sur mon compte</a>
<?php
}
else{
	?>
	<p>L'accès n'est pas autorisé. Si vous n'êtes pas un employé veuillez quitter cette page</p>
	<a href="index.php">Se connecter</a>
	<a href="vue_compte.php">Revenir à mon compte</a>
	<?php
}
?>