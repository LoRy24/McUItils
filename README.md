# McUItils
This is a simple API to simplify GUI creation in Minecraft

## How to use it:
#### Create the gui
The first thing that you have to do is create the GUI object. To do that, you have to write:
```java
McGUI gui = new McGUI("GuiName", GuiLines.LINES, main.instance);
```

After do this, you can start inserting items in there. For example, if you want to fill the empty slots with
a block, you can write:

```java
gui.fillBlacksWith(Material.material, name);
```

#### Create a button

The most important things that you can add to the gui are... buttons! So... if you want to create one,
you have to do this steps: <br> <br>

First, you have to create the GUI item.
```java
GUItem item = new GUICustomItem(Material.material); // Create the custom item object
```

So you can also add more details at the item, for example a custom name, a description, a type and more.
You can make it in to ways:
```java
// 1 | If you want to do it after creating the object:
item.setName("§cDisplayName"); // Set a name to the item
item.setAmount(1); // Set the amount of the item
item.setLore("Line1", "Line2"); // Set the description of the item by strings
item.setLore(arrayList); // Set the description of the item by arrayList

// 2 | If you want to do it inside the object declaration:
GUItem item2 = new GUICustomItem(Material.material).setName("§cName");
```
After this, you can add the item to the GUI as a button
```java
// Remember: The slot is the array index, so it starts counting from 0 (First line: 0-8 and not 1-9)

// If you want to create an item with a generic event listener, so it execute the click actions when you interact
// with the button using every types of interactions (Such as left click, right click or drop key).
gui.createButton(item, slot, new GUIButtonEvents(() -> {
    // Write actions here
}));

// If you want to separate left click and right click event actions
gui.createButton(item, slot, new GUIButtonEvents(() -> {
    // Write left click actions here
}, () -> { /* Write right click actions here */ }));
```

#### Open the gui

The last thing to do and also the most important is... opening the gui! You can do that writing this:
```java
gui.openInventoryTo(player);
```

### Working with heads
If you want to put a player head into the GUI, you can use a special item class named **GUIHead**.
So here are some example of its methods:
```java
GUIHead head = new GUIHead();
head.setSkullOwner(ownerName); // Set the owner of the head
```

If you want to set a custom head texture to your skull, you can use the method called setCustomTexture.
```java
head.setCustomTexture(base64formatTexture);
```

## License
You can read the license into the LICENSE file. Remember also that the project is open-source, so
happy coding!