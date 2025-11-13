package com.pnfsoftware.jeb.core.units.code.asm.processor.memory;

import com.pnfsoftware.jeb.util.math.MathUtil;
import java.util.WeakHashMap;

public class VirtualEncodedMemoryArea extends AbstractEncodedMemoryArea {
   private static WeakHashMap cache = new WeakHashMap();
   public static final VirtualEncodedMemoryArea _0 = get(0, 1);
   public static final VirtualEncodedMemoryArea _1 = get(1, 1);
   public static final VirtualEncodedMemoryArea _2 = get(2, 2);
   public static final VirtualEncodedMemoryArea _3 = get(3, 2);
   public static final VirtualEncodedMemoryArea _4 = get(4, 3);
   public static final VirtualEncodedMemoryArea _5 = get(5, 3);
   public static final VirtualEncodedMemoryArea _00 = get(0, 2);
   public static final VirtualEncodedMemoryArea _000 = get(0, 3);
   public static final VirtualEncodedMemoryArea _0000 = get(0, 4);
   private int value;
   private int length;

   protected VirtualEncodedMemoryArea(int var1, int var2) {
      this.value = var1;
      this.length = var2;
   }

   @Override
   public int getLength() {
      return this.length;
   }

   @Override
   public long decode(byte[] var1) {
      return this.value;
   }

   public static VirtualEncodedMemoryArea get(int var0, int var1) {
      long var2 = var1 + ((long)var0 << 6);
      VirtualEncodedMemoryArea var4 = (VirtualEncodedMemoryArea)cache.get(var2);
      if (var4 == null) {
         var4 = new VirtualEncodedMemoryArea(var0, var1);
         cache.put(var2, var4);
      }

      return var4;
   }

   public static VirtualEncodedMemoryArea zeros(int var0) {
      return get(0, var0);
   }

   public static VirtualEncodedMemoryArea ones(int var0) {
      return get((int)MathUtil.makeMask(var0), var0);
   }
}
