<?php

class Tarea
{

    private $fk_proyecto;
    private $fk_usuario;
    private $nombre;
    private $estado;

    function __construct()
    {
        $this->fk_proyecto = 0;
        $this->fk_usuario = 0;
        $this->nombre = "";
        $this->estado = 0;
    }

    function getFk_Proyecto()
    {
        return $this->fk_proyecto;
    }
    function getFk_Usuario()
    {
        return $this->fk_usuario;
    }
    function getNombre()
    {
        return $this->nombre;
    }

    function getEstado()
    {
        return $this->estado;
    }

    function setFk_Proyecto($fk_proyecto)
    {
        $this->fk_proyecto = $fk_proyecto;
    }
    function setFk_Usuario($fk_usuario)
    {
        $this->fk_usuario = $fk_usuario;
    }

    function setNombre($nombre)
    {
        $this->nombre = $nombre;
    }
    function setEstado($estado)
    {
        $this->estado = $estado;
    }


    function __toString()
    {
        return "Tarea: fk_proyecto= " . $this->fk_proyecto . ", fk_usuario= " . $this->fk_usuario .
            ", nombre= " . $this->nombre . ", estado= " . $this->estado;
    }
}
