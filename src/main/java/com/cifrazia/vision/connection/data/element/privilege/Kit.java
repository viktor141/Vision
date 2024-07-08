package com.cifrazia.vision.connection.data.element.privilege;

import java.util.HashMap;
import java.util.List;

public class Kit {
    private int id;
    private String name;
    private long cool_down;
    private long price;
    private String currency;
    private boolean is_default;
    private PermissionOfKit permission;
    private List<KitItem> items;

    public class PermissionOfKit {
        private int id;
        private String code_name;
        private String display_name;
        private String description;
        private HashMap<String, Integer> values;
        private long price;
        private String currency;
        private boolean is_staff;

        public String getCode_name() {
            return code_name;
        }
    }

    public class KitItem {
        private int id;
        private String material_name;
        private int damage;
        private int count;
        private String nbt;


        public String getMaterialName() {
            return material_name;
        }

        public int getDamage() {
            return damage;
        }

        public int getCount() {
            return count;
        }

        public String getNbt() {
            return nbt;
        }
    }

    public String getName() {
        return name;
    }

    public List<KitItem> getItems() {
        return items;
    }

    public PermissionOfKit getPermission() {
        return permission;
    }

    public long getCool_down() {
        return cool_down;
    }
}
/*
        "id": 1,
        "name": "Master",
        "cool_down": 2592000,
        "price": 99,
        "currency": "gold",
        "is_default": false,
        "permission": {
            "id": 78,
            "code_name": "kit.master",
            "display_name": "Доступ к набору \"Master\"",
            "description": "-",
            "values": {},
            "price": null,
            "currency": "gold",
            "is_staff": false
        },
        "items": [
            {
                "id": 1681,
                "material_name": "minecraft:bedrock",
                "damage": 0,
                "count": 1,
                "nbt": null
            },
            {
                "id": 1689,
                "material_name": "minecraft:ender_pearl",
                "damage": 0,
                "count": 8,
                "nbt": null
            },
            {
                "id": 16,
                "material_name": "tconstruct:smeltery_controller",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"tconstruct:smeltery_controller\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 3,
                "material_name": "minecraft:dye",
                "damage": 15,
                "count": 32,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:dye\",\"Count\":\"32b\",\"Damage\":\"15s\"}"
            },
            {
                "id": 4,
                "material_name": "minecraft:coal",
                "damage": 0,
                "count": 16,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:coal\",\"Count\":\"16b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 5,
                "material_name": "minecraft:iron_ingot",
                "damage": 0,
                "count": 16,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:iron_ingot\",\"Count\":\"16b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 6,
                "material_name": "minecraft:glowstone_dust",
                "damage": 0,
                "count": 16,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:glowstone_dust\",\"Count\":\"16b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 7,
                "material_name": "minecraft:gold_ingot",
                "damage": 0,
                "count": 12,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:gold_ingot\",\"Count\":\"12b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 8,
                "material_name": "minecraft:diamond",
                "damage": 0,
                "count": 8,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:diamond\",\"Count\":\"8b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 9,
                "material_name": "draconicevolution:draconium_dust",
                "damage": 0,
                "count": 8,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"draconicevolution:draconium_dust\",\"Count\":\"8b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 10,
                "material_name": "bigreactors:ingotyellorium",
                "damage": 0,
                "count": 4,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"bigreactors:ingotyellorium\",\"Count\":\"4b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 11,
                "material_name": "thermalfoundation:material",
                "damage": 165,
                "count": 4,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"thermalfoundation:material\",\"Count\":\"4b\",\"Damage\":\"165s\"}"
            },
            {
                "id": 12,
                "material_name": "extendedcrafting:material",
                "damage": 36,
                "count": 4,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"extendedcrafting:material\",\"Count\":\"4b\",\"Damage\":\"36s\"}"
            },
            {
                "id": 13,
                "material_name": "thermalfoundation:material",
                "damage": 167,
                "count": 2,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"thermalfoundation:material\",\"Count\":\"2b\",\"Damage\":\"167s\"}"
            },
            {
                "id": 14,
                "material_name": "minecraft:skull",
                "damage": 1,
                "count": 3,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:skull\",\"Count\":\"3b\",\"Damage\":\"1s\"}"
            },
            {
                "id": 15,
                "material_name": "tconstruct:seared",
                "damage": 3,
                "count": 2,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"tconstruct:seared\",\"Count\":\"2b\",\"Damage\":\"3s\"}"
            },
            {
                "id": 17,
                "material_name": "tconstruct:seared_tank",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"tconstruct:seared_tank\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 18,
                "material_name": "tconstruct:smeltery_io",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"tconstruct:smeltery_io\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 19,
                "material_name": "tconstruct:casting",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"tconstruct:casting\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 20,
                "material_name": "tconstruct:faucet",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"tconstruct:faucet\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 21,
                "material_name": "thermalexpansion:dynamo",
                "damage": 1,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"thermalexpansion:dynamo\",\"Count\":\"1b\",\"tag\":{\"RSControl\":\"0b\",\"Facing\":\"1b\",\"Energy\":\"0\",\"Level\":\"0b\"},\"Damage\":\"1s\"}"
            },
            {
                "id": 22,
                "material_name": "enderio:block_solar_panel",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"enderio:block_solar_panel\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 24,
                "material_name": "thermalfoundation:material",
                "damage": 513,
                "count": 2,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"thermalfoundation:material\",\"Count\":\"2b\",\"Damage\":\"513s\"}"
            },
            {
                "id": 27,
                "material_name": "thermalfoundation:material",
                "damage": 515,
                "count": 2,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"thermalfoundation:material\",\"Count\":\"2b\",\"Damage\":\"515s\"}"
            },
            {
                "id": 30,
                "material_name": "enderio:item_basic_capacitor",
                "damage": 0,
                "count": 2,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"enderio:item_basic_capacitor\",\"Count\":\"2b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 29,
                "material_name": "thermalfoundation:material",
                "damage": 514,
                "count": 2,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"thermalfoundation:material\",\"Count\":\"2b\",\"Damage\":\"514s\"}"
            },
            {
                "id": 31,
                "material_name": "enderio:item_power_conduit",
                "damage": 0,
                "count": 8,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"enderio:item_power_conduit\",\"Count\":\"8b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 32,
                "material_name": "thermaldynamics:duct_32",
                "damage": 0,
                "count": 8,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"thermaldynamics:duct_32\",\"Count\":\"8b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 33,
                "material_name": "thermaldynamics:duct_16",
                "damage": 0,
                "count": 8,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"thermaldynamics:duct_16\",\"Count\":\"8b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 34,
                "material_name": "exnihilocreatio:item_mesh",
                "damage": 3,
                "count": 2,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"exnihilocreatio:item_mesh\",\"Count\":\"2b\",\"Damage\":\"3s\"}"
            },
            {
                "id": 35,
                "material_name": "exnihilocreatio:block_sieve",
                "damage": 0,
                "count": 2,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"exnihilocreatio:block_sieve\",\"Count\":\"2b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 37,
                "material_name": "minecraft:iron_helmet",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:iron_helmet\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 38,
                "material_name": "minecraft:iron_chestplate",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:iron_chestplate\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 39,
                "material_name": "minecraft:iron_leggings",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:iron_leggings\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 40,
                "material_name": "minecraft:iron_boots",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:iron_boots\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 45,
                "material_name": "exnihilocreatio:hammer_iron",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"exnihilocreatio:hammer_iron\",\"Count\":\"1b\",\"tag\":{\"ench\":[{\"lvl\":\"4s\",\"id\":\"32s\"},{\"lvl\":\"3s\",\"id\":\"34s\"},{\"lvl\":\"3s\",\"id\":\"35s\"}]},\"Damage\":\"0s\"}"
            },
            {
                "id": 46,
                "material_name": "xlfoodmod:oreo_cookie",
                "damage": 0,
                "count": 16,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"xlfoodmod:oreo_cookie\",\"Count\":\"16b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 47,
                "material_name": "xlfoodmod:healthy_energy_drink",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"xlfoodmod:healthy_energy_drink\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 63,
                "material_name": "exnihilocreatio:crook_bone",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"exnihilocreatio:crook_bone\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 64,
                "material_name": "minecraft:iron_sword",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:iron_sword\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 65,
                "material_name": "minecraft:iron_pickaxe",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:iron_pickaxe\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            },
            {
                "id": 66,
                "material_name": "minecraft:iron_shovel",
                "damage": 0,
                "count": 1,
                "nbt": "{\"ForgeCaps\":{\"customnpcs:itemscripteddata\":{}},\"id\":\"minecraft:iron_shovel\",\"Count\":\"1b\",\"Damage\":\"0s\"}"
            }
        ]
    }*/