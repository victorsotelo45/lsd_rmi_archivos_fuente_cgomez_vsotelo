package servidorAlertas.utilidades;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class UtilidadesRegistroC
{
	public static Remote ObtenerObjRemoto(String dirIP, int puerto, String nameObjReg) throws NotBoundException, MalformedURLException, RemoteException
	{
		String URLRegistro;
		URLRegistro = "rmi://"+ dirIP + ":" + puerto + "/" + nameObjReg;
		try
		{
			return Naming.lookup(URLRegistro);
			
		}
                
                
                catch(MalformedURLException | NotBoundException | RemoteException e)
		{
			System.out.println("Excepcion en obtencion del objeto remoto" + e);
			return null;
			
		}
	}
}