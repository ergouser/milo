package org.eclipse.milo.opcua.stack.core.types.enumerated;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumField;
import org.jetbrains.annotations.Nullable;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.6">https://reference.opcfoundation.org/v105/Core/docs/Part22/5.3.1/#5.3.1.6</a>
 */
public enum TsnStreamState implements UaEnumeration {
    /**
     * The related TSN Stream is currently disabled.
     */
    Disabled(0),

    /**
     * The related TSN Stream is in the process of receiving configuration parameters from the TSN Control Layer.
     */
    Configuring(1),

    /**
     * The related TSN Stream has successfully received and applied the configuration from the TSN Control Layer. The related TSN Stream is not fully operational as long as local preconditions (e.g. synchronization state) are not valid.
     */
    Ready(2),

    /**
     * The related TSN Stream object is configured and all other required preconditions (e.g. synchronization state) for sending / receiving data are valid.
     */
    Operational(3),

    /**
     * The related TSN Stream object is in an error state.
     */
    Error(4);

    private final int value;

    TsnStreamState(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("ns=0;i=24220");
    }

    public static @Nullable TsnStreamState from(int value) {
        switch (value) {
            case 0:
                return Disabled;
            case 1:
                return Configuring;
            case 2:
                return Ready;
            case 3:
                return Operational;
            case 4:
                return Error;
            default:
                return null;
        }
    }

    public static EnumDefinition definition() {
        return new EnumDefinition(new EnumField[]{
            new EnumField(0L, LocalizedText.NULL_VALUE, new LocalizedText("", "The related TSN Stream is currently disabled."), "Disabled"),
            new EnumField(1L, LocalizedText.NULL_VALUE, new LocalizedText("", "The related TSN Stream is in the process of receiving configuration parameters from the TSN Control Layer."), "Configuring"),
            new EnumField(2L, LocalizedText.NULL_VALUE, new LocalizedText("", "The related TSN Stream has successfully received and applied the configuration from the TSN Control Layer. The related TSN Stream is not fully operational as long as local preconditions (e.g. synchronization state) are not valid."), "Ready"),
            new EnumField(3L, LocalizedText.NULL_VALUE, new LocalizedText("", "The related TSN Stream object is configured and all other required preconditions (e.g. synchronization state) for sending / receiving data are valid."), "Operational"),
            new EnumField(4L, LocalizedText.NULL_VALUE, new LocalizedText("", "The related TSN Stream object is in an error state."), "Error")
        });
    }

    public static final class Codec extends GenericDataTypeCodec<TsnStreamState> {
        @Override
        public Class<TsnStreamState> getType() {
            return TsnStreamState.class;
        }

        @Override
        public TsnStreamState decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, TsnStreamState.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, TsnStreamState value) {
            encoder.writeEnum(null, value);
        }
    }
}