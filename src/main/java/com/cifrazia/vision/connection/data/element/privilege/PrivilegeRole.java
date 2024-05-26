package com.cifrazia.vision.connection.data.element.privilege;

import com.cifrazia.vision.connection.data.element.ModPack;

import java.util.List;

public class PrivilegeRole {
    private int id;
    private String code_name;
    private String display_name;
    private String description;
    private String color;
    private String prefix;
    private String suffix;
    private String prefix_short;
    private String suffix_short;
    private int priority;
    private boolean is_staff;
    private long price;
    private String currency;
    private long created_at;
    private long updated_at;
    private String icon;
    private InheritRole inherit_role;
    private List<PermissionOfRole> perms;
    private List<ModPack> modpacks;


    public class InheritRole{
        private int id;
        private String code_name;
        private String display_name;
    }

    public class PermissionOfRole{
        private int id;
        private String code_name;
        private String display_name;
        private String description;
        private String values;
        private boolean is_staff;
        private long price;
        private String currency;
        private long created_at;
        private long updated_at;
        private List<ModPack> modpacks;
        private boolean mode;
    }
}

/*
            "id": 8,
            "code_name": "skyblock.legend",
            "display_name": "Legend",
            "description": "Легенды обладают множеством способностей, которые удивлят Ваших друзей и соперников. Становись лучшим и получай редкие и ценные ресурсы из комплекта Легенды. Выделись из всех.",
            "color": "#FF5555",
            "prefix": "&c[Legend]",
            "suffix": null,
            "prefix_short": "&c[L]",
            "suffix_short": null,
            "priority": 300,
            "is_staff": false,
            "price": 699,
            "currency": "gold",
            "created_at": 1588655771,
            "updated_at": 1698319864,
            "icon": "https://ru-1.s3.cifrazia.com/cifrazia-minecraft/system/role_icons/skyblock.legend.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=cifrazia-minecraft%2F20240523%2Feu-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240523T171138Z&X-Amz-Expires=3600&X-Amz-SignedHeaders=host&X-Amz-Signature=43c9103c73b1323ce0034ea091d8590437d033084ef68ad654c52bfe5e1143f9",
            "inherit_role": {
                "id": 7,
                "code_name": "skyblock.hero",
                "display_name": "Hero"
            },
            "perms": [
                {
                    "id": 85,
                    "code_name": "tp.delay",
                    "display_name": "Задержка перед телепортацией 1 секунда на /spawn",
                    "description": "-",
                    "values": {
                        "time": 1
                    },
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1592392782,
                    "updated_at": 1662199723,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 270,
                    "code_name": "chatty.chat.donate",
                    "display_name": null,
                    "description": "chatty.chat.donate",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1677517110,
                    "updated_at": 1688554510,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 194,
                    "code_name": "OpenInv.openender",
                    "display_name": "Открытие эндер сундука - /oe",
                    "description": "/oe",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1658217365,
                    "updated_at": 1662198215,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 314,
                    "code_name": "commandfeed.feed",
                    "display_name": "/feed - насытить свой голод",
                    "description": "",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1688549662,
                    "updated_at": 1688554664,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 295,
                    "code_name": "ability.feed",
                    "display_name": "Пополнить голод",
                    "description": "/feed",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1688487267,
                    "updated_at": 1688487291,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 89,
                    "code_name": "death.save.experience",
                    "display_name": "Сохранение опыта при смерти",
                    "description": "-",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1592394570,
                    "updated_at": 1659056813,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 227,
                    "code_name": "OpenInv.editender",
                    "display_name": "Взаимодействие с виртуальным эндер сундуком",
                    "description": "",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1658566609,
                    "updated_at": 1662198192,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 82,
                    "code_name": "ability.god",
                    "display_name": "Режим бога",
                    "description": "-",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1592391524,
                    "updated_at": 1659056834,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 87,
                    "code_name": "death.save.inventory",
                    "display_name": "Сохранение инвентаря при смерти",
                    "description": "-",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1592394409,
                    "updated_at": 1659056820,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 80,
                    "code_name": "kit.legend",
                    "display_name": "Доступ к набору \"Legend\"",
                    "description": "-",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1592391481,
                    "updated_at": 1659056842,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 94,
                    "code_name": "island.legend",
                    "display_name": "Доступ к острову \"Legend\"",
                    "description": "-",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1593077277,
                    "updated_at": 1659056793,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 154,
                    "code_name": "nte.legend",
                    "display_name": "Префикс [L] в табе",
                    "description": "-",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1657191856,
                    "updated_at": 1659056559,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 195,
                    "code_name": "simpletpa.back",
                    "display_name": "Возвращение на место смерти - /back",
                    "description": "/back",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1658316590,
                    "updated_at": 1662199501,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 76,
                    "code_name": "ability.fly",
                    "display_name": "Полет",
                    "description": "-",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1591544439,
                    "updated_at": 1659056856,
                    "modpacks": [],
                    "mode": true
                },
                {
                    "id": 315,
                    "code_name": "worldguard.heal",
                    "display_name": "/heal - излечить себя",
                    "description": "",
                    "values": {},
                    "is_staff": false,
                    "price": null,
                    "currency": "gold",
                    "created_at": 1688549676,
                    "updated_at": 1688554636,
                    "modpacks": [
                        {
                            "id": 15,
                            "name": "Anarchy"
                        }
                    ],
                    "mode": true
                }
            ],
            "modpacks": []
        }*/