package com.legacy.aether.client.gui.dialogue;

import java.util.ArrayList;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.text.StringTextComponent;

public class GuiDialogue extends Screen
{

	private ArrayList<DialogueOption> dialogueOptions = new ArrayList<DialogueOption>();

	private String dialogue;

	public GuiDialogue(String dialogue)
	{
		this.dialogue = dialogue;
	}

	public GuiDialogue(String dialogue, DialogueOption... options)
	{
		this(dialogue);

		this.addDialogueOptions(options);
	}

    public void addDialogueWithOptions(String dialogue, DialogueOption... options) 
    {
    	this.dialogue = dialogue;

        this.dialogueOptions.clear();

        this.addDialogueOptions(options);
        this.positionDialogueOptions(this.getDialogueOptions());
    }

    public void initGui()
    {
    	this.positionDialogueOptions(this.getDialogueOptions());
    }

    private void positionDialogueOptions(ArrayList<DialogueOption> options)
    {
        int lineNumber = 0;

        for (DialogueOption option : options) 
        {
            option.setDialogueId(lineNumber);
            option.setXPosition((this.screenWidth / 2) - (option.getWidth() / 2));
            option.setYPosition((this.screenHeight / 2) + this.fontRenderer.wrapStringToWidthAsList(this.dialogue, 300).size() * 12 + 12 * lineNumber);

            lineNumber++;
        }
    }

	public void addDialogueOptions(DialogueOption... options)
	{
        for (DialogueOption option : options) 
        {
            this.dialogueOptions.add(option);
        }
	}

	public void addDialogueMessage(String dialogueMessage)
	{
		MinecraftClient.getInstance().inGameHud.getChatHud().addMessage(new StringTextComponent(dialogueMessage));
	}

	public void dialogueTreeCompleted()
	{
		this.client.openScreen(null);
	}

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

	@Override
    public void draw(int mouseX, int mouseY, float partialTicks)
    {
    	super.draw(mouseX, mouseY, partialTicks);

        int optionWidth = 0;

        for (String theDialogue : this.fontRenderer.wrapStringToWidthAsList(this.dialogue, 300))
        {
            int stringWidth = this.fontRenderer.getStringWidth(theDialogue);

        	this.drawGradientRect(this.screenWidth / 2 - stringWidth / 2 - 2, this.screenHeight / 2 + optionWidth * 12 - 2, this.screenWidth / 2 + stringWidth / 2 + 2, this.screenHeight / 2 + optionWidth * 10 + 10, 0x66000000, 0x66000000);
        	this.drawString(this.fontRenderer, theDialogue, this.screenWidth / 2 - stringWidth / 2, this.screenHeight / 2 + optionWidth * 10, 0xffffff);
        	++optionWidth;
        }

    	for (DialogueOption dialogue : this.dialogueOptions)
    	{
    		dialogue.renderDialogue(mouseX, mouseY);
    	}
    }

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
    {
		if (mouseButton == 0)
		{
	    	for (DialogueOption dialogue : this.dialogueOptions)
	    	{
	    		if (dialogue.isMouseOver(mouseX, mouseY))
	    		{
	    			dialogue.playPressSound(this.client.getSoundLoader());
	    			this.dialogueClicked(dialogue);
	    		}
	    	}
		}

    	return super.mouseClicked(mouseX, mouseY, mouseButton);
    }

	public void dialogueClicked(DialogueOption dialogue)
	{
		
	}

	public ArrayList<DialogueOption> getDialogueOptions()
	{
		return this.dialogueOptions;
	}

	public String getDialogue()
	{
		return this.dialogue;
	}

}