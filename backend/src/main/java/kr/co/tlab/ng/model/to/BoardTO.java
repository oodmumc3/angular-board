package kr.co.tlab.ng.model.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardTO {
    private Integer id;
    private String title;
    private String writer;
    private String contents;
    private Date datetime;
}
