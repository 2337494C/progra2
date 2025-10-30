import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Nuevos imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tienda 
{
    private List<Producto> catalogo = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    //Lista de clientes cargados desde archivo
    private List<Cliente> clientes = new ArrayList<>();

    //Agregar producto al catálogo manualmente
    public void agregarProductoCatalogo(Producto producto) 
    {
        catalogo.add(producto);
    }

    //Mostrar catálogo de productos
    public void mostrarCatalogo() 
    {
        System.out.println("\n--- CATÁLOGO DE PRODUCTOS ---");
        if (catalogo.isEmpty()) 
        {
            System.out.println("(catálogo vacío)");
            return;
        }
        int i = 1;
        for (Producto p : catalogo) {
            System.out.println(i + ". " + p);
            i++;
        }
    }

    //Cargar productos desde archivo .txt
    public void cargarProductosDesdeArchivo(String ruta) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) 
        {
            String linea;
            while ((linea = br.readLine()) != null) 
            {
                String[] datos = linea.split(";");
                if (datos.length == 3) 
                {
                    String nombre = datos[0].trim();
                    double precio = Double.parseDouble(datos[1].trim());
                    int stock = Integer.parseInt(datos[2].trim());
                    catalogo.add(new Producto(nombre, precio, stock));
                }
            }
            System.out.println("Productos cargados desde: " + ruta);
        } catch (IOException e) 
        {
            System.out.println("Error al leer archivo de productos: " + e.getMessage());
        }
    }

    //Cargar clientes desde archivo .txt
    public void cargarClientesDesdeArchivo(String ruta) 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) 
        {
            String linea;
            while ((linea = br.readLine()) != null) 
            {
                String nombre = linea.trim();
                if (!nombre.isEmpty()) 
                {
                    clientes.add(new Cliente(nombre));
                }
            }
            System.out.println("Clientes cargados desde: " + ruta);
        } catch (IOException e) 
        {
            System.out.println("Error al leer archivo de clientes: " + e.getMessage());
        }
    }

    //Mostrar lista de clientes cargados
    public void mostrarClientes() 
    {
        if (clientes.isEmpty()) {
            System.out.println("(No hay clientes cargados)");
            return;
        }
        System.out.println("\n--- CLIENTES CARGADOS ---");
        int i = 1;
        for (Cliente c : clientes) 
        {
            System.out.println(i + ". " + c.getNombre());
            i++;
        }
    }

    //Seleccionar cliente cargado
    private Cliente seleccionarCliente() 
    {
        if (clientes.isEmpty()) 
        {
            System.out.print("No hay clientes cargados. Ingresa un nombre: ");
            String nombreNuevo = scanner.nextLine();
            Cliente nuevo = new Cliente(nombreNuevo);
            clientes.add(nuevo);
            return nuevo;
        }

        System.out.println("\n--- SELECCIONAR CLIENTE ---");
        int i = 1;
        for (Cliente c : clientes) 
        {
            System.out.println(i + ". " + c.getNombre());
            i++;
        }
        System.out.print("Número de cliente: ");
        int idx = Integer.parseInt(scanner.nextLine()) - 1;
        if (idx < 0 || idx >= clientes.size()) 
        {
            System.out.println("Cliente no válido. Se usará el primero.");
            idx = 0;
        }
        return clientes.get(idx);
    }

    //Modificar producto del catalogo
    public void modificarProducto() 
    {
        mostrarCatalogo();
        if (catalogo.isEmpty()) return;

        System.out.print("Número de producto a modificar: ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;
        if (indice < 0 || indice >= catalogo.size()) {
            System.out.println("Producto no válido.");
            return;
        }

        Producto p = catalogo.get(indice);
        System.out.println("Modificar: 1) Nombre  2) Precio  3) Stock");
        System.out.print("Opción: ");
        int op = Integer.parseInt(scanner.nextLine());

        switch (op) 
        {
            case 1:
                System.out.print("Nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                catalogo.set(indice, new Producto(nuevoNombre, p.getPrecio(), p.getStock()));
                System.out.println("Nombre actualizado.");
                break;
            case 2:
                System.out.print("Nuevo precio: ");
                double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                catalogo.set(indice, new Producto(p.getNombre(), nuevoPrecio, p.getStock()));
                System.out.println("Precio actualizado.");
                break;
            case 3:
                System.out.print("Nuevo stock: ");
                int nuevoStock = Integer.parseInt(scanner.nextLine());
                catalogo.set(indice, new Producto(p.getNombre(), p.getPrecio(), nuevoStock));
                System.out.println("Stock actualizado.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    //Eliminar producto del catalogo
    public void eliminarProducto() 
    {
        mostrarCatalogo();
        if (catalogo.isEmpty()) return;

        System.out.print("Número de producto a eliminar: ");
        int indice = Integer.parseInt(scanner.nextLine()) - 1;
        if (indice < 0 || indice >= catalogo.size()) {
            System.out.println("Producto no válido.");
            return;
        }
        Producto eliminado = catalogo.remove(indice);
        System.out.println("Producto eliminado: " + eliminado.getNombre());
    }

    //Menu principal
    public void iniciar() 
    {
        Cliente cliente = seleccionarCliente();

        int opcion;
        do {
            System.out.println("\n--- MENÚ DE TIENDA ---");
            System.out.println("Cliente activo: " + cliente.getNombre());
            System.out.println("1. Ver catálogo");
            System.out.println("2. Agregar producto al carrito");
            System.out.println("3. Ver carrito");
            System.out.println("4. Realizar pedido");
            System.out.println("5. Cancelar pedido (restaurar stock)");
            System.out.println("6. Modificar producto");
            System.out.println("7. Eliminar producto");
            System.out.println("8. Ver clientes cargados");
            System.out.println("9. Cambiar de cliente");
            System.out.println("10. Salir");
            System.out.print("Opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) 
            {
                case 1:
                    mostrarCatalogo();
                    break;
                case 2:
                    mostrarCatalogo();
                    if (catalogo.isEmpty()) break;
                    System.out.print("Seleccione el número del producto: ");
                    int indice = Integer.parseInt(scanner.nextLine()) - 1;
                    System.out.print("Cantidad: ");
                    int cantidad = Integer.parseInt(scanner.nextLine());
                    if (indice >= 0 && indice < catalogo.size()) 
                    {
                        cliente.getCarrito().agregarProducto(catalogo.get(indice), cantidad);
                    } else 
                    {
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
                    cliente.getCarrito().cancelarYRestaurarStock();
                    break;
                case 6:
                    modificarProducto();
                    break;
                case 7:
                    eliminarProducto();
                    break;
                case 8:
                    mostrarClientes();
                    break;
                case 9:
                    cliente = seleccionarCliente();
                    break;
                case 10:
                    System.out.println("Gracias por visitar la tienda, " + cliente.getNombre());
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 10);
    }

    // Main
    public static void main(String[] args) 
    {
        Tienda tienda = new Tienda();

        // Carga de productos y clientes
        tienda.cargarProductosDesdeArchivo("productos.txt");
        tienda.cargarClientesDesdeArchivo("clientes.txt");

        tienda.iniciar();
    }
}

