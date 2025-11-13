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
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class cjq {
   private static final ILogger q = GlobalLog.getLogger(cjq.class);
   private IVirtualMemory RF;
   private Map xK = new TreeMap();

   public cjq(IVirtualMemory var1) {
      this.RF = var1;
   }

   public IVirtualMemory q() {
      return this.RF;
   }

   public Collection RF() {
      return Collections.unmodifiableCollection(this.xK.values());
   }

   public void xK() {
      this.xK.clear();
   }

   public int q(boolean var1) {
      if (var1) {
         this.xK();
      }

      for (long var3 : this.RF.getAllocatedPageBases()) {
         cjq.CU var5 = this.q(var3, true);
         if (var5 != null) {
            Object[] var10000 = new Object[]{var5};
         }
      }

      return this.xK.size();
   }

   public cjq.CU q(String var1) {
      for (cjq.CU var3 : this.xK.values()) {
         String var4 = var3.Dw();
         if (var4 != null && var4.equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public cjq.CU q(long var1, boolean var3) {
      cjq.CU var4 = (cjq.CU)this.xK.get(var1);
      if (var4 != null) {
         return var4;
      } else if (!var3) {
         return null;
      } else {
         try {
            var4 = this.q(var1);
         } catch (Exception var6) {
            q.catching(var6);
            return null;
         }

         if (var4 == null) {
            return null;
         } else {
            this.xK.put(var1, var4);
            return var4;
         }
      }
   }

   private cjq.CU q(long var1) throws Exception {
      if (!this.RF.isAllocatedPage(var1)) {
         return null;
      } else {
         int var5 = this.RF.readInt(var1);
         long var3 = var1 + 4L;
         if (var5 != ELF.ElfMagicIntLE) {
            return null;
         } else {
            int var6 = this.RF.readByte(var3) & 255;
            var3++;
            if (var6 != 2) {
               return null;
            } else {
               int var7 = this.RF.readByte(var3) & 255;
               var3++;
               if (var7 != 1) {
                  return null;
               } else {
                  int var8 = this.RF.readByte(var3) & 255;
                  var3++;
                  if (var8 != 1) {
                     return null;
                  } else {
                     this.RF.readByte(var3);
                     this.RF.readByte(++var3);
                     var3 = ++var3 + 7L;
                     int var9 = this.RF.readShort(var3) & '\uffff';
                     var3 += 2L;
                     if (var9 != 3) {
                        return null;
                     } else {
                        int var10 = this.RF.readShort(var3) & '\uffff';
                        var3 += 2L;
                        int var11 = this.RF.readInt(var3);
                        var3 += 4L;
                        if (var11 != 1) {
                           return null;
                        } else {
                           this.RF.readLong(var3);
                           var3 += 8L;
                           long var12 = this.RF.readLong(var3);
                           var3 += 8L;
                           this.RF.readLong(var3);
                           var3 += 8L;
                           this.RF.readInt(var3);
                           var3 += 4L;
                           this.RF.readShort(var3);
                           var3 += 2L;
                           int var14 = this.RF.readShort(var3) & '\uffff';
                           var3 += 2L;
                           int var15 = this.RF.readShort(var3) & '\uffff';
                           var3 += 2L;
                           this.RF.readShort(var3);
                           var3 += 2L;
                           this.RF.readShort(var3);
                           var3 += 2L;
                           this.RF.readShort(var3);
                           var3 = var1 + var12;
                           long var16 = Long.MAX_VALUE;
                           long var18 = 0L;
                           long var20 = 0L;
                           long var22 = 0L;

                           for (int var24 = 0; var24 < var15; var24++) {
                              int var25 = this.RF.readInt(var3);
                              if (var25 == 1) {
                                 long var26 = this.RF.readLong(var3 + 16L);
                                 long var28 = this.RF.readLong(var3 + 40L);
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
                                 var20 = this.RF.readLong(var3 + 16L);
                                 var22 = this.RF.readLong(var3 + 40L);
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
                              long var92 = var3 + var22;
                              LinkedHashMap var94 = new LinkedHashMap();
                              ArrayList var29 = new ArrayList();

                              while (var3 < var92) {
                                 long var95 = this.RF.readLong(var3);
                                 var3 += 8L;
                                 long var32 = this.RF.readLong(var3);
                                 var3 += 8L;
                                 if (var95 == 0L) {
                                    break;
                                 }

                                 if (var95 == 1L) {
                                    var29.add((int)var32);
                                 } else {
                                    var94.put((int)var95, var32);
                                 }
                              }

                              long var96 = (Long)var94.get(5);
                              int var97 = ((Long)var94.get(10)).intValue();
                              byte[] var33 = new byte[var97];
                              var3 = var1 + var96;
                              VirtualMemoryUtil.readBytes(this.RF, var3, var33, 0, var33.length);
                              ELFStringTable var34 = new ELFStringTable(var33);
                              String var35 = null;
                              Long var36 = (Long)var94.get(14);
                              if (var36 != null) {
                                 int var37 = var36.intValue();
                                 var35 = var34.getString(var37);
                              }

                              ArrayList var104 = new ArrayList();

                              for (int var39 : var29) {
                                 var104.add(var34.getString(var39));
                              }

                              int var105 = 0;
                              var36 = (Long)var94.get(4);
                              if (var36 != null) {
                                 long var106 = var36;
                                 var3 = var1 + var106;
                                 var105 = this.RF.readInt(var3 + 4L);
                              } else {
                                 var36 = (Long)var94.get(1879047925);
                                 if (var36 != null) {
                                    long var107 = var36;
                                    var3 = var1 + var107;
                                    cjq.ej var41 = cjq.ej.q(this.RF, var3);
                                    var105 = var41.q();
                                    if (var105 < var41.Dw) {
                                       var105 = var41.Dw;
                                    }
                                 }
                              }

                              Assert.a(var105 >= 0);
                              long var108 = 0L;
                              var36 = (Long)var94.get(3);
                              if (var36 != null) {
                                 var108 = var36;
                              }

                              long var109 = (Long)var94.get(6);
                              int var43 = ((Long)var94.get(11)).intValue();
                              LinkedHashMap var44 = new LinkedHashMap();
                              ArrayList var45 = new ArrayList();
                              Assert.a(var43 >= 24);
                              var3 = var1 + var109;

                              for (int var46 = 0; var46 < var105; var46++) {
                                 cjq.nI var47 = new cjq.nI();
                                 var47.q = this.RF.readInt(var3);
                                 var47.Dw = this.RF.readInt(var3 + 4L) & 0xFF;
                                 var47.oW = this.RF.readInt(var3 + 5L) & 0xFF;
                                 var47.Uv = this.RF.readShort(var3 + 6L) & '\uffff';
                                 var47.RF = this.RF.readLong(var3 + 8L);
                                 var47.xK = this.RF.readLong(var3 + 16L);
                                 var3 += var43;
                                 var47.gO = var34.getString(var47.q);
                                 var45.add(var47);
                                 int var48 = var47.Uv();
                                 if (var48 == 2 || var48 == 10 || var48 == 1) {
                                    cjq.nI var49 = (cjq.nI)var44.put(var47.gO, var47);
                                    if (var49 != null) {
                                       Object[] var10000 = new Object[]{var47.gO};
                                    }
                                 }
                              }

                              ArrayList var110 = new ArrayList();
                              var36 = (Long)var94.get(12);
                              if (var36 != null) {
                                 long var111 = var36;
                                 if (var111 != 0L && var111 != -1L) {
                                    var110.add(var1 + var111);
                                 }
                              }

                              var36 = (Long)var94.get(25);
                              if (var36 != null) {
                                 long var112 = var36;
                                 int var115 = ((Long)var94.get(27)).intValue();
                                 var3 = var1 + var112;
                                 var92 = var3 + var115;

                                 while (var3 < var92) {
                                    long var50 = this.RF.readLong(var3);
                                    var3 += 8L;
                                    var110.add(var50);
                                 }
                              }

                              TreeMap var113 = new TreeMap();
                              var36 = (Long)var94.get(23);
                              if (var36 != null) {
                                 long var114 = var36;
                                 int var116 = ((Long)var94.get(2)).intValue();
                                 int var51 = ((Long)var94.get(20)).intValue();
                                 if (var51 != 7) {
                                    return null;
                                 }

                                 byte var52 = 24;
                                 short var53;
                                 if (var10 == 183) {
                                    var53 = 1026;
                                 } else {
                                    if (var10 != 62) {
                                       return null;
                                    }

                                    var53 = 7;
                                 }

                                 var3 = var1 + var114;
                                 int var54 = var116 / var52;

                                 for (int var55 = 0; var55 < var54; var55++) {
                                    long var56 = this.RF.readLong(var3);
                                    long var58 = this.RF.readLong(var3 + 8L);
                                    var3 += var52;
                                    long var60 = var1 + var56;
                                    int var62 = (int)(var58 >>> 32);
                                    int var63 = (int)var58;
                                    if (var63 != var53 || var62 < 0 || var62 >= var45.size()) {
                                       break;
                                    }

                                    cjq.nI var64 = (cjq.nI)var45.get(var62);
                                    var113.put(var60, var64);
                                 }
                              }

                              return new cjq.CU(var1, var20, var108, var35, var104, var44, var113, var110);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public long RF(String var1) {
      for (cjq.CU var3 : this.xK.values()) {
         long var4 = var3.q(var1);
         if (var4 != 0L) {
            return var4;
         }
      }

      return 0L;
   }

   public long q(long var1, String var3) {
      cjq.CU var4 = this.q(var1, true);
      return var4 == null ? 0L : var4.q(var3);
   }

   public cjq.eo xK(String var1) {
      for (cjq.CU var3 : this.xK.values()) {
         cjq.eo var4 = var3.RF(var1);
         if (var4 != null) {
            return var4;
         }
      }

      return null;
   }

   public cjq.eo RF(long var1, String var3) {
      cjq.CU var4 = this.q(var1, true);
      return var4 == null ? null : var4.RF(var3);
   }

   public static class CU {
      long q;
      long RF;
      long xK;
      String Dw;
      List Uv;
      Map oW;
      Map gO;
      List nf;

      public CU(long var1, long var3, long var5, String var7, List var8, Map var9, Map var10, List var11) {
         this.q = var1;
         this.RF = var3;
         this.xK = var5;
         this.Dw = var7;
         this.Uv = var8;
         this.oW = var9;
         this.gO = var10;
         this.nf = var11;
      }

      public long q() {
         return this.q;
      }

      public long RF() {
         return this.RF == 0L ? 0L : this.q + this.RF;
      }

      public long xK() {
         return this.xK == 0L ? 0L : this.q + this.xK;
      }

      public String Dw() {
         return this.Dw;
      }

      public List Uv() {
         return this.Uv;
      }

      public Collection oW() {
         return Collections.unmodifiableCollection(this.oW.values());
      }

      public long q(String var1) {
         cjq.nI var2 = (cjq.nI)this.oW.get(var1);
         return var2 != null && var2.nf() && var2.Uv() == 2 ? this.q + var2.RF() : 0L;
      }

      public cjq.eo RF(String var1) {
         cjq.nI var2 = (cjq.nI)this.oW.get(var1);
         if (var2 != null && var2.nf()) {
            int var3 = var2.Uv();
            Assert.a(var3 == 2 || var3 == 10 || var3 == 1);
            return new cjq.eo(this, var2);
         } else {
            return null;
         }
      }

      public cjq.nI q(long var1) {
         return (cjq.nI)this.gO.get(var1);
      }

      public List gO() {
         return Collections.unmodifiableList(this.nf);
      }

      @Override
      public String toString() {
         return Strings.ff("0x%X: %s (%d symbols)", this.q, Strings.safe(this.Dw, "(no name)"), this.oW.size());
      }

      public String nf() {
         StringBuilder var1 = new StringBuilder(this.toString());
         var1.append("\n");
         Strings.ff(var1, "Depends on: %s\n", this.Uv);
         Strings.ff(var1, "%d symbols:\n", this.oW.size());
         int var2 = 0;

         for (cjq.nI var4 : this.oW.values()) {
            Strings.ff(var1, "%d: %s\n", var2, var4);
            var2++;
         }

         if (this.gO != null) {
            Strings.ff(var1, "Dynamic relocations in .plt.got (lazy imports):\n");

            for (Entry var6 : this.gO.entrySet()) {
               Strings.ff(var1, "0x%X => %s\n", var6.getKey(), var6.getValue());
            }
         }

         return var1.toString();
      }
   }

   static class ej {
      private IVirtualMemory q;
      private long RF;
      private int xK;
      private int Dw;
      private int Uv;
      private int oW;

      public static cjq.ej q(IVirtualMemory var0, long var1) throws MemoryException {
         cjq.ej var3 = new cjq.ej();
         var3.q = var0;
         var3.RF = var1;
         var3.xK = var0.readInt(var1);
         var3.Dw = var0.readInt(var1 + 4L);
         var3.Uv = var0.readInt(var1 + 8L);
         var3.oW = var0.readInt(var1 + 12L);
         return var3;
      }

      public int q() throws IOException {
         long var1 = this.RF + 16L + this.Uv * 8;
         long var3 = var1;
         int var5 = 0;

         for (int var6 = 0; var6 < this.xK; var6++) {
            int var7 = this.q.readInt(var3);
            var3 += 4L;
            if (var7 > var5) {
               var5 = var7;
            }
         }

         int var13 = var5 - this.Dw;
         long var14 = var1 + this.xK * 4;
         var3 = var14 + var13 * 4;
         int var9 = this.q.readInt(var3);

         for (long var12 = var3 + 4L; (var9 & 1) == 0; var13++) {
            var9 = this.q.readInt(var12);
            var12 += 4L;
         }

         return var13 + 1 + this.Dw;
      }
   }

   public static class eo {
      cjq.CU q;
      cjq.nI RF;

      public eo(cjq.CU var1, cjq.nI var2) {
         this.q = var1;
         this.RF = var2;
      }

      public long q() {
         return this.q.q() + this.RF.RF();
      }

      public String RF() {
         return this.RF.q();
      }

      public boolean xK() {
         return this.RF.Uv() == 1;
      }

      public boolean Dw() {
         return this.RF.Uv() == 2 || this.RF.Uv() == 10;
      }

      public boolean Uv() {
         return this.RF.Uv() == 2;
      }

      public boolean oW() {
         return this.RF.Uv() == 10;
      }

      cjq.CU gO() {
         return this.q;
      }

      cjq.nI nf() {
         return this.RF;
      }
   }

   public static class nI {
      int q;
      long RF;
      long xK;
      int Dw;
      int Uv;
      int oW;
      String gO;

      public String q() {
         return this.gO;
      }

      public long RF() {
         return this.RF;
      }

      public long xK() {
         return this.xK;
      }

      public int Dw() {
         return this.Dw >> 4 & 15;
      }

      public int Uv() {
         return this.Dw & 15;
      }

      public int oW() {
         return this.oW & 3;
      }

      public int gO() {
         return this.Uv;
      }

      public boolean nf() {
         return this.RF() != 0L && this.xK() != 0L && (this.Dw() == 1 || this.Dw() == 2);
      }

      @Override
      public String toString() {
         return Strings.ff(
            "%s @ +0x%X (s=%d, b=%s, t=%s, v=%s, S=%s)",
            this.q(),
            this.RF(),
            this.xK(),
            ELF.getSTBString(this.Dw()),
            ELF.getSTTString(this.Uv()),
            ELF.getSTVString(this.oW()),
            ELF.getSHNString(this.gO())
         );
      }
   }
}
