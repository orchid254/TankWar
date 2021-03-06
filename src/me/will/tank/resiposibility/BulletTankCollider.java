package me.will.tank.resiposibility;

import me.will.tank.manager.ResManager;
import me.will.tank.props.BaseGameProps;
import me.will.tank.props.Bullet;
import me.will.tank.props.Explode;
import me.will.tank.props.Tank;

/**
 * @author : zhenweiLi
 * @date :2020-03-22 07:40
 * DESC : 子弹和坦克的碰撞检测器
 */
public class BulletTankCollider implements Collider {

    @Override
    public void collide(BaseGameProps o1, BaseGameProps o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            collideWith((Bullet) o1, (Tank) o2);
        }
    }

    /**
     * 检测子弹和坦克是否发生碰撞的方法
     */
    private void collideWith(Bullet bullet, Tank tank) {

        //  默认不开启队友伤害
        if (bullet.getGroup() == tank.getGroup()) {
            return;
        }

        //  子弹死亡则无需检测
        if (!bullet.isLiving()) {
            return;
        }

        //  碰撞检测
        if (bullet.getRectangle().intersects(tank.getRectangle())) {
            bullet.die();
            tank.die();
            //  显示爆炸效果
            int explodeX = tank.getRectangle().x + ResManager.getTankWidth() / 2 - Explode.EXPLODE_WIDTH / 2;
            int explodeY = tank.getRectangle().y + ResManager.getTankHeight() / 2 - Explode.EXPLODE_HEIGHT / 2;
            new Explode(explodeX, explodeY);
        }
    }

}
