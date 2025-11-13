package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.cji;
import com.pnfsoftware.jebglobal.cvk;
import com.pnfsoftware.jebglobal.cvm;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class qa {
   private static final ILogger q = GlobalLog.getLogger(qa.class);
   private static byte[] RF;
   private static qa xK;
   private Map Dw = new HashMap();
   private Map Uv = new HashMap();

   public static qa q() {
      if (xK == null) {
         xK = new qa(RF);
      }

      return xK;
   }

   public static int q(String var0) {
      cji var1 = new cji();
      byte[] var2 = var1.digest(Strings.encodeUTF8(var0));
      return EndianUtil.bytesToInt(var2, 0, ByteOrder.BIG_ENDIAN);
   }

   public static byte[] RF(String var0) {
      cji var1 = new cji();
      return var1.digest(Strings.encodeUTF8(var0));
   }

   private qa(byte[] var1) {
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
               if (this.Dw.put(var11, var10) != null) {
                  throw new RuntimeException(Strings.ff("Event hash collision: %s", var11.toString(16).toUpperCase()));
               }

               int var12 = Integer.parseUnsignedInt(var9.substring(0, 8), 16);
               Object var13 = (List)this.Uv.get(var12);
               if (var13 == null) {
                  var13 = new ArrayList();
                  this.Uv.put(var12, var13);
               }

               Lists.addSorted((List)var13, var10);
            }
         }
      }
   }

   public int RF() {
      return this.Dw.size();
   }

   public Set xK() {
      return this.Dw.keySet();
   }

   public int q(int var1) {
      List var2 = (List)this.Uv.get(var1);
      return var2 == null ? 0 : var2.size();
   }

   public String q(BigInteger var1) {
      return (String)this.Dw.get(var1);
   }

   public List RF(int var1) {
      List var2 = (List)this.Uv.get(var1);
      return var2 == null ? Collections.emptyList() : var2;
   }

   public String[] RF(BigInteger var1) {
      String var2 = (String)this.Dw.get(var1);
      return var2 == null ? null : this.xK(var2);
   }

   public List xK(int var1) {
      List var2 = (List)this.Uv.get(var1);
      if (var2 == null) {
         return Collections.emptyList();
      } else {
         ArrayList var3 = new ArrayList();

         for (String var5 : var2) {
            var3.add(this.xK(var5));
         }

         return var3;
      }
   }

   private String[] xK(String var1) {
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
      byte[] var0 = AssetManager.getAssetBytes(cvm.q(new byte[]{38, 65, 18, 16, 28}, 2, 117));
      cvk.q(
         cvm.q(
               new byte[]{
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  6,
                  0,
                  0,
                  0,
                  0,
                  3,
                  12,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0,
                  0
               },
               2,
               184
            )
            .getBytes(Charset.forName("UTF-8")),
         var0
      );

      ZipEntry var2;
      try (ZipInputStream var1 = new ZipInputStream(new ByteArrayInputStream(var0))) {
         while ((var2 = var1.getNextEntry()) != null) {
            String var3 = var2.getName();
            if ("hashes.txt".equals(var3)) {
               RF = IO.readInputStream(var1);
            }
         }
      } catch (IOException var6) {
      }
   }

   public static class eo {
      String q;
      String RF;

      public String q() {
         return this.q;
      }

      public String RF() {
         return this.RF;
      }
   }
}
