package crm_project_22.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project_22.config.MysqlConfig;
import crm_project_22.entity.NguoiDung;
import crm_project_22.service.LoginService;

/**
 * 
 * @author binhcc
 *	Ý nghĩa từng package :
 *		- controller : là package lưu trữ những file dùng để khai báo đường dẫn và xử lý logic cho
 *đường dẫn.
 */

/**
 * 
 * @author binhcc
 *	
 *	Bước 1 : Xác định chức năng sẽ làm ở giao diện là gì
 *	Bước 2 : Xác định câu query giành cho chức năng đó
 *	Bước 3 : Kiểm tra query xem có hoạt động không ( Lưu ý kiểm tra ở tool DBeaver)
 *	Bước 4 : Nhận tham số bằng số lượng tham số mà câu query cần
 *	Bước 5 : Chuẩn bị câu query ở servlet
 *	Bước 6 : Mở kết nối tới CSDL
 *	Bước 7 : Truyền câu query chuẩn bị ở bước 5 vào Connection mới được mở ( và truyền tham số nếu có )
 *	Bước 8 : Thực thi câu query
 *	Bước 9 : Lấy kết quả từ câu query đã thực thi
 *	Bước 10 : Xử lý logic code tương ứng với nghiệp vụ
 *
 */

/**
 * 
 * @author binhcc
 *	SQL Injection, Insert Batch, Stream, Non Blocking...
 *
 */

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
	
	private LoginService loginService = new LoginService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String matkhau = req.getParameter("password");
	
		boolean isSuccess = loginService.checkLogin(req,email, matkhau);
		
		if(isSuccess) {
			System.out.println("Đăng nhập thành công");
		}else {
			System.out.println("Đăng nhập thất bại");
		}
		
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		
////		Nhận tham số
//		String email = req.getParameter("email");
//		String matkhau = req.getParameter("password");
//		
////		Chuẩn bị câu query
////		String query = "SELECT *\n"
////				+ "FROM NguoiDung nd\n"
////				+ "WHERE nd.email = '" + email + "' AND nd.matkhau = '"+ matkhau +"'";
//		
//		String query = "SELECT *\n"
//				+ "FROM NguoiDung nd\n"
//				+ "WHERE nd.email = ? AND nd.matkhau = ?"; //? : là giá trị của tham số sẽ được truyền vào sau
//		
////		Mở kết nối tới CSDL
//		Connection connection = MysqlConfig.getConnection();
//		List<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
//		
//		try {
////			Truyền câu query vào Connection
//			PreparedStatement statement = connection.prepareStatement(query);
////			Truyền giá trị tham số vào câu query nếu có
//			statement.setString(1, email); //Lưu ý : setString,setInt... phải tùy vào kiểu dữ liệu của cột đang so sánh của tham số
//			statement.setString(2, matkhau);
//			
////			Thực thi câu query
//			/**
//			 * executeQuery : Khi câu truy vấn là câu lấy dữ liệu SELECT
//			 * executeUpdate : Không phải là câu lấy dữ liệu. INSERT, DELETE, UPDATE...
//			 */
//			ResultSet resultSet = statement.executeQuery();
////			Duyệt dữ liệu từ resultSet
//			while(resultSet.next()) {
//				NguoiDung nguoiDung = new NguoiDung();
//				//int id = resultSet.getInt("id"); //Lấy giá trị của cột id trong CSDL khi duyệt qua từng dòng
//				
//				nguoiDung.setId(resultSet.getInt("id"));
//				nguoiDung.setFullname(resultSet.getString("fullname"));
//				nguoiDung.setEmail(resultSet.getString("email"));
//				
//				listNguoiDung.add(nguoiDung);
//			}
//			
//			if(listNguoiDung.size() > 0) {
//				System.out.println("Đăng nhập thành công");
//			}else {
//				System.out.println("Đăng nhập thất bại");
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("Lỗi thực thi câu query " + e.getLocalizedMessage());
//		} finally {
//			if(connection != null) {
//				try {
//					connection.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
//				}
//			}
//		}
//		
//		req.getRequestDispatcher("login.jsp").forward(req, resp);
	}

}
