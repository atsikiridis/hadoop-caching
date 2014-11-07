package com.artemtsik.hadoopcaching;

public interface ClusterCache<K,V> {

	public void set(final K key, final V value, final int expiry);

	public V get(final K key);

	public void add(final K key, final V value, final int expiry);

	public void replace(final K key, final V value, final int expiry);

	public void delete( final K key, final int blockForSeconds);

	public void incr(final K key, final int by);

	public void decr(final K key, final int by);

	public void flushAll();
}
