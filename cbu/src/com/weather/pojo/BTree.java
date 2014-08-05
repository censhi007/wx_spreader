package com.weather.pojo;

import com.weather.inf.Tree;

/**
 * 平衡二叉树
 * @author BUJUN
 *
 */

public class BTree<T> implements Tree<T>{
	private T T;
	private BTree<T> left;
	private BTree<T> right;
	private int ld=0;//左树高度
	private int rd=0;//右树高度
	
	private static final long serialVersionUID = -54846811337301981L;
	/**
	 * 如果本节点
	 */
	@Override
	public T put(T t) {
		int i = this.compareTo(t);
		if( i==0 )return T;
		//比本节点小，则将t插入到左子树
		if(i<0){	
			if(left == null){				
				left = getTree(t);
				ld=left.degree();
				makeBalance();//如果树不平衡了，那么将旋转树，使其平衡
				return t;
			}else{
				T x =left.put(t);
				ld=left.degree();
				makeBalance();//如果树不平衡了，那么将旋转树，使其平衡
				return x;
			}
		}
		//比本节点大。将t插入到右子树
		if(i>0){
			if(right == null){
				right = getTree(t);
				rd=right.degree();
				makeBalance();//如果树不平衡了，那么将旋转树，使其平衡
				return t;
			}else{
				T x = right.put(t);
				rd = right.degree();
				
				makeBalance();//如果树不平衡了，那么将旋转树，使其平衡
				return x;
			}
			
		}
		return null;
	}
	/**
	 * 使树平衡
	 * @return
	 */
	public boolean makeBalance(){
		if(isBalance())return true;//如果已经平衡了，那么直接退出
		if(left!=null && !left.isBalance()){
			left.makeBalance();//如果左子树不平衡，那么使左子树平衡
			ld=left.degree();
		}
		if(right!=null && !right.isBalance()){
			right.makeBalance();//如果右子树不平衡，那么使右子树平衡
			rd=right.degree();
		}
		if(isBalance())return true;//如果下级旋转后，本级已经平衡了，直接退出。
		if(ld >rd){
			if(left.ld > left.rd){
				RR();//右旋
			}else{
				left.LR();//左孩子左旋
				RR();//右旋
			}
			return true;
		}else if(ld < rd){
			if(right.rd > right.ld){
				LR();//左旋
			}else{
				right.RR();//右孩子右旋
				LR();//左旋
			}
		}
		return false;
	}
	/**
	 * 左旋
	 */
	private void LR(){
		//System.out.println("左旋"+T+"前,LD："+ld+",RD:"+rd);
		BTree<T> l= left;
		BTree<T> r= right;
		T x = T;
		
		right = r.right;
		
		r.right = r.left;
		r.rd=r.right==null ? 0 :r.right.degree();
		r.left=l;
		r.ld = l ==null ? 0 : l.degree();
		
		left = r;
		ld = r.degree();
		rd=right== null ? 0 : right.degree();
		
		T = left.T;
		left.T=x;
		//System.out.println("左旋"+T+"后,LD："+ld+",RD:"+rd+"\n");
	}
	/**
	 * 右旋
	 */
	private void RR(){
		//System.out.println("右旋"+T+"前,LD："+ld+",RD:"+rd);
		BTree<T> l= left;
		BTree<T> r= right;
		T x = T;
		
		left = l==null ? null : l.left;
		
		l.left = l.right;
		l.ld=l.left==null ? 0 :l.left.degree();
		l.right = r;
		l.rd=r==null? 0 : l.right.degree();
		
		right = l;
		rd = right.degree();
		ld= left==null ? 0 :left.degree();
		
		T = right.T;
		right.T = x;
		
		//System.out.println("右旋"+T+"后,LD："+ld+",RD:"+rd+"\n");
	}
	/**
	 * 默认根节点不是叶子节点。<br/>
	 * 如果根节点不仅是叶子节点，并且是需要删除的节点，那么直接删除本树即可<br/>
	 * 
	 */
	@Override
	public T remove(T t) {
		if(isLeaf())return null;//叶子节点不进行操作
		int i= compareTo(t);
		if(i == 0){
			//找到了节点。
			if(right == null ){//单左树
				T x =T;
				T = left.T;
				right = left.right;
				left = left.left;
				ld = left.degree();
				makeBalance();
				return x;
			}
			if(left ==null){
				T x =T;
				T = right.T;
				left = right.left;
				right = right.right;
				rd = right.degree();
				makeBalance();
				return x;
			}
			//如果左右子树健全
			if(ld >= rd ){
				//从左树中删除一个叶子
				BTree<T> ll = left;
				if(ll.right == null){
					T x = T;
					T = ll.T;
					left = ll.left;
					ld =left ==null ? 0 : left.degree();
					makeBalance();
					return x;
				}
				
				while(ll.right.right!=null){
					ll=ll.right;
				}
				if(ll.right.right==null){
					T x = T;
					T = ll.right.T;
					ll.right = ll.right.left;
					rd = ll.right==null ? 0 : ll.right.degree();
					makeBalance();
					return x;
				}
			}else{
				//从右子树中删除最小的
				BTree<T> ll = right;
				if(ll.left==null){
					T x = T;
					T = ll.T;
					right = ll.right;
					rd = right ==null ? 0 : right.degree();
					makeBalance();
					return x;
				}
				
				while(ll.left.left!=null){
					ll=ll.left;
				}
				if(ll.left.left==null){
					T x = T;
					T = ll.left.T;
					ll.left = ll.left.right;
					ld= ll.left ==null ? 0 :ll.left.degree();
					makeBalance();
					return x;
				}
			}
			
			return null;
		}
		if(i<0){
			if(left ==null) return null;//没找到
			if(left.isLeaf()){
				if(left.compareTo(t)==0){
					//找到了
					ld = 0;
					BTree<T> x = left;
					left = null;
					makeBalance();
					return x.T;
				}
				//没找到
				return null;
			}
			T x = left.remove(t);
			if(x == null )return null;//没找到
			ld = left.degree();//说明进行了删除，左子树的高度发生了变化。
			makeBalance();
			return x;
		}
		if(i>0){
			if(right == null )return null;//没有找到
			if(right.isLeaf()){
				if(right.compareTo(t)==0){
					//找到了
					rd=0;
					BTree<T> x = right;
					right=null;
					return x.T;
				}
				return null;//没有找到
			}
			T x = right.remove(t);
			if(x == null )return null;//没找到
			rd = right.degree();//说明进行了删除，右子树的高度发生了变化。
			makeBalance();
			return x;
		}
		return null;
	}

