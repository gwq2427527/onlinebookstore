package common.dao.hibernate;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import util.NumberUtil;
import util.Page;
import util.SearchParamBean;

public abstract class BaseHibernateDao extends HibernateDaoSupport implements BaseDao {

	protected static Logger log = LoggerFactory.getLogger(BaseHibernateDao.class);

	@Resource
	private HibernateTemplate hibernateTemplate;

	@SuppressWarnings("rawtypes")
	public void deleteByIds(Class clazz, String ids) {
		if (ids != null) {

			String[] temp = ids.split(",");
			if (temp == null || temp.length <= 0) {
				throw new RuntimeException("ids error");
			}
			for (String str : temp) {
				if (!NumberUtil.isNumeric(str)) {
					throw new RuntimeException("ids error");
				}
			}
			String hql = "delete " + clazz.getSimpleName() + " where id in(" + ids + ")";
			updateByHQL(hql);
		}
	}

	protected HibernateTemplate getHT() {
		return this.hibernateTemplate;
	}

	public Serializable save(Object model) {
		return hibernateTemplate.save(model);
	}

	public void saveOrUpdate(Object model) {
		hibernateTemplate.saveOrUpdate(model);
	}

	public void update(Object model) {
		hibernateTemplate.update(model);

	}

	public void merge(Object model) {
		hibernateTemplate.merge(model);
	}

