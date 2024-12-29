package com.manager.doc.service.parsesql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.springframework.stereotype.Service;

@Service
public class BodyColumnSQLImpl implements IBodyColumnSQL {
    @Override
    public String[] keyAndValue(String sql) throws JSQLParserException {
        Select selectStatement = (Select) CCJSqlParserUtil.parse(sql); //! discover flow
        SelectBody selectBody = selectStatement.getSelectBody();

        if(selectBody instanceof PlainSelect) {
            PlainSelect plainSelect = (PlainSelect) selectBody;
            return new String[]{plainSelect.getSelectItems().get(0).toString(),
                                plainSelect.getSelectItems().get(1).toString()};
        }
        return new String[]{null, null};
    }
}
