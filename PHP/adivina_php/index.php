<?php
	session_start();
	$min = 1;
	$max = 50;
	if(isset($_SESSION["aleatorio"])) {
		$aleatorio = $_SESSION["aleatorio"];
	}
	else {
		$aleatorio = rand($min, $max);
		$_SESSION["aleatorio"] = $aleatorio;
	}
	if(isset($_POST["numero"])) {
		if($_POST["numero"] > $max || $_POST["numero"] < $min) {
			$error = "El número debe estar entre $min y $max";
		} else {
			if($_POST["numero"] == $aleatorio) {
				$res = "Has acertado el numero";
				session_unset();
			} else {
				if($_POST["numero"] < $aleatorio) {
					$error = "No has acertado el numero, el numero secreto es mayor";
				}
				else {
					$error = "No has acertado el numero, el numero secreto es menor";
				}
			}
		}
	}
?>

<html>
<body>
<h1>Adivina mi numero</h1>
<form method="POST">
<?php
	if(isset($error)) {
		echo "<p>$error</p>";
	}
	if(isset($res)) {
		echo "<p>$res</p>";
		echo "<button>Volver a jugar</button>";
	}
	else {
?>
<label>Dime un numero entre <?php echo $min ?> y <?php echo $max ?></label><br>
<input type="number" name="numero" />
<input type="submit" value="Jugar" />
</form>
<?php
}
?>
</body>
</html>