import java.util.ArrayList;
import java.util.List;

public class Carrito
{
    private List<ItemCarrito> items = new ArrayList<>();
    public void agregarProducto(Producto producto, int cantidad)
    {
        if (producto.disminuirStock(cantidad)) 
        {
            items.add(new ItemCarrito(producto, cantidad));
            System.out.println("Producto agregado al carrito: " + producto.getNombre());
        }
    }

public double calcularTotal() {
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
}
