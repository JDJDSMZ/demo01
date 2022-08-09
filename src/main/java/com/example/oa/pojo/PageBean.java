package com.example.oa.pojo;

import lombok.*;

import java.util.List;

@Setter //set
@Getter //get
@ToString // toString
@AllArgsConstructor //全参构造函数
@NoArgsConstructor // 空参构造函数
public class PageBean<T> {
	private int currentPage;//当前页码
	private int pageSize;//每页显示的条数
	private int count;//总条数
	private int pages;//总页数
	private List<T> list;//每页的数据
}
