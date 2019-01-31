package pers.allen.explore.pattern.decorator.i;

public class SourceBeforePrint implements Sourcable{

	Sourcable sourcable;
	
	public SourceBeforePrint(Sourcable sourcable){
		this.sourcable = sourcable;
	}
	
	@Override
	public void print() {
		System.out.println("sourceBeforePrint print.....");
		sourcable.print();
	}

	@Override
	public String print(String str) {
		
		return str;
	}

}
