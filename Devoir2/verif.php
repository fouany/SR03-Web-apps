<?php

session_start();
require_once('include.php');
require_once('config/config.php');

$pwd=$_REQUEST['mdp'];
$login=$_REQUEST['login'];
if (!isset($login) || !isset($pwd) || $pwd == "" || $login == "" || !ctype_alnum($pwd) || !ctype_alnum($login)) {
	$url_redirect = "index.php"; 
} else {
	$mysqli=mysqli_connect(DB_HOST, DB_USER, DB_PASSWD,DB_NAME);
	$car_interdits = array("'", "\"", ";","%","<",">"); 
	$loginf=str_replace($car_interdits, "", $login);
	$pwdf=str_replace($car_interdits, "", $pwd);
	
	
	if ($mysqli->connect_error) {
		echo 'Erreur connection BDD (' . $mysqli->connect_errno . ') '. $mysqli->connect_error;
	}
	else {
		$stmt = $mysqli->prepare("select id_user,profil_user,nom,prenom,numero_compte from users where login=? and mot_de_passe=?");  
		$stmt->bind_param("ss", $loginf, $pwdf); 
		$stmt->execute();
		$stmt->bind_result($id_user,$profil_user,$nom,$prenom,$numero_compte); 
      if ($stmt->fetch())
	  {
          $utilisateur = array ("nom" => $nom,
                                "prenom" => $prenom,
                                "login" => $loginf,
                                "id_user" => $id_user,
                                "numero_compte" => $numero_compte,
                                "profil_user" => $profil_user);
		$_SESSION["connected_user"] = $utilisateur;
		$url_redirect = "vue_compte.php";
      } 
	  else {
         $url_redirect = "index.php";
      }
      $stmt->close();
      
      $mysqli->close();
	  
	 /*
		
		$req="select id_user,profil_user,nom,prenom,numero_compte from users where login='$loginf' and mot_de_passe='$pwdf'";
		if (!$result = $mysqli->query($req)) {
			echo 'Erreur requête BDD ['.$req.'] (' . $mysqli->errno . ') '. $mysqli->error;
		} 	
		else {
			$result=$mysqli->query($req);
			$utilisateur = $result->fetch_assoc();
			$result->free();
			if (empty($utilisateur))
			{
				$url_redirect = "index.php";
			}
			else
			{
				$_SESSION["connected_user"] = $utilisateur;
				$url_redirect = "vue_compte.php";
			}
			$mysqli->close();
			
		}
		*/
	}

	}

header("Location: $url_redirect");


?>
