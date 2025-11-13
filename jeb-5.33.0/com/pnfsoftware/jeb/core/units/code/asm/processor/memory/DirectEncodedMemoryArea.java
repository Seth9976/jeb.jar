package com.pnfsoftware.jeb.core.units.code.asm.processor.memory;

import java.util.WeakHashMap;

public class DirectEncodedMemoryArea extends AbstractEncodedMemoryArea {
   private static WeakHashMap cache = new WeakHashMap();
   private int index;
   private int length;

   protected DirectEncodedMemoryArea(int var1, int var2) {
      this.index = var1;
      this.length = var2;
   }

   public int getIndex() {
      return this.index;
   }

   @Override
   public int getLength() {
      return this.length;
   }

   public static DirectEncodedMemoryArea get(int var0, int var1) {
      int var2 = var0 + (var1 << 6);
      DirectEncodedMemoryArea var3 = (DirectEncodedMemoryArea)cache.get(var2);
      if (var3 == null) {
         var3 = new DirectEncodedMemoryArea(var0, var1);
         cache.put(var2, var3);
      }

      return var3;
   }

   public static int decodeInt(int var0, int var1, byte[] var2) {
      return get(var0, var1).decodeInt(var2);
   }

   public static DirectEncodedMemoryArea getThumb2(int var0, int var1, int var2) {
      return get(var0 + var1 * 16, var2);
   }

   @Override
   public long decode(byte[] var1) {
      return EncodedMemoryAreaUtil.zeroExtend(var1, this.index, this.length);
   }

   public EncodedMemoryAreaList shift(int var1) {
      return new EncodedMemoryAreaList(this, VirtualEncodedMemoryArea.get(0, var1));
   }
}
