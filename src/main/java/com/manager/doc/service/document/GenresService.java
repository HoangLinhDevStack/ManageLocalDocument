package com.manager.doc.service.document;

import com.manager.doc.dao.document.GenresDao;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenresService {

    @Autowired
    @Qualifier("genresDaoImpl")
    private GenresDao genresDao;

    public Map<Integer, String> listGenres() throws JSQLParserException {
        return genresDao.getIDAndNameGenres();
    }

}
