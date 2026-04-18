package kr.co.javaex.sec23.service;

import kr.co.javaex.sec23.repository.CategoryRepository;
import java.util.List;
import java.util.Map;

public class CategoryService {

    private CategoryRepository repo = new CategoryRepository();

    // 카테고리 목록 조회 (빠진 메서드 추가!)
    public List<Map<String, Object>> showCategory() {
        return repo.findAll();
    }

    // 카테고리 추가
    public boolean addCategory(int newId, String name, Integer parentId) {
        List<Map<String, Object>> list = repo.findAll();
        for (Map<String, Object> c : list) {
            if ((int) c.get("categoryId") == newId) {
                System.out.println("이미 존재하는 카테고리ID입니다!");
                return false;
            }
        }
        boolean result = repo.insert(newId, name, parentId);
        if (result) {
            System.out.println("카테고리가 등록되었습니다.");
        }
        return result;
    }

    // 카테고리 수정
    public void updateCategory(int id, String newName) {
        boolean result = repo.update(id, newName);
        if (result) {
            System.out.println("카테고리가 수정되었습니다.");
        } else {
            System.out.println("카테고리를 찾을 수 없습니다.");
        }
    }

    // 카테고리 삭제
    public void deleteCategory(int id) {
        boolean result = repo.delete(id);
        if (result) {
            System.out.println("카테고리가 삭제되었습니다.");
        } else {
            System.out.println("카테고리를 찾을 수 없습니다.");
        }
    }
}