import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class DataResource {
    private volatile boolean flag=true;
    private BlockingQueue<String> blockingQueue=null;
    private AtomicReference<String> atomicReference=new AtomicReference<>();

    public DataResource(BlockingQueue blockingQueue){
        this.blockingQueue=blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public void prodMsg() throws Exception{
        atomicReference.set("abc");
        boolean bq;
        while (flag){
            bq= blockingQueue.offer(atomicReference.get(),2L, TimeUnit.SECONDS);
            if(bq){
                System.out.println(Thread.currentThread().getName()+"生产消息成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"生产消息失败");
            }
          TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"大老板叫停,生产结束");
    }
    public void proComser() throws Exception{
        String data=null;
        while (flag){
          data= blockingQueue.poll(2L,TimeUnit.SECONDS);
          if(null==data||data.equalsIgnoreCase("")) {
              flag = false;
              System.out.println(Thread.currentThread().getName() + "2秒已过消费数据失败");
              return;
          }
          System.out.println(Thread.currentThread().getName()+"消费数据成功");
        }
    }
    public void stop(){
        this.flag=false;
    }
}
class Test {
    public static void main(String[] args) throws InterruptedException {
        DataResource dataResource=new DataResource(new ArrayBlockingQueue(3));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"生产线程已启动");
            try {

                dataResource.prodMsg();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"消费线程已启动");
            System.out.println();
            System.out.println();
            try {

                dataResource.proComser();
                System.out.println();
                System.out.println();

            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();
        TimeUnit.SECONDS.sleep(5);

        System.out.println("5秒后mian线程停止生产和消息了");
        dataResource.stop();
    }
}
