package ajedrez;

public class torre extends pieza {

	public torre(int fila, int columna, boolean blanca) {
		super(fila, columna, blanca);
	}

	@Override
	public boolean validarMovimiento(int nF, int nC, tablero tablero) {
		if (!tablero.Tablerito(nF, nC)) {
			return false;
		}

		int mF = Math.abs(nF - this.fila);
		int mC = Math.abs(nC - this.columna);

		if ((mF == 0 && mC != 0) || (mF != 0 && mC == 0)) {
			if (comprobarPiezaIntermedia(mF == 0, nF, nC, tablero)) {
				if (tablero.getpieza(nF, nC) != 0) {
					boolean equipo = (tablero.getpieza(nF, nC) > 0 && this.blanca)
							|| (tablero.getpieza(nF, nC) < 0 && !this.blanca);
					if (!equipo) {
						System.out.println("Capturaste una ficha del enemigo");
						return true;
					} else {
						return false;
					}
				} else {
					return true;
				}
			} else {
				return false;
			}
		}

		return false;
	}

	public boolean comprobarPiezaIntermedia(boolean comprobar, int nF, int nC, tablero tablero) {
		if (comprobar) {
			int cI = Math.min(this.columna, nC) + 1;
			int cF = Math.max(this.columna, nC);

			for (int col = cI; col < cF; col++) {
				if (tablero.getpieza(nF, col) != 0) {
					return false;
				}
			}
			return true;
		} else {
			int fI = Math.min(this.fila, nF) + 1;
			int fF = Math.max(this.fila, nF);

			for (int fila = fI; fila < fF; fila++) {
				if (tablero.getpieza(fila, nC) != 0) {
					return false;
				}
			}
			return true;
		}
	}

}