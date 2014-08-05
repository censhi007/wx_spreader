package com.weather.pojo;

@SuppressWarnings("rawtypes")
public class Hits<T> implements java.io.Serializable,Comparable<Hits>{	
	private static final long serialVersionUID = -5162911505526271477L;
	private int index;
	private int hit;
	private T str;
	public Hits(){
		hit=1;
	}
	public Hits(int index,T str){
		this.index=index;
		this.str=str;
		hit=1;
	}
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public T getStr() {
		return str;
	}
	public void setStr(T str) {
		this.str = str;
	}
	@SuppressWarnings({ "unchecked"})
	@Override
	public int compareTo(Hits o) {	
		if(o.hit==hit){
			return ((Comparable<T>)str).compareTo(((Hits<T>)o).str);
			
		}
		return o.hit-hit;
	}
	public void hit(){
		hit++;
	}
}
