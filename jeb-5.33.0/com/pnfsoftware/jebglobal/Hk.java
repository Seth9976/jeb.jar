package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Hk implements zz {
   private final Tw pC;
   private final Nd A;
   private Set kS = new HashSet();

   public Hk(Tw var1, Nd var2) {
      this.pC = var1;
      this.A = var2;
   }

   public boolean pC(long var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var3, TreeSet var4, TreeSet var5) {
      auu var6 = this.pC.E(var1);
      CFG var7 = var6.getData().getCFG();
      HashSet var8 = new HashSet();
      HashSet var9 = new HashSet();
      BasicBlock var10 = var7.getEntryBlock();
      int var11 = var3.getProcessorMode() == 64 ? 8 : 4;
      if (var3.getProcessorMode() == 64) {
         var9.add((int)RegisterUtil.createPureRegisterId(0, 10));
         var9.add(30);
         var9.add(31);
         var9.add(16);
         var9.add(17);
         var9.add(18);
      } else {
         var9.add(15);
         var9.add(14);
         var9.add(13);
      }

      var9.addAll(this.kS);
      boolean var12 = false;
      int var13 = 0;

      while (true) {
         long var14 = var10.getBase();
         Iterator var16 = var10.iterator();

         while (true) {
            if (var16.hasNext()) {
               com.pnfsoftware.jeb.corei.parsers.arm.rQ var17 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var16.next();
               if (var17.E() || var17.sY()) {
                  return false;
               }

               if (this.A.pC(var14, var17.getProcessorMode(), false)) {
                  var12 = true;
               }

               ArrayList var18 = new ArrayList();
               ArrayList var19 = new ArrayList();
               var17.getDefUse(var18, var19);

               for (Integer var21 : var19) {
                  if (var21 >= var11 && !var9.contains(var21) && !this.pC(var17, var21, var9)) {
                     if (!var12) {
                        return true;
                     }

                     this.kS.add(var21);
                  }
               }

               for (Integer var26 : var18) {
                  var9.add(var26);
               }

               var14 += var17.getSize();
               if (++var13 != 10) {
                  continue;
               }
            } else {
               var8.add(var10.getBase());
               BasicBlock var22 = null;

               for (BasicBlock var24 : var10.getOutputs()) {
                  if (!var8.contains(var24.getBase())) {
                     if (var24.getFirstAddress() == var14) {
                        var22 = var24;
                        break;
                     }

                     if (var22 == null) {
                        var22 = var24;
                     } else if (var22.getFirstAddress() < var14) {
                        if (var24.getFirstAddress() > var14) {
                           var22 = var24;
                        } else {
                           var22 = var22.getFirstAddress() < var24.getFirstAddress() ? var22 : var24;
                        }
                     } else if (var24.getFirstAddress() < var14) {
                        var22 = var24;
                     } else {
                        var22 = var22.getFirstAddress() < var24.getFirstAddress() ? var22 : var24;
                     }
                  }
               }

               if (var22 != null) {
                  var10 = var22;
                  break;
               }
            }

            return false;
         }
      }
   }

   private boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Integer var2, Set var3) {
      String var4 = var1.wS().pC();
      if (var4.equals("STR")) {
         return RegisterUtil.getPureId(var1.A()[0].getOperandValue()) == var2.intValue() && this.pC(var1.A()[1], var1.getProcessorMode());
      } else if (var1.getProcessorMode() == 64) {
         return !var4.equals("STP")
            ? false
            : (
                  RegisterUtil.getPureId(var1.A()[0].getOperandValue()) == var2.intValue()
                     || RegisterUtil.getPureId(var1.A()[1].getOperandValue()) == var2.intValue()
               )
               && this.pC(var1.A()[2], var1.getProcessorMode());
      } else if (var4.equals("PUSH")) {
         return true;
      } else {
         return var4.startsWith("STM") ? RegisterUtil.getPureId(var1.A()[0].getOperandValue()) != var2.intValue() : false;
      }
   }

   private boolean pC(Yg var1, int var2) {
      if (!(var1 instanceof KH var3)) {
         return false;
      } else {
         return !var3.A().wS(var2) ? false : var3.sY() == null || var3.sY().isImmediate() && this.pC(var3.sY().getOperandValue());
      }
   }

   private boolean pC(long var1) {
      return var1 <= 128L && var1 >= -128L;
   }
}
