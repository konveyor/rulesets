package com.example.apps;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.builder.ExpressionBuilder;

import java.util.HashSet;
import java.util.Set;

public class AwsStreaming {

    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            @Override
            public Set<String> updateRoutesToCamelContext(CamelContext context) throws Exception {
                return new HashSet<>();
            }

            public void configure() {
                from("direct:listActivities")
                .setHeader("CamelAwsStateMachineOperation", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStateMachinesMaxResults", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStepFunctionsStateMachineActivityName", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStepFunctionsStateMachineActivityArn", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStateMachineActivitiesMaxResults", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStateMachineExecutionArn", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStateMachineExecutionName", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStateMachineExecutionInput", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStateMachineExecutionTraceHeader", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStateMachineExecutionHistoryMaxResults", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStateMachineExecutionHistoryIncludeExecutionData", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStateMachineExecutionHistoryReverseOrder", ExpressionBuilder.bodyExpression())
                .setHeader("CamelAwsStateMachineExecutionMaxResults", ExpressionBuilder.bodyExpression())
                 .to("aws2-step-functions://test?awsSfnClient=#awsSfnClient&operation=listActivities");
            }
        };
    }

}
