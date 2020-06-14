
package servidorAlertas.sop_rmi;

import clienteHabitacion.sop_rmi.AsintomaticoCllbckInt;
import servidorAlertas.utilidades.UtilidadesRegistroC;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import servidorAlertas.dao.ClsAsintomaticoDAOImpl;
import servidorAlertas.dao.AsintomaticoDAOInt;
import servidorAlertas.dto.ClsAsintomaticoDTO;
import servidorNotificaciones.dto.ClsMensajeNotificacionDTO;
import servidorNotificaciones.sop_rmi.NotificacionInt;

public class ClsGestionAsintomaticosImpl extends UnicastRemoteObject implements GestionAsintomaticosInt {

    
    private static NotificacionInt objetoRemotoServidorNotificaciones;
    private ArrayList<AsintomaticoCllbckInt> asintomaticos;
    private int numeroPacientes;

    public ClsGestionAsintomaticosImpl(String direccionIpRMIRegistry, int numPuertoRMIRegistry) throws RemoteException, NotBoundException, MalformedURLException {
        this.asintomaticos = new ArrayList<AsintomaticoCllbckInt>();
        this.numeroPacientes=0;
        objetoRemotoServidorNotificaciones = (NotificacionInt)UtilidadesRegistroC.ObtenerObjRemoto(direccionIpRMIRegistry, numPuertoRMIRegistry,"ObjetoRemotoNotificaciones");
             
    }

    public void setNumeroPacientes(int numeroPacientes) {
        this.numeroPacientes = numeroPacientes;
    }

    public int getNumeroPacientes() {
        return this.numeroPacientes;
    }
    
    @Override
    public boolean registrarAsintomatico(AsintomaticoCllbckInt objAsintomaticoCllbck) throws RemoteException {
            
        System.out.println("Desde registrarAsintomatico()...");
        int id = objAsintomaticoCllbck.getPacienteAsintomatico().getId();
        boolean  bandera = false;
        if(existeAsintomatico(id) == null)       
        {   if(this.asintomaticos.size() < this.numeroPacientes)
            {
                  bandera = this.asintomaticos.add(objAsintomaticoCllbck);

            }else
            {
                JOptionPane.showMessageDialog(null, "Ya se registraron el maximo numero de pacientes: "+this.numeroPacientes+" !!!");
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "El paciente con id "+id+" ya se encuentra registrado!!!");
        }
        System.out.println("Saliendo de registrarAsintomatico()...");
        return bandera;
           
    }
    
    @Override
    public ClsAsintomaticoDTO consultarAsintomatico(int id) throws RemoteException
    {
            System.out.println("Desde consultarAsintomatico()...");
            AsintomaticoCllbckInt objAsintomaticoCllbck = existeAsintomatico(id);
            ClsAsintomaticoDTO pacienteAsintomatico = null;
            if(objAsintomaticoCllbck != null)
            {   
                pacienteAsintomatico = objAsintomaticoCllbck.getPacienteAsintomatico();
                
            }else
            {
               JOptionPane.showMessageDialog(null, "El paciente con id "+id+" no existe!!!"); 
            }
            System.out.println("Saliendo de consultarAsintomatico()...");
                
            return pacienteAsintomatico;
		
    }

