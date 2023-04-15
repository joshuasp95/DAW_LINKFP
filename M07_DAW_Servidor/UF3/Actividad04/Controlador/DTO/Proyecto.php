<?php

class Proyecto
{

    private $id;
    private $nombre;



    function __construct()
    {
        $this->id = 0;
        $this->nombre = "";
    }

    function getId()
    {
        return $this->id;
    }
    function getNombre()
    {
        return $this->nombre;
    }

    function setId($id)
    {
        $this->id = $id;
    }
    function setNombre($nombre)
    {
        $this->nombre = $nombre;
    }

    function __toString()
    {
        return "Proyecto: id= " . $this->id . ", nombre= " . $this->nombre;
    }
}
