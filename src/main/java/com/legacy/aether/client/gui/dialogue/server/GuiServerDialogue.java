package com.legacy.aether.client.gui.dialogue.server;

import com.google.common.collect.Lists;
import com.legacy.aether.Aether;
import com.legacy.aether.client.gui.dialogue.DialogueOption;
import com.legacy.aether.client.gui.dialogue.GuiDialogue;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;

import java.util.ArrayList;

public class GuiServerDialogue extends GuiDialogue {

    private String dialogueName;

    public GuiServerDialogue(String dialogueName, String dialogue, ArrayList<String> dialogueText) {
        super(dialogue);

        this.dialogueName = dialogueName;

        ArrayList<DialogueOption> dialogueOptions = Lists.newArrayList();

        for (String dialogueForOption : dialogueText) {
            dialogueOptions.add(new DialogueOption(dialogueForOption));
        }

        this.addDialogueOptions(dialogueOptions.toArray(new DialogueOption[]{}));
    }

    @Override
    public void dialogueClicked(DialogueOption dialogue) {
        PacketBuffer buffer = new PacketBuffer(Unpooled.buffer());

        buffer.writeString(this.dialogueName);
        buffer.writeInt(dialogue.getDialogueId());

        Minecraft.getMinecraft().player.connection.sendPacket(new CPacketCustomPayload(Aether.locate("dialogue_clicked"), buffer));

        this.dialogueTreeCompleted();
    }

}