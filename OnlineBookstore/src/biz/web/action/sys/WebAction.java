package biz.web.action.sys;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import util.Constant;
import util.DateUtil;
import util.MD5;
import util.MathExtend;
import util.mail.Mail;
import util.mail.SpringMail;
import biz.entity.Book;
import biz.entity.BookType;
import biz.entity.SessionBean;
import biz.entity.UserOrderBook;
import biz.entity.main.SimpleUser;
import biz.web.service.impl.BizService;
import common.action.struts.BaseAction;

@ParentPackage("struts-default")
@Namespace("/com")
@Component
public class WebAction extends BaseAction {
	@Autowired
	private BizService service;

	private int uid;
	private String name;
	private String password;
	private static final String SIMPLEUSERCART = "SIMPLEUSERCART";

	@Action(value = "toOrder", results = { @Result(name = "toCart", type = "redirect", 
			location = "/com/toCart.action") })
 
	public String toOrder() {
		SimpleUser user = getUser();
		if (user == null) {
			getHttpSession().setAttribute("orderMessage", "Please login to order");
			return "toCart";
		}

		List<Book> list = (List<Book>) getHttpSession().getAttribute(SIMPLEUSERCART);
		if (list == null || list.size() == 0) {
			getHttpSession().setAttribute("orderMessage", "No book in cart");
			return "toCart";
		}
		try {
			service.addOrder(list, user);
			getHttpSession().setAttribute("orderMessage", "Order Success");
			list.clear();

		} catch (Exception e) {
			e.printStackTrace();
			getHttpSession().setAttribute("orderMessage", "Order Faield");
		}
		return "toCart";
	}

	@Action(value = "toCart", results = { @Result(name = "checkout", location = "/qiantai/checkout.jsp") })

	public String toCart() {
		SimpleUser user = getUser();
		if (user == null) {
			putRequestValue("total", 0);
			putRequestValue("size", 0);
			return "checkout";
		}
		List<Book> list = (List<Book>) getHttpSession().getAttribute(SIMPLEUSERCART);
		double total = 0;
		int size = 0;
		if (list != null) {
			for (Book g : list) {
				total += g.getOffPrice();
			}
			size = list.size();
		}
		total = MathExtend.round(total, 2);
		putRequestValue("total", total);
		putRequestValue("size", size);
		return "checkout";
	}

	@Action(value = "del2Cart", results = { @Result(name = "toCart", type = "redirect", location = "/com/toCart.action") })
	@RequestMapping(value = "/del2Cart.do", method = RequestMethod.GET)
	public String del2Cart() {
		Book goods = (Book) service.get(Book.class, uid);
		if (goods != null) {
			List<Book> list = (List<Book>) getHttpSession().getAttribute(SIMPLEUSERCART);
			if (list != null) {
				list.remove(goods);
			}

		}
		return "toCart";
	}

	@Action(value = "add2Cart", results = { @Result(name = "checkout", location = "/qiantai/checkout.jsp"),
			@Result(name = "toCart", type = "redirect", location = "/com/toCart.action") })
	 
	public String add2Cart() {
		SimpleUser user = getUser();
		if (user == null) {
			putRequestValue("total", 0);
			return "checkout";
		}
		Book goods = (Book) service.get(Book.class, uid);
		if (goods != null) {
			List<Book> list = (List<Book>) getHttpSession().getAttribute(SIMPLEUSERCART);
			if (list == null) {
				list = new ArrayList<Book>();
				getHttpSession().setAttribute(SIMPLEUSERCART, list);
			}
			if (!list.contains(goods)) {
				list.add(goods);
			}
		}
		return "toCart";
	}

	@Action(value = "userLogout", results = { @Result(name = "index", type = "redirect", 
			location = "/com/index.action") })
	public String userLogout() {
		getHttpSession().removeAttribute("SimpleUser");
		getHttpSession().removeAttribute(SIMPLEUSERCART);
		getHttpSession().invalidate();
		return "index";
	}

	@Action(value = "toUserManage", results = { @Result(name = "main", location = "/WEB-INF/jsp/main.jsp") })
	public String toUserManage() {
		SessionBean sb = new SessionBean();
		sb.setUser(getUser());

		getHttpSession().setAttribute(Constant.SESSION_BEAN, sb);
		return "main";
	}

	@Action(value = "index", results = { @Result(name = "index", location = "/qiantai/index.jsp") })
	public String index() {
		List<Book> list = service.findBook(8);
		putRequestValue("list", list);
		return "index";
	}