    @Override
    public boolean enviarIndicadores(int id, int frecuenciaCardiaca, int frecuenciaRespiratoria,float temperatura) throws RemoteException {
            
        System.out.println("Desde enviarIndicadores()...");
        String nombres, apellidos, tipo_id, mensaje;
        String fechaAlerta, horaAlerta, strDateFormatFecha, strDateFormatHora;
        Date fechaActual;
        SimpleDateFormat objSDF;
        strDateFormatFecha = "dd-MM-yyyy";
        strDateFormatHora = "HH:mm:ss";
        fechaActual = new Date();
        objSDF = new SimpleDateFormat(strDateFormatFecha);
        fechaAlerta = objSDF.format(fechaActual);
        objSDF = new SimpleDateFormat(strDateFormatHora);
        horaAlerta =  objSDF.format(fechaActual);
        boolean bandera = false;
        int puntuacionIndicadores = 0; 
        int puntuacionFrecCardiaca = 0, puntuacionFrecRespiratoria = 0, puntuacionTemperatura = 0;
        AsintomaticoDAOInt objetoAsintomaticoDAO;    
        ClsMensajeNotificacionDTO objMensajeNotificacion;
        ClsAsintomaticoDTO pacienteAsintomatico;
        
        AsintomaticoCllbckInt objAsintomaticoCllbck = existeAsintomatico(id);
        if(objAsintomaticoCllbck != null)
        {
            if(frecuenciaCardiaca < 60 || frecuenciaCardiaca > 80) 
            {
                puntuacionIndicadores++;
                puntuacionFrecCardiaca = 1;
            }
            if(frecuenciaRespiratoria < 70 || frecuenciaRespiratoria > 90)
            { 
                puntuacionIndicadores++;
                puntuacionFrecRespiratoria = 1;
            }
            if(temperatura < 36.2 || temperatura > 37.2) 
            {
                puntuacionIndicadores++;
                puntuacionTemperatura = 1;
            }
            
            pacienteAsintomatico = objAsintomaticoCllbck.getPacienteAsintomatico();
            
            nombres = pacienteAsintomatico.getNombres();
            apellidos = pacienteAsintomatico.getApellidos();
            tipo_id = pacienteAsintomatico.getTipo_id();
            
            objetoAsintomaticoDAO = new ClsAsintomaticoDAOImpl();
            
            if(puntuacionIndicadores == 0 || puntuacionIndicadores == 1)
            {
                System.out.println("El paciente "+nombres+" "+apellidos+" identificado con ["+tipo_id+"]["+id+"] debe continuar monitorizacion!!!");
                
            }
            
            if(puntuacionIndicadores == 2)
            {
                objetoAsintomaticoDAO.escribirHistorialAsintomatico(pacienteAsintomatico, fechaAlerta, horaAlerta, puntuacionIndicadores);
                mensaje = "Alerta, el personal médico debe visitar al paciente "+nombres+" "+apellidos+" identificado con ["+tipo_id+"]["+id+"]!!!";
                objAsintomaticoCllbck.notificarMensajeCllbck(mensaje);
                if(puntuacionFrecCardiaca == 0) frecuenciaCardiaca = 0;
                if(puntuacionFrecRespiratoria == 0) frecuenciaRespiratoria = 0;
                if(puntuacionTemperatura == 0) temperatura = 0;
                objMensajeNotificacion = new ClsMensajeNotificacionDTO(pacienteAsintomatico, frecuenciaCardiaca, frecuenciaRespiratoria, temperatura, fechaAlerta, horaAlerta, mensaje);
                objetoRemotoServidorNotificaciones.notificarRegistro(objMensajeNotificacion);
            }
            
            if(puntuacionIndicadores >= 3)
            {   
                objetoAsintomaticoDAO.escribirHistorialAsintomatico(pacienteAsintomatico, fechaAlerta, horaAlerta, puntuacionIndicadores);
                mensaje = "Alerta, el personal médico debe remitir el paciente "+nombres+" "+apellidos+" identificado con ["+tipo_id+"]["+id+"] al hospital!!!";
                objAsintomaticoCllbck.notificarMensajeCllbck(mensaje);
                objMensajeNotificacion = new ClsMensajeNotificacionDTO(pacienteAsintomatico, frecuenciaCardiaca, frecuenciaRespiratoria, temperatura, fechaAlerta, horaAlerta, mensaje);
                objetoRemotoServidorNotificaciones.notificarRegistro(objMensajeNotificacion);
            }
               
            bandera = true;
        }else System.out.println("El id del paciente asintomatico no existe!!!");
        
        System.out.println("Saliendo de enviarIndicadores()...");
        return bandera;
    }
    
    public AsintomaticoCllbckInt existeAsintomatico(int id) throws RemoteException
    {
        AsintomaticoCllbckInt resultado = null;
        for(AsintomaticoCllbckInt objAsintomaticoCllbck:asintomaticos)
        {
            if(objAsintomaticoCllbck.getPacienteAsintomatico().getId() == id)
            {    resultado = objAsintomaticoCllbck;
                  break;  
            }
        }
        return resultado;
    }

}
