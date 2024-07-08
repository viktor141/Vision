package com.cifrazia.vision.connection.data.element;


import com.cifrazia.vision.connection.data.element.privilege.PrivilegeRole;
import com.cifrazia.vision.connection.data.element.server.Server;

import java.util.List;

public class CifraziaUser {
    private int id;
    private String uuid;
    private String username;
    private boolean is_secured;
    private String skin;
    private String cape;
    private String email;
    private List<Group> groups;
    private Restrictions restrictions;
    private boolean two_step;
    private long gold_balance;
    private String invited_by;

    public class Group {
        private ModPack modpack;
        private Server server;
        private PrivilegeRole role;
        private long expired_at;
        private long priority;

        public ModPack getModpack() {
            return modpack;
        }

        public Server getServer() {
            return server;
        }

        public PrivilegeRole getRole() {
            return role;
        }

        public long getExpired_at() {
            return expired_at;
        }
    }

    public class Restrictions {
        private Restriction ban;
        private Restriction mute;

        public class Restriction {
            private float created_at;
            private float expired_at;
            private float updated_at;
            private String reason;
            private String type;
            private String player_uuid;
            private String player_name;
            private String issuer_uuid;
            private String issuer_name;

            /*Restriction: {
                'created_at': timestamp in seconds float,
                'expired_at': timestamp in seconds float,
                'updated_at': timestamp in seconds float,
                'reason': string,
                'type': Enum "ban" | "mute",
                'player_uuid': string uuid,
                'player_name': string,
                'issuer_uuid': string uuid,
                'issuer_name': string,
            }*/
        }
    }

    /*"restrictions": {
        "ban": null,
        "mute": null
    },
    "two_step": false,
    "gold_balance": 1006,
    "invited_by": null*/

