<?php

require("../Interfaces/I_conexion.php");
require("../Interfaces/I_metodos.php");
require("../Actividades/Controlador/DTO/Usuario.php");

class DB_Helper implements I_conexion, I_metodos
{

	/**
	 * @return mixed
	 */
	public function conectarBD()
	{
		$conn = mysqli_connect(self::host, self::user, self::pass, self::db_name, self::puerto)
			or die("Error al conectar a la BBDD");
		return $conn;
	}

	/**
	 * @return mixed
	 */
	public function desconectarBD($conn)

	{
		try {
			mysqli_close($conn);
		} catch (Exception $e) {
			echo "Error al desconectar de la BBDD: " . $e->getMessage();
		}
	}


	public function ComprobarUsuario($conn, $usuarioDTO)
	{
		$usuarioComprobado = new Usuario();

		try {

			$sql = "select * from " . self::tabla_usuario . " where " . self::usuario_nombre . " = " . $usuarioDTO->getNombre();

			$resultado = mysqli_query($conn, $sql);

			mysqli_data_seek($resultado, 0);

			$usuarioDevuelto = mysqli_fetch_array($resultado);

			$usuarioComprobado = $usuarioDevuelto;

			mysqli_free_result($resultado);

			return $usuarioComprobado;
		} catch (Exception $e) {
			echo "Error al hacer la consulta en la BBDD: " . $e->getMessage();
			return $usuarioComprobado;
		}
	}
}
