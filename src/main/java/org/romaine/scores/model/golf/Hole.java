package org.romaine.scores.model.golf;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
import javax.persistence.Id;
import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Hole {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    /**
     * Standard course size
     *  1-18
     */
    @Column(nullable = false)
    private byte holeNumber;

    /**
     * Par 2 for miniature golf
     * 2-5
     */
    @Column(nullable = false)
    private byte par;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "course_id")
    private Course course;
}
