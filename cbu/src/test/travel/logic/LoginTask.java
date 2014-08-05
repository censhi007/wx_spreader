package test.travel.logic;

import test.travel.model.User;

import com.cbu.util.LThread;

public class LoginTask extends LThread{
	private User loger;
	public LoginTask(User lu){
		this.loger=lu;
	}
	@Override
	public void main() {
	}

	@Override
	public void destroy() {
		this.getCtr().gee();
	}
	
}
