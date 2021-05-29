package com.softnet.medical.model;


import com.softnet.medical.config.StringPrefixedIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    @GenericGenerator(
            name = "patient_seq",
            strategy = "com.softnet.medical.config.StringPrefixedIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = StringPrefixedIdGenerator.VALUE_PREFIX_PARAMETER, value = "PAT_"),
                    @Parameter(name = StringPrefixedIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
    private String patientCode;

    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    public String getName(){
        return firstName.concat(" ").concat(lastName);
    }
}
