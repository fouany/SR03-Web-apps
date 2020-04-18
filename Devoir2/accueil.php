<?php
session_start();
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
                  <label>Solde : </label><span><?php echo $_SESSION["connected_user"]["solde_compte"];?> &euro;</span>
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
	echo('tu es un employé tu peux accéder aux fiches des clients');
	echo '<a href="fiche_client.php">fiche des clients</a>';
}
else 
	echo('Tu es un client');

?>
        


</body>
</html>
