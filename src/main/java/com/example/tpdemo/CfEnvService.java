package com.example.tpdemo;

import io.pivotal.cfenv.core.CfApplication;
import io.pivotal.cfenv.core.CfEnv;
import io.pivotal.cfenv.core.CfService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//  Runs after the context is fully initialized

@Component
public class CfEnvService implements ApplicationRunner {
    private static final Logger log = LogManager.getLogger(CfEnvService.class);

    private CfEnvInfo cfEnvInfo;

    @Override
    public void run(ApplicationArguments args) {
        if (System.getenv("VCAP_APPLICATION") == null) {
            log.info("Not running on Cloud Foundry, skipping CF environment info retrieval");
            return;
        }

        CfEnv cfEnv = new CfEnv();
        CfApplication app = cfEnv.getApp();

        cfEnvInfo = new CfEnvInfo();
        cfEnvInfo.setApplicationName(app.getName());
        cfEnvInfo.setApplicationId(app.getApplicationId());
        cfEnvInfo.setInstanceIndex(String.valueOf(app.getInstanceIndex()));
        cfEnvInfo.setSpaceName(app.getSpaceName());
        cfEnvInfo.setSpaceId(app.getSpaceId());
        cfEnvInfo.setUris(app.getUris());

        List<CfEnvInfo.ServiceInfo> services = new ArrayList<>();
        for (CfService service : cfEnv.findAllServices()) {
            services.add(new CfEnvInfo.ServiceInfo(
                    service.getName(), service.getLabel(), service.getPlan()));
        }
        cfEnvInfo.setServices(services);

        log.info("Application Name: {}", cfEnvInfo.getApplicationName());
        log.info("Application ID: {}", cfEnvInfo.getApplicationId());
        log.info("Instance Index: {}", cfEnvInfo.getInstanceIndex());
        log.info("Space: {} ({})", cfEnvInfo.getSpaceName(), cfEnvInfo.getSpaceId());
        log.info("URIs: {}", cfEnvInfo.getUris());

        for (CfEnvInfo.ServiceInfo service : services) {
            log.info("Bound Service: {} [label={}, plan={}]",
                    service.getName(), service.getLabel(), service.getPlan());
        }
    }

    public CfEnvInfo getCfEnvInfo() {
        return cfEnvInfo;
    }
}
