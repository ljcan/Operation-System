package cn.just.thread.work;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * �Ƚ��ȳ�ҳ���û��㷨
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
					System.out.println("��ǰ�����ڴ��ҳ���ǣ�");
					for(int j=0;j<list.size();j++){
						System.out.print(list.get(j)+" ");
					}
					System.out.println();
				}
			}else{		//����ڴ����Ѿ������ҳ�潫������β
				list.remove((Integer)page);
				list.add(page);
			}
			}else{
				if(list.size()>=3){
					//�����Ҫ��ҳ���Ѿ������ڴ潫������β
					int block=(int) allList.get(i);
					if(list.contains(block)){
						list.remove((Integer)block);
						list.add(block);
						if(!list.isEmpty()){
							System.out.println("��ǰ�����ڴ��ҳ���ǣ�");
							for(int j=0;j<list.size();j++){
								System.out.print(list.get(j)+" ");
							}
							System.out.println();
						}
					}else{
						//ɾ����ͷ�����������ʹ�õ�ҳ��
						list.remove(0);
						list.add(block);
						count++;
						if(!list.isEmpty()){
							System.out.println("��ǰ�����ڴ��ҳ���ǣ�");
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
		System.out.println("ȱҳ��Ϊ��"+percent);
	}
}
