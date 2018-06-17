package biz.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.ServiceMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import util.MD5;
import util.MathExtend;
import util.StringUtil;
import biz.entity.main.SimpleUser;
import biz.entity.main.User;
import biz.web.service.impl.BizService;

import common.action.struts.BaseAction;

@Controller
@RequestMapping("/com")
public class WebAction extends BaseAction {
	@Autowired
	private BizService service;
	private static final String SIMPLEUSERCART = "SIMPLEUSERCART";

	@RequestMapping(value = "/toOrder.do", method = RequestMethod.GET)
	public String toOrder() {
		SimpleUser user = getUser();
		if (user == null) {
			session.setAttribute("orderMessage", "你没有登录,不能提交订单");
			return "redirect:/com/toCart.do";
		}

		List<Goods> list = (List<Goods>) session.getAttribute(SIMPLEUSERCART);
		if (list == null || list.size() == 0) {
			session.setAttribute("orderMessage", "你没有选购产品,不能提交订单");
			return "redirect:/com/toCart.do";
		}
		try {
			service.addOrder(list, user);
			session.setAttribute("orderMessage", "提交成功,你可以在个人管理中管理订单");
			list.clear();
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("orderMessage", "提交失败");
		}
		return "redirect:/com/toCart.do";
	}

	@RequestMapping(value = "/toCart.do", method = RequestMethod.GET)
	public String toCart() {
		SimpleUser user = getUser();
		if (user == null) {
			request.setAttribute("total", 0);
			request.setAttribute("size", 0);
			return "forward:/qiantai/checkout.jsp";
		}
		List<Goods> list = (List<Goods>) session.getAttribute(SIMPLEUSERCART);
		double total = 0;
		int size = 0;
		if (list != null) {
			for (Goods g : list) {
				total += g.getPrice();
			}
			size = list.size();
		}
		total = MathExtend.round(total, 2);
		request.setAttribute("total", total);
		request.setAttribute("size", size);
		return "forward:/qiantai/checkout.jsp";
	}

	private SimpleUser getUser() {
		SimpleUser su = (SimpleUser) session.getAttribute("SimpleUser");
		return su;
	}

	@RequestMapping(value = "/del2Cart.do", method = RequestMethod.GET)
	public String del2Cart(int uid) {
		//		SimpleUser user = getUser();
		//		if(user==null){
		//			request.setAttribute("total",0 );
		//			return "forward:/qiantai/checkout.jsp";
		//		}
		Goods goods = (Goods) service.get(Goods.class, uid);
		if (goods != null) {
			List<Goods> list = (List<Goods>) session.getAttribute(SIMPLEUSERCART);
			if (list != null) {
				list.remove(goods);
			}

		}
		return "redirect:/com/toCart.do";
	}

	@RequestMapping(value = "/add2Cart.do", method = RequestMethod.GET)
	public String add2Cart(int uid) {
		SimpleUser user = getUser();
		if (user == null) {
			request.setAttribute("total", 0);
			return "forward:/qiantai/checkout.jsp";
		}
		Goods goods = (Goods) service.get(Goods.class, uid);
		if (goods != null) {
			List<Goods> list = (List<Goods>) session.getAttribute(SIMPLEUSERCART);
			if (list == null) {
				list = new ArrayList<Goods>();
				session.setAttribute(SIMPLEUSERCART, list);
			}
			if (!list.contains(goods)) {
				list.add(goods);
			}
		}
		return "redirect:/com/toCart.do";
	}

	@RequestMapping(value = "/user.do", method = RequestMethod.GET)
	public String touser(String type) {
		SimpleUser su = (SimpleUser) session.getAttribute("SimpleUser");
		if (su == null) {
			return "redirect:/com/index.do";
		}
		if (StringUtil.isEmpty(type)) {
			type = "info";
		}
		if ("note".equals(type)) {
		} else if ("score".equals(type)) {
		} else if ("fav".equals(type)) {

		}
		request.setAttribute("type", type);
		return "forward:/qiantai/user.jsp";
	}

