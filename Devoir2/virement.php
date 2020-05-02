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
$now = time();

if ($now > $_SESSION['expire']) {
            session_destroy();
            echo "Votre session à expiré <a href='index.php'>reconnectez vous ici</a>";
        }//permet de détruire la session si celle-ci est ouverte depuis plus de 30 minutes sans action

else if (isset($_SESSION["connected_user"])) //on vérifie qu'une session est bien en cours avant d'afficher quoi que ce soit pour éviter une attaque par vol de session
{
	$debiteur= $_REQUEST['numero_compte'];
	$receveur=$_REQUEST['num_cpte_benef'];
	$montant=$_REQUEST['montant'];
	?>
	<?php
	if(ctype_digit($debiteur) && ctype_digit($receveur) && ctype_digit($montant)) //on vérifié que les entrées sont au bon format sinon on refuse l'action
	{
		if (!isset($_REQUEST['mytoken']) || $_REQUEST['mytoken'] != $_SESSION['mytoken']) {
			  echo("<p>Erreur, une attaque csrf à été détectée vous allez être redirigé et l'incident sera consigné</p>");
              header("Refresh:5; vue_compte.php");
          } else {
		function getMySqliConnection() {
			return new mysqli(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);
		}
		function get_dest($num_cpte)   //fonction vérifiant si le numéro de compte entré correspond bien à un utilisateur avant d'effectuer le virement
		{
			$mysqli = getMySqliConnection();

			$dest=0;

			if ($mysqli->connect_error) {
				echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
			} else {
				//
				$stmt = $mysqli->prepare("SELECT id_user from users WHERE numero_compte=?");   //requête paramétrée pour éviter les injections de code
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
				     
      $stmt = $mysqli->prepare("update users set solde_compte=solde_compte+? where numero_compte=?");    //requête paramétrée pour éviter les injections de code
      $stmt->bind_param("ds", $montant, $receveur); 
      $stmt->execute(); 
      $stmt->close();

      $stmt = $mysqli->prepare("update users set solde_compte=solde_compte-? where numero_compte=?");   //requête paramétrée pour éviter les injections de code
      $stmt->bind_param("ds", $montant, $debiteur); 
      $stmt->execute();
      $stmt->close();
  
      $mysqli->close();
  }



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
