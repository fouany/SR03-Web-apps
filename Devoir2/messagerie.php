<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>Messagerie</title>
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
		//permet de détruire la session si celle-ci est ouverte depuis plus de 30 minutes sans action

else if (isset($_SESSION["connected_user"]))  //on vérifie qu'une session est bien en cours avant d'afficher quoi que ce soit pour éviter une attaque par vol de session
	{
		$id=$_SESSION["connected_user"]["id_user"];
		?>

		<?php
		if(is_numeric($id)) //on vérifié que les entrées sont au bon format sinon on refuse l'action
		{
			function getMySqliConnection() {
				return new mysqli(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);
			}

			function get_nom($ids) {                                                                                       //fonction permettant de récupérer le nom correspondant a l'id de l'expéditeur, pour un affichage plus user-friendly
				$mysqli = getMySqliConnection();

				$user = array();

				if ($mysqli->connect_error) {
					echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
				} else {
					$req="SELECT nom,prenom from users WHERE id_user='$ids'";
					if (!$result = $mysqli->query($req)) {
						echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
					} else {
						$user=$result->fetch_assoc();

						$result->free();
					}
					$mysqli->close();
				}

				return $user;
			}
			?>
			<h1>Liste des messages</h1>	 
		<?php
		$mysqli=mysqli_connect(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);                                              // On affiche tous les messages de l'utilisateur dont la session est en cours un par un 
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
				$user=get_nom($mess['id_user_from']);
				echo('Message de: ');
				echo($user['nom']);
				echo(' ');
				echo($user['prenom']);
				echo('</br>');
				echo('Sujet message: ');
				echo($mess['sujet_msg']);
				echo('</br>');
				echo('Contenu du message: ');
				echo($mess['corps_msg']);
				echo('</br>');
				echo('</br>');
			}
			$result->free();
		}
		?>


	<a href="nouveau_message.php">Envoyer un message</a>

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
	<p>Vous ne devriez pas être la sans être connecté veuillez vous connecter</p>
	<a href="index.php">Se connecter</a>
<?php
}
?>