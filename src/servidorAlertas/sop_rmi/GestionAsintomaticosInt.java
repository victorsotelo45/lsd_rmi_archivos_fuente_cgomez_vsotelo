
package servidorAlertas.sop_rmi;

import clienteHabitacion.sop_rmi.AsintomaticoCllbckInt;
import servidorAlertas.dto.ClsAsintomaticoDTO;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GestionAsintomaticosInt extends Remote{
    
    public boolean registrarAsintomatico(AsintomaticoCllbckInt objAsintomaticoCllbck) throws RemoteException;
    public ClsAsintomaticoDTO consultarAsintomatico(int id) throws RemoteException;
    public boolean enviarIndicadores(int id, int frecuenciaCardiaca, int frecuenciaRespiratoria, float temperatura) throws RemoteException;
    
    
}
