import java.io.*;
import java.util.*;

public class Cartelera 
{
private List<Pelicula> peliculas = new ArrayList<>();
private List<Funcion> funciones = new ArrayList<>();

public List<Pelicula> getPeliculas() { return peliculas; }
public List<Funcion> getFunciones() { return funciones; }

public void cargar() {
cargarPeliculas("peliculas.txt");
cargarFunciones("funciones.txt");
}

private void cargarPeliculas(String ruta) 
{
    peliculas.clear();
    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) 
    {
    String linea;
    while ((linea = br.readLine()) != null) 
        {
        if (linea.isBlank()) continue;
        String[] p = linea.split(";");
        Pelicula pel = new Pelicula(p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim()), p[3].trim());
        peliculas.add(pel);
        }
    } catch (IOException e)
    {
    System.out.println("No se pudo leer " + ruta + ": " + e.getMessage());
    }
}

private void cargarFunciones(String ruta) 
{
    funciones.clear();
    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) 
    {
        String linea;
        while ((linea = br.readLine()) != null) 
            {
            if (linea.isBlank()) continue;
            String[] f = linea.split(";");
            String nombrePelicula = f[0].trim();
            int idSala = Integer.parseInt(f[1].trim());
            String horario = f[2].trim();
            double precio = Double.parseDouble(f[3].trim());
            Pelicula pel = buscarPeliculaPorNombre(nombrePelicula);
            if (pel == null) continue;
            Sala sala = new Sala(idSala, 100);
            funciones.add(new Funcion(pel, sala, horario, precio));
            }
    } catch (IOException e) 
    {
    System.out.println("No se pudo leer " + ruta + ": " + e.getMessage());
    }
}

public Pelicula buscarPeliculaPorNombre(String nombre) 
{
for (Pelicula p : peliculas) if (p.getNombre().equalsIgnoreCase(nombre)) return p;
return null;
}

public void mostrarPeliculas() 
{
    if (peliculas.isEmpty()) 
    {
        System.out.println("(cartelera vacía)");
        return;
    }
    int i = 1;
    for (Pelicula p : peliculas) 
    {
        System.out.println(i + ". " + p);
        i++;
    }
}

public void mostrarFuncionesPorPelicula(String nombre) 
{
    for (Funcion f : funciones) 
    {
        if (f.getPelicula().getNombre().equalsIgnoreCase(nombre)) 
        {
            System.out.println(" - " + f);
        }
    }
}
}