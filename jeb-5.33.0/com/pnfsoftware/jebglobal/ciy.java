package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.input.BytesInput;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class ciy {
   Map pC = new TreeMap();

   public void pC(civ var1, byte[] var2) {
      int var3 = var1.pC().A();
      int var4 = var1.pC().wS().A();
      Object var5 = (Map)this.pC.get(var3);
      if (var5 == null) {
         var5 = new TreeMap();
         this.pC.put(var3, var5);
      }

      ByteBuffer var6 = ByteBuffer.wrap(var2);
      var6.order(ByteOrder.LITTLE_ENDIAN);

      for (int var7 = (var4 - 1) * 16; var6.hasRemaining(); var7++) {
         short var8 = var6.getShort();
         if (var8 > 0) {
            String var9 = new String(var2, var6.position(), var8 * 2, Charset.forName("UTF-16LE"));
            var5.put(var7, var9);
            var6.position(var6.position() + var8 * 2);
         }
      }
   }

   public void pC(IUnit var1) {
      for (Entry var3 : this.pC.entrySet()) {
         int var4 = (Integer)var3.getKey();
         Map var5 = (Map)var3.getValue();
         StringBuilder var6 = new StringBuilder();

         for (int var8 : var5.keySet()) {
            String var9 = (String)var5.get(var8);
            Strings.ff(var6, "%d \"%s\"\n", var8, Formatter.escapeString(var9));
         }

         String var10 = Strings.ff("strings-%d.txt", var4);
         IUnit var11 = var1.getUnitProcessor().process(var10, new BytesInput(Strings.encodeUTF8(var6.toString())), var1);
         var1.addChild(var11);
      }
   }
}
