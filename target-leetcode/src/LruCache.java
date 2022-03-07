import java.util.*;

/**
 * @author gzf
 */
public class LruCache<K,V> extends LinkedHashMap<K,V> {

    private static final long serialVersionUID = -5278798649845957343L;
    private final int CACHE_SIZE;

    public LruCache(int cacheSize) {
        //true表示让LinkedHashMap按照访问顺序来排序，最近访问的在头部，最老访问的在尾部。
        super((int)Math.ceil(cacheSize/0.75)+1,0.75f,true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        //当map中的数据量大于指定缓存个数时，就自动删除最老的数据
        return size() > CACHE_SIZE;
    }
}
