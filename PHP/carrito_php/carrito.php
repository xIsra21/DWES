<?php
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);
session_start();

$productos = [
    1 => ["nombre" => "Camiseta", "precio" => 15],
    2 => ["nombre" => "Pantalón", "precio" => 30],
    3 => ["nombre" => "Zapatillas", "precio" => 60]
];

if (!isset($_SESSION["carrito"])) {
    $_SESSION["carrito"] = [];
}

if (isset($_GET["id"])) {
    $id = (int)$_GET["id"];
    if (isset($productos[$id])) {
        $_SESSION["carrito"][$id] = ($_SESSION["carrito"][$id] ?? 0) + 1;
    }
}
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Carrito</title>
</head>
<body>
<h1>Carrito de la compra</h1>

<a href="index.php">⬅ Seguir comprando</a>

<?php
$total = 0;

if (empty($_SESSION["carrito"])) {
    echo "<p>El carrito está vacío</p>";
} else {
    echo "<ul>";
    foreach ($_SESSION["carrito"] as $id => $cantidad) {
        $subtotal = $productos[$id]["precio"] * $cantidad;
        $total += $subtotal;

        echo "<li>
            {$productos[$id]["nombre"]} 
            ({$cantidad}) - {$subtotal} €
            <a href='eliminar.php?id=$id'>❌</a>
        </li>";
    }
    echo "</ul>";
    echo "<h3>Total: $total €</h3>";
}
?>

<br>
<a href="vaciar.php">🗑 Vaciar carrito</a>

</body>
</html>

