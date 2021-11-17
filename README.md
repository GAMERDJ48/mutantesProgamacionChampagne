# Desafio MercadoLibre

Por Programación Champagne
<h3>Integrantes: </h3>
<ul>
<li>Martín Carrión</li>
<li>Martiniano Juarroz</li>
<li>Lucas López</li>
<li>Gerónimo Ortiz</li>
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
<li>400: "error":"El arreglo no es cuadrado"</li>
<li>400: "error":"El arreglo no presenta caracteres validos"</li>
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

<hr />

## Correcciones agregadas

###Cambios a la entidad 
-Se agrega anotación Data para resumir las anotaciones getter, setter y toString

###Cambios a la capa repositorio
-Se agregan dos métodos con queries al MutantRepository, uno devuelve la cuenta de mutantes en la base de datos y otro devuelve la cantidad total de entradas en la tabla mutantes

###Cambios a la capa de servicio
-Se traslada la lógica de StatsExperto al metodo statsService, ademas para statsService se consideró el caso en el que la tabla este vacía y se usan las queries agregadas en el repositorio para evitar tener que traer las entradas de la tabla con toda su información

###Cambios a Stats y StatsExperto
-Se elimina StatsExperto y se traslada su funcionalidad al metodo statsService de MutantService
-Se cambia nombre de atributos de la clase Stats usando camel case

###Cambios a MutantDetector
-Se acorta cantidad de código en la validación
-Cambio al algoritmo completo, primero que nada, lo que hacemos es crear un solo metodo testHorizontal que se encarga del análisis de coincidencias en la matriz. Este único método analiza solamente las filas de la matriz. Para el análisis horizontal, simplemente le pasamos el adn como está al método. Para el análisis vertical lo que hacemos es "rotar" la matriz y hacer que las columnas de la matriz sean las filas de una nueva de matriz que será analizada por este metodo testHorizontal. Para el análisis de diagonales inversas, lo que hacemos es crear un metodo que obtiene todas las diagonales inversas de la matriz, las convierte en string y las pone como filas de una nueva matriz que sera usada en testHorizontal. Por último, para el analisis de diagonales principales, lo que hacemos es crear una nueva matriz que es la matriz adn pero con una reflexión respecto al eje vertical, de modo que las diagonales principales de la matriz original, son las diagonales inversas de la nueva matriz, permitiendonos así, aplicar el criterio de analisis de diagonales inversas para esta nueva matriz. De esta forma, con estos metodos de transformación de matrices, solo necesitamos el metodo testHorizontal para hacer la detección de mutantes. 
-Usamos map y filter para algunos métodos

###Mejoras a hacer
-Mejorar la extracción de las diagonales de las matrices
-Aplicar regex para hacer mas rápido la detección de coincidencias
