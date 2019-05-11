package me.will.tank;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;

/**
 * @author : zhenweiLi
 * @date :2019-04-28 20:23
 * DESC : 坦克类
 */
public class Tank {

    /**
     * 坦克的宽度
     */
    private static final int TANK_WIDTH = ResManager.mGoodTankD.getWidth();

    /**
     * 坦克的高度
     */
    private static final int TANK_HEIGHT = ResManager.mGoodTankD.getHeight();

    /**
     * 坦克移动速度( 正向 )
     */
    private static final int TANK_SPEED = 2;

    /**
     * 坦克的 X 轴位置
     */
    private int mTankX;

    /**
     * 坦克的 Y 轴位置
     */
    private int mTankY;

    /**
     * 坦克方向
     */
    private Dir mTankDir;

    /**
     * 坦克运动状态
     */
    private boolean moving;

    /**
     * 游戏窗口
     */
    private TankFrame mTankFrame;

    /**
     * 获取坦克方向
     *
     * @return 方向枚举
     */
    public Dir getTankDir() {
        return mTankDir;
    }

    /**
     * 设置坦克方向
     *
     * @param dir 方向枚举
     */
    public void setTankDir(Dir dir) {
        this.mTankDir = dir;
    }

    /**
     * 坦克是否存活
     */
    private boolean mIsLiving = true;

    /**
     * 获取坦克是否移动的状态
     *
     * @return 是否运动
     */
    public boolean isMoving() {
        return moving;
    }

    /**
     * 随机数生成器
     */
    private Random mRandom = new Random();

    /**
     * 坦克的分组
     */
    private Group mGroup;

    /**
     * 设置坦克是否移动的状态
     *
     * @param moving 是否运动
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getTankX() {
        return mTankX;
    }

    public int getTankY() {
        return mTankY;
    }

    public int getTankWidth() {
        return TANK_WIDTH;
    }

    public int getTankHeight() {
        return TANK_HEIGHT;
    }

    public Group getGroup() {
        return mGroup;
    }

    public void setGroup(Group mGroup) {
        this.mGroup = mGroup;
    }

    /**
     * 无参构造
     */
    public Tank() {
    }

    /**
     * 构造方法
     *
     * @param tankX 初始化 X 轴
     * @param tankY 初始化 Y 轴
     * @param dir   初始化的方向
     */
    public Tank(int tankX, int tankY, Dir dir, Group group, TankFrame tankFrame) {
        this.mTankX = tankX;
        this.mTankY = tankY;
        this.mTankDir = dir;
        this.mGroup = group;
        this.mTankFrame = tankFrame;
        this.moving = (group == Group.BAD);
    }

    /**
     * 坦克绘制的方法
     *
     * @param g 画笔
     */
    public void paint(Graphics g) {

        if (!mIsLiving) {
            mTankFrame.getTankList().remove(this);
        }

        switch (mTankDir) {
            case U:
                g.drawImage(this.mGroup == Group.GOOD ? ResManager.mGoodTankU : ResManager.mBadTankU, mTankX, mTankY, null);
                break;
            case D:
                g.drawImage(this.mGroup == Group.GOOD ? ResManager.mGoodTankD : ResManager.mBadTankD, mTankX, mTankY, null);
                break;
            case L:
                g.drawImage(this.mGroup == Group.GOOD ? ResManager.mGoodTankL : ResManager.mBadTankL, mTankX, mTankY, null);
                break;
            case R:
                g.drawImage(this.mGroup == Group.GOOD ? ResManager.mGoodTankR : ResManager.mBadTankR, mTankX, mTankY, null);
                break;
            case LU:
                g.drawImage(this.mGroup == Group.GOOD ? ResManager.mGoodTankLU : ResManager.mBadTankLU, mTankX, mTankY, null);
                break;
            case LD:
                g.drawImage(this.mGroup == Group.GOOD ? ResManager.mGoodTankLD : ResManager.mBadTankLD, mTankX, mTankY, null);
                break;
            case RU:
                g.drawImage(this.mGroup == Group.GOOD ? ResManager.mGoodTankRU : ResManager.mBadTankRU, mTankX, mTankY, null);
                break;
            case RD:
                g.drawImage(this.mGroup == Group.GOOD ? ResManager.mGoodTankRD : ResManager.mBadTankRD, mTankX, mTankY, null);
                break;
            default:
                break;
        }
        move();
    }

