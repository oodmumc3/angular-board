package kr.co.tlab.ng.service.impl;

import kr.co.tlab.ng.model.entity.Board;
import kr.co.tlab.ng.model.to.BoardTO;
import kr.co.tlab.ng.orm.BoardDao;
import kr.co.tlab.ng.service.BoardService;
import kr.co.tlab.ng.util.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDao boardDao;

    @Override
    public List<BoardTO> findAll() {
        return boardDao.findAll().stream()
            .map(b -> new BoardTO(b.getId(), b.getTitle(), b.getWriter(), b.getContents(), LocalDateTimeUtil.convertLocalDateTimeTODate(b.getDatetime())))
            .collect(Collectors.toList());
    }

    @Override
    public boolean save(BoardTO boardTO) {
        LocalDateTime regDate = LocalDateTimeUtil.convertDateTOLocalDateTime(boardTO.getDatetime());
        Board board = new Board(null, boardTO.getTitle(), boardTO.getWriter(), boardTO.getContents(), regDate);
        return boardDao.save(board);
    }
}
