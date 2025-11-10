import java.util.Scanner;
import java.util.UUID;

public class SistemaCine 
{
private Cartelera cartelera = new Cartelera();
private Bitacora bitacora = new Bitacora();
private Scanner scanner = new Scanner(System.in);

public void iniciar() 
{
        cartelera.cargar();
        int opcion;
        do 
        {
                System.out.println("\n--- MENU CINE ---");
                System.out.println("1. Consultar cartelera");
                System.out.println("2. Consultar horarios por pelicula");
                System.out.println("3. Disponibilidad y comprar boleto");
                System.out.println("4. Clientes");
                System.out.println("5. Proximos estrenos");
                System.out.println("0. Salir");
                System.out.print("Opcion: ");
                opcion = leerEntero();

                switch (opcion) 
                {
                        case 1 -> cartelera.mostrarPeliculas();
                        case 2 -> consultarHorarios();
                        case 3 -> comprarBoleto();
                        case 4 -> cartelera.mostrarClientes();
                        case 5 -> cartelera.mostrarEstrenos();
                        case 0 -> System.out.println("Adios.");
                        default -> System.out.println("opcion invalida.");
                }
        } while (opcion != 0);
}

private void consultarHorarios() 
{
        System.out.print("Nombre de la pelicula: ");
        String nombre = scanner.nextLine();
        cartelera.mostrarFuncionesPorPelicula(nombre);
}

private void comprarBoleto() 
{
        var funcs = cartelera.getFunciones();
        if (funcs.isEmpty()) 
        { 
                System.out.println("(sin funciones)");
                return; 
        }

        System.out.println("\nFunciones:");
        for (int i = 0; i < funcs.size(); i++) 
        {
                System.out.println((i + 1) + ". " + funcs.get(i));
        }
        System.out.print("Elige numero de funcion: ");
        int idx = leerEntero();
        Funcion f = cartelera.getFuncionPorIndice(idx);
        if (f == null) 
        { 
                System.out.println("opcion no valida"); 
                return; 
        }
        int disponibles = f.getDisponibles();
        System.out.println("Disponibles: " + disponibles);
        if (disponibles == 0) 
        { 
                System.out.println("sin lugares");
                return; 
        }
        System.out.print("ID de cliente: ");
        String id = scanner.nextLine().trim();
        Cliente cli = cartelera.getClientes().stream().filter(c -> c.getId().equalsIgnoreCase(id)).findFirst().orElse(null);
        if (cli == null) 
        { 
                System.out.println("cliente no encontrado"); 
                return; 
        }
        int asiento = f.reservarAsiento();
        if (asiento == -1);
        { 
                System.out.println("sin lugares"); 
                return; 
        }
        String folio = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Boleto boleto = new Boleto(folio, cli, f, asiento);
        bitacora.registrar(boleto);
        System.out.println("compra exitosa: " + boleto);
}

private int leerEntero() 
{
        try 
        { 
                return Integer.parseInt(scanner.nextLine().trim()); 
        } catch (Exception e) 
        { 
                return -1; 
        }
}

public static void main(String[] args) 
{
        new SistemaCine().iniciar();
}
}