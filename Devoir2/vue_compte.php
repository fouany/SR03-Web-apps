<?php
session_start();
require_once('include.php');
require_once('config/config.php');

  $mytoken = bin2hex(random_bytes(128));
  $_SESSION["mytoken"] = $mytoken;

?>
<?php
if (isset($_SESSION["connected_user"]))
{
  $id=$_SESSION["connected_user"]["id_user"];
  function getMySqliConnection() {
    return new mysqli(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);
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
    <link rel="stylesheet" type="text/css" media="all"  href="css/mystyle.css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
  </head>
  <body>
    <header>
      <h2><?php echo $_SESSION["connected_user"]["prenom"];?> <?php echo $_SESSION["connected_user"]["nom"];?> - Mon compte</h2>
    </header>

    <div class="fieldset">
      <div class="field">
        <label>N° compte : </label><span><?php echo " " .$_SESSION["connected_user"]["numero_compte"];?></span>
      </div>
      <div class="field">
      <label>Solde : </label><span><?php echo " " .$solde['solde_compte'];?> &euro;</span>
      </div>
    </div>

    <a class="lien-utiles" href="virement_client.php">Effectuer un virement</a>

    <a lass="lien-utiles" href="messagerie.php">Accéder à votre messagerie</a>

    <?php
    if($_SESSION["connected_user"]["profil_user"]=="EMPLOYE")
    {
      ?>
      <p>En tant qu'employé, vous avez accès aux fiches des clients</p>
      <a href="fiche_client.php">Fiches des clients</a>

      <?php
    }
    ?>

    <a class="deconnexion" href="deconnexion.php">Se déconnecter</a>   


  </body>
  </html>
  <?php
}
else{
 ?>
 <p>L'accès n'est pas autorisé. Veuillez vous connecter.</p>
 <a href="index.php">Se connecter</a>
 <?php
}
?>