package me.will.tank.resiposibility;

import me.will.tank.props.BaseGameProps;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : zhenweiLi
 * @date :2020-03-22 08:38
 * DESC : 碰撞检测责任链
 */
public class ColliderChain implements Collider {

    private List<Collider> mColliderList = new ArrayList<>();

    public ColliderChain() {
    }

    public ColliderChain add(Collider collider) {
        mColliderList.add(collider);
        return this;
    }

    @Override
    public void collide(BaseGameProps o1, BaseGameProps o2) {
        for (int i = 0; i < mColliderList.size(); i++) {
            Collider collider = mColliderList.get(i);
            collider.collide(o1, o2);
        }
    }
}
