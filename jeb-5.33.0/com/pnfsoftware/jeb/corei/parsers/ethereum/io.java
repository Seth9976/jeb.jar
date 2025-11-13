package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.ckv;
import com.pnfsoftware.jebglobal.ckx;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class io {
   private static final ILogger pC = GlobalLog.getLogger(io.class);
   private static byte[] A;
   private static io kS;
   private Map wS = new HashMap();
   private Map UT = new HashMap();

   public static io pC() {
      if (kS == null) {
         kS = new io(A);
      }

      return kS;
   }

   private io(byte[] var1) {
      if (var1 != null) {
         String var2 = Strings.decodeUTF8(var1);
         int var3 = 0;

         for (String var7 : Strings.splitLines(var2)) {
            var3++;
            var7 = var7.trim();
            if (!var7.isEmpty() && !var7.startsWith("#")) {
               String[] var8 = var7.split(":");
               if (var8.length != 2) {
                  throw new RuntimeException(Strings.ff("Line %d: Illegal entry: %s", var3, var7));
               }

               String var9 = var8[0];
               String var10 = var8[1];
               BigInteger var11 = new BigInteger(var9, 16);
               if (this.wS.put(var11, var10) != null) {
                  throw new RuntimeException(Strings.ff("Event hash collision: %s", var11.toString(16).toUpperCase()));
               }

               int var12 = Integer.parseUnsignedInt(var9.substring(0, 8), 16);
               Object var13 = (List)this.UT.get(var12);
               if (var13 == null) {
                  var13 = new ArrayList();
                  this.UT.put(var12, var13);
               }

               Lists.addSorted((List)var13, var10);
            }
         }
      }
   }

   public String[] pC(BigInteger var1) {
      String var2 = (String)this.wS.get(var1);
      return var2 == null ? null : this.pC(var2);
   }

   public List pC(int var1) {
      List var2 = (List)this.UT.get(var1);
      if (var2 == null) {
         return Collections.emptyList();
      } else {
         ArrayList var3 = new ArrayList();

         for (String var5 : var2) {
            var3.add(this.pC(var5));
         }

         return var3;
      }
   }

   private String[] pC(String var1) {
      int var2 = var1.indexOf(40);
      if (var2 >= 0 && var1.charAt(var1.length() - 1) == ')') {
         ArrayList var3 = new ArrayList();
         String var4 = var1.substring(0, var2);
         var3.add(var4);
         String var5 = var1.substring(var2 + 1, var1.length() - 1);
         if (var5.length() > 0) {
            for (String var9 : var5.split(",")) {
               var3.add(var9);
            }
         }

         return (String[])var3.toArray(new String[var3.size()]);
      } else {
         throw new RuntimeException();
      }
   }

   static {
      byte[] var0 = AssetManager.getAssetBytes(ckx.pC(new byte[]{38, 65, 18, 16, 28}, 2, 246));
      ckv.pC(
         ckx.pC(
               new byte[]{
                  54,
                  44,
                  31,
                  9,
                  11,
                  27,
                  14,
                  15,
                  28,
                  84,
                  8,
                  75,
                  74,
                  9,
                  17,
                  8,
                  0,
                  12,
                  25,
                  12,
                  18,
                  2,
                  1,
                  9,
                  20,
                  12,
                  111,
                  61,
                  19,
                  2,
                  15,
                  9,
                  69,
                  65,
                  15,
                  10,
                  75,
                  64,
                  29,
                  82,
                  73,
                  29,
                  7,
                  83,
                  65,
                  7,
                  0,
                  15,
                  5,
                  5,
                  8,
                  21,
                  17,
                  22,
                  93
               },
               1,
               117
            )
            .getBytes(Charset.forName("UTF-8")),
         var0
      );

      ZipEntry var2;
      try (ZipInputStream var1 = new ZipInputStream(new ByteArrayInputStream(var0))) {
         while ((var2 = var1.getNextEntry()) != null) {
            String var3 = var2.getName();
            if ("hashes.txt".equals(var3)) {
               A = IO.readInputStream(var1);
            }
         }
      } catch (IOException var6) {
      }
   }
}
