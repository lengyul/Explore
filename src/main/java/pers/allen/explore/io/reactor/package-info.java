/**
 * 《Scalable IO in Java》笔记
 * http://gee.cs.oswego.edu/dl/cpjslides/nio.pdf
 * 
 * 
 * 网络处理程序基本的处理过程：
 * Read request  读取数据
 * Decode request 数据解码
 * Process service 处理业务
 * Encode reply   数据编码
 * Send reply 	发送数据
 * 
 *  _____________________________________________
 * | read -> decode -> compute -> encode -> send |
 * |___________________handler___________________|
 * 
 * 文件传输： 磁盘 -> 内核缓冲区 -> 用户缓冲区 -> socket缓冲区 
 */
/**
 * @author lengyul
 * @date 2019年5月10日 上午11:24:36
 */
package pers.allen.explore.io.reactor;
