package com.DashO.app.JEP_448;

import jdk.incubator.vector.*;

import java.lang.foreign.MemorySegment;
import java.nio.ByteOrder;

public class JEP_448_Vector_API {

    public static void laneWiseBitwise() {
        int[] values = {7, 2, 3, 4, 5, 1, 2, 3, 4, 5};

        VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;
        var upperBound = SPECIES.loopBound(values.length);
        for (int i = 0; i < upperBound; i += SPECIES.length()) {
            var valueVector = IntVector.fromArray(SPECIES, values, i);

            var bitCountResult = valueVector.lanewise(VectorOperators.BIT_COUNT);
            var leadingZerosResult = valueVector.lanewise(VectorOperators.LEADING_ZEROS_COUNT);
            var trailingZerosResult = valueVector.lanewise(VectorOperators.TRAILING_ZEROS_COUNT);
            var reverseResult = valueVector.lanewise(VectorOperators.REVERSE);
            var reverseBytesResult = valueVector.lanewise(VectorOperators.REVERSE_BYTES);

            System.out.println("Bit Count: " + bitCountResult);
            System.out.println("Leading Zero Count: " + leadingZerosResult);
            System.out.println("Trailing Zero Count: " + trailingZerosResult);
            System.out.println("Reverse: " + reverseResult);
            System.out.println("Reverse Bytes: " + reverseBytesResult);
        }
    }

    public static int compress(int value, int mask) {
        int result = 0;
        int shift = 0;
        do {
            int maskBit = mask & 1;
            result |= ((value & maskBit) << shift);
            shift += maskBit;
            value >>= 1;
            mask >>= 1;
        } while (mask != 0);
        return result;
    }

    public static int expand(int value, int mask) {
        int originalMask = mask;
        int rightEvenZero = ~mask << 1;
        int[] array = new int[5];

        for (int count = 0; count < 5; count++) {
            int rightOddZero = rightEvenZero ^ (rightEvenZero << 1);
            rightOddZero ^= (rightOddZero << 2);
            rightOddZero ^= (rightOddZero << 4);
            rightOddZero ^= (rightOddZero << 8);
            rightOddZero ^= (rightOddZero << 16);
            int move = rightOddZero & mask;
            array[count] = move;
            mask = (mask ^ move) | (move >> (1 << count));
            rightEvenZero &= ~rightOddZero;
        }

        for (int count = 4; count >= 0; count--) {
            int move = array[count];
            int targetValue = value << (1 << count);
            value = (value & ~move) | (targetValue & move);
        }
        return value & originalMask;
    }

    public static void vectorFromMemorySegment() {
        byte[] data = new byte[64];
        data[0] = 1;
        data[1] = 2;
        data[2] = 3;
        data[3] = 4;
        data[4] = 5;

        VectorSpecies<Byte> BYTE_SPECIES = ByteVector.SPECIES_PREFERRED;
        final var memorySegment = MemorySegment.ofArray(data);
        var mask = BYTE_SPECIES.maskAll(true);
        ByteVector byteVector = ByteVector.fromMemorySegment(BYTE_SPECIES, memorySegment, 0, ByteOrder.nativeOrder(), mask);
        byteVector.intoMemorySegment(memorySegment, 0, ByteOrder.nativeOrder());
        System.out.println("Memory Segment: " + byteVector);
    }

    public static void compressExpand() {
        int[] values = {1, 2, 3, 4};

        IntVector sourceVector = IntVector.fromArray(IntVector.SPECIES_128, values, 0);
        boolean[] booleans = {true, false, true, false};
        VectorMask<Integer> mask = VectorMask.fromArray(IntVector.SPECIES_128, booleans, 0);
        IntVector compressedVector = sourceVector.compress(mask);
        System.out.println("Compressed Data: " + compressedVector.toString());
        IntVector expandedVector = compressedVector.expand(mask);
        System.out.println("Expanded Data: " + expandedVector.toString());
        VectorMask<Integer> mask1 = VectorMask.fromArray(IntVector.SPECIES_128, booleans, 0);
        VectorMask<Integer> compressedMask = mask1.compress();
        System.out.println("Mask Compressed Data: " + compressedMask.toString());
    }

    public static void JEP_448_Method_Call(){
        laneWiseBitwise();
        vectorFromMemorySegment();
        compressExpand();
        int val = compress(1001, 2000);
        System.out.println("Compress = " + val);
        int val1 = expand(1001, 0101);
        System.out.println("Expand = " + val1);
    }
}
