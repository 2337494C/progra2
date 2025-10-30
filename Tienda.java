import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tienda {
    private List<Producto> catalogo = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void agregarProductoCatalogo(Producto producto) {
        catalogo.add(producto);
    }

    public void mostrarCatalogo() {
        System.out.println("\n--- CATÁLOGO DE PRODUCTOS ---");
        int i = 1;
        for (Producto p : catalogo) {
            System.out.println(i + ". " + p);
            i++;
        }
    }

    public void iniciar() {
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        Cliente cliente = new Cliente(nombre);

        int opcion;
        do {
            System.out.println("\n--- MENÚ DE TIENDA ---");
            System.out.println("1. Ver catálogo");
            System.out.println("2. Agregar producto al carrito");
            System.out.println("3. Ver carrito");
            System.out.println("4. Realizar pedido");
            System.out.println("5. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    mostrarCatalogo();
                    break;
                case 2:
                    mostrarCatalogo();
                    System.out.print("Seleccione el número del producto: ");
                    int indice = Integer.parseInt(scanner.nextLine()) - 1;
                    System.out.print("Cantidad: ");
                    int cantidad = Integer.parseInt(scanner.nextLine());
                    if (indice >= 0 && indice < catalogo.size()) {
                        cliente.getCarrito().agregarProducto(catalogo.get(indice), cantidad);
                    } else {
                        System.out.println("Producto no válido.");
                    }
                    break;
                case 3:
                    cliente.getCarrito().mostrarContenido();
                    break;
                case 4:
                    Pedido pedido = cliente.realizarPedido();
                    pedido.confirmarPedido();
                    break;
                case 5:
                    System.out.println("Gracias por visitar la tienda, " + cliente.getNombre());
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);
    }

    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        tienda.agregarProductoCatalogo(new Producto("Laptop", 15000, 5));
        tienda.agregarProductoCatalogo(new Producto("Mouse", 300, 10));
        tienda.agregarProductoCatalogo(new Producto("Teclado", 700, 8));
        tienda.agregarProductoCatalogo(new Producto("Monitor", 5000, 4));

        tienda.iniciar();
    }
public void cargarProductosDesdeArchivo(String ruta)
{
    try (BufferredReader br = new BufferedReader(new FileReader (ruta)))
    {
       wwhile ((linea = br.readLine()) != null){
        String[] datos = linea.split(",");
        if (datos.length == 3) {
            String nombre = datos[0].trim():
            double precio = Double.parsenDouble(datos[1].trim());
            int stock = Integer.parseInt(datos[2].trim());
            catalogo.add(new Producto (nombre, precio, stock));
        }
       }
    }  ccatch (I0Exception e){
        System.out.println("Error al leer el archivo de productos: " + e.getMessage());
    }
}

//public void cargarClientesDesdeArchivos
}