
package servidorNotificaciones.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import servidorAlertas.dao.ClsAsintomaticoDAOImpl;
import servidorAlertas.dao.AsintomaticoDAOInt;
import servidorAlertas.dao.ClsAsintomaticoDAO;
import servidorAlertas.dto.ClsAsintomaticoDTO;
import servidorNotificaciones.dto.ClsMensajeNotificacionDTO;


public class ClsNotificacionImpl extends UnicastRemoteObject implements NotificacionInt{

    public ClsNotificacionImpl() throws RemoteException{
    }
    
    @Override
    public void notificarRegistro(ClsMensajeNotificacionDTO objMensajeNotificacion) throws RemoteException {
        
        System.out.println("Desde notificarRegistro()...");
        
        ClsAsintomaticoDTO pacienteAsintomatico = objMensajeNotificacion.getPacienteAsintomatico();
        ArrayList<ClsAsintomaticoDAO> pacientesDAO;
        int frecuanciaCardiaca, frecuenciaRespiratoria;
        float temperatura;
        
        System.out.println("Alerta Generada");
        System.out.println("Id: "+pacienteAsintomatico.getId());
        System.out.println("Nombres: "+pacienteAsintomatico.getNombres());
        System.out.println("Apellidos: "+pacienteAsintomatico.getApellidos());
        System.out.println("Direccion: "+pacienteAsintomatico.getDireccion());
        frecuanciaCardiaca = objMensajeNotificacion.getFrecuenciaCardiaca();
        frecuenciaRespiratoria = objMensajeNotificacion.getFrecuenciaRespiratoria();
        temperatura = objMensajeNotificacion.getTemperatura();
        System.out.println("Indicadores que generaron la Alerta:");
        if(frecuanciaCardiaca != 0)
            System.out.println("Frecuencia Cardiaca: "+frecuanciaCardiaca);
        if(frecuenciaRespiratoria != 0)
            System.out.println("Frecuencia Respiratoria: "+frecuenciaRespiratoria);
        if(temperatura != 0)
            System.out.println("Temperatura: "+temperatura);
        
        System.out.println("El personal medico debe revisar el paciente");
        AsintomaticoDAOInt objetoAsintomaticoDAO = new ClsAsintomaticoDAOImpl();
        pacientesDAO = objetoAsintomaticoDAO.leerHistorialAsintomatico(pacienteAsintomatico.getId());
        
        for(ClsAsintomaticoDAO objPacienteDAO: pacientesDAO)
        {
            System.out.println(objPacienteDAO.getFechaAlerta()+"   "+objPacienteDAO.getHoraAlerta()+"          "+objPacienteDAO.getPuntuacion());
        }
        
        System.out.println("Saliendo de notificarRegistro()...");
        
    }
   
}
