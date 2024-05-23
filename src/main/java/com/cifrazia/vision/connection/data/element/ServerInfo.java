package com.cifrazia.vision.connection.data.element;

import java.util.List;

public class ServerInfo {

    private Description description;

    private Players players;

    private Version version;

    private ModInfo modinfo;

    private String favicon;

    public Description getDescription() {
        return description;
    }

    public Players getPlayers() {
        return players;
    }

    public class Description {
        private String text;

        public String getText() {
            return text;
        }
    }

    public class Players {
        private int max;
        private int online;
        private List<Player> sample;

        public int getMax() {
            return max;
        }

        public int getOnline() {
            return online;
        }

        public List<Player> getSample() {
            return sample;
        }
    }

    public class Player {
        private String name;
        private String id;
    }

    public class Version {
        private String name;
        private int protocol;
    }

    public class ModInfo {
        private String type;
        private List<Mod> modList;
    }

    public class Mod {
        private String modid;
        private String version;
    }

}
/*{
    "description": {
        "text": "Cifrazia.com"
    },
    "players": {
        "max": 75,
        "online": 0
    },
    "version": {
        "name": "Mohist 1.12.2",
        "protocol": 340
    },
    "modinfo": {
        "type": "FML",
        "modList": [
            {
                "modid": "minecraft",
                "version": "1.12.2"
            },
            {
                "modid": "mcp",
                "version": "9.42"
            },
            {
                "modid": "mohist",
                "version": "1.12.2-320"
            },
            {
                "modid": "FML",
                "version": "8.0.99.99"
            },
            {
                "modid": "forge",
                "version": "14.23.5.2860"
            },
            {
                "modid": "mixinbooter",
                "version": "8.8"
            },
            {
                "modid": "magiccore",
                "version": "@VERSION@"
            },
            {
                "modid": "vision",
                "version": "1.12"
            }
        ]
    }
}*/