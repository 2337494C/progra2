public class Funcion 
{
private Pelicula pelicula;
private Sala sala;
private String horario;
private double precio;
private boolean[] asientos = new boolean[60];

public Funcion(Pelicula pelicula, Sala sala, String horario, double precio) 
{
    this.pelicula = pelicula;
    this.sala = sala;
    this.horario = horario;
    this.precio = precio;
}

public Pelicula getPelicula() { return pelicula; }
public Sala getSala() { return sala; }
public String getHorario() { return horario; }
public double getPrecio() { return precio; }

public int getDisponibles() 
{
    int libres = 0;
    for (boolean a : asientos) if (!a) libres++;
    return libres;
}

public int reservarAsiento() 
{
    for (int i = 0; i < asientos.length; i++) 
    {
        if (!asientos[i]) 
        {
            asientos[i] = true;
            return i + 1;
        }
    }
    return -1;
}

@Override
public String toString() 
{
    return pelicula.getNombre() + " | Sala " + sala.getIdSala() + " | " + horario +" | $" + precio + " | disp:" + getDisponibles();
}
}