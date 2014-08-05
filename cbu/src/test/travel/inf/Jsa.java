package test.travel.inf;

import java.util.ArrayList;
import java.util.List;

public class Jsa {
	private List<Object> list= new ArrayList<Object>();
	
	public void push(Object o){
		list.add(o);
	}
	public void add(Object o){
		list.add(o);
	}
	
	public void remove(Object o){
		list.remove(o);
	}
	public int length(){
		return list.size();
	}
	public List<Object> getList(){
		return list;
	}
}
