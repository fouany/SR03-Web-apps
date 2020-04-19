<?php

session_start();

			$pwd=$_REQUEST['mdp'];
			$login=$_REQUEST['login'];
          if (!isset($login) || !isset($pwd) || $pwd == "" || $login == "") {
              $url_redirect = "connexion.php"; 
          } else {
			    $db_connection_array = parse_ini_file("config/config.ini");
				$mysqli=mysqli_connect($db_connection_array['DB_HOST'], $db_connection_array['DB_USER'], $db_connection_array['DB_PASSWD'], $db_connection_array['DB_NAME']);
          
                if ($mysqli->connect_error) {
				echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
				}
				else {
				$req="select id_user,login,mot_de_passe,profil_user,nom,prenom,numero_compte,solde_compte from users where login='$login' and mot_de_passe='$pwd'";
				if (!$result = $mysqli->query($req)) {
				echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
			       } 	
				else {
			$result=$mysqli->query($req);
            $utilisateur = $result->fetch_assoc();
			$result->free();
			if (empty($utilisateur))
			{
				$url_redirect = "connexion.php";
			}
			else
			{
				$_SESSION["connected_user"] = $utilisateur;
                $url_redirect = "index.php";
			}
      $mysqli->close();
			}

              }
		  }
 header("Location: $url_redirect");

 
 ?>