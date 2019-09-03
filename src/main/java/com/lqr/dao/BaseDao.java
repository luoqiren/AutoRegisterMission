package com.lqr.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
	public T get(Serializable id);
	public void save(T entity);
	public void update(T entity);
	public void delete(T entity);
	
	/**
	 * 根据实体类对象字段获取总记录数
	 * @param className
	 * @param params
	 * @param size
	 * @return
	 */
	public Long findByClassPropSumRecoreds(final Class<?> className, final Map<String, Object> params);
	
	/**
	 * 根据实体类对象字段获取总记录数
	 * @param className
	 * @return
	 */
	public Long findByClassPropSumRecoreds(final Class<?> className);
	
	
	
	/**
	 * 根据实体类对象字段查询
	 * @param className  要分页的实体类  ： A.class
	 * @param params 字段列表  CommonUtil.objectToMap(A);
	 * @return
	 */
	public List<T> findByClassProperties(final Class<?> className,
			final Map<String, Object> params);
	/**
	 * 根据字段查询并分页显示
	 * @param className  要分页的实体类  ： A.class
	 * @param params 字段列表  CommonUtil.objectToMap(A);
	 * @param index 当前页 page.getPageNo()
	 * @param size  每页显示的大小 page.getPageSize()
	 * @param orderField 排序 ("userName, createDate")
	 * @return List<T>
	 */
	public List<T> findByClassPropertiesPage(final Class<?> className,
			final Map<String, Object> params, final int index, final int size,
			final int order, String... orderField);
	
	
	/**
	 * 根据字段查询并分页显示
	 * 
	 * @param className
	 *            要分页的实体类 ： A.class
	 * @param index
	 *            当前页 page.get
	 * @param size
	 *            每页显示的大小 page.get
	 * @param orderField
	 *            排序 ("userName, createDate")
	 * @return List<T>
	 */
	public List<T> findByClassPropertiesPage(final Class<?> className, final int index, final int size, final int order,
			String... orderField);

	
	/**
	 * 查询单个值
	 * @param hql 语句
	 * @param values 参数列表
	 * @return 返回单个值
	 */
	public Object executeSacale(final String hql, final Object... values);
	
	/**
	 * 执行hql删除、修改语句
	 * @param hql 语句
	 * @param values 值列表
	 * @return
	 */
	public int executNonQuery(final String hql, final Object... values);
	
	/**
	* <p>Description: 执行原生SQL进行查询 </p>
	* @date 20180101
	* @param sql 传入带有占位符的SQL
	 * @param index 当前页 page.getPageNo()
	 * @param size  每页显示的大小 page.getPageSize()
	* @param values 每个通配符对应参数的值
	* @return
	 */
	public List<Object> findByOrigSQL(String sql, final int index, final int size, Object... values);
	
	/**
	 * 根据原生SQL获取总记录数
	 * @param className
	 * @param params
	 * @param size
	 * @return
	 */
	public Long findByOrigSQLSumRecoreds(String sql, Object... values);
	
	<B> List<B> findDataByOrigSQL(String sql, int index, int size, B t);
	
}
