package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Av {
   @SerId(1)
   List pC = new ArrayList();

   public static Av pC(ByteBuffer var0, boolean var1, long var2) {
      Av var4 = new Av();

      try {
         long var5 = 0L;
         if (var1) {
            var0.getLong();
            var5 += 8L;

            for (Long var7 = var0.getLong(); var5 < var2 && var7 != 0L; var5 += 8L) {
               var4.pC.add(var7);
               var7 = var0.getLong();
            }
         } else {
            var0.getInt();
            var5 += 4L;

            for (Long var11 = var0.getInt() & 4294967295L; var5 < var2 && var11 != 0L; var5 += 4L) {
               var4.pC.add(var11);
               var11 = var0.getInt() & 4294967295L;
            }
         }

         return var4;
      } catch (BufferUnderflowException var8) {
         return null;
      }
   }

   public List pC() {
      return this.pC;
   }
}
