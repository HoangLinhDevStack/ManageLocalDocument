package com.manager.doc.dao.document;

import com.manager.doc.dao.interact.InteractDao;
import com.manager.doc.model.document.Genres;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GenresDaoImpl implements GenresDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("interactDaoImpl")
    private InteractDao interactDao;

    @Override
    public Map<Integer, String> getIDAndNameGenres() throws JSQLParserException {
        String sql = "SELECT IDGenres, GenresName FROM genres";
        return interactDao.keyAndValueRowByRow(sql);
    }
}
