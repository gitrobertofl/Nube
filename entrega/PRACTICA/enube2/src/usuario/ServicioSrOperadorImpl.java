
package usuario;

import java.rmi.*;
import java.rmi.server.*;
import java.io.File;

import servidor.MiRegistroRMI;
import servidor.NubeRMI;

/******************************************************************************************
 * <div> Servicio Servidor-operador:</div>
 *<div> Este servicio tiene un doble objetivo:</div>
 * <ul><li> 1.- Por un lado, suministra los metodos necesarios para que el servidor gestione
 * los lugares de almacenamiento para cada cliente.</li>
 *
 *<li>2.- Se encarga de la operacion de bajada de ficheros desde el repositorio al cliente,
 * es decir, cuando un cliente quiere bajar un fichero se lo pide al servidor mediante el
 * servicio Gestor. Una vez que el servidor averigua que repositorio aloja el fichero requerido
 *  por el cliente, este llama a un metodo del Servicio Servidor-Operador y le pasa la URL del
 *  cliente para que pueda llamar al metodo de descarga del servicio DiscoCliente que es el que
 *  realmente se encarga de la descarga</li></ul>
 * 
 * 
 * Con licencia GPL v3
 * @see MiRegistroRMI
 * @see NubeRMI
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * @version 20201103
 * 
 *******************************************************************************************/
public class ServicioSrOperadorImpl extends UnicastRemoteObject
                            implements ServicioSrOperadorInterface
  {
  /*==============================================================================
    Atributos de la clase (static)
    ===============================================================================*/ 
  private static final long serialVersionUID = 123456789L;
  /*==============================================================================
	Atributos de instancia
	===============================================================================*/ 
  private String nickRepositorio;//nombre que identifica este Repositorio
  private String FICHEROS_DE_REPOS="reposFile";
  
  /*===============================================================================
   * Constructores de la clase
   * ==============================================================================*/  

  /******************************************************************************
   * Constructor por defecto. Llama al constructor de la clase padre Unicast.
   * 
   * @throws RemoteException
   *
   ********************************************************************************/
  public ServicioSrOperadorImpl() throws RemoteException
    {
    super();
    }

  /******************************************************************************
   * Constructor en el que incializa el nombre de repositorio, ademas de llamar
   * al constructor padre de Unicast
   * 
   * @param nombre nombre del repositorio que se crea
   * @throws RemoteException
   *
   *****************************************************************************/
  public ServicioSrOperadorImpl(String nombre) throws RemoteException
    {
    super();
    
    this.nickRepositorio=nombre;
    }

  /******************************************************************************
   * Constructor en el que inicializa la ruta donde se guardan los ficheros  y
   * se inicializa el nombre del repositorio,, ademas de llamar
   * al constructor padre de Unicast.
   * 
   * @param nombre nombre del repositorio que se crea
   * @param ruta la de almacenamiento de los ficheros de los clientes
   * @throws RemoteException
   *****************************************************************************/
  public  ServicioSrOperadorImpl(String nombre, String ruta) throws RemoteException
    {
    super();
    
    this.nickRepositorio=nombre;
    this.FICHEROS_DE_REPOS=ruta;
    }
  
  /*================================================================================
  Metodos publicos 
  ================================================================================*/
  //get
  /*****************************************************************************
   * obtener el nombre del repositorio activo.
   * @return nombre del repositorio
   * @throws RemoteException
   *   
   *************************************************************************** */
  public String getNickRepositorio() throws RemoteException   
    {
    return nickRepositorio;
    }  
   
  //set
 /*****************************************************************************
  * cambiar el nombre del repositorio activo.
  * @param nombre nombre nuevo para el repositorio
  * @throws RemoteException
  *   
  *************************************************************************** */
  public void setNicRepositorio(String nombre)throws RemoteException
    {
    nickRepositorio=nombre;  
    }
    
  //servicios ficheros

  /**********************************************************************************************
   * Metodo ofrecido para que el servidor gestione el lugar de almacemiento (carpeta para los
   * ficheros de un cliente, cuyo ID se pasa como parametro.
   * @param iDCliente cadena con el ID del cliente
   * @return true si no se produjo error, false en caso contrario
   * @throws RemoteException     
   *********************************************************************************************/  
  public Boolean  carpetaParaCliente(String iDCliente) throws RemoteException
    {
	//el directorio raiz del paquete sera la ubicacion actual del repositorio
    File directorio=new File(FICHEROS_DE_REPOS+"//"+nickRepositorio+"//"+iDCliente);
    Boolean res=directorio.mkdirs();
    return res;
    }//fin carpeta para cliente
    
  /**********************************************************************************************
   *   
   * Aqui es donde se baja el fichero de un cliente a su carpeta particular. si el fichero esta
   * compartido, bajará el fichero del propietario en el del cliente actual
   * 
   *  @param iDCliente el del cliente en ejecucion
   *  @param iDClienteProp el del propietario del fichero
   *  @param nombreFichero el fichero a bajar
   *  @return true si se bajo sin problemas, false en caso contrario
   *  @throws RemoteException
   *  @throws NotBoundException
   *   
   *********************************************************************************************/
  public Boolean bajarFichero(String iDCliente, String iDClienteProp, String nombreFichero)
	throws RemoteException, NotBoundException
    {
    Boolean resultado=false;
    String directorio=FICHEROS_DE_REPOS+"//"+nickRepositorio+"//"+iDClienteProp;
    //obtenemos el fichero. sabemos que existe. Ruta
    Fichero  fichero= new Fichero(directorio,nombreFichero,iDClienteProp);
	//llamamos al servicio de cliente para bajar fichero al mismo
    resultado=((ServicioDiscoClienteInterface)
    		    MiRegistroRMI.getRefRegistroRMI().lookup(NubeRMI.getBindDiscoCliente()+iDCliente))
				.bajarFichero(fichero);    
        
    return resultado;
    }//fin bajarFichero

              
      
     
      
}//fin clase ServicioSrOperaodrImpl
