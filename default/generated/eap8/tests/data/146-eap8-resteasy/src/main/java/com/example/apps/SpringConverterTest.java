package com.example.apps;

import org.jboss.resteasy.client.ProxyFactory;
   import org.jboss.resteasy.spi.StringConverter;
   import org.jboss.resteasy.test.BaseResourceTest;

   import javax.ws.rs.HeaderParam;
   import javax.ws.rs.MatrixParam;
   import javax.ws.rs.PUT;
   import javax.ws.rs.Path;
   import javax.ws.rs.PathParam;
   import javax.ws.rs.QueryParam;
   import javax.ws.rs.ext.Provider;

   public class SpringConverterTest extends BaseResourceTest
   {
      public static class POJO
      {
         private String name;

         public String getName()
         {
            return name;
         }

         public void setName(String name)
         {
            this.name = name;
         }
      }

      @Provider
      public static class POJOConverter implements StringConverter<POJO>
      {
         public POJO fromString(String str)
         {
            System.out.println("FROM STRNG: " + str);
            POJO pojo = new POJO();
            pojo.setName(str);
            return pojo;
         }

         public String toString(POJO value)
         {
            return value.getName();
         }
      }

      @Path("/")
      public static class MyResource
      {
         @Path("{pojo}")
         @PUT
         public void put(@QueryParam("pojo")POJO q, @PathParam("pojo")POJO pp,
                         @MatrixParam("pojo")POJO mp, @HeaderParam("pojo")POJO hp)
         {
         }
      }

      public void setUp() throws Exception
      {
         dispatcher.getRegistry().addPerRequestResource(MyResource.class);
      }

      @Path("/")
      public static interface MyClient
      {
         @Path("{pojo}")
         @PUT
         void put(@QueryParam("pojo")POJO q, @PathParam("pojo")POJO pp,
                  @MatrixParam("pojo")POJO mp, @HeaderParam("pojo")POJO hp);
      }

      public void testIt() throws Exception
      {
         MyClient client = ProxyFactory.create(MyClient.class, "http://localhost:8081");
         POJO pojo = new POJO();
         pojo.setName("pojo");
         client.put(pojo, pojo, pojo, pojo);
      }
   }