package kr.co.tlab.ng.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tl_board")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "meta_seq")
    @TableGenerator(name = "meta_seq", allocationSize = 1, initialValue = 1, table = "tl_sequence")
    private Integer id;

    @Column(name = "title", length = 127, nullable = false)
    private String title;

    @Column(name = "writer", length = 10, nullable = false)
    private String writer;

    @Column(name = "contents")
    @Type(type="text")
    private String contents;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime datetime = LocalDateTime.now();
}
