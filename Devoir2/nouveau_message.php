<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>Nouveau message</title>
	<link rel="stylesheet" type="text/css" media="all"  href="css/mystyle.css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>



	<?php
	session_start();
	require_once('include.php');
	require_once('config/config.php');
	$now = time();
	
	if ($now > $_SESSION['expire']) {
            session_destroy();
            echo "Votre session à expiré <a href='index.php'>reconnectez vous ici</a>";
        }

else if (isset($_SESSION["connected_user"]))
	{
		$statut=$_SESSION["connected_user"]["profil_user"];
		$id_from=$_SESSION["connected_user"]["id_user"];
		if(ctype_alpha($statut) && is_numeric($id_from))
		{
			?>

			<p>Choisissez le destinataire dans la liste:</p>
			<div class="text-center">

			<FORM Method="post" action="envoi.php" id='message'>
				<input type="hidden" name="action" value="connexion">
				<select name="destinataire" >
					<?php

					$mysqli=mysqli_connect(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);
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
					<input  type='hidden' name='id_from' value="<?php echo $id_from;?>"/>
					<input class="form-field" type="text" name='sujet' placeholder='Sujet du message'>
					<textarea class="form-field" name="corps" form="message" cols='70' rows='10'>Entrez votre message ici</textarea>
					<input class="btn btn-primary" type="submit" value="Envoyer le message"/>
					</div>

			</select>
		</FORM>

		<a href="vue_compte.php">Retourner sur mon compte</a>
		<?php
	}
	else
	{
		?>
		<p>Une erreur est survenue, veuillez réessayer</p>
		<a href="vue_compte.php">Revenir à mon compte</a>
		<?php 
	}
}
else{
	?>
	<p>L'accès n'est pas autorisé. Veuillez vous connecter.</p>
	<a href="index.php">Se connecter</a>
	<?php
}
?>