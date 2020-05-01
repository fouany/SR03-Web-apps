<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>Virement client</title>
	<link rel="stylesheet" type="text/css" media="all"  href="css/mystyle.css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>


	<?php
	session_start();
	require_once('include.php');
	require_once('config/config.php');
	$mytoken=$_SESSION['mytoken'];

	if (isset($_SESSION["connected_user"]))
	{
		$num_cpte=$_SESSION["connected_user"]["numero_compte"];
		?>
		<div class="text-center">
		<form method='POST' action='virement.php'>
			<input type="hidden" name="mytoken" value="<?php echo $mytoken; ?>">
			<input class="form-field" type='hidden' name='numero_compte' value="<?php echo $num_cpte; ?>" />
			<p class="special">Montant du virement à effectuer depuis votre compte</p>
			<input class="form-field" type='text' name='montant' />
			<p class="special">Numéro du compte bénéficiaire</p>
			<input class="form-field" type='text' name='num_cpte_benef' />
			<button class="btn btn-primary">Effectuer un virement vers le destinataire</button>
		</form>
		</div>

		<a href="vue_compte.php">Retourner sur mon compte</a>
		<?php
	}
	else{
		?>
		<p>L'accès n'est pas autorisé. Veuillez vous connecter.</p>
		<a href="index.php">Se connecter</a>
		<?php
	}
	?>