
package clienteHabitacion.sop_rmi;

import clienteHabitacion.GUICliente;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import servidorAlertas.dto.ClsAsintomaticoDTO;

public class ClsAsintomaticoCllbckImpl extends UnicastRemoteObject implements AsintomaticoCllbckInt
{       
        public ClsAsintomaticoDTO pacienteAsintomatico;
        GUICliente GUIC;
        
        public ClsAsintomaticoCllbckImpl() throws RemoteException
        {
                super(); //invoca al constructor de la clase base

        }
        
        public ClsAsintomaticoCllbckImpl(ClsAsintomaticoDTO pacienteAsintomatico) throws RemoteException
        {
                this.pacienteAsintomatico = pacienteAsintomatico;   
                
        }

        public ClsAsintomaticoCllbckImpl(ClsAsintomaticoDTO pacienteAsintomatico, GUICliente GUIC) throws RemoteException
        {
                this.pacienteAsintomatico = pacienteAsintomatico;   
                this.GUIC = GUIC;
        }
        
    @Override
    public ClsAsintomaticoDTO getPacienteAsintomatico() throws RemoteException {
        System.out.println("Desde getPacienteAsintomatico()...");
        System.out.println("Saliendo de getPacienteAsintomatico()...");
        return this.pacienteAsintomatico;
    }
    
    @Override
    public void notificarMensajeCllbck(String mensaje) throws RemoteException {
        System.out.println("Desde notificarMensajeCllbck()...");
        GUIC.fijarAlerta(mensaje);
        System.out.println(mensaje); 
        System.out.println("Saliendo de notificarMensajeCllbck()...");
    }

}
