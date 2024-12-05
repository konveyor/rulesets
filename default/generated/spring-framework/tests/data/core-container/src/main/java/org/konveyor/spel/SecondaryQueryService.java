package java.org.konveyor.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.*;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SecondaryQueryService {

    @Autowired
    private SimpleEvaluationContext evaluationContext;

    public String generateSql(List<Integer> ids) {
        ExpressionParser parser = new SpelExpressionParser();

        evaluationContext.setRootObject(ids);
        Expression expression = parser.parseExpression("'IN (' + #root.join(\',\') + ')'");
        String inClause = (String) expression.getValue(evaluationContext);
        return "SELECT * FROM users WHERE user_id " + inClause;
    }
}
