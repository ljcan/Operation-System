package cn.just.thread.experiment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class BankerAlgorithm2 {
	static int allo;
	static int num;			//资源的总数
	public static List<Resource> resourcesList=new ArrayList<Resource>();
	public static List<Work> worksList=new ArrayList<Work>();
	public static List<Work> success=new ArrayList<Work>();
	
	public static class Work{
		public String name;
		public List<Map<String,Integer>> need=new ArrayList<Map<String,Integer>>();
		public List<Map<String,Integer>> allocation=new ArrayList<Map<String,Integer>>();
		public boolean finish;
		public Work() {
		}
	}
	/**
	 * 资源类
	 * @author shinelon
	 *
	 */
	public static class Resource{
		public int sum;		//资源的总数
		public int available;
		public String name;
		public Resource(int sum, String name,int available) {
			super();
			this.sum = sum;				//资源总数
			this.name = name;			//资源名称
			this.available=available;	//资源可用数
		}
	}
	
	private static void run() {
		int remain;			//资源剩余数
		Iterator<Work> it_work=worksList.iterator();
		Iterator<Resource> it_resource=resourcesList.iterator();
		if(it_work.hasNext()){
			int i=0;
			Work work=it_work.next();
			while(it_resource.hasNext()){		//遍历资源队列,如果有可用资源就暂时各自分配一个
					if(work.need.size()==0){
						work.finish=true;
					}
				Resource resource=it_resource.next();
				if(!work.finish){		//当前任务如果没有完成，则进行分配
						if(resource.available>0){		//如果该类资源还有可用资源
							int temp_avali=resource.available;
							if(temp_avali--<=0){
								System.out.println("当前状态为不安全状态，会产生死锁");
								break;
							}
							if(work.need.get(i).get("资源"+i)!=null){
							int allo_temp=work.allocation.get(i).get("资源"+i);
							work.allocation.get(i).put("资源"+i, allo_temp++);
							int need_temp=work.need.get(i).get("资源"+i);
							work.need.get(i).put("资源"+i, need_temp--);
							if(need_temp==0){
//								work.need.remove(i);		//当前资源已经不需要了就将其移出
								work.need.get(i).put("资源"+i, 0);
							}
							}else{
								work.allocation.get(i).put("资源"+i, 1);
							}
							resource.available--;
						}else{
							System.out.println("产生了死锁");
							System.exit(0);
						}
						int k=0;
						for(Map m:work.need){
							if(m.get("资源"+i)!=null)
								break;
							k++;
							if(k==m.size()){
								work.finish=true;
							}
						}
				}else{
//					work.finish=true;		//标识为完成状态
					success.add(work);		//将安装状态的顺序放入一个队列中
					it_work.remove(); 		//将执行完毕的进程移出工作队列
					break;
				}
				i++;
			}
		}
		
	}
	public static void main(String[] args) {
		System.out.println("请输入资源的类别数目：");
		
		int resourceNum = 0;
		int threadNum;
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		do{
		num=scanner.nextInt(5);			//1~5类资源
		if(num==0){
			System.out.println("你输入的数目为0，请重新输入");
		}
		}while(num==0);
		for(int i=0;i<num;i++){
			System.out.println("请输入第"+i+"类的资源的总数");
			resourceNum=scanner.nextInt(20);
			resourcesList.add(new Resource(resourceNum, "资源"+i,resourceNum));
		}
		System.out.println("请输入进程数：");
		threadNum=scanner.nextInt();	
			
		for(int i=0;i<threadNum;i++){
			Work work=new Work();
			for(int j=0;j<num;j++){
			System.out.println("请输入进程"+i+"已经分配的"+j+"资源的数目（不能超过资源总数）");
			int allo=scanner.nextInt();
			System.out.println("请输入进程"+i+"还需要的"+j+"资源的数目（不能超过资源总数）");
			int ne=scanner.nextInt();
			Map<String,Integer> need_map=new HashMap<String,Integer>();
			need_map.put("资源"+j, ne);
			work.need.add(need_map);
			Map<String,Integer> allo_map=new HashMap<String,Integer>();
			allo_map.put("资源"+j, allo);
			work.allocation.add(allo_map);
			}
			work.finish=false;			//初始化为未完成状态
			work.name="进程"+i;
			worksList.add(work);
		}
		//如果进程队列中还有进程没有分配给资源
		while(!worksList.isEmpty()){
			run();
		}
		System.out.println();
		for(int i=0;i<success.size();i++){
			System.out.println(success.get(i).name);
		}
	}
}
