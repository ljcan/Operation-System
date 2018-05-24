package cn.just.thread.work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
/**
 * 磁盘调度算法，SCAN算法
 * @author shinelon
 *
 */
public class Scan {
	public static ArrayList<Integer> list=new ArrayList<Integer>();
	public static int SIZE=20;
	public static void main(String[] args) {
		Random random=new Random();
		for(int i=0;i<SIZE;i++){
			list.add(random.nextInt(200));
		}
		for(int i=0;i<SIZE;i++){
			System.out.print(list.get(i)+" ");
		}
		System.out.println();
		int location=random.nextInt(200);	//当前指针的位置
		System.out.println("当前指针位置："+location);
		int direction=random.nextInt(2);	//0,由外向里，1：由里向外
		Collections.sort(list);
		Iterator<Integer> it=list.iterator();
		int size=0;
		int sum=0;
		if(direction==1){ 		//由里向外寻址	
			System.out.println("寻址方向为：由里向外");
		 while(it.hasNext()){
			 int n=it.next();
			 if(location<n){
				 int next=n;
				 sum+=Math.abs(next-location);
				 location=next;
				 list.set(size, 0);
			 }
			 size++;
			}
		 if(!list.isEmpty()){
			 for(int i=list.size()-1;i>=0;i--){
				if(list.get(i)!=0){
				 sum+=Math.abs(location-list.get(i));
				 location=list.get(i);
				 }
			 }
		 }
		 System.out.println("总的寻址长度为："+sum);
		 System.out.println("平均寻址长度为："+(double)(sum/SIZE));
		}
		if(direction==0){		//由里向外寻址
			System.out.println("寻址方向为：由外向里");
			while(it.hasNext()){
				int n=it.next();
				if(location<n){
					while(size>=0){
						int next=list.get(size);
						sum+=Math.abs(location-next);
						location=next;
						list.set(size, 0);
						size--;
					}
				}
				size++;
			}
				if(!list.isEmpty()){
					for(int i=0;i<list.size();i++){
						if(list.get(i)!=0){
							sum+=Math.abs(list.get(i)-location);
							location=list.get(i);
						}
					}
			}
			 System.out.println("总的寻址长度为："+sum);
			 System.out.println("平均寻址长度为："+(double)(sum/SIZE));
		}
	}
}

