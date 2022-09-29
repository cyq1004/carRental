package com.yeqifu.sys.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

@Data
public class PageReq {

	/**
	 * 当前页,默认1
	 */
	private long current = 1;

	/**
	 * 每页显示条数,默认10
	 */
	private long size = 10;

	/**
	 * 是否开启分页功能
	 */
	private Boolean pageAble = true;

	public <T> Page<T> page() {
		return new Page<>(current, size);
	}

}
