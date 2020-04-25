<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Connexion</title>
  <link rel="stylesheet" type="text/css" media="all"  href="css/mystyle.css" />
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
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