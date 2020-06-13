package servidorNotificaciones;

import clienteHabitacion.utilidades.UtilidadesConsola;
import java.rmi.RemoteException;
import servidorAlertas.utilidades.UtilidadesRegistroS;
import servidorNotificaciones.sop_rmi.ClsNotificacionImpl;

public class ServidorDeObjetos
{
        
	public static void main (String args[]) throws RemoteException
	{
		int numPuertoRMIRegistry = 0;
		String direccionIpRMIRegistry = "";
		System.out.print("Cual es la direccion ip donde se encuentra el rmiregistry: ");
		direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
		System.out.print("Cual es el numero de puerto por el cual escucha el rmiregistry: ");
		numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
                ClsNotificacionImpl objRemoto = new ClsNotificacionImpl();
		
		try
		{
			UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
			UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoRemotoNotificaciones");
                        
		}catch(RemoteException e)
		{
			System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" + e.getMessage());
			
		}
	}
}