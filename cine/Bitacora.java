import java.io.*;

public class Bitacora 
{
private String ruta = "bitacora.txt";

public void registrar(Boleto boleto) 
{
    try (FileWriter fw = new FileWriter(ruta, true);
    BufferedWriter bw = new BufferedWriter(fw)) 
    {
        bw.write(boleto.toLinea());
        bw.newLine();
    } catch (IOException e) 
    {
        System.out.println("no se pudo escribir bitacora: " + e.getMessage());
    }
}
}