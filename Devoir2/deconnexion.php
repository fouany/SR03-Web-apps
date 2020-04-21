<?php
session_start();
?>
</br>
<p>Vous allez être redirigé vers la page de connexion</p>
<?php

 unset($_SESSION["connected_user"]);
 header("Refresh:5; index.php");
?>