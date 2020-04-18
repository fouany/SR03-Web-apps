<?php
session_start();
?>
<?php
$client=$_REQUEST['client'];
 $db_connection_array = parse_ini_file("config/config.ini");
 $mysqli=mysqli_connect($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
          
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
					echo($users['nom']);
					echo('</br>');
					echo($users['prenom']);
					echo('</br>');
					echo($users['login']);
					echo('</br>');
					echo($users['numero_compte']);
					$num_cpte=$users['numero_compte'];
					echo('</br>');
					echo($users['solde_compte']);
					echo('</br>');
					echo('</br>');
				}

 echo("<form method='POST' action='virement.php'>");
                echo("<input type='hidden' name='numero_compte' value=$num_cpte />");
				echo("Montant du virement à effectuer depuis ce compte");
				echo("<input type='text' name='montant' />");
				echo("Numéro du compte bénéficiaire");
				echo("<input type='text' name='num_cpte_benef' />");
                echo('<button>Effectuer un virement depuis ce compte</button>');
            echo('</form>');
?>