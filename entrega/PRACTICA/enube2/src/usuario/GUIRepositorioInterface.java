package usuario;

/********************************************************************************
 * <div>Esta clase va a implementar el interface del entorno de repositorio
 * en modo consola.</div> 
 * <div>Contiene los metodos para la visualizacion de cada menu</div>
 *
 * Con licencia GPL v3
 *
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * @version 20201103
 *********************************************************************************/
public interface GUIRepositorioInterface
  {
  /********************************************************************************
   * <div> Este metodo captura el id del cliente con ficheros en este repositorio</div>
   * 
   * @param iDRepositorio el correspondiente al repositorio en ejecucion.
   * @return cadena introducida (si intro, cadena vacia)
   *  
   *******************************************************************************/
  public String obtenerIDCliente(String iDRepositorio);

  /********************************************************************************
   * <div> Este metodo visualiza el listado pasado por parametro</div>
   * 
   * @param iDRepositorio el correspondiente al repositorio en ejecucion.
   * @param listado cada registro en una linea
   *  
   *******************************************************************************/
  public void verListadoClientes(String iDRepositorio, String listado);
	   
  /********************************************************************************
   * <div> Este metodo visualiza el listado pasado por parametro</div>
   * 
   * @param iDRepositorio el correspondiente al repositorio en ejecucion.
   * @param iDCliente el correspondiente al listado
   * @param listado cada registro en una linea
   *  
   *******************************************************************************/
  public void verListadoFicherosCliente(String iDRepositorio, String iDCliente, String listado);
	
	
  }//fin GUIRepositorioInterface
