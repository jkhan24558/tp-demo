package com.example.tpdemo;

import java.util.List;

public class CfEnvInfo {

    private String applicationName;
    private String applicationId;
    private String instanceIndex;
    private String spaceName;
    private String spaceId;
    private List<String> uris;
    private List<ServiceInfo> services;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getInstanceIndex() {
        return instanceIndex;
    }

    public void setInstanceIndex(String instanceIndex) {
        this.instanceIndex = instanceIndex;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public List<String> getUris() {
        return uris;
    }

    public void setUris(List<String> uris) {
        this.uris = uris;
    }

    public List<ServiceInfo> getServices() {
        return services;
    }

    public void setServices(List<ServiceInfo> services) {
        this.services = services;
    }

    public static class ServiceInfo {
        private String name;
        private String label;
        private String plan;

        public ServiceInfo(String name, String label, String plan) {
            this.name = name;
            this.label = label;
            this.plan = plan;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getPlan() {
            return plan;
        }

        public void setPlan(String plan) {
            this.plan = plan;
        }
    }
}