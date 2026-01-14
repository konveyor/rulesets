package com.example.apps;

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;

/**
 * @author <a href="mailto:dklingenberg@gmail.com">David Klingenberg</a>
 */
<<<<<<< Updated upstream
@DataSourceDefinitions({
    @DataSourceDefinition(
            name = "jdbc/single-DS",
            className="com.example.MyDataSource",
            portNumber=6689,
            serverName="example.com",
            user="lance",
            password="secret"
    )
})
=======
@DataSourceDefinition(
        name = "jdbc/single-DS",
        className="com.example.MyDataSource",
        portNumber=6689,
        serverName="example.com",
        user="lance",
        password="secret"
)
>>>>>>> Stashed changes
public class AnnotationSingleDs
{
}
