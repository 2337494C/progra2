public class Pedido {
    private Cliente cliente;
    private Carrito carrito;
    private String estado;

    public Pedido(Cliente cliente, Carrito carrito) {
        this.cliente = cliente;
        this.carrito = carrito;
        this.estado = "Pendiente";
    }

    public void confirmarPedido() {
        System.out.println("\nPedido confirmado para " + cliente.getNombre());
        carrito.mostrarContenido();
        System.out.println("Estado del pedido: " + estado);
        estado = "Completado";
    }
}
