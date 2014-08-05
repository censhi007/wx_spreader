package com.cbu.util;

import java.util.LinkedList;

/**
 * 线程池。管理所有的线程以及与外界的连接。
 * @author BUJUN
 *
 */
public class TPool extends LThread{
	
	private boolean stop=false;
	private LinkedList<LThread>pool1=new LinkedList<LThread>();
	private LinkedList<LThread>pool2=new LinkedList<LThread>();
	private LinkedList<LThread>pool3=new LinkedList<LThread>();
	private LinkedList<LThread>pool4=new LinkedList<LThread>();
	private LinkedList<LThread>pool5=new LinkedList<LThread>();
	public TPool(){
		this.setSn(5);		
	}
	public TPool(int sn){
		this.setSn(sn);
	}
	@Override
	public void main() {
		while(!stop){//如果没有在外部停止线程的话，那么无线循环下去
			
			if(hasNext()){
				while(!pool1.isEmpty() && Next()){//只有在还有值的情况下才会Next					
					st(pool1.pop());
				}
				while(!pool2.isEmpty()&& Next()){
					st(pool2.pop());
				}
				while(!pool3.isEmpty()&& Next()){
					st(pool3.pop());
				}
				while(!pool4.isEmpty()&& Next()){
					st(pool4.pop());
				}
				while(!pool5.isEmpty()&& Next()){
					st(pool5.pop());
				}
				
			}
			
		}
	}
	@Override
	public void destroy() {
		
	}

	public void addTask(LThread lt){
		pool3.add(lt);
	}
	/**
	 * 按照优先级加入任务
	 * @param lt
	 * @param pr
	 */
	public void addTask(LThread lt,int pr){
		switch(pr){
		case 1:pool1.add(lt);break;
		case 2:pool2.add(lt);break;
		case 3:pool3.add(lt);break;
		case 4:pool4.add(lt);break;
		case 5:
		default:
			pool5.add(lt);break;
		}
	}

	private void st(LThread lt){
		lt.setCtr(this);
		lt.start();
	}
	
	public Runnable start(){
		Thread t=new Thread(this);
		t.setDaemon(true);
		t.start();
		return this;
	}
	/**
	 * 在管理器的管理下运行
	 * @param ctr
	 * @return
	 */
	public Runnable start(LThread ctr){
		this.setCtr(ctr);
		Thread t=new Thread(this);
		t.setDaemon(true);
		t.start();
		return this;
	}
	
	public boolean isEmpty(){
		return pool1.isEmpty()&&pool2.isEmpty()&&pool3.isEmpty()&&pool4.isEmpty()&&pool5.isEmpty();
	}
	/**
	 * 停止循环
	 */
	public void stop(){
		this.stop=true;
	}
}
