package prueba;

import java.util.Random;

public class Problema {
	protected int numFil, numCol, numObstaculos, matriz[][],filaIni, filaFin, columIni, columFin;
	long semilla;
	Random ram;
	
	public Problema(long semilla, int numFil, int numCol, int numObstaculos) {
		this.numFil = numFil;
		this.numCol = numCol;
		this.numObstaculos = numObstaculos;
		this.semilla = semilla;
		this.matriz = new int [numFil][numCol]; 
		ram = new Random (semilla);
		for (int i=0; i<numFil; i++) {
			for (int j=0; j<numCol; j++) {
				matriz[i][j] = 0;
			}
		}
		posiciones();
		obstaculos();
		
	}
	
	private void posiciones() {
		filaIni = ram.nextInt(numFil); //Para crear posicion inicial
		columIni = ram.nextInt(numCol);
		filaFin = ram.nextInt(numFil); //Para crear posicion final
		columFin = ram.nextInt(numCol);
		
	}
	
	public int getNumFil() {
		return numFil;
	}

	public int getNumCol() {
		return numCol;
	}

	public int getFilaIni() {
		return filaIni;
	}

	public int getFilaFin() {
		return filaFin;
	}

	public int getColumIni() {
		return columIni;
	}

	public int getColumFin() {
		return columFin;
	}

	private void obstaculos() {
		int cont = 0;
		while(cont < numObstaculos) {
			int filaObsta = ram.nextInt(numFil); //Para crear los obstaculos
			int columObsta = ram.nextInt(numCol);
			
			if((matriz[filaObsta][columObsta] == 0) && !(filaObsta==filaIni && columObsta==columIni) && !(filaObsta==filaFin && columObsta==columFin)) {
				matriz[filaObsta][columObsta] = 1;
				cont++;
			}
		}
	}
}
