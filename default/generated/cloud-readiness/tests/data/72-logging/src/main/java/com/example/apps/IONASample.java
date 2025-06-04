package com.example.apps;

import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;

import java.io.*;

public class IONASample
{ 
   
  public static void main(String args[]) 
  { 
    java.util.Properties props = System.getProperties(); 
    props.put("org.omg.CORBA.ORBClass", "com.iona.corba.art.artimpl.ORBImpl"); 
    props.put("org.omg.CORBA.ORBSingletonClass", "com.iona.corba.art.artimpl.ORBSingleton"); 
 
    ORB orb = ORB.init(args, props);
     
    System.out.println("initializing ORB"); 
     
    try  
    {
      org.omg.CORBA_2_3.ORB orb23 = (org.omg.CORBA_2_3.ORB) orb;
       
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
      org.omg.CORBA.Object o = orb.string_to_object(ior); 
 
      System.out.println("narrowing CORBA::Object"); 

    } 
    catch (SystemException ex) {
      System.err.println("Exception during string_to_object() or narrow()"); 
      System.exit(0);
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