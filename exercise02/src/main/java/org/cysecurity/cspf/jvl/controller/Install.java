    package org.cysecurity.cspf.jvl.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cysecurity.cspf.jvl.model.HashMe;

/**
 *
 * @author breakthesec
 */
public class Install extends HttpServlet {

       static String dburl;
       static String jdbcdriver;
       static String dbuser;
       static String dbpass;
       static String dbname;
       static String siteTitle;
       static String adminuser;
       static String adminpass;
       static String env;
               
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String configPath=getServletContext().getRealPath("/WEB-INF/config.properties");
        
        //Getting Database Configuration from User Input
        dburl = request.getParameter("dburl");
        jdbcdriver = request.getParameter("jdbcdriver");
        dbuser = request.getParameter("dbuser");
        dbpass = request.getParameter("dbpass");
        dbname = request.getParameter("dbname");
        siteTitle= request.getParameter("siteTitle");
        adminuser= request.getParameter("adminuser");
        env = request.getParameter("env");
        adminpass= HashMe.hashMe(request.getParameter("adminpass"));
        
        //Moifying Configuration Properties:
         Properties config=new Properties();
         config.load(new FileInputStream(configPath));
         config.setProperty("dburl",dburl);
         config.setProperty("jdbcdriver",jdbcdriver);
         config.setProperty("dbuser",dbuser);
         config.setProperty("dbpass",dbpass);
         config.setProperty("dbname",dbname);
         config.setProperty("siteTitle",siteTitle);
         FileOutputStream fileout = new FileOutputStream(configPath);
         config.store(fileout, null); 
         fileout.close();
         
        response.setContentType("text/html;charset=UTF-8");
         try {
            PrintWriter out = response.getWriter();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet install</title>");            
            out.println("</head>");
            out.println("<body>");

            if(setup(env, dburl, dbuser, dbpass))
            {
                out.print("successfully installed");
            }
            else
            {
                out.print("Something went wrong. Unable to install");
            }
            out.println("</body>");
            out.println("</html>");
        }
         catch(Exception e)
         {
             
         }
    }
     protected boolean setup(String env, String dburl, String dbuser, String dbpass) throws IOException
    {
        
       if(env.equals("prod"))   
       {
           if(dburl.equals("jdbc:mysql://localhost:3306/db001prod"))
           {
                    try
                   {
                    Class.forName(jdbcdriver);
                    Connection con= DriverManager.getConnection(dburl,dbuser,dbpass);
                      if(con!=null && !con.isClosed())
                        {
                            //Database creation
                             Statement stmt = con.createStatement();  

                             stmt.executeUpdate("Create table users(ID int NOT NULL AUTO_INCREMENT, username varchar(30),email varchar(60), password varchar(60), about varchar(50),privilege varchar(20),avatar TEXT,secretquestion int,secret varchar(30),primary key (id))");
                            
                            if(!con.isClosed())
                            {   
                                return true;
                            }
                              return false;
                        }
                   }
                   catch(SQLException ex)
                   {
                      System.out.println("SQLException: " + ex.getMessage());
                     System.out.println("SQLState: " + ex.getSQLState());
                     System.out.println("VendorError: " + ex.getErrorCode());
                   }
                   catch(ClassNotFoundException ex)
                   {
                       System.out.print("JDBC Driver Missing:<br/>"+ex);
                   }
            }
            return false;
      
       }
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}