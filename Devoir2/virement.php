<?php
session_start();
$debiteur= $_REQUEST['numero_compte'];
$receveur=$_REQUEST['num_cpte_benef'];
$montant=$_REQUEST['montant'];
$url_redirect="accueil.php";
?>
<?php
echo($debiteur);

$db_connection_array = parse_ini_file("config/config.ini");
$mysqli=mysqli_connect($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
  if ($mysqli->connect_error) {
				echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
				}
				else {
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
				}
				
				      $mysqli->close();
					  
 header("Location: $url_redirect");
?>
 