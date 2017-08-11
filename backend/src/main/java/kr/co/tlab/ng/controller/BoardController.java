package kr.co.tlab.ng.controller;

import kr.co.tlab.ng.model.to.BoardTO;
import kr.co.tlab.ng.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BoardService boardService;

    @RequestMapping(
        value = "/board",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public List<BoardTO> boardList() {
        return boardService.findAll();
    }

    @RequestMapping(
        value = "/board",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public boolean saveBoard(
        @RequestBody BoardTO boardTO
    ) {
        logger.info("## boardTO :: {}", boardTO);
        return boardService.save(boardTO);
    }
}
