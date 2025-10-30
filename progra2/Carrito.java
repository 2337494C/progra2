import java.util.ArrayList;
import java.util.List;

public class Carrito 
{
    private List<ItemCarrito> items = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) 
    {
        if (producto == null) 
        {
            System.out.println("Producto inválido.");
            return;
        }
        if (cantidad <= 0) 
        {
            System.out.println("Cantidad inválida.");
            return;
        }
        if (producto.disminuirStock(cantidad)) 
        {
            items.add(new ItemCarrito(producto, cantidad));
            System.out.println("Producto agregado al carrito: " + producto.getNombre());
        } else 
        {
            System.out.println("No hay stock suficiente de: " + producto.getNombre());
        }
    }

    public double calcularTotal() 
    {
        double total = 0;
        for (ItemCarrito item : items) 
        {
            total += item.getSubtotal();
        }
        return total;
    }

    public void mostrarContenido() 
    {
        System.out.println("\n--- CARRITO DE COMPRAS ---");
        if (items.isEmpty()) 
        {
            System.out.println("(vacío)");
            System.out.println("TOTAL: $0.0");
            return;
        }
        for (ItemCarrito item : items)
        {
            System.out.println(item);
        }
        System.out.println("TOTAL: $" + calcularTotal());
    }

    public List<ItemCarrito> getItems() 
    {
        return items;
    }

    //Cancela el carrito y regresa al stock
    public void cancelarYRestaurarStock() 
    {
        for (ItemCarrito item : items) 
        {
            item.getProducto().aumentarStock(item.getCantidad());
        }
        items.clear();
        System.out.println("Carrito cancelado. Stock restaurado.");
    }
}
