package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Ser
public class ej {
   @SerId(1)
   List q = new ArrayList();

   public static ej q(ByteBuffer var0, boolean var1) {
      ej var2 = new ej();

      while (var0.remaining() > 0) {
         nI var3 = nI.q(var0, var1);
         if (var3 != null) {
            var2.q.add(var3);
            if (var3.q == 0L) {
               break;
            }
         }
      }

      return var2;
   }

   public List q() {
      return this.q;
   }

   public Long q(long var1) {
      for (nI var4 : this.q) {
         if (var4.q == var1) {
            return var4.RF;
         }
      }

      return null;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (nI var4 : this.q) {
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
