package crm_project_22.service;

import java.util.List;

import crm_project_22.entity.LoaiThanhVien;
import crm_project_22.repository.LoaiThanhVienRepository;

public class RoleService {

	private LoaiThanhVienRepository loaiThanhVienRepository = new LoaiThanhVienRepository();
	
	public boolean insert(String tenQuyen, String mota) {
		int count = loaiThanhVienRepository.insert(tenQuyen, mota);
		
		return count > 0;
	}
	
	public List<LoaiThanhVien> getAllLoaiThanhVien(){
		return loaiThanhVienRepository.getAllLoaiThanhVien();
	}
	
	public boolean deleteRole(int id) {
		int count = loaiThanhVienRepository.deleteById(id);
		
		return count > 0;
	}
}
