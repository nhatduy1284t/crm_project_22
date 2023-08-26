package crm_project_22.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_22.config.MysqlConfig;
import crm_project_22.entity.LoaiThanhVien;
import crm_project_22.entity.NguoiDung;

/**
 * 
 * @author binhcc
 *	Tên class của repository sẽ tạo theo tên bảng, để quản lý tất cả các câu query liên quan tới bảng đó
 *	findBy : giành cho câu Select có điều kiện WHERE
 */

public class NguoiDungRepository {
	
	public List<NguoiDung> findByEmailAndPassword(String email, String matkhau){
		String query = "SELECT *\n"
		+ "FROM NguoiDung nd\n"
		+ "JOIN LoaiThanhVien ltv ON ltv.id = nd.id_loaithanhvien\n"
		+ "WHERE nd.email = ? AND nd.matkhau = ?"; //? : là giá trị của tham số sẽ được truyền vào sau
		
		Connection connection = MysqlConfig.getConnection();
		List<NguoiDung> listNguoiDung = new ArrayList<NguoiDung>();
		
		try {
//			Truyền câu query vào Connection
			PreparedStatement statement = connection.prepareStatement(query);
//			Truyền giá trị tham số vào câu query nếu có
			statement.setString(1, email); //Lưu ý : setString,setInt... phải tùy vào kiểu dữ liệu của cột đang so sánh của tham số
			statement.setString(2, matkhau);
			
//			Thực thi câu query
			/**
			 * executeQuery : Khi câu truy vấn là câu lấy dữ liệu SELECT
			 * executeUpdate : Không phải là câu lấy dữ liệu. INSERT, DELETE, UPDATE...
			 */
			ResultSet resultSet = statement.executeQuery();
//			Duyệt dữ liệu từ resultSet
			while(resultSet.next()) {
				NguoiDung nguoiDung = new NguoiDung();
				//int id = resultSet.getInt("id"); //Lấy giá trị của cột id trong CSDL khi duyệt qua từng dòng
				
				nguoiDung.setId(resultSet.getInt("id"));
				nguoiDung.setFullname(resultSet.getString("fullname"));
				nguoiDung.setEmail(resultSet.getString("email"));
				
				LoaiThanhVien loaiThanhVien = new LoaiThanhVien();
				loaiThanhVien.setTen(resultSet.getString("ten"));
				
				nguoiDung.setLoaiThanhVien(loaiThanhVien);
				
				listNguoiDung.add(nguoiDung);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Lỗi thực thi câu query " + e.getLocalizedMessage());
		} finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Lỗi đóng kết nối " + e.getLocalizedMessage());
				}
			}
		}
		
		return listNguoiDung;
	}
	
}