	@Action(value = "getBook", results = { @Result(name = "index", location = "/qiantai/single.jsp") })
	public String getBook() {

		Book item = (Book) service.get(Book.class, uid);
		putRequestValue("item", item);

		List list1 = service.findAll(BookType.class);
		putRequestValue("list1", list1);

		List<UserOrderBook> list = service.queryByHQL("from UserOrderBook where book.id=? order by id desc",
				item.getId());
		int today = 0;
		int week = 0;
		int month = 0;
		int total = list.size();

		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
		String currentday = DateUtil.getCurrentTime();
		Date _weekday = DateUtil.addDays(new Date(), -7);
		String weekday = timeformat.format(_weekday);
		Date _monthday = DateUtil.addDays(new Date(), -30);
		String monthday = timeformat.format(_monthday);

		log.info(currentday);
		log.info(weekday);
		log.info(monthday);

		double totalscore = 0d;
		if (list.size() > 0) {
			int size = 0;
			for (UserOrderBook b : list) {
				String orderDate = b.getUserOrder().getAddDate();
				if (orderDate.equals(currentday)) {
					today++;
				}
				if (orderDate.compareTo(weekday) > 0 && orderDate.compareTo(currentday) <= 0) {
					week++;
				}
				if (orderDate.compareTo(monthday) > 0 && orderDate.compareTo(currentday) <= 0) {
					month++;
				}
				if (b.getScore() > 0) {
					totalscore += b.getScore();
					size++;
				}
			}
			if (size > 0) {
				totalscore = MathExtend.round(totalscore / size, 1);
			}
		}
		if (totalscore == 0) {
			totalscore = 5;
		}
		putRequestValue("goodsScore", totalscore);
		putRequestValue("commentList", list);
		putRequestValue("today", today);
		putRequestValue("week", week);
		putRequestValue("month", month);
		putRequestValue("total", total);

		return "index";
	}

	@Action(value = "list", results = { @Result(name = "index", location = "/qiantai/product.jsp") })
	public String list() {

		if (uid != 0) {
			List<?> list = service.queryByHQL("from Book where bookType.id=?", uid);
			putRequestValue("list", list);
		} else {
			List<?> list = service.queryByHQL("from Book ");
			putRequestValue("list", list);

		}

		List list1 = service.findAll(BookType.class);
		putRequestValue("list1", list1);

		return "index";
	}

	@Action(value = "userLogin", results = { @Result(name = "index", type = "redirect", 
			location = "/qiantai/index.jsp"),
			@Result(name = "login", type = "redirect", location = "/qiantai/login.jsp") })
	public String userLogin() {

		String msg = "Username or password is incorrect";
		try {
			MD5 md = new MD5();
			password = md.getMD5ofStr(password);
			SimpleUser user = (SimpleUser) service.findUser(SimpleUser.class.getSimpleName(), name, password);
			if (user != null) {
				if (user.isChecked() == false) {
					msg = "Account is not activated";
				} else {
					getHttpSession().setAttribute("SimpleUser", user);
					msg = "Success";
				}
			}
			return "index";
		} catch (Exception e) {
			e.printStackTrace();
		}
		getHttpSession().setAttribute("loginErrorMessage", msg);
		return "login";
	}

	@Action(value = "activeAccount", results = { @Result(name = "index", type = "redirect", 
			location = "/qiantai/index.jsp"),
			@Result(name = "login", type = "redirect", location = "/qiantai/login.jsp") })
	public String activeAccount() {

		String msg = "Account activation is successful";
		try {
			service.updateCheck(name);
		} catch (Exception e) {
			msg = "Account activation is failed";
			e.printStackTrace();
		}
		getHttpSession().setAttribute("loginErrorMessage", msg);
		return "login";
	}

	private SimpleUser bean;

	@Action(value = "regUser", results = { @Result(name = "account", type = "redirect", 
			location = "/qiantai/account.jsp"),
			@Result(name = "login", type = "redirect", location = "/qiantai/login.jsp") })
	public String regUser() throws Exception {
		String msg = "";
		try {
			String temp = bean.getType() + bean.getUser().getUname() + DateUtil.getCurrentTime();
			bean.setCheckmd5(new MD5().getMD5ofStr(temp));
			service.addSimpleUser(bean);

			try {
				SpringMail smail = new SpringMail();

				Mail mail = new Mail();
				mail.setContext("Click the link below to activate your account.<br/> <a target='_blank' href='http://localhost:8080/OnlineBookstore/com/activeAccount.action?name="
						+ bean.getCheckmd5() + "' >Activate Account</a>");
				mail.setTitle("Activate your account");
				mail.setTo(bean.getUser().getUserEmail());

				smail.sendMimeMail(null, mail);
			} catch (Exception e) {
				e.printStackTrace();
			}

			msg = "Registration success! Please activate your account";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "registration failed";
		}
		getHttpSession().setAttribute("regErrorMessage", msg);
		return "account";
	}

	public SimpleUser getBean() {
		return bean;
	}

	public void setBean(SimpleUser bean) {
		this.bean = bean;
	}

	public BizService getService() {
		return service;
	}

	public void setService(BizService service) {
		this.service = service;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private SimpleUser getUser() {
		SimpleUser su = (SimpleUser) getHttpSession().getAttribute("SimpleUser");
		return su;
	}

}
