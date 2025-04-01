package ajedrez;

import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class tablero extends JFrame {
	private int[][] casillas;
	private boolean cambiardejugador;
	private JPanel contentPane;
	private static JButton[][] tableroGrafico;
	private static String imagen = "./Ajedrez/imagenes/FichaPeonNegra.png";
	private static ImageIcon icon = new ImageIcon(imagen);
	private static String imagen2 = "./Ajedrez/imagenes/FichaPeon.png";
	private static ImageIcon icon2 = new ImageIcon(imagen2);
	private static int filaPieza;
	private static int columnaPieza;
	private static int movimientoFila;
	private static int movimientoColumna;
	private int origen_destino_MouseListener = 0;
	JButton btnNewButton = new JButton("Pagina Web");
	private static int continuar = 0;
	private static String jugador1;
	private static String jugador2;

	public tablero(int[][] casillas, boolean ajugarjeje) {
		super();
		this.casillas = casillas;
		this.cambiardejugador = ajugarjeje;

	}

	public boolean Tablerito(int fila, int columna) {
		return fila >= 0 && fila < casillas.length && columna >= 0 && columna < casillas[0].length;
	}

	public tablero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1200, 980);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		setTitle("Ajedrez-Daniel Vivas Álvarez");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tableroGrafico = new JButton[8][8];
		int buttonSize = 109;
		int startX = 65;
		int startY = 31;
		Color blanco = Color.WHITE;
		Color negro = Color.BLACK;
        JTextField txtFechaInicio = new JTextField("Fecha Inicio: 23 Feb 2024");
        txtFechaInicio.setBounds(1000, 430, 150, 30);
        txtFechaInicio.setEditable(false);
        contentPane.add(txtFechaInicio);
        JTextField txtFechaFinal = new JTextField("Fecha Final: 3 Jun 2024");
        txtFechaFinal.setBounds(1000, 590, 150, 30);
        txtFechaFinal.setEditable(false);
        contentPane.add(txtFechaFinal);
		btnNewButton.setBounds(1000, 500, 150, 50);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File web = new File("./Ajedrez/Ajedrezhtml/ajedrez.html");
                if (web.exists()) {
                    try {
						Desktop.getDesktop().open(web);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
                } else {
                    System.out.println("El archivo no existe.");
                }
			}
		});

		for (int i = 0; i < 8; i++) {
			for (int w = 0; w < 8; w++) {
				tableroGrafico[i][w] = new JButton("");
				tableroGrafico[i][w].setBounds(startX + w * buttonSize, startY + i * buttonSize, buttonSize,
						buttonSize);
				tableroGrafico[i][w].setBackground((i + w) % 2 == 0 ? blanco : negro);

				tableroGrafico[i][w].addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						if (origen_destino_MouseListener == 0) {
							filaPieza = ((JButton) e.getSource()).getY() / buttonSize;
							columnaPieza = ((JButton) e.getSource()).getX() / buttonSize;
//		                        System.out.println("fila origen y columna origen "+ filaPieza+ " "+columnaPieza);
							origen_destino_MouseListener = 1;
						} else {
							movimientoFila = ((JButton) e.getSource()).getY() / buttonSize;
							movimientoColumna = ((JButton) e.getSource()).getX() / buttonSize;
//		                        System.out.println("fila destino y columna destino "+ movimientoFila+ " "+movimientoColumna);
							origen_destino_MouseListener = 0;
							continuar = 1;

						}
					}
				});

				contentPane.add(tableroGrafico[i][w]);

			}
		}

		casillas = new int[8][8];
		casillas[0][0] = 1;// torre
		casillas[0][1] = 2;// caballo
		casillas[0][2] = 3;// alfil
		casillas[0][3] = 4;// reina
		casillas[0][4] = 5;// rey
		casillas[0][5] = 3;// alfil
		casillas[0][6] = 2;// caballo
		casillas[0][7] = 1;// torre
		for (int i = 0; i < 8; i++) {
			casillas[1][i] = 6;// peón
			casillas[6][i] = -6;// peón
		}
		for (int w = 2; w < 5 + 1; w++) {
			for (int z = 0; z < 7 + 1; z++) {
				casillas[w][z] = 0;// vacío
			}
		}
		casillas[7][0] = -1;// torre
		casillas[7][1] = -2;// caballo
		casillas[7][2] = -3;// alfil
		casillas[7][3] = -4;// reina
		casillas[7][4] = -5;// rey
		casillas[7][5] = -3;// alfil
		casillas[7][6] = -2;// caballo
		casillas[7][7] = -1;// torre
		imprimirTablero();
	}

	public void jugadores() {

	do {
	    
	    try {
	    	jugador1 = JOptionPane.showInputDialog(null, "Ingrese el nombre del jugador 1 (blancas):", "Jugador1", JOptionPane.INFORMATION_MESSAGE, icon2, null, "").toString();
		} catch (Exception e) {
			jugador1=null;
		}
	} while (jugador1 == null || jugador1.trim().isEmpty());

	do {
		try {
			 jugador2 = JOptionPane.showInputDialog(null, "Ingrese el nombre del jugador 2 (negras):", "Jugador2", JOptionPane.INFORMATION_MESSAGE, icon, null, "").toString();
		} catch (Exception e) {
			jugador2=null;
		}
	   
	} while (jugador2 == null || jugador2.trim().isEmpty());

		


	}

	public int[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(int[][] casillas) {
		this.casillas = casillas;
	}

	public void setpieza(int pieza, int fila, int columna) {
		casillas[fila - 1][columna - 1] = pieza;

	}

	public int getpieza(int fila, int columna) {
		int pieza = casillas[fila][columna];
		return pieza;

	}

	public void crearTableroGrafico() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablero frame = new tablero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		jugadores();
	}

	public void imprimirTablero() {

		for (int w = 0; w < 7 + 1; w++) {
			for (int z = 0; z < 7 + 1; z++) {
				int piezas = (casillas[w][z]);
				switch (piezas) {
				case 1:
					tableroGrafico[w][z]
							.setIcon(new ImageIcon("./Ajedrez/imagenes/Torre.png"));
					break;
				case 2:
					tableroGrafico[w][z]
							.setIcon(new ImageIcon("./Ajedrez/imagenes/Caballo.png"));
					break;
				case 3:
					tableroGrafico[w][z]
							.setIcon(new ImageIcon("./Ajedrez/imagenes/Alfil.png"));
					break;
				case 4:
					tableroGrafico[w][z]
							.setIcon(new ImageIcon("./Ajedrez/imagenes/Reina.png"));
					break;
				case 5:
					tableroGrafico[w][z]
							.setIcon(new ImageIcon("./Ajedrez/imagenes/Rey.png"));
					break;
				case 6:
					tableroGrafico[w][z].setIcon(
							new ImageIcon("./Ajedrez/imagenes/FichaPeon.png"));
					break;
				case -1:
					tableroGrafico[w][z].setIcon(
							new ImageIcon("./Ajedrez/imagenes/TorreNegra.png"));
					break;
				case -2:
					tableroGrafico[w][z].setIcon(
							new ImageIcon("./Ajedrez/imagenes/CaballoNegro.png"));
					break;
				case -3:
					tableroGrafico[w][z].setIcon(
							new ImageIcon("./Ajedrez/imagenes/AlfilNegro.png"));
					break;
				case -4:
					tableroGrafico[w][z].setIcon(
							new ImageIcon("./Ajedrez/imagenes/ReinaNegra.png"));
					break;
				case -5:
					tableroGrafico[w][z].setIcon(
							new ImageIcon("./Ajedrez/imagenes/ReyNegro.png"));
					break;
				case -6:
					tableroGrafico[w][z].setIcon(
							new ImageIcon("./Ajedrez/imagenes/FichaPeonNegra.png"));
					break;

				default:
					System.out.print(" ");
					break;
				}
			}
			System.out.println("");
		}

	}

	public void ganadorBlancas() {
		String blancas = "Blancas";
		String negras = "Negras";
		String ganador = "Ganador";
		String perdedor = "Perdedor";
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ajedrez", "root", "");
			Statement consultas = conexion.createStatement();
			consultas.executeUpdate("INSERT INTO jugadores (nombre, resultado, equipo, rival) VALUES ('" + jugador1
					+ "', '" + ganador + "', '" + blancas + "','" + jugador2 + "')");
			consultas.executeUpdate("INSERT INTO jugadores (nombre, resultado, equipo, rival) VALUES ('" + jugador2
					+ "', '" + perdedor + "', '" + negras + "','" + jugador1 + "')");
			ResultSet rs = consultas.executeQuery("SELECT * FROM jugadores");
			StringBuilder message = new StringBuilder();
			while (rs.next()) {
				message.append("Nombre: ").append(rs.getString("nombre")).append(", ");
				message.append("Resultado: ").append(rs.getString("resultado")).append(", ");
				message.append("Equipo: ").append(rs.getString("equipo")).append(", ");
				message.append("Rival: ").append(rs.getString("rival")).append("\n");
			}

			JOptionPane.showMessageDialog(null, message.toString(), "Datos de Jugadores",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void ganadorNegras() {
		String blancas = "Blancas";
		String negras = "Negras";
		String ganador = "Ganador";
		String perdedor = "Perdedor";
		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1/ajedrez", "root", "");
			Statement consultas = conexion.createStatement();
			consultas.executeUpdate("INSERT INTO jugadores (nombre, resultado, equipo, rival) VALUES ('" + jugador1
					+ "', '" + perdedor + "', '" + blancas + "','" + jugador2 + "')");
			consultas.executeUpdate("INSERT INTO jugadores (nombre, resultado, equipo, rival) VALUES ('" + jugador2
					+ "', '" + ganador + "', '" + negras + "','" + jugador1 + "')");
			ResultSet rs = consultas.executeQuery("SELECT * FROM jugadores");
			StringBuilder message = new StringBuilder();
			while (rs.next()) {
				message.append("Nombre: ").append(rs.getString("nombre")).append(", ");
				message.append("Resultado: ").append(rs.getString("resultado")).append(", ");
				message.append("Equipo: ").append(rs.getString("equipo")).append(", ");
				message.append("Rival: ").append(rs.getString("rival")).append("\n");
			}

			JOptionPane.showMessageDialog(null, message.toString(), "Datos de Jugadores",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void realizarMovimiento() {
		JOptionPane.showMessageDialog(null, "Turno del Jugador 1 (blancas)", "",
				JOptionPane.INFORMATION_MESSAGE, icon2);
		boolean rey = true;
		try {

			do {

				if (!Tablerito(filaPieza, columnaPieza)) {
					System.out.println("No puedes hacer eso");
					continue;
				}

				if (!Tablerito(movimientoFila, movimientoColumna)) {
					JOptionPane.showMessageDialog(null, "No puedes hacer eso");
					continue;
				}

				while (continuar == 0) {
					Thread.sleep(100);
				}
				continuar = 0;

				int pieza = casillas[filaPieza][columnaPieza];
				pieza pieza2;

				if ((cambiardejugador && pieza > 0) || (!cambiardejugador && pieza < 0)) {
					JOptionPane.showMessageDialog(null,
							"Pieza seleccionada no es del jugador actual. Intenta de nuevo.");
					continue;
				}

				switch (Math.abs(pieza)) {
				case 1:
					pieza2 = new torre(filaPieza, columnaPieza, pieza > 0);
					break;
				case 2:
					pieza2 = new caballo(filaPieza, columnaPieza, pieza > 0);
					break;
				case 3:
					pieza2 = new alfil(filaPieza, columnaPieza, pieza > 0);
					break;
				case 4:
					pieza2 = new dama(filaPieza, columnaPieza, pieza > 0);
					break;
				case 5:
					pieza2 = new rey(filaPieza, columnaPieza, pieza > 0);
					break;
				case 6:
					pieza2 = new peon(filaPieza, columnaPieza, pieza > 0, false);
					break;
				default:
					System.out.println("Pieza no valida");
					continue;
				}

				if (pieza2.validarMovimiento(movimientoFila, movimientoColumna, this)) {

					System.out.println("Se movio la pieza");

					if (Math.abs(casillas[movimientoFila][movimientoColumna]) == 5) {
						rey = false;
						if (!cambiardejugador) {
							JOptionPane.showMessageDialog(null, "¡Jugador 1 (blancas) gana!");
							casillas[movimientoFila][movimientoColumna] = casillas[filaPieza][columnaPieza];
							casillas[filaPieza][columnaPieza] = 0;
							tableroGrafico[filaPieza][columnaPieza].setIcon(null);
							imprimirTablero();
							ganadorBlancas();
							break;
						} else {
							JOptionPane.showMessageDialog(null, "¡Jugador 2 (negras) gana!");
							casillas[movimientoFila][movimientoColumna] = casillas[filaPieza][columnaPieza];
							casillas[filaPieza][columnaPieza] = 0;
							tableroGrafico[filaPieza][columnaPieza].setIcon(null);
							imprimirTablero();
							ganadorNegras();
							break;
						}
					}
					casillas[movimientoFila][movimientoColumna] = casillas[filaPieza][columnaPieza];
					casillas[filaPieza][columnaPieza] = 0;
					tableroGrafico[filaPieza][columnaPieza].setIcon(null);
					if (Math.abs(pieza) == 6 && (movimientoFila == 0 || movimientoFila == 7)) {
						transformacionDelPeon(movimientoFila, movimientoColumna);
					}
					imprimirTablero();

				} else {
					System.out.println("No se puede hacer");
					continue;
				}

				cambiardejugador = !cambiardejugador;

				if (cambiardejugador) {
					JOptionPane.showMessageDialog(null, "Turno del Jugador 2 (negras)", "",
							JOptionPane.INFORMATION_MESSAGE, icon);
				} else {
					JOptionPane.showMessageDialog(null, "Turno del Jugador 1 (blancas)", "",
							JOptionPane.INFORMATION_MESSAGE, icon2);
				}
			} while (rey);

		} catch (Exception e) {
			System.out.println("Error,paso algo que no deberia");
			realizarMovimiento();

		}

	}

	public boolean isAjugarjeje() {
		return cambiardejugador;
	}

	public void setAjugarjeje(boolean ajugarjeje) {
		this.cambiardejugador = ajugarjeje;
	}

	public void transformacionDelPeon(int fila, int columna) {
		try {
			int opcion;
			do {
				String mensaje = "Has llegado a la última fila, puedes transformarte en esto:\n" + "1. Torre\n"
						+ "2. Caballo\n" + "3. Alfil\n" + "4. Dama";
				String opcionPeon = JOptionPane.showInputDialog(null, mensaje + "\nIngresa el número de la opción:");
				opcion = Integer.parseInt(opcionPeon);
			} while (opcion < 1 || opcion > 4);

			int transformacion;
			switch (opcion) {
			case 1:
				if (cambiardejugador) {
					transformacion = -1;
					break;
				} else {
					transformacion = 1;
					break;
				}

			case 2:
				if (cambiardejugador) {
					transformacion = -2;
					break;
				} else {
					transformacion = 2;
					break;
				}
			case 3:
				if (cambiardejugador) {
					transformacion = -3;
					break;
				} else {
					transformacion = 3;
					break;
				}
			case 4:
				if (cambiardejugador) {
					transformacion = -4;
					break;
				} else {
					transformacion = 4;
					break;
				}
			default:
				System.out.println("Opción no válida.");
				return;
			}
			casillas[fila][columna] = transformacion;

		} catch (Exception e) {
			transformacionDelPeon(fila, columna);
		}

	}
}
