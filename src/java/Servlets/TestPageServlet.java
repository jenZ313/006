/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author darcy
 */
@WebServlet(name = "TestPageServlet", urlPatterns = {"/TestPageServlet"})
public class TestPageServlet extends testServlet {

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
        int userId=getUserId(request);
        String[] oT= (new BackEnd.Read.Teacher.ReadTests(userId)).read().toString().split(",");
        
        List<Integer> ownedTest = new ArrayList<Integer>();
        for(int i=0; i<oT.length;i++){
            if(!oT[i].equals("")){
            ownedTest.add(Integer.parseInt(oT[i]));
            }
        }
        for(Integer i:ownedTest){
            request.setAttribute("test"+i, (new BackEnd.Read.Test.ReadName(i)).read());
        }
        request.setAttribute("ownedTest",ownedTest);
        request.setAttribute("userId", userId);
        request.setAttribute("userName", (new BackEnd.Read.Teacher.ReadName(userId)).read());
        if(request.getParameter("groupId")!=null){
            request.setAttribute("groupId", Integer.parseInt(request.getParameter("groupId")));
        }
        RequestDispatcher r= request.getRequestDispatcher("TestPage.jsp");
        r.forward(request, response);
    }
    
    public void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    (new BackEnd.Command.createTestCommand(request.getParameter("testName"), getUserId(request), new java.util.Date(), 0)).execute();
    //testManager.creatExame(request.getParameter("testName"),Integer.parseInt(request.getParameter("timeLimit")), getUserId(request),0);
    processRequest(request,response);
    } 
    public void detal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TestDetalServlet?testId="+request.getParameter("testId"));
    }
    public void back(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TeacherPageServlet");
    }
    public void asign(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("choosingTime.jsp?testId="+request.getParameter("testId")+"&groupId="+request.getParameter("groupId"));
    }
}
