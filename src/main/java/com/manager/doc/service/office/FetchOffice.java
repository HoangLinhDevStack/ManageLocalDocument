package com.manager.doc.service.office;

import com.manager.doc.dao.office.OfficeDao;
import com.manager.doc.model.office.Office;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FetchOffice {

    @Autowired
    @Qualifier("officeDaoImpl")
    private OfficeDao fetchOffice;

    public Map<Integer, String> choiceOffices() throws JSQLParserException {
        return fetchOffice.fetchOffice();
    }
}
