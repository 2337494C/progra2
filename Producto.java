public class Producto {
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public boolean disminuirStock(int cantidad) {
        if (cantidad <= stock) {
            stock -= cantidad;
            return true;
        } else {
            System.out.println("No hay suficiente stock para " + nombre);
            return false;
        }
    }

    @Override
    public String toString() {
        return nombre + " - $" + precio + " (" + stock + " disponibles)";
    }
}
