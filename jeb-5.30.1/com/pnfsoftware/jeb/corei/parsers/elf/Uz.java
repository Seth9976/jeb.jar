package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Ser
public class Uz {
   @SerId(1)
   List q = new ArrayList();

   public static Uz q(ByteBuffer var0, boolean var1) {
      Uz var2 = new Uz();

      while (var0.remaining() > 0) {
         var2.q.add(Nz.q(var0, var1));
      }

      return var2;
   }

   public List q() {
      return this.q;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (Nz var4 : this.q) {
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
