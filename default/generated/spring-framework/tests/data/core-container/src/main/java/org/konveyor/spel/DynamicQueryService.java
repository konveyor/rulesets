package java.org.konveyor.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicQueryService {

    public String generateSql(List<Integer> ids) {
        ExpressionParser parser = new SpelExpressionParser();
        SimpleEvaluationContext context = new SimpleEvaluationContext();

        context.setRootObject(ids);
        Expression expression = parser.parseExpression("'IN (' + #root.join(\',\') + ')'");
        String inClause = (String) expression.getValue(context);
        return "SELECT * FROM users WHERE user_id " + inClause;
    }
}
