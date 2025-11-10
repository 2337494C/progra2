public class Boleto 
{
private String folio;
private Cliente cliente;
private Funcion funcion;
private int asiento;

public Boleto(String folio, Cliente cliente, Funcion funcion, int asiento) 
{
    this.folio = folio;
    this.cliente = cliente;
    this.funcion = funcion;
    this.asiento = asiento;
}

public String toLinea() 
{
    return folio + ";" + cliente.getId() + ";" + funcion.getPelicula().getNombre() + ";" + funcion.getSala().getIdSala() + ";" + funcion.getHorario() + ";" + asiento + ";" + funcion.getPrecio();
}

@Override
public String toString() 
{
    return "Folio " + folio + " | " + cliente.getNombre() + " | " + funcion.getPelicula().getNombre() + " | Sala " + funcion.getSala().getIdSala() + " | " + funcion.getHorario() + " | Asiento " + asiento;
}
}