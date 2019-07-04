import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RwLock {
    private volatile Map<String,String> map=new HashMap<>();
    ReentrantReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    public void put(String key,String value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入数据");
            map.put(key,value);
            TimeUnit.MICROSECONDS.sleep(300);
            System.out.println(Thread.currentThread().getName()+"\t 已写完数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在读取数据");
            map.get(key);
            TimeUnit.MICROSECONDS.sleep(200);
            System.out.println(Thread.currentThread().getName()+"\t 已读完数据");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

class TestRWlock{
    public static void main(String[] args) {
        RwLock rwLock=new RwLock();
        for (int i = 1; i <=5; i++) {
            final int temp=i;
            new Thread(()->{
                rwLock.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <=5; i++) {
            final int temp=i;
            new Thread(()->{
                rwLock.get(temp+"");
            },String.valueOf(i)).start();
        }

    }

}
