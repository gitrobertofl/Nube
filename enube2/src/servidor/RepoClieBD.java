package servidor; 
//import java.io.Serializable;

/********************************************************************************
   
 *  PRACTICA DE SISTEMAS DISTRIBUIDOS (20-21). 
 *
 * <div>Esta clase representa la asociacion de los clientes con los repositorio</div>
 * <div> sus campos son:</div>
 * <ul><li>nombre de cliente .- Una cadena de tamano indeterminado.</li>
 * <li> Nombre de repositorio.- Una cadena de tamano indeterminado</li></ul>
 *
 *   Todos los metodos estan synchronized para evitar problemas de concurrencia
 *
 * <div>Con licencia GPL v3</div>
 * 
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * 
 * @version 20201106
 * 
 ********************************************************************************/

public class RepoClieBD// implements Serializable
  {
  /*==============================================================================
   * Atributos de la clase
   ===============================================================================*/    
  private String nombreCliente;
  private String nombreRepositorio;
  
  /*===============================================================================
   * Constructores de la clase
   * ==============================================================================*/    
  /**********************************************************************************
   * El constructor por defecto crea un RepoClie con nombre y password 
   * en blanco 
   *********************************************************************************/
  public RepoClieBD()
    {
    this.nombreCliente="";
    this.nombreRepositorio="";
    }//fin constructor por defecto
  /**********************************************************************************
   * <div>Este constructor recibe como argumentos dos cadenas con el nombre de
   * cliente y repositorio asociado</div>
   * 
   * @param iDCliente nombre del cliente
   * @param iDRepo nombre del repositorio
   * 
   *********************************************************************************/
  public RepoClieBD(String iDCliente, String iDRepo)
    {
    this.nombreCliente=iDCliente;
    this.nombreRepositorio=iDRepo;
    }//fin constructor RepoClie (clie,repo)
  /*======================================================================
   * Metodos publicos
   * ==============================================================================*/    
  /**********************************************************************************
   * <div>Este metodo obtiene la referencia a una cadena (String) con el contenido
   *  del atributo nombre</div>
   * de Cliente
   * 
   * @return String con el nombre de cliente
   *********************************************************************************/
  public synchronized String getNombreCliente()
    {
    return this.nombreCliente;
    }
  /**********************************************************************************
   * <div>Este metodo obtiene la referencia a una cadena con el contenido del atributo
   * nombre de repositorio</div>
   * 
   * @return String con con el nombre del repositorio
   * 
   *********************************************************************************/
  public synchronized String getNombreRepositorio()
    {
    return this.nombreRepositorio;
    }

  
    public String toString()
      {
      return nombreRepositorio+" "+nombreCliente;  
        }
    

  
}//fin clase