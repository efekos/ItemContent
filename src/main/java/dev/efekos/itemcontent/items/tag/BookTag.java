package dev.efekos.itemcontent.items.tag;

public class BookTag extends ItemTag {
    public BookTag(ItemTag oldItemTag, String title, String author, Integer generation) {
        super(oldItemTag.Damage, oldItemTag.getHideFlags(), oldItemTag.display, oldItemTag.isUnbreakable(), oldItemTag.getCustomModelData(), oldItemTag.getEnchantments(), oldItemTag.RepairCost);
        this.author = author;
        this.title = title;
        this.generation = generation;
    }

    private final String author;
    private final String title;
    private final Integer generation;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Integer getGeneration() {
        return generation;
    }
}
