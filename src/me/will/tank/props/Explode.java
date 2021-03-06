package me.will.tank.props;

import me.will.tank.utils.Audio;
import me.will.tank.main.GameModel;
import me.will.tank.manager.ResManager;

import java.awt.Graphics;

/**
 * @author : zhenweiLi
 * @date :2019-05-11 20:10
 * DESC : 爆炸类
 */
public class Explode extends BaseGameProps {

    /**
     * 爆炸的宽度
     */
    public static final int EXPLODE_WIDTH = ResManager.explodes[0].getWidth();

    /**
     * 爆炸的高度
     */
    public static final int EXPLODE_HEIGHT = ResManager.explodes[0].getHeight();

    /**
     * 爆炸的 X 轴位置
     */
    private int mExplodeX;

    /**
     * 爆炸的 Y 轴位置
     */
    private int mExplodeY;

    /**
     * 当前爆炸的步骤数
     */
    private int mStep;

    public Explode() {
    }

    public Explode(int mExplodeX, int mExplodeY) {
        this.mExplodeX = mExplodeX;
        this.mExplodeY = mExplodeY;
        GameModel.getInstance().add(this);

        //  播放爆炸音效
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    /**
     * 绘制爆炸
     *
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(ResManager.explodes[mStep++], mExplodeX, mExplodeY, null);
        if (mStep >= ResManager.explodes.length) {
            GameModel.getInstance().remove(this);
        }
    }

}
