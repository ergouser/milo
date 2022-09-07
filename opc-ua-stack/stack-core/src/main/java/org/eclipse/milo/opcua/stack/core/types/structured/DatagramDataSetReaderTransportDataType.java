package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.6.5">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.6.5</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class DatagramDataSetReaderTransportDataType extends DataSetReaderTransportDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23614");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23866");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23934");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=24002");

    private final NetworkAddressDataType address;

    private final String qosCategory;

    private final ReceiveQosDataType[] datagramQos;

    private final String topic;

    public DatagramDataSetReaderTransportDataType(NetworkAddressDataType address, String qosCategory,
                                                  ReceiveQosDataType[] datagramQos, String topic) {
        this.address = address;
        this.qosCategory = qosCategory;
        this.datagramQos = datagramQos;
        this.topic = topic;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
    }

    public NetworkAddressDataType getAddress() {
        return address;
    }

    public String getQosCategory() {
        return qosCategory;
    }

    public ReceiveQosDataType[] getDatagramQos() {
        return datagramQos;
    }

    public String getTopic() {
        return topic;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23866),
            new NodeId(0, 15628),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Address", LocalizedText.NULL_VALUE, new NodeId(0, 15502), -1, null, UInteger.valueOf(0), false),
                new StructureField("QosCategory", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DatagramQos", LocalizedText.NULL_VALUE, new NodeId(0, 23608), 1, null, UInteger.valueOf(0), false),
                new StructureField("Topic", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DatagramDataSetReaderTransportDataType> {
        @Override
        public Class<DatagramDataSetReaderTransportDataType> getType() {
            return DatagramDataSetReaderTransportDataType.class;
        }

        @Override
        public DatagramDataSetReaderTransportDataType decode(SerializationContext context,
                                                             UaDecoder decoder) {
            NetworkAddressDataType address = (NetworkAddressDataType) decoder.readStruct("Address", NetworkAddressDataType.TYPE_ID);
            String qosCategory = decoder.readString("QosCategory");
            ReceiveQosDataType[] datagramQos = (ReceiveQosDataType[]) decoder.readStructArray("DatagramQos", ReceiveQosDataType.TYPE_ID);
            String topic = decoder.readString("Topic");
            return new DatagramDataSetReaderTransportDataType(address, qosCategory, datagramQos, topic);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DatagramDataSetReaderTransportDataType value) {
            encoder.writeStruct("Address", value.getAddress(), NetworkAddressDataType.TYPE_ID);
            encoder.writeString("QosCategory", value.getQosCategory());
            encoder.writeStructArray("DatagramQos", value.getDatagramQos(), ReceiveQosDataType.TYPE_ID);
            encoder.writeString("Topic", value.getTopic());
        }
    }
}