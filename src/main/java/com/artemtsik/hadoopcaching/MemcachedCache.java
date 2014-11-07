package com.artemtsik.hadoopcaching;

import net.spy.memcached.MemcachedClient;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MemcachedCache<K,V> implements ClusterCache<K,V> {

	private final MemcachedClient memcachedClient;

	public MemcachedCache() {
		// TODO configurable socket address
		try {
			this.memcachedClient = new MemcachedClient(new InetSocketAddress("localhost", 11211));
		}
		catch (IOException e) {
			// TODO throw custom catchable exception! cannot instantiate, tell why.
			throw new RuntimeException();
		}
	}

	@Override
	public void set(final K key, final V value, final int expiry) {
		this.memcachedClient.set(key.toString(), expiry, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public V get(final K key) {
		try {
			return (V) memcachedClient.get(key.toString());
		}
		catch (ClassCastException e) {
			throw new RuntimeException("Expected a different value type."); // TODO throw wrong type exception
		}
	}

	@Override
	public void add(final K key, final V value, final int expiry) {
		memcachedClient.add((String) key, expiry, value);
	}

	@Override
	public void replace(final K key, final V value, final int expiry) {
		memcachedClient.replace((String) key, expiry, value);
	}

	@Override
	public void delete(final K key, final int blockForSeconds) {
		memcachedClient.delete((String) key);
	}

	@Override
	public void incr(final K key, final int by) {
		memcachedClient.incr((String ) key, by);
	}

	@Override
	public void decr(final K key, final int by) {
		memcachedClient.decr((String) key, by);
	}

	@Override
	public void flushAll() {
		memcachedClient.flush();
	}
}
