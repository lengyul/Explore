package pers.allen.explore.pattern.adapter;

public class OutputAdapterImpl extends InputServiceImpl implements OutputService {

	@Override
	public void print(String str) {
		String result = this.toUpperCaseStr(str);
		System.out.println(result);
	}

}
