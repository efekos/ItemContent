# ItemContent

ItemContent is a class that represents an ItemStack fully compatible with hover events for Spigot API. Most of the spigot
plugins use really complicated looking NMS stuff to show an item in chat. But this repository makes the process a lot easier.
All you have to do is pass in an `ItemContent` as the content argument, and that's it! You can create custom `ItemContent`s
(trust me, you don't want to try this way), or use the parser method `ItemContent#from(ItemStack)`.