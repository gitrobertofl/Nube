package usuario;


/** *****************************************************************************
 * esta es una clase accesoria para obtener un usuario con su nombre y contraseña.
 * 
 * Con licencia GPL v3
 * 
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * @version 20201103
 *******************************************************************************/
public class AcreditacionUsuario
  {
  /*==============================================================================
   * Atributos de la clase (static)
   ===============================================================================*/ 
  /*==============================================================================
   * Atributos de instancia
   ===============================================================================*/ 
  private String nombre="";
  private String password="";

  /*===============================================================================
   * Constructores de la clase
   * ==============================================================================*/  
  /*******************************************************************************
   * Constructor por defecto. No hace nada
   ******************************************************************************/
  public AcreditacionUsuario()
    {
    }
  
  /*===============================================================================
   * Metodos publicos
   * ==============================================================================*/  
  
  //get
  /*******************************************************************************
   * obtiene el nombre de usuario
   * @return cadena con el nombre de usuario
   * 
   ******************************************************************************/
  public String getNombre()
  {
  return this.nombre;
  }   

  /*******************************************************************************
   * obtiene la contraseña
   * @return cadena con la contraseña
   * 
   ******************************************************************************/
  public String getPassword()
    {
    return this.password;
    }   
  
  //set

  /*******************************************************************************
   * cambia el nombre de usuario
   *  @param nombre cadena con el nombre de usuario
   * 
   ******************************************************************************/
  public void setNombre(String nombre)
    {
    this.nombre=nombre;
    }
  /*******************************************************************************
   * obtiene el nombre de usuario
   * @param  pwd cadena con la contrasena
   * 
   ******************************************************************************/
  public void setPassword(String pwd)
    {
    this.password=pwd;
    }
  
    
  }//fin AcreditacionUsuario
