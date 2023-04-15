<?php

class V_Tareas
{

    private $Proyecto;
    private $Usuario;
    private $Tarea;
    private $Estado;

    function __construct()
    {
        $this->Proyecto = 0;
        $this->Usuario = 0;
        $this->Tarea = "";
        $this->Estado = 0;
    }

    function getProyecto()
    {
        return $this->Proyecto;
    }
    function getUsuario()
    {
        return $this->Usuario;
    }
    function getTarea()
    {
        return $this->Tarea;
    }

    function getEstado()
    {
        return $this->Estado;
    }

    function setProyecto($Proyecto)
    {
        $this->Proyecto = $Proyecto;
    }
    function setUsuario($Usuario)
    {
        $this->Usuario = $Usuario;
    }

    function setTarea($Tarea)
    {
        $this->Tarea = $Tarea;
    }
    function setEstado($Estado)
    {
        $this->Estado = $Estado;
    }


    function __toString()
    {
        return "Tarea: Proyecto= " . $this->Proyecto . ", Usuario= " . $this->Usuario .
            ", Tarea= " . $this->Tarea . ", Estado= " . $this->Estado;
    }
}