	@RequestMapping(value = "/userLogin.do", method = RequestMethod.POST)
	public String userLogin(String name, String password) {

		String msg = "Wrong account or wrong password";
		try {
			MD5 md = new MD5();
			password = md.getMD5ofStr(password);
			SimpleUser user = (SimpleUser) service.findUser(SimpleUser.class.getSimpleName(), name, password);
			if (user != null) {
				session.setAttribute("SimpleUser", user);
				msg = "成功";
			}
			return "redirect:/qiantai/index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("loginErrorMessage", msg);
		return "redirect:/qiantai/login.jsp";
	}

	@RequestMapping(value = "/regUser.do", method = RequestMethod.POST)
	public String regUser(SimpleUser bean) throws Exception {
		String msg = "";
		try {
			bean.setType("user");
			service.addSimpleUser(bean);
			msg = "Registration Success";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Regiatration Failed";
		}
		session.setAttribute("regErrorMessage", msg);
		return "redirect:/qiantai/account.jsp";
	}

	@RequestMapping(value = "/userPwd.do", method = RequestMethod.POST)
	@ResponseBody
	public String userPwd(String oldpwd, String newpwd) {
		String msg = "";
		try {
			SimpleUser su = (SimpleUser) session.getAttribute("SimpleUser");
			User sessionUser = su.getUser();
			MD5 md = new MD5();
			String password = md.getMD5ofStr(oldpwd);

			SimpleUser temp = (SimpleUser) service.get(SimpleUser.class, su.getId());
			if (!password.equals(temp.getUser().getUserPassword())) {
				msg = "Wrong old password";
			} else {
				sessionUser.setUserPassword(newpwd);
				service.updateSimpleUser(su);
				msg = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Modify password failed";
		}
		return "{\"msg\":\"" + msg + "\"}";
	}

	@RequestMapping(value = "/userUpdate.do", method = RequestMethod.POST)
	public String userUpdate(User bean) {
		try {
			SimpleUser su = (SimpleUser) session.getAttribute("SimpleUser");
			User sessionUser = su.getUser();

			bean.setUserId(sessionUser.getUserId());
			bean.setUname(sessionUser.getUname());
			if (StringUtil.notEmpty(bean.getUserPassword())) {
				MD5 md = new MD5();
				String password = md.getMD5ofStr(bean.getUserPassword());
				bean.setUserPassword(password);
			} else {
				bean.setUserPassword(sessionUser.getUserPassword());
			}
			service.update(bean);

			sessionUser.setUserAddress(bean.getUserAddress());
			sessionUser.setUserBirth(bean.getUserBirth());
			sessionUser.setUserEmail(bean.getUserEmail());
			sessionUser.setUserGender(bean.getUserGender());
			sessionUser.setUserName(bean.getUserName());
			sessionUser.setUserPhone(bean.getUserPhone());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/com/user.do?type=info";
	}

	@RequestMapping(value = "/userLogout.do", method = RequestMethod.GET)
	public String userLogout() {
		session.removeAttribute("SimpleUser");
		session.removeAttribute(SIMPLEUSERCART);
		return "forward:/com/index.do";
	}

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index() {
		List<Goods> list = service.findGoods(8);
		request.setAttribute("list", list);
		return "forward:/qiantai/index.jsp";
	}

	@RequestMapping(value = "/toUserManage.do")
	public String toUserManage() {
		return "mainUser";
	}

	@RequestMapping(value = "/queryMovieTop.do", method = RequestMethod.GET)
	public String queryMovieTop() {
		SimpleUser su = (SimpleUser) session.getAttribute("SimpleUser");
		if (su == null) {
			return "forward:/com/queryMovie.do";
		} else {

			return "forward:/qiantai/list.jsp";
		}
	}

	@RequestMapping(value = "/list.do", method = RequestMethod.GET)
	public String list(Integer sceneid, Integer modeid) {

		List<?> list1 = service.findAll(Scene.class);
		request.setAttribute("list1", list1);

		List<?> list2 = service.findAll(ServiceMode.class);
		request.setAttribute("list2", list2);

		List<Goods> list = null;

		if (sceneid != null) {
			list = service.queryByHQL("from Goods where scene.id=?", sceneid);
		} else if (modeid != null) {
			list = service.queryByHQL("from Goods where mode.id=?", modeid);
		} else {
			list = service.findAll(Goods.class);
		}
		request.setAttribute("list", list);

		return "forward:/qiantai/product.jsp";
	}

	@RequestMapping(value = "/getGoods.do", method = RequestMethod.GET)
	public String getGoods(Integer uid) {

		List<?> list1 = service.findAll(Scene.class);
		request.setAttribute("list1", list1);

		List<?> list2 = service.findAll(ServiceMode.class);
		request.setAttribute("list2", list2);

		Goods goods = (Goods) service.get(Goods.class, uid);
		request.setAttribute("item", goods);

		//TODO 获取推荐
		SimpleUser user = getUser();
		List<Goods> suglist = null;
		if (user == null) {
			suglist=service.findSuggestList(goods);
		}else{
			suglist=service.findSuggestList(goods,user);
		}

		request.setAttribute("sugList", suglist);
		
		
		double score = service.getGoodsScore(uid);
		request.setAttribute("goodsScore", score);

		return "forward:/qiantai/single.jsp";
	}

	
}
