package cn.just.thread.experiment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class DynamicPriority {
	public static List<PCBWork> list=new ArrayList<PCBWork>();
	public static class PCBWork{
		public int priority;
		public String name;
		public int time;
		private String state;			
		public PCBWork(int priority, String name, int time, String state) {
			super();
			this.priority = priority;
			this.name = name;
			this.time = time;
			this.state = state;
		}
		@Override
		public String toString() {
			return "PCBWork [priority=" + priority + ", name=" + name + ", time=" + time + ", state=" + state + "]";
		}
	}
	public static void run() throws InterruptedException{
		Iterator<PCBWork> it=list.iterator();
		if(it.hasNext()){
			PCBWork work=it.next();
			work.state="ready";
			System.out.println(work.toString());
			if(work.time>0){		//��ʼ����
				work.state="running";
				Thread.sleep(1000);
				if(work.priority>0){
					work.priority--;
				}
				work.time--;
				System.out.println(work.toString());
			}else{
				work.state="finash";
				System.out.println(work.toString());
				System.out.println(work.name+"ִ�����");
				it.remove();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		int num;
		int pri;
		int time;
		System.out.println("���������:");
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		num=scanner.nextInt();
		Random rand=new Random();
		for(int i=0;i<num;i++){
			pri=rand.nextInt(10);		//����һ��0~10֮ǰ�����������������Ȩ
			time=rand.nextInt(20);
			list.add(new PCBWork(pri,"����"+i,time,"ready"));
		}
		while(!list.isEmpty()){
			Collections.sort(list,new Comparator<PCBWork>() {		//�������ȼ���С����
				@Override
				public int compare(PCBWork t1, PCBWork t2) {
					int pri1=t1.priority;
					int pri2=t2.priority;
					return -(pri1-pri2);
				}
			});
			run();
		}
	}
}
