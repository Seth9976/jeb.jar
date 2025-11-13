package com.pnfsoftware.jeb.corei.parsers.elf;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bK {
   @SerId(1)
   List q = new ArrayList();
   @SerId(2)
   List RF = new ArrayList();

   public static bK q(ByteBuffer var0) {
      bK var1 = new bK();
      int var2 = var0.getInt();

      for (int var3 = 0; var3 < var2; var3++) {
         var1.q.add(var0.getInt());
      }

      var2 = var0.getInt();

      for (int var5 = 0; var5 < var2; var5++) {
         var1.q.add(var0.getInt());
      }

      return var1;
   }
}
