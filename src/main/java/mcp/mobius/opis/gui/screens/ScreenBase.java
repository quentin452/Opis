package mcp.mobius.opis.gui.screens;

import java.util.HashMap;

import mcp.mobius.opis.gui.interfaces.IWidget;
import mcp.mobius.opis.gui.widgets.LayoutCanvas;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Mouse;

public abstract class ScreenBase extends GuiScreen {

    protected GuiScreen parent; // Return screen if available.
    protected Minecraft mc; // Minecraft instance
    protected HashMap<String, IWidget> widgets; // List of widgets on this ui

    public ScreenBase(GuiScreen parent) {
        this.parent = parent;
        this.mc = Minecraft.getMinecraft();
        this.widgets = new HashMap<String, IWidget>();

        this.addWidget("canvas", new LayoutCanvas());

        Mouse.getDWheel(); // We init the DWheel method (getDWheel returns the value since the last call, so we have
        // to call it once on ui creation)
    }

    /////////////////////
    // WIDGET HANDLING //
    /////////////////////

    public IWidget addWidget(String name, IWidget widget) {
        this.widgets.put(name, widget);
        return this.getWidget(name);
    }

    public IWidget getWidget(String name) {
        return this.widgets.get(name);
    }

    public IWidget delWidget(String name) {
        IWidget widget = this.getWidget(name);
        // this.widgets.remove(widget);
        this.widgets.remove(name);
        return widget;
    }

    public IWidget getRoot() {
        return this.getWidget("canvas");
    }

    /////////////////////
    // DRAWING METHODS //
    /////////////////////

    @Override
    public void drawScreen(int i, int j, float f) {
        this.drawDefaultBackground(); // The dark background common to most of the UIs
        super.drawScreen(i, j, f);

        for (IWidget widget : this.widgets.values()) widget.draw();
    }

    // Used to easily display a UI without having to mess with mc handle.
    public void display() {
        this.parent = this.mc.currentScreen;
        this.mc.displayGuiScreen(this);
    }

    /////////////////////
    // INPUT METHODS //
    /////////////////////

    // Keyboard handling. Basic one is just returning to the previous UI when Esc is pressed
    @Override
    public void keyTyped(char keyChar, int keyID) {
        if (keyID == 1) if (this.parent == null) {
            this.mc.displayGuiScreen((GuiScreen) null);
            this.mc.setIngameFocus();
        } else this.mc.displayGuiScreen(this.parent);
    }

    @Override
    public void handleMouseInput() {
        this.getRoot().handleMouseInput();
    }
}
