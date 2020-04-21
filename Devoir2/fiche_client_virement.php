<?php
session_start();
?>
<p>Voici les informations personnelles du client demandé
</br></br>
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
					echo('nom: ');
					echo($users['nom']);
					echo('</br>');
					echo('prénom: ');
					echo($users['prenom']);
					echo('</br>');
					echo('login: ');
					echo($users['login']);
					echo('</br>');
					echo('numéro de compte: ');
					echo($users['numero_compte']);
					$num_cpte=$users['numero_compte'];
					echo('</br>');
					echo('solde du compte: ');
					echo($users['solde_compte']);
					echo('</br>');
					echo('</br>');
				}
?>
<form method='POST' action='virement.php'>
                <input type='hidden' name='numero_compte' value="<?php echo $num_cpte; ?>" />
				</br>Montant du virement à effectuer depuis ce compte
				</br><input type='text' name='montant' />
				</br>Numéro du compte bénéficiaire
				</br><input type='text' name='num_cpte_benef' />
                </br><button>Effectuer un virement depuis ce compte</button>
           </form>



</br></br><a href="vue_compte.php">retourner sur mon compte</a>