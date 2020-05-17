package prueba;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSetup;
import robocode.control.RobotSpecification;



/**
 * 
 * @date   2018-03-22
 * 
 * Plantilla para la prÃ¡ctica de algoritmos de bÃºsqueda con Robocode (G. Ing. Comp.)
 * 
 * 
 */


public class RouteFinder {

	public static void main(String[] args) {
		
		
		
		//Creamos un mapa con los datos especificados
		// Create the battlefield
		
		int numPixelFila= 800;
		int numPixelCol=600;
		int tamCelda = 50;       //celdas de 50 x 50
		
		//nÃºmero de obstÃ¡culos
		////DEBERA COINCIDIR CON EL VALOR PROPORCIONADO AL ROBOT
		int numObstaculos = 8;
		
		//semilla para el generador de nÃºmeros aleatorios
		//DEBERA COINCIDIR CON EL VALOR PROPORCIONADO AL ROBOT
		long semilla = 0;  
		
		//tamaÃ±o del mapa de obstÃ¡culos
		//DEBERA COINCIDIR CON EL VALOR PROPORCIONADO AL ROBOT
		int nFil = numPixelFila / tamCelda;
		int nCol = numPixelCol  / tamCelda;
		


		//AQUI SE DEBERA DE GENERAR EL MAPA DE OBSTACULOS Y LAS POSICIONES INICIAL Y FINAL DEL ROBOT
		//
		//
 		//  ...
		//
		//
		Problema p = new Problema(semilla, nFil, nCol, numObstaculos);
		
		
		
		
		
		
		
		// Crear el RobocodeEngine desde una instalaciÃ³n en C:/Robocode
		RobocodeEngine engine =
				 new RobocodeEngine(new java.io.File("C:/Robocode")); //Windows
				// new RobocodeEngine(new java.io.File("/Users/joseamontenegromontes/robocode")); //MAC 
		// Mostrar el simulador de Robocode
		engine.setVisible(true);
		
		
		
		BattlefieldSpecification battlefield =
				new BattlefieldSpecification(numPixelFila, numPixelCol);
		// Establecer parÃ¡metros de la batalla
		int numberOfRounds = 5;
		long inactivityTime = 10000000;
		double gunCoolingRate = 1.0;
		int sentryBorderSize = 50;
		boolean hideEnemyNames = false;
		

		
		// En modelRobots recogemos la especificaciÃ³n de los robots que utilizaremos en la simulaciÃ³n.
		// En este caso serÃ¡n un robot sittingDuck y nuestro propio robot. La referencia a nuestro robot
		// debe ser relativa al proyecto que pusimos en Options>Preferences>DevelopmentOptions en Robocode,
		// indicando el nÃ³mbre del paquete (si lo hay) y del robot.  En nuestro caso suponemos como nombre 
		// prueba.Bot3*
		// Al nombre de los robots definidos por el usuario siempre hay que aÃ±adirle el carÃ¡cter * al final. 
		RobotSpecification[] modelRobots =
				engine.getLocalRepository
				("sample.SittingDuck,prueba.Bot3*");
		
		// Incluiremos un robot sittingDuck por obstÃ¡culo, mÃ¡s nuestro propio robot.
		RobotSpecification[] existingRobots =	new RobotSpecification[numObstaculos+1];
		RobotSetup[] robotSetups 			= 	new RobotSetup[numObstaculos+1];
		
		int[][] matriz = p.getMatriz();
		
		/*
	     * Creamos primero nuestro propio robot y lo colocamos en la posiciÃ³n inicial del problema,
	     * que deberÃ¡ estar libre de obstÃ¡culo.
		 */
		
		int indice = 0;
		
		existingRobots[indice] = modelRobots[1];

		//double fila = 125.0, columna = 125.0, 
		double arriba = 0.0;
		
		robotSetups[indice]=new RobotSetup(  (double) 50*p.getFilaIni() +25	   ,        //AQUÃ� DEBE FIGURAR LA FILA EN PIXELS CORRESPONDIENTE A LA POSICIÃ“N INICIAL DEL ROBOT
											 (double) 50* p.getColumIni()+25		   ,        //AQUÃ� DEBE FIGURAR LA COLUMNA EN PIXELS CORRESPONDIENTE A LA POSICIÃ“N INICIAL DEL ROBOT    
											 arriba);              //orientaciÃ³n inicial

		
		/*
	     * Creamos un robot sittingDuck por cada obstÃ¡culo, y lo colocamos en el centro de la 
	     * celda correspondiente.
		 */
		
		
		//AQUÃ� SE DEBERÃ�A CREAR UN ROBOT sittingDuck EN LA POSICIÃ“N DE CADA OBSTÃ�CULO DE LA MALLA.
		//
		//
		//  ...
		//
		//
		
		//double sittingDuckFila , sittingDuckColumna;
		
		indice++;
		for (int f = 0; f < nFil; f++){
			for (int c = 0; c < nCol; c++){
				
				//HEMOS PUESTO SOLO UNO
				if((matriz[f][c]) == 1) {
					
					existingRobots[indice] = modelRobots[0];   //sittingDuck
					double fi = f;
					double co = c;					
					robotSetups[indice]=  new RobotSetup(50*fi+25,50*co+25,arriba);   //AQUÃ� DEBE FIGURAR LA FILA EN PIXELS CORRESPONDIENTE AL CENTRO DE LA CELDA DONDE HAY OBSTÃ�CULO
																		//AQUÃ� DEBE FIGURAR LA FILA EN PIXELS CORRESPONDIENTE AL CENTRO DE LA COLUMNA DONDE HAY OBSTÃ�CULO
																		//orientaciÃ³n
					indice++;
					
				}//if mapa
		}//for c
	}//for f
			
		Algoritmo a = new Algoritmo();
		ArrayList<Nodo> camino = a.buscaSolucion(p.getFilaIni(), p.getFilaFin(),  p.getColumIni(), p.getColumFin(), matriz);
		
			//CON ESTE BLOQUE DE CODIGO SE IMPRIME POR PANTALLA LA MATRIZ GENERADA
		for(int i = 0; i < p.getNumFil(); i++) {
			for(int j = 0; j < p.getNumCol(); j++) {
				System.out.print("[" + matriz[i][j] + "] ");
			}
			System.out.println();
		}
		
		System.out.println();
		
		System.out.println("Generados " + (indice - 1) + " sitting ducks.");		
		System.out.println("Posicion inicial: "+p.filaIni + p.columIni);
		System.out.println("Posicion final: "+p.filaFin + p.columFin);
		System.out.print("Camino: ");
		for(int i = 0; i < camino.size(); i++) {
			System.out.print(camino.get(i));
		}
	
		/* 
		 * Crear y desarrollar la batalla con los robots antes definidos
		 */
		BattleSpecification battleSpec =
				new BattleSpecification(battlefield,
						numberOfRounds,
						inactivityTime,
						gunCoolingRate,
						sentryBorderSize,
						hideEnemyNames,
						existingRobots,
						robotSetups);
		
		// Ejecutar la simulaciÃ³n el tiempo especificado
		engine.runBattle(battleSpec, true); 
		// Cerrar la simulaciÃ³n
		engine.close();
		// Asegurarse de que la MV de Java se cierra adecuadamente.
		System.exit(0);
	}
	
}
