package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class nH {
   public static nH.eo q(InputStream var0, String var1) {
      try {
         nH.eo var4;
         try (oe var2 = new oe(var0)) {
            jA var3 = new jA(var2, 0);
            if (var3.Dw > var3.Uv) {
               return nH.eo.q;
            }

            if (var3.xK == 2) {
               q(var2, var3);
               return nH.eo.RF;
            }

            if (var3.xK == 3 || var3.xK == 0 && Strings.endsWith(var1, ".xml")) {
               var3.xK(var2);
               new jA(var2, 1).Uv(var2);
               var3 = new jA(var2, 0);
               return mw.q(var3.xK) ? nH.eo.xK : nH.eo.q;
            }

            var4 = nH.eo.q;
         }

         return var4;
      } catch (IOException var7) {
         return nH.eo.q;
      }
   }

   public static Map q(InputStream var0) throws IOException {
      Map var3;
      try (oe var1 = new oe(var0)) {
         jA var2 = new jA(var1, 0);
         var3 = q(var1, var2);
      }

      return var3;
   }

   private static Map q(uL var0, jA var1) throws IOException {
      if (var1.xK != 0 && var1.xK != 2) {
         throw new IOException("Unexpected type: " + var1.xK);
      } else {
         int var2 = var0.readInt();
         var1.xK(var0);

         while (true) {
            jA var3 = new jA(var0, 0);
            if (var3.xK == 1) {
               var3.Uv(var0);
               LinkedHashMap var4 = new LinkedHashMap(1);

               for (int var5 = 0; var5 < var2; var5++) {
                  var1 = new jA(var0, 512);
                  int var6 = var0.readInt();
                  String var7 = zR.q(var0, 128, true);
                  var4.put(var6, var7);
                  var1.Uv(var0);
               }

               return var4;
            }

            var3.Uv(var0);
         }
      }
   }

   public static enum eo {
      q,
      RF,
      xK;
   }
}
