package com.weather.inf;


/**
 * 本地的Thread类，该类继承自Runnable
 * @author Administrator
 *
 */
public abstract class LThread implements Runnable{
	private LThread ctr;	
	
	private int SN=10;//计数器
	@Override
	public final void run(){
		try{
			main();
		}catch(Exception e){
			throw e;
		}finally{
			destroy();
		}
	}
	/**
	 * 子类继承时，必须重写本方法<br/>
	 * 线程运行的主体函数
	 */
	public abstract void main ();
	/**
	 * 子类继承时，必须重写本方法<br/>
	 * 线程结束时自动调用的销毁方法
	 */
	public abstract void destroy();
	
	public Runnable start(){
		new Thread(this).start();
		return this;
	}
	/**
	 * 在管理器的管理下运行
	 * @param ctr
	 * @return
	 */
	public Runnable start(LThread ctr){
		this.ctr=ctr;
		new Thread(this).start();
		return this;
	}
	/**
	 * 只有下级对象才能获取控制线程
	 * @return
	 */
	protected LThread getCtr() {
		return ctr;
	}
	
	
	protected void setSn(int sn){
		this.SN=sn;
	}
	/**
	 * 返还一个权限
	 */
	public void gee(){
		SN++;
	}
	/**
	 * 获取下一个权限，如果没有获取成功返回false
	 * @return
	 */
	public boolean Next(){
		if(SN>0){
			SN--;
			return true;
		}
		return false;
	}
	
	public boolean hasNext(){
		return SN>0;
	}
}
