package com.ypy.springcloud.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * 自定义负载均衡策略
 *  * （1）依旧轮询策略，但是加上新需求，
 *  * （2）每个服务器要求被调用5次。
 *  * （3）也即以前是每台机器一次，现在是每台机器5次
 *
 */
public class RandomRule_YPY extends AbstractLoadBalancerRule {

    private int total = 0;// 总共被调用的次数，目前要求每台被调用5次

    private int currentIndex = 0;// 当前提供服务的机器号

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            // 可用的服务列表
            List<Server> upList = lb.getReachableServers();

            // 所有的服务列表
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();

            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

            // 修改部分
            if (total < 5) {
                server = upList.get(currentIndex);
                total ++;
            } else {
                total = 0;
                currentIndex ++;
                if (currentIndex >= upList.size()) {
                    currentIndex = 0;
                }
            }
            // 修改部分

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;
    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
