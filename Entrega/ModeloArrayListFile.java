
/**
 * Implementa la parte de Modelo de Datow
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;


public class ModeloArrayListFile extends ModeloArrayList
{
    static final String fichero = "productos.objetos";

    public ModeloArrayListFile(){
      super();
      cargarFichero();
  }

  public void cargarFichero(){

  	try {
        FileInputStream fis = new FileInputStream(fichero);
        ObjectInputStream ion = new ObjectInputStream(fis);
        try{
          Producto aux = (Producto) ion.readObject();

          while (true){
            super.insertarProducto(aux);
            System.out.println("Producto añadido");
            aux = (Producto)ion.readObject();
          }
        } catch(EOFException eofe){
          fis.close();
          ion.close();
        }
    }catch(IOException e){
      System.err.println(" Error en E/S sobre fichero "+fichero+ " "+e);
    }
    catch (ClassNotFoundException cnfe){
      System.err.println(" El fichero no tiene objetos ");
    }

  }

    public boolean insertarProducto ( Producto p){
      boolean resu = super.insertarProducto(p);
      volcarFichero();
      return resu;    
    }
 
    public boolean borrarProducto ( int codigo ){
      boolean resu = super.borrarProducto(codigo);
      if (resu == true){
        volcarFichero();
      }
      return resu;
    }
    
    public boolean modificarProducto (Producto nuevo){
      boolean resu = super.modificarProducto(nuevo);
      if (resu == true){
        volcarFichero();
      }
      return resu;
    }

    public void volcarFichero(){
      try{
        FileOutputStream fos = new FileOutputStream(fichero);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        for (Producto aux:lista){
          oos.writeObject(aux);
        }

        fos.close();
        oos.close();
      } catch(IOException ioe){
        System.err.println("Error en E/S sobre fichero");
      }
    }
    
    
}    
