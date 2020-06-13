package servidorAlertas;

import clienteHabitacion.utilidades.UtilidadesConsola;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
import servidorAlertas.utilidades.UtilidadesRegistroS;
import servidorAlertas.sop_rmi.ClsGestionAsintomaticosImpl;

public class ServidorDeObjetos
{
     	public static void main (String args[]) throws RemoteException, NotBoundException, MalformedURLException
	{
		int numPuertoRMIRegistry = 0;
		String direccionIpRMIRegistry = "";
                int numeroPacientes = 0;
                System.out.print("Digite el maximo numero de pacientes en el sistema (1 a 5): ");
                do
                {    numeroPacientes = UtilidadesConsola.leerEntero();
                     if(numeroPacientes < 1 || numeroPacientes > 5)
                        System.out.print("El maximo numero de pacientes en el sistema es de 1 a 5, digite valor nuevamente: ");
                }while(numeroPacientes < 1 || numeroPacientes > 5);
                
		System.out.print("Cual es la direccion ip donde se encuentra el rmiregistry: ");
		direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
		System.out.print("Cual es el numero de puerto por el cual escucha el rmiregistry: ");
		numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
		ClsGestionAsintomaticosImpl objRemoto = new ClsGestionAsintomaticosImpl(direccionIpRMIRegistry,numPuertoRMIRegistry);
		objRemoto.setNumeroPacientes(numeroPacientes);
                
		try
		{
			UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
			UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "ObjetoGestionAsintomaticos");
 
		}catch(RemoteException e)
		{
			System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto" + e.getMessage());
			
		}
	}

}