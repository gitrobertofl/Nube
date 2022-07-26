package usuario;
//import java.util.Vector;
import java.rmi.*;

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

public interface ServicioSrOperadorInterface extends Remote 
  {
  //get
  /*****************************************************************************
   * obtener el nombre del repositorio activo.
   * @return nombre del repositorio
   * @throws RemoteException
   *   
   *************************************************************************** */
  public String getNickRepositorio()throws RemoteException;    
    
  //set
 /*****************************************************************************
  * cambiar el nombre del repositorio activo.
  * @param nombre nombre nuevo para el repositorio
  * @throws RemoteException
  *   
  *************************************************************************** */
  public void setNicRepositorio(String nombre)throws RemoteException;
 
  //servicio de ficheros
  /**********************************************************************************************
   * Metodo ofrecido para que el servidor gestione el lugar de almacemiento (carpeta para los
   * ficheros de un cliente, cuyo ID se pasa como parametro.
   * @param iDCliente cadena con el ID del cliente
   * @return true si no se produjo error, false en caso contrario
   * @throws RemoteException     
   *********************************************************************************************/  
  public Boolean  carpetaParaCliente(String iDCliente) throws RemoteException; 
 
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
		  throws RemoteException,NotBoundException;
  
  
}
