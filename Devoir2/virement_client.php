<?php
session_start();
$num_cpte=$_SESSION["connected_user"]["numero_compte"];
?><?php

 echo("<form method='POST' action='virement.php'>");
                echo("<input type='hidden' name='numero_compte' value='$num_cpte' />");
				echo("Montant du virement à effectuer depuis votre compte");
				echo("<input type='text' name='montant' />");
				echo("Numéro du compte bénéficiaire");
				echo("<input type='text' name='num_cpte_benef' />");
                echo('<button>Effectuer un virement vers le destinataire</button>');
            echo('</form>');
?>