public class Pelicula 
{
private String nombre;
private String genero;
private int duracionMin;
private String clasificacion;

public Pelicula(String nombre, String genero, int duracionMin, String clasificacion) 
{
    this.nombre = nombre;
    this.genero = genero;
    this.duracionMin = duracionMin;
    this.clasificacion = clasificacion;
}

public String getNombre() { return nombre; }
public String getGenero() { return genero; }
public int getDuracionMin() { return duracionMin; }
public String getClasificacion() { return clasificacion; }
@Override

public String toString() 
{
    return nombre + " (" + genero + ", " + duracionMin + " min, " + clasificacion + ")";
}
}
