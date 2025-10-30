public class Cliente {
    private String nombre;
    private Carrito carrito;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.carrito = new Carrito();
    }

    public String getNombre() {
        return nombre;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public Pedido realizarPedido() {
        return new Pedido(this, carrito);
    }
}
