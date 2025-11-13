package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class DB {
   public static DB.Av pC(InputStream var0, String var1) {
      try {
         DB.Av var4;
         try (MQ var2 = new MQ(var0)) {
            tH var3 = new tH(var2, 0);
            if (var3.wS > var3.UT) {
               return DB.Av.pC;
            }

            if (var3.kS == 2) {
               pC(var2, var3);
               return DB.Av.A;
            }

            if (var3.kS == 3 || var3.kS == 0 && Strings.endsWith(var1, ".xml")) {
               var3.pC(var2);
               new tH(var2, 1).kS(var2);
               var3 = new tH(var2, 0);
               return Eq.pC(var3.kS) ? DB.Av.kS : DB.Av.pC;
            }

            var4 = DB.Av.pC;
         }

         return var4;
      } catch (IOException var7) {
         return DB.Av.pC;
      }
   }

   public static Map pC(InputStream var0) throws IOException {
      Map var3;
      try (MQ var1 = new MQ(var0)) {
         tH var2 = new tH(var1, 0);
         var3 = pC(var1, var2);
      }

      return var3;
   }

   private static Map pC(EX var0, tH var1) throws IOException {
      if (var1.kS != 0 && var1.kS != 2) {
         throw new IOException("Unexpected type: " + var1.kS);
      } else {
         int var2 = var0.readInt();
         var1.pC(var0);

         while (true) {
            tH var3 = new tH(var0, 0);
            if (var3.kS == 1) {
               var3.kS(var0);
               LinkedHashMap var4 = new LinkedHashMap(1);

               for (int var5 = 0; var5 < var2; var5++) {
                  var1 = new tH(var0, 512);
                  int var6 = var0.readInt();
                  String var7 = bM.pC(var0, 128, true);
                  var4.put(var6, var7);
                  var1.kS(var0);
               }

               return var4;
            }

            var3.kS(var0);
         }
      }
   }

   public static enum Av {
      pC,
      A,
      kS;
   }
}
