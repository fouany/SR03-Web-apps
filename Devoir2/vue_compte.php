<?php
session_start();

?>
<?php
 if (isset($_SESSION["connected_user"]))
 {
	 $id=$_SESSION["connected_user"]["id_user"];
function getMySqliConnection() {
  $db_connection_array = parse_ini_file("config/config.ini");
  return new mysqli($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
}

function get_solde($ids) {
  $mysqli = getMySqliConnection();

  $solde = 0;

  if ($mysqli->connect_error) {
      echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
  } else {
      $req="SELECT solde_compte from users WHERE id_user='$ids'";
      if (!$result = $mysqli->query($req)) {
          echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
      } else {
		$solde=$result->fetch_assoc();
	  
          $result->free();
      }
      $mysqli->close();
  }

  return $solde;
}
$solde=get_solde($id);
?>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Mon Compte</title>
</head>
<body>
    <header>
        <h2><?php echo $_SESSION["connected_user"]["prenom"];?> <?php echo $_SESSION["connected_user"]["nom"];?> - Mon compte</h2>
    </header>

          <div class="fieldset">
              <div class="fieldset_label">
                  <span>Votre compte</span>
              </div>
              <div class="field">
                  <label>N° compte : </label><span><?php echo $_SESSION["connected_user"]["numero_compte"];?></span>
              </div>
              <div class="field">
                  <label>Solde : </label><span><?php echo $solde['solde_compte'];?> &euro;</span>
              </div>
          </div>
		  </br>
		  </br>
		  <a href="virement_client.php">Effectuer un virement</a>
		  </br>
		  </br>
		  <a href="messagerie.php">Accéder à votre messagerie</a>
		  </br>
		  </br>
<?php
if($_SESSION["connected_user"]["profil_user"]=="EMPLOYE")
{
?>
	<p></br>Tu es un employé tu peux accéder aux fiches des clients</p>
	</br>
	</br><a href="fiche_client.php">fiches des clients</a>

	<?php
}
?>

     </br></br><a href="deconnexion.php">Se déconnecter</a>   


</body>
</html>
 <?php
 }
 else{
 ?>
 <p>Vous ne devriez pas être la sans être connecté veuillez vous connecter</p>
 </br></br><a href="index.php">Se connecter</a>
 <?php
 }
 ?>