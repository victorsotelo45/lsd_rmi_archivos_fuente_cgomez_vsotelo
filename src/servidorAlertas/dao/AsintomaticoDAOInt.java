
package servidorAlertas.dao;

import java.util.ArrayList;
import servidorAlertas.dto.ClsAsintomaticoDTO;


public interface AsintomaticoDAOInt {
    
    public ArrayList<ClsAsintomaticoDAO> leerHistorialAsintomatico(int id_Asintomatico);
    public void escribirHistorialAsintomatico(ClsAsintomaticoDTO pacienteAsintomatico, String fechaAlerta, String horaAlerta, int puntuacion);
    
}
