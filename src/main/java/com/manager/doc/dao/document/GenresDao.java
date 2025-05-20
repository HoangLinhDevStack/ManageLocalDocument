package com.manager.doc.dao.document;

import com.manager.doc.model.document.Genres;
import net.sf.jsqlparser.JSQLParserException;

import java.util.List;
import java.util.Map;

public interface GenresDao {
    Map<Integer, String> getIDAndNameGenres() throws JSQLParserException;
}