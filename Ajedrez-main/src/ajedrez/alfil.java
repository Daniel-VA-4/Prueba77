package ajedrez;
public class alfil extends pieza {

    public alfil(int fila, int columna, boolean blanca) {
        super(fila, columna, blanca);
    }

    @Override
    public boolean validarMovimiento(int nF, int nC, tablero tablero) {
        if (!tablero.Tablerito(nF, nC)) {
            return false;
        }

        int mF = Math.abs(nF - this.fila);
        int mC = Math.abs(nC - this.columna);

        if (mF == mC) {
            int pasoF = (nF > this.fila) ? 1 : -1;
            int pasoC = (nC > this.columna) ? 1 : -1;

            for (int fila = this.fila + pasoF, columna = this.columna + pasoC;
                 fila != nF;
                 fila += pasoF, columna += pasoC) {

                if (tablero.getpieza(fila, columna) != 0) {
                    boolean equipo = (tablero.getpieza(fila, columna) > 0 && !this.blanca) || 
                                     (tablero.getpieza(fila, columna) < 0 && this.blanca);
                    if (equipo) {
                        return false;
                    }
                }
            }

          
            if (tablero.getpieza(nF, nC) != 0) {
                boolean equipoFinal = (tablero.getpieza(nF, nC) > 0 && !this.blanca) || 
                                      (tablero.getpieza(nF, nC) < 0 && this.blanca);
                if (!equipoFinal) {
                    return false;
                }
            }

            return true; 
        }
        return false;
    }
}
