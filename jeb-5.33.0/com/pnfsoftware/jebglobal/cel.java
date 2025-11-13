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
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class cel {
   private static final ILogger ld = GlobalLog.getLogger(cel.class);
   private static final Random gp = new Random();
   private cdz oT;
   final int pC;
   final int A;
   final int kS;
   final int wS;
   final int UT;
   final int E;
   final int sY;
   final int ys;
   private int fI = 292;
   private int WR = 1;
   private Map NS = new HashMap();
   private Map vP = new HashMap();
   private String xC = "/";
   private static final String ED = ckx.pC(new byte[]{-3, 95, 2, 29, 12, 76, 92, 22, 9, 10, 73, 74, 29, 29}, 1, 210);
   private static final String Sc = ckx.pC(new byte[]{-2, 95, 2, 29, 12, 76, 92, 22, 9, 10, 73, 73, 2, 75}, 1, 209);

   public cel(cdz var1) {
      this.oT = var1;
      this.pC = this.kS(ckx.pC(new byte[]{2, 59, 47, 63, 54, 42, 48, 44}, 2, 149), -1);
      this.A = this.kS(ckx.pC(new byte[]{16, 42, 53, 50, 45, 58, 34, 60}, 2, 91), -1);
      this.kS = this.kS(ckx.pC(new byte[]{-6, 22, 0, 14, 20, 28, 22, 7}, 1, 169), -1);
      this.wS = this.kS(ckx.pC(new byte[]{16, 42, 53, 50, 45, 44, 41, 44}, 2, 30), -1);
      this.UT = this.kS(ckx.pC(new byte[]{43, 17, 21, 28, 23, 18, 12, 25}, 1, 123), -1);
      this.E = this.kS(ckx.pC(new byte[]{12, 48, 62, 54, 60, 43, 43, 39, 55, 107}, 2, 84), -1);
      this.sY = this.kS(ckx.pC(new byte[]{-100, 16, 28, 15, 3, 10, 29, 29, 6}, 1, 211), -1);
      this.ys = this.kS(ckx.pC(new byte[]{-59, 16, 28, 17, 23, 4, 21}, 1, 138), -1);
   }

   public int pC(String var1) {
      return this.kS(var1, 0);
   }

   public cel.Av pC(int var1) {
      return (cel.Av)this.vP.get(var1);
   }

   public String pC() {
      return this.xC;
   }

   public String A(String var1) {
      if (!var1.startsWith("/")) {
         var1 = this.xC + "/" + var1;
      }

      return IO.simplifyPathUnix(var1);
   }

   private cel.K pC(String var1, boolean var2, boolean var3) {
      var1 = this.A(var1);
      cel.K var4 = (cel.K)this.NS.get(var1);
      if (var4 == null) {
         var4 = new cel.K(var1, 0);
         if (!this.pC(var4, var3, var2)) {
            return null;
         }

         this.NS.put(var1, var4);
      }

      return var4;
   }

   private int A() {
      int var1 = this.fI++;

      while (this.vP.containsKey(var1)) {
         var1 = this.fI++;
      }

      return var1;
   }

   public int pC(String var1, int var2) {
      int var3 = this.kS(var1);
      if (var3 == -1) {
         return -1;
      } else {
         this.A(var3);
         return 0;
      }
   }

   public int pC(String var1, int var2, int var3, boolean var4) {
      byte var5;
      if ((var2 & 1) == 1) {
         var5 = 2;
      } else if ((var2 & 2) == 2) {
         var5 = 3;
      } else {
         var5 = 1;
      }

      var5 |= 4;
      boolean var6 = (var2 & this.ys) != 0;
      this.oT.fI().monitorHLSpecial(0, var1, var2);
      cel.K var7 = this.pC(var1, var6, var4);
      if (var7 == null) {
         return -1;
      } else {
         int var8 = this.A();
         cel.Av var9 = new cel.Av(var8, var5, var1, var7);
         this.vP.put(var8, var9);
         return var8;
      }
   }

   public int pC(String var1, int var2, int var3) {
      return this.pC(var1, var2, var3, false);
   }

   public int kS(String var1) {
      return this.pC(var1, 0, 0);
   }

   public int A(int var1) {
      return this.vP.remove(var1) == null ? -1 : 0;
   }

   public int A(String var1, int var2) {
      return -1;
   }

   public int pC(int var1, int var2, byte[] var3, int var4) {
      cel.Av var5 = (cel.Av)this.vP.get(var1);
      if (var5 == null || !var5.A()) {
         return -1;
      } else if (!this.pC(var5.sY())) {
         return -1;
      } else if (var2 == 0) {
         return 0;
      } else if (var5.wS()) {
         IEmulatedAndroid var6 = this.oT.kS();
         if (var6 == null) {
            return -1;
         } else {
            File var7 = var6.getDropboxFolder();
            File var8 = new File(var7, var5.kS.kS.pC);
            if (IO.inFolder(var8, var7)) {
               try (RandomAccessFile var9 = new RandomAccessFile(var8, ckx.pC(new byte[]{109, 5}, 1, 31))) {
                  var9.seek(var5.kS.A);
                  var9.write(var3, var4, var2);
               } catch (IOException var14) {
                  return -1;
               }

               var5.pC(var5.ys() + var2);
               return var2;
            } else {
               return -1;
            }
         }
      } else {
         return -1;
      }
   }

   public int A(int var1, int var2, byte[] var3, int var4) {
      cel.Av var5 = (cel.Av)this.vP.get(var1);
      if (var5 == null || !var5.pC()) {
         return -1;
      } else if (!this.pC(var5.sY())) {
         return -1;
      } else if (var2 == 0) {
         return 0;
      } else {
         int var6 = var5.ld();
         if (var6 == 0) {
            return 0;
         } else if (var5.wS()) {
            int var7 = Math.min(var2, var6);
            System.arraycopy(var5.sY().UT.array(), var5.ys(), var3, var4, var7);
            var5.pC(var5.ys() + var7);
            return var7;
         } else {
            return -1;
         }
      }
   }

   public int pC(int var1, int var2, int var3, boolean var4, byte[] var5, int var6) {
      cel.Av var7 = (cel.Av)this.vP.get(var1);
      if (var7 == null || !var7.pC()) {
         return -1;
      } else if (!this.pC(var7.sY())) {
         return -1;
      } else if (var2 == 0) {
         return 0;
      } else {
         int var8 = var7.ld();
         if (var8 == 0) {
            return 0;
         } else if (!var7.wS()) {
            return -1;
         } else {
            int var9 = 0;

            for (int var10 = var7.ys(); var10 < var7.sY().UT() && var9 < var2; var10++) {
               var9++;
               if ((var7.sY().UT.get(var10) & 255) == var3) {
                  if (!var4) {
                     var9--;
                  }
                  break;
               }
            }

            System.arraycopy(var7.sY().UT.array(), var7.ys(), var5, var6, var9);
            var7.pC(var7.ys() + var9);
            return var9;
         }
      }
   }

   public int pC(int var1, int var2, IVirtualMemory var3, long var4, Integer var6) {
      cel.Av var7 = (cel.Av)this.vP.get(var1);
      if (var7 == null || !var7.pC()) {
         return -1;
      } else if (!this.pC(var7.sY())) {
         return -1;
      } else if (var2 == 0) {
         return 0;
      } else if (var2 < 0) {
         return -1;
      } else if (var7.wS() && var7.E().equals("/dev/urandom")) {
         byte[] var12 = new byte[var2];
         gp.nextBytes(var12);
         return !VirtualMemoryUtil.writeBytes(var3, var4, var12, 0, var2) ? -1 : var2;
      } else {
         int var8 = var7.ld();
         if (var8 == 0) {
            return 0;
         } else {
            int var9 = Math.min(var2, var8);
            if (var7.UT()) {
               byte[] var13 = new byte[var9];
               var7.sY().E.get(var13);
               return !VirtualMemoryUtil.writeBytes(var3, var4, var13, 0, var9) ? -1 : var9;
            } else {
               int var10 = var6 != null ? var6 : var7.ys();
               byte[] var11 = var7.sY().UT.array();
               if (!VirtualMemoryUtil.writeBytes(var3, var4, var11, var10, var9)) {
                  return -1;
               } else {
                  if (var6 == null) {
                     var7.pC(var10 + var9);
                  }

                  return var9;
               }
            }
         }
      }
   }

   public int A(int var1, int var2, IVirtualMemory var3, long var4, Integer var6) {
      cel.Av var7 = (cel.Av)this.vP.get(var1);
      if (var7 == null || !var7.A()) {
         return -1;
      } else if (!this.pC(var7.sY())) {
         return -1;
      } else if (var2 == 0) {
         return 0;
      } else if (var2 < 0) {
         return -1;
      } else if (var7.wS() && var7.E().equals("/dev/urandom")) {
         return var2;
      } else if (var7.UT()) {
         byte[] var19 = new byte[var2];
         if (!VirtualMemoryUtil.readBytes(var3, var4, var19, 0, var19.length)) {
            return -1;
         } else {
            var7.sY().E.append(var19);
            return var2;
         }
      } else {
         IEmulatedAndroid var8 = this.oT.kS();
         if (var8 == null) {
            return -1;
         } else {
            int var9 = var6 != null ? var6 : var7.ys();
            byte[] var10 = new byte[var2];
            if (!VirtualMemoryUtil.readBytes(var3, var4, var10, 0, var10.length)) {
               return -1;
            } else {
               File var11 = var8.getDropboxFolder();
               File var12 = new File(var11, var7.kS.kS.pC);
               if (IO.inFolder(var12, var11)) {
                  try (RandomAccessFile var13 = new RandomAccessFile(var12, ckx.pC(new byte[]{49, 24}, 2, 137))) {
                     var13.seek(var9);
                     var13.write(var10, var9, var2);
                  } catch (IOException var18) {
                     return -1;
                  }

                  if (var6 == null) {
                     var7.pC(var9 + var2);
                  }

                  return var2;
               } else {
                  return -1;
               }
            }
         }
      }
   }

   public int pC(String var1, IVirtualMemory var2, long var3, boolean var5) {
      int var6 = this.kS(var1);
      if (var6 < 0) {
         return -1;
      } else {
         int var7 = this.pC(var6, var2, var3, var5);
         this.A(var6);
         return var7;
      }
   }

   public int A(String var1, IVirtualMemory var2, long var3, boolean var5) {
      int var6 = this.kS(var1);
      if (var6 < 0) {
         return -1;
      } else {
         int var7 = this.pC(var6, var2, var3, var5);
         this.A(var6);
         return var7;
      }
   }

   public int pC(int var1, IVirtualMemory var2, long var3, boolean var5) {
      cel.Av var6 = (cel.Av)this.vP.get(var1);
      if (var6 == null) {
         return -1;
      } else {
         cel.K var7 = var6.sY();
         if (!this.pC(var7)) {
            return -1;
         } else {
            INativeType var8 = this.oT.wS.getType("stat" + (var5 ? "64" : ""));
            VMWriter var9 = new VMWriter(var2, var3, var8);
            var9.set(ckx.pC(new byte[]{48, 27, 47, 29, 23, 31}, 2, 148), var7.kS);
            var9.set(ckx.pC(new byte[]{23, 7, 43, 54, 7, 1}, 1, 100), var7.wS);
            var9.set(ckx.pC(new byte[]{48, 27, 47, 10, 27, 19, 2}, 2, 33), var7.wS());
            return !var9.commit() ? -1 : 0;
         }
      }
   }

   public int kS(String var1, IVirtualMemory var2, long var3, boolean var5) {
      int var6 = this.kS(var1);
      if (var6 < 0) {
         return -1;
      } else {
         int var7 = this.A(var6, var2, var3, var5);
         this.A(var6);
         return var7;
      }
   }

   public int A(int var1, IVirtualMemory var2, long var3, boolean var5) {
      cel.Av var6 = (cel.Av)this.vP.get(var1);
      if (var6 == null) {
         return -1;
      } else {
         INativeType var7 = this.oT.wS.getType(ckx.pC(new byte[]{106, 7, 21, 21, 18, 21}, 1, 25) + (var5 ? "64" : ""));
         VMWriter var8 = new VMWriter(var2, var3, var7);
         char var9;
         if (var6.E().startsWith(ckx.pC(new byte[]{-124, 95, 2, 29, 12, 76}, 1, 171))) {
            var9 = '龠';
         } else {
            var9 = '\uef53';
         }

         var8.set(ckx.pC(new byte[]{6, 57, 43, 13, 9, 21}, 1, 96), Integer.valueOf(var9));
         var8.set(ckx.pC(new byte[]{37, 48, 18, 10, 27, 19, 2}, 2, 61), 4096);
         return !var8.commit() ? -1 : 0;
      }
   }

   public boolean kS(int var1) {
      cel.Av var2 = (cel.Av)this.vP.get(var1);
      if (var2 == null) {
         return false;
      } else if (!var2.wS()) {
         return false;
      } else {
         return var2.kS.kS.UT != null ? true : this.pC(var2.kS.kS);
      }
   }

   public Boolean wS(int var1) {
      cel.Av var2 = (cel.Av)this.vP.get(var1);
      if (var2 == null) {
         return null;
      } else {
         return var2.wS() && !this.kS(var1) ? null : var2.kS.kS() <= 0;
      }
   }

   private boolean pC(cel.K var1) {
      return this.pC(var1, true, false);
   }

   private boolean pC(cel.K var1, boolean var2, boolean var3) {
      if (var1.A()) {
         return true;
      } else if (var1.UT != null) {
         return true;
      } else {
         String var4 = var1.pC;
         if (var4.startsWith("/proc/") && !var4.startsWith("/proc/self")) {
            String var5 = var4.substring(6);
            int var6 = var5.indexOf("/");
            if (var6 >= 0) {
               var5 = var5.substring(0, var6);
            } else {
               var6 = var5.length();
            }

            try {
               int var7 = Integer.parseInt(var5);
               if (var7 == this.oT.kS().getProcessId()) {
                  var4 = "/proc/self" + var4.substring(6 + var6);
               }
            } catch (Exception var17) {
            }
         }

         if (ckx.pC(18, 6645295, 8875880, var4)) {
            if (var2) {
               String var20 = this.oT.E();
               var1.pC(Strings.encodeUTF8(var20));
            }
         } else if (ckx.pC(15, 7562543, -1013084365, var4)) {
            if (var2) {
               String var21 = this.oT.NS.A();
               var1.pC(Strings.encodeUTF8(var21));
            }
         } else if (ckx.pC(16, 7564335, 823801163, var4)) {
            if (var2) {
               String var22 = ckx.pC(
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
                        24,
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
                        38,
                        23,
                        95,
                        26,
                        29,
                        4,
                        73,
                        9,
                        26,
                        68,
                        90,
                        12,
                        64,
                        68,
                        31,
                        75,
                        76,
                        93,
                        88,
                        73,
                        95,
                        85,
                        29,
                        89,
                        69,
                        66,
                        121,
                        70,
                        78,
                        1,
                        5,
                        10,
                        78,
                        8,
                        13,
                        75,
                        67,
                        6,
                        16,
                        67,
                        71,
                        7,
                        28,
                        42
                     },
                     2,
                     92
                  )
                  + ckx.pC(
                     new byte[]{
                        -86,
                        85,
                        0,
                        0,
                        85,
                        15,
                        12,
                        4,
                        0,
                        0,
                        29,
                        26,
                        85,
                        0,
                        0,
                        90,
                        8,
                        9,
                        9,
                        0,
                        0,
                        16,
                        82,
                        95,
                        85,
                        8,
                        80,
                        16,
                        0,
                        0,
                        3,
                        87,
                        84,
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
                     157
                  )
                  + ckx.pC(
                     new byte[]{
                        116,
                        13,
                        18,
                        27,
                        74,
                        89,
                        94,
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
                        87,
                        28,
                        16,
                        2,
                        16,
                        64,
                        24,
                        1,
                        80,
                        111,
                        66,
                        81,
                        83,
                        15,
                        87,
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
                        38,
                        23,
                        95,
                        26,
                        29,
                        4,
                        73,
                        9,
                        26,
                        68,
                        90,
                        12,
                        64,
                        68,
                        31,
                        75,
                        76,
                        93,
                        88,
                        73,
                        95,
                        85,
                        29,
                        89,
                        69,
                        66,
                        121,
                        70,
                        78,
                        1,
                        5,
                        10,
                        78,
                        8,
                        13,
                        75,
                        67,
                        6,
                        16,
                        67,
                        71,
                        7,
                        28,
                        42
                     },
                     2,
                     100
                  )
                  + ckx.pC(
                     new byte[]{
                        116,
                        13,
                        18,
                        27,
                        74,
                        89,
                        3,
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
                        85,
                        28,
                        16,
                        2,
                        16,
                        64,
                        66,
                        1,
                        80,
                        111,
                        66,
                        81,
                        83,
                        15,
                        80,
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
                        38,
                        23,
                        95,
                        26,
                        29,
                        4,
                        73,
                        9,
                        26,
                        68,
                        90,
                        12,
                        64,
                        68,
                        31,
                        75,
                        76,
                        93,
                        88,
                        73,
                        95,
                        85,
                        29,
                        89,
                        69,
                        66,
                        121,
                        70,
                        78,
                        1,
                        5,
                        10,
                        78,
                        8,
                        13,
                        75,
                        67,
                        6,
                        16,
                        67,
                        71,
                        7,
                        28,
                        42
                     },
                     2,
                     145
                  );
               var1.pC(Strings.encodeUTF8(var22));
            }
         } else if (ckx.pC(17, 7564335, 200551913, var4)) {
            if (var2) {
               String var23 = this.oT.gp();
               cen var27 = new cen(var23, null, null);
               String var31 = var27.pC(null);
               var1.pC(Strings.encodeUTF8(var31));
            }
         } else if (ckx.pC(13, 7299887, 17989870, var4)) {
            if (var2) {
               byte[] var24 = this.E(9);
               if (var24 == null) {
                  var24 = Strings.encodeUTF8("Processor  : AArch64 Processor rev 14 (aarch64)");
               }

               var1.pC(var24);
            }
         } else {
            if (var4.startsWith(ckx.pC(new byte[]{-53, 75, 5, 21, 21, 78}, 1, 228))) {
               IEmulatedAndroid var26 = this.oT.kS();
               if (var26 != null) {
                  if (var4.startsWith(ckx.pC(new byte[]{-44, 75, 5, 21, 21, 78, 78, 17, 0, 95}, 1, 251))) {
                     String var30 = var26.getAppFolder();
                     if (var4.startsWith(var30 + "/")) {
                        String var33 = var4.substring(var30.length() + 1);
                        if (var33.startsWith(ckx.pC(new byte[]{47, 6, 18, 86, 19, 27, 10, 94, 64, 15}, 2, 150))) {
                           String var35 = var33.substring(10);
                           File var36 = var26.getLocalApkFile();
                           if (var36 != null) {
                              try {
                                 boolean var13;
                                 try (ZipBrowser var10 = new ZipBrowser(var36)) {
                                    String var11 = ckx.pC(new byte[]{-23, 5, 11, 77, 78, 19, 31, 91, 2, 25, 91, 78, 89, 78}, 1, 133) + var35;
                                    if (!var2) {
                                       if (var10.getEntry(var11) != null) {
                                          return true;
                                       }

                                       return false;
                                    }

                                    InputStream var37 = var10.getEntryStream(var11);
                                    var1.pC(IO.readInputStream(var37));
                                    var13 = true;
                                 }

                                 return var13;
                              } catch (IOException var19) {
                              }
                           }
                        } else if (var33.equals("base.apk")) {
                           if (var2) {
                              File var34 = var26.getLocalApkFile();

                              try {
                                 byte[] var9 = IO.readFile(var34);
                                 var1.pC(var9);
                              } catch (IOException var14) {
                                 return false;
                              }
                           }

                           return true;
                        }
                     }
                  } else if (var4.startsWith(ckx.pC(new byte[]{108, 11, 17, 13, 19, 70, 18, 27, 17, 82, 7}, 2, 7))) {
                     File var29 = var26.getDropboxFolder();
                     String var32 = var26.getAppDataFolder();
                     if (var4.startsWith(var32 + "/")) {
                        File var8 = new File(var29, var4);

                        try {
                           if (var8.isFile()) {
                              if (var2) {
                                 var1.pC(IO.readFile(var8));
                              }

                              return true;
                           }

                           if (var3) {
                              IO.createFile(var8, false);
                              return true;
                           }
                        } catch (IOException var16) {
                        }
                     }
                  }
               }

               return false;
            }

            if (var4.equals("/dev/binder")) {
               if (var2) {
               }
            } else if (var4.equals("/dev/urandom")) {
               if (var2) {
               }
            } else {
               if (!var4.startsWith("/")) {
                  return false;
               }

               boolean var25 = false;
               if (var4.startsWith(ckx.pC(new byte[]{108, 13, 25, 23, 93}, 2, 222))
                  || var4.startsWith(ckx.pC(new byte[]{108, 28, 9, 10, 6, 12, 10, 71, 22, 73, 70, 76}, 2, 42))) {
                  var25 = true;
               } else if (var4.startsWith(ckx.pC(new byte[]{108, 14, 0, 28, 10, 70}, 2, 46)) && Strings.endsWith(var4, ".so", ".oat")) {
                  var25 = true;
               } else if (var4.startsWith(ckx.pC(new byte[]{108, 28, 9, 10, 6, 12, 10, 71, 24, 73, 74, 85, 29, 15}, 2, 115))) {
                  var25 = true;
               } else if (var4.startsWith(ckx.pC(new byte[]{-32, 89, 19, 11, 10, 11, 29, 93, 67, 5, 11, 84, 2, 27}, 1, 207))) {
                  var25 = true;
               } else if (var4.startsWith(ckx.pC(new byte[]{52, 95, 2, 29, 11, 17, 22, 23, 91, 67, 5, 11, 84, 2, 27}, 1, 27))) {
                  var25 = true;
               } else if (var4.equals("/proc/self/exe")) {
                  var25 = true;
               }

               if (!var25) {
                  return false;
               }

               if (var2) {
                  byte[] var28;
                  if (var4.endsWith(ckx.pC(new byte[]{109, 28, 31}, 2, 212))) {
                     if (var4.endsWith("/libc++.so")) {
                        var28 = this.E(10);
                     } else {
                        var28 = this.E(8);
                     }
                  } else if (var4.endsWith("/linker64")) {
                     var28 = this.E(11);
                  } else {
                     var28 = this.E(7);
                  }

                  if (var28 == null) {
                     var28 = AssetManager.getAssetBytes(
                        ckx.pC(new byte[]{60, 15, 10, 22, 29, 6, 13, 73, 79, 11, 7, 65, 78, 19, 31, 91, 2, 25, 91, 78, 89, 78, 93, 19, 15, 1, 11}, 1, 93)
                     );
                     if (var28 == null) {
                        return false;
                     }
                  }

                  var1.pC(var28);
               }
            }
         }

         var1.kS = 5;
         var1.wS = var4.hashCode() & 65535;
         return true;
      }
   }

   private byte[] E(int var1) {
      if (this.oT.FE != null) {
         try {
            return this.oT.FE.kS(var1);
         } catch (IOException var2) {
         }
      }

      return null;
   }

   public int pC(int var1, int var2, int var3) {
      cel.Av var4 = (cel.Av)this.vP.get(var1);
      if (var4 == null || !var4.wS() || !var4.kS()) {
         return -1;
      } else if (!this.pC(var4.sY())) {
         return -1;
      } else if (var4.wS() && var4.E().equals("/dev/urandom")) {
         return 0;
      } else {
         if (var3 == this.A) {
            var4.pC(var2);
         } else if (var3 == this.kS) {
            var4.pC(var4.ys() + var2);
         } else {
            if (var3 != this.wS) {
               return -1;
            }

            if (!var4.sY().kS()) {
               return -1;
            }

            var4.pC(var4.sY().wS() + var2);
         }

         return var4.ys();
      }
   }

   private int kS(String var1, int var2) {
      Long var3 = this.oT.wS.getTypeLibraryService().findFirstIntegerConstantByName(var1);
      return var3 == null ? var2 : var3.intValue();
   }

   private cel.Av pC(int var1, String var2, cel.K var3) {
      int var4 = this.A();
      cel.Av var5 = new cel.Av(var4, var1, var2, var3);
      this.vP.put(var4, var5);
      return var5;
   }

   private int[] kS() {
      int var1 = this.WR++;
      cel.K var2 = new cel.K(ckx.pC(new byte[]{108, 31, 25, 9, 23, 26, 72}, 2, 109) + var1, 1);
      cel.Av var3 = this.pC(1, null, var2);
      cel.Av var4 = this.pC(2, null, var2);
      return new int[]{var3.pC, var4.pC};
   }

   public int pC(int[] var1) {
      return this.pC(var1, 0);
   }

   public int pC(int[] var1, int var2) {
      if ((var2 & ~(this.E | this.sY)) != 0) {
         return -1;
      } else {
         int[] var3 = this.kS();
         var1[0] = var3[0];
         var1[1] = var3[1];
         return 0;
      }
   }

   public int UT(int var1) {
      return this.pC(var1, null);
   }

   public int pC(int var1, Integer var2) {
      return this.pC(var1, var2, null);
   }

   public int pC(int var1, Integer var2, Integer var3) {
      cel.Av var4 = (cel.Av)this.vP.get(var1);
      if (var4 == null) {
         return -1;
      } else if (var2 == null || var2 != var1) {
         int var5 = var3 == null ? 0 : var3;
         if ((var5 & ~this.sY) != 0) {
            return -1;
         } else {
            if (var2 == null) {
               var2 = this.A();
            } else if (this.vP.containsKey(var2)) {
               this.A(var2);
            }

            cel.Av var6 = new cel.Av(var2, var4.A, var4.kS);
            this.vP.put(var2, var6);
            return var2;
         }
      } else {
         return var3 == null ? var2 : -1;
      }
   }

   public String wS(String var1) {
      if (var1.equals(ED)) {
         return ckx.pC(new byte[]{108, 28, 9, 10, 6, 12, 10, 71, 22, 73, 70, 76, 72, 80, 65, 102, 73, 65, 67, 67, 87, 67, 65, 3, 24}, 2, 149);
      } else {
         if (var1.startsWith(Sc)) {
            try {
               int var2 = Integer.parseInt(var1.substring(Sc.length()));
               cel.Av var3 = (cel.Av)this.vP.get(var2);
               if (var3 != null) {
                  return var3.E();
               }
            } catch (Exception var4) {
            }
         }

         return null;
      }
   }

   public static class Av {
      int pC;
      int A;
      cel.Sv kS;

      public Av(int var1, int var2, String var3, cel.K var4) {
         this.pC = var1;
         this.A = var2;
         this.kS = new cel.Sv(var3, var4);
      }

      public Av(int var1, int var2, cel.Sv var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }

      public boolean pC() {
         return (this.A & 1) != 0;
      }

      public boolean A() {
         return (this.A & 2) != 0;
      }

      public boolean kS() {
         return (this.A & 4) != 0;
      }

      public boolean wS() {
         return this.kS.pC();
      }

      public boolean UT() {
         return this.kS.A();
      }

      public String E() {
         return this.kS.pC;
      }

      public cel.K sY() {
         return this.kS.kS;
      }

      public int ys() {
         return this.kS.A;
      }

      public void pC(int var1) {
         this.kS.A = var1;
      }

      public int ld() {
         if (this.kS.pC()) {
            if (this.kS.kS.UT != null) {
               return this.kS.kS.UT.limit() - this.kS.A;
            }
         } else if (this.kS.A() && this.kS.kS.E != null) {
            return this.kS.kS.E.available();
         }

         return 0;
      }

      @Override
      public String toString() {
         return Strings.ff(
            ckx.pC(new byte[]{122, 2, 100, 70, 9, 29, 72, 26, 5, 86, 83, 8, 78, 10, 13, 6, 20, 78, 13, 72, 93, 125, 113}, 1, 60), this.kS, this.A
         );
      }
   }

   public static class K {
      String pC;
      int A;
      int kS;
      int wS;
      ByteBuffer UT;
      BytePipe E;

      K(String var1, int var2) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else if (var2 != 0 && var2 != 1) {
            throw new IllegalArgumentException();
         } else {
            this.pC = var1;
            this.A = var2;
            if (var2 == 1) {
               this.E = new BytePipe();
            }
         }
      }

      public boolean pC() {
         return this.A == 0;
      }

      public boolean A() {
         return this.A == 1;
      }

      public void pC(byte[] var1) {
         if (var1 == null) {
            throw new IllegalArgumentException();
         } else {
            this.UT = ByteBuffer.wrap(var1);
         }
      }

      public boolean kS() {
         return this.pC()
            && !this.pC.startsWith(ckx.pC(new byte[]{108, 31, 2, 22, 17, 70}, 2, 116))
            && !this.pC.startsWith(ckx.pC(new byte[]{108, 11, 21, 15, 93}, 2, 233));
      }

      public int wS() {
         return this.UT != null && this.kS() ? this.UT.limit() : 0;
      }

      public int UT() {
         if (this.UT != null) {
            return this.UT.limit();
         } else {
            return this.E != null ? this.E.available() : 0;
         }
      }

      @Override
      public String toString() {
         return this.pC;
      }
   }

   public static class Sv {
      String pC;
      int A;
      cel.K kS;

      public Sv(String var1, cel.K var2) {
         if (var2 == null) {
            throw new IllegalArgumentException();
         } else {
            this.pC = var1;
            this.kS = var2;
         }
      }

      public boolean pC() {
         return this.kS.pC();
      }

      public boolean A() {
         return this.kS.A();
      }

      public int kS() {
         if (this.kS.pC()) {
            return this.kS.UT.limit() - this.A;
         } else {
            return this.kS.A() ? this.kS.E.available() : 0;
         }
      }

      @Override
      public String toString() {
         return Strings.ff(ckx.pC(new byte[]{63, 86, 51, 112, 72, 93, 125}, 1, 26), this.kS, this.A);
      }
   }
}
