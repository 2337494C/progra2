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
            //nombre;genero;duracion;clasificacion
            if (p.length < 4) continue;
            peliculas.add(new Pelicula(p[0].trim(), p[1].trim(), Integer.parseInt(p[2].trim()), p[3].trim()));
        }
    } catch (IOException | NumberFormatException e) 
    {
        System.out.println("error" + ruta + ": " + e.getMessage());
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
            //nombrePelicula;idSala;horario;precio
            if (f.length < 4) continue;
            Pelicula p = buscarPeliculaPorNombre(f[0].trim());
            if (p == null) continue;
            int idSala = Integer.parseInt(f[1].trim());
            Sala s = new Sala(idSala, 60);
            String horario = f[2].trim();
            double precio = Double.parseDouble(f[3].trim());
            funciones.add(new Funcion(p, s, horario, precio));
        }
    } catch (IOException | NumberFormatException e) 
    {
        System.out.println("error" + ruta + ": " + e.getMessage());
    }
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
