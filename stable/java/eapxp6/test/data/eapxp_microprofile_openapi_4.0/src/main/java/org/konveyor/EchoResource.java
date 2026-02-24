package org.konveyor;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

/**
 * @author Paul Ferraro
 */
@Path("/echo")
@Produces(MediaType.TEXT_PLAIN)
public class EchoResource {

    @GET
    @Path("{value}")
    public String echo(@RequestBody(content = @Content(mediaType = "text/plain")) @PathParam("value") String value) {
        return value;
    }
}

