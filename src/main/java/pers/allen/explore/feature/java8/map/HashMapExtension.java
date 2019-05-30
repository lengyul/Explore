package pers.allen.explore.feature.java8.map;

public class HashMapExtension {

	/*
	 * 存储结构：
	 * HashMap --> （hash）Array --> LinkedList 
	 * 
	 * 1.8
	 * Map --> （hash）Array --> LinkedList >= 8 
	 * 					    --> RedBlackTree
	 * 
	 * 扩展方法：
	 * getOrDefault(Object key, V defaultValue)
	 * putIfAbsent(K key, V value)
	 * remove(Object key, Object value)
	 * replace(K key, V oldValue, V newValue)
	 * replace(K key, V value)
	 * computeIfAbsent(K key,Function<? super K, ? extends V> mappingFunction)
	 * computeIfPresent(K key,BiFunction<? super K, ? super V, ? extends V> remappingFunction)
	 * compute(K key,BiFunction<? super K, ? super V, ? extends V> remappingFunction)
	 * merge(K key, V value,BiFunction<? super V, ? super V, ? extends V> remappingFunction)
	 * forEach(BiConsumer<? super K, ? super V> action)
	 * replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
	 * 
	 * 
	 */
	
	
	
}
