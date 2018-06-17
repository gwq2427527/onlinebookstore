package biz.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import biz.entity.main.SimpleUser;

@Entity
@Table(name = "t_user_order")
public class UserOrder {
	private int id;
	private SimpleUser buyer;
	private SimpleUser seller;
	private String sid;
	private String addDate;
	private double money;
	private String status;//Shipping,Shipped,Received

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "seller_id")
	public SimpleUser getSeller() {
		return seller;
	}

	public void setSeller(SimpleUser seller) {
		this.seller = seller;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "buyer_id")
	public SimpleUser getBuyer() {
		return buyer;
	}

	public void setBuyer(SimpleUser buyer) {
		this.buyer = buyer;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
