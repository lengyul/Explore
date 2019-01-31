package pers.allen.explore.pattern.command;

/**
 * 所有的命令对象都实现此接口
 * @author lengyul
 *
 */
public interface Command {
	
	void execute();
	
	void undo();
}
