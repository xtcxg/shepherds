package com.miex.shepherds.utils;

import java.util.UUID;
import java.util.zip.CRC32;

public class SnowFlakeUtil {

    private static final long workerId = 23;
    private static final long datacenterId = 22L;
    private static long sequence =0L;

    private static final long twepoch = 1288834974657L;

    private static final long WORKER_ID_BITS = 5L;
    private static final long DATACENTER_ID_BITS = 10L - WORKER_ID_BITS;
    private static final long SEQUENCE_BITS = 12L;

    private static final long MAX_WORKER_ID = ~(-1L << WORKER_ID_BITS);
    private static final long MAX_DATACENTER_ID = ~(-1L << DATACENTER_ID_BITS);

    private static final long WORKER_ID_SHIFT = SEQUENCE_BITS;
    private static final long DATACENTER_ID_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS;
    private static final long TIMESTAMP_LEFT_SHIFT = SEQUENCE_BITS + WORKER_ID_BITS + DATACENTER_ID_BITS;
    private static final long SEQUENCE_MASK = ~(-1L << SEQUENCE_BITS);
    private static long lastTimestamp = -1L;

    public synchronized  static long getId(){
        long timestamp = System.currentTimeMillis();

        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0",MAX_WORKER_ID));
        }
        if (datacenterId > MAX_DATACENTER_ID || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0",MAX_DATACENTER_ID));
        }
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("clock is moving backwards, rejecting request until " + lastTimestamp);
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        return ((timestamp - twepoch) << TIMESTAMP_LEFT_SHIFT) |
                (datacenterId << DATACENTER_ID_SHIFT) |
                (workerId << WORKER_ID_SHIFT) |
                sequence;
    }
    public synchronized  static String getCrc(){
//        String id = getId() + "";
        String id = UUID.randomUUID().toString();
        CRC32 crc = new CRC32();
        crc.reset();
        crc.update(id.getBytes(),0,id.length());

        return Long.toHexString(crc.getValue());
    }

    private static long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }
}
