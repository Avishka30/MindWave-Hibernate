package lk.ijse.mindwave.bo.custom;

import lk.ijse.mindwave.dto.TherapyProgramDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TherapyProgramsBO {
    boolean saveTherapyPrograms(TherapyProgramDTO therapyProgramDTO);
    boolean updateTherapyPrograms(TherapyProgramDTO therapyProgramDTO);
    boolean deleteTherapyPrograms(String id) throws Exception;
    ArrayList<TherapyProgramDTO> loadAllTherapyPrograms() throws SQLException, ClassNotFoundException ;
    String getNextTherapyProgramId();
}
