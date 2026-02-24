const { createApp } = Vue;

createApp({
    data() {
        return {
            message: '',
            loading: false,
            error: null,
            cfenvInfo: null,
            cfenvLoading: false,
            cfenvError: null,
            actuatorInfo: null,
            actuatorLoading: false,
            actuatorError: null
        };
    },
    mounted() {
        this.fetchHello();
        this.fetchCfEnv();
        this.fetchActuatorInfo();
    },
    methods: {
        async fetchHello() {
            this.loading = true;
            this.error = null;
            try {
                const response = await fetch('/hello');
                if (!response.ok) {
                    throw new Error(`HTTP ${response.status}`);
                }
                this.message = await response.text();
            } catch (err) {
                this.error = 'Failed to reach /hello endpoint: ' + err.message;
            } finally {
                this.loading = false;
            }
        },
        async fetchCfEnv() {
            this.cfenvLoading = true;
            this.cfenvError = null;
            try {
                const response = await fetch('/cfenv');
                if (response.status === 404) {
                    this.cfenvInfo = null;
                    return;
                }
                if (!response.ok) {
                    throw new Error(`HTTP ${response.status}`);
                }
                this.cfenvInfo = await response.json();
            } catch (err) {
                this.cfenvError = 'Failed to reach /cfenv endpoint: ' + err.message;
            } finally {
                this.cfenvLoading = false;
            }
        },
        async fetchActuatorInfo() {
            this.actuatorLoading = true;
            this.actuatorError = null;
            try {
                const response = await fetch('/actuator/info');
                if (!response.ok) {
                    throw new Error(`HTTP ${response.status}`);
                }
                this.actuatorInfo = await response.json();
            } catch (err) {
                this.actuatorError = 'Failed to reach /actuator/info endpoint: ' + err.message;
            } finally {
                this.actuatorLoading = false;
            }
        }
    }
}).mount('#app');