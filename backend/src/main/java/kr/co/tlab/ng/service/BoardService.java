package kr.co.tlab.ng.service;

import kr.co.tlab.ng.model.to.BoardTO;

import java.util.List;

public interface BoardService {
    List<BoardTO> findAll();

    boolean save(BoardTO boardTO);
}
