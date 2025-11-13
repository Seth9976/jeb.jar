package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.FunctionEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class TN {
   public static IEncodedMemoryArea pC(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> var0.decode(var1) + 1L);
   }

   public static IEncodedMemoryArea A(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> var0.decode(var1) ^ 1L);
   }

   public static IEncodedMemoryArea pC(IEncodedMemoryArea var0, IEncodedMemoryArea var1) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var2 -> var0.decode(var2) - var1.decode(var2));
   }

   public static IEncodedMemoryArea pC(IEncodedMemoryArea var0, boolean var1, IEncodedMemoryArea var2, boolean var3) {
      return new FunctionEncodedMemoryArea(
         var0.getLength() + var2.getLength(),
         var4 -> (var1 ? EncodedMemoryAreaUtil.signExtendLong(var4, var0) : var0.decode(var4))
            * (var3 ? EncodedMemoryAreaUtil.signExtendLong(var4, var2) : var2.decode(var4))
      );
   }

   public static IEncodedMemoryArea A(IEncodedMemoryArea var0, IEncodedMemoryArea var1) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var2 -> var0.decode(var2) ^ var1.decode(var2));
   }

   public static IEncodedMemoryArea kS(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> (var0.decode(var1) + 1L) % (1 << var0.getLength()));
   }

   public static IEncodedMemoryArea pC(IEncodedMemoryArea var0, int var1) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var2 -> (var0.decode(var2) + var1) % (1 << var0.getLength()));
   }

   public static IEncodedMemoryArea wS(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> (long)Gq.A(var0.decode(var1)));
   }

   public static IEncodedMemoryArea UT(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> (long)Gq.pC(var0.decode(var1)));
   }

   public static IEncodedMemoryArea E(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> {
         long var2 = var0.decode(var1);
         int var4 = Gq.A(var2);
         return var2 >>> var4 + 1;
      });
   }

   public static IEncodedMemoryArea kS(IEncodedMemoryArea var0, IEncodedMemoryArea var1) {
      return new FunctionEncodedMemoryArea(var0.getLength() + (1 << var1.getLength()) - 1, var2 -> {
         long var3 = var0.decode(var2);
         int var5 = var1.decodeInt(var2);
         return var3 << var5;
      });
   }
}
