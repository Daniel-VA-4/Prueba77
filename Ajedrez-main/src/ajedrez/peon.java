package ajedrez;

public class peon extends pieza {
	private boolean primerturno = true;

	public peon(int fila, int columna, boolean blanca, boolean primerturno) {
		super(fila, columna, blanca);
		this.primerturno = primerturno;
	}

	public boolean getPrimerturno() {
		return primerturno;
	}

	public void setPrimerturno(boolean primerturno) {
		this.primerturno = primerturno;
	}

	@Override
	public boolean validarMovimiento(int nF, int nC, tablero tablero) {

		if (!tablero.Tablerito(nF, nC)) {
			return false;
		}

		int mF = nF - this.fila;
		int mC = Math.abs(nC - this.columna);

		if (blanca) {
			if (mC == 0 && tablero.getpieza(nF, nC) == 0) {
				if (mF == 1) {
					return true;
				} else if (fila == 1 && mF == 2 && tablero.getpieza(fila + 1, nC) == 0) {
					return true;
				}
			} else if (mC == 1 && mF == 1 && tablero.getpieza(nF, nC) != 0) {
				System.out.println("Eliminaste una ficha del enemigo");
				return true;
			}

		} else {
			if (mC == 0 && tablero.getpieza(nF, nC) == 0) {
				if (mF == -1) {
					return true;
				} else if (fila == 6 && mF == -2 && tablero.getpieza(fila - 1, nC) == 0) {
					return true;
				}
			} else if (mC == 1 && mF == -1 && tablero.getpieza(nF, nC) != 0) {
				System.out.println("Eliminaste una ficha del enemigo");
				return true;
			}
		}

		return false;
	}
}
