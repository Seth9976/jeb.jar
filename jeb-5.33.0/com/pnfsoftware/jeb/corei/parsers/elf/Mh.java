package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Mh {
   @SerId(1)
   List pC = new ArrayList();

   public static Mh pC(ByteBuffer var0, boolean var1) {
      Mh var2 = new Mh();

      while (var0.remaining() > 0) {
         var2.pC.add(GK.pC(var0, var1));
      }

      return var2;
   }

   public List pC() {
      return this.pC;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (GK var4 : this.pC) {
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
