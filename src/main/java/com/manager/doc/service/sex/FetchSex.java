package com.manager.doc.service.sex;

import com.manager.doc.dao.sex.SexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FetchSex {
    @Autowired
    @Qualifier("sexDaoImpl")
    private SexDao fetchSex;

    public Map<Integer, String> choiceSex()  {

        return fetchSex.fetchSex();
    }
}
