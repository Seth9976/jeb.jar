package com.pnfsoftware.jeb.core.units.code.asm.simulator;

import com.pnfsoftware.jeb.util.io.EndianUtil;
import java.nio.ByteOrder;
import java.util.List;

public class ResolvedOperandByteValue implements IResolvedOperandValue {
   private byte[] bytes;

   public ResolvedOperandByteValue(long var1, int var3, ByteOrder var4) {
      this.bytes = new byte[var3 / 8];
      EndianUtil.numberToBytes(var4, var1, this.bytes);
   }

   public ResolvedOperandByteValue(byte[] var1) {
      this.bytes = var1;
   }

   @Override
   public byte[] getBytes() {
      return this.bytes;
   }

   @Override
   public List getElements() {
      return null;
   }
}
