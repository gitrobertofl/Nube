package servidor; 

/********************************************************************************
   
 *  PRACTICA DE SISTEMAS DISTRIBUIDOS (20-21). 
 *
 *  <div>Esta clase representa el registro de fichero</div>
 *   <div>Sus campos son:</div>
 *	<ul><li>Nombre del cliente.- Una cadena de tamano indeterminado que indica
 *	el cliente al que pertenece</li>
 *	<li>Nombre del repositorio.- Una cadena de tamano indeterminado que indica
 *	el repositorio en el que se encuentra el fichero</li>
 *	<li>nombre del fichero.- sin ruta. Una cadena de tamano indeterminado</li>
 *  <li> compartido.- booleano que indica si el fichero esta compartido.</li></ul>
 *
 *   <div>Todos los metodos son synchronized para evitar problemas de concurrencia</div> 
 *
 *  @see Datos
 *
 * <div>Con licencia GPL v3</div>
 * 
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * 
 * @version 20201230
 * 
 ********************************************************************************/

public class FicheroBD
  {
  /*==============================================================================
   * Atributos de la clase
   =============================================================================*/
  private String nombreFichero;
  private String nombreCliente;
  private String nombreRepositorio;
  private long peso;
  private Boolean compartido;
  /*===============================================================================
   * Constructores de la clase
   * ==============================================================================*/    
  /**********************************************************************************
   * El constructor por defecto crea un fichero con nombre y password 
   * en blanco y no activo
   *********************************************************************************/
  public FicheroBD()
    {
    this.nombreCliente="";
    this.nombreRepositorio="";
    this.nombreFichero="";
	this.peso=0;
	this.compartido=false;
    }//fin constructor por defecto
  /**********************************************************************************
   * Este constructor recibe como argumentos el cliente, el repo y el fichero
   * que asigna a los atributos (por defecto no esta compartido).
   *
   * @param iDCliente cadena con el ID del cliente
   * @param iDRepo cadena con el ID del repositorio
   * @param nFichero cadena con el nombre del fichero
   *
   *********************************************************************************/
  public FicheroBD(String iDCliente, String iDRepo,String nFichero)
    {
    this.nombreCliente=iDCliente;
    this.nombreRepositorio=iDRepo;
    this.nombreFichero=nFichero;
	this.peso=0;
	this.compartido=false;
    }//fin constructor FicheroBD (idcliente,idrepo,fichero)
	
  /**********************************************************************************
   * Este constructor recibe como argumentos el nombre, el cliente, el repo y 
   * su tamano que asigna a los atributos (por defecto no esta compartido).
   *
   * @param iDCliente cadena con el ID del cliente
   * @param iDRepo cadena con el ID del repositorio
   * @param nFichero cadena con el nombre del fichero
   * @param tamano entero largo con el tamano del fichero
   *
   *********************************************************************************/
  public FicheroBD(String iDCliente, String iDRepo,String nFichero,Long tamano)
    {
    this.nombreCliente=iDCliente;
    this.nombreRepositorio=iDRepo;
    this.nombreFichero=nFichero;
	this.peso=tamano;
	this.compartido=false;
    }//fin constructor FicheroBD (idcliente,idrepo,fichero,tamano)
	
  /*======================================================================
   * Metodos publicos
   * ==============================================================================*/    
  /**********************************************************************************
   * Este metodo obtiene la referencia a una cadena (String) con el contenido
   * del atributo nombre de Cliente
   * 
   * @return String con el nombre del cliente
   *********************************************************************************/
  public synchronized String getNombreCliente()
    {
    return this.nombreCliente;
    }
  /**********************************************************************************
   * Este metodo obtiene la referencia a una cadena (String) con el contenido del 
   * atributo nombre de repositorio
   * 
   * @return String con el nombre del repositorio
   *********************************************************************************/
  public synchronized String getNombreRepositorio()
    {
    return this.nombreRepositorio;
    }
  
  /**********************************************************************************
   * Este metodo obtiene la referencia a una cadena (String) con el contenido del 
   * atributo nombre de fichero
   * 
   * @return String con el nombre del fichero
   *********************************************************************************/
  public synchronized String getNombreFichero()
    {
    return this.nombreFichero;
    }

  /**********************************************************************************
   * Este metodo obtiene un entero largo con el tamano del fichero
   * 
   * @return String con el nombre del fichero
   *********************************************************************************/
  public synchronized Long getPeso()
    {
    return this.peso;
    }

  /**********************************************************************************
   * Este metodo obtiene el estado de comparticion del fichero
   * 
   * @return true si esta compartido y false en caso contrario
   *********************************************************************************/
  public synchronized Boolean getEstaCompartido()
    {
	return compartido;	
	}  

  /**********************************************************************************
   * Este metodo establece el estado del fichero en compartido
   * es decir, compartido=true;
   * 
   *********************************************************************************/
  public synchronized void setCompartir()
    {
	compartido=true;	
	}
  /**********************************************************************************
   * Este metodo establece el estado del fichero en no compartido
   * es decir, compartido=false;
   * 
   *********************************************************************************/
  public synchronized void setNoCompartir()
    {
	compartido=false;	
	} 
    
  public String toString()
    {
    return nombreRepositorio+"-"+nombreCliente+"-"+nombreFichero+"-"+compartido.toString();  
    }
    
  }//fin clase ficheroBD
