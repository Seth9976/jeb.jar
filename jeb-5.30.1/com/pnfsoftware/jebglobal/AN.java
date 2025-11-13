package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AN implements YX {
   private static final int q = 10;
   private final aaf RF;
   private final Jc xK;
   private Set Dw = new HashSet();

   public AN(aaf var1, Jc var2) {
      this.RF = var1;
      this.xK = var2;
   }

   public boolean q(long var1, fA var3, TreeSet var4, TreeSet var5) {
      axp var6 = this.RF.oW(var1);
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

      var9.addAll(this.Dw);
      boolean var12 = false;
      int var13 = 0;

      while (true) {
         long var14 = var10.getBase();
         Iterator var16 = var10.iterator();

         while (true) {
            if (var16.hasNext()) {
               fA var17 = (fA)var16.next();
               if (var17.oW() || var17.gO()) {
                  return false;
               }

               if (this.xK.q(var14, var17.getProcessorMode(), false)) {
                  var12 = true;
               }

               ArrayList var18 = new ArrayList();
               ArrayList var19 = new ArrayList();
               var17.getDefUse(var18, var19);

               for (Integer var21 : var19) {
                  if (var21 >= var11 && !var9.contains(var21) && !this.q(var17, var21, var9)) {
                     if (!var12) {
                        return true;
                     }

                     this.Dw.add(var21);
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

               for (BasicBlock var24 : var10.getOutputBlocks()) {
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

   private boolean q(fA var1, Integer var2, Set var3) {
      String var4 = var1.Dw().q();
      if (var4.equals("STR")) {
         return RegisterUtil.getPureId(var1.RF()[0].getOperandValue()) == var2.intValue() && this.q(var1.RF()[1], var1.getProcessorMode());
      } else if (var1.getProcessorMode() == 64) {
         return !var4.equals("STP")
            ? false
            : (
                  RegisterUtil.getPureId(var1.RF()[0].getOperandValue()) == var2.intValue()
                     || RegisterUtil.getPureId(var1.RF()[1].getOperandValue()) == var2.intValue()
               )
               && this.q(var1.RF()[2], var1.getProcessorMode());
      } else if (var4.equals("PUSH")) {
         return true;
      } else {
         return var4.startsWith("STM") ? RegisterUtil.getPureId(var1.RF()[0].getOperandValue()) != var2.intValue() : false;
      }
   }

   private boolean q(CW var1, int var2) {
      if (!(var1 instanceof wh var3)) {
         return false;
      } else {
         return !var3.RF().Dw(var2) ? false : var3.gO() == null || var3.gO().isImmediate() && this.q(var3.gO().getOperandValue());
      }
   }

   private boolean q(long var1) {
      return var1 <= 128L && var1 >= -128L;
   }
}
