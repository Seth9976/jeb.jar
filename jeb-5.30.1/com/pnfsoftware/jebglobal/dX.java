package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.EncodedMemoryAreaUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.FunctionEncodedMemoryArea;
import com.pnfsoftware.jeb.core.units.code.asm.processor.memory.IEncodedMemoryArea;

public class dX {
   public static IEncodedMemoryArea q(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> var0.decode(var1) + 1L);
   }

   public static IEncodedMemoryArea RF(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> var0.decode(var1) ^ 1L);
   }

   public static IEncodedMemoryArea q(IEncodedMemoryArea var0, IEncodedMemoryArea var1) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var2 -> var0.decode(var2) - var1.decode(var2));
   }

   public static IEncodedMemoryArea q(IEncodedMemoryArea var0, boolean var1, IEncodedMemoryArea var2, boolean var3) {
      return new FunctionEncodedMemoryArea(
         var0.getLength() + var2.getLength(),
         var4 -> (var1 ? EncodedMemoryAreaUtil.signExtendLong(var4, var0) : var0.decode(var4))
            * (var3 ? EncodedMemoryAreaUtil.signExtendLong(var4, var2) : var2.decode(var4))
      );
   }

   public static IEncodedMemoryArea RF(IEncodedMemoryArea var0, IEncodedMemoryArea var1) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var2 -> var0.decode(var2) ^ var1.decode(var2));
   }

   public static IEncodedMemoryArea xK(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> (var0.decode(var1) + 1L) % (1 << var0.getLength()));
   }

   public static IEncodedMemoryArea q(IEncodedMemoryArea var0, int var1) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var2 -> (var0.decode(var2) + var1) % (1 << var0.getLength()));
   }

   public static IEncodedMemoryArea Dw(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> (long)k.RF(var0.decode(var1)));
   }

   public static IEncodedMemoryArea Uv(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> (long)k.q(var0.decode(var1)));
   }

   public static IEncodedMemoryArea oW(IEncodedMemoryArea var0) {
      return new FunctionEncodedMemoryArea(var0.getLength(), var1 -> {
         long var2 = var0.decode(var1);
         int var4 = k.RF(var2);
         return var2 >>> var4 + 1;
      });
   }

   public static IEncodedMemoryArea xK(IEncodedMemoryArea var0, IEncodedMemoryArea var1) {
      return new FunctionEncodedMemoryArea(var0.getLength() + (1 << var1.getLength()) - 1, var2 -> {
         long var3 = var0.decode(var2);
         int var5 = var1.decodeInt(var2);
         return var3 << var5;
      });
   }
}
