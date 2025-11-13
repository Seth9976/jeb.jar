package com.pnfsoftware.jeb.core.input;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class InputHelper {
   private static Map mapWrappedBytes = new HashMap();

   public static IInput wrapBytes(byte[] var0) {
      Object var1 = (IInput)mapWrappedBytes.get(var0);
      if (var1 == null) {
         var1 = new BytesInput(var0);
         mapWrappedBytes.put(var0, var1);
      }

      return (IInput)var1;
   }

   public static int readBytes(IInput var0, long var1, byte[] var3, int var4, int var5) throws IOException {
      int var7;
      try (InputStream var6 = var0.getStream()) {
         var6.skip(var1);
         var7 = var6.read(var3, var4, var5);
      }

      return var7;
   }
}
