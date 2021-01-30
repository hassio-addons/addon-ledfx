server {
    listen {{ .interface }}:{{ .port }} default_server;

    include /etc/nginx/includes/server_params.conf;
    include /etc/nginx/includes/proxy_params.conf;

    location / {
        allow   172.30.32.2;
        deny    all;
        sub_filter_types * ;
        sub_filter_once off;
        sub_filter '/static/' '{{ .ingress_entry }}/static/';
        sub_filter '/api/websocket' '{{ .ingress_entry }}/api/websocket';
        sub_filter '/api/log' '{{ .ingress_entry }}/api/log';
        sub_filter 'baseURL:"/api"' 'baseURL:"{{ .ingress_entry }}/api"';
        proxy_pass http://backend;
    }
}