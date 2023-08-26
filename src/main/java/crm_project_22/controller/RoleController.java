package crm_project_22.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project_22.service.RoleService;

@WebServlet(name = "roleController", urlPatterns = {"/role","/role-add"})
public class RoleController extends HttpServlet {

	private RoleService roleService = new RoleService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/role":
				req.setAttribute("listRole", roleService.getAllLoaiThanhVien());
				req.getRequestDispatcher("role-table.jsp").forward(req, resp);
				break;
				
			case "/role-add":
				
				req.getRequestDispatcher("role-add.jsp").forward(req, resp);
				break;
				
			default:
				
				break;
		}
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		
		switch (path) {
			case "/role-add":
				String tenQuyen = req.getParameter("tenQuyen");
				String mota = req.getParameter("moTa");
				boolean isSuccess = roleService.insert(tenQuyen, mota);
				
				req.setAttribute("isSuccess", isSuccess);
				req.getRequestDispatcher("role-add.jsp").forward(req, resp);
				break;
				
			default:
				
				break;
		}
		
			
	}
}
