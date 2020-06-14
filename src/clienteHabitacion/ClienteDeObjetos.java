package clienteHabitacion;

import clienteHabitacion.sop_rmi.ClsAsintomaticoCllbckImpl;
import clienteHabitacion.utilidades.UtilidadesConsola;
import clienteHabitacion.utilidades.UtilidadesRegistroC;
import java.io.File;
import static java.lang.System.exit;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import servidorAlertas.dto.ClsAsintomaticoDTO;
import servidorAlertas.sop_rmi.GestionAsintomaticosInt;

public class ClienteDeObjetos
{
	private static GestionAsintomaticosInt objetoRemotoServidorAlertas;
        
                
        public static void MenuPrincipal() throws RemoteException
        {
            String nombres, apellidos, tipo_id, direccion;
            int opcion, id, frecuenciaCardiaca, frecuenciaRespiratoria;
            float temperatura;
            boolean registroAsintomaticoCllbck, notificoIndicador;
            ClsAsintomaticoDTO pacienteAsintomatico;
            ClsAsintomaticoCllbckImpl nuevoAsintomaticoCllbck;
            
            do
            {
                System.out.println("Menu");
                System.out.println("1. Registrar Asintomatico:");
                System.out.println("2. Consultar Asintomatico:");
                System.out.println("3. Enviar Indicadores");
                System.out.println("4. Salir");
                System.out.print("Escoge una opcion: ");
                opcion = UtilidadesConsola.leerEntero();
                switch(opcion)
                {
                    case 1:
                           System.out.println("Registrar Datos del Paciente:");
                           System.out.print("Id: ");
                           id = UtilidadesConsola.leerEntero(); 
                           pacienteAsintomatico = objetoRemotoServidorAlertas.consultarAsintomatico(id);
                           if(pacienteAsintomatico == null)
                           {
                               System.out.print("Nombres: ");
                               nombres = UtilidadesConsola.leerCadena();
                               System.out.print("Apellidos: ");
                               apellidos = UtilidadesConsola.leerCadena();
                               System.out.print("Tipo de Id: ");
                               tipo_id = UtilidadesConsola.leerCadena();
                               System.out.print("Direccion: ");
                               direccion = UtilidadesConsola.leerCadena();
                               pacienteAsintomatico = new ClsAsintomaticoDTO(nombres, apellidos, tipo_id, id, direccion);
                               nuevoAsintomaticoCllbck = new ClsAsintomaticoCllbckImpl(pacienteAsintomatico);
                               registroAsintomaticoCllbck = objetoRemotoServidorAlertas.registrarAsintomatico(nuevoAsintomaticoCllbck);
                               if(registroAsintomaticoCllbck)
                                   System.out.println("Se registro paciente asintomatico exitosamente!!!");
                               else
                                   System.out.println("No se registro paciente asintomatico (error de registro o ya se registraron el maximo numero de pacientes)!!!");
                           
                           }else System.out.println("El paciente asintomatico ya esta registrado!!!");
                        
                    break;
                    
                    case 2:
                           System.out.print("Digite id del paciente asintomatico a consultar: ");
                           id = UtilidadesConsola.leerEntero();
                           pacienteAsintomatico = objetoRemotoServidorAlertas.consultarAsintomatico(id);
                           if(pacienteAsintomatico != null)
                           {
                                System.out.println("Datos del paciente asintomatico:");
                                System.out.println("Nombres: "+pacienteAsintomatico.getNombres());
                                System.out.println("Apellidos: "+pacienteAsintomatico.getApellidos());
                                System.out.println("Tipo de Id: "+pacienteAsintomatico.getTipo_id());
                                System.out.println("Id: "+pacienteAsintomatico.getId());
                                System.out.println("Direccion: "+pacienteAsintomatico.getDireccion());
                            System.out.println("Datos del paciente asintomatico consultados exitosamente!!!");
                           }else
                           {
                               System.out.println("No se consulto paciente asintomatico (error de consulta o el paciente no existe!!!)");
                           }
                    break;
                    
                    case 3:
                            System.out.print("Digite id del paciente asintomatico a enviar indicador: ");
                            id = UtilidadesConsola.leerEntero();
                            System.out.print("Digite la frecuencia cardiaca del paciente asintomatico: ");
                            frecuenciaCardiaca = UtilidadesConsola.leerEntero();
                            System.out.print("Digite la frecuencia respiratoria del paciente asintomatico: ");
                            frecuenciaRespiratoria = UtilidadesConsola.leerEntero();
                            System.out.print("Digite la temperatura del paciente asintomatico: ");
                            temperatura = UtilidadesConsola.leerFlotante();
                            notificoIndicador = objetoRemotoServidorAlertas.enviarIndicadores(id,frecuenciaCardiaca,frecuenciaRespiratoria,temperatura);
                            if(notificoIndicador)
                                System.out.println("Se notifico indicador de paciente asintomatico exitosamente!!!");
                            else System.out.println("Error al notificar indicador de paciente asintomatico!!!");
                                
                    break;
                    
                    case 4:
                        
                                exit(0);
                    break;
                    
                    default:
                                System.out.println("Opcion no valida!!!");
                    break;
                
                
                }
            
            }while(opcion!=4);   
            
            
            
        }
	
	public static void main (String [] args) throws NotBoundException, MalformedURLException, RemoteException
	{
		String directorioArchivo;
                int numPuertoRMIRegistry = 0;
		String direccionIpRMIRegistry = "";
		System.out.print("Cual es la direccion ip donde se encuentra el rmiregistry: ");
		direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
		System.out.print("Cual es el numero de puerto por el cual escucha el rmiregistry: ");	
		numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
		objetoRemotoServidorAlertas = (GestionAsintomaticosInt)UtilidadesRegistroC.ObtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry,"ObjetoGestionAsintomaticos");
                
                directorioArchivo = "src/servidorAlertas/";
                File archivo = new File(directorioArchivo+"historialDeAlertas.txt");
                if(archivo.delete())
                   System.out.println("El archivo historialDeAlertas.txt ha sido borrado exitosamente!!!");
                else System.out.println("El archivo historialDeAlertas.txt no se ha podido borrar o no existe!!!");
                
                MenuPrincipal();
	
	}
	
}