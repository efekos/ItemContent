package dev.efekos.itemcontent.items.tag;

import java.util.List;

public class KnowledgeBookTag extends ItemTag {
    private final List<String> Recipes;

    public KnowledgeBookTag(ItemTag oldItemTag, List<String> recipeIds) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        Recipes = recipeIds;
    }

    public List<String> getRecipes() {
        return Recipes;
    }
}
