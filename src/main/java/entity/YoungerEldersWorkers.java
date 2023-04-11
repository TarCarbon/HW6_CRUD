package entity;

import lombok.Builder;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@ToString
public class YoungerEldersWorkers {
    private String type;
    private String name;
    private LocalDate birthday;
}
