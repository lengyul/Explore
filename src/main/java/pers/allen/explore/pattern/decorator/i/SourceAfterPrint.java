package pers.allen.explore.pattern.decorator.i;

public class SourceAfterPrint implements Sourcable{

	Sourcable sourcable;
	
	public SourceAfterPrint(Sourcable sourcable){
		this.sourcable = sourcable;
	}
	
	@Override
	public void print() {
		sourcable.print();
		System.out.println("sourceAfterPrint print.....");
	}

	@Override
	public String print(String str) {
		
		return str;
	}

}
