package com.ipca.budget.travelmobile.entities

enum class DataTypeSecondary(val id: String, val buttonId: String) {
    CONFORT("confort", "confortId"),
    ADVENTURE("adventure", "adventureId"),
    RELAX("relax", "relaxId"),
    ENTERTAINMENT("entertainment", "entertainmentId"),
    HISTORY("history", "historyId"),
    FOODIES("foodies", "foodiesId"),
    NIGHT_LIFE("nightLife", "nightLifeId"),
    MARKETS("markets", "marketsId"),
    ZEN("zen", "zenId"),
    WILD_LIFE("wildLife", "wildLifeId"),
    PRIVACY("privacy", "privacyId"),
    THEMATIC("thematic", "thematicId");

    companion object {
        fun getDataById(typeToFind: String?): DataTypeSecondary? {
            return DataTypeSecondary.values().firstOrNull {
                it.id==typeToFind
            }
        }

        fun getDataByButtonId(buttonIdToFind: String?): DataTypeSecondary? {
            return DataTypeSecondary.values().firstOrNull {
                it.buttonId==buttonIdToFind
            }
        }
    }
}