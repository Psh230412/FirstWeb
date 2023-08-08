package login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import join.JoinDao;

@WebServlet("/login")
public class LoginServelt extends HttpServlet {
   LoginDao dao = new LoginDao();
   JoinDao joinDao = new JoinDao();

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      req.getRequestDispatcher("/WEB-INF/loginPage/login.jsp").forward(req, resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String formType = req.getParameter("form_type");

      if (formType.equals("loginForm")) {
         String id = req.getParameter("id");
         String password = req.getParameter("password");
         boolean remember = false;

         if (req.getParameter("remember") != null) {
            remember = req.getParameter("remember").equals("check");
         }
         if (dao.checkId(id)) {
            if (dao.checkPassword(id, password)) {
               if (remember) {
                  Cookie cookie = new Cookie("remember", id);
                  resp.addCookie(cookie);
               }
               HttpSession session = req.getSession();
               session.setAttribute("loggedUserId", id);
            
               // 다음페이지로 이동
               resp.sendRedirect("main/index.html");
            } else {
               req.setAttribute("loginError", "아이디 또는 비밀번호를 틀리셨습니다.");
               req.getRequestDispatcher("/WEB-INF/loginPage/login.jsp").forward(req, resp);
            }
         } else {
            req.setAttribute("loginError", "아이디 또는 비밀번호를 틀리셨습니다.");
            req.getRequestDispatcher("/WEB-INF/loginPage/login.jsp").forward(req, resp);
         }
      } else if (formType.equals("joinForm")) {
         String joinNickname = req.getParameter("joinNickname");
         String joinId = req.getParameter("joinId");
         String joinPassword = req.getParameter("joinPassword");
         String joinPasswordRe = req.getParameter("joinPasswordRe");

         boolean isJoin = true;

         if (!joinDao.duplicateNickname(joinNickname)) {
            req.removeAttribute("joinNicknameError");
         } else {
            req.setAttribute("inputNickname", joinNickname);
            req.setAttribute("joinNicknameError", "같은 닉네임이 존재합니다.");
            isJoin = false;
         }

         if (!joinDao.duplicateId(joinId)) {
            req.removeAttribute("joinIdError");
         } else {
            req.setAttribute("inputId", joinId);
            req.setAttribute("joinIdError", "같은 이름의 아이디가 존재합니다.");
            isJoin = false;
         }

         if (joinPassword.equals(joinPasswordRe)) {
            req.removeAttribute("joinPasswordError");
         } else {
            req.setAttribute("joinPassword", joinPassword);
            req.setAttribute("joinPasswordRe", joinPasswordRe);
            req.setAttribute("joinPasswordError", "입력한 비밀번호가 서로 다릅니다.");
            isJoin = false;
         }

         if (isJoin) {
            dao.insertId(joinNickname, joinId, joinPassword);
         }
         req.getRequestDispatcher("/WEB-INF/loginPage/login.jsp").forward(req, resp);
      }
   }
}