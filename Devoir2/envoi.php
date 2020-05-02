<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Devoir2</title>
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

else if (isset($_SESSION["connected_user"])){
	
	$id_from= $_REQUEST['id_from'];
	$id_to= $_REQUEST['destinataire'];
	$sujet= $_REQUEST['sujet'];
	$corps= $_REQUEST['corps'];
	?>
	<?php
	if(ctype_digit($id_from) && ctype_digit($id_to))
	{
		$car_interdits = array("'","\"",";","%","<",">"); 
		$sujet_mod=str_replace($car_interdits, "", $sujet);
		$corps_mod=str_replace($car_interdits, "", $corps);

		$mysqli=mysqli_connect(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);
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

 		header("Refresh:3; messagerie.php");
		
	}
	else
	{
		?>
		<p>Une erreur est survenue, veuillez réessayer.</p>
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