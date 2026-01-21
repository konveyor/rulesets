package org.konveyor;

import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.media.Schema;
import org.kohsuke.MetaInfServices;

/**
 * @author Paul Ferraro
 */
@MetaInfServices(OASFilter.class)
public class TestFilter implements OASFilter {

    @Override
    public Schema filterSchema(Schema schema) {
        Schema.SchemaType type = schema.getType();
        schema.setType(type);
        Boolean max = schema.getExclusiveMaximum();
        schema.setExclusiveMaximum(max);
        Boolean min = schema.getExclusiveMinimum();
        schema.setExclusiveMinimum(min);
        Boolean nullable = schema.getNullable();
        schema.setNullable(nullable);
        return schema;
    }
}

