package ajedrez;

public class pieza {
	protected int fila;
	protected int columna;
	protected boolean blanca;

	public pieza(int fila, int columna, boolean blanca) {
		super();
		this.fila = fila;
		this.columna = columna;
		this.blanca = blanca;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public boolean isBlanca() {
		return blanca;
	}

	public void setBlanca(boolean blanca) {
		this.blanca = blanca;
	}

	public boolean validarMovimiento(int nF, int nC, tablero tablero) {

		return false;
	}
}