    /**
     * 坦克移动的方法
     */
    private void move() {

        if (!isMoving()) {
            return;
        }

        switch (mTankDir) {
            case U:
                mTankY -= TANK_SPEED;
                break;
            case D:
                mTankY += TANK_SPEED;
                break;
            case L:
                mTankX -= TANK_SPEED;
                break;
            case R:
                mTankX += TANK_SPEED;
                break;
            case LU:
                mTankX -= TANK_SPEED - 1;
                mTankY -= TANK_SPEED - 1;
                break;
            case LD:
                mTankX -= TANK_SPEED - 1;
                mTankY += TANK_SPEED - 1;
                break;
            case RU:
                mTankX += TANK_SPEED - 1;
                mTankY -= TANK_SPEED - 1;
                break;
            case RD:
                mTankX += TANK_SPEED - 1;
                mTankY += TANK_SPEED - 1;
                break;
            default:
                break;
        }

        //  敌方坦克随机开火
        if (mGroup == Group.BAD && mRandom.nextInt(100) > 95) {
            this.fire();
        }

        //  敌方坦克随机移动
        if (mGroup == Group.BAD && mRandom.nextInt(100) > 95) {
            this.mTankDir = Dir.values()[mRandom.nextInt(4)];
        }

        //  边界检测
        boundCheck();
    }

    /**
     * 边界检测
     */
    private void boundCheck() {

        if (this.mTankX < 0) {
            this.mTankX = 0;
        }

        if (this.mTankY < 20) {
            this.mTankY = 20;
        }

        if (this.mTankX > TankFrame.GAME_WIDTH - Tank.TANK_WIDTH) {
            this.mTankX = TankFrame.GAME_WIDTH - Tank.TANK_WIDTH;
        }

        if (this.mTankY > TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT) {
            this.mTankY = TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT;
        }

    }

    /**
     * 发射子弹
     */
    public void fire() {
        List<Bullet> bulletList = mTankFrame.getBulletList();
        Bullet bullet;
        switch (mTankDir) {
            case U:
                bullet = new Bullet(this.mTankX + (TANK_WIDTH >> 1) - (Bullet.BULLET_WIDTH >> 1), this.mTankY - Bullet.BULLET_HEIGHT, this.mTankDir, this.mGroup, this.mTankFrame);
                break;
            case D:
                bullet = new Bullet(this.mTankX + (TANK_WIDTH >> 1) - (Bullet.BULLET_WIDTH >> 1), this.mTankY + TANK_HEIGHT, this.mTankDir, this.mGroup, this.mTankFrame);
                break;
            case L:
                bullet = new Bullet(this.mTankX - Bullet.BULLET_WIDTH, this.mTankY + (TANK_HEIGHT >> 1) - (Bullet.BULLET_HEIGHT >> 1), this.mTankDir, this.mGroup, this.mTankFrame);
                break;
            case R:
                bullet = new Bullet(this.mTankX + TANK_WIDTH, this.mTankY + (TANK_HEIGHT >> 1) - (Bullet.BULLET_HEIGHT >> 1), this.mTankDir, this.mGroup, this.mTankFrame);
                break;
            case LU:
                bullet = new Bullet(this.mTankX - Bullet.BULLET_WIDTH, this.mTankY - Bullet.BULLET_WIDTH, this.mTankDir, this.mGroup, this.mTankFrame);
                break;
            case LD:
                bullet = new Bullet(this.mTankX - TANK_WIDTH + Bullet.BULLET_WIDTH, this.mTankY + TANK_WIDTH, this.mTankDir, this.mGroup, this.mTankFrame);
                break;
            case RU:
                bullet = new Bullet(this.mTankX + TANK_WIDTH, this.mTankY - (Bullet.BULLET_WIDTH >> 1), this.mTankDir, this.mGroup, this.mTankFrame);
                break;
            case RD:
                bullet = new Bullet(this.mTankX + TANK_WIDTH, this.mTankY + TANK_HEIGHT, this.mTankDir, this.mGroup, this.mTankFrame);
                break;
            default:
                bullet = null;
                break;
        }
        bulletList.add(bullet);

        if (this.mGroup == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    /**
     * 坦克报废
     */
    public void die() {
        this.mIsLiving = false;
    }
}
