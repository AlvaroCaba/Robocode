package prueba;

import static robocode.util.Utils.normalRelativeAngleDegrees;


import java.awt.Color;
import java.awt.Graphics2D;

import robocode.Robot;

/**
 * 
 */

/**
 * @date 2018-03-22
 * 
 * Plantilla para la definición de un robot en Robocode
 *
 */
public class Bot3 extends Robot {

	int fila 		= 16;
	int columna 	= 12;
	int tamCelda 	= 50;
	int filaPixels = 800;
	
	//The main method in every robot
	public void run() {
		

		System.out.println("Iniciando ejecución del robot");
		
		// Nuestro robot será rojo
		setAllColors(Color.red);

		//DATOS QUE DEBEN COINCIDIR CON LOS DEL PROGRAMA main DE LA CLASE RouteFinder
	
		
		//Orientamos inicialmente el robot hacia arriba
		turnRight(normalRelativeAngleDegrees(0 - getHeading()));
		
		//A continuación nuestro robot girará un poco sobre sí mismo		
		int k = 0;
		while(k < 20){
			turnRight(90);
			k++;
		}
		
		
		// AQUI DEBE:
		//  1. GENERARSE EL PROBLEMA DE BÚSQUEDA
		//  2. BUSCAR LA SOLUCIÓN CON UN ALGORITMO DE BÚSQUEDA
		//  3. EJECUTAR LA SOLUCIÓN ENCONTRADA
			
	}


/***
* Esté método se ejecutará cuándo se pulse el botón Pintar

*/
	public void onPaint(Graphics2D g) {

		double filaInicial = 100.0, columnaInicial = 100.0;
		double filaFinal   = 200.0, columnaFinal = 500.0;

		
	    // Inicial
	    g.setColor(Color.GREEN);
	    g.fillRect((int)filaInicial,(int) columnaInicial, 15, 15);
	    
	    // DESTINO
	    g.setColor(new Color(0xff, 0x00, 0x00, 0x80));
	    g.fillRect((int) filaFinal,(int) columnaFinal, 15, 15);
		
	    
	    //Cuadrículas

	    
		g.setPaint(Color.white);

	    for (int i=0; i<fila;i++)
	    		g.drawLine(i*tamCelda+1, 0, i*tamCelda+1, filaPixels);
	    
	    for (int i=0; i<columna;i++)
	    		g.drawLine (0, i*tamCelda+1, filaPixels, i*tamCelda+1);
	    
	 
	    
	}
	
	
}
