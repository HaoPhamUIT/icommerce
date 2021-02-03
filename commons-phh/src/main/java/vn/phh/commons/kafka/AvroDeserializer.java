package vn.phh.commons.kafka;

import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.serialization.Deserializer;
import vn.phh.commons.constants.CommonConstants;
import vn.phh.kafka.message.OrderAvro;

import java.lang.reflect.Type;
import java.util.Map;

public class AvroDeserializer<T extends SpecificRecordBase> implements Deserializer<T> {

    public final Class<T> targetType;

    public AvroDeserializer(Class<T> targetType) {
        this.targetType = targetType;
    }

    @Override
    public void close() {
        // No-op
    }

    @Override
    public void configure(Map<String, ?> arg0, boolean arg1) {
        // No-op
    }

    @SuppressWarnings("unchecked")
    @Override
    public T deserialize(String topic, byte[] data) {
        Type type;
        switch (topic) {
            case CommonConstants.KAFKA_TOPIC_ORDER:
                type = OrderAvro.class;
                break;
            default:
                type = null;
        }
        return cast(type, data);
    }

    private T cast(Type type, byte[] data) {
        try {
            T result = null;

            if (data != null) {
                DatumReader<GenericRecord> datumReader = new SpecificDatumReader<>(
                        ReflectData.get().getSchema(type));
                Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);

                result = (T) datumReader.read(null, decoder);
            }
            return result;
        } catch (Exception ex) {
            return null;
        }
    }
}
