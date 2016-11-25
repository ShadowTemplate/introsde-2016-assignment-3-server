package introsde.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Person {
    private Long id;
    private String firstname;
    private String lastname;
    private List<Measure> currentHealth; // one for each type of measure
    private List<Measure> healthHistory; // all measurements
}
