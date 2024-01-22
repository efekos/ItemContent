package dev.efekos.itemcontent.items;

import com.google.gson.Gson;
import dev.efekos.itemcontent.items.compound.*;
import dev.efekos.itemcontent.items.tag.*;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Used to show items in chat. To use this class, you must have an {@link ItemStack} to get content for it. How
 * this works is: Simply it replicates everything inside an {@link ItemStack} to something Minecraft would
 * understand. When you use this content with {@link HoverEvent#HoverEvent(HoverEvent.Action, Content...)}, it
 * gets this content as an item NBT data. This is why most of the item showers use NMS, because they need an
 * exact same copy of the items nbt data, which seems like accessible only using nms. But with this class, you
 * can show most of the normal items in game. {@link ItemContent#from(ItemStack)} copies all the data inside
 * {@link ItemStack} to itself exactly, however some display names might be lost at the items inside a shulker
 * box, even with the fact that their names get saved too. Some extreme items (like a ban shulker or a cursed
 * bundle) have a small chance to make some lag in the server, maybe, just maybe, even crash it.
 */
public class ItemContent extends Content {
    @Override
    public HoverEvent.Action requiredAction() {
        return HoverEvent.Action.SHOW_ITEM;
    }
    /**
     * Item id of this item. String representation of a {@link NamespacedKey} as well.
     */
    private final String id;
    /**
     * Count of the item. Important for items inside shulker boxes.
     */
    private final Integer Count;
    /**
     * All the other data about item. Display, enchantments, lore etc...
     */
    private final String tag;
    /**
     * Number that indicates the slot of this item. Important for items inside shulker boxes.
     */
    private final Integer Slot;
    /**
     * Getter for {@link #id}
     * @return Item id of this content.
     */
    public String getId() {
        return id;
    }
    /**
     * Getter for {@link #Count}
     * @return Count of this content.
     */
    public Integer getCount() {
        return Count;
    }
    /**
     * Getter for {@link #tag}
     * @return The tag which is used to store entire tag data. It is actually a {@link Tag}, but gets converted
     * to {@link String} inside an {@link ItemContent} so Minecraft will understand.
     */
    public String getTag() {
        return tag;
    }
    /**
     * Getter for {@link #Slot}
     * @return Slot of the item.
     */
    public Integer getSlot() {
        return Slot;
    }
    /**
     * Constructs an {@link ItemContent} without a slot. Since {@link #Slot} is only important for contents that
     * will be in under shulker box content, you can use this constructor for other cases.
     * @param id ID of the item. Recommended: {@link NamespacedKey#getNamespace()}+':'+{@link NamespacedKey#getKey()}.
     * @param count Amount of the item. Recommended: {@link ItemStack#getAmount()}.
     * @param tag Actual data about the item. Recommended: {@link ItemTag#ItemTag(Integer, Integer, Display, boolean, Integer, EnchantmentCompound[], Integer)}
     *            or {@link ItemContent#from(ItemStack)} if you don't want to struggle constructing a {@link Tag}.
     */
    public ItemContent(String id, Integer count, ItemTag tag) {
        this.id = id;
        Count = count;
        this.tag = new Gson().toJson(tag)
                .replace("\"Colors\":[","\"Colors\":[I;")
                .replace("\"FadeColors\":[","\"FadeColors\":[I;");
        this.Slot = null;
    }
    /**
     * Constructs an {@link ItemContent} with the data given.
     * @param id ID of the item. Recommended: {@link NamespacedKey#getNamespace()}+':'+{@link NamespacedKey#getKey()}.
     * @param count Amount of the item. Recommended: {@link ItemStack#getAmount()}.
     * @param tag Actual data about the item. Recommended: {@link ItemTag#ItemTag(Integer, Integer, Display, boolean, Integer, EnchantmentCompound[], Integer)}
     *            or {@link ItemContent#from(ItemStack, Integer)} if you don't want to struggle constructing an {@link ItemTag}.
     * @param slot Slot of the item. Only important if this content will be under a shulker box content.
     */
    public ItemContent(String id, Integer count, ItemTag tag, Integer slot) {
        this.id = id;
        Count = count;
        this.tag = clearTagString(new Gson().toJson(tag)
                .replace("\"Colors\":[","\"Colors\":[I;")
                .replace("\"FadeColors\":[","\"FadeColors\":[I;"));
        Slot = slot;
    }
    /**
     * Generates an {@link ItemContent} usable in {@link HoverEvent#HoverEvent(HoverEvent.Action, Content...)} for
     * the {@link ItemStack} given.
     * @param stack Any {@link ItemStack} you want.
     * @return An almost exact copy of the NBT data about the item. Can be used mainly in {@link HoverEvent#HoverEvent(HoverEvent.Action, Content...)}.
     */
    public static ItemContent from(ItemStack stack){
        return from(stack,null);
    }
    /**
     * Generates an {@link ItemContent} usable in {@link HoverEvent#HoverEvent(HoverEvent.Action, Content...)} for
     * the {@link ItemStack} given. But instead of {@link #from(ItemStack)}, this also add a "Slot" value. It is
     * something important for content inside shulker boxes.
     * @param stack Any {@link ItemStack} you want.
     * @param slot Value you want to put in "Slot".
     * @return An almost exact copy of the NBT data about the item. Can be used mainly in {@link HoverEvent#HoverEvent(HoverEvent.Action, Content...)}.
     */
    public static ItemContent from(ItemStack stack,Integer slot){
        NamespacedKey key = stack.getType().getKey();
        String id = key.getNamespace()+":"+key.getKey();

        ItemMeta meta = stack.getItemMeta();

        ItemTag itemTag = new ItemTag(0, calculateHideFlags(meta.getItemFlags()), new Display(getName(meta), getLore(meta)), meta.isUnbreakable(), meta.hasCustomModelData() ? meta.getCustomModelData() : null, getEnchantsCompounds(meta.getEnchants()),0);

        if(meta instanceof EnchantmentStorageMeta enchantmentStorage){
            itemTag = new EnchantmentStorageTag(itemTag,getEnchantsCompounds(enchantmentStorage.getStoredEnchants()));
        }
        if(meta instanceof BookMeta bookMeta){
            itemTag = new BookTag(itemTag,bookMeta.hasTitle()?bookMeta.getTitle():null,bookMeta.hasAuthor()?bookMeta.getAuthor():null, bookMeta.hasGeneration()?getBookGeneration(bookMeta.getGeneration()):null);
        }
        if(meta instanceof MapMeta mapMeta){
            itemTag = new MapTag(itemTag,mapMeta.getMapView().getId());
        }
        if(meta instanceof FireworkMeta fireworkMeta){
            List<ExplosionCompound> compounds = new ArrayList<>();
            for (FireworkEffect effect : fireworkMeta.getEffects()) {

                compounds.add(new ExplosionCompound(effect.hasFlicker(),effect.hasTrail(),calculateExplosionType(effect.getType()), effect.getColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList()), effect.getFadeColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList())));
            }

            itemTag = new FireworkTag(itemTag,new FireworkCompound(fireworkMeta.getPower(),compounds.toArray(new ExplosionCompound[0])));
        }
        if(meta instanceof BannerMeta bannerMeta){
            List<BannerPatternCompound> patternCompounds = new ArrayList<>();

            for (Pattern pattern : bannerMeta.getPatterns()) {
                patternCompounds.add(new BannerPatternCompound(pattern.getPattern().getIdentifier(), calculatePatternColor(pattern.getColor())));
            }

            itemTag = new BannerTag(itemTag,new BannerCompound(calculatePatternColor(stack),patternCompounds.toArray(new BannerPatternCompound[0])));
        }
        if(meta instanceof SkullMeta skullMeta){
            if(skullMeta.hasOwner())
                itemTag = new SkullTag(itemTag,skullMeta.getOwningPlayer().getName());
        }
        if(meta instanceof SuspiciousStewMeta suspiciousStewMeta){
            List<SuspiciousStewEffectCompound> compounds = new ArrayList<>();
            for (PotionEffect effect : suspiciousStewMeta.getCustomEffects()) {
                compounds.add(new SuspiciousStewEffectCompound(calculatePotionEffect(effect.getType()),effect.getDuration()));
            }

            itemTag = new SuspiciousStewTag(itemTag,compounds.toArray(SuspiciousStewEffectCompound[]::new));
        }
        if(meta instanceof Damageable dMeta){
            if(dMeta.hasDamage()) itemTag.Damage = dMeta.getDamage();
        }
        if(meta instanceof LeatherArmorMeta leatherArmorMeta){
            itemTag.display = new LeatherArmorDisplay(getName(meta),getLore(meta),calculateDecimalColor(leatherArmorMeta.getColor()));
        }
        if(meta instanceof PotionMeta potionMeta){

            boolean isCustom = potionMeta.hasCustomEffects()&&potionMeta.hasColor();

            if(isCustom){
                List<PotionEffectCompound> compounds = new ArrayList<>();
                for (PotionEffect effect : potionMeta.getCustomEffects()) {
                    compounds.add(new PotionEffectCompound(calculatePotionEffect(effect.getType()),effect.getDuration(),effect.getAmplifier(),effect.hasParticles(),effect.isAmbient(),effect.hasIcon()));
                }

                itemTag = new PotionTag(itemTag,compounds.toArray(new PotionEffectCompound[0]),calculateDecimalColor(potionMeta.getColor()));
            } else {
                PotionData data = potionMeta.getBasePotionData();
                String baseName = getBasePotionName(data);
                String strong = data.isUpgraded() ? "strong_" : "";
                String _long = data.isExtended() ? "long_" : "";

                itemTag = new PotionTag(itemTag,"minecraft:"+_long+strong+baseName);
            }
        }
        if(meta instanceof CompassMeta compassMeta){

            if(compassMeta.hasLodestone()){
                Location lodestone = compassMeta.getLodestone();
                String worldKey = lodestone.getWorld().getName();

                itemTag = new CompassTag(itemTag,new CompassPositionCompound(lodestone.getBlockX(),lodestone.getBlockY(),lodestone.getBlockZ()), worldKey, compassMeta.isLodestoneTracked());
            }
        }
        if(meta instanceof CrossbowMeta crossbowMeta){
            if(crossbowMeta.hasChargedProjectiles()){
                itemTag = new CrossbowTag(itemTag,crossbowMeta.getChargedProjectiles().stream().map(ItemContent::from).collect(Collectors.toList()), true);
            } else {
                itemTag = new CrossbowTag(itemTag,new ArrayList<>(),false);
            }
        }
        if(meta instanceof Repairable repairable){
            if(repairable.hasRepairCost()) itemTag.RepairCost = repairable.getRepairCost();
        }
        if(meta instanceof KnowledgeBookMeta knowledgeBookMeta){
            List<String> recipes = knowledgeBookMeta.getRecipes().stream().map(namespacedKey -> namespacedKey.getNamespace() + ":" + namespacedKey.getKey()).collect(Collectors.toList());
            itemTag = new KnowledgeBookTag(itemTag,recipes);
        }
        if(meta instanceof BlockStateMeta blockStateMeta) {
            BlockState state = blockStateMeta.getBlockState();
            if(state instanceof ShulkerBox shulkerBox){
                List<ItemContent> contents = new ArrayList<>();
                for (int i = 0; i < 27; i++) {
                    ItemStack shulkerItem = shulkerBox.getInventory().getItem(i);
                    if(shulkerItem==null)continue;
                    contents.add(from(shulkerItem,i));
                }
                itemTag = new ShulkerBoxTag(itemTag,new ShulkerBoxCompound(contents));
            }
            // NO WAY I'M CHECKING ALL SUBCLASSES OF BLOCKSTATE JUST TO MAKE YOU SEE "(+NBT)". OKAY?
        }
        if(meta instanceof FireworkEffectMeta effectMeta){
            if(effectMeta.hasEffect()){
                FireworkEffect effect = effectMeta.getEffect();
                assert effect != null;
                itemTag = new FireworkEffectTag(itemTag,new ExplosionCompound(effect.hasFlicker(),effect.hasTrail(),calculateExplosionType(effect.getType()),effect.getColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList()), effect.getFadeColors().stream().map(ItemContent::calculateDecimalColor).collect(Collectors.toList())));
            }
        }

        return new ItemContent(id, stack.getAmount(), itemTag,slot);
    }
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    // ACTUAL ITEM CONTENT
    // UTILS
    // ˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅˅
    /**
     * Removes default values from the tag string. Example: At {@code {"text":"hi","bold":false}}, {@code "bold":false}
     * is useless because texts are not bold by default. This method removes it from the tag.
     * @param tagString {@link String} version of a {@link Tag}.
     * @return Filtered version of the tag given.
     */
    public static String clearTagString(String tagString){
        return tagString
                .replace(",\"HideFlags\":0","")
                .replace(",\"Damage\":0","")
                .replace(",\"RepairCost\":0","")
                .replace(",\"Unbreakable\":false","")
                .replace(",\"Enchantments\":[]","")
                .replace(",\"Explosions\":[]","")
                .replace(",\"Lore\":[]","")
                .replace(",\\\"HideFlags\\\":0","")
                .replace(",\\\"Damage\\\":0","")
                .replace(",\\\"RepairCost\\\":0","")
                .replace(",\\\"Unbreakable\\\":false","")
                .replace(",\\\"Enchantments\\\":[]","")
                .replace(",\\\"Explosions\\\":[]","")
                .replace(",\\\"Lore\\\":[]","")
                .replace(",\\\"Lore\\\":[]","")
                .replace(",\\\"obfuscated\\\":false","")
                .replace(",\\\"underlined\\\":false","")
                .replace(",\\\"strikethrough\\\":false","")
                .replace(",\\\"bold\\\":false","")
                .replace(",\\\\\\\"obfuscated\\\\\\\":false","")
                .replace(",\\\\\\\"underlined\\\\\\\":false","")
                .replace(",\\\\\\\"strikethrough\\\\\\\":false","")
                .replace(",\\\\\\\"bold\\\\\\\":false","");
    }
    /**
     * Generates {@link EnchantmentCompound}s for every enchantment inside the {@link Map} given.
     * @param enchantments A {@link Map} of the enchantments. Recommended: {@link ItemMeta#getEnchants()}
     * @return An array that contains and {@link EnchantmentCompound} for every enchantment given.
     */
    public static EnchantmentCompound[] getEnchantsCompounds(Map<Enchantment,Integer> enchantments){
        List<EnchantmentCompound> enchantCompounds = new ArrayList<>();

        enchantments.forEach((enchantment, integer) -> enchantCompounds.add(new EnchantmentCompound(enchantment.getKey().getNamespace()+":"+enchantment.getKey().getKey(),integer)));
        return enchantCompounds.toArray(new EnchantmentCompound[0]);
    }
    /**
     * Generates {@link TextCompound}s for the display name under the meta given.
     * @param meta Any {@link ItemMeta}.
     * @return An array of {@link TextCompound} if {@link ItemMeta#hasDisplayName()}.
     */
    public static TextCompound[] getName(ItemMeta meta){
        if(!meta.hasDisplayName()) return null;

        BaseComponent[] bases = TextComponent.fromLegacyText(meta.getDisplayName());
        List<TextCompound> compounds = new ArrayList<>();
        for (BaseComponent baseComponent : bases) {
            if(baseComponent instanceof TextComponent)

                compounds.add(textCompoundFrom((TextComponent) baseComponent));
        }

        return compounds.toArray(new TextCompound[0]);
    }
    /**
     * Converts a {@link TextComponent} to a {@link TextCompound}.
     * @param component Any {@link TextComponent} that you want to convert.
     * @return A {@link TextCompound} representing the component given.
     */
    public static TextCompound textCompoundFrom(TextComponent component){
        List<TextCompound> extras = new ArrayList<>();
        if(component.getExtra()!=null){
            for (BaseComponent baseComponent : component.getExtra()) {
                if(baseComponent instanceof TextComponent){
                    extras.add(textCompoundFrom((TextComponent) baseComponent));
                }
            }
        }
        return new TextCompound(component.isItalic(),component.isBold(),component.isUnderlined(),component.isStrikethrough(),component.getColor(), component.getText(),component.isObfuscated(), !extras.isEmpty() ?extras:null);
    }
    /**
     * Generates arrays of {@link TextCompound} for the lore, each array representing one line of the lore.
     * @param meta Any {@link ItemMeta}.
     * @return Arrays of the lore inside an array.
     */
    public static TextCompound[][] getLore(ItemMeta meta){
        List<TextCompound[]> loreCompounds = new ArrayList<>();
        List<String> lore = meta.getLore();
        if(lore == null) lore = new ArrayList<>();
        for (String s : lore) {
            BaseComponent[] components = TextComponent.fromLegacyText(s);
            TextCompound[] loreLineCompounds = Arrays.stream(components.clone()).map(baseComponent -> textCompoundFrom((TextComponent) baseComponent)).toArray(TextCompound[]::new);
            loreCompounds.add(loreLineCompounds);
        }
        return loreCompounds.toArray(new TextCompound[0][0]);
    }
    /**
     * Generates a number for "HideFlags" property from the flags given.
     * @param flags A {@link Set} of {@link ItemFlag}s. Recommended: {@link ItemMeta#getItemFlags()}
     * @return A number that Minecraft will consider as a list of the flags given.
     */
    public static Integer calculateHideFlags(Set<ItemFlag> flags){
        int hideFlagsBit = 0;
        for (ItemFlag flag : flags) {
            switch (flag){
                case HIDE_DYE: hideFlagsBit += 64;
                case HIDE_UNBREAKABLE: hideFlagsBit += 4;
                case HIDE_ENCHANTS: hideFlagsBit += 1;
                case HIDE_PLACED_ON: hideFlagsBit += 16;
                case HIDE_DESTROYS: hideFlagsBit += 8;
                case HIDE_ATTRIBUTES: hideFlagsBit += 2;
                case HIDE_POTION_EFFECTS:hideFlagsBit+=32;
            }
        }
        return hideFlagsBit;
    }
    /**
     * Converts a {@link org.bukkit.inventory.meta.BookMeta.Generation} to a number that Minecraft will understand.
     * @param generation Generation of the book. Recommended: {@link BookMeta#getGeneration()}.
     * @return Numeric ID of the generation given.
     */
    public static Integer getBookGeneration(BookMeta.Generation generation){
        if(generation==null)return 0;
        return switch (generation) {
            case COPY_OF_ORIGINAL -> 1;
            case COPY_OF_COPY -> 2;
            case TATTERED -> 3;
            default -> 0;
        };
    }
    /**
     * Converts type of {@link FireworkEffect} to a number that Minecraft will understand.
     * @param type Type of the effect. Recommended: {@link FireworkEffect#getType()}.
     * @return Numeric ID of the type given.
     */
    public static Integer calculateExplosionType(FireworkEffect.Type type){
        return switch (type) {
            case BALL_LARGE -> 1;
            case STAR -> 2;
            case CREEPER -> 3;
            case BURST -> 4;
            default -> 0;
        };
    }
    /**
     * Converts a {@link DyeColor} to a number for uses inside a banner pattern.
     * @param color Any {@link DyeColor}.
     * @return Numeric ID of the color given.
     */
    public static Integer calculatePatternColor(DyeColor color){
        return switch (color) {
            case ORANGE -> 1;
            case MAGENTA -> 2;
            case LIGHT_BLUE -> 3;
            case YELLOW -> 4;
            case LIME -> 5;
            case PINK -> 6;
            case GRAY -> 7;
            case LIGHT_GRAY -> 8;
            case CYAN -> 9;
            case PURPLE -> 10;
            case BLUE -> 11;
            case BROWN -> 12;
            case GREEN -> 13;
            case RED -> 14;
            case BLACK -> 15;
            default -> 0;
        };
    }
    /**
     * Same with {@link #calculatePatternColor(DyeColor)}, but this time it does it based on {@link Material} of the item given.
     * @param stack Any {@link ItemStack} with its {@link Material} being one of the banners.
     * @return Numeric ID for the banner type.
     */
    public static Integer calculatePatternColor(ItemStack stack){
        return switch (stack.getType()) {
            case ORANGE_BANNER -> 1;
            case MAGENTA_BANNER -> 2;
            case LIGHT_BLUE_BANNER -> 3;
            case YELLOW_BANNER -> 4;
            case LIME_BANNER -> 5;
            case PINK_BANNER -> 6;
            case GRAY_BANNER -> 7;
            case LIGHT_GRAY_BANNER -> 8;
            case CYAN_BANNER -> 9;
            case PURPLE_BANNER -> 10;
            case BLUE_BANNER -> 11;
            case BROWN_BANNER -> 12;
            case GREEN_BANNER -> 13;
            case RED_BANNER -> 14;
            case BLACK_BANNER -> 15;
            default -> 0;
        };
    }
    /**
     * Converts a {@link PotionEffectType} to a number Minecraft will understand.
     * @param type Any {@link PotionEffectType} you want.
     * @return Numeric ID of the effect type given.
     */
    public static NamespacedKey calculatePotionEffect(PotionEffectType type){
        return new NamespacedKey("minecraft",type.getName());
    }
    /**
     * Converts a {@link Color} to a decimal color, a color format used for leather armors and fireworks in Minecraft.
     * @param color Any {@link Color}
     * @return An {@link Integer} for the color given, found using this formula: {@code (Red << 16) + (Green << 8) + Blue}.
     */
    public static Integer calculateDecimalColor(Color color){
        int Red = color.getRed();
        int Blue = color.getBlue();
        int Green = color.getGreen();

        return (Red << 16) + (Green << 8) + Blue;
    }
    /**
     * Converts a {@link PotionData} to a string Minecraft will understand.
     * @param data Any {@link PotionData}. Recommended: {@link PotionMeta#getBasePotionData()}.
     * @return String ID of the base potion given.
     */
    public static String getBasePotionName(PotionData data){
        return switch (data.getType()) {
            case JUMP -> "leaping";
            case SPEED -> "swiftness";
            case TURTLE_MASTER -> "turtle_master";
            case INSTANT_HEAL -> "healing";
            case INSTANT_DAMAGE -> "harming";
            default -> data.getType().getEffectType().getName();
        };
    }
    public static NamespacedKey namespacedKeyFromString(String value){
        String[] split = value.split(":");
        return new NamespacedKey(split[0],split[1]);
    }
}
