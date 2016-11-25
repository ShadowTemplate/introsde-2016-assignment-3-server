package introsde.assignment.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Measure {
    private Long mid;
    private Date dateRegistered;
    private String measureType;
    private String measureValue;
    private String measureValueType;
}