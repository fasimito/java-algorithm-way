# Algorithm of JDK's Specifications

This is introduce you many JDK's specifications, mapped with some code implementations.

## HashMap and Hashtable说明

Hashtable put源码如下：
```
public synchronized V put(K key, V value) {
  // Make sure the value is not null  
  if (value == null) { 
    throw new NullPointerException();  
  }  
  // Makes sure the key is not already in the hashtable.  
  Entry tab[] = table;  
  int hash = key.hashCode();  
  int index = (hash & 0x7FFFFFFF) % tab.length;  
  for (Entry e = tab[index]; e != null; e = e.next) {  
    if ((e.hash == hash) && e.key.equals(key)) {  
      V old = e.value;  
      e.value = value;  
      return old;  
    }  
  }  
  modCount++;  
  if (count >= threshold) {  
    // Rehash the table if the threshold is exceeded  
    rehash();  
    tab = table;  
    index = (hash & 0x7FFFFFFF) % tab.length;  
  }  
  // Creates the new entry.  
  Entry e = tab[index];  
  tab[index] = new Entry(hash, key, value, e);  
  count++;  
  return null;  
} 
```
通过源码分析可以知道Hashtable有如下特性：
1. put方法是同步的，操作是线程安全的；
2. Key和Value都不能空，如果为null，编译可以通过，运行会抛NullPointerException。 value为空是指明了抛，而key为空是因为需要调用key.hashCode(),导致抛空指针异常；
3. Hashtable则保留了contains，containsValue和containsKey三个方法，其中contains和containsValue功能相同。

关于HashMap,分析源码

```
public V put(K key, V value) { 
  if (key == null) 
    return putForNullKey(value);  
  int hash = hash(key.hashCode());  
  int i = indexFor(hash, table.length);  
  for (Entry e = table[i]; e != null; e = e.next) {  
    Object k;  
    if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {  
      V oldValue = e.value;  
      e.value = value;  
      e.recordAccess(this);  
      return oldValue;  
    }  
  }  
  modCount++;  
  addEntry(hash, key, value, i);   
  return null;  
}  
```
得出下面结论：
1. 方法是非同步的，即非线程安全的，多线程情况下需要对资源加锁处理；
2. 方法允许key和value为null;
3. HashMap把Hashtable的contains方法去掉了，改成containsValue和containsKey
4. HashMap中，null可以作为键，这样的键只有一个；可以有一个或多个键所对应的值为null。当get()方法返回null值时，可能是 HashMap中没有该键，也可能使该键所对应的值为null。因此，在HashMap中不能由get()方法来判断HashMap中是否存在某个键， 而应该用containsKey()方法来判断

另外两者之间还有一些区别：
1. HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator迭代器不是fail-fast的。所以当有其它线程改变了HashMap的结构（增加或者移除元素），将会抛出ConcurrentModificationException，但迭代器本身的remove()方法移除元素则不会抛出ConcurrentModificationException异常。但这并不是一个一定发生的行为，要看JVM。这条同样也是Enumeration和Iterator的区别;
2. Hashtable中hash数组默认大小是11，增加的方式是 old*2+1。HashMap中hash数组的默认大小是16，而且一定是2的指数;
3. 两者通过hash值散列到hash表的算法不一样,
Hashtable是古老的除留余数法，直接使用hashcode
```
int hash = key.hashCode();  
int index = (hash & 0x7FFFFFFF) % tab.length; 
```
而HashMap是强制容量为2的幂，重新根据hashcode计算hash值，在使用hash 位与 （hash表长度 – 1），也等价取膜，但更加高效，取得的位置更加分散，偶数，奇数保证了都会分散到。前者就不能保证
```
int hash = hash(k);  
int i = indexFor(hash, table.length);  
static int hash(Object x) {  
　　int h = x.hashCode();  
　　h += ~(h << 9);  
　　h ^= (h >>> 14);  
　　h += (h << 4);  
　　h ^= (h >>> 10);  
　　return h;  
}
static int indexFor(int h， int length) {  
　　return h & (length-1);  
```
