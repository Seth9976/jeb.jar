package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.units.code.android.IEmulatedAndroid;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VMWriter;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.util.collect.BytePipe;
import com.pnfsoftware.jeb.util.encoding.zip.ZipBrowser;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class cjw {
   private static final ILogger zz = GlobalLog.getLogger(cjw.class);
   static final int q = 1;
   static final int RF = 2;
   static final int xK = 4;
   private cjo JY;
   final int Dw;
   final int Uv;
   final int oW;
   final int gO;
   final int nf;
   final int gP;
   final int za;
   final int lm;
   private int HF = 292;
   private int LK = 1;
   private Map io = new HashMap();
   private Map qa = new HashMap();
   private String Hk = "/";

   public cjw(cjo var1) {
      this.JY = var1;
      this.Dw = this.xK(cvm.q(new byte[]{81, 21, 11, 25, 2, 7, 20, 19}, 1, 16), -1);
      this.Uv = this.xK(cvm.q(new byte[]{-85, 22, 0, 14, 20, 12, 22, 17}, 1, 248), -1);
      this.oW = this.xK(cvm.q(new byte[]{16, 42, 53, 50, 45, 42, 50, 58}, 2, 54), -1);
      this.gO = this.xK(cvm.q(new byte[]{16, 42, 53, 50, 45, 44, 41, 44}, 2, 7), -1);
      this.nf = this.xK(cvm.q(new byte[]{19, 46, 36, 49, 45, 36, 38, 48}, 2, 79), -1);
      this.gP = this.xK(cvm.q(new byte[]{-21, 16, 17, 1, 1, 12, 14, 3, 12, 8}, 1, 164), -1);
      this.za = this.xK(cvm.q(new byte[]{12, 48, 51, 53, 61, 44, 63, 45, 55}, 2, 18), -1);
      this.lm = this.xK(cvm.q(new byte[]{21, 16, 28, 17, 23, 4, 21}, 1, 90), -1);
   }

   public cjw.eo q(int var1) {
      return (cjw.eo)this.qa.get(var1);
   }

   public String q() {
      return this.Hk;
   }

   public String q(String var1) {
      if (!var1.startsWith("/")) {
         var1 = this.Hk + "/" + var1;
      }

      return com.pnfsoftware.jeb.util.io.IO.simplifyPathUnix(var1);
   }

   private cjw.nI q(String var1, boolean var2, boolean var3) {
      var1 = this.q(var1);
      cjw.nI var4 = (cjw.nI)this.io.get(var1);
      if (var4 == null) {
         var4 = new cjw.nI(var1, 0);
         if (!this.q(var4, var3, var2)) {
            return null;
         }

         this.io.put(var1, var4);
      }

      return var4;
   }

   private int RF() {
      int var1 = this.HF++;

      while (this.qa.containsKey(var1)) {
         var1 = this.HF++;
      }

      return var1;
   }

   public int q(String var1, int var2) {
      int var3 = this.RF(var1);
      if (var3 == -1) {
         return -1;
      } else {
         this.RF(var3);
         return 0;
      }
   }

   public int q(String var1, int var2, int var3, boolean var4) {
      byte var5;
      if ((var2 & 1) == 1) {
         var5 = 2;
      } else if ((var2 & 2) == 2) {
         var5 = 3;
      } else {
         var5 = 1;
      }

      var5 |= 4;
      boolean var6 = (var2 & this.lm) != 0;
      this.JY.za().monitorHLSpecial(0, var1, var2);
      cjw.nI var7 = this.q(var1, var6, var4);
      if (var7 == null) {
         return -1;
      } else {
         int var8 = this.RF();
         cjw.eo var9 = new cjw.eo(var8, var5, var1, var7);
         this.qa.put(var8, var9);
         return var8;
      }
   }

   public int q(String var1, int var2, int var3) {
      return this.q(var1, var2, var3, false);
   }

   public int RF(String var1) {
      return this.q(var1, 0, 0);
   }

   public int RF(int var1) {
      return this.qa.remove(var1) == null ? -1 : 0;
   }

   public int RF(String var1, int var2) {
      return -1;
   }

   public int q(int var1, int var2, byte[] var3, int var4) {
      cjw.eo var5 = (cjw.eo)this.qa.get(var1);
      if (var5 == null || !var5.RF()) {
         return -1;
      } else if (!this.q(var5.gO())) {
         return -1;
      } else if (var2 == 0) {
         return 0;
      } else if (var5.Dw()) {
         IEmulatedAndroid var6 = this.JY.xK();
         if (var6 == null) {
            return -1;
         } else {
            File var7 = var6.getDropboxFolder();
            File var8 = new File(var7, var5.xK.xK.q);
            if (com.pnfsoftware.jeb.util.io.IO.inFolder(var8, var7)) {
               try (RandomAccessFile var9 = new RandomAccessFile(var8, cvm.q(new byte[]{87, 5}, 1, 37))) {
                  var9.seek(var5.xK.RF);
                  var9.write(var3, var4, var2);
               } catch (IOException var14) {
                  return -1;
               }

               var5.q(var5.nf() + var2);
               return var2;
            } else {
               return -1;
            }
         }
      } else {
         return -1;
      }
   }

   public int RF(int var1, int var2, byte[] var3, int var4) {
      cjw.eo var5 = (cjw.eo)this.qa.get(var1);
      if (var5 == null || !var5.q()) {
         return -1;
      } else if (!this.q(var5.gO())) {
         return -1;
      } else if (var2 == 0) {
         return 0;
      } else {
         int var6 = var5.gP();
         if (var6 == 0) {
            return 0;
         } else if (var5.Dw()) {
            int var7 = Math.min(var2, var6);
            System.arraycopy(var5.gO().Uv.array(), var5.nf(), var3, var4, var7);
            var5.q(var5.nf() + var7);
            return var7;
         } else {
            return -1;
         }
      }
   }

   public int q(int var1, int var2, int var3, boolean var4, byte[] var5, int var6) {
      cjw.eo var7 = (cjw.eo)this.qa.get(var1);
      if (var7 == null || !var7.q()) {
         return -1;
      } else if (!this.q(var7.gO())) {
         return -1;
      } else if (var2 == 0) {
         return 0;
      } else {
         int var8 = var7.gP();
         if (var8 == 0) {
            return 0;
         } else if (!var7.Dw()) {
            return -1;
         } else {
            int var9 = 0;

            for (int var10 = var7.nf(); var10 < var7.gO().Uv() && var9 < var2; var10++) {
               var9++;
               if ((var7.gO().Uv.get(var10) & 255) == var3) {
                  if (!var4) {
                     var9--;
                  }
                  break;
               }
            }

            System.arraycopy(var7.gO().Uv.array(), var7.nf(), var5, var6, var9);
            var7.q(var7.nf() + var9);
            return var9;
         }
      }
   }

   public int q(int var1, int var2, IVirtualMemory var3, long var4, Integer var6) {
      cjw.eo var7 = (cjw.eo)this.qa.get(var1);
      if (var7 == null || !var7.q()) {
         return -1;
      } else if (!this.q(var7.gO())) {
         return -1;
      } else if (var2 == 0) {
         return 0;
      } else {
         int var8 = var7.gP();
         if (var8 == 0) {
            return 0;
         } else {
            int var9 = Math.min(var2, var8);
            if (var7.Uv()) {
               byte[] var12 = new byte[var9];
               var7.gO().oW.get(var12);
               return !VirtualMemoryUtil.writeBytes(var3, var4, var12, 0, var9) ? -1 : var9;
            } else {
               int var10 = var6 != null ? var6 : var7.nf();
               byte[] var11 = var7.gO().Uv.array();
               if (!VirtualMemoryUtil.writeBytes(var3, var4, var11, var10, var9)) {
                  return -1;
               } else {
                  if (var6 == null) {
                     var7.q(var10 + var9);
                  }

                  return var9;
               }
            }
         }
      }
   }

   public int RF(int var1, int var2, IVirtualMemory var3, long var4, Integer var6) {
      cjw.eo var7 = (cjw.eo)this.qa.get(var1);
      if (var7 == null || !var7.RF()) {
         return -1;
      } else if (!this.q(var7.gO())) {
         return -1;
      } else if (var2 == 0) {
         return 0;
      } else if (var7.Uv()) {
         byte[] var19 = new byte[var2];
         if (!VirtualMemoryUtil.readBytes(var3, var4, var19, 0, var19.length)) {
            return -1;
         } else {
            var7.gO().oW.append(var19);
            return var2;
         }
      } else {
         IEmulatedAndroid var8 = this.JY.xK();
         if (var8 == null) {
            return -1;
         } else {
            int var9 = var6 != null ? var6 : var7.nf();
            byte[] var10 = new byte[var2];
            if (!VirtualMemoryUtil.readBytes(var3, var4, var10, 0, var10.length)) {
               return -1;
            } else {
               File var11 = var8.getDropboxFolder();
               File var12 = new File(var11, var7.xK.xK.q);
               if (com.pnfsoftware.jeb.util.io.IO.inFolder(var12, var11)) {
                  try (RandomAccessFile var13 = new RandomAccessFile(var12, cvm.q(new byte[]{111, 5}, 1, 29))) {
                     var13.seek(var9);
                     var13.write(var10, var9, var2);
                  } catch (IOException var18) {
                     return -1;
                  }

                  if (var6 == null) {
                     var7.q(var9 + var2);
                  }

                  return var2;
               } else {
                  return -1;
               }
            }
         }
      }
   }

   public int q(String var1, IVirtualMemory var2, long var3, boolean var5) {
      int var6 = this.RF(var1);
      if (var6 < 0) {
         return -1;
      } else {
         int var7 = this.q(var6, var2, var3, var5);
         this.RF(var6);
         return var7;
      }
   }

   public int RF(String var1, IVirtualMemory var2, long var3, boolean var5) {
      int var6 = this.RF(var1);
      if (var6 < 0) {
         return -1;
      } else {
         int var7 = this.q(var6, var2, var3, var5);
         this.RF(var6);
         return var7;
      }
   }

   public int q(int var1, IVirtualMemory var2, long var3, boolean var5) {
      cjw.eo var6 = (cjw.eo)this.qa.get(var1);
      if (var6 == null) {
         return -1;
      } else {
         cjw.nI var7 = var6.gO();
         if (!this.q(var7)) {
            return -1;
         } else {
            INativeType var8 = this.JY.Dw.getType("stat" + (var5 ? "64" : ""));
            VMWriter var9 = new VMWriter(var2, var3, var8);
            var9.set(cvm.q(new byte[]{48, 27, 47, 29, 23, 31}, 2, 103), var7.xK);
            var9.set(cvm.q(new byte[]{48, 27, 47, 16, 28, 6}, 2, 181), var7.Dw);
            var9.set(cvm.q(new byte[]{88, 7, 43, 44, 26, 19, 31}, 1, 43), var7.Dw());
            return !var9.commit() ? -1 : 0;
         }
      }
   }

   public int xK(String var1, IVirtualMemory var2, long var3, boolean var5) {
      int var6 = this.RF(var1);
      if (var6 < 0) {
         return -1;
      } else {
         int var7 = this.RF(var6, var2, var3, var5);
         this.RF(var6);
         return var7;
      }
   }

   public int RF(int var1, IVirtualMemory var2, long var3, boolean var5) {
      cjw.eo var6 = (cjw.eo)this.qa.get(var1);
      if (var6 == null) {
         return -1;
      } else {
         INativeType var7 = this.JY.Dw.getType(cvm.q(new byte[]{48, 27, 17, 13, 20, 26}, 2, 214) + (var5 ? "64" : ""));
         VMWriter var8 = new VMWriter(var2, var3, var7);
         char var9;
         if (var6.oW().startsWith(cvm.q(new byte[]{34, 95, 2, 29, 12, 76}, 1, 13))) {
            var9 = '龠';
         } else {
            var9 = '\uef53';
         }

         var8.set(cvm.q(new byte[]{37, 48, 4, 0, 2, 12}, 2, 217), Integer.valueOf(var9));
         var8.set(cvm.q(new byte[]{112, 57, 61, 17, 26, 19, 31}, 1, 22), 4096);
         return !var8.commit() ? -1 : 0;
      }
   }

   private boolean q(cjw.nI var1) {
      return this.q(var1, true, false);
   }

   private boolean q(cjw.nI var1, boolean var2, boolean var3) {
      if (var1.RF()) {
         return true;
      } else if (var1.Uv != null) {
         return true;
      } else {
         String var4 = var1.q;
         if (cvm.q(15, 7562543, -1013084365, var4)) {
            if (var2) {
               String var5 = this.JY.HF.Dw();
               var1.q(Strings.encodeUTF8(var5));
            }
         } else if (cvm.q(16, 7564335, 823801163, var4)) {
            if (var2) {
               String var18 = cvm.q(
                     new byte[]{
                        116,
                        13,
                        18,
                        27,
                        69,
                        93,
                        80,
                        88,
                        68,
                        16,
                        5,
                        84,
                        75,
                        66,
                        83,
                        14,
                        1,
                        7,
                        28,
                        16,
                        2,
                        16,
                        64,
                        25,
                        1,
                        80,
                        111,
                        66,
                        81,
                        83,
                        92,
                        85,
                        16,
                        81,
                        94,
                        68,
                        31,
                        88,
                        72,
                        19,
                        89,
                        84,
                        64,
                        24,
                        65,
                        70,
                        70,
                        73,
                        76,
                        73,
                        65,
                        84,
                        69,
                        83,
                        14,
                        0,
                        97,
                        76,
                        76,
                        0,
                        82,
                        73,
                        71,
                        72,
                        84,
                        83,
                        0,
                        82,
                        69,
                        83,
                        69,
                        82,
                        86,
                        74,
                        5,
                        94,
                        69,
                        59,
                        64,
                        19,
                        22,
                        31,
                        71,
                        6,
                        6,
                        16,
                        82,
                        71,
                        10,
                        77,
                        14,
                        67,
                        76,
                        87,
                        71,
                        69,
                        77,
                        87,
                        31,
                        94,
                        93,
                        78,
                        22,
                        123,
                        93,
                        3,
                        10,
                        3,
                        11,
                        73,
                        2,
                        65,
                        8,
                        70,
                        13,
                        17,
                        14,
                        26,
                        27,
                        121
                     },
                     2,
                     228
                  )
                  + cvm.q(
                     new byte[]{
                        116,
                        13,
                        18,
                        27,
                        69,
                        81,
                        83,
                        88,
                        68,
                        16,
                        5,
                        84,
                        75,
                        66,
                        83,
                        1,
                        9,
                        10,
                        28,
                        16,
                        2,
                        16,
                        64,
                        25,
                        84,
                        80,
                        111,
                        66,
                        81,
                        83,
                        95,
                        1,
                        16,
                        81,
                        94,
                        68,
                        31,
                        88,
                        72,
                        19,
                        89,
                        84,
                        64,
                        24,
                        65,
                        70,
                        70,
                        73,
                        76,
                        73,
                        65,
                        84,
                        69,
                        83,
                        14,
                        0,
                        97,
                        76,
                        76,
                        0,
                        82,
                        73,
                        71,
                        72,
                        84,
                        83,
                        0,
                        82,
                        69,
                        83,
                        69,
                        82,
                        86,
                        74,
                        5,
                        94,
                        69,
                        59,
                        64,
                        19,
                        22,
                        31,
                        71,
                        6,
                        6,
                        16,
                        82,
                        71,
                        10,
                        77,
                        14,
                        67,
                        76,
                        87,
                        71,
                        69,
                        77,
                        87,
                        31,
                        94,
                        93,
                        78,
                        22,
                        123,
                        93,
                        3,
                        10,
                        3,
                        11,
                        73,
                        2,
                        65,
                        8,
                        70,
                        13,
                        17,
                        14,
                        26,
                        27,
                        121
                     },
                     2,
                     43
                  )
                  + cvm.q(
                     new byte[]{
                        -62,
                        85,
                        0,
                        0,
                        90,
                        8,
                        9,
                        9,
                        0,
                        0,
                        29,
                        26,
                        85,
                        0,
                        0,
                        90,
                        8,
                        84,
                        84,
                        0,
                        0,
                        16,
                        82,
                        95,
                        0,
                        93,
                        80,
                        16,
                        0,
                        0,
                        83,
                        81,
                        2,
                        0,
                        0,
                        16,
                        16,
                        7,
                        13,
                        9,
                        3,
                        16,
                        19,
                        11,
                        24,
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
                        15,
                        78,
                        17,
                        21,
                        29,
                        87,
                        76,
                        12,
                        2,
                        67,
                        79,
                        15,
                        10,
                        22,
                        29,
                        6,
                        13,
                        74,
                        92,
                        7,
                        27,
                        26,
                        29,
                        4,
                        8,
                        74,
                        67,
                        5,
                        11,
                        84,
                        2,
                        27,
                        77,
                        11,
                        6,
                        1,
                        7,
                        10,
                        76,
                        67,
                        5,
                        11,
                        1,
                        77,
                        93,
                        28,
                        101
                     },
                     1,
                     245
                  )
                  + cvm.q(
                     new byte[]{
                        -2,
                        85,
                        0,
                        0,
                        90,
                        8,
                        84,
                        84,
                        0,
                        0,
                        29,
                        26,
                        85,
                        0,
                        0,
                        90,
                        8,
                        86,
                        86,
                        0,
                        0,
                        16,
                        82,
                        5,
                        90,
                        93,
                        80,
                        16,
                        0,
                        0,
                        83,
                        86,
                        5,
                        0,
                        0,
                        16,
                        16,
                        7,
                        13,
                        9,
                        3,
                        16,
                        19,
                        11,
                        24,
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
                        15,
                        78,
                        17,
                        21,
                        29,
                        87,
                        76,
                        12,
                        2,
                        67,
                        79,
                        15,
                        10,
                        22,
                        29,
                        6,
                        13,
                        74,
                        92,
                        7,
                        27,
                        26,
                        29,
                        4,
                        8,
                        74,
                        67,
                        5,
                        11,
                        84,
                        2,
                        27,
                        77,
                        11,
                        6,
                        1,
                        7,
                        10,
                        76,
                        67,
                        5,
                        11,
                        1,
                        77,
                        93,
                        28,
                        101
                     },
                     1,
                     201
                  );
               var1.q(Strings.encodeUTF8(var18));
            }
         } else if (cvm.q(17, 7564335, 200551913, var4)) {
            if (var2) {
               String var19 = this.JY.nf();
               cjy var6 = new cjy(var19, null, null);
               String var7 = var6.q(null);
               var1.q(Strings.encodeUTF8(var7));
            }
         } else if (cvm.q(13, 7299887, 17989870, var4)) {
            if (var2) {
               byte[] var20 = this.Dw(9);
               if (var20 == null) {
                  var20 = Strings.encodeUTF8("Processor  : AArch64 Processor rev 14 (aarch64)");
               }

               var1.q(var20);
            }
         } else {
            if (var4.startsWith(cvm.q(new byte[]{108, 11, 17, 13, 19, 70}, 2, 141))) {
               IEmulatedAndroid var22 = this.JY.xK();
               if (var22 != null) {
                  if (var4.startsWith(cvm.q(new byte[]{-122, 75, 5, 21, 21, 78, 78, 17, 0, 95}, 1, 169))) {
                     String var25 = var22.getAppFolder();
                     if (var4.startsWith(var25 + "/")) {
                        String var27 = var4.substring(var25.length() + 1);
                        if (var27.startsWith(cvm.q(new byte[]{-27, 5, 11, 77, 78, 19, 31, 91, 2, 27}, 1, 137))) {
                           String var28 = var27.substring(10);
                           File var9 = var22.getLocalApkFile();
                           if (var9 != null) {
                              try {
                                 boolean var13;
                                 try (ZipBrowser var10 = new ZipBrowser(var9)) {
                                    String var11 = cvm.q(new byte[]{-15, 5, 11, 77, 78, 19, 31, 91, 2, 25, 91, 78, 89, 78}, 1, 157) + var28;
                                    if (!var2) {
                                       if (var10.getEntry(var11) != null) {
                                          return true;
                                       }

                                       return false;
                                    }

                                    InputStream var29 = var10.getEntryStream(var11);
                                    var1.q(com.pnfsoftware.jeb.util.io.IO.readInputStream(var29));
                                    var13 = true;
                                 }

                                 return var13;
                              } catch (IOException var17) {
                              }
                           }
                        }
                     }
                  } else if (var4.startsWith(cvm.q(new byte[]{27, 75, 5, 21, 21, 78, 90, 6, 22, 23, 93}, 1, 52))) {
                     File var24 = var22.getDropboxFolder();
                     String var26 = var22.getAppDataFolder();
                     if (var4.startsWith(var26 + "/")) {
                        File var8 = new File(var24, var4);

                        try {
                           if (var8.isFile()) {
                              if (var2) {
                                 var1.q(com.pnfsoftware.jeb.util.io.IO.readFile(var8));
                              }

                              return true;
                           }

                           if (var3) {
                              com.pnfsoftware.jeb.util.io.IO.createFile(var8, false);
                              return true;
                           }
                        } catch (IOException var15) {
                        }
                     }
                  }
               }

               return false;
            }

            if (!var4.startsWith("/")) {
               return false;
            }

            boolean var21 = false;
            if (var4.startsWith(cvm.q(new byte[]{-52, 77, 11, 7, 65}, 1, 227))
               || var4.startsWith(cvm.q(new byte[]{108, 28, 9, 10, 6, 12, 10, 71, 22, 73, 70, 76}, 2, 83))) {
               var21 = true;
            } else if (var4.startsWith(cvm.q(new byte[]{108, 14, 0, 28, 10, 70}, 2, 118)) && Strings.endsWith(var4, ".so", ".oat")) {
               var21 = true;
            } else if (var4.startsWith(cvm.q(new byte[]{108, 28, 9, 10, 6, 12, 10, 71, 24, 73, 74, 85, 29, 15}, 2, 66))) {
               var21 = true;
            } else if (var4.startsWith(cvm.q(new byte[]{-21, 89, 19, 11, 10, 11, 29, 93, 67, 5, 11, 84, 2, 27}, 1, 196))) {
               var21 = true;
            } else if (var4.startsWith(cvm.q(new byte[]{108, 31, 2, 22, 22, 28, 4, 28, 91, 76, 65, 1, 31, 20, 30}, 2, 252))) {
               var21 = true;
            }

            if (!var21) {
               return false;
            }

            if (var2) {
               byte[] var23;
               if (var4.endsWith(cvm.q(new byte[]{109, 28, 31}, 2, 107))) {
                  var23 = this.Dw(8);
               } else {
                  var23 = this.Dw(7);
               }

               if (var23 == null) {
                  var23 = AssetManager.getAssetBytes(
                     cvm.q(new byte[]{34, 1, 20, 11, 29, 0, 3, 69, 22, 73, 70, 76, 72, 82, 92, 15, 13, 30, 90, 24, 83, 31, 64, 85, 66, 79, 43}, 2, 210)
                  );
                  if (var23 == null) {
                     return false;
                  }
               }

               var1.q(var23);
            }
         }

         var1.xK = 5;
         var1.Dw = var4.hashCode() & 65535;
         return true;
      }
   }

   private byte[] Dw(int var1) {
      if (this.JY.CE != null) {
         try {
            return this.JY.CE.xK(var1);
         } catch (IOException var2) {
         }
      }

      return null;
   }

   public int q(int var1, int var2, int var3) {
      cjw.eo var4 = (cjw.eo)this.qa.get(var1);
      if (var4 == null || !var4.Dw() || !var4.xK()) {
         return -1;
      } else if (!this.q(var4.gO())) {
         return -1;
      } else {
         if (var3 == this.Uv) {
            var4.q(var2);
         } else if (var3 == this.oW) {
            var4.q(var4.nf() + var2);
         } else {
            if (var3 != this.gO) {
               return -1;
            }

            if (!var4.gO().xK()) {
               return -1;
            }

            var4.q(var4.gO().Dw() + var2);
         }

         return var4.nf();
      }
   }

   private int xK(String var1, int var2) {
      Long var3 = this.JY.Dw.getTypeLibraryService().findFirstIntegerConstantByName(var1);
      return var3 == null ? var2 : var3.intValue();
   }

   private cjw.eo q(int var1, String var2, cjw.nI var3) {
      int var4 = this.RF();
      cjw.eo var5 = new cjw.eo(var4, var1, var2, var3);
      this.qa.put(var4, var5);
      return var5;
   }

   private int[] xK() {
      int var1 = this.LK++;
      cjw.nI var2 = new cjw.nI(cvm.q(new byte[]{108, 31, 25, 9, 23, 26, 72}, 2, 60) + var1, 1);
      cjw.eo var3 = this.q(1, null, var2);
      cjw.eo var4 = this.q(2, null, var2);
      return new int[]{var3.q, var4.q};
   }

   public int q(int[] var1) {
      return this.q(var1, 0);
   }

   public int q(int[] var1, int var2) {
      if ((var2 & ~(this.gP | this.za)) != 0) {
         return -1;
      } else {
         int[] var3 = this.xK();
         var1[0] = var3[0];
         var1[1] = var3[1];
         return 0;
      }
   }

   public int xK(int var1) {
      return this.q(var1, null);
   }

   public int q(int var1, Integer var2) {
      return this.q(var1, var2, null);
   }

   public int q(int var1, Integer var2, Integer var3) {
      cjw.eo var4 = (cjw.eo)this.qa.get(var1);
      if (var4 == null) {
         return -1;
      } else if (var2 == null || var2 != var1) {
         int var5 = var3 == null ? 0 : var3;
         if ((var5 & ~this.za) != 0) {
            return -1;
         } else {
            if (var2 == null) {
               var2 = this.RF();
            } else if (this.qa.containsKey(var2)) {
               this.RF(var2);
            }

            cjw.eo var6 = new cjw.eo(var2, var4.RF, var4.xK);
            this.qa.put(var2, var6);
            return var2;
         }
      } else {
         return var3 == null ? var2 : -1;
      }
   }

   public String xK(String var1) {
      return cvm.q(14, 6645039, -696186686, var1)
         ? cvm.q(new byte[]{8, 92, 10, 10, 7, 17, 8, 66, 77, 11, 7, 65, 78, 17, 0, 47, 47, 2, 29, 12, 6, 22, 0, 69, 2}, 1, 39)
         : null;
   }

   public static class CU {
      String q;
      int RF;
      cjw.nI xK;

      public CU(String var1, cjw.nI var2) {
         if (var2 == null) {
            throw new IllegalArgumentException();
         } else {
            this.q = var1;
            this.xK = var2;
         }
      }

      public boolean q() {
         return this.xK.q();
      }

      public boolean RF() {
         return this.xK.RF();
      }

      public int xK() {
         return this.RF;
      }

      public int Dw() {
         if (this.xK.q()) {
            return this.xK.Uv.limit() - this.RF;
         } else {
            return this.xK.RF() ? this.xK.oW.available() : 0;
         }
      }

      @Override
      public String toString() {
         return Strings.ff(cvm.q(new byte[]{127, 86, 51, 112, 72, 93, 125}, 1, 90), this.xK, this.RF);
      }
   }

   public static class eo {
      int q;
      int RF;
      cjw.CU xK;

      public eo(int var1, int var2, String var3, cjw.nI var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = new cjw.CU(var3, var4);
      }

      public eo(int var1, int var2, cjw.CU var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }

      public boolean q() {
         return (this.RF & 1) != 0;
      }

      public boolean RF() {
         return (this.RF & 2) != 0;
      }

      public boolean xK() {
         return (this.RF & 4) != 0;
      }

      public boolean Dw() {
         return this.xK.q();
      }

      public boolean Uv() {
         return this.xK.RF();
      }

      public String oW() {
         return this.xK.q;
      }

      public cjw.nI gO() {
         return this.xK.xK;
      }

      public int nf() {
         return this.xK.RF;
      }

      public void q(int var1) {
         this.xK.RF = var1;
      }

      public int gP() {
         if (this.xK.q()) {
            if (this.xK.xK.Uv != null) {
               return this.xK.xK.Uv.limit() - this.xK.RF;
            }
         } else if (this.xK.RF() && this.xK.xK.oW != null) {
            return this.xK.xK.oW.available();
         }

         return 0;
      }

      @Override
      public String toString() {
         return Strings.ff(cvm.q(new byte[]{29, 2, 100, 70, 9, 29, 72, 26, 5, 86, 83, 8, 78, 10, 13, 6, 20, 78, 13, 72, 93, 125, 113}, 1, 91), this.xK, this.RF);
      }
   }

   public static class nI {
      String q;
      int RF;
      int xK;
      int Dw;
      ByteBuffer Uv;
      BytePipe oW;

      nI(String var1, int var2) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else if (var2 != 0 && var2 != 1) {
            throw new IllegalArgumentException();
         } else {
            this.q = var1;
            this.RF = var2;
            if (var2 == 1) {
               this.oW = new BytePipe();
            }
         }
      }

      public boolean q() {
         return this.RF == 0;
      }

      public boolean RF() {
         return this.RF == 1;
      }

      public void q(byte[] var1) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.Uv = ByteBuffer.wrap(var1);
         }
      }

      public boolean xK() {
         return this.q() && !this.q.startsWith(cvm.q(new byte[]{108, 31, 2, 22, 17, 70}, 2, 125));
      }

      public int Dw() {
         return this.Uv != null && this.xK() ? this.Uv.limit() : 0;
      }

      public int Uv() {
         if (this.Uv != null) {
            return this.Uv.limit();
         } else {
            return this.oW != null ? this.oW.available() : 0;
         }
      }

      @Override
      public String toString() {
         return this.q;
      }
   }
}
