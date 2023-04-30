package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.COMPANY;
import com.example.demo.domain.POST;
import com.example.demo.dto.PostDto;

//https://geonoo.tistory.com/149
//jpa 사용법+sql문 받아옴
public interface PostRepository extends JpaRepository<POST, Integer> {
	long count();
	//현재 공고가 살아있는지 확인하는 쿼리
	//엔티티의 필드명을 이용하여 SQL을 생성하기 때문에, 엔티티의 필드명에 맞춰서 JPQL 쿼리를 작성
	@Query("SELECT p FROM post p WHERE p.dead = false") 
	Page<POST> findNonDead(Pageable pageable);
	
	POST findByInfoId(Integer infoId);
	
	@Query(value = "SELECT p.info_id, p.content, p.dead, p.deadline, p.info_pos, p.info_tech, p.last_check, p.max_career, p.min_career, p.max_pay, p.min_pay, p.title, p.type, p.info_cp_name, p.info_url, p.info_loc " 
		       + "FROM post p, company c "
		       + "WHERE p.info_cp_name = c.cp_name "
		       + "AND p.dead = false "
		       + "AND (c.cp_name = :company OR :company is null) "
		       + "AND (p.type = :jobType OR :jobType is null) "
		       + "AND (c.emp_num >= :employee OR :employee is null) "
		       + "AND (p.min_pay <= :pay AND p.max_pay >= :pay OR :pay is null) "
		       + "AND (p.min_career <= :career AND p.max_career >= :career OR :career is null)", nativeQuery = true)
		List<POST> findPostsByFilter(@Param("company") String company, @Param("jobType") String jobType,
		        @Param("employee") int employee, @Param("pay") int pay, @Param("career") int career);
	

	
	//+ "AND p.info_pos IN (:jobList) AND p.info_tech IN (:stackList) "
	//@Param("jobList") List<String> jobList, @Param("stackList") List<String> stackList,
	//p.info_id, p.content, p.dead, p.deadline, p.info_pos, p.info_tech, p.last_check, p.max_career, p.min_career, p.max_pay, p.min_pay, p.title, p.type, p.info_cp_name, p.info_url, p.info_loc 
	//+ "AND (p.info_pos IN (:jobList) OR :jobList is null) "
    //+ "AND (p.info_tech IN (:stackList) OR :stackList is null) "
}