	@Override
	public T search(T t) {
		int i=compareTo(t);
		if(i == 0 )return T;
		if(i<0){
			if(left ==null)return null;
			return left.search(t);
		}
		if(i>0){
			if(right == null)return null;
			return right.search(t);
		}
		return null;
	}
	
	public void setData(T data){
		T=data;
	}
	public T getData(){
		return T;
	}

	public BTree<T> getLeft() {
		return left;
	}

	public void setLeft(BTree<T> left) {
		this.left = left;
	}

	public BTree<T> getRight() {
		return right;
	}

	public void setRight(BTree<T> right) {
		this.right = right;
	}
	
	public boolean isLeaf(){
		return left ==null && right ==null;
	}
	/**
	 * 获取当前树的深度。<br/>
	 * 先获取左子树的深度，再获取右子树的深度。<br/>
	 * 去深度最高的子树的高度，然后再在基础上+1
	 */
	public int degree(){
		int l= ld;
		int r= rd;		
		int d =( l < r ? r : l ) + 1;		
		return d;
	}
	
	protected int Ldegree(){
		return left==null ? 0 : left.degree();
	}
	protected int Rdegree(){
		return right==null ? 0 : right.degree();
	}
	/**
	 * 如果子树是不平衡的，那么本树也是不平衡的。<br/>
	 * 如果子树是平衡的，那么获取左右子树的深度，如果深度相差大于1，那么本树是不平衡的。<br/>
	 * 否则本树是平衡的
	 */
	public boolean isBalance(){
		boolean ib=true;
		ib =ib && (left ==null ? true : left.isBalance());
		ib =ib && (right == null ? true : right.isBalance());
		if(!ib)return ib;
		int l= ld;
		int r= rd;			
		int c = l-r;		
		if(c<-1 || c>1)return false;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public int compareTo(T t){
		if(T==null || t==null )return 0;
		
		return ((Comparable<T>)T).compareTo(t);
	}
	
	public BTree<T> getTree(T t){
		BTree<T> tree = new BTree<T>();
		tree.setData(t);
		return tree;
	}
}
