package crm_project_22.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import crm_project_22.entity.NguoiDung;
import crm_project_22.repository.NguoiDungRepository;

/**
 * 
 * @author binhcc
 *	Tên class của Service phải thể hiện nó xử lý logic code cho Controller nào 
 *	ví dụ : LoginController thì sẽ có LoginService
 *
 * - Tên hàm trong Service phải đặt gợi nhớ tới tính năng đang xử lý
 */

public class LoginService {
	
	private NguoiDungRepository nguoiDungRepository = new NguoiDungRepository();
	
	public boolean checkLogin(HttpServletRequest request, String email, String password) {
		List<NguoiDung> listNguoiDung = nguoiDungRepository.findByEmailAndPassword(email, password);
		if(listNguoiDung.size() > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("roleName", listNguoiDung.get(0).getLoaiThanhVien().getTen());
		}
		
		return listNguoiDung.size() > 0;
	}
	
}
