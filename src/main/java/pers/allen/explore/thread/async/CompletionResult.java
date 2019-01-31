package pers.allen.explore.thread.async;

public interface CompletionResult<V,T> {
	
	void completed(V result,T t);
	
}
