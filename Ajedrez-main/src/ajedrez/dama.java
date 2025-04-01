package ajedrez;

public class dama extends pieza {

	public dama(int fila, int columna, boolean blanca) {
		super(fila, columna, blanca);
	}

	@Override
	public boolean validarMovimiento(int nF, int nC, tablero tablero) {
		if (!tablero.Tablerito(nF, nC)) {
			return false;
		}

		int mF = Math.abs(nF - this.fila);
		int mC = Math.abs(nC - this.columna);

		if (mF == 0 || mC == 0 || mF == mC) {
			if (mF == 0 && mC == 0) {
				return false;
			}
			int filita = mF == 0 ? 0 : (nF - this.fila) / mF;
			int columnita = mC == 0 ? 0 : (nC - this.columna) / mC;
			int abc = this.fila + filita;
			int def = this.columna + columnita;
			while (abc != nF || def != nC) {
				if (tablero.getpieza(abc, def) != 0) {
					return false;
				}
				abc += filita;
				def += columnita;
			}
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
