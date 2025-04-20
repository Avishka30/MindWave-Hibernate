package lk.ijse.mindwave.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TherapyProgramTM {
    private String id;
    private String name;
    private int duration;
    private double fee;
    private String therapistId;
}
