/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

//import static Servlets.testServlet.userGroupManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import BackEnd.Command.Command;
import java.time.LocalDateTime;
import java.time.LocalTime;
import BackEnd.*;
import BackEnd.Read.Teacher.*;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author darcy
 */
@WebServlet(name = "GroupPageServlet", urlPatterns = {"/GroupPageServlet"})
public class GroupPageServlet extends testServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("groupId", request.getParameter("groupId"));
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        request.setAttribute("groupName", (new BackEnd.Read.Group.ReadName(groupId)).read());
        request.setAttribute("debug", (new BackEnd.Read.Group.ReadCreator(groupId)).read());
        request.setAttribute("teacherName",(new BackEnd.Read.Teacher.ReadName(((int)(new BackEnd.Read.Group.ReadCreator(groupId)).read()))).read());
        String[] stu = ((String)(new BackEnd.Read.Group.ReadStudents(groupId)).read()).split(",");
        int[] students = new int[stu.length];
        for(int i=0; i<stu.length;i++){
            if(!stu[i].equals("")){
            students[i]=Integer.parseInt(stu[i]);
            }
        }
        request.setAttribute("students", students);
        int userId = getUserId(request);
        if(userId==-1){
            try{
            response.sendRedirect("LogInPage.html");
            
            }catch(IOException e){
                
            }
        }else{
            for(int i=0; i<students.length;i++){
            if(students[i]!=0){
            request.setAttribute("student"+students[i]+"name",(new BackEnd.Read.Student.ReadName(students[i])));
            }
            }
        }
        
        int type = (int)(new BackEnd.Command.checkIdentity(userId)).execute();
        request.setAttribute("userType", type);
        request.setAttribute("userId", userId);
        int[] test = (int[])(new BackEnd.Read.Group.ReadTests(groupId)).read();
        HashMap<Integer, java.util.Date> tests=new HashMap<>();
        for(int i=0;i<test.length;i++){
            tests.put(test[i], new Date());
            
            request.setAttribute("Tests"+test[i]+"name", (new BackEnd.Read.Test.ReadName(test[i])).read());
        }
        request.setAttribute("Tests",tests);
        //for(Integer i:userGroupManager.getTests(groupId).keySet()){
          //  request.setAttribute("Tests"+i+"name",testManager.getTestName(i));
        //}
        RequestDispatcher r = request.getRequestDispatcher("GroupDitaljsp.jsp");
        r.forward(request, response);
    }
    protected void processRequest1(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GroupPageServlet</title>");            
            out.println("</head>");
            out.println("<body>");
           buildPage( request,  response, out);
            out.println("</body>");
            out.println("</html>");
        }
    }
    public void buildPage(HttpServletRequest request, HttpServletResponse response,PrintWriter out){
        int id = Integer.parseInt(request.getParameter("groupId"));
        int[] students = (int[])(new BackEnd.Read.Group.ReadStudents(id)).read();
        out.println("<h1>"+(new BackEnd.Read.Group.ReadName(id)).read()+" Teacher: " + new BackEnd.Read.Teacher.ReadName(((int)(new BackEnd.Read.Group.ReadCreator(id)).read())).read() +" "+ id+"</h1>");
        Cookie c[] = request.getCookies();
        int userId=-1;
        for(int i=0;i<c.length;i++){
            if(c[i].getName().equals("userId")){
                userId=Integer.parseInt(c[i].getValue());
            }
        }
        if(userId==-1){
            try{
            response.sendRedirect("LogInPage.html");
            
            }catch(IOException e){
                
            }
        }else{
            int type = (int)(new BackEnd.Command.checkIdentity(userId)).execute();
            for(int i=0; i<students.length;i++){
             out.print("<form action=\"GroupPageServlet\" method=\"Post\">");
            out.print("<label \">"+(new BackEnd.Read.Student.ReadName(students[i])).read());
            out.print("<input type=\"hidden\" name=\"studentId\" id=\"studentId\" value="+students[i]+">");
            out.print("<input type=\"hidden\" name=\"groupId\" id=\"groupId\" value="+id+">");
            if(type==11){
            out.print("<input type=\"submit\" name=\"act\" id=\"act\" value=\"deleat\">");
            }
            out.print("</form></br>");
            }
            //out.println("<script type=\"text/javascript\">");  
            //out.print("window.location='LogInPage.html';"); 
            //out.println("alert('wrong username or password');");
            //out.println("</script>");
        }
    }
    public void deleat(HttpServletRequest request, HttpServletResponse response){
        (new BackEnd.Command.deleteMemberCommand(Integer.parseInt(request.getParameter("studentId")), Integer.parseInt(request.getParameter("groupId")))).execute();
        try{
        response.sendRedirect("GroupPageServlet?groupId="+request.getParameter("groupId"));
        }catch(IOException e){
                   
        }
    }
    public String creatTitle(int id){
        return ""+(new BackEnd.Read.Group.ReadName(id)).read()+" Teacher: " + new BackEnd.Read.Teacher.ReadName(((int)(new BackEnd.Read.Group.ReadCreator(id)).read())).read();
    }
    public void assign(HttpServletRequest request, HttpServletResponse response){
        try{
        response.sendRedirect("TestPageServlet?groupId="+request.getParameter("groupId"));
        }catch(IOException e){
                   
        }
    }
    public void finsh(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        int testId = Integer.parseInt(request.getParameter("testId"));
        int year = Integer.parseInt(request.getParameter("year"));
        int month = Integer.parseInt(request.getParameter("month"));
        int day = Integer.parseInt(request.getParameter("day"));
        int hour = Integer.parseInt(request.getParameter("hour"));
        int minuit = Integer.parseInt(request.getParameter("minuit"));
        LocalDateTime due= LocalDateTime.of(year,month,day,hour,minuit);
        Date dueDate = new Date(year,month,day,hour,minuit);
        (new BackEnd.Command.addTestToGroup(groupId, testId, due)).execute();
        //userGroupManager.addTest(groupId, testId, due);
        processRequest(request,response);
    }
    public void start(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TestPresenterServlet?testId="+request.getParameter("testId")+
                "&groupId="+request.getParameter("groupId"));
    }
    public void startMarking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("MarkingPageChoosing.jsp");
    }
    public void grade(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int testId=Integer.parseInt(request.getParameter("testId"));
        request.setAttribute("testId", testId);
        int groupId=Integer.parseInt(request.getParameter("groupId"));
        request.setAttribute("groupId", groupId);
        int[] students= (int[])(new BackEnd.Read.Group.ReadStudents(groupId)).read();
        request.setAttribute("students", students);
        for(int i=0;i<students.length;i++){
            if(students[i]!=0){
                request.setAttribute("student"+i+"name", (new BackEnd.Read.Student.ReadName(students[i])).read());
            }
        }
        RequestDispatcher r = request.getRequestDispatcher("MarkingPageChoosing.jsp");
        r.forward(request, response);
    }
    public void choose(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("TestSubmitionServlet?testId="+request.getParameter("testId")+
                "&groupId="+request.getParameter("groupId")+
                "&studentId="+request.getParameter("studentId"));
        
    }
    public void back(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
        
        int user=getUserId(request);
        int type = (int)(new BackEnd.Command.checkIdentity(user)).execute();
        if(type==11){
            response.sendRedirect("TeacherPageServlet");
        }else if(type==12){
            response.sendRedirect("StudentPageServlet");
        }
    }
    }
