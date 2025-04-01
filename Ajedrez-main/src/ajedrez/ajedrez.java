package ajedrez;

import java.awt.EventQueue;
import java.util.Scanner;

public class ajedrez {

	public static void main(String[] args) {
		
		tablero tablerito = new tablero();
		tablerito.crearTableroGrafico();;
		tablerito.imprimirTablero();
		tablerito.realizarMovimiento();

	}
}
//Creado por Daniel Vivas Álvarez