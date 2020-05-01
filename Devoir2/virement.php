<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Connexion</title>
  <link rel="stylesheet" type="text/css" media="all"  href="css/mystyle.css" />
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<?php
session_start();
require_once('include.php');
require_once('config/config.php');

if (isset($_SESSION["connected_user"]))
{
	$debiteur= $_REQUEST['numero_compte'];
	$receveur=$_REQUEST['num_cpte_benef'];
	$montant=$_REQUEST['montant'];
	?>
	<?php
	if(ctype_digit($debiteur) && ctype_digit($receveur) && ctype_digit($montant))
	{
		if (!isset($_REQUEST['mytoken']) || $_REQUEST['mytoken'] != $_SESSION['mytoken']) {
			  echo("<p>Erreur, une attaque csrf à été détectée vous allez être redirigé et l'incident sera consigné</p>");
              header("Refresh:5; vue_compte.php");
          } else {
		function getMySqliConnection() {
			return new mysqli(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);
		}
		function get_dest($num_cpte) {
			$mysqli = getMySqliConnection();

			$dest=0;

			if ($mysqli->connect_error) {
				echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
			} else {
				//
				$stmt = $mysqli->prepare("SELECT id_user from users WHERE numero_compte=?");  
		$stmt->bind_param("i", $num_cpte); 
		$stmt->execute();
		$stmt->bind_result($id_user); 
      if ($stmt->fetch())
	  {
        $dest=1;  
      } 

      $stmt->close();
      
      $mysqli->close();
			}
	  //
	  /*
				$req="SELECT id_user from users WHERE numero_compte='$num_cpte'";
				if (!$result = $mysqli->query($req)) {
					echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
				} else {
					$result=$mysqli->query($req);
					$val=$result->fetch_assoc();
					if(!$val){
					}
					else{
						$dest=1;
					}

					$result->free();
				}
				$mysqli->close();
				*/
			

			return $dest;
		}
		}
		$dest=get_dest($receveur);
		if($dest==1)
		{
			$mysqli=mysqli_connect(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);
			if ($mysqli->connect_error) {
				echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
			}
			else {
				     
      $stmt = $mysqli->prepare("update users set solde_compte=solde_compte+? where numero_compte=?");  
      $stmt->bind_param("ds", $montant, $receveur); 
      $stmt->execute(); 
      $stmt->close();

      $stmt = $mysqli->prepare("update users set solde_compte=solde_compte-? where numero_compte=?");  
      $stmt->bind_param("ds", $montant, $debiteur); 
      $stmt->execute();
      $stmt->close();
  
      $mysqli->close();
  }
					/*
				$req1="update users set solde_compte=solde_compte+$montant where numero_compte='$receveur'";
				$req2="update users set solde_compte=solde_compte-$montant where numero_compte='$debiteur'";
				if (!$result = $mysqli->query($req1)) {
					echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
				}
				if (!$result = $mysqli->query($req2)) {
					echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
				}
				else
				{
					echo('Virement effectué');
				}
				*/


			echo('Virement effectué');
			header("Refresh:5; vue_compte.php");
		}
		else
		{
			?>
			<p>Le destinataire entré est inconnu, veuillez réessayer avec un numéro valide.</p>
			<a href="vue_compte.php">Retour au compte</a>
		<?php
	}
}

	
else
{
	?>
	<p>Le numéro de compte ou le montant ne sont pas au bon format, veuillez réessayer.</p>
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
