package kr.co.tlab.ng.orm.impl;

import kr.co.tlab.ng.model.entity.Board;
import kr.co.tlab.ng.orm.BaseDao;
import kr.co.tlab.ng.orm.BoardDao;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl extends BaseDao<Board, Integer> implements BoardDao {
}
