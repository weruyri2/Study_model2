package net.basket.db;

import java.sql.Date;

public class basketDTO {
	
	private int b_num;
	private String b_m_id;
	private int b_g_num;
	private int b_g_amount;
	private String b_g_size;
	private String b_g_color;
	private Date b_date;
	public int getB_num() {
		return b_num;
	}
	public void setB_num(int b_num) {
		this.b_num = b_num;
	}
	public String getB_m_id() {
		return b_m_id;
	}
	public void setB_m_id(String b_m_id) {
		this.b_m_id = b_m_id;
	}
	public int getB_g_num() {
		return b_g_num;
	}
	public void setB_g_num(int b_g_num) {
		this.b_g_num = b_g_num;
	}
	public int getB_g_amount() {
		return b_g_amount;
	}
	public void setB_g_amount(int b_g_amount) {
		this.b_g_amount = b_g_amount;
	}
	public String getB_g_size() {
		return b_g_size;
	}
	public void setB_g_size(String b_g_size) {
		this.b_g_size = b_g_size;
	}
	public String getB_g_color() {
		return b_g_color;
	}
	public void setB_g_color(String b_g_color) {
		this.b_g_color = b_g_color;
	}
	public Date getB_date() {
		return b_date;
	}
	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}
	
	
	@Override
	public String toString() {
		return "basketDTO [b_num=" + b_num + ", b_m_id=" + b_m_id + ", b_g_num=" + b_g_num + ", b_g_amount="
				+ b_g_amount + ", b_g_size=" + b_g_size + ", b_g_color=" + b_g_color + ", b_date=" + b_date + "]";
	}

	


	
	
}
