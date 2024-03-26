package com.github.bearboy.ribbon.configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightUpTimeIRule extends AbstractLoadBalancerRule {
    private static final int warmup = 1000 * 10;
    private final Random random = new Random();

    private List<Integer> weights = new ArrayList<>();

    static int calculateWarmupWeight(int uptime, int warmup, int weight) {
        int ww = (int) (uptime / ((float) warmup / weight));
        return ww < 1 ? 1 : (Math.min(ww, weight));
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }

    @Override
    public Server choose(Object key) {
        ILoadBalancer lb = getLoadBalancer();
        if (lb == null) {
            return null;
        }
        Server server = null;
        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();

            if (serverCount == 0) {
                return null;
            }
            int serverIndex = 0;

            // 计算权重
            weights = calWeights(allList);
            double maxTotalWeight = weights.isEmpty() ? 0 : weights.get(weights.size() - 1);

            // generate a random weight between 0 (inclusive) to maxTotalWeight (exclusive)
            double randomWeight = random.nextDouble() * maxTotalWeight;
            // pick the server index based on the randomIndex
            int n = 0;
            for (Integer d : weights) {
                if (d >= randomWeight) {
                    serverIndex = n;
                    break;
                } else {
                    n++;
                }
            }

            server = allList.get(serverIndex);

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Next.
            server = null;
        }
        return server;
    }

    private static List<Integer> calWeights(List<Server> allList) {
        List<Integer> finalWeights = new ArrayList<Integer>();
        int weightSoFar = 0;
        for (Server svr : allList) {
            if (svr instanceof DiscoveryEnabledServer) {
                DiscoveryEnabledServer discoveryServer = (DiscoveryEnabledServer) svr;
                Long timestamp = discoveryServer.getInstanceInfo().getLeaseInfo().getServiceUpTimestamp();
                int weight = 1;
                if (timestamp > 0L) {
                    long uptime = System.currentTimeMillis() - timestamp;
                    if (uptime > 0 && uptime < warmup) {
                        weight = calculateWarmupWeight((int) uptime, warmup, 50);
                    }
                }
                weightSoFar += weight;
                finalWeights.add(weightSoFar);
            }
        }
        return finalWeights;
    }
}
