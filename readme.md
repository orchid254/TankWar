# 坦克大战

## 编码规范

> 参考《阿里巴巴Java开发手册(华山版)》

阿里巴巴 Java 代码静态检查插件。

## 面向对象

### 游戏道具 props

所有游戏道具继承自 BaseGameProps 类。

#### 坦克 tank

- 英雄坦克
- 敌方坦克

#### 子弹 bullet

- 默认开火方案
- 安全开火方案
- 超级开火方案

#### 爆炸 explode

帧动画

#### 墙壁 wall

子弹不可穿墙。

## 设计模式

> v1.0.0 面向对象版本完成后，加入设计模式。
> 对应坦克大战一班视频第五节课开始。

### 单例模式

PropertyManager 类修改为单例模式。

### 策略模式

1. Tank 类修改开火策略。

2. 游戏物体之间互相碰撞检测策略。

### 门面模式

每添加一个新的游戏物体，现在需要和所有已经存在的物体打交道，非常麻烦。

TankFrame 应该只负责显示，而不需要和具体的游戏物体打交道。

解决方案 ：定义 GameModel 类，作为所有游戏物体的父类。

GameModel 分离了 View 和 Model，使得各种逻辑独立，互不影响。

### 调停者模式

抽象出游戏物体的父类 BaseGameProps，使得各个游戏道具之间互不影响。

### 责任链模式

由于需要进行碰撞检测的物体非常多，如果每个策略都单独定义，代码会非常复杂和冗余。

考虑使用责任链模式将所有碰撞检测器进行链接，依次进行碰撞检测。

### 装饰器模式

慎用 ！慎用 ！慎用 ！

装饰器模式将导致原来的游戏道具类型被封装，从而导致很多 BUG ：

1. 内存占用率高问题

因为 RectBounderDecorator 类每次都是 new 出来的，内存地址值
都不相同，所以在将被其装饰的 tank 实例从集合中移除的时候，会找不到
原来的实例对象。每次移除都失败的后果就是实例不断增加，内存占用极高。

2. 碰撞检测失效问题

当 tank 被 RectBounderDecorator 类修饰以后，进行碰撞检测时，
instanceof() 方法将无法正确识别 tank 类，因为返回的类型已经变更为
RectBounderDecorator 类，从而导致程序异常。

现在的使用方式 :

现在程序中只对墙壁使用了 RectBounderDecorator 装饰，原因 ：

1. 墙壁只需加载，在游戏结束前无需移除，不会增加内存负担。
2. 在进行碰撞检测时，可以提前获取到被装饰的对象，再传入碰撞检测
责任链中进行判断，从而避免发生穿墙等问题。

### 观察者模式

> 对应坦克大战第十节课。

观察者只和事件打交道，不直接和事件源打交道，降低耦合。
特殊情况下，观察者也可以通过事件获取到源对象进行处理。









