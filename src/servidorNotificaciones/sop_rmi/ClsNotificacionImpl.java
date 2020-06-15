
package servidorNotificaciones.sop_rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import servidorAlertas.dao.ClsAsintomaticoDAOImpl;
import servidorAlertas.dao.AsintomaticoDAOInt;
import servidorAlertas.dao.ClsAsintomaticoDAO;
import servidorAlertas.dto.ClsAsintomaticoDTO;
import servidorNotificaciones.GUINotificaciones;
import servidorNotificaciones.dto.ClsMensajeNotificacionDTO;
import java.applet.AudioClip;


public class ClsNotificacionImpl extends UnicastRemoteObject implements NotificacionInt{
    HashMap<Integer, GUINotificaciones> GUIAsintomaticos;

    public ClsNotificacionImpl() throws RemoteException{
        GUIAsintomaticos = new HashMap();
    }
    
    @Override
    public void notificarRegistro(ClsMensajeNotificacionDTO objMensajeNotificacion) throws RemoteException {
        
        System.out.println("Desde notificarRegistro()...");
     
        ClsAsintomaticoDTO pacienteAsintomatico = objMensajeNotificacion.getPacienteAsintomatico();
        ArrayList<ClsAsintomaticoDAO> pacientesDAO;
        int frecuanciaCardiaca, frecuenciaRespiratoria;
        float temperatura;
        
        /*System.out.println("Alerta Generada");
        System.out.println("Id: "+pacienteAsintomatico.getId());
        System.out.println("Nombres: "+pacienteAsintomatico.getNombres());
        System.out.println("Apellidos: "+pacienteAsintomatico.getApellidos());
        System.out.println("Direccion: "+pacienteAsintomatico.getDireccion());*/
        //Para cada paciente se crea un GUI
        GUINotificaciones GUI;
        if(GUIAsintomaticos.containsKey(pacienteAsintomatico.getId())){
            GUI = GUIAsintomaticos.get(pacienteAsintomatico.getId()); 
            GUI.limpiarIndicadores();
            //GUI.limpiarAlertas();
        }else{
            GUI = new GUINotificaciones();
            GUIAsintomaticos.put(pacienteAsintomatico.getId(), GUI);
        }
            
        if(!GUI.isVisible())
            GUI.setVisible(true);
        //Enviar al GUI los datos del paciente
        GUI.fijarAsintomatico(pacienteAsintomatico.getTipo_id(),pacienteAsintomatico.getId(),pacienteAsintomatico.getNombres(),
        pacienteAsintomatico.getApellidos(), pacienteAsintomatico.getDireccion());
        frecuanciaCardiaca = objMensajeNotificacion.getFrecuenciaCardiaca();
        frecuenciaRespiratoria = objMensajeNotificacion.getFrecuenciaRespiratoria();
        temperatura = objMensajeNotificacion.getTemperatura();
        //System.out.println("Indicadores que generaron la Alerta:");
        //Enviar al GUI los indicadores
        if(frecuanciaCardiaca != 0)
            //System.out.println("Frecuencia Cardiaca: "+frecuanciaCardiaca);
            GUI.fijarFrecuenciaCardiaca(frecuanciaCardiaca);
        if(frecuenciaRespiratoria != 0)
            //System.out.println("Frecuencia Respiratoria: "+frecuenciaRespiratoria);
            GUI.fijarFrecuenciaRespiratoria(frecuenciaRespiratoria);
        if(temperatura != 0)
            //System.out.println("Temperatura: "+temperatura);
            GUI.fijarTemperatura(temperatura);

        String fecha = objMensajeNotificacion.getFechaAlerta();
        String hora = objMensajeNotificacion.getHoraAlerta();
        GUI.fijarHoraFecha(hora, fecha);
        //Enviar al GUI el mensaje del tipo de alerta
        String mensaje = objMensajeNotificacion.getMensaje();
        
        GUI.fijarMensajeTipoAlerta(mensaje);
        AudioClip sonido;
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/alerta.wav"));
        sonido.play();
        //System.out.println("El personal medico debe revisar el paciente");
        AsintomaticoDAOInt objetoAsintomaticoDAO = new ClsAsintomaticoDAOImpl();
        pacientesDAO = objetoAsintomaticoDAO.leerHistorialAsintomatico(pacienteAsintomatico.getId());
        GUI.fijarAlerta(pacientesDAO);
        
        System.out.println("Saliendo de notificarRegistro()...");
        
    }
   
}
