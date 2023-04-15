<?php

class Personaje
{
    protected $nombre = "";
    protected $especie = "";
    protected $puntosExperiencia = 0;
    protected $vida = 0;

    function __construct($nombre, $especie, $puntosExperiencia, $vida)
    {
        $this->nombre = $nombre;
        $this->especie = $especie;
        $this->puntosExperiencia = $puntosExperiencia;
        $this->vida = $vida;
    }
    function getNombre()
    {
        return $this->nombre;
    }
    function getEspecie()
    {
        return $this->especie;
    }
    function getPuntosExperiencia()
    {
        return $this->puntosExperiencia;
    }
    function getVida()
    {
        return $this->vida;
    }
    function setNombre($nombre)
    {
        $this->nombre = $nombre;
    }
    function setEspecie($especie)
    {
        $this->especie = $especie;
    }
    function setPuntosExperiencia($puntosExperiencia)
    {
        $this->puntosExperiencia = $puntosExperiencia;
    }
    function setVida($vida)
    {
        $this->vida = $vida;
    }
    function restarVida($numero)
    {
        $this->vida -= $numero;
    }

    function __toString()
    {
        return "Nombre: " . $this->nombre . "; Especie: " . $this->especie .
            "; Puntos Experiencia: " . $this->puntosExperiencia;
    }

    function __destruct()
    {
    }
}
class Guerrero extends Personaje
{
    private $enemigosAbatidos = 0;
    private $dañoSufrido = 0;
    private $puntosGanados = 0;
    private $url = "../imgs/lei-shen2.png";

    function __construct($nombre, $especie, $puntosExperiencia, $enemigosAbatidos, $dañoSufrido, $vida)
    {
        parent::__construct($nombre, $especie, $puntosExperiencia, $vida);
        $this->enemigosAbatidos = $enemigosAbatidos;
        $this->dañoSufrido = $dañoSufrido;
    }
    function getEnemigosAbatidos()
    {
        return $this->enemigosAbatidos;
    }
    function getDañoSufrido()
    {
        return $this->dañoSufrido;
    }
    function getAtaque1()
    {
        return $this->enemigosAbatidos;
    }
    function getAtaque2()
    {
        return $this->dañoSufrido;
    }

    function getURL()
    {
        return $this->url;
    }


    function setEnemigosAbatidos($enemigosAbatidos)
    {
        $this->enemigosAbatidos += $enemigosAbatidos;
    }
    function setDañoSufrido($dañoSufrido)
    {
        $this->dañoSufrido += $dañoSufrido;
        $this->vida -= ($dañoSufrido / 2);
    }

    function __toString()
    {
        return "Nombre: " . parent::getNombre() . "; Especie: " . parent::getEspecie() .
            "; Puntos Experiencia: " . parent::getPuntosExperiencia();
    }

    function __destruct()
    {
    }
    function getPuntosGanados()
    {
        return $this->puntosGanados;
    }

    function setPuntosGanados($puntos)
    {
        $this->puntosGanados = $puntos;
    }

    function puntosGanados()
    {
        $enemigosAbatidos = $this->enemigosAbatidos;
        $dañoSufrido = $this->dañoSufrido;
        $this->puntosGanados += ($enemigosAbatidos * 10) + ($dañoSufrido * 5);
        return $this->puntosGanados;
    }
}

class Mago extends Personaje
{
    private $secretosDescubiertos = 0;
    private $hechizosLanzados = 0;
    private $puntosGanados = 0;
    private $url = "../imgs/medivh.png";

    function __construct($nombre, $especie, $puntosExperiencia, $secretosDescubiertos, $hechizosLanzados, $vida)
    {
        parent::__construct($nombre, $especie, $puntosExperiencia, $vida);
        $this->secretosDescubiertos = $secretosDescubiertos;
        $this->hechizosLanzados = $hechizosLanzados;
    }
    function getSecretosDescubiertos()
    {
        return $this->secretosDescubiertos;
    }
    function getHechizosLanzados()
    {
        return $this->hechizosLanzados;
    }
    function getAtaque1()
    {
        return $this->secretosDescubiertos;
    }
    function getAtaque2()
    {
        return $this->hechizosLanzados;
    }
    function getURL()
    {
        return $this->url;
    }

    function setSecretosDescubiertos($secretosDescubiertos)
    {
        $this->secretosDescubiertos += $secretosDescubiertos;
    }
    function setHechizosLanzados($hechizosLanzados)
    {
        $this->hechizosLanzados += $hechizosLanzados;
    }
    function __toString()
    {
        return "Nombre: " . parent::getNombre() . "; Especie: " . parent::getEspecie() .
            "; Puntos Experiencia: " . parent::getPuntosExperiencia();
    }

    function __destruct()
    {
    }
    function getPuntosGanados()
    {
        return $this->puntosGanados;
    }

    function setPuntosGanados($puntos)
    {
        $this->puntosGanados = $puntos;
    }
    function puntosGanados()
    {
        $secretosDescubiertos = $this->secretosDescubiertos;
        $hechizosLanzados = $this->hechizosLanzados;
        $this->puntosGanados += ($secretosDescubiertos * 10) + ($hechizosLanzados * 5);
        return $this->puntosGanados;
    }
}

class Explorador extends Personaje
{
    private $objetivosDescubiertos = 0;
    private $vecesSinSerDescubierto = 0;
    private $puntosGanados = 0;
    private $url = "../imgs/thrall.png";


    function __construct($nombre, $especie, $puntosExperiencia, $objetivosDescubiertos, $vecesSinSerDescubierto, $vida)
    {
        parent::__construct($nombre, $especie, $puntosExperiencia, $vida);
        $this->objetivosDescubiertos = $objetivosDescubiertos;
        $this->vecesSinSerDescubierto = $vecesSinSerDescubierto;
    }
    function getObjetivosDescubiertos()
    {
        return $this->objetivosDescubiertos;
    }
    function getVecesSinSerDescubierto()
    {
        return $this->vecesSinSerDescubierto;
    }
    function getAtaque1()
    {
        return $this->objetivosDescubiertos;
    }
    function getAtaque2()
    {
        return $this->vecesSinSerDescubierto;
    }
    function getURL()
    {
        return $this->url;
    }

    function setObjetivosDescubiertos($objetivosDescubiertos)
    {
        $this->objetivosDescubiertos += $objetivosDescubiertos;
    }
    function setVecesSinSerDescubierto($vecesSinSerDescubierto)
    {
        $this->vecesSinSerDescubierto += $vecesSinSerDescubierto;
    }
    function __toString()
    {
        return "Nombre: " . parent::getNombre() . "; Especie: " . parent::getEspecie() .
            "; Puntos Experiencia: " . parent::getPuntosExperiencia();
    }

    function __destruct()
    {
    }
    function getPuntosGanados()
    {
        return $this->puntosGanados;
    }

    function setPuntosGanados($puntos)
    {
        $this->puntosGanados = $puntos;
    }
    function puntosGanados()
    {
        $objetivosDescubiertos = $this->objetivosDescubiertos;
        $vecesSinSerDescubierto = $this->vecesSinSerDescubierto;
        $this->puntosGanados += ($objetivosDescubiertos * 10) + ($vecesSinSerDescubierto * 5);
        return $this->puntosGanados;
    }
}
