package usuario;

import java.rmi.RemoteException;

import servidor.MiRegistroRMI;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;

/******************************************************************************
 * 
 * *  PRACTICA DE SISTEMAS DISTRIBUIDOS (20-21).
 *
 * <div>Esta es la clase que representa entidad Repositorio.</div>
 * <div> Esta clase hereda los atributos y metodos de la clase abstracta 
 *       Usuario.</div>
 * <div> Implementa los metodo especificos de un cliente de nube</div>
 * 
 * 
 * 
 * Con licencia GPL v3
 * 
 * @see MiRegistroRMI
 * @see Usuario
 * 
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * @version 20210310
 * 
 ******************************************************************************/
public class UsrRepositorio extends Usuario {
  /*==============================================================================
   * Atributos de la clase (static)
   ===============================================================================*/ 
  /*==============================================================================
   * Atributos de instancia
   ===============================================================================*/ 
  //Ruta donde se guardan los ficheros de los repositorios
  private String FICHEROS_DE_REPOS="reposFile";
  
  //creamos un objeto para el servicio del repositorio para servidor
  private ServicioSrOperadorInterface servicioSrOperador;
  //creamos un objeto para el servicio del repositorio para el cliente
  private ServicioClOperadorInterface servicioClOperador;
  //GUI propio de repositorio
  GUIRepositorioInterface gUIRepositorio=(GUIRepositorioInterface)gUI;
  /*===============================================================================
   * Constructores de la clase
   * ==============================================================================*/  
  /******************************************************************************
   * Constructor por defecto. Llama al constructor Usuario en el que le dice el tipo
   * de usuario es. Usuario inicializa la conexion con RMI.
   * ******************************************************************************/
  public UsrRepositorio()
	{
	super(TIPO_REPOSITORIO);	
	}//FIN CONSTRUCTOR Repositorio()

  /*================================================================================
  Metodos abstractos publicos o protected 
================================================================================*/
  
  /******************************************************************************
   * Este metodo se encarga de preparar el inicio de sesion como Repositorio.
   * 
   * Una vez ha iniciado la sesion correctamente en Usuario,
   * nos manda a un menu con las operaciones del repositorio, 
   * identificado por su ID. 
   * 
   * @param iDRepositorio cadena con el ID del repositorio
   * @throws RemoteException
   * @throws AlreadyBoundException
   * @throws NotBoundException
   * 
   * ******************************************************************************/
  @Override
  protected void inicioSesionUsuario(String iDRepositorio) 
			throws RemoteException, AlreadyBoundException,NotBoundException
	{
	//esta acreditado: obtener y marcar como activo y registrarlo en RMI
    servicioSrOperador=new ServicioSrOperadorImpl(iDRepositorio,FICHEROS_DE_REPOS);
    servicioClOperador=new ServicioClOperadorImpl(iDRepositorio,FICHEROS_DE_REPOS);
    // los registra en RMI. con gestor, aqui no puede al ser otro host
    gestor.registraServiciosRepositorio(iDRepositorio,servicioSrOperador,servicioClOperador); 
    //entramos en menu repositorio acreditado
    menuRepositorio(iDRepositorio);
    //como vamos a salir, marcamos como repositorio inactivo,
	
	//y si tiene un cliente enganchado? avisar o no hacerlo

    //desactivamos la sesion
	autenticacion.desactivarUsuario(iDRepositorio,tipoUsuario);
    // desregistramos los servicios de RMI
    gestor.desRegistrarServiciosRepositorio(iDRepositorio);
    }//fin inicioSesionUsuairo
  
  /*================================================================================
   Metodos privados 
   ================================================================================*/
  /******************************************************************************
   * Este metodo se encarga de mostrar y ejecutar las operaciones del repositorio
   * 
   * @param iDRepositorio cadena con el ID del cliente
   * @throws RemoteException
   * 
   * ******************************************************************************/
  private void menuRepositorio(String iDRepositorio)throws RemoteException
    {
	//-------------------     variables -------------------------------------------  
    //opcion de menu
    Character opcion='0';
    String iDCliente="";
    //-------------------     cuerpo ---------------------------------------------
    do
      {
      opcion=gUI.menuUsuarioAcreditado(iDRepositorio);
      switch (opcion)
        {
        case '1'://listar clientes
                 gUIRepositorio.verListadoClientes(iDRepositorio,gestor.getListadoClientesRepo(iDRepositorio));
                 //vuelve al menu de inicio de repositorio
                 opcion='0';
                 break;
        case '2'://listar ficheros de cliente
                 //obtenemos nombre de cliente por consola y con el visualizamos el listado
                 iDCliente=gUIRepositorio.obtenerIDCliente(iDRepositorio);
                 gUIRepositorio.verListadoFicherosCliente(iDRepositorio,
                                               iDCliente,
                                               gestor.getListadoFicherosCliente(iDCliente));
                 
                 //vuelve al menu de inicio de repositorio
                 opcion='0'; 
                 break;
        case '3'://cerrar sesion de Repositorio (logout)
                 //no hace nada pues ya cierra el menu inicial los obj. remotos 
                 break;
        default :System.out.print("opcion erronea");   
        }//fin switch
               }
      while(opcion!='3');
      }//fin menu
  }//fin clase Repositorio
    
    
    