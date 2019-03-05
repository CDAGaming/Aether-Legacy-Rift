package com.legacy.aether.client.gui.dialogue.server;

import io.netty.buffer.Unpooled;

import java.util.ArrayList;

import net.minecraft.client.MinecraftClient;
import net.minecraft.server.network.packet.CustomPayloadC2SPacket;
import net.minecraft.util.PacketByteBuf;

import com.google.common.collect.Lists;
import com.legacy.aether.Aether;
import com.legacy.aether.client.gui.dialogue.DialogueOption;
import com.legacy.aether.client.gui.dialogue.GuiDialogue;

public class GuiServerDialogue extends GuiDialogue
{

	private String dialogueName;

	public GuiServerDialogue(String dialogueName, String dialogue, ArrayList<String> dialogueText)
	{
		super(dialogue);

		this.dialogueName = dialogueName;

		ArrayList<DialogueOption> dialogueOptions = Lists.newArrayList();

		for (String dialogueForOption : dialogueText)
		{
			dialogueOptions.add(new DialogueOption(dialogueForOption));
		}

        this.addDialogueOptions(dialogueOptions.toArray(new DialogueOption[] {}));
	}

	@Override
	public void dialogueClicked(DialogueOption dialogue)
	{
		PacketByteBuf buffer = new PacketByteBuf(Unpooled.buffer());

		buffer.writeString(this.dialogueName);
		buffer.writeInt(dialogue.getDialogueId());

		MinecraftClient.getInstance().player.networkHandler.sendPacket(new CustomPayloadC2SPacket(Aether.locate("dialogue_clicked"), buffer));

		this.dialogueTreeCompleted();
	}

}