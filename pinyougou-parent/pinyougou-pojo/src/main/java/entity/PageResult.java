package entity;

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable{
	//当前页展示数据
	private List rows;
	
	//总记录数
	private Long total;

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public PageResult(List rows, Long total) {
		super();
		this.rows = rows;
		this.total = total;
	}
	 
	public PageResult(Long total, List rows) {
		super();
		this.rows = rows;
		this.total = total;
	}
	
}
