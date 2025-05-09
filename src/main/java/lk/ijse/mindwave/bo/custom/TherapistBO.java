package lk.ijse.mindwave.bo.custom;

import lk.ijse.mindwave.dto.TherapistDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TherapistBO {
    boolean saveTherapist(TherapistDTO therapistDTO);
    boolean updateTherapist(TherapistDTO therapistDTO);
    boolean deleteTherapist(String id) throws Exception;
    ArrayList<TherapistDTO> loadAllTherapists() throws SQLException, ClassNotFoundException ;
    String getNaxtTherapistID();

}
