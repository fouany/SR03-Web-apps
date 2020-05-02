<?php
session_start();
require_once('include.php');
require_once('config/config.php');
$mytoken=$_SESSION['mytoken'];
$now = time();

if ($now > $_SESSION['expire']) {
            session_destroy();
            echo "Votre session à expiré <a href='index.php'>reconnectez vous ici</a>";
        }

else if (isset($_SESSION["connected_user"]) && ($_SESSION["connected_user"]["profil_user"]=='EMPLOYE'))
{
	?>
	<p>Voici les informations personnelles du client demandé</p>
	<?php
	$client=$_REQUEST['client'];
	
	if(ctype_alnum($client)){
		$mysqli=mysqli_connect(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);

		if ($mysqli->connect_error) {
			echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
		}
		else {
			$req="SELECT * FROM users WHERE nom='$client'";
			if (!$result = $mysqli->query($req)) {
				echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
			} 
			else{
				$result=$mysqli->query($req);
			}
			$users=$result->fetch_assoc();
			?> 
			<div class="text-center">
			<?php 
			echo('Nom: ');
			echo($users['nom']);
			echo('</br>');
			echo('Prénom: ');
			echo($users['prenom']);
			echo('</br>');
			echo('Login: ');
			echo($users['login']);
			echo('</br>');
			echo('Numéro de compte: ');
			echo($users['numero_compte']);
			$num_cpte=$users['numero_compte'];
			echo('</br>');
			echo('Solde du compte: ');
			echo($users['solde_compte']."€");

		}
		?>

		<!doctype html>
		<html lang="fr">
		<head>
			<meta charset="utf-8">
			<title>Fiche client - Virement</title>
			<link rel="stylesheet" type="text/css" media="all"  href="css/mystyle.css" />
			<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		</head>
		<body>
			<form method='POST' action='virement.php'>
				<input type="hidden" name="mytoken" value="<?php echo $mytoken; ?>">
				<input class="form-field" type='hidden' name='numero_compte' value="<?php echo $num_cpte; ?>" />
				<p class="special">Montant du virement à effectuer depuis ce compte:</p>
				<input type='text' name='montant' />
				<p class="special">Numéro du compte bénéficiaire: </p>
				<input class="form-field" type='text' name='num_cpte_benef' />
				<button class="btn btn-primary">Effectuer un virement depuis ce compte</button>
			</form>
			</div>


			<a href="vue_compte.php">Retourner sur mon compte</a>
			<?php
		}
		else
		{
			?>
			<p>Vous n'êtes pas autorisé à faire ça, veuillez quitter cette page</p>
			<a href="index.php">Se connecter</a>
			<a href="vue_compte.php">Revenir à mon compte</a>
			<?php
		}
	}
	else{
		?>
		<p>L'accès n'est pas autorisé</p>
		<a href="index.php">Se connecter</a>
		<a class="deconnexion" href="vue_compte.php">Revenir à mon compte</a>

	</body>
	</html>
	<?php
}
?>