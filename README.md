# Videojuego _Super Elizabeth Sis._

A partir de una interfaz proporcionada por la cátedra debimos desarrollar en solo 3 semanas un videojuego que cumpla con el argumento y los requerimientos funcionales solicitados.
Además los requisitos obligatorios agregamos las funcionalidades adicionales descritas abajo.

## 📋Consigna

El objetivo de este trabajo práctico es desarrollar un video juego en el cual la Princesa Elizabeth vaya subiendo por los distintos niveles del volcán hasta llegar a la cima. La princesa
deberá romper y subir por los distintos bloques de ladrillos hasta encontrar la salida, además deberá eliminar los enemigos que van apareciendo.

## 📋Requerimientos obligatorios

1. En pantalla deberá verse el interior de un volcán, con una serie de pisos con filas de bloques. Cada piso debe tener al menos un bloque que pueda ser destruido cuando la princesa Elizabeth salte desde el piso inferior. La cantidad de pisos en la pantalla queda
a criterio de cada grupo, aunque deben ser no menos de 4 pisos y no más de 6 pisos.
2. Al iniciar el juego, la princesa Elizabeth debe aparecer en el centro de la fila inferior de bloques.
3. Mientras la princesa esté parada sobre una fila de bloques podrá: moverse hacia la
izquierda o hacia la derecha presionando las teclas izquierda o derecha respectivamente,
y podrá saltar si se presiona una tecla. Si no se presiona ninguna tecla direccional, la
princesa debe quedarse quieta. Si la princesa no está parada sobre una fila de bloques
debe caer en forma vertical hasta el siguiente fila de bloques.
4. Cuando la princesa salte podrá destruir/romper el bloque superior con el que colisionó,
haciendo que este desaparezca, es decir el objeto quede nulo (null). De esa manera, en
un próximo salto podrá subir de un salto la fila de arriba. Cada fila de bloques deberá
ocupar el ancho de la pantalla y debe tener al menos un bloque que se rompa fácilmente
con golpe de la princesa y otros bloques de acero que no pueden ser destruidos con nada.
Ambos tipos de bloques deben poder distinguirse.
5. Cada fila de bloques debe tener inicialmente 2 Tiranosaurios Rex mutantes caminando
sobre la misma. Ellos se podrán mover de izquierda a derecha y viceversa, y deben
cambiar de dirección al llegar a la pantalla. Si llegan a un hueco en la fila de bloques,
deben caer de manera vertical y seguir caminando por la fila de abajo en la misma
dirección en la que venían. Los tiranosaurios no se pueden superponer (no puede ir
caminando un tiranosaurio encima de otro), aunque sí pueden cruzarse por el mismo
piso. Los tiranosaurios deben ir apareciendo en posiciones aleatorias de manera que
siempre haya al menos 2 en la pantalla.
6. La princesa tiene una habilidad secreta: puede lanzar su disparo cuando se presione una tecla. El disparo debe salir en la dirección donde está caminando o caminaba la
princesa. Cuando el disparo hace contacto con un Tiranosaurio Rex, lo mata y debe
desaparecer tanto el disparo como el enemigo eliminado. La princesa solo puede realizar
un disparo por vez.
7. Los Tiranosaurios Rex tienen una mutación que les permite lanzar una bomba como
proyectil. Pueden lanzar sólo una a la vez, que se moverá hacia la dirección donde está
caminando. Si la bomba hace colisión con la princesa, perdemos el juego. Si la bomba
sale de pantalla o colisiona con un disparo de la princesa, la bomba es eliminada. Los
proyectiles lanzados pueden atravesar otras bombas y a otros Tiranosaurios Rex.
8. Cuando la princesa haga colisión con un Tiranosaurio Rex o una bomba perdemos el
juego. Cuando la princesa salga por el sector superior de la pantalla ganamos el juego.
El salto de la princesa debe permitirle esquivar las bombas de los tiranosaurios.
9. Aclaramos que cuando un Tiranosaurio Rex, bomba, disparo o bloque de
roca ya no esta más en la pantalla, los objetos deben ser eliminados, no vale
únicamente ocultar las imágenes del juego.
10. Durante todo el juego deberá mostrarse en algún lugar de la pantalla: la cantidad
de enemigos eliminados y el puntaje obtenido. Por cada enemigo eliminado se gana 2
puntos.


## 📋Funcionalidades adicionales


- Magma: Se muestra el magma del volcán subiendo cuando se llega a cierta altura, si llegara a tener contacto
con la Princesa se pierde una vida.
- Pisos extras: cuando se llega a filas más alta, la pantalla hace un
desplazamiento hacia abajo para que puedan visualizarse más pisos del volcán.
- Niveles: Existe la posibilidad de comenzar un nuevo nivel después de subir al piso más alto del nivel. Incrementar la dicultad y la velocidad.
- Jefe final
- Vidas: cada vez que muera la princesa se le reste una vida
- Música de fondo y efectos de sonido
- Pantallas de inicio, indicadoras de nivel y de Game Over

## 📷Screenshots

<img src="https://github.com/user-attachments/assets/07c8c5b8-c084-4767-b015-65491d8716d2" width=800>
<br>

<img src="https://github.com/user-attachments/assets/a3f30b57-49c7-486d-85fc-e17b1dde84de" width=800>
<br>

<img src="https://github.com/user-attachments/assets/47634a44-d97d-451c-bfa4-e997a3c7595f" width=800>
<br>


## 💻☕Equipo de desarrollo

|Nombre|Contacto|
|----|----|
|  Dávalos, Leonardo | [GitHub](https://github.com/davaloslm), [LinkedIn](https://linkedin.com/in/leonardo-davalos) | 
|  Valdiviezo, Alan | [GitHub](https://github.com/AlanValdiviezo) | 

