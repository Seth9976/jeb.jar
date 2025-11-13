package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ELF;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStringTable;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Longs;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class cee {
   private static final ILogger pC = GlobalLog.getLogger(cee.class);
   private IVirtualMemory A;
   private Map kS = new TreeMap();
   private Map wS = new HashMap();

   public cee(IVirtualMemory var1) {
      this.A = var1;
   }

   public cee.Sv pC(String var1) {
      for (cee.Sv var3 : this.kS.values()) {
         String var4 = var3.kS();
         if (var4 != null && var4.equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public cee.Sv pC(long var1, boolean var3) {
      cee.Sv var4 = (cee.Sv)this.kS.get(var1);
      if (var4 != null) {
         return var4;
      } else if (!var3) {
         return null;
      } else {
         try {
            var4 = this.A(var1);
         } catch (Exception var6) {
            pC.catching(var6);
            return null;
         }

         if (var4 == null) {
            return null;
         } else {
            this.kS.put(var1, var4);
            return var4;
         }
      }
   }

   private cee.Sv A(long var1) throws Exception {
      if (!this.A.isAllocatedPage(var1)) {
         return null;
      } else {
         int var5 = this.A.readInt(var1);
         long var3 = var1 + 4L;
         if (var5 != ELF.ElfMagicIntLE) {
            return null;
         } else {
            int var6 = this.A.readByte(var3) & 255;
            var3++;
            if (var6 != 2) {
               return null;
            } else {
               int var7 = this.A.readByte(var3) & 255;
               var3++;
               if (var7 != 1) {
                  return null;
               } else {
                  int var8 = this.A.readByte(var3) & 255;
                  var3++;
                  if (var8 != 1) {
                     return null;
                  } else {
                     this.A.readByte(var3);
                     this.A.readByte(++var3);
                     var3 = ++var3 + 7L;
                     int var9 = this.A.readShort(var3) & '\uffff';
                     var3 += 2L;
                     if (var9 != 3) {
                        return null;
                     } else {
                        int var10 = this.A.readShort(var3) & '\uffff';
                        var3 += 2L;
                        int var11 = this.A.readInt(var3);
                        var3 += 4L;
                        if (var11 != 1) {
                           return null;
                        } else {
                           this.A.readLong(var3);
                           var3 += 8L;
                           long var12 = this.A.readLong(var3);
                           var3 += 8L;
                           this.A.readLong(var3);
                           var3 += 8L;
                           this.A.readInt(var3);
                           var3 += 4L;
                           this.A.readShort(var3);
                           var3 += 2L;
                           int var14 = this.A.readShort(var3) & '\uffff';
                           var3 += 2L;
                           int var15 = this.A.readShort(var3) & '\uffff';
                           var3 += 2L;
                           this.A.readShort(var3);
                           var3 += 2L;
                           this.A.readShort(var3);
                           var3 += 2L;
                           this.A.readShort(var3);
                           var3 = var1 + var12;
                           long var16 = Long.MAX_VALUE;
                           long var18 = 0L;
                           long var20 = 0L;
                           long var22 = 0L;

                           for (int var24 = 0; var24 < var15; var24++) {
                              int var25 = this.A.readInt(var3);
                              if (var25 == 1) {
                                 long var26 = this.A.readLong(var3 + 16L);
                                 long var28 = this.A.readLong(var3 + 40L);
                                 if (var28 > 0L) {
                                    if (Longs.compareUnsigned(var26, var16) < 0) {
                                       var16 = var26;
                                    }

                                    long var30 = var26 + var28;
                                    if (Longs.compareUnsigned(var30, var18) > 0) {
                                       var18 = var30;
                                    }
                                 }
                              } else if (var25 == 2) {
                                 Assert.a(var20 == 0L);
                                 var20 = this.A.readLong(var3 + 16L);
                                 var22 = this.A.readLong(var3 + 40L);
                              }

                              var3 += var14;
                           }

                           if (var16 == Long.MAX_VALUE) {
                              return null;
                           } else if (var16 != 0L) {
                              return null;
                           } else {
                              Assert.a(var20 > 0L && var22 > 0L);
                              var3 = var1 + var20;
                              long var93 = var3 + var22;
                              LinkedHashMap var95 = new LinkedHashMap();
                              ArrayList var29 = new ArrayList();

                              while (var3 < var93) {
                                 long var96 = this.A.readLong(var3);
                                 var3 += 8L;
                                 long var32 = this.A.readLong(var3);
                                 var3 += 8L;
                                 if (var96 == 0L) {
                                    break;
                                 }

                                 if (var96 == 1L) {
                                    var29.add((int)var32);
                                 } else {
                                    var95.put((int)var96, var32);
                                 }
                              }

                              long var97 = (Long)var95.get(5);
                              int var98 = ((Long)var95.get(10)).intValue();
                              byte[] var33 = new byte[var98];
                              var3 = var1 + var97;
                              VirtualMemoryUtil.readBytes(this.A, var3, var33, 0, var33.length);
                              ELFStringTable var34 = new ELFStringTable(var33);
                              String var35 = null;
                              Long var36 = (Long)var95.get(14);
                              if (var36 != null) {
                                 int var37 = var36.intValue();
                                 var35 = var34.getString(var37);
                              }

                              ArrayList var105 = new ArrayList();

                              for (int var39 : var29) {
                                 var105.add(var34.getString(var39));
                              }

                              int var106 = 0;
                              var36 = (Long)var95.get(4);
                              if (var36 != null) {
                                 long var107 = var36;
                                 var3 = var1 + var107;
                                 var106 = this.A.readInt(var3 + 4L);
                              } else {
                                 var36 = (Long)var95.get(1879047925);
                                 if (var36 != null) {
                                    long var108 = var36;
                                    var3 = var1 + var108;
                                    cee.Ws var41 = cee.Ws.pC(this.A, var3);
                                    var106 = var41.pC();
                                    if (var106 < var41.wS) {
                                       var106 = var41.wS;
                                    }
                                 }
                              }

                              Assert.a(var106 >= 0);
                              long var109 = 0L;
                              var36 = (Long)var95.get(3);
                              if (var36 != null) {
                                 var109 = var36;
                              }

                              long var110 = (Long)var95.get(6);
                              int var43 = ((Long)var95.get(11)).intValue();
                              LinkedHashMap var44 = new LinkedHashMap();
                              HashMap var45 = new HashMap();
                              ArrayList var46 = new ArrayList();
                              Assert.a(var43 >= 24);
                              var3 = var1 + var110;

                              for (int var47 = 0; var47 < var106; var47++) {
                                 cee.K var48 = new cee.K();
                                 var48.pC = this.A.readInt(var3);
                                 var48.wS = this.A.readInt(var3 + 4L) & 0xFF;
                                 var48.E = this.A.readInt(var3 + 5L) & 0xFF;
                                 var48.UT = this.A.readShort(var3 + 6L) & '\uffff';
                                 var48.A = this.A.readLong(var3 + 8L);
                                 var48.kS = this.A.readLong(var3 + 16L);
                                 var3 += var43;
                                 var48.sY = var34.getString(var48.pC);
                                 var46.add(var48);
                                 int var49 = var48.UT();
                                 if (var49 == 2 || var49 == 10 || var49 == 1) {
                                    cee.K var50 = (cee.K)var44.put(var48.sY, var48);
                                    if (var50 != null) {
                                       Object[] var10000 = new Object[]{var48.sY};
                                    }

                                    var45.put(var1 + var48.A, var48);
                                 }
                              }

                              ArrayList var111 = new ArrayList();
                              var36 = (Long)var95.get(12);
                              if (var36 != null) {
                                 long var112 = var36;
                                 if (var112 != 0L && var112 != -1L) {
                                    var111.add(var1 + var112);
                                 }
                              }

                              var36 = (Long)var95.get(25);
                              if (var36 != null) {
                                 long var113 = var36;
                                 int var116 = ((Long)var95.get(27)).intValue();
                                 var3 = var1 + var113;
                                 var93 = var3 + var116;

                                 while (var3 < var93) {
                                    long var51 = this.A.readLong(var3);
                                    var3 += 8L;
                                    var111.add(var51);
                                 }
                              }

                              TreeMap var114 = new TreeMap();
                              var36 = (Long)var95.get(23);
                              if (var36 != null) {
                                 long var115 = var36;
                                 int var117 = ((Long)var95.get(2)).intValue();
                                 int var52 = ((Long)var95.get(20)).intValue();
                                 if (var52 != 7) {
                                    return null;
                                 }

                                 byte var53 = 24;
                                 short var54;
                                 if (var10 == 183) {
                                    var54 = 1026;
                                 } else {
                                    if (var10 != 62) {
                                       return null;
                                    }

                                    var54 = 7;
                                 }

                                 var3 = var1 + var115;
                                 int var55 = var117 / var53;

                                 for (int var56 = 0; var56 < var55; var56++) {
                                    long var57 = this.A.readLong(var3);
                                    long var59 = this.A.readLong(var3 + 8L);
                                    var3 += var53;
                                    long var61 = var1 + var57;
                                    int var63 = (int)(var59 >>> 32);
                                    int var64 = (int)var59;
                                    if (var64 != var54 || var63 < 0 || var63 >= var46.size()) {
                                       break;
                                    }

                                    cee.K var65 = (cee.K)var46.get(var63);
                                    var114.put(var61, var65);
                                 }
                              }

                              this.wS.putAll(var45);
                              return new cee.Sv(var1, var20, var109, var35, var105, var44, var45, var114, var111);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public long A(String var1) {
      for (cee.Sv var3 : this.kS.values()) {
         long var4 = var3.pC(var1);
         if (var4 != 0L) {
            return var4;
         }
      }

      return 0L;
   }

   public cee.Av kS(String var1) {
      for (cee.Sv var3 : this.kS.values()) {
         cee.Av var4 = var3.A(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   public cee.Av pC(long var1) {
      cee.K var3 = (cee.K)this.wS.get(var1);
      if (var3 == null) {
         return null;
      } else {
         long var4 = var1 - var3.A;
         cee.Sv var6 = (cee.Sv)this.kS.get(var4);
         if (var6 == null) {
            Assert.debugFail("Missing ELF container reference?");
         }

         return new cee.Av(var6, var3);
      }
   }

   public static class Av {
      cee.Sv pC;
      cee.K A;

      public Av(cee.Sv var1, cee.K var2) {
         this.pC = var1;
         this.A = var2;
      }

      public long pC() {
         return this.pC.pC() + this.A.A();
      }

      public String A() {
         return this.A.pC();
      }

      public boolean kS() {
         return this.A.UT() == 2;
      }

      public boolean wS() {
         return this.A.UT() == 10;
      }
   }

   public static class K {
      int pC;
      long A;
      long kS;
      int wS;
      int UT;
      int E;
      String sY;

      public String pC() {
         return this.sY;
      }

      public long A() {
         return this.A;
      }

      public long kS() {
         return this.kS;
      }

      public int wS() {
         return this.wS >> 4 & 15;
      }

      public int UT() {
         return this.wS & 15;
      }

      public int E() {
         return this.E & 3;
      }

      public int sY() {
         return this.UT;
      }

      public boolean ys() {
         return this.A() != 0L && this.kS() != 0L && (this.wS() == 1 || this.wS() == 2);
      }

      @Override
      public String toString() {
         return Strings.ff(
            "%s @ +0x%X (s=%d, b=%s, t=%s, v=%s, S=%s)",
            this.pC(),
            this.A(),
            this.kS(),
            ELF.getSTBString(this.wS()),
            ELF.getSTTString(this.UT()),
            ELF.getSTVString(this.E()),
            ELF.getSHNString(this.sY())
         );
      }
   }

   public static class Sv {
      long pC;
      long A;
      long kS;
      String wS;
      List UT;
      Map E;
      Map sY;
      Map ys;
      List ld;

      public Sv(long var1, long var3, long var5, String var7, List var8, Map var9, Map var10, Map var11, List var12) {
         this.pC = var1;
         this.A = var3;
         this.kS = var5;
         this.wS = var7;
         this.UT = var8;
         this.E = var9;
         this.sY = var10;
         this.ys = var11;
         this.ld = var12;
      }

      public long pC() {
         return this.pC;
      }

      public long A() {
         return this.kS == 0L ? 0L : this.pC + this.kS;
      }

      public String kS() {
         return this.wS;
      }

      public long pC(String var1) {
         cee.K var2 = (cee.K)this.E.get(var1);
         return var2 != null && var2.ys() && var2.UT() == 2 ? this.pC + var2.A() : 0L;
      }

      public cee.Av A(String var1) {
         cee.K var2 = (cee.K)this.E.get(var1);
         if (var2 != null && var2.ys()) {
            int var3 = var2.UT();
            Assert.a(var3 == 2 || var3 == 10 || var3 == 1);
            return new cee.Av(this, var2);
         } else {
            return null;
         }
      }

      public cee.K pC(long var1) {
         return (cee.K)this.ys.get(var1);
      }

      public List wS() {
         return Collections.unmodifiableList(this.ld);
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X: %s (%d symbols)", this.pC, Strings.safe(this.wS, "(no name)"), this.E.size());
      }
   }

   static class Ws {
      private IVirtualMemory pC;
      private long A;
      private int kS;
      private int wS;
      private int UT;
      private int E;

      public static cee.Ws pC(IVirtualMemory var0, long var1) throws MemoryException {
         cee.Ws var3 = new cee.Ws();
         var3.pC = var0;
         var3.A = var1;
         var3.kS = var0.readInt(var1);
         var3.wS = var0.readInt(var1 + 4L);
         var3.UT = var0.readInt(var1 + 8L);
         var3.E = var0.readInt(var1 + 12L);
         return var3;
      }

      public int pC() throws IOException {
         long var1 = this.A + 16L + this.UT * 8;
         long var3 = var1;
         int var5 = 0;

         for (int var6 = 0; var6 < this.kS; var6++) {
            int var7 = this.pC.readInt(var3);
            var3 += 4L;
            if (var7 > var5) {
               var5 = var7;
            }
         }

         int var13 = var5 - this.wS;
         long var14 = var1 + this.kS * 4;
         var3 = var14 + var13 * 4;
         int var9 = this.pC.readInt(var3);

         for (long var12 = var3 + 4L; (var9 & 1) == 0; var13++) {
            var9 = this.pC.readInt(var12);
            var12 += 4L;
         }

         return var13 + 1 + this.wS;
      }
   }
}
