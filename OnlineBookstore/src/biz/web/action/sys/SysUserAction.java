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
import biz.entity.main.SysUser;
import biz.web.service.impl.BizService;

import com.opensymphony.xwork2.ModelDriven;
import common.action.struts.BaseAction;
 
@ParentPackage("struts-default")
@Namespace("/sys")
@Component
public class SysUserAction extends BaseAction implements ModelDriven<SysUser> {
	@Autowired
	private BizService service;

	private int uid;
	private SysUser bean = new SysUser();

	@Action(value = "add2SysUser", results = { @Result(name = "add2",
			location = "/WEB-INF/jsp/sys/addSysUser.jsp") })
	public String add2() {
		return "add2";
	}
	
	@Action(value = "getSysUser", results = { @Result(name = "getOne",
			location = "/WEB-INF/jsp/sys/modifySysUser.jsp") })
	public String get() {
		try {
			SysUser temp = (SysUser) service.get(SysUser.class, uid);
			putRequestValue("modifybean", temp);
			return "getOne";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	@Action(value = "deleteSysUser")
	public String delete() {
		try {
			service.delete(SysUser.class, ids);
			MessageUtil.addRelMessage(getHttpServletRequest(),
					"Delete the information of administrator success.", "mainquery");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "Delete Failed");
			return ERROR;
		}
	}

	@Action(value = "updateSysUser")
	public String update() {
		try {
			service.updateSysUser(bean);
			MessageUtil.addMessage(getHttpServletRequest(), "Update the administrator success.");
			return SUCCESS;
		} catch (Exception e) {
			MessageUtil.addMessage(getRequest(), "Update the administrator failed.");
			return ERROR;
		}
	}

	@Action(value = "addSysUser")
	public String add() {
		try {
			service.addSysUser(bean);
			MessageUtil.addMessage(getHttpServletRequest(), "Add the administrator success.");
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			MessageUtil.addMessage(getRequest(), "Add the administrator failed");
			return ERROR;
		}
	}

	@Action(value = "querySysUser", results = { @Result(name = "queryList",
			location = "/WEB-INF/jsp/sys/listSysUser.jsp") })
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
							parmvalues.add(FieldUtil.format(SysUser.class, name, fieldValue));
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
			page = service.find(p, SysUser.class);

			getHttpSession().setAttribute(Constant.SESSION_PAGE, page);
			return "queryList";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}

	public SysUser getModel() {
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
