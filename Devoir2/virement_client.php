<?php
session_start();

 if (isset($_SESSION["connected_user"]))
 {
	 $num_cpte=$_SESSION["connected_user"]["numero_compte"];
?>

<form method='POST' action='virement.php'>
</br><input type='hidden' name='numero_compte' value="<?php echo $num_cpte; ?>" />
</br>Montant du virement à effectuer depuis votre compte
</br><input type='text' name='montant' />
</br></br>Numéro du compte bénéficiaire
</br><input type='text' name='num_cpte_benef' />
</br></br><button>Effectuer un virement vers le destinataire</button>
</form>

</br></br><a href="vue_compte.php">retourner sur mon compte</a>
<?php
 }
 else{
 ?>
 <p>Vous ne devriez pas être la sans être connecté veuillez vous connecter</p>
 </br></br><a href="index.php">Se connecter</a>
 <?php
 }
 ?>