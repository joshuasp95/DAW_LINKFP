<?php

include_once("I_conexion.php");
include_once("I_metodos.php");
include_once(" ../DTO/Usuario.php");
include_once(" ../DTO/Proyecto.php");
include_once(" ../DTO/Tarea.php");
include_once(" ../DTO/V_Tareas.php");

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


	public function comprobarUsuario($conn, $usuarioDTO)
	{
		$usuarioComprobado = new Usuario();

		try {

			$sql = "select * from " . self::tabla_usuario . " where " . self::usuario_nombre . " = '"
				. $usuarioDTO->getNombre() . "' and " . self::usuario_pass . " = '" . $usuarioDTO->getPass() . "';";

			$resultado = mysqli_query($conn, $sql);

			mysqli_data_seek($resultado, 0);

			$usuarioDevuelto = mysqli_fetch_array($resultado);

			$usuarioComprobado->setId($usuarioDevuelto['id']);
			$usuarioComprobado->setNombre($usuarioDevuelto['nombre']);
			$usuarioComprobado->setPass($usuarioDevuelto['pass']);
			$usuarioComprobado->setTipoUsuario($usuarioDevuelto['tipo_usuario']);

			mysqli_free_result($resultado);

			return $usuarioComprobado;
		} catch (Exception $e) {
			echo "Error al hacer la consulta en la BBDD: " . $e->getMessage();
			return new Usuario();
		}
	}

	public function mostrarDatosUsuarios($conn)
	{
		$arrUsuarios = array();

		$sql = "select * from " . self::tabla_usuario;

		try {

			$resultado = mysqli_query($conn, $sql);

			$numFilas = mysqli_num_rows($resultado);

			if ($numFilas == 0) {
				echo "No hay datos para mostrar";
			} else {
				for ($i = 0; $i < $numFilas; $i++) {
					$usuario = new Usuario();

					$filaUsuario = mysqli_fetch_array($resultado);

					$usuario->setId($filaUsuario['id']);
					$usuario->setNombre($filaUsuario['nombre']);
					$usuario->setPass($filaUsuario['pass']);
					$usuario->setTipoUsuario($filaUsuario['tipo_usuario']);

					println($usuario);

					array_push($arrUsuarios, $usuario);
				}
			}

			return $arrUsuarios;
		} catch (Exception $e) {
			echo "Error al hacer la consulta en la BBDD: " + $e->getMessage();
			return $arrUsuarios;
		}
	}


	public function insertarUsuario($conn, $usuarioDTO)
	{
		$sql = "insert into " . self::tabla_usuario . " (" . self::usuario_nombre . ", " . self::usuario_pass .
			", " . self::usuario_tipo_usuario . " ) values ('" . $usuarioDTO->getNombre() . "', '" . $usuarioDTO->getPass() .
			"', '" . $usuarioDTO->getTipoUsuario() . "' );";

		try {
			if ($conn->query($sql) === true) {
				echo "Se ha insertado correctamente en la BBDD";
			} else {
				echo "Error no se ha podido insertar en la BBDD";
			}
		} catch (Exception $e) {
			echo $e->getMessage();
		}
	}


	public function mostrarDatosProyectos($conn)
	{
		$arrProyectos = array();

		$sql = "select * from " . self::tabla_proyecto;

		try {

			$resultado = mysqli_query($conn, $sql);

			$numFilas = mysqli_num_rows($resultado);

			if ($numFilas == 0) {
				echo "No hay datos para mostrar";
			} else {
				for ($i = 0; $i < $numFilas; $i++) {
					$proyecto = new Proyecto();

					$filaProyecto = mysqli_fetch_array($resultado);

					$proyecto->setId($filaProyecto['id']);
					$proyecto->setNombre($filaProyecto['nombre']);

					// println($proyecto);

					array_push($arrProyectos, $proyecto);
				}
			}
			return $arrProyectos;
		} catch (Exception $e) {
			echo "Error al hacer la consulta en la BBDD: " + $e->getMessage();
			return $arrProyectos;
		}
	}


	public function insertarProyecto($conn, $proyectoDTO)
	{
		$sql = "insert into " . self::tabla_proyecto . " (" . self::proyecto_nombre . " ) values ('" . $proyectoDTO->getNombre() . "' );";

		try {
			if ($conn->query($sql) === true) {
				echo "Se ha insertado correctamente en la BBDD";
			} else {
				echo "Error no se ha podido insertar en la BBDD";
			}
		} catch (Exception $e) {
			echo $e->getMessage();
		}
	}


	public function mostrarTareas($conn, $nm_proyecto)
	{

		$arrTareas = array();

		$sql = "select * from " . self::tabla_vista_tareas . " where " .
			self::vista_tareas_proyecto . " = '" . $nm_proyecto . "'";

		try {
			$resultado = mysqli_query($conn, $sql);

			$numFilas = mysqli_num_rows($resultado);

			if ($numFilas == 0) {
				echo "No hay tareas en la BBDD";
			} else {
				for ($i = 0; $i < $numFilas; $i++) {
					$vtarea = new V_Tareas();

					$filaTarea = mysqli_fetch_array($resultado);

					$vtarea->setProyecto($filaTarea['Proyecto']);
					$vtarea->setUsuario($filaTarea['Usuario']);
					$vtarea->setTarea($filaTarea['Tarea']);
					$vtarea->setEstado($filaTarea['Estado']);

					array_push($arrTareas, $vtarea);
				}
				return $arrTareas;
			}
		} catch (Exception $e) {
			echo "Error al mostrar tareas: " . $e->getMessage();
		}
	}

	public function insertarTarea($conn, $tareaDTO)
	{

		$sql = "insert into " . self::tabla_tarea . " ( " . self::tarea_fk_proyecto . ", " . self::tarea_fk_usuario . ", " . self::tarea_nombre .
			", " . self::tarea_estado . " ) values ('" . $tareaDTO->getFk_Proyecto() . "', '" . $tareaDTO->getFk_Usuario() .
			"', '" . $tareaDTO->getNombre() . "', '" . $tareaDTO->getEstado() . "' );";

		try {
			if ($conn->query($sql) === true) {
				echo "Se ha insertado correctamente en la BBDD";
			} else {
				echo "Error no se ha podido insertar en la BBDD";
			}
		} catch (Exception $e) {
			echo $e->getMessage();
		}
	}

	public function borrarUsuario($conn, $usuarioId)
	{
		$sql = "delete from " . self::tabla_usuario . " where " . self::usuario_id . " = '" . $usuarioId . "'";
		try {
			if ($conn->query($sql) === true) {
				echo "Se ha borrado correctamente el registro de la BBDD";
			} else {
				echo "Error no se ha podido borrar el registro";
			}
		} catch (Exception $e) {
			echo $e->getMessage();
		}
	}
	public function borrarProyecto($conn, $proyectoId)
	{
		$sql = "delete from " . self::tabla_proyecto . " where " . self::proyecto_id . " = '" . $proyectoId . "'";
		try {
			if ($conn->query($sql) === true) {
				echo "Se ha borrado correctamente el registro de la BBDD";
			} else {
				echo "Error no se ha podido borrar el registro";
			}
		} catch (Exception $e) {
			echo $e->getMessage();
		}
	}
	public function borrarTarea($conn, $proyectoId, $usuarioId)
	{
		$sql = "delete from " . self::tabla_tarea . " where " . self::tarea_fk_proyecto . " = " .
			$proyectoId . " and " . self::tarea_fk_usuario . " = " . $usuarioId . ";";
		try {
			if ($conn->query($sql) === true) {
				echo "Se ha borrado correctamente el registro de la BBDD";
			} else {
				echo "Error no se ha podido borrar el registro";
			}
		} catch (Exception $e) {
			echo $e->getMessage();
		}
	}

	public function actualizarVista($conn)
	{
		$sql = "CREATE OR REPLACE VIEW vista_tareas AS
		SELECT 
			proyecto.nombre AS Proyecto,
			usuario.nombre AS Usuario,
			tarea.nombre AS Tarea,
			tarea.estado AS Estado
		FROM
			tarea
				INNER JOIN
			proyecto ON proyecto.id = tarea.fk_proyecto
				INNER JOIN
			usuario ON usuario.id = tarea.fk_usuario
		ORDER BY proyecto.nombre;";
		try {
			if ($conn->query($sql) === true) {
				echo "Se ha actualizado correctamente la vista de la BBDD";
			} else {
				echo "Error no se ha podido actualizar la vista";
			}
		} catch (Exception $e) {
			echo $e->getMessage();
		}
	}

	public function modificarUsuario($conn, $usuarioDTO)
	{
		$sql = "update " . self::tabla_usuario . " set " . self::usuario_nombre . " = '" .
			$usuarioDTO->getNombre() . "', " . self::usuario_pass . " = '" . $usuarioDTO->getPass() .
			"', " . self::usuario_tipo_usuario . " = " . $usuarioDTO->getTipoUsuario() .
			" where " . self::usuario_id . " = " . $usuarioDTO->getId() . ";";
		try {
			if ($conn->query($sql) === true) {
				echo "Se ha actualizado correctamente el registro de la BBDD";
			} else {
				echo "Error no se ha podido actualizar el registro";
			}
		} catch (Exception $e) {
			echo $e->getMessage();
		}
	}
	public function modificarProyecto($conn, $proyectoDTO)
	{
		$sql = "update " . self::tabla_proyecto . " set " . self::proyecto_nombre . " = '" .
			$proyectoDTO->getNombre() . "' where " . self::proyecto_id . " = " . $proyectoDTO->getId() . ";";
		try {
			if ($conn->query($sql) === true) {
				echo "Se ha actualizado correctamente el registro de la BBDD";
			} else {
				echo "Error no se ha podido actualizar el registro";
			}
		} catch (Exception $e) {
			echo $e->getMessage();
		}
	}
	public function modificarTarea($conn, $tareaDTO)
	{
		$sql = "update " . self::tabla_tarea . " set "  . self::tarea_nombre . " = '" . $tareaDTO->getNombre() .
			"', " . self::tarea_estado . " = " . $tareaDTO->getEstado() .
			" where " . self::tarea_fk_usuario . " = " . $tareaDTO->getFk_Usuario() . " and " .
			self::tarea_fk_proyecto . " = " . $tareaDTO->getFk_Proyecto();
		try {
			if ($conn->query($sql) === true) {
				echo "Se ha actualizado correctamente el registro de la BBDD";
			} else {
				echo "Error no se ha podido actualizar el registro";
			}
		} catch (Exception $e) {
			echo $e->getMessage();
		}
	}

	public function mostrarTareasUsuario($conn, $usuarioDTO)
	{

		$arrTareasUsuario = array();

		$sql = "select * from " . self::tabla_vista_tareas . " where " .
			self::vista_tareas_usuario . " = '" . $usuarioDTO->getNombre() . "'";

		try {
			$resultado = mysqli_query($conn, $sql);

			$numFilas = mysqli_num_rows($resultado);

			if ($numFilas == 0) {
				echo "No hay tareas en la BBDD";
			} else {
				for ($i = 0; $i < $numFilas; $i++) {
					$vtarea = new V_Tareas();

					$filaTareaUsuario = mysqli_fetch_array($resultado);

					$vtarea->setProyecto($filaTareaUsuario['Proyecto']);
					$vtarea->setUsuario($filaTareaUsuario['Usuario']);
					$vtarea->setTarea($filaTareaUsuario['Tarea']);
					$vtarea->setEstado($filaTareaUsuario['Estado']);

					array_push($arrTareasUsuario, $vtarea);
				}
				return $arrTareasUsuario;
			}
		} catch (Exception $e) {
			echo "Error al mostrar tareas: " . $e->getMessage();
		}
	}
}


function println(string $string = '')
{
	print($string . PHP_EOL);
}
