package usuario;

/******************************************************************************
 * <div>Clase ejecutora de repositorio.</div>
 * 
 * <div> esta clase existe por la imposición del enunciado de ejecutar los
 * clientes y repositorios sin parametros.</div> 
 * <div>Utiliza la clase UsrRepositorio para llamar a la parte de repositorio, que 
 * usa la herencia con la clase Usuario.</div>
 * 
 * @see UsrRepositorio
 * @see Usuario
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * @version 20210601
 *****************************************************************************/
public class Repositorio
  {
  //parametro necesario para UsrUsuario;	
  private static final String TIPO_USUARIO="repositorio";
 
  /*****************************************************************************
  *
  *  <div>Método principal de ejecución main. Prepara el acceso a los servicios
  *  remotos de servidor.</div>
  *  <div>Para determinar el tipo de entidad, repositorio,
  *  necesita parámetro de entrada, instanciando la que corresponda.</div>
  *
  *  @param args una cadena con el tipo de entidad
  *
 *************************************************************************** */
  public static void main (String args[])
    {
	//gestor de seguridad
    Usuario usuario;
    usuario = new UsrRepositorio();
	if (usuario.getConectadoConSRV())
	  //una vez montado el esquema remoto, iniciamos el menu principal
	  usuario.menuUsuario();    
	 else
   	  {	 
	  GUIUsuarioInterface gUI = new GUIClienteTexto(TIPO_USUARIO);
	  gUI.finDePrograma();
	  }
	//termina todo  
 	System.exit(0); 
	}//fin del main
  	  
  }//fin clase repositorio