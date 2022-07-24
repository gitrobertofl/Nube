package servidor;  


/********************************************************************************
   
 *  PRACTICA DE SISTEMAS DISTRIBUIDOS (20-21). 
 *
 * <div>Esta clase representa el registro Repositorio.</div>
 * <div> Sus campos son:</div>
 *  <ul><li> Nombre de repositorio.- Una cadena de tamano indeterminado</li>
 *  <li> contraseña.- Una cadena de tamano indeterminado</li>
 *  <li>ID de sesión.- Solo tendra contenido si el repositorio esta autentificado
 *  en el sistema. Identifica univocamente la sesion</li></ul>
 * 
 * <div> Todos los metodos estan synchronized para evitar problemas de concurrencia</div>
 *
 * <div>Con licencia GPL v3</div>
 * 
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * 
 * @version 20201116
 * 
 ********************************************************************************/

public class RepositorioBD
  {
  /*==============================================================================
   * Atributos de la clase
   ===============================================================================*/    
  private String nombreRepositorio;
  private String password; //(OJO SEGURIDAD: NO ESTA CIFRADA)
  private String iDSesion;//si es "" esta inactivo
  //private Boolean activo;
  
  /*===============================================================================
   * Constructores de la clase
   * ==============================================================================*/    
  /**********************************************************************************
   * El constructor por defecto crea un Repositorio con nombre y password 
   * en blanco y no activo
   *********************************************************************************/
  public RepositorioBD()
    {
    this.nombreRepositorio="";
    this.password="";
    //this.activo=false;
    this.iDSesion="";
    
    }//fin constructor por defecto
  /**********************************************************************************
   * Este constructor recibe como argumentos dos cadenas con el nombre de Repositorio
   * y password, que asigna a los atributos. El Repositorio no esta activo.
   * 
   * @param usr caden con el nombre del repositorio
   * @param pass cadena con la contrasena del repositorio
   *********************************************************************************/
  public RepositorioBD(String usr,String pass)
    {
    this.nombreRepositorio=usr;
    this.password=pass;
    //this.activo=false;
    this.iDSesion="";
    }//fin constructor Repositorio (usr,pass)

  /*======================================================================
   * Metodos publicos
   * ==============================================================================*/    
  /**********************************************************************************
   * Este metodo obtiene la referencia a una cadena (String) con el contenido 
   * del atributo nombre de Repositorio
   * 
   * @return String con el nombre de Repositorio asignado
   *********************************************************************************/
  public synchronized String getNombreRepositorio()
    {
    return this.nombreRepositorio;
    }
  /**********************************************************************************
   * Este metodo obtiene la referencia a una cadena (String) con el contenido 
   * del atributo password
   * 
   * @return String con la contrasena del Repositorio
   *********************************************************************************/
  public synchronized String getPasswordRepositorio()
    {
    return this.password;
    }

  /**********************************************************************************
   * 
   * este metodo obtiene el id de sesion del repositorio
   *
   *  @return String con la cadena de sesion
   *
   *********************************************************************************/
  public synchronized String getIDSesion()
    {
    return this.iDSesion;
    }
    
   //el nombre de Repositorio no se modifica nunca (campo clave).
   //public synchronized Boolean setNombreRepositorio(String nuevoNombre)
   
  /**********************************************************************************
   * Este metodo modifica la contrasena con otra pasada por parametro
   * 
   * @param nuevaPwd String con la nueva contrasena
   * @return Boolean si cambio correcto=true, no correcto=false;
   *********************************************************************************/
  public synchronized Boolean setPassword(String nuevaPwd)
    {
    this.password=nuevaPwd;
    return true;
    }//fin setPassword
  /**********************************************************************************
   * Este metodo modifica el ID de sesión
   * 
   * @param iD cadena con el iD de sesión nuevo
   *********************************************************************************/
  public synchronized void setIDSesion(String iD)
    {
    this.iDSesion=iD;
    }

    
  @Override
  public String toString()
    {
    return nombreRepositorio+"-"+password+"-"+this.iDSesion;  
    }

}//fin clase Repositorio
