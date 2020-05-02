<?php
session_start();
require_once('include.php');
require_once('config/config.php');
?>

<!doctype html>
<html lang="fr">
<head>
	<meta charset="utf-8">
	<title>Connexion</title>
	<link rel="stylesheet" type="text/css" media="all"  href="css/mystyle.css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
	<p>Vous allez être redirigé vers la page de connexion</p>
</body>
</html>

<?php
session_destroy();
header("Refresh:3; index.php");
?>