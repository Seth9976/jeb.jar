package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Ws {
   @SerId(1)
   List pC = new ArrayList();

   public static Ws pC(ByteBuffer var0, boolean var1) {
      Ws var2 = new Ws();

      while (var0.remaining() > 0) {
         K var3 = K.pC(var0, var1);
         if (var3 != null) {
            var2.pC.add(var3);
            if (var3.pC == 0L) {
               break;
            }
         }
      }

      return var2;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (K var4 : this.pC) {
         if (var2 >= 1) {
            var1.append('\n');
         }

         var1.append("  ");
         var1.append(var4);
         var2++;
      }

      return var1.toString();
   }
}
