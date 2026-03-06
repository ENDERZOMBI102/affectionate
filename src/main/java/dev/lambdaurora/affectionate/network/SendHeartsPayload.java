package dev.lambdaurora.affectionate.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

import static dev.lambdaurora.affectionate.Affectionate.id;


public record SendHeartsPayload(int playerId) implements CustomPacketPayload {
	public static final StreamCodec<FriendlyByteBuf, SendHeartsPayload> CODEC = CustomPacketPayload.codec(SendHeartsPayload::write, SendHeartsPayload::new);
	public static final CustomPacketPayload.Type<SendHeartsPayload> TYPE = new CustomPacketPayload.Type<>(id("send_hearts"));

	public SendHeartsPayload(FriendlyByteBuf buf) {
		this(buf.readVarInt());
	}

	private void write(FriendlyByteBuf friendlyByteBuf) {
		friendlyByteBuf.writeVarInt(this.playerId);
	}

	@Override
	public @NotNull Type<? extends CustomPacketPayload> type() {
		return TYPE;
	}
}
