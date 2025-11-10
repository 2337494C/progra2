import java.io.*;
import java.util.*;

public class Cartelera 
{
private List<Pelicula> peliculas = new ArrayList<>();
private List<Funcion> funciones = new ArrayList<>();
private List<Cliente> clientes = new ArrayList<>();
private List<String> estrenos = new ArrayList<>();

public List<Pelicula> getPeliculas() { return peliculas; }
public List<Funcion> getFunciones() { return funciones; }
public List<Cliente> getClientes() { return clientes; }
public List<String> getEstrenos() { return estrenos; }

public void cargar() 
{
    cargarPeliculas("peliculas.txt");
    cargarFunciones("funciones.txt");
    cargarClientes("clientes.txt");
    cargarEstrenos("estrenos.txt");
}

private void cargarClientes(String ruta) 
{
    clientes.clear();
    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) 
    {
        String linea;
        while ((linea = br.readLine()) != null) 
        {
            if (linea.isBlank()) continue;
            String[] c = linea.split(";");
            clientes.add(new Cliente(c[0].trim(), c[1].trim()));
        }
    } catch (IOException e) 
    {
        System.out.println("no se pudo leer " + ruta + ": " + e.getMessage());
    }
}

private void cargarEstrenos(String ruta) 
{
    estrenos.clear();
    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) 
    {
        String linea;
        while ((linea = br.readLine()) != null) 
        {
            if (linea.isBlank()) continue;
            estrenos.add(linea.trim());
        }
    } catch (IOException e) 
    {
        System.out.println("no se pudo leer " + ruta + ": " + e.getMessage());
    }
}

public Pelicula buscarPeliculaPorNombre(String nombre) 
{
    for (Pelicula p : peliculas) if (p.getNombre().equalsIgnoreCase(nombre)) return p;
    return null;
}

public void mostrarPeliculas() { /* igual que tienes */ }
public void mostrarFuncionesPorPelicula(String nombre) { /* igual que tienes */ }
public void mostrarClientes() 
{
    if (clientes.isEmpty()) { System.out.println("(sin clientes)"); return; }
    for (Cliente c : clientes) System.out.println(" - " + c);
}

public void mostrarEstrenos() 
{
    if (estrenos.isEmpty()) { System.out.println("(sin estrenos)"); return; }
    for (String e : estrenos) System.out.println(" - " + e);
}

public Funcion getFuncionPorIndice(int idx) 
{
    if (idx < 1 || idx > funciones.size()) return null;
    return funciones.get(idx - 1);
}
}