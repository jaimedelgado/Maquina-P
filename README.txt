Dirígete a la carpeta alex y abre una terminal en esa ruta.

Introduce el siguiente comando para compilar el fichero pr1.l: java -cp jlex.jar JLex.Main pr1.l
Renombra el archivo pr1.l.java por AnalizadorLexicoTiny.java

Dirígete a la carpeta astCUP y abre una terminal en esa ruta.
Introduce el siguiente comando para compilar el fichero Tiny.cup: java -jar cup.jar -nopositions -parser AnalizadorSintacicoTiny -symbols ClaseLexica -dump_states Tiny.cup 2> automata.txt

Configura el build path del proyecto java para incluir el archivo cup.jar que se encuenta en la carpeta asstCUP.

En eclipse se realiza de la siguiente manera:
	-Botón derecho en el proyecto -> Properties -> Java Build Path -> Libraries -> Add JARs -> Selecciona el archivo cup.jar

El Main.java de el paquete "procesador" se ejecuta pasando la ruta de los archivos de ejemplo "inputVSem.txt" e "inputVSem2.txt". Este, genera el codigop asociado a los programas de ejemplo de los archivos. 

El Main.java de el paquete "maquinap" de la misma manera. Este, genera el codigop asociado a los programas de ejemplo de los archivos y ejecuta dicho código en la maquinap. 