    public String getUsername() {
        return username;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public String getSkin() {
        return skin;
    }
}
/*{
    "id": 54672,
    "uuid": "000614e2-dfaa-8e88-86c6-17ddf5f7577c",
    "username": "test13test",
    "is_secured": false,
    "skin": null,
    "cape": null,
    "email": "viktor3243@gmail.com",
    "groups": [
        {
            "modpack": null,
            "server": null,
            "role": {
                "id": 1,
                "code_name": "user",
                "display_name": "Игрок",
                "description": "Группа по умолчанию",
                "color": "#FFFFFF",
                "prefix": "&f[Игрок]",
                "suffix": null,
                "prefix_short": "&f[И]",
                "suffix_short": null,
                "priority": 1,
                "is_staff": false,
                "price": null,
                "currency": "gold",
                "created_at": 1634128273,
                "updated_at": 1688554717,
                "icon": null,
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "nte.player",
                        "display_name": "Префикс [И] в табе",
                        "values": {},
                        "mode": true
                    }
                ],
                "modpacks": []
            },
            "expired_at": null,
            "priority": -10000000000
        },
        {
            "modpack": {
                "id": 15,
                "name": "Anarchy"
            },
            "server": null,
            "role": {
                "id": 0,
                "code_name": "modpack__15",
                "display_name": "Modpack: Anarchy",
                "description": "Modpack default permissions",
                "prefix": null,
                "suffix": null,
                "prefix_short": null,
                "suffix_short": null,
                "color": null,
                "icon": null,
                "priority": -1000000000,
                "is_staff": true,
                "price": null,
                "currency": "gold",
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "betterrtp.use",
                        "display_name": "/rtp",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "GWarp.Warp",
                        "display_name": "/warp название пароль",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "betterrtp.world.*",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "home.sethome",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "home.home",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "home.home-del",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpatoggle",
                        "display_name": "tpatoggle",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.simpletpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpaccept",
                        "display_name": "Принять запрос на телепортацию /tpaccept",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpahere",
                        "display_name": "Запрос на телепортацию к себе /tpahere",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd",
                        "display_name": "Право открыть аукцион",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.sell",
                        "display_name": "Право продавать на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.active",
                        "display_name": "Право открыть листинги на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.expired",
                        "display_name": "Право открыть прошедшие аукционы",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.transactions",
                        "display_name": "Право посмотреть историю транзакций аукциона",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.search",
                        "display_name": "Право искать на аукционе по ключевым словам",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandenderchest.enderchest",
                        "display_name": "CommandEnderChest",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "ability.enderchest",
                        "display_name": "Enderchest",
                        "values": {
                            "discount": 50
                        },
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.sell",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "kit.start",
                        "display_name": "Доступ к набору новичка",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "PlayerReport.*",
                        "display_name": "admin report",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.command.tps",
                        "display_name": "tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.tps",
                        "display_name": "lagg.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.check",
                        "display_name": "lagg check",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.admin",
                        "display_name": "lagg.admin",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.broadcast.*",
                        "display_name": "bukkit.broadcast",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.activity",
                        "display_name": "spark.activity",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.healthreport",
                        "display_name": "spark.healthreport",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.ping",
                        "display_name": "spark.ping",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "forgeserversparkplugin.spark",
                        "display_name": "forgeserversparkplugin.spark",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spigot.command.tps",
                        "display_name": "spigot.command.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "easybackup.notify",
                        "display_name": "easybackup.notify",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.msg",
                        "display_name": "chatty.command.msg",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.reply",
                        "display_name": "chatty.command.reply",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.ignore",
                        "display_name": "chatty.command.ignore",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.maxsell.9",
                        "display_name": "Можно выставить 9 предметов на аукцион",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.sold",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.selling",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpdeny",
                        "display_name": "Отклонить телепортацию к вам от игрока /tpdeny",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "ftblib.command.team",
                        "display_name": "Вступление в команду",
                        "values": {},
                        "mode": true
                    }
                ],
                "modpacks": [
                    {
                        "id": 15,
                        "name": "Anarchy"
                    }
                ],
                "created_at": 1718545613.3972523,
                "updated_at": 1718545613.3972523,
                "mode": true
            },
            "expired_at": null
        },
        {
            "modpack": {
                "id": 6,
                "name": "Anarchy Old"
            },
            "server": null,
            "role": {
                "id": 0,
                "code_name": "modpack__6",
                "display_name": "Modpack: Anarchy Old",
                "description": "Modpack default permissions",
                "prefix": null,
                "suffix": null,
                "prefix_short": null,
                "suffix_short": null,
                "color": null,
                "icon": null,
                "priority": -1000000000,
                "is_staff": true,
                "price": null,
                "currency": "gold",
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "protectionstones.create",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones.destroy",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones.view",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones.info",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones.members",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones_limit_2",
                        "display_name": "Количество доступных блоков привата: 2",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones.create",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones.destroy",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones.view",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones.info",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones.members",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "protectionstones_limit_2",
                        "display_name": "Количество доступных блоков привата: 2",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "PlayerReport.*",
                        "display_name": "admin report",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "minecraft.command.tell",
                        "display_name": "tell",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.command.tell",
                        "display_name": "tell",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "minecraft.command.list",
                        "display_name": "minecraft.command.list",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.admin",
                        "display_name": "lagg.admin",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.check",
                        "display_name": "lagg check",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.tps",
                        "display_name": "lagg.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.activity",
                        "display_name": "spark.activity",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.gc",
                        "display_name": "spark.gc",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.tps",
                        "display_name": "spark.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.healthreport",
                        "display_name": "spark.healthreport",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.ping",
                        "display_name": "spark.ping",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "forgeserversparkplugin.spark",
                        "display_name": "forgeserversparkplugin.spark",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "litebans.notify",
                        "display_name": "All notifications (includes staff notifications, like silent bans). This permission must also be set to false if you are removing other notify permissions.",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.msg",
                        "display_name": "chatty.command.msg",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.reply",
                        "display_name": "chatty.command.reply",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.ignore",
                        "display_name": "chatty.command.ignore",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "kit.start",
                        "display_name": "Доступ к набору новичка",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "GWarp.Warp",
                        "display_name": "/warp название пароль",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.broadcast.*",
                        "display_name": "bukkit.broadcast",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spigot.command.tps",
                        "display_name": "spigot.command.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "easybackup.notify",
                        "display_name": "easybackup.notify",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    }
                ],
                "modpacks": [
                    {
                        "id": 6,
                        "name": "Anarchy Old"
                    }
                ],
                "created_at": 1718545613.3972523,
                "updated_at": 1718545613.3972523,
                "mode": true
            },
            "expired_at": null
        },
        {
            "modpack": {
                "id": 21,
                "name": "Anarchy-Test"
            },
            "server": null,
            "role": {
                "id": 0,
                "code_name": "modpack__21",
                "display_name": "Modpack: Anarchy-Test",
                "description": "Modpack default permissions",
                "prefix": null,
                "suffix": null,
                "prefix_short": null,
                "suffix_short": null,
                "color": null,
                "icon": null,
                "priority": -1000000000,
                "is_staff": true,
                "price": null,
                "currency": "gold",
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "betterrtp.use",
                        "display_name": "/rtp",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "GWarp.Warp",
                        "display_name": "/warp название пароль",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "betterrtp.world.*",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "home.sethome",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "home.home",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "home.home-del",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpatoggle",
                        "display_name": "tpatoggle",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.simpletpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpaccept",
                        "display_name": "Принять запрос на телепортацию /tpaccept",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpahere",
                        "display_name": "Запрос на телепортацию к себе /tpahere",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd",
                        "display_name": "Право открыть аукцион",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.sell",
                        "display_name": "Право продавать на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.active",
                        "display_name": "Право открыть листинги на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.expired",
                        "display_name": "Право открыть прошедшие аукционы",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.transactions",
                        "display_name": "Право посмотреть историю транзакций аукциона",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.search",
                        "display_name": "Право искать на аукционе по ключевым словам",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandenderchest.enderchest",
                        "display_name": "CommandEnderChest",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "ability.enderchest",
                        "display_name": "Enderchest",
                        "values": {
                            "discount": 50
                        },
                        "mode": true
                    }
                ],
                "modpacks": [
                    {
                        "id": 21,
                        "name": "Anarchy-Test"
                    }
                ],
                "created_at": 1718545613.3972523,
                "updated_at": 1718545613.3972523,
                "mode": true
            },
            "expired_at": null
        },
        {
            "modpack": {
                "id": 19,
                "name": "Divinity"
            },
            "server": null,
            "role": {
                "id": 0,
                "code_name": "modpack__19",
                "display_name": "Modpack: Divinity",
                "description": "Modpack default permissions",
                "prefix": null,
                "suffix": null,
                "prefix_short": null,
                "suffix_short": null,
                "color": null,
                "icon": null,
                "priority": -1000000000,
                "is_staff": true,
                "price": null,
                "currency": "gold",
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "enderio.*",
                        "display_name": "Доступ ко всем механизмам и предметам в InderIo",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.command.tps",
                        "display_name": "tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.tps",
                        "display_name": "spark.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.tps",
                        "display_name": "lagg.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.activity",
                        "display_name": "spark.activity",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.gc",
                        "display_name": "spark.gc",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.healthreport",
                        "display_name": "spark.healthreport",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.ping",
                        "display_name": "spark.ping",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "forgeserversparkplugin.spark",
                        "display_name": "forgeserversparkplugin.spark",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.msg",
                        "display_name": "chatty.command.msg",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.ignore",
                        "display_name": "chatty.command.ignore",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.reply",
                        "display_name": "chatty.command.reply",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.simpletpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpahere",
                        "display_name": "Запрос на телепортацию к себе /tpahere",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpdeny",
                        "display_name": "Отклонить телепортацию к вам от игрока /tpdeny",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpaccept",
                        "display_name": "Принять запрос на телепортацию /tpaccept",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "PlayerReport.Report",
                        "display_name": "report",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.admin",
                        "display_name": "lagg.admin",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.check",
                        "display_name": "lagg check",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "ftblib.command.team",
                        "display_name": "Вступление в команду",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.broadcast.*",
                        "display_name": "bukkit.broadcast",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "litebans.notify.broadcast",
                        "display_name": "Ban/mute/warning/kick broadcasts.",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "minecraft.command.list",
                        "display_name": "minecraft.command.list",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spigot.command.tps",
                        "display_name": "spigot.command.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "easybackup.notify",
                        "display_name": "easybackup.notify",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "kit.start",
                        "display_name": "Доступ к набору новичка",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd",
                        "display_name": "Право открыть аукцион",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.sell",
                        "display_name": "Право продавать на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.active",
                        "display_name": "Право открыть листинги на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.expired",
                        "display_name": "Право открыть прошедшие аукционы",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.transactions",
                        "display_name": "Право посмотреть историю транзакций аукциона",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.search",
                        "display_name": "Право искать на аукционе по ключевым словам",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.sell",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.maxsell.9",
                        "display_name": "Можно выставить 9 предметов на аукцион",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.selling",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.sold",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    }
                ],
                "modpacks": [
                    {
                        "id": 19,
                        "name": "Divinity"
                    }
                ],
                "created_at": 1718545613.3972523,
                "updated_at": 1718545613.3972523,
                "mode": true
            },
            "expired_at": null
        },
        {
            "modpack": {
                "id": 24,
                "name": "Divinity-Test"
            },
            "server": null,
            "role": {
                "id": 0,
                "code_name": "modpack__24",
                "display_name": "Modpack: Divinity-Test",
                "description": "Modpack default permissions",
                "prefix": null,
                "suffix": null,
                "prefix_short": null,
                "suffix_short": null,
                "color": null,
                "icon": null,
                "priority": -1000000000,
                "is_staff": true,
                "price": null,
                "currency": "gold",
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "enderio.*",
                        "display_name": "Доступ ко всем механизмам и предметам в InderIo",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.command.tps",
                        "display_name": "tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.tps",
                        "display_name": "spark.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.tps",
                        "display_name": "lagg.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.activity",
                        "display_name": "spark.activity",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.gc",
                        "display_name": "spark.gc",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.healthreport",
                        "display_name": "spark.healthreport",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.ping",
                        "display_name": "spark.ping",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "forgeserversparkplugin.spark",
                        "display_name": "forgeserversparkplugin.spark",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.msg",
                        "display_name": "chatty.command.msg",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.ignore",
                        "display_name": "chatty.command.ignore",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.reply",
                        "display_name": "chatty.command.reply",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.simpletpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpahere",
                        "display_name": "Запрос на телепортацию к себе /tpahere",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpdeny",
                        "display_name": "Отклонить телепортацию к вам от игрока /tpdeny",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpaccept",
                        "display_name": "Принять запрос на телепортацию /tpaccept",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "PlayerReport.Report",
                        "display_name": "report",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.admin",
                        "display_name": "lagg.admin",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.check",
                        "display_name": "lagg check",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "ftblib.command.team",
                        "display_name": "Вступление в команду",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.broadcast.*",
                        "display_name": "bukkit.broadcast",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "litebans.notify.broadcast",
                        "display_name": "Ban/mute/warning/kick broadcasts.",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "minecraft.command.list",
                        "display_name": "minecraft.command.list",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spigot.command.tps",
                        "display_name": "spigot.command.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "easybackup.notify",
                        "display_name": "easybackup.notify",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "kit.start",
                        "display_name": "Доступ к набору новичка",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd",
                        "display_name": "Право открыть аукцион",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.sell",
                        "display_name": "Право продавать на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.active",
                        "display_name": "Право открыть листинги на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.expired",
                        "display_name": "Право открыть прошедшие аукционы",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.transactions",
                        "display_name": "Право посмотреть историю транзакций аукциона",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.search",
                        "display_name": "Право искать на аукционе по ключевым словам",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.sell",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.maxsell.9",
                        "display_name": "Можно выставить 9 предметов на аукцион",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.selling",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.sold",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    }
                ],
                "modpacks": [
                    {
                        "id": 24,
                        "name": "Divinity-Test"
                    }
                ],
                "created_at": 1718545613.3972523,
                "updated_at": 1718545613.3972523,
                "mode": true
            },
            "expired_at": null
        },
        {
            "modpack": {
                "id": 18,
                "name": "Enigma"
            },
            "server": null,
            "role": {
                "id": 0,
                "code_name": "modpack__18",
                "display_name": "Modpack: Enigma",
                "description": "Modpack default permissions",
                "prefix": null,
                "suffix": null,
                "prefix_short": null,
                "suffix_short": null,
                "color": null,
                "icon": null,
                "priority": -1000000000,
                "is_staff": true,
                "price": null,
                "currency": "gold",
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "ftblib.command.team",
                        "display_name": "Вступление в команду",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.wand",
                        "display_name": "worldedit.wand",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.expand",
                        "display_name": "worldedit.selection.expand",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.hpos",
                        "display_name": "worldedit.selection.hpos",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.pos",
                        "display_name": "worldedit.selection.pos",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.outset",
                        "display_name": "worldedit.selection.outset",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.wand",
                        "display_name": "worldguard.region.wand",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.list.own",
                        "display_name": "worldguard.region.list.own",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.addmember.own.*",
                        "display_name": "worldguard.region.addmember.own.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.select.*",
                        "display_name": "worldguard.region.select.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.claim",
                        "display_name": "worldguard.region.claim",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.remove.own.*",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.info.own.*",
                        "display_name": "worldguard.region.info.own.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard_region_count_2",
                        "display_name": "Количество доступных приватов: 2",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard_region_size_100000",
                        "display_name": "Размер привата 100000 блоков",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.reply",
                        "display_name": "chatty.command.reply",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.ignore",
                        "display_name": "chatty.command.ignore",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.msg",
                        "display_name": "chatty.command.msg",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.activity",
                        "display_name": "spark.activity",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.gc",
                        "display_name": "spark.gc",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.tps",
                        "display_name": "spark.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.healthreport",
                        "display_name": "spark.healthreport",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.ping",
                        "display_name": "spark.ping",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "forgeserversparkplugin.spark",
                        "display_name": "forgeserversparkplugin.spark",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "betterrtp.use",
                        "display_name": "/rtp",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "betterrtp.world.*",
                        "display_name": null,
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "minecraft.command.tell",
                        "display_name": "tell",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.command.tell",
                        "display_name": "tell",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpatoggle",
                        "display_name": "tpatoggle",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpaccept",
                        "display_name": "Принять запрос на телепортацию /tpaccept",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpahere",
                        "display_name": "Запрос на телепортацию к себе /tpahere",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "kit.start",
                        "display_name": "Доступ к набору новичка",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "PlayerReport.Report",
                        "display_name": "report",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.tps",
                        "display_name": "lagg.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.check",
                        "display_name": "lagg check",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.broadcast.*",
                        "display_name": "bukkit.broadcast",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "litebans.notify.broadcast",
                        "display_name": "Ban/mute/warning/kick broadcasts.",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.activity",
                        "display_name": "spark.activity",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spigot.command.tps",
                        "display_name": "spigot.command.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "easybackup.notify",
                        "display_name": "easybackup.notify",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd",
                        "display_name": "Право открыть аукцион",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.sell",
                        "display_name": "Право продавать на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.active",
                        "display_name": "Право открыть листинги на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.expired",
                        "display_name": "Право открыть прошедшие аукционы",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.transactions",
                        "display_name": "Право посмотреть историю транзакций аукциона",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.search",
                        "display_name": "Право искать на аукционе по ключевым словам",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.maxsell.14",
                        "display_name": "Можно выставить 14 предметов на аукцион (Дефолт: 9)",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "betterrtp.use",
                        "display_name": "/rtp",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "kit.start",
                        "display_name": "Доступ к набору новичка",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpdeny",
                        "display_name": "Отклонить телепортацию к вам от игрока /tpdeny",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "enderio.*",
                        "display_name": "Доступ ко всем механизмам и предметам в InderIo",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.removemember.own.*",
                        "display_name": "worldguard.region.removemember.own.*",
                        "values": {},
                        "mode": true
                    }
                ],
                "modpacks": [
                    {
                        "id": 18,
                        "name": "Enigma"
                    }
                ],
                "created_at": 1718545613.3972523,
                "updated_at": 1718545613.3972523,
                "mode": true
            },
            "expired_at": null
        },
        {
            "modpack": {
                "id": 13,
                "name": "Enigma-Test"
            },
            "server": null,
            "role": {
                "id": 0,
                "code_name": "modpack__13",
                "display_name": "Modpack: Enigma-Test",
                "description": "Modpack default permissions",
                "prefix": null,
                "suffix": null,
                "prefix_short": null,
                "suffix_short": null,
                "color": null,
                "icon": null,
                "priority": -1000000000,
                "is_staff": true,
                "price": null,
                "currency": "gold",
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "ftblib.command.team",
                        "display_name": "Вступление в команду",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.wand",
                        "display_name": "worldedit.wand",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.expand",
                        "display_name": "worldedit.selection.expand",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.hpos",
                        "display_name": "worldedit.selection.hpos",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.pos",
                        "display_name": "worldedit.selection.pos",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.outset",
                        "display_name": "worldedit.selection.outset",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.wand",
                        "display_name": "worldguard.region.wand",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.list.own",
                        "display_name": "worldguard.region.list.own",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.addmember.own.*",
                        "display_name": "worldguard.region.addmember.own.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.removemember.own.*",
                        "display_name": "worldguard.region.removemember.own.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.info.own.*",
                        "display_name": "worldguard.region.info.own.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.select.*",
                        "display_name": "worldguard.region.select.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.claim",
                        "display_name": "worldguard.region.claim",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.remove.own.*",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard_region_count_2",
                        "display_name": "Количество доступных приватов: 2",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "enderio.*",
                        "display_name": "Доступ ко всем механизмам и предметам в InderIo",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard_region_size_100000",
                        "display_name": "Размер привата 100000 блоков",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.msg",
                        "display_name": "chatty.command.msg",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.reply",
                        "display_name": "chatty.command.reply",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.ignore",
                        "display_name": "chatty.command.ignore",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.activity",
                        "display_name": "spark.activity",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.gc",
                        "display_name": "spark.gc",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.tps",
                        "display_name": "spark.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.healthreport",
                        "display_name": "spark.healthreport",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.ping",
                        "display_name": "spark.ping",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "forgeserversparkplugin.spark",
                        "display_name": "forgeserversparkplugin.spark",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "wildernesstp.command.wild",
                        "display_name": "Рандомная телепортация",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "wild.wildtp",
                        "display_name": "wild.wildtp",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "wild.wildtp.world.*",
                        "display_name": "wild.wildtp.world.world",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "wildernesstp.command.wild",
                        "display_name": "Рандомная телепортация",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "minecraft.command.tell",
                        "display_name": "tell",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "PlayerReport.Report",
                        "display_name": "report",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.simpletpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpdeny",
                        "display_name": "Отклонить телепортацию к вам от игрока /tpdeny",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpaccept",
                        "display_name": "Принять запрос на телепортацию /tpaccept",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpahere",
                        "display_name": "Запрос на телепортацию к себе /tpahere",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "minecraft.command.list",
                        "display_name": "minecraft.command.list",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "kit.start",
                        "display_name": "Доступ к набору новичка",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "PlayerReport.Report",
                        "display_name": "report",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bountifulbaubles.*",
                        "display_name": "Все права на BountifulBaubles",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.command.tps",
                        "display_name": "tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.tps",
                        "display_name": "lagg.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.check",
                        "display_name": "lagg check",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.admin",
                        "display_name": "lagg.admin",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.broadcast.*",
                        "display_name": "bukkit.broadcast",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "litebans.notify.broadcast",
                        "display_name": "Ban/mute/warning/kick broadcasts.",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.activity",
                        "display_name": "spark.activity",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.gc",
                        "display_name": "spark.gc",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.healthreport",
                        "display_name": "spark.healthreport",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.ping",
                        "display_name": "spark.ping",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "forgeserversparkplugin.spark",
                        "display_name": "forgeserversparkplugin.spark",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spigot.command.tps",
                        "display_name": "spigot.command.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "easybackup.notify",
                        "display_name": "easybackup.notify",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "enderio.*",
                        "display_name": "Доступ ко всем механизмам и предметам в InderIo",
                        "values": {},
                        "mode": true
                    }
                ],
                "modpacks": [
                    {
                        "id": 13,
                        "name": "Enigma-Test"
                    }
                ],
                "created_at": 1718545613.3972523,
                "updated_at": 1718545613.3972523,
                "mode": true
            },
            "expired_at": null
        },
        {
            "modpack": {
                "id": 23,
                "name": "Heaven"
            },
            "server": null,
            "role": {
                "id": 0,
                "code_name": "modpack__23",
                "display_name": "Modpack: Heaven",
                "description": "Modpack default permissions",
                "prefix": null,
                "suffix": null,
                "prefix_short": null,
                "suffix_short": null,
                "color": null,
                "icon": null,
                "priority": -1000000000,
                "is_staff": true,
                "price": null,
                "currency": "gold",
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "enderio.*",
                        "display_name": "Доступ ко всем механизмам и предметам в InderIo",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.command.tps",
                        "display_name": "tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.tps",
                        "display_name": "spark.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.tps",
                        "display_name": "lagg.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.activity",
                        "display_name": "spark.activity",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.gc",
                        "display_name": "spark.gc",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.healthreport",
                        "display_name": "spark.healthreport",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.ping",
                        "display_name": "spark.ping",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "forgeserversparkplugin.spark",
                        "display_name": "forgeserversparkplugin.spark",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.msg",
                        "display_name": "chatty.command.msg",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.ignore",
                        "display_name": "chatty.command.ignore",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.reply",
                        "display_name": "chatty.command.reply",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.simpletpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpahere",
                        "display_name": "Запрос на телепортацию к себе /tpahere",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpdeny",
                        "display_name": "Отклонить телепортацию к вам от игрока /tpdeny",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpaccept",
                        "display_name": "Принять запрос на телепортацию /tpaccept",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "PlayerReport.Report",
                        "display_name": "report",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.admin",
                        "display_name": "lagg.admin",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.check",
                        "display_name": "lagg check",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "ftblib.command.team",
                        "display_name": "Вступление в команду",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.broadcast.*",
                        "display_name": "bukkit.broadcast",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "litebans.notify.broadcast",
                        "display_name": "Ban/mute/warning/kick broadcasts.",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "minecraft.command.list",
                        "display_name": "minecraft.command.list",
                        "values": {},
                        "mode": false
                    },
                    {
                        "code_name": "spigot.command.tps",
                        "display_name": "spigot.command.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "easybackup.notify",
                        "display_name": "easybackup.notify",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "minecraft.command.tell",
                        "display_name": "tell",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.command.tell",
                        "display_name": "tell",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpatoggle",
                        "display_name": "tpatoggle",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "kit.start",
                        "display_name": "Доступ к набору новичка",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd",
                        "display_name": "Право открыть аукцион",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.sell",
                        "display_name": "Право продавать на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.active",
                        "display_name": "Право открыть листинги на аукционе",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.expired",
                        "display_name": "Право открыть прошедшие аукционы",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.transactions",
                        "display_name": "Право посмотреть историю транзакций аукциона",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.cmd.search",
                        "display_name": "Право искать на аукционе по ключевым словам",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "auctionhouse.maxsell.14",
                        "display_name": "Можно выставить 14 предметов на аукцион (Дефолт: 9)",
                        "values": {},
                        "mode": true
                    }
                ],
                "modpacks": [
                    {
                        "id": 23,
                        "name": "Heaven"
                    }
                ],
                "created_at": 1718545613.3972523,
                "updated_at": 1718545613.3972523,
                "mode": true
            },
            "expired_at": null
        },
        {
            "modpack": {
                "id": 17,
                "name": "Heaven_Test"
            },
            "server": null,
            "role": {
                "id": 0,
                "code_name": "modpack__17",
                "display_name": "Modpack: Heaven_Test",
                "description": "Modpack default permissions",
                "prefix": null,
                "suffix": null,
                "prefix_short": null,
                "suffix_short": null,
                "color": null,
                "icon": null,
                "priority": -1000000000,
                "is_staff": true,
                "price": null,
                "currency": "gold",
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "enderio.*",
                        "display_name": "Доступ ко всем механизмам и предметам в InderIo",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.command.tps",
                        "display_name": "tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.tps",
                        "display_name": "spark.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.tps",
                        "display_name": "lagg.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.activity",
                        "display_name": "spark.activity",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.gc",
                        "display_name": "spark.gc",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.healthreport",
                        "display_name": "spark.healthreport",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spark.ping",
                        "display_name": "spark.ping",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "forgeserversparkplugin.spark",
                        "display_name": "forgeserversparkplugin.spark",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.msg",
                        "display_name": "chatty.command.msg",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.ignore",
                        "display_name": "chatty.command.ignore",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "chatty.command.reply",
                        "display_name": "chatty.command.reply",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.simpletpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpahere",
                        "display_name": "Запрос на телепортацию к себе /tpahere",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpdeny",
                        "display_name": "Отклонить телепортацию к вам от игрока /tpdeny",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpcancel",
                        "display_name": "Отменить телепортацию к игроку /tpcancel [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpaccept",
                        "display_name": "Принять запрос на телепортацию /tpaccept",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "simpletpa.tpa",
                        "display_name": "Отправить запрос на телепортацию к игроку /tpa [ник]",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "PlayerReport.Report",
                        "display_name": "report",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.admin",
                        "display_name": "lagg.admin",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "lagg.check",
                        "display_name": "lagg check",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "ftblib.command.team",
                        "display_name": "Вступление в команду",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "commandspawn.spawn",
                        "display_name": "commandspawn.spawn",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "com.cifrazia.magicessentials.server.command.CommandSpawn",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.broadcast.*",
                        "display_name": "bukkit.broadcast",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "litebans.notify.broadcast",
                        "display_name": "Ban/mute/warning/kick broadcasts.",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "minecraft.command.list",
                        "display_name": "minecraft.command.list",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "spigot.command.tps",
                        "display_name": "spigot.command.tps",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "easybackup.notify",
                        "display_name": "easybackup.notify",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "wildstacker.toggle",
                        "display_name": "wildstacker.toggle",
                        "values": {},
                        "mode": true
                    }
                ],
                "modpacks": [
                    {
                        "id": 17,
                        "name": "Heaven_Test"
                    }
                ],
                "created_at": 1718545613.3972523,
                "updated_at": 1718545613.3972523,
                "mode": true
            },
            "expired_at": null
        },
        {
            "modpack": {
                "id": 7,
                "name": "Mystery"
            },
            "server": null,
            "role": {
                "id": 0,
                "code_name": "modpack__7",
                "display_name": "Modpack: Mystery",
                "description": "Modpack default permissions",
                "prefix": null,
                "suffix": null,
                "prefix_short": null,
                "suffix_short": null,
                "color": null,
                "icon": null,
                "priority": -1000000000,
                "is_staff": true,
                "price": null,
                "currency": "gold",
                "inherit_role": null,
                "perms": [
                    {
                        "code_name": "worldedit.wand",
                        "display_name": "worldedit.wand",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.pos",
                        "display_name": "worldedit.selection.pos",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.hpos",
                        "display_name": "worldedit.selection.hpos",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.outset",
                        "display_name": "worldedit.selection.outset",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldedit.selection.expand",
                        "display_name": "worldedit.selection.expand",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.wand",
                        "display_name": "worldguard.region.wand",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.list.own",
                        "display_name": "worldguard.region.list.own",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.removemember.own.*",
                        "display_name": "worldguard.region.removemember.own.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.addmember.own.*",
                        "display_name": "worldguard.region.addmember.own.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.info.own.*",
                        "display_name": "worldguard.region.info.own.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.select.*",
                        "display_name": "worldguard.region.select.*",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.claim",
                        "display_name": "worldguard.region.claim",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard.region.remove.own.*",
                        "display_name": "-",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard_region_size_100000",
                        "display_name": "Размер привата 100000 блоков",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "worldguard_region_count_2",
                        "display_name": "Количество доступных приватов: 2",
                        "values": {},
                        "mode": true
                    },
                    {
                        "code_name": "bukkit.broadcast.*",
                        "display_name": "bukkit.broadcast",
                        "values": {},
                        "mode": true
                    }
                ],
                "modpacks": [
                    {
                        "id": 7,
                        "name": "Mystery"
                    }
                ],
                "created_at": 1718545613.3972523,
                "updated_at": 1718545613.3972523,
                "mode": true
            },
            "expired_at": null
        }
    ],
    "restrictions": {
        "ban": null,
        "mute": null
    },
    "two_step": false,
    "gold_balance": 1006,
    "invited_by": null
}*/