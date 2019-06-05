package me.ipodtouch0218.iptcore.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.ipodtouch0218.iptcore.inventory.elements.GuiElement;

public class GuiInventory {

	private Inventory inv;
	private int size;
	private GuiElement[] elements;
	
	public GuiInventory(int size, String title, GuiElement... elements) {
		if (size % 9 != 0 || size <= 0) {
			throw new IllegalArgumentException("Size must be a multiple of 9!");
		}
		this.size = size;
		this.elements = elements;

		if (title != null) {
			inv = Bukkit.createInventory(null, size, ChatColor.translateAlternateColorCodes('&', title));
		} else {
			inv = Bukkit.createInventory(null, size);
		}
		updateInventory();
	}
	
	//---METHODS---//
	public void updateInventory() {
		if (elements == null) { return; }
		for (int i = 0; i < elements.length; i++) {
			ItemStack item = null;
			if (elements[i] != null) {
				item = elements[i].getItem();
			}
			inv.setItem(i, item);
		}
	}
	
	//---SETTERS---//
	public void setElement(int slot, GuiElement element) {
		if (slot < 0 || slot >= size) {
			throw new IllegalArgumentException("Slot # must be between 0 and the containers size! (" + size + ")");
		}
		elements[slot] = element;
		updateInventory();
	}	
	
	//---GETTERS---//
	public Inventory getInventory() { return inv; }
	public int getSize() { return size; }
	public GuiElement[] getElements() { return elements; }
	public GuiElement getElement(int slot) { 
		if (slot < 0 || slot >= size) { return null; }
		return elements[slot];
	}
}
