package servidor; 



/******************************************************************************
 * NubeRMI contiene los BIND de la aplicación NUBE en RMI
 * En el caso de repositorio y cliente, solo el inicio, pues dependen de su ID
 * 
 * @see MiRegistroRMI
 * 
 * 
 * Con licencia GPL v3 (añadir enlace a la licencia)
 * 
 * @author Roberto J. de la Fuente Lopez 
 *         rfuente4@alumno.uned.es
 *         robertofl@aconute.es
 * @version 20210105
 ******************************************************************************/
public class NubeRMI //implements Serializable
  {
  //datos de configuración de servidor
  //la IP del host con el servicio RMI   
  private static String IPHostRegistroRMI="localhost";
  //EL PUERT DE ESCUCHA DEL REGISTRO RMI
  private static Integer puertoServidorRMI=1099;  
   
  //nombre simbólico del objeto remoto de acceso a datos 
  private static String bindDatos="servidor/BD";
  //nombre simbólico del objeto remoto del servicio gestor
  private static String bindGestor="servidor/gestor";
  //nombre simbólico del objeto remoto del servicio  autenticacion
  private static String bindAutenticacion="servidor/autenticacion";
  //datos de configuración de repositorio
  //nombre objeto remoto servicio repo servidor
  private static String bindRepoSr="Repositorio/SR/";
  //nombre objeto remoto servicio repo cliente
  private static String bindRepoCl="Repositorio/CL/";
  //nombre objeto remoto para servicio disco cliente
  private static String bindDiscoCliente="Cliente/";     
      
 
 //getters
 
  public static String getIPServidorRMI()
    {
    return IPHostRegistroRMI;
    }//fin getIPServidor
    
  public static Integer getPuertoServidorRMI()
    {
    return puertoServidorRMI;   
    }
 
  public static String getBindDatos()
    {
    return bindDatos;	 
    }
	
  public static String getBindGestor()
    {
	return bindGestor;  
	}
	
  public static String getBindAutenticacion()
    {
	return bindAutenticacion;  
    }
  
  public static String getBindRepoSr()
   {
   return bindRepoSr; 
   }
 
 public static String getBindRepoCl()
   {
   return bindRepoCl; 
   }
 
 public static String getBindDiscoCliente()
  {
  return bindDiscoCliente;
  
  }
	
   
  }//fin claseNubeRMI
