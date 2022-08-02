package org.romaine.scores.model.golf;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(
            name = "course_id")
    private Course course;
}
