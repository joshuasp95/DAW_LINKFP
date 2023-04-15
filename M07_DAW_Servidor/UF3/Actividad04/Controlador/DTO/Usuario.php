<?php

class Usuario
{

    private $id;
    private $nombre;
    private $pass;
    private $tipo_usuario;

    function __construct()
    {
        $this->id = 0;
        $this->nombre = "";
        $this->pass = "";
        $this->tipo_usuario = 0;
    }

    function getId()
    {
        return $this->id;
    }
    function getNombre()
    {
        return $this->nombre;
    }
    function getPass()
    {
        return $this->pass;
    }
    function getTipoUsuario()
    {
        return $this->tipo_usuario;
    }

    function setId($id)
    {
        $this->id = $id;
    }
    function setNombre($nombre)
    {
        $this->nombre = $nombre;
    }
    function setPass($pass)
    {
        $this->pass = $pass;
    }
    function setTipoUsuario($tipo_usuario)
    {
        $this->tipo_usuario = $tipo_usuario;
    }

    function __toString()
    {
        return "Usuario: id= " . $this->id . ", nombre= " . $this->nombre . ", pass= " . $this->pass
            . ", tipo= " . $this->tipo_usuario;
    }
}
