package biz.web.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import util.DateUtil;
import util.MD5;
import util.Page;
import util.StringUtil;
import util.mail.Mail;
import util.mail.SpringMail;
import biz.entity.Book;
import biz.entity.UserOrder;
import biz.entity.UserOrderBook;
import biz.entity.main.SimpleUser;
import biz.entity.main.SysUser;
import biz.entity.main.User;
import biz.web.dao.ISysDao;
import common.service.BaseService;

@Service("bizService")
@Repository
public class BizService extends BaseService {

	@Autowired
	private ISysDao dao;

	public List queryByHQL(String hql, Object... values) {
		return dao.queryByHQL(hql, values);
	}

	public void deleteSimpleUser(Class<SimpleUser> class1, String ids) {
		List<User> list = dao.queryByHQL("from User where uname in(select user.uname from SimpleUser where id in (" + ids + "))");
		for (User user : list) {
			dao.delete(user);
		}
	}

	public void addSimpleUser(SimpleUser obj) {
		User user = obj.getUser();
		MD5 md = new MD5();
		user.setUserPassword(md.getMD5ofStr(user.getUserPassword()));
		dao.save(user);
		dao.save(obj);
	}

	public void updateSimpleUser(SimpleUser obj) {
		SimpleUser temp = (SimpleUser) dao.get(SimpleUser.class, obj.getId());
		User user = temp.getUser();
		user.setUserAddress(obj.getUser().getUserAddress());
		user.setUserBirth(obj.getUser().getUserBirth());
		user.setUserEmail(obj.getUser().getUserEmail());
		user.setUserGender(obj.getUser().getUserGender());
		user.setUserName(obj.getUser().getUserName());
		user.setUserPhone(obj.getUser().getUserPhone());
		if (StringUtils.isNotBlank(obj.getUser().getUserPassword())) {
			MD5 md = new MD5();
			user.setUserPassword(md.getMD5ofStr(obj.getUser().getUserPassword()));
		}
		dao.merge(user);
		obj.setUser(user);
		dao.merge(obj);

	}

	//add object 


	public void add(Object obj) {
		dao.save(obj);
	}

	//update object 
	public void update(Object obj) {
		dao.merge(obj);
	}
    //get object by id
	 
	@SuppressWarnings("rawtypes")
	public Object get(Class clazz, Serializable id) {
		return dao.get(clazz, id);
	}

	public void deleteUser(String ids) {
		dao.deleteByIds(User.class, ids);
	}

	public void delete(Class clazz, String ids) {
		dao.deleteByIds(clazz, ids);
	}

	public Object findUser(String type, String userid, String pwd) {
		return dao.queryUser(type, userid, pwd);
	}

	public User findUser(String userid) {
		return dao.queryUser(userid);
	}

	public Page findUser(Page page) {
		return dao.list(page, "User");
	}

	public Page find(Page page, Class clazz) {
		return dao.list(page, clazz.getSimpleName());
	}

	public List findAll(Class clazz) {
		return dao.queryByHQL("from " + clazz.getSimpleName());
	}

	public void addSysUser(SysUser obj) {
		User user = obj.getUser();
		MD5 md = new MD5();
		user.setUserPassword(md.getMD5ofStr(user.getUserPassword()));
		dao.save(user);
		dao.save(obj);
	}

	public void updateSysUser(SysUser obj) {
		SysUser temp = (SysUser) dao.get(SysUser.class, obj.getId());
		User user = temp.getUser();
		user.setUserAddress(obj.getUser().getUserAddress());
		user.setUserBirth(obj.getUser().getUserBirth());
		user.setUserEmail(obj.getUser().getUserEmail());
		user.setUserGender(obj.getUser().getUserGender());
		user.setUserName(obj.getUser().getUserName());
		user.setUserPhone(obj.getUser().getUserPhone());
		if (StringUtils.isNotBlank(obj.getUser().getUserPassword())) {
			MD5 md = new MD5();
			user.setUserPassword(md.getMD5ofStr(obj.getUser().getUserPassword()));
		}
		dao.merge(user);
		obj.setUser(user);
		dao.merge(obj);

	}

	public List findAll(Class clazz, Map<String, Object> params) {
		return dao.findAll(clazz, params);
	}

	public <T> T unique(final String hql, final Object... paramlist) {
		return dao.unique(hql, paramlist);
	}

	public List<Book> findBook(int i) {
		return dao.queryByHQLLimit("from Book order by id desc", 0, i);
	}

	public void updateCheck(String name) {
		dao.updateByHQL("update SimpleUser set checked=true where checkmd5=?", name);
	}

	public void addOrder(List<Book> list, SimpleUser user) {

		Map<SimpleUser, List<Book>> map = new HashMap<SimpleUser, List<Book>>();
		for (Book b : list) {
			Book temp = (Book) dao.get(Book.class, b.getId());
			List<Book> templist = map.get(temp.getSeller());
			if (templist == null) {
				templist = new ArrayList<Book>();
				map.put(temp.getSeller(), templist);
			}
			templist.add(temp);
		}
		double total = 0;
		for (SimpleUser seller : map.keySet()) {
			UserOrder order = new UserOrder();
			order.setSeller(seller);
			order.setAddDate(DateUtil.getCurrentTime());
			order.setBuyer(user);
			order.setStatus("Shipping");
			int id = (Integer) dao.save(order);
			String sid = "OB" + StringUtil.format0String(5, id);
			order.setSid(sid);
			dao.update(order);

			double money = 0;
			for (Book temp : map.get(seller)) {
				money += temp.getOffPrice();
				UserOrderBook ob = new UserOrderBook();
				ob.setBook(temp);
				ob.setUserOrder(order);
				ob.setPrice(temp.getOffPrice());
				dao.save(ob);
			}
			total += money;
			order.setMoney(money);
			dao.update(order);
		}
		
		try {
			SpringMail smail = new SpringMail();
			String conString = "Your order has been paid<br/> Total: "+total+"<br/>";
			conString+="<table>"+
			"<tr>"+
			"<td width=350px>Book Name</td>"+
			"<td width=150px> Book ISBN</td>"+
			"<td width=150px>Book Price</td>"+
			"</tr>";
			for (Book b : list) {
				conString+=
						"<tr>"+
						"<td>"+b.getName()+"</td>"+
						"<td>"+b.getSid()+"</td>"+
						"<td> "+b.getOffPrice()+"</td>"+
						"</tr>";
			}
			
			conString+="</table>";
			Mail mail = new Mail();
			mail.setContext(conString);
			mail.setTitle("Your order has been paied");
			mail.setTo(user.getUser().getUserEmail());

			smail.sendMimeMail(null, mail);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateUserOrderBook(UserOrderBook order) {
		UserOrder temp = (UserOrder) dao.get(UserOrder.class, order.getUserOrder().getId());
		temp.setStatus("Received");
		dao.update(temp); 
		dao.update(order);
		
	}

}
