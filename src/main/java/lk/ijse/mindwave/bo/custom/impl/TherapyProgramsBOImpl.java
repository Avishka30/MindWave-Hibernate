package lk.ijse.mindwave.bo.custom.impl;

import lk.ijse.mindwave.bo.custom.TherapyProgramsBO;
import lk.ijse.mindwave.dao.custom.TherapistDAO;
import lk.ijse.mindwave.dao.custom.TherapyProgramDAO;
import lk.ijse.mindwave.dao.custom.impl.TherapistDAOImpl;
import lk.ijse.mindwave.dao.custom.impl.TherapyProgramDAOImpl;
import lk.ijse.mindwave.dto.TherapyProgramDTO;
import lk.ijse.mindwave.entity.TherapyProgram;

import java.sql.SQLException;
import java.util.ArrayList;

public class TherapyProgramsBOImpl implements TherapyProgramsBO {

    TherapyProgramDAO therapyProgramDAO = new TherapyProgramDAOImpl();
    TherapistDAO therapistDAO = new TherapistDAOImpl();

    @Override
    public boolean saveTherapyPrograms(TherapyProgramDTO therapyProgramDTO) {
        return therapyProgramDAO.save(
                new TherapyProgram(
                        therapyProgramDTO.getProgramId(),
                        therapyProgramDTO.getProgramName(),
                        therapyProgramDTO.getDuration(),
                        therapyProgramDTO.getFee(),
                        new ArrayList<>(),
                        new ArrayList<>()
//                        therapist // Fix: Pass Therapist entity, not String
                )
        );
    }

    @Override
    public boolean updateTherapyPrograms(TherapyProgramDTO therapyProgramDTO) {
        return therapyProgramDAO.update(
                new TherapyProgram(
                        therapyProgramDTO.getProgramId(),
                        therapyProgramDTO.getProgramName(),
                        therapyProgramDTO.getDuration(),
                        therapyProgramDTO.getFee(),
                        new ArrayList<>(),
                        new ArrayList<>()
//                        therapist // Fix: Pass Therapist entity, not String
                )
        );
    }

    @Override
    public boolean deleteTherapyPrograms(String id) throws Exception {
        return therapyProgramDAO.deleteByPK(id);
    }

    @Override
    public ArrayList<TherapyProgramDTO> loadAllTherapyPrograms() throws SQLException, ClassNotFoundException {
        ArrayList<TherapyProgramDTO> therapyProgramDTOS = new ArrayList<>();
        ArrayList<TherapyProgram> therapyPrograms = (ArrayList<TherapyProgram>) therapyProgramDAO.getAll();

        for (TherapyProgram therapyProgram : therapyPrograms) {
            therapyProgramDTOS.add(
                    new TherapyProgramDTO(
                            therapyProgram.getProgramId(),
                            therapyProgram.getProgramName(),
                            therapyProgram.getDuration(),
                            therapyProgram.getFee()
                    )
            );
        }
        return therapyProgramDTOS;
    }

    @Override
    public String getNextTherapyProgramId() {
        return therapyProgramDAO.getNextId();
    }
}
