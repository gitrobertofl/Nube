package servidor; 


/********************************************************************************
   
 *  PRACTICA DE SISTEMAS DISTRIBUIDOS (20-21). 
 *
 *   <div> Esta clase representa el registro de cliente.</div> 
 *   <div> sus campos son:</div>
 *   <ul><li>nombre de cliente .- Una cadena de tamano indeterminado</li>
 *	<li>contraseña.- Una cadena de tamano indeterminado</li>
 *    <li>ID de sesión.- Solo tendra contenido si el cliente esta autentificado
 *	 en el sistema. Identifica univocamente la sesion</li></ul> 
 * 
 *  <div>Todos los metodos estan synchronized para evitar problemas de concurrencia</div>
 *
 * <div>Con licencia GPL v3</div>
 * 
 *  @see Datos
 *
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * 
 * @version 20201106
 * 
 ********************************************************************************/

public class ClienteBD
  {
  /*==============================================================================
   * Atributos de la clase
   ===============================================================================*/ 
  private String nombreCliente;
  private String password; //(OJO SEGURIDAD: NO ESTA CIFRADA)
  //private Boolean activo;
  private String iDSesion;//si es "" esta inactivo
  
  /*===============================================================================
   * Constructores de la clase
   * ==============================================================================*/    
  /**********************************************************************************
   * El constructor por defecto crea un Cliente con nombre y password 
   * en blanco y no activo
   *********************************************************************************/
  public ClienteBD()
    {
    this.nombreCliente="";
    this.password="";
    //this.activo=false;
    this.iDSesion="";
    }//fin constructor por defecto
  /**********************************************************************************
   * <div>Este constructor recibe como argumentos dos cadenas con el nombre de Cliente
   * y password, que asigna a los atributos. El Cliente no esta activo.</div>
   * 
   * @param usr cadena con el nombre de cliente
   * @param pass cadena con la contraseña del cliente
   *********************************************************************************/
  public ClienteBD(String usr,String pass)
    {
    this.nombreCliente=usr;
    this.password=pass;
    //this.activo=false;
    this.iDSesion="";
    }//fin constructor Cliente (usr,pass)
  /*======================================================================
   * Metodos publicos
   * ==============================================================================*/    
  /**********************************************************************************
   * Este metodo obtiene la referencia a una cadena (String) con el contenido
   * del atributo nombre de Cliente
   * 
   * @return String con el nombre de Cliente asignado
   *********************************************************************************/
  public synchronized String getNombreCliente()
    {
    return this.nombreCliente;
    }
  /**********************************************************************************
   * Este metodo obtiene la referencia a una cadena (String) con el contenido
   * del atributo password
   * 
   * @return String con la contrasena del Cliente
   *********************************************************************************/
  public synchronized String getPasswordCliente()
    {
    return this.password;
    }
  /**********************************************************************************
   * Este metodo obtiene la referencia a una cadena (String) con el contenido
   * del atributo IDSesion
   
   * @return String con el identificador de sesion
   *********************************************************************************/
  public synchronized String getIDSesion()
    {
    return this.iDSesion;
    }
    
   
  /**********************************************************************************
   * <div>Este metodo modifica la contrasena con otra pasada por parametro</div>
   * 
   * @param nuevaPwd cadena con la nueva contrasena
   * @return si cambio correcto=true, no correcto=false;
   *********************************************************************************/
  public synchronized Boolean setPassword(String nuevaPwd)
    {
    this.password=nuevaPwd;
    return true;
    }//fin setPassword
    
    
  /**********************************************************************************
   * Este metodo modifica el ID de sesion pasado por parametro
   * 
   * @param iD String con el nuevo ID de sesion
   *********************************************************************************/
  public synchronized void setIDSesion(String iD)
    {
    this.iDSesion=iD;
    }
 
    
  @Override
  public String toString()
    {
    return nombreCliente+"-"+password+"-"+this.iDSesion;  
    }
}//fin clase Cliente