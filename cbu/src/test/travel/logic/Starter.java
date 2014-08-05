package test.travel.logic;

import test.travel.model.User;

import com.cbu.util.Caller;
import com.cbu.util.ErrLog;
import com.cbu.util.HTTPConnector;
import com.cbu.util.LThread;
import com.cbu.util.TPool;

/**
 * 开始入口
 * @author BUJUN
 *
 */
public class Starter implements Caller{
	//初始化一个http连接
	private HTTPConnector con=new HTTPConnector("TrainTraveler");
	private TPool pool=new TPool(5);//线程池
	private User lu;
	public void init(){
		//生成一个登陆对象
	}
	
	public void start(){
		//登陆
		this.setTask(new LoginTask(lu),1);
	}
	
	
	@Override
	public void pull(ErrLog log) {
		
	}
	@Override
	public void setCall(Caller call) {
		
	}
	public void addTask(LThread task){
		pool.addTask(task);
	};
	public void addTask(LThread task,int pr){
		pool.addTask(task,pr);
	};
	/**
	 * 设置caller为starter
	 * @param task
	 */
	public void setTask(LThread task){
		task.setCall(this);
		pool.addTask(task);
	}
	public void setTask(LThread task,int pr){
		task.setCall(this);
		pool.addTask(task,pr);
	}

	public HTTPConnector getCon() {
		return con;
	}

	public void setCon(HTTPConnector con) {
		this.con = con;
	}
	
}
