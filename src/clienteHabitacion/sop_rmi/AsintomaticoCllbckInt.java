package clienteHabitacion.sop_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import servidorAlertas.dto.ClsAsintomaticoDTO;

public interface AsintomaticoCllbckInt extends Remote{
        public ClsAsintomaticoDTO getPacienteAsintomatico() throws RemoteException;
	public void notificarMensajeCllbck(String mensaje) throws RemoteException;
}

