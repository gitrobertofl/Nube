package usuario;

import java.rmi.*;

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

public interface ServicioDiscoClienteInterface extends Remote 
  {
  //get
  /*****************************************************************************
   * obtener el nombre del cliente activo.
   * @return nombre del repositorio
   * @throws RemoteException
   *   
   *************************************************************************** */
  public String getIDCliente() throws RemoteException;

  //set
 /*****************************************************************************
  * cambiar el nombre del cliente activo.
  * @param nombre nombre nuevo para el repositorio
  * @throws RemoteException
  *   
  *************************************************************************** */
  public void setIDCliente(String nombre)throws RemoteException;
  
  //servicio de disco
  /*****************************************************************************
   * Crea una carpeta para los ficheros bajados de los repositorios.
   * @param iDCliente nombre del cliente para su ruta
   * @return devuelve true si se hizo, false en caso contrario
   * @throws RemoteException
   *   
   *************************************************************************** */
  public Boolean  carpetaParaCliente(String iDCliente) throws RemoteException;
  
  /**********************************************************************************
   * <div> Metodo remoto que recibe como parametros el fichero a bajar</div>
   * <div>El fichero se escribe en la carpeta local del cliente iDCliente.</div>
   * 
   * @param fichero objeto que contiene el fichero a bajar
   * @return Si no hubo problema, true, y false en caso contrario
   * @throws RemoteException
   *********************************************************************************/  
  public Boolean bajarFichero(Fichero fichero) throws RemoteException;
   
  
  
  }//FIN interface ServicioDiscoCliente