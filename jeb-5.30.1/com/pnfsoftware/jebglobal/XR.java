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

public class XR {
   private um q;
   private Map RF = new HashMap();
   private Map xK = new HashMap();
   private SegmentMap Dw = new SegmentMap();

   public XR(um var1) {
      this.q = var1;
      this.Dw.setRemoveSegmentsOnOverlap(true);
   }

   public void q() {
      this.RF.clear();
      this.xK.clear();
      this.Dw.clear();
      this.RF();
   }

   private void RF() {
      for (INativeCodeUnit var2 : this.q.getPotentialDebuggees()) {
         var2.setPhysicalImageBase(0L);
      }
   }

   void q(boolean var1) {
      if (this.RF.isEmpty() || var1) {
         if (this.q.q(true, true)) {
            this.RF.clear();
            this.xK.clear();
            this.Dw.clear();
            this.RF();
            int var2 = 0;

            for (INativeCodeUnit var4 : this.q.getPotentialDebuggees()) {
               if (var4.getParent() instanceof com.pnfsoftware.jeb.corei.parsers.elf.vb) {
                  com.pnfsoftware.jeb.corei.parsers.elf.vb var5 = (com.pnfsoftware.jeb.corei.parsers.elf.vb)var4.getParent();
                  String var6 = var5.getName();
                  long var7 = var4.getVirtualImageBase();
                  this.RF.put(var6, var5);
                  if (var2 == 0 && this.q.q != null) {
                     var7 = this.q.q;
                  }

                  this.xK.put(var6, var7);
                  this.Dw.add(new TE(var7, var5, var6));
               }

               var2++;
            }

            if (this.q.zz() != null) {
               for (IUnit var14 : this.q.zz().getChildren()) {
                  if (var14 instanceof com.pnfsoftware.jeb.corei.parsers.elf.vb var17) {
                     String var20 = var17.getName();
                     long var23 = var17.getLoaderInformation().getImageBase();
                     this.RF.put(var20, var17);
                     this.xK.put(var20, var23);
                     this.Dw.add(new TE(var23, var17, var20));
                  }
               }
            }

            List var13 = this.q.RF().ui();
            if (var13 != null) {
               for (bg var18 : var13) {
                  String var21 = var18.q();

                  for (com.pnfsoftware.jeb.corei.parsers.elf.vb var8 : this.RF.values()) {
                     String var9 = var8.getName();
                     if (this.q(var9, var21)) {
                        long var10;
                        if (var18.xK() != 0L) {
                           var10 = var18.xK() - var8.RF();
                        } else {
                           var10 = var18.RF();
                        }

                        this.xK.put(var9, var10);
                        this.Dw.add(new TE(var10, var8, var21));
                        break;
                     }
                  }
               }
            }

            for (INativeCodeUnit var19 : this.q.getPotentialDebuggees()) {
               Long var22 = this.q(var19);
               if (var22 != null && var22 != var19.getVirtualImageBase()) {
                  var19.setPhysicalImageBase(var22);
               }
            }
         }
      }
   }

   public long q(com.pnfsoftware.jeb.corei.parsers.elf.vb var1) {
      List var2 = this.q.RF().ui();
      if (var2 == null) {
         return 0L;
      } else {
         String var3 = var1.getName();

         for (bg var5 : var2) {
            String var6 = var5.q();
            if (this.q(var3, var6)) {
               long var7 = var5.RF();
               if (var5.xK() != 0L) {
                  var7 = var5.xK() - var1.RF();
               }

               return var7;
            }
         }

         return 0L;
      }
   }

   private boolean q(String var1, String var2) {
      return var2 != null && (var2.equals(var1) || var2.endsWith("/" + var1));
   }

   UnitAddress q(long var1) {
      this.q(false);
      TE var3 = (TE)this.Dw.getSegmentContaining(var1);
      if (var3 == null) {
         return new UnitAddress(null, Strings.ff("%Xh", var1));
      } else {
         ICodeObjectUnit var4 = var3.xK();
         ICodeUnit var5 = (ICodeUnit)UnitUtil.findFirstChildByType(var4, ICodeUnit.class, false);
         if (var5 == null) {
            return null;
         } else {
            long var6 = var4.getLoaderInformation().getImageBase();
            if (var5 instanceof INativeCodeUnit) {
               var6 = ((INativeCodeUnit)var5).getVirtualImageBase();
            }

            long var8 = var6 + (var1 - var3.q());
            String var10 = Strings.ff("%Xh", var8);
            return new UnitAddress(var5, var10);
         }
      }
   }

   long q(ICodeUnit var1, String var2) {
      if (var1 == null) {
         return Conversion.stringToLong(var2, -1L);
      } else {
         this.q(false);
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
               Long var9 = (Long)this.xK.get(var8);
               if (var9 == null) {
                  var9 = var6;
               }

               return var9 + (var3 - var6);
            }
         }
      }
   }

   Long q(ICodeUnit var1) {
      ICodeObjectUnit var2 = (ICodeObjectUnit)var1.getParent();
      String var3 = var2.getName();
      return (Long)this.xK.get(var3);
   }

   void q(ICodeUnit var1, Long var2) {
      ICodeObjectUnit var3 = (ICodeObjectUnit)var1.getParent();
      String var4 = var3.getName();
      this.xK.put(var4, var2);
   }

   com.pnfsoftware.jeb.corei.parsers.elf.vb q(String var1) {
      return (com.pnfsoftware.jeb.corei.parsers.elf.vb)this.RF.get(var1);
   }
}
