package cn.just.thread.work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
/**
 * ���̵����㷨��SCAN�㷨
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
		int location=random.nextInt(200);	//��ǰָ���λ��
		System.out.println("��ǰָ��λ�ã�"+location);
//		int direction=random.nextInt(1);	//0,�������1����������
		int direction=1;
		Collections.sort(list);
		Iterator<Integer> it=list.iterator();
		int size=0;
		int sum=0;
		if(direction==1){ 		//��������	
		 while(it.hasNext()){
			 int n=it.next();
			 if(location<n){
				 int next=n;
				 sum+=next-location;
				 location=next;
//				 it.remove();
				 list.set(size, 0);
			 }
			 size++;
			}
		 if(!list.isEmpty()){
			 for(int i=list.size()-1;i>=0;i--){
				if(list.get(i)!=0){
				 sum+=location-list.get(i);
				 location=list.get(i);
				 }
			 }
		 }
		 System.out.println("�ܵ�Ѱַ����Ϊ��"+sum);
		 System.out.println("ƽ��Ѱַ����Ϊ��"+(double)(sum/SIZE));
		}
		
	}
}
