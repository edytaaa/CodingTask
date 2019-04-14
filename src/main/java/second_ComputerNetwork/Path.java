package second_ComputerNetwork;

import java.util.ArrayList;
import java.util.List;

class Path {
    private long ping;
    private List<String> servers;

    public Path(long ping, List<String> servers) {
        this.ping = ping;
        this.servers = servers;
    }

    public Path() {
        ping = 0;
        servers = new ArrayList<>();
    }

    public long getPing() {
        return ping;
    }

    public List<String> getServers() {
        return servers;
    }

    public void addServer(String ip, long ping) {
        this.servers.add(ip);
        this.ping += ping;
    }
}
