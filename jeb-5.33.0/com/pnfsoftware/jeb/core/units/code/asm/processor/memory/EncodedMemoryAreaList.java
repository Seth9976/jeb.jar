package com.pnfsoftware.jeb.core.units.code.asm.processor.memory;

public class EncodedMemoryAreaList extends AbstractEncodedMemoryArea {
   private IEncodedMemoryArea[] memoryAreas;

   public EncodedMemoryAreaList(IEncodedMemoryArea... var1) {
      this.memoryAreas = var1;
   }

   @Override
   public int getLength() {
      int var1 = 0;

      for (IEncodedMemoryArea var5 : this.memoryAreas) {
         var1 += var5.getLength();
      }

      return var1;
   }

   @Override
   public long decode(byte[] var1) {
      return EncodedMemoryAreaUtil.zeroExtendLong(var1, this.memoryAreas);
   }

   public static EncodedMemoryAreaList shift(IEncodedMemoryArea var0, int var1) {
      return new EncodedMemoryAreaList(var0, VirtualEncodedMemoryArea.get(0, var1));
   }

   public static EncodedMemoryAreaList fromBits(int... var0) {
      IEncodedMemoryArea[] var1 = new IEncodedMemoryArea[var0.length];

      for (int var2 = 0; var2 < var0.length; var2++) {
         var1[var2] = DirectEncodedMemoryArea.get(var0[var2], 1);
      }

      return new EncodedMemoryAreaList(var1);
   }
}
