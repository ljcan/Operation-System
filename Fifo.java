package cn.just.thread.work;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * ҳ���û��㷨֮�Ƚ��ȳ��㷨
 * @author shinelon
 *
 */
public class Fifo {
	//�ܹ� ����20��ҳ��
	public static List allList=new ArrayList(20);
	public static List list=new ArrayList();
	public static int count=0;
	/**
	 * FIFO
	 */
	@SuppressWarnings("unchecked")
	public static void task(){
//		Iterator it=list.iterator();
		int b;
		for(int j=0;j<20;j++){
			if(!list.contains(allList.get(j))&&list.size()<3){
				list.add(allList.get(j));
				count++;
				if(!list.isEmpty()){
					System.out.println("��ǰ�����ڴ��ҳ���ǣ�");
					for(int i=0;i<list.size();i++){
						System.out.print(list.get(i)+" ");
					}
					System.out.println();
				}
			}else{
				if(list.size()>=3&&!list.contains(allList.get(j))){
//					it.remove();
					list.remove(0);  //����ͷɾ��
					list.add(allList.get(j));
					count++;
					if(!list.isEmpty()){
						System.out.println("��ǰ�����ڴ��ҳ���ǣ�");
						for(int i=0;i<list.size();i++){
							System.out.print(list.get(i)+" ");
						}
						System.out.println();
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Random random=new Random();
		for(int i=0;i<20;i++){
			allList.add(random.nextInt(10));
		}
		for(int i=0;i<20;i++){
			System.out.print(allList.get(i)+" ");
		}
		System.out.println();
		task();
		double percent=(double)count/20;
		System.out.println("ȱҳ��Ϊ��"+percent);
	}
}
