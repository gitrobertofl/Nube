
package usuario;
import java.rmi.*;
import java.rmi.server.*;
import java.io.*;

/******************************************************************************
 * 
 *  PRACTICA DE SISTEMAS DISTRIBUIDOS (20-21). 
 *
 * <div>Este es un servicio del Cliente.</div>
 * <div>Será utilizado por el servicio Servidor-Operador del repositorio para
 * descargar al disco duro local del cliente el fichero que este 
 * considere oportuno.</div>
 * 
 * 
 * @author Roberto J. de la Fuente Lopez
 * rfuente4@alumno.uned.es
 * robertofl@aconute.es
 * 
 * @version 20210422
 ******************************************************************************/
public class ServicioDiscoClienteImpl extends UnicastRemoteObject
                            implements ServicioDiscoClienteInterface
  {
  /*===========================================================================
    Atributos de la clase
   ==========================================================================*/ 
  private static final long serialVersionUID = 123456789L;	
  /*===========================================================================
    Atributos de instancia
    ==========================================================================*/ 
	
  private String iDCliente;//nombre que identifica este Cliente
 
  private String FICHEROS_DE_CLIENTES="clientesFile";//ruta ficheros bajados
  
  /*===========================================================================
     Constructores de la clase
  ===========================================================================*/ 
  /******************************************************************************
   * Constructor por defecto. Llama al constructor de la clase padre Unicast.
   * 
   * @throws RemoteException
   *
   ********************************************************************************/
 public ServicioDiscoClienteImpl() throws RemoteException
    {
    super();
    }

 /******************************************************************************
  * Constructor en el que incializa el nombre de cliente, ademas de llamar
  * al constructor padre de Unicast
  * 
  * @param nombre nombre del repositorio que se crea
  * @throws RemoteException
  *
  *****************************************************************************/
  public ServicioDiscoClienteImpl(String nombre) throws RemoteException
    {
    super();
    
    this.iDCliente=nombre;
    }
	
  /******************************************************************************
   * Constructor en el que inicializa la ruta donde se guardan los ficheros  y
   * se inicializa el nombre del cliente,, ademas de llamar
   * al constructor padre de Unicast.
   * 
   * @param nombre nombre del repositorio que se crea
   * @param ruta la de almacenamiento de los ficheros de los clientes
   * @throws RemoteException
   *****************************************************************************/
  public ServicioDiscoClienteImpl(String nombre,String ruta) throws RemoteException
    {
    super();
    
    this.iDCliente=nombre;
	this.FICHEROS_DE_CLIENTES=ruta;
    }	
  /*================================================================================
     Metodos publicos 
  ================================================================================*/  
  //get
  /*****************************************************************************
   * obtener el nombre del cliente activo.
   * @return nombre del repositorio
   * @throws RemoteException
   *   
   *************************************************************************** */
  public String getIDCliente() throws RemoteException   
    {
    return iDCliente;
    }//fin getIDCLiente

  //set
 /*****************************************************************************
  * cambiar el nombre del cliente activo.
  * @param nombre nombre nuevo para el repositorio
  * @throws RemoteException
  *   
  *************************************************************************** */
  public void setIDCliente(String nombre)throws RemoteException
    {
    iDCliente=nombre;  
    }//fin setIDCLiente
 
  //servicio de disco
  /*****************************************************************************
   * Crea una carpeta para los ficheros bajados de los repositorios.
   * @param iDCliente nombre del cliente para su ruta
   * @return devuelve true si se hizo, false en caso contrario
   * @throws RemoteException
   *   
   *************************************************************************** */
  public Boolean  carpetaParaCliente(String iDCliente) throws RemoteException
    {
    //el directorio raiz del paquete sera la ubicacion actual del repositorio
    File directorio=new File(FICHEROS_DE_CLIENTES+"//"+iDCliente);
    Boolean res=directorio.mkdirs();
    return res;
    }//fin carpeta para clientes
 
 
  /**********************************************************************************
   * <div> Metodo remoto que recibe como parametros el fichero a bajar</div>
   * <div>El fichero se escribe en la carpeta local del cliente iDCliente.</div>
   * 
   * @param fichero objeto que contiene el fichero a bajar
   * @return Si no hubo problema, true, y false en caso contrario
   * @throws RemoteException
   *********************************************************************************/  
  public Boolean bajarFichero(Fichero fichero) throws RemoteException
    {
	Boolean resultado=false;  
    File salida=new File(FICHEROS_DE_CLIENTES+"//"+this.iDCliente+"//"+fichero.obtenerNombre());
    try
      {
      OutputStream outputStream = new FileOutputStream(salida);
      resultado=fichero.escribirEn(outputStream);
      }
     catch (FileNotFoundException e) 
       {
       
    	System.out.println("sin gui no debería llegar:Fichero no encontrado");
       }//fin catch filenotfoundexception
    return resultado;	  
    }//fin bajar fichero
  
  }//fin clase ServicioDiscoCliente