package ajedrez;

public class rey extends pieza {

	public rey(int fila, int columna, boolean blanca) {
		super(fila, columna, blanca);
	}

	@Override
	public boolean validarMovimiento(int nF, int nC, tablero tablero) {
		if (!tablero.Tablerito(nF, nC)) {
			return false;
		}

		int mF = Math.abs(nF - this.fila);
		int mC = Math.abs(nC - this.columna);

		if ((mF == 1 && mC == 0) || (mF == 0 && mC == 1) || (mF == 1 && mC == 1)) {
			int pieza = tablero.getpieza(nF, nC);
			if (pieza != 0) {
				boolean equipo = (pieza > 0 && this.blanca) || (pieza < 0 && !this.blanca);
				if (!equipo) {
					System.out.println("Eliminaste una ficha del enemigo");
					return true;
				} else {
					return false;
				}
			}
			return true;
		}

		return false;
	}
}
