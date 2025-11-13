package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.util.io.Endianness;

public interface IRegisterData {
   IRegisterBank getBank();

   Endianness getEndianness();

   int size();

   String getName(int var1);

   int getBitsize(int var1);

   byte[] getValue(int var1);

   Long getValueAsLong(int var1);

   boolean setValue(int var1, byte[] var2);

   boolean setValueAsLong(int var1, long var2);

   long getProgramCounter() throws CannotReadRegisterException;

   long getFlags() throws CannotReadRegisterException;
}
