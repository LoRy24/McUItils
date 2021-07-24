# McUItils
This is a simple Minecraft 1.8 API that helps you to create a custom GUI Menus with cool features, like
buttons or custom heads.

## How to use it:

All the documentation about this API can be found at the sequent link:
https://lory24.gitbook.io/mcuitils/ <br>

### Here are some important things about this API

Using this API, you can build Minecraft's GUI in a very simple way. So, when you want to create
an MC GUI, you have to create the McGUI object like above:
```java
McGUI gui = new McGUI("GUI Title", GuiLines.LINES, MainClass.getInstance());
```

After creating the GUI, you can also create a custom item and subsequently create a button into
the interface. here is an example:
```java
GUItem item = new GUICustomItem(Material.BARRIER).setName("§cName");
```

When you have created a custom item, you can now insert it into the GUI using the **gui.createButton()** 
method, writing something like this:
```java
gui.createButton(item, 4, new GUIButtonEvents(() -> {
    // put here all the actions for when you interact with the button
}));
```

After having created all the menu features, you can open the GUI to a specific player, using the
gui.openInventoryTo() method. Remember: the parameter is the player who you want to open the gui.
Here is an example:
```java
gui.openInventoryTo(Bukkit.getPlayerExact("PlayerName"));
```

## License
You can read the license into the LICENSE file. Remember also that the project is open-source, so
happy coding!