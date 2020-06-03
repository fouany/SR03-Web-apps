<?php 
	session_start();
	require_once('include.php');
	require_once('config/config.php');
	//$_SESSION['nbr_essais'] = 0;                       // pour reset le compteur d'essais en cas d'erreur
	
?>
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Connexion</title>
  <link rel="stylesheet" type="text/css" media="all"  href="css/mystyle.css" />
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<?php
if ($_SESSION['nbr_essais']<5)
	{
	$_SESSION['start'] = time();
	$_SESSION['expire'] = $_SESSION['start'] + (30 * 60); //la session expire après 30 minutes	
?>
<body>
  <header>
    <h1>Connexion</h1>
  </header>
  
  <section>
    <div class="login-page">
      <div class="form">
        <form method="POST" action="verif.php">
          <input class="form-field" type="text" name="login" placeholder="Login"/>
          <input class="form-field" type="password" name="mdp" placeholder="Mot de passe"/>
          <div class="text-center"> <button class="btn btn-primary">Login</button> </div>
        </form>
      </div>
    </div>

  </section>

</body>
</html>

<?php
	}
	else{
		?>
		
		<h1>Nombre de tentatives dépassé veuillez contacter l'administrateur du serveur pour régler le soucis </h1>
		
		<?php
	}
?>