package biz.web.action.sys;

import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import util.Constant;
import util.FieldUtil;
import util.MessageUtil;
import util.Page;
import util.SearchParamBean;
import util.StringUtil;
import biz.entity.main.SimpleUser;
import biz.web.service.impl.BizService;

import com.opensymphony.xwork2.ModelDriven;
import common.action.struts.BaseAction;
 
@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class SimpleUserAction extends BaseAction implements ModelDriven<SimpleUser> {
	@Autowired
	private BizService service;

	private int uid;
	private SimpleUser bean = new SimpleUser();

	@Action(value = "add2SimpleUser", results = { @Result(name = "add2", 
			location = "/WEB-INF/jsp/sys/addSimpleUser.jsp") })
	public String add2() {
		return "add2";
	}

	@Action(value = "getSimpleUser", results = { @Result(name = "getOne", 
			location = "/WEB-INF/jsp/sys/modifySimpleUser.jsp") })
	public String get() {
		try {
			SimpleUser temp = (SimpleUser) service.get(SimpleUser.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteSimpleUser")
	public String delete() {
		try {
			service.deleteSimpleUser(SimpleUser.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(), "Delete Success.", "mainquery");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "Delete Failed");
			return ERROR;
		}
	}

	@Action(value = "updateSimpleUser")
	public String update() {
		try {
			service.updateSimpleUser(bean);
			MessageUtil.addMessage(getHttpServletRequest(), "Update Success.");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "Update Failed");
			return ERROR;
		}
	}

	@Action(value = "addSimpleUser")
	public String add() {
		try {
			service.addSimpleUser(bean);
			MessageUtil.addMessage(getHttpServletRequest(), "Added Success.");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "Added Failed");
			return ERROR;
		}
	}

	@Action(value = "querySimpleUser", results = { @Result(name = "queryList", 
			location = "/WEB-INF/jsp/sys/listSimpleUser.jsp") })
	public String query() {
		try {
			int pageNum = 0;
			String t = getHttpServletRequest().getParameter("pageNum");
			if (StringUtil.notEmpty(t)) {
				pageNum = Integer.valueOf(t);
			}
			Page p = (Page) getHttpSession().getAttribute(Constant.SESSION_PAGE);
			if (pageNum == 0 || p == null) {
				p = new Page();
				p.setCurrentPageNumber(1);
				p.setTotalNumber(0l);
				p.setItemsPerPage(Constant.SESSION_PAGE_NUMBER);

			 
				LinkedList<String> parmnames = new LinkedList<String>();
				 
				LinkedList<Object> parmvalues = new LinkedList<Object>();
				 
				Set parm = getHttpServletRequest().getParameterMap().entrySet();
				for (Object o : parm) {
					Entry<String, Object> e = (Entry<String, Object>) o;
					String name = e.getKey(); 
					if (name.startsWith("s_")) {
						String fieldValue = getHttpServletRequest().getParameter(name); 
						if (StringUtil.notEmpty(fieldValue)) {
							name = name.substring(2, name.length()); 
							parmnames.add(name);
							parmvalues.add(FieldUtil.format(SimpleUser.class, name, fieldValue));
						}
					}
				}

				SearchParamBean sbean = new SearchParamBean();
				sbean.setParmnames(parmnames);
				sbean.setParmvalues(parmvalues);

				p.setConditonObject(sbean);
			} else {
				p.setCurrentPageNumber(pageNum);
			}
			Page page = null;
			page = service.find(p, SimpleUser.class);

			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public SimpleUser getModel() {
		return bean;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

}
