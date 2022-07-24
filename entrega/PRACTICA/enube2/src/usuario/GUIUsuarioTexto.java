package usuario; 

import java.util.Scanner;
import servidor.MiRegistroRMI;
import java.io.*;
/*******************************************************************************
 * <div>Esta clase abstracta va a implementar el entorno de usuario modo consola
 * del usuario (comun a repositorio o cliente).</div>
 * <div> El método abstracto lo debera implementar Repositorio y Cliente con sus
 * menus y operaciones propias</div>
 *
 * 
 * <div>Con licencia GPL v3</div>
 * 
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * @version 20201103
 *********************************************************************************/
public abstract class GUIUsuarioTexto implements GUIUsuarioInterface
  {
  /*==========================================================================
    Atributos de la clase
   ========================================================================*/  
  private final Character ALTA='1';
  //private final Character AUTENTIFICAR='2';
  protected String tipoUsuario="";    
  //direccion del host en el que se ejecuta el repositorio/cliente
  private String direccionIP;

  /*===========================================================================
   * Constructores de la clase
   * ==========================================================================*/      
      
  /*********************************************************************************
   * Constructor por defecto. Inicializa la direccionIP donde se ejecuta
   ********************************************************************************/
  public GUIUsuarioTexto()
    {
    direccionIP=MiRegistroRMI.obtenerIPLocalhost();
    }

  /*********************************************************************************
   * Constructor que recibe el tipo de usuario que ejecuta e inicializa la 
   * direccionIP donde se ejecuta
   *
   * @param tipo cadena con el tipo de usuario
   *
   ********************************************************************************/
  public GUIUsuarioTexto(String tipo)
    {
    tipoUsuario=tipo;
    direccionIP=MiRegistroRMI.obtenerIPLocalhost();
    } 
    
  /*==========================================================================
   * Metodos publicos
   * ========================================================================*/    
  //inicio y fin
	
  /*********************************************************************************
   * <div>Este metodo visualiza el menu inicial del usuario, para el registro y 
   *  autentificacion</div>
   * 
   * @return devuelve el primer caracter pulsado con numero que se pulsa
   * o 'n' si intro u otra cosa    
   ********************************************************************************/
  public Character menuInicio() 
    {
    //---------------------- variables  ----------------------------------
	@SuppressWarnings("resource")//para no cerrar Scanner
    Scanner teclado=new Scanner(System.in);
    Character tecla='0'; //captura la primera tecla pulsada
    String cadenaEntrada="";
     //limites de opcion
    char opcionInicial='1';
    char opcionFinal='3';
    //--------------------ejecucion--------------------------------------
    this.verCabecera();
    System.out.println(" 1 - Registrar un nuevo usuario");
    System.out.println(" 2 - Autentificarse en el sistema (hacer login)");
    System.out.println(" 3 - salir");
    System.out.println("");
    System.out.println("Pulsar una opcion y despues intro. Si es erronea volvera a este menu");
    System.out.print("opcion elegida : ");
    cadenaEntrada=teclado.nextLine();
    //si solo se pulsa intro, la cadena esta vacia, por lo que hay que volver a imprimir
    if ((cadenaEntrada.length()==0) ||
           ((cadenaEntrada.charAt(0)<opcionInicial)&&(cadenaEntrada.charAt(0)>opcionFinal)))
      {
      tecla='n';   
      }//fin si entrada vacia
     else
      {
      tecla=cadenaEntrada.charAt(0);   
      }
   return tecla;
   }//fin pantalla inicio
  
  /*********************************************************************************
   * <div>Visualiza un mensaje de fin de programa para el tipo de usuario</div> 
   * 
   * 
   ********************************************************************************/
  public void finDePrograma()
    {
	this.verCabecera();
    System.out.println("fin de "+tipoUsuario);
    }

  //captura de datos

  /***************************************************************************
   *  <div>Visualiza la obtencion de nombre de repositorio y contrasena
   *  no se permiten nombre de Repositorio en blanco;contrasena si.</div>
   *
   *  @param tipoLogin si es 1, es el registro, si es =2 es autentificar
   ****************************************************************************/
  public AcreditacionUsuario login(Character tipoLogin)  
    {
    AcreditacionUsuario acreditacionUsuario=new AcreditacionUsuario();
    @SuppressWarnings("resource")//para no cerrar Scanner
    Scanner teclado=new Scanner(System.in);
    String cadenaEntrada="";//para capturar la entrada
    //si tipoLogin=1, es el registto  ,
    if (tipoLogin==ALTA)
      {
      //imprimimos la cabecera
      cabeceraRegistro();
      }//fin si es tipo 1 registro
     else //sino es el login es AUTENTIFICAR_Repositorio
      {
      cabeceraLogin();   
      }//fin si no es tipo 1 (tipo 2)
    do//hacer hasta que se introduzca algo por teclado(no intro)
      {
      System.out.print("Introduce el nombre de "+tipoUsuario+"  : ");
      cadenaEntrada=teclado.nextLine();    
      //si es solo intro repite y sino sale al password
      if (cadenaEntrada.length()==0)
        {
        //repite pantalla
        //si tipoLogin=1, es el registro,
        if (tipoLogin==ALTA)
          {
          //imprimimos la cabecera
          cabeceraRegistro();
          }//fin si es tipo 1 registro
         else //si es AUTENTIFICA_Repositorio
          {
          cabeceraLogin();   
          }//fin si no es tipo 1 (tipo 2)
          
        }//fin si era solo intro
       else
        {
        acreditacionUsuario.setNombre(cadenaEntrada); 
        }//fin sino esta vacía
      }//fin repetir hasta que Repositorio sea algo
    while(acreditacionUsuario.getNombre().equals(""));
    //ahora capturamos la contrase�a. Vale la cadena vac�a
    System.out.print("Introduce la contrasena : ");
    //la introducimos en el objeto de acreditaci�n
    acreditacionUsuario.setPassword(obtenerPwd());
    return acreditacionUsuario;
    }//fin pantalla login  
  
  //mensajes
  /*********************************************************************************
   * <div>Visualiza mensaje de usuario (repositorio o cliente)registrado 
   * correctamente</div>
   * 
   ********************************************************************************/  
  public void altaUsuarioCorrecta()
    {
    //imprimimos la cabecera
    cabeceraRegistro();
    System.out.println("El "+tipoUsuario+" se ha registrado correctamente\n");
    pulsaIntro();
    }//fin altaRepositorioCorrecta


 
  /***************************************************************************
   * Visualiza mensaje de error en registro usuario:
   *
   ***************************************************************************/  
  public void errorAcreditacion()
    {
    //imprimimos la cabecera
    cabeceraRegistro();
    System.out.println("El "+tipoUsuario+" ya existe\n");
    pulsaIntro();
    }//fin errorAcreditacion

  /***************************************************************************
   * Visualiza mensaje de error en autenticacion usuario
   *
   ***************************************************************************/  
  public void errorAutenticacion()
    {
    cabeceraLogin();
    System.out.println("Nombre y/o contrasena erroneos\n");
    pulsaIntro();  
	}

  /***************************************************************************
   * Visualiza mensaje de sesion abierta para el usuario tipoUsuario
   *
   * @param tipoUsuario repositorio o cliente
   **************************************************************************/  
  public void errorSesionAbierta(String tipoUsuario)
    {
	this.verCabecera();
	System.out.println("La sesion del "+tipoUsuario+" ya esta abierta.") ; 
	//pulsa tecla
	pulsaIntro();   
    }//fin sesion ya abierta
  
  /***************************************************************************
   * <div>Visualiza mensaje cuando el repositorio asignado al cliente no esta
   * disponible.</div>
   * <div>Está aquí y no en GUICliente dado que es previo al inicio de 
   * sesion.</div>
   * 
   **************************************************************************/  
  public void errorRepoNoDisponible()
    {
    this.verCabecera();
	System.out.println("No se puede abrir sesión del "+tipoUsuario+" por no tener repositorios disponibles");
	//pulsa tecla
	pulsaIntro();  
	}//fin errorRepoNoDisponible
  
  /***************************************************************************
   * 
   * Visualiza un error cuando no encuentra el registro RMI
   * 
   **************************************************************************/
  public void errorRegistroRMI() 
    {
	this.verCabecera();
	System.out.println("No encuentra registro RMI desde "+tipoUsuario);
    //pulsa tecla
    pulsaIntro();
    
    }//fin errorRegistroRMI   
     
  /***************************************************************************
   * 
   * Visualiza un error cuando no encuentra el objeto remoto.
   * 
   * @param er cadena con el error del sistema
   * 
   **************************************************************************/
    public void errorAccesoRemotoObjeto(String er) 
     {
     this.verCabecera();	
     System.out.println("Error de acceso a objeto remoto en "+er+"\n" ); 
   //pulsa tecla
     pulsaIntro();
     }
     
  /***************************************************************************
    * 
    * Visualiza un error cuando no encuentra el objeto remoto en el registro
    * 
   * @param er cadena con el error del sistema
    **************************************************************************/
  public void errorNoEncuentraObjRemoto(String er)
    {
	this.verCabecera();  
    System.out.println("No se encuentra el objeto remoto en registro: "+ er + "\n");   
  //pulsa tecla
    pulsaIntro();
    }
    
   
  /***************************************************************************
   * 
   * Visualiza un error cuando el objeto remoto ya estaba registrado en el
   * registro. Se utiliza cuando se intenta abrir dos sesiones 
   * 
   * @param er cadena con el error del sistema
   **************************************************************************/
   public void errorObjRemotoYaRegistrado(String er)
     {
	 this.verCabecera();   
	 System.out.println("El objeto remoto ya estaba registrado"+er+"\n");  
	//pulsa tecla
	    pulsaIntro();
     }
   
   /***************************************************************************
    * 
    * Visualiza un error generico de acceso a objetos remotos. 
    * 
    * @param er cadena con el error del sistema
    **************************************************************************/
     public void errorGenerico(String er)
     {
	 this.verCabecera();   
     System.out.println("excepcion generica "+er+ "\n");   
   //pulsa tecla
     pulsaIntro();
     }  
  
  /***************************************************************************
   * Se visualiza el menu del usuario, según corresponda una vez acreditado.
   * 
   * @param iDUsuario obtiene el ID del usuario
   * @return devuelve el primer caracter pulsado con numero que se pulsa (u otra cosa)
   * o 'n' si intro
   **************************************************************************/ 
  public abstract Character menuUsuarioAcreditado(String iDUsuario);
   
  /*===========================================================================
   * Metodos protected
   * ========================================================================*/   
  
  /**************************************************************************
   * <div>Este metodo ejecuta un scroll de pantalla para limpiarla y 
   * visualizar solo lo que interesa</div>
   *************************************************************************/   
  protected void limpiarPantalla()
    {
    for (int i=0;i<40;i++)
      {
      System.out.println(); 
      }
    }//fin limpiar consola

  /**************************************************************************
   * <div>Este metodo implementa la forma de determinar la accion por la que 
   * el usuario se da por enterado del mensaje que el muestra el sistema.</div>
   * <div> Muestra uno u otro mensaje segun este en IDE o en consola. Esto es
   * así para sortear el problema de que los IDE desactivan la consola del 
   *  sistema</div>
   *************************************************************************/   
  protected void pulsaIntro()
    {
	System.out.println("");  
    System.out.print("Pulsa intro\n");
    obtenerPwd();
    }//fin pulsa Intro  
  
  /**************************************************************************
   * <div>Este metodo implementa la visualizacion de la cabecera de todas las
   * pantallas de usuario</div>
   *************************************************************************/   
  protected void verCabecera()
    {
    limpiarPantalla();
    //cabecera
	System.out.println("              "+tipoUsuario.toUpperCase()+" en IP: "+direccionIP);
    System.out.println("--------------------------------------------------------------------------");
    System.out.println("                   ALMACENAMIENTO EN LA NUBE");
    System.out.println("---------------------------------------------------------------------------");
    System.out.println("");
    }//fin ver cabecera    
     
  /**************************************************************************
   * <div>Este metodo obtiene una contrasena desde el teclado.</div>
   * <div> Muestra uno u otro mensaje segun este en IDE o en consola. Esto es
   * así para sortear el problema de que los IDE desactivan la consola del 
   *  sistema. Desde consola no se ve la contrasena y desde IDE si</div>
   *************************************************************************/        
   protected String obtenerPwd()
     {
     Console terminal = System.console();
     String cadenaEntrada="";
        //si consola virtual, contrase�a en claro
        if (terminal==null )
          {
          @SuppressWarnings("resource")//para no cerrar Scanner
          Scanner teclado=new Scanner(System.in);    
          System.out.print(". Fuera de consola. ");
          cadenaEntrada=teclado.nextLine();
          //si no es intro, se actualiza la contrase�a
          if (cadenaEntrada.length()==0)
            cadenaEntrada=""; //borrado por seguridad.
          }//fin si terminal==0
         else
          {//tiene consola
          try
            {
            cadenaEntrada=new String(terminal.readPassword());
            }
           catch  (IOError io)
            {
            System.out.println("no se pudo capturar : "+io);
            }
          }//fin sino terminal
       
      //aqui ciframos la contrasena    
      
      return cadenaEntrada;  
     }//fin pulsa Intro    
     
  /*===========================================================================
   * Metodos privados
   * ========================================================================*/   

   /***************************************************************************
    * Al tener la misma funcionalidad tanto el menu de autenticacion como el
    *  de alta, solo se diferencia en la cabecera. Esta es la de autenticacion 
    **************************************************************************/  
   private void cabeceraLogin()  
     {
     this.verCabecera();
     System.out.println("         LOGIN DE "+tipoUsuario.toUpperCase());
     for (int i=0;i<5;i++)
        {
        System.out.println(); 
        } 
     }//fin cabecera de login
   
   /***************************************************************************
    * Al tener la misma funcionalidad tanto el menu de autenticacion como el 
    * de alta, solo se diferencia en la cabecera. Esta es la de registro 
    **************************************************************************/  
   private void cabeceraRegistro()
     {
     this.verCabecera();
     System.out.println("            ALTA DE "+tipoUsuario.toUpperCase());
     for (int i=0;i<5;i++)
        {
        System.out.println(); 
        } 
     }//fin cabecera de registro   

  }//fin clase GUIRepositorioTexto