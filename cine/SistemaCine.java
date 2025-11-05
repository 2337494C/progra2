import java.util.Scanner;

public class SistemaCine {
    private Cartelera cartelera = new Cartelera();
        private Scanner scanner = new Scanner(System.in);

            public void iniciar() {
                    cartelera.cargar();

                            int opcion;
                                    do {
                                                System.out.println("\n--- MENÚ CINE ---");
                                                            System.out.println("1. Consultar cartelera");
                                                                        System.out.println("2. Consultar horarios por película");
                                                                                    System.out.println("0. Salir");
                                                                                                System.out.print("Opción: ");
                                                                                                            opcion = leerEntero();

                                                                                                                        switch (opcion) {
                                                                                                                                        case 1 -> cartelera.mostrarPeliculas();
                                                                                                                                                        case 2 -> consultarHorarios();
                                                                                                                                                                        case 0 -> System.out.println("Adiós.");
                                                                                                                                                                                        default -> System.out.println("Opción inválida.");
                                                                                                                                                                                                    }
                                                                                                                                                                                                            } while (opcion != 0);
                                                                                                                                                                                                                }

                                                                                                                                                                                                                    private void consultarHorarios() {
                                                                                                                                                                                                                            System.out.print("Nombre de la película: ");
                                                                                                                                                                                                                                    String nombre = scanner.nextLine();
                                                                                                                                                                                                                                            cartelera.mostrarFuncionesPorPelicula(nombre);
                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                    private int leerEntero() {
                                                                                                                                                                                                                                                            try {
                                                                                                                                                                                                                                                                        String s = scanner.nextLine();
                                                                                                                                                                                                                                                                                    return Integer.parseInt(s.trim());
                                                                                                                                                                                                                                                                                            } catch (Exception e) {
                                                                                                                                                                                                                                                                                                        return -1;
                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                    }

                                                                                                                                                                                                                                                                                                                        public static void main(String[] args) {
                                                                                                                                                                                                                                                                                                                                new SistemaCine().iniciar();
                                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                                    }