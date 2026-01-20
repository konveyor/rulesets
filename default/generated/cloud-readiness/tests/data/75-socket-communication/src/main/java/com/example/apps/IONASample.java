package com.example.apps;

import java.io.*;

public class IONASample
{ 
   
  public static void main(String args[]) 
  { 
    java.util.Properties props = System.getProperties(); 
    props.put("org.omg.CORBA.ORBClass", "com.iona.corba.art.artimpl.ORBImpl"); 
    props.put("org.omg.CORBA.ORBSingletonClass", "com.iona.corba.art.artimpl.ORBSingleton"); 
 
    System.out.println("initializing ORB");
     
    try  
    {
      if (!readCommandLineArguments(args))
      { 
        usage(); 
        return; 
      } 
       
      System.out.println("retrieving exported Bank reference"); 
 
      String ior = null; 

      System.out.println("reading stringified object reference from file");


      InputStream fis = null;
      InputStreamReader osr=new InputStreamReader(fis);
      BufferedReader br=new BufferedReader(osr);
      ior = br.readLine(); 
      br.close(); 
 
      System.out.println("calling ORB::string_to_object"); 
      System.out.println("IOR is :"+ior); 

      System.out.println("narrowing CORBA::Object"); 

    } 
    catch (java.io.IOException io_exc)
    { 
      System.err.println("IOException during string_to_object() or narrow()"); 
      io_exc.printStackTrace(); 
      System.exit(0); 
    } 
  }

  private static void usage() {

  }

  private static boolean readCommandLineArguments(String[] args) {
    return false;
  }

}