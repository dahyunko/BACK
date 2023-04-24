package com.example.demo.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // jpa 필수
@AllArgsConstructor
@ToString
@Entity(name = "post")
public class POST {
	//https://wikidocs.net/161165
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer infoId;
	
	@Column(nullable = false, length = 45)
	private String cpName;
	
	@Column(nullable = false, length = 45)
	private String title;
	
	@Column
	private LocalDate deadline;
	private int type;
	@Column(length = 45)
	private String cpLoc;
	@Column(columnDefinition = "LONGTEXT")
	private String infoPos;
	@Column(columnDefinition = "LONGTEXT")
	private String infoTech;
	@Column(columnDefinition = "int default 0")
	private int minCareer;
	@Column(columnDefinition = "int default 20")
	private int maxCareer;
	@Column(columnDefinition = "int default -1")
	private int minPay;
	@Column(columnDefinition = "int default -1")
	private int maxPay;
	@Column(columnDefinition = "LONGTEXT")
	private String postUrl;
	@Column(columnDefinition = "LONGTEXT")
	private String content;
	 @Column(columnDefinition = "int default 0")
	private int dead;
	private LocalDate lastCheck;
}
