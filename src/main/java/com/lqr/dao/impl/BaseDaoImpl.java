package com.lqr.dao.impl;

/**
 * 
 */

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;

import com.lqr.dao.BaseDao;


/**
 */
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	private final int ORDER_BY_ASC=1; //升序 
	private final int ORDER_BY_DESC=0; //降序 
	
	@Autowired
	@Qualifier(value="hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	private Class<T> entityClass;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class<T>) params[0];
	}

	public T get(Serializable id) {
		return (T) hibernateTemplate.get(entityClass, id);
	}

	public void save(T entity) {
		hibernateTemplate.save(entity);
	}

	public void update(T entity) {
		hibernateTemplate.update(entity);
	}

	public void delete(T entity) {
		hibernateTemplate.delete(entity);
	}

	/**
	 * 批量删除
	 * @param collection 要删除的集合
	 */
	public void deleteAll(Collection<?> collection) {
		hibernateTemplate.deleteAll(collection);
	}
	
	/**
	 * 根据实体类对象字段获取总记录数
	 * @param className
	 * @param params
	 * @param size
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Long findByClassPropSumRecoreds(final Class<?> className, final Map<String, Object> params) {
		List<Long> object = (List<Long>) hibernateTemplate.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Criteria criteria = session.createCriteria(className);
						for (String field : params.keySet()){
							criteria.add(Property.forName(field).eq(params.get(field)));
						}
						criteria.setProjection(Projections.rowCount());
						return criteria.list();
					}
				});
		return object == null ? 0L : object.get(0);
	}
	
	/**
	 * 根据实体类对象字段获取总记录数
	 * @param className
	 * @return
	 */
	public Long findByClassPropSumRecoreds(final Class<?> className) {
		return findByClassPropSumRecoreds(className, new HashMap<String,Object>());
	}
	
	/**
	 * 根据实体类对象字段查询
	 * @param params
	 *            字段列表
	 * @param values
	 *            值列表
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findByClassProperties(final Class<?> className,
			final Map<String, Object> params) {
		return (List<T>) hibernateTemplate.execute(new HibernateCallback() {
			public List<?> doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = session.createCriteria(className);
				for (String field : params.keySet()){
					criteria.add(Property.forName(field).eq(params.get(field)));
				}
				return criteria.list();
			}
		});
	}
	
	/**
	 * 根据字段查询并分页显示
	 * 
	 * @param className
	 *            要分页的实体类
	 * @param params
	 *            字段列表
	 * @param index
	 *            当前页
	 * @param size
	 *            每页显示的大小
	 * @param orderField
	 *            排序 ("userName, createDate")
	 * @return List<T>
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findByClassPropertiesPage(final Class<?> className,
			final Map<String, Object> params, final int index, final int size,
			final int order, final String... orderField) {
		return (List<T>) hibernateTemplate.execute(new HibernateCallback() {
			public List<?> doInHibernate(Session session)
					throws HibernateException {
				Criteria criteria = session.createCriteria(className);
				for (String f : params.keySet())
					criteria.add(Property.forName(f).eq(params.get(f)));
				if (orderField!=null) {//排序条件不为空的时候
					switch (order) {
					case ORDER_BY_ASC:
						for (int i = 0; i < orderField.length; i++) {
							criteria.addOrder(org.hibernate.criterion.Order.asc(orderField[i]));
						}
						break;
					case ORDER_BY_DESC:
						for (int i = 0; i < orderField.length; i++) {
							criteria.addOrder(org.hibernate.criterion.Order.desc(orderField[i]));
						}
						break;
					}
				}
				criteria.setFirstResult((index - 1) * size);
				criteria.setMaxResults(size);
				return criteria.list();
			}
		});
	}
	
	/**
	 * 根据字段查询并分页显示
	 * 
	 * @param className
	 *            要分页的实体类
	 * @param index
	 *            当前页
	 * @param size
	 *            每页显示的大小
	 * @param orderField
	 *            排序 ("userName, createDate")
	 * @return List<T>
	 */
	public List<T> findByClassPropertiesPage(final Class<?> className,final int index, final int size,final int order, String... orderField) {
		return findByClassPropertiesPage(className, new HashMap<String, Object>(), index, size, order, orderField);
	}
	
	/**
	 * 查询单个值
	 * @param hql 语句
	 * @param values 参数列表
	 * @return 返回单个值
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object executeSacale(final String hql, final Object... values) {
		return hibernateTemplate.execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql);
				setParams(query, values);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
	}

	/**
	 * 执行hql删除、修改语句
	 * @param hql 语句
	 * @param values 值列表
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int executNonQuery(final String hql, final Object... values) {
		return (int) hibernateTemplate.execute(new HibernateCallback() {
			public Integer doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createQuery(hql);
				setParams(query, values);
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * 根据原生SQL获取总记录数
	 * @param className
	 * @param params
	 * @param size
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Long findByOrigSQLSumRecoreds(final String sql, final Object... values) {
		List<BigInteger> object = (List<BigInteger>) hibernateTemplate.execute(new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						StringBuffer countSql = new StringBuffer();
						countSql.append("select count(1) ").append(sql.substring(sql.toLowerCase().indexOf("from")));
						Query query = session.createSQLQuery(countSql.toString());
						setParams(query, values);//配置参数
						return query.list();
					}
				});
		return object == null ? 0L : Long.valueOf(object.get(0).toString());
	}
	
	
	/**
	 * sql 传入带有占位符的SQL
		index 当前页 page.getPageNo()
		size 每页显示的大小 page.getPageSize()
		values 每个通配符对应参数的值
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Object> findByOrigSQL(final String sql, final int index, final int size, final Object... values) {
		return (List<Object>) hibernateTemplate.execute(new HibernateCallback() {
			public List<?> doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(sql);
				setParams(query, values);//配置参数
				query.setFirstResult((index - 1) * size);
				query.setMaxResults(size);
				return query.list();
			}
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <B> List<B> findDataByOrigSQL(final String sql, final int index,final int size, final B t) {
		
		return (List<B>) hibernateTemplate.execute(new HibernateCallback() {
			public List<B> doInHibernate(Session session)
					throws HibernateException {
				Query query = session.createSQLQuery(sql).addEntity(t.getClass());
				if(index != 0 && size != 0){
					query.setFirstResult((index - 1) * size);
					query.setMaxResults(size);
				}
				return query.list();
			}
		});
	}
	
	
	/**
	 * 配置参数 
	 * @param query
	 * @param values
	 */
	protected void setParams(Query query, Object... values) {
		if (!isEmptyOrNull(values)) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
	}
	/**
	 * 判断值是否为空或
	 * @param values
	 * @return
	 */
	private boolean isEmptyOrNull(Object... values) {
		return (values==null || values.length == 0);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

}