	public void delete(Object model) {
		hibernateTemplate.delete(model);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void delete(Class clazz, Serializable id) {
		Object temp = hibernateTemplate.load(clazz, id);
		hibernateTemplate.delete(temp);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object get(Class clazz, Serializable id) {
		return hibernateTemplate.get(clazz, id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object load(Class clazz, Serializable id) {
		return hibernateTemplate.load(clazz, id);
	}

	@SuppressWarnings("rawtypes")
	public List queryByHQL(String hql, Object... values) {
		return getHibernateTemplate().find(hql, values);
	}

	@SuppressWarnings("rawtypes")
	public List queryByHQL(String hql) {
		return getHibernateTemplate().find(hql);
	}

	public void updateByHQL(String hql, Object... values) {
		getHibernateTemplate().bulkUpdate(hql, values);
	}

	public void updateByHQL(String hql, Object values) {
		getHibernateTemplate().bulkUpdate(hql, values);
	}
	// When pn<=-1& pageSize<=-1 Query all the records
	//pageSize is the munber of records
 
	  
	public Page list(final Page p, final String name) {
		return (Page) hibernateTemplate.execute(new HibernateCallback<Page>() {
			public Page doInHibernate(Session session) throws HibernateException, SQLException {
				SearchParamBean bean = (SearchParamBean) p.getConditonObject();
				LinkedList<String> parmnames = bean.getParmnames();
				LinkedList<Object> parmvalues = bean.getParmvalues();

				StringBuilder sb = new StringBuilder(200);
				sb.append("from " + name + " where 1=1");
				// for (String name : parmnames) {
				// sb.append(" and ").append(name).append("=? ");
				// }
				for (int i = 0; i < parmnames.size(); i++) {
					String name = parmnames.get(i);
					if (parmvalues.get(i) instanceof String) {
						sb.append(" and ").append(name).append(" like ? ");
					} else {
						sb.append(" and ").append(name).append("=? ");
					}
				}
				sb.append(" order by id desc");
				String hql = sb.toString();

				Page temp = queryByPage(hql, parmvalues, (p.getCurrentPageNumber() - 1) * p.getItemsPerPage(), p.getItemsPerPage(), p.getTotalNumber(), session);

				p.setList(temp.getList());
				if (p.getTotalNumber() <= 0) {
					p.setTotalNumber(temp.getTotalNumber());
				}
				return p;

			}
		});
	}

	protected Page queryByPage(final String listHql, final List<? extends Object> params, final Integer firstResult, final Integer maxResults,
			final Long totalCount, Session session) {
		if (listHql == null || listHql.trim().length() == 0) {
			return null;
		}
		Page page = new Page();
		// Query listQuery = this.createQuery(listHql, params);
		Query listQuery = session.createQuery(listHql);
		if (params != null && !params.isEmpty()) {
			int size = params.size();
			for (int i = 0; i < size; i++) {
				listQuery.setParameter(i, params.get(i));
			}
		} else {
			if (params != null && !params.isEmpty()) {
				listQuery.setParameter(0, params);
			}
		}
	 
		listQuery.setFirstResult(firstResult);
		listQuery.setMaxResults(maxResults);
		List list = listQuery.list();
		page.setList(list);

	 
		if (totalCount > 0) {
			page.setTotalNumber(totalCount);
		} else {
			String countHql = listHql;
			int order_index = countHql.toUpperCase().lastIndexOf("ORDER");
			if (order_index != -1) {
				countHql = countHql.substring(0, order_index);
			}

			// hibernate turns hql to count
			//because HQL（SQL）dose not support : select count(distinct x, y) from xx;
			 
			QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(null, countHql, Collections.EMPTY_MAP,
					(org.hibernate.engine.SessionFactoryImplementor) session.getSessionFactory());
			queryTranslator.compile(Collections.EMPTY_MAP, false);
			countHql = new StringBuffer("select count(*) from (").append(queryTranslator.getSQLString()).append(") tmp_count_table").toString();
			SQLQuery countQuery = session.createSQLQuery(countHql);
			if (params != null && !params.isEmpty()) {
				int size = params.size();
				for (int i = 0; i < size; i++) {
					countQuery.setParameter(i, params.get(i));
				}
			}
			page.setTotalNumber(Long.valueOf(countQuery.uniqueResult().toString()));

		}

		return page;
	}


	@SuppressWarnings("unchecked")
	public <T> T unique(final String hql, final Object... paramlist) {
		return hibernateTemplate.execute(new HibernateCallback<T>() {

			public T doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (paramlist != null) {
					for (int i = 0; i < paramlist.length; i++) {
						query.setParameter(i, paramlist[i]);
					}
				}
				return (T) query.uniqueResult();
			}
		});
	}

	public List findAll(Class clazz, Map<String, Object> params) {
		String hql = "from " + clazz.getSimpleName();
		if (params == null || params.size() == 0) {
			return queryByHQL(hql);
		}
		Object[] objs = new Object[params.size()];
		hql += " where 1=1";
		int index = 0;
		for (String name : params.keySet()) {
			hql += " and " + name + "=?";
			objs[index++] = params.get(name);
		}
		return queryByHQL(hql, objs);
	}
	public Page list(final Page p, final String name,final String orderBy) {
		return (Page) hibernateTemplate.execute(new HibernateCallback<Page>() {
			public Page doInHibernate(Session session)
					throws HibernateException, SQLException {
				SearchParamBean bean = (SearchParamBean) p.getConditonObject();
				LinkedList<String> parmnames = bean.getParmnames();
				LinkedList<Object> parmvalues = bean.getParmvalues();

				StringBuilder sb = new StringBuilder(200);
				sb.append("from " + name + " where 1=1");
				// for (String name : parmnames) {
				// sb.append(" and ").append(name).append("=? ");
				// }
				for (int i = 0; i < parmnames.size(); i++) {
					String name = parmnames.get(i);
					if (parmvalues.get(i) instanceof String) {
						sb.append(" and ").append(name).append(" like ? ");
					} else {
						sb.append(" and ").append(name).append("=? ");
					}
				}
				sb.append(" order by "+orderBy);
				String hql = sb.toString();

				Page temp = queryByPage(hql, parmvalues,
						(p.getCurrentPageNumber() - 1) * p.getItemsPerPage(),
						p.getItemsPerPage(), p.getTotalNumber(), session);

				p.setList(temp.getList());
				if (p.getTotalNumber() <= 0) {
					p.setTotalNumber(temp.getTotalNumber());
				}
				return p;

			}
		});
	}
}
