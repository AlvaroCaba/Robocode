package prueba;

import static robocode.util.Utils.normalRelativeAngleDegrees;


import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

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
	int numObstaculos = 8;
	long semilla = 0;
	
	
	
	//The main method in every robot
	public void run() {
		

		System.out.println("Iniciando ejecución del robot");
	
		
		// Nuestro robot será rojo
		setAllColors(Color.black);

		//DATOS QUE DEBEN COINCIDIR CON LOS DEL PROGRAMA main DE LA CLASE RouteFinder
	
		
		//Orientamos inicialmente el robot hacia arriba
		turnRight(normalRelativeAngleDegrees(0 - getHeading()));
		
		//A continuación nuestro robot girará un poco sobre sí mismo		
		int k = 0;
		while(k < 20){
			turnRight(90);
			k++;
		}
		
		
		// AQU� DEBE:
				//  1. GENERARSE EL PROBLEMA DE B�SQUEDA
					Problema p = new Problema(semilla, fila, columna, numObstaculos);
					
					
				//  2. BUSCAR LA SOLUCI�N CON UN ALGORITMO DE B�SQUEDA
					Algoritmo a = new Algoritmo();
					ArrayList<Nodo> camino = a.buscaSolucion(p.getFilaIni(), p.getFilaFin(),  p.getColumIni(), p.getColumFin(), p.getMatriz());
					
				//  3. EJECUTAR LA SOLUCI�N ENCONTRADA
					recorrer(camino);
			}
			
			private void recorrer(ArrayList<Nodo> a) {
				Nodo actual, siguiente;
				ArrayList<Nodo> lista = a;
				double dir;
				for(int i = 0; i < lista.size() - 1; i++){ //suponiendo que el robot comienza orientado hacia arriba
					actual = lista.get(i);
					siguiente = lista.get(i+1);
					if(actual.getF()==siguiente.getF() && actual.getC()>siguiente.getC()){ //moverse a la celda de abajo
						dir=getHeading();
						switch((int) dir){
							case 0:	turnRight(180);
									ahead(50);
								break;
							case 90: turnRight(90);
									 ahead(50);
								break;
							case 180: ahead(50);
								break;
							case 270: turnLeft(90);
									 ahead(50);
								break;
						}
					}
					if(actual.getF()==siguiente.getF() && actual.getC()<siguiente.getC()){ //moverse a la celda de arriba
						dir=getHeading();
						switch((int) dir){
							case 0:	ahead(50);
								break;
							case 90: turnLeft(90);
									 ahead(50);
								break;
							case 180: turnRight(180);
									  ahead(50);
								break;
							case 270: turnRight(90);
									  ahead(50);
								break;
						}
					}
					if(actual.getF()<siguiente.getF() && actual.getC()==siguiente.getC()){ //moverse a la celda de la derecha
						dir=getHeading();
						switch((int) dir){
							case 0:	turnRight(90);
									ahead(50);
								break;
							case 90: ahead(50);
								break;
							case 180: turnLeft(90);
								 	  ahead(50);
								break;
							case 270: turnRight(180);
							          ahead(50);
								break;
						}
					}
					if(actual.getF()>siguiente.getF() && actual.getC()==siguiente.getC()){ // moverse a la celda de la izquierda
						dir=getHeading();
						switch((int) dir){
							case 0: turnLeft(90);
									ahead(50);
								break;
							case 90: turnRight(180);
									 ahead(50);
								break;
							case 180: turnRight(90);
									  ahead(50);
								break;
							case 270: ahead(50);
								break;
						}
					}
				}
			}



//* Esté método se ejecutará cuándo se pulse el botón Pintar


	public void onPaint(Graphics2D g) {

		Problema p = new Problema(semilla, fila, columna, numObstaculos);
		
	    // Inicial
	    g.setColor(Color.GREEN);
	    g.fillRect(50*p.getFilaIni() +25, 50*p.getColumIni()+25, 50, 50);
	    
	    // DESTINO
	    g.setColor(new Color(0xff, 0x00, 0x00, 0x80));
	    g.fillRect(50*p.getFilaFin() +25,p.getColumFin(), 50, 50);
		
	    
	    //Cuadrículas

	    
		g.setPaint(Color.white);

	    for (int i=0; i<fila;i++)
	    		g.drawLine(i*tamCelda+1, 0, i*tamCelda+1, filaPixels);
	    
	    for (int i=0; i<columna;i++)
	    		g.drawLine (0, i*tamCelda+1, filaPixels, i*tamCelda+1);
	    
	 
	    
	}
	
	
}
