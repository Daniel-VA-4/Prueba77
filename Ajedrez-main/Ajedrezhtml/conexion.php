<?php
$servidor = "localhost";
$usuario = "root";
$contraseña = "";
$base = "ajedrez";

$conn = new mysqli($servidor, $usuario, $contraseña,$base);

if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

$jugador1 = $_POST['jugador1'];
$piezas1 = $_POST['piezas1'];
$jugador2 = $_POST['jugador2'];
$piezas2 = $_POST['piezas2'];
$ganador = $_POST['ganador'];

$ganadorNombre = $ganador == "jugador1" ? $jugador1 : $jugador2;
$perdedorNombre = $ganador == "jugador1" ? $jugador2 : $jugador1;

$equipoGanador = $ganador == "jugador1" ? $piezas1 : $piezas2;
$equipoPerdedor = $ganador == "jugador1" ? $piezas2 : $piezas1;

$sql1 = "INSERT INTO jugadores (nombre, resultado, equipo, rival) VALUES ('$jugador1', '" . ($ganador == "jugador1" ? "ganador" : "perdedor") . "', '$piezas1', '$jugador2')";
$sql2 = "INSERT INTO jugadores (nombre, resultado, equipo, rival) VALUES ('$jugador2', '" . ($ganador == "jugador2" ? "ganador" : "perdedor") . "', '$piezas2', '$jugador1')";

if ($conn->query($sql1) === TRUE && $conn->query($sql2) === TRUE) {
    echo "Registro exitoso";
} else {
    echo "Error: " . $conn->error;
}

$conn->close();
