package cn.just.thread.work;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author shinelon
 *
 */
public class LRU {
	public static List allList=new ArrayList();
	public static List list=new ArrayList();
	public static int count=0;
	public static int BLOCK_NUM=30;
	public static void task(){
		for(int i=0;i<BLOCK_NUM;i++){
		  if(list.size()<3){
			  int page=(int) allList.get(i);
			if(!list.contains(page)){
				list.add(page);
				count++;
				if(!list.isEmpty()){
					System.out.println("当前调入内存的页面是：");
					for(int j=0;j<list.size();j++){
						System.out.print(list.get(j)+" ");
					}
					System.out.println();
				}
			}else{		//如果内存中已经调入该页面将其放入队尾
				list.remove((Integer)page);
				list.add(page);
			}
			}else{
				if(list.size()>=3){
					//如果需要的页面已经调入内存将其放入队尾
					int block=(int) allList.get(i);
					if(list.contains(block)){
						list.remove((Integer)block);
						list.add(block);
						if(!list.isEmpty()){
							System.out.println("当前调入内存的页面是：");
							for(int j=0;j<list.size();j++){
								System.out.print(list.get(j)+" ");
							}
							System.out.println();
						}
					}else{
						//删除队头，即最近最少使用的页面
						list.remove(0);
						list.add(block);
						count++;
						if(!list.isEmpty()){
							System.out.println("当前调入内存的页面是：");
							for(int j=0;j<list.size();j++){
								System.out.print(list.get(j)+" ");
							}
							System.out.println();
						}
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Random random=new Random();
		for(int i=0;i<BLOCK_NUM;i++){
			allList.add(random.nextInt(10));
		}
		for(int i=0;i<BLOCK_NUM;i++){
			System.out.print(allList.get(i)+" ");
		}
		System.out.println();
		task();
		float percent=(float)count/BLOCK_NUM;
		System.out.println("缺页率为："+percent);
	}
}
