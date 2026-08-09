package dev.anvilcraft.ponder.api.instruction;

public interface Interpolation {
    double progress(double deltaTime);

    double duration(double journey);

    /**
     * 创建一个匀速插值器
     *
     * @param speed 速度值（正数），单位：距离/时间
     * @return 返回一个Interpolation接口的实现
     */
    static Interpolation linear(double speed) {
        return new Interpolation() {
            @Override
            public double progress(double deltaTime) {
                // 匀速运动：位移与时间成正比
                return deltaTime;
            }

            @Override
            public double duration(double journey) {
                // t = s / v
                return journey / speed;
            }
        };
    }

    /**
     * 创建一个缓入插值器（匀加速）
     *
     * @param acceleration 加速度值（正数），单位：距离/时间²
     * @return 返回一个Interpolation接口的实现
     */
    static Interpolation easeIn(double acceleration) {
        return new Interpolation() {
            @Override
            public double progress(double deltaTime) {
                // 匀加速运动位移比例：s(t)/S = f²
                // 其中 f = t/T
                return deltaTime * deltaTime;
            }

            @Override
            public double duration(double journey) {
                // s = ½·a·T² => T = sqrt(2s/a)
                return Math.sqrt(2 * journey / acceleration);
            }
        };
    }

    /**
     * 创建一个重力下落插值器（无终端速度限制）
     *
     * @return 返回一个Interpolation接口的实现
     */
    static Interpolation gravity() {
        return Interpolation.easeIn(0.08);  // 原版重力加速度 0.08 方块/tick²
    }

    /**
     * 创建一个缓出插值器（匀减速）
     *
     * @param deceleration 减速度值（正数），单位：距离/时间²
     * @return 返回一个Interpolation接口的实现
     */
    static Interpolation easeOut(double deceleration) {
        return new Interpolation() {
            @Override
            public double progress(double deltaTime) {
                // 匀减速运动位移比例：s(t)/S = 2f - f²
                // 其中 f = t/T
                return 2 * deltaTime - deltaTime * deltaTime;
            }

            @Override
            public double duration(double journey) {
                // 匀减速：s = ½·a·T² (初速度 v0 = a·T，末速度为0)
                // T = sqrt(2s/a)
                return Math.sqrt(2 * journey / deceleration);
            }
        };
    }

    /**
     * 创建一个缓入缓出插值器
     *
     * @param acceleration 加速度值（正数），单位：距离/时间²
     * @return 返回一个Interpolation接口的实现
     */
    static Interpolation easeInOut(double acceleration) {
        return new Interpolation() {
            @Override
            public double progress(double fraction) {
                // 分为两段：
                // 前半段（f ∈ [0, 0.5]）：匀加速，位移比例 = 2f²
                // 后半段（f ∈ [0.5, 1]）：匀减速，位移比例 = 1 - 2(1-f)²
                if (fraction < 0.5) {
                    return 2 * fraction * fraction;
                } else {
                    return 1 - 2 * (1 - fraction) * (1 - fraction);
                }
            }

            @Override
            public double duration(double journey) {
                // 匀加速段位移 = 匀减速段位移 = journey/2
                // 单段时间 T_half = sqrt(2 * (journey/2) / a) = sqrt(journey / a)
                // 总时间 = 2 * T_half
                return 2 * Math.sqrt(journey / acceleration);
            }
        };
    }
}
