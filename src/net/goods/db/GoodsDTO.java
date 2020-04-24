package net.goods.db;

import java.sql.Date;

public class GoodsDTO {
	
	private int num;
	private String category;
	private String name;
	private String content;
	private String size;
	private String color;
	private int amount;
	private int price;
	private String image; //상품에 관련된 모든 이미지를 한번에 저장
	private int best; // 인기상품처리
	private Date date;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCotent() {
		return content;
	}
	public void setContent(String cotent) {
		this.content = cotent;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getBest() {
		return best;
	}
	public void setBest(int best) {
		this.best = best;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "AdminGoodsDTO [num=" + num + ", category=" + category + ", name=" + name + ", content=" + content
				+ ", size=" + size + ", color=" + color + ", amount=" + amount + ", price=" + price + ", image=" + image
				+ ", best=" + best + ", date=" + date + "]";
	}
	

	
	
	
//	CREATE TABLE `model2`.`model2_goods` (
//			  `num` INT NOT NULL,
//			  `category` VARCHAR(100) NULL,
//			  `name` VARCHAR(100) NULL,
//			  `content` VARCHAR(100) NULL,
//			  `size` VARCHAR(100) NULL,
//			  `color` VARCHAR(100) NULL,
//			  `amount` INT NULL,
//			  `price` INT NULL,
//			  `image` VARCHAR(200) NULL,
//			  `best` INT NULL,
//			  `date` DATE NULL,
//			  PRIMARY KEY (`num`));

}
