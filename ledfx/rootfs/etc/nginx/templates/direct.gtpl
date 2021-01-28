server {
    {{ if not .ssl }}
    listen 80 default_server;
    {{ else }}
    listen 80 default_server ssl http2;
    {{ end }}

    include /etc/nginx/includes/server_params.conf;
    include /etc/nginx/includes/proxy_params.conf;

    {{ if .ssl }}
    include /etc/nginx/includes/ssl_params.conf;

    ssl_certificate /ssl/{{ .certfile }};
    ssl_certificate_key /ssl/{{ .keyfile }};
    {{ end }}

    location = /authentication {
        internal;
        proxy_pass              http://supervisor/auth;
        proxy_pass_request_body off;
        proxy_set_header        Content-Length "";
        proxy_set_header        X-Supervisor-Token "{{ env "SUPERVISOR_TOKEN" }}";
    }

    location  /api/websocket {
        proxy_pass http://backend;
    }

    location / {
        {{ if .ssl }}
        proxy_set_header Accept-Encoding "";
    	sub_filter 'ws://' 'wss://';
    	sub_filter_types application/javascript;
        sub_filter_once off;
        {{ end }}
        auth_request /authentication;
        auth_request_set $auth_status $upstream_status;
        proxy_pass http://backend;
    }
}