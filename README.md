# Desafio MercadoLibre

Por Programación Champagne
<h3>Integrantes: </h3>
<ul>
<li>Martín Carrión</li>
<li>Martiniano Juarroz</li>
<li>Lucas López</li>
<li>Gerónimo Ortíz</li>
<li>Alvaro Pereyra</li>
<li>Franco Ruiz</li>
<li>Nicolás Saurina</li>
</ul>
<hr />

## Consigna

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Mens.
<hr />

## URLs Útiles

#### Link de la aplicación

https://mutantsprogramacionchampagne.herokuapp.com/
<hr />

## Requests

<hr />

#### Chequear si una cadena de ADN pertenece un Mutante

POST https://mutantsprogramacionchampagne.herokuapp.com/api/v1/mutant/

Ejemplo de body de la Request:

```json
{
  "dna": [
    "ATGCGA",
    "CCGTCC",
    "TTAAGT",
    "ATAAGG",
    "CCTTTA",
    "TTTTGG"
  ]
}
```

##### Estados posibles en la respuesta

<ul>
<li>200: El adn ingresado es de un mutante</li>
<li>403: El adn ingresado no es de un mutante</li>
<li>400: Hubo un error a la hora de procesar la petición</li>
</ul>

<hr />

#### Obtener estadisticas sobre la base de datos

GET https://mutantsprogramacionchampagne.herokuapp.com/api/v1/mutant/stats

##### Ejemplo de respuesta

```json
{
  "count_mutant_dna": 11.0,
  "count_human_dna": 22.0,
  "mutantRatio": 0.5
}
```

<hr />

## Cobertura de código

Se cubre 86% del código

![Code coverage](/files/coverage.jpg)
