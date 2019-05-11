package me.will.tank;

/**
 * @author : zhenweiLi
 * @date :2019-04-21 09:30
 * DESC : new 出一个窗口
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        //  新建主窗口
        TankFrame tankFrame = new TankFrame();

        //  初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            Tank tank = new Tank(50 + i * 80, 200, Dir.D, Group.BAD, tankFrame);
            tankFrame.getTankList().add(tank);
        }

        ////  播放背景音乐
        //  new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        //  每隔 50 毫秒主窗口自动绘制
        while (true) {
            Thread.sleep(16);
            tankFrame.repaint();
        }

    }
}
