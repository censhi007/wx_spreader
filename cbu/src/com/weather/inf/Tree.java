package com.weather.inf;

public interface Tree<T> extends java.io.Serializable{
	/**
	 * 向树中添加对象
	 * @param t
	 */
	public T put(T t);
	/**
	 * 同书中删除对象
	 * @param t
	 * @return
	 */
	public T remove(T t);
	/**
	 * 从树中查找对象
	 * @param t
	 * @return
	 */
	public T search(T t);
	/**
	 * 是否是叶子
	 * @return
	 */
	public boolean isLeaf();
	/**
	 * 获取当前树的深度
	 * @return
	 */
	public int degree();
	/**
	 * 当前树是否平衡
	 * @return
	 */
	public boolean isBalance();
}
