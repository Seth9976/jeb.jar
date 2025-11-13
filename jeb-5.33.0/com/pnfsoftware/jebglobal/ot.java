package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.UnitAddress;
import com.pnfsoftware.jeb.core.units.UnitUtil;
import com.pnfsoftware.jeb.core.units.code.ICodeUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.util.collect.SegmentMap;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ot {
   private ia pC;
   private Map A = new HashMap();
   private Map kS = new HashMap();
   private SegmentMap wS = new SegmentMap();

   public ot(ia var1) {
      this.pC = var1;
      this.wS.setRemoveSegmentsOnOverlap(true);
   }

   public void pC() {
      this.A.clear();
      this.kS.clear();
      this.wS.clear();
      this.A();
   }

   private void A() {
      for (INativeCodeUnit var2 : this.pC.getPotentialDebuggees()) {
         var2.setPhysicalImageBase(0L);
      }
   }

   void pC(boolean var1) {
      if (this.A.isEmpty() || var1) {
         if (this.pC.pC(true, true)) {
            this.A.clear();
            this.kS.clear();
            this.wS.clear();
            this.A();
            int var2 = 0;

            for (INativeCodeUnit var4 : this.pC.getPotentialDebuggees()) {
               if (var4.getParent() instanceof com.pnfsoftware.jeb.corei.parsers.elf.sy) {
                  com.pnfsoftware.jeb.corei.parsers.elf.sy var5 = (com.pnfsoftware.jeb.corei.parsers.elf.sy)var4.getParent();
                  String var6 = var5.getName();
                  long var7 = var4.getVirtualImageBase();
                  this.A.put(var6, var5);
                  if (var2 == 0 && this.pC.pC != null) {
                     var7 = this.pC.pC;
                  }

                  this.kS.put(var6, var7);
                  this.wS.add(new rJ(var7, var5, var6));
               }

               var2++;
            }

            if (this.pC.oT() != null) {
               for (IUnit var14 : this.pC.oT().getChildren()) {
                  if (var14 instanceof com.pnfsoftware.jeb.corei.parsers.elf.sy var17) {
                     String var20 = var17.getName();
                     long var23 = var17.getLoaderInformation().getImageBase();
                     this.A.put(var20, var17);
                     this.kS.put(var20, var23);
                     this.wS.add(new rJ(var23, var17, var20));
                  }
               }
            }

            List var13 = this.pC.A().mv();
            if (var13 != null) {
               for (JE var18 : var13) {
                  String var21 = var18.pC();

                  for (com.pnfsoftware.jeb.corei.parsers.elf.sy var8 : this.A.values()) {
                     String var9 = var8.getName();
                     if (this.pC(var9, var21)) {
                        long var10;
                        if (var18.kS() != 0L) {
                           var10 = var18.kS() - var8.A();
                        } else {
                           var10 = var18.A();
                        }

                        this.kS.put(var9, var10);
                        this.wS.add(new rJ(var10, var8, var21));
                        break;
                     }
                  }
               }
            }

            for (INativeCodeUnit var19 : this.pC.getPotentialDebuggees()) {
               Long var22 = this.pC(var19);
               if (var22 != null && var22 != var19.getVirtualImageBase()) {
                  var19.setPhysicalImageBase(var22);
               }
            }
         }
      }
   }

   public long pC(com.pnfsoftware.jeb.corei.parsers.elf.sy var1) {
      List var2 = this.pC.A().mv();
      if (var2 == null) {
         return 0L;
      } else {
         String var3 = var1.getName();

         for (JE var5 : var2) {
            String var6 = var5.pC();
            if (this.pC(var3, var6)) {
               long var7 = var5.A();
               if (var5.kS() != 0L) {
                  var7 = var5.kS() - var1.A();
               }

               return var7;
            }
         }

         return 0L;
      }
   }

   private boolean pC(String var1, String var2) {
      return var2 != null && (var2.equals(var1) || var2.endsWith("/" + var1));
   }

   UnitAddress pC(long var1) {
      this.pC(false);
      rJ var3 = (rJ)this.wS.getSegmentContaining(var1);
      if (var3 == null) {
         return new UnitAddress(null, Strings.ff("%Xh", var1));
      } else {
         ICodeObjectUnit var4 = var3.kS();
         ICodeUnit var5 = (ICodeUnit)UnitUtil.findFirstChildByType(var4, ICodeUnit.class, false);
         if (var5 == null) {
            return null;
         } else {
            long var6 = var4.getLoaderInformation().getImageBase();
            if (var5 instanceof INativeCodeUnit) {
               var6 = ((INativeCodeUnit)var5).getVirtualImageBase();
            }

            long var8 = var6 + (var1 - var3.pC());
            String var10 = Strings.ff("%Xh", var8);
            return new UnitAddress(var5, var10);
         }
      }
   }

   long pC(ICodeUnit var1, String var2) {
      if (var1 == null) {
         return Conversion.stringToLong(var2, -1L);
      } else {
         this.pC(false);
         if (!(var1.getParent() instanceof ICodeObjectUnit)) {
            return -1L;
         } else {
            long var3 = Conversion.stringToLong(var2, -1L);
            if (var3 == -1L) {
               return -1L;
            } else {
               ICodeObjectUnit var5 = (ICodeObjectUnit)var1.getParent();
               long var6 = var5.getLoaderInformation().getImageBase();
               if (var1 instanceof INativeCodeUnit) {
                  var6 = ((INativeCodeUnit)var1).getVirtualImageBase();
               }

               String var8 = var5.getName();
               Long var9 = (Long)this.kS.get(var8);
               if (var9 == null) {
                  var9 = var6;
               }

               return var9 + (var3 - var6);
            }
         }
      }
   }

   Long pC(ICodeUnit var1) {
      ICodeObjectUnit var2 = (ICodeObjectUnit)var1.getParent();
      String var3 = var2.getName();
      return (Long)this.kS.get(var3);
   }

   com.pnfsoftware.jeb.corei.parsers.elf.sy pC(String var1) {
      return (com.pnfsoftware.jeb.corei.parsers.elf.sy)this.A.get(var1);
   }
}
