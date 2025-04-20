package lk.ijse.mindwave.view.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class TherapistTM {
    private String id;
    private String name;
    private String specialization;
    private String availability;
}
