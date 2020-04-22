<?php
session_start();

 if (isset($_SESSION["connected_user"]))
 {
	 $debiteur= $_REQUEST['numero_compte'];
$receveur=$_REQUEST['num_cpte_benef'];
$montant=$_REQUEST['montant'];
?>
<?php
if(ctype_digit($debiteur) && ctype_digit($receveur) && ctype_digit($montant))
{
function getMySqliConnection() {
  $db_connection_array = parse_ini_file("config/config.ini");
  return new mysqli($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
}
function get_dest($num_cpte) {
  $mysqli = getMySqliConnection();

  $dest=0;

  if ($mysqli->connect_error) {
      echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
  } else {
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
  }

  return $dest;
}
$dest=get_dest($receveur);
if($dest==1)
{
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

 header("Refresh:5; vue_compte.php");
}
else
{
 ?>
 <p>Le destinataire entré est inconnu, veuillez réessayer avec un numéro valide</p>
 </br></br><a href="vue_compte.php">retour au compte</a>
 <?php
 }
}
 else
 {
	  ?>
 <p>Le numéro de compte ou le montant ne sont pas au bon format, veuillez réessayer</p>
  </br></br><a href="vue_compte.php">Revenir à mon compte</a>
 <?php
 }
 }
 else{
 ?>
 <p>Vous ne devriez pas être la sans être connecté veuillez vous connecter</p>
 </br></br><a href="index.php">Se connecter</a>
 <?php
 }
 ?>
 