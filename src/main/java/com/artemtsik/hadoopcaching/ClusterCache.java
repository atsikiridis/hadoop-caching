package com.artemtsik.hadoopcaching;

public interface ClusterCache<K,V> {

	public void set(K key, V value, int expiry);

	public V get(K key);

	public void add(K key, V value, int expiry);

	public void replace(K key, V value, int expiry);

	public void delete(K key, int blockForSeconds);

	public void incr(K key, V value);

	public void decr(K key, V value);

	public void flushAll();
}
