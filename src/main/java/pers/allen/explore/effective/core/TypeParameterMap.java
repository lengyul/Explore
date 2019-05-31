package pers.allen.explore.effective.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 考虑使用类型安全的异构容器
 * @author lengyul
 * @date 2019年5月17日 下午7:52:06
 */
public class TypeParameterMap {
	
	/*
	 * 限制每个容器只能有固定数目的参数类型，以这种方式使用的Class对象称作类型令牌
	 */
	private Map<Class<?>, Object> favorites = new HashMap<>();
	
	public <T> void putFavorite(Class<T> type,T value) {
		favorites.put(Objects.requireNonNull(type), type.cast(value));
	}
	
	public <T> T getFavorite(Class<T> type) {
		return type.cast(favorites.get(type));
	}
	
}
