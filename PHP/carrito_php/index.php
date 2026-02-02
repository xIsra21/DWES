<?php
session_start();

$productos = [
    1 => ["nombre" => "Camiseta", "precio" => 15],
    2 => ["nombre" => "Pantalón", "precio" => 30],
    3 => ["nombre" => "Zapatillas", "precio" => 60]
];
?>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tienda</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<h1>Tienda Online</h1>

<a href="carrito.php">🛒 Ver carrito</a>

<?php foreach ($productos as $id => $producto): ?>
    <div class="producto">
        <h3><?= $producto["nombre"] ?></h3>
        <p>Precio: <?= $producto["precio"] ?> €</p>
        <a href="carrito.php?id=<?= $id ?>">Añadir al carrito</a>
    </div>
<?php endforeach; ?>

</body>
</html>

