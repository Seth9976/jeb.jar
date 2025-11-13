package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jebglobal.uV;
import com.pnfsoftware.jebglobal.zj;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

class eW {
   HE pC;
   List A;
   int kS = 0;
   final long wS;
   boolean UT = false;
   SortedMap E = new TreeMap();
   List sY = new ArrayList();

   public eW(HE var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, long var3) {
      this(var1, pC(var1.RW, var2), var3);
   }

   public eW(HE var1, List var2, long var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var2.size();
      this.wS = var3;
   }

   public void pC(long var1, List var3, com.pnfsoftware.jeb.corei.parsers.arm.rQ var4) {
      if (this.pC()) {
         throw new IllegalStateException("IT Block can not retrieve more instructions");
      } else {
         this.kS--;
         this.E.put(var1, new ArrayList(var3));
         this.sY.add(var4);
         if (this.kS != 0) {
            for (IEStatement var6 : var3) {
               if (var6 instanceof IEAssign var7 && (var7.isRoutineCall() || var7.isBreakingFlow())) {
                  throw new IllegalConversionException("Illegal Jump in the middle of an IT Block");
               }
            }
         }
      }
   }

   private static List pC(oP var0, com.pnfsoftware.jeb.corei.parsers.arm.rQ var1) {
      ArrayList var2 = new ArrayList();
      Deque var3 = uV.A(var1.kS());
      int var4 = uV.pC(var1.kS());
      IEGeneric var5 = var0.pC(var4);
      IEGeneric var6 = var0.pC((var4 & 1) == 0 ? var4 | 1 : var4 & 14);
      var2.add(var5);

      while (!var3.isEmpty()) {
         boolean var7 = (Boolean)var3.pop();
         var2.add(var7 ? var5 : var6);
      }

      return var2;
   }

   public boolean pC() {
      return this.kS == 0;
   }

   public void pC(IERoutineContext var1, List var2, long var3) {
      this.pC(var1, var2, this.wS, var3);
      ArrayList var5 = new ArrayList(this.E.entrySet());

      for (int var6 = 0; var6 < var5.size(); var6++) {
         Entry var7 = (Entry)var5.get(var6);
         if (this.UT) {
            if (var6 != 0) {
               this.pC(var1, var2, (Long)var7.getKey(), var3);
            }
         } else {
            long var8 = (Long)var7.getKey();

            for (IEStatement var11 : (List)var7.getValue()) {
               IEStatement var12 = (IEStatement)var11.duplicate();
               var12.setLowerLevelAddress(var8);
               var2.add(var12);
            }
         }
      }
   }

   private void pC(IERoutineContext var1, List var2, long var3, long var5) {
      ArrayList var7 = new ArrayList(this.E.entrySet());
      eW.Av var8 = null;
      ArrayList var9 = new ArrayList();

      for (int var10 = 0; var10 < var7.size(); var10++) {
         Entry var11 = (Entry)var7.get(var10);
         if (var3 <= (Long)var11.getKey()) {
            IEGeneric var12 = (IEGeneric)this.A.get(var10);
            if (var8 == null) {
               var8 = new eW.Av();
               var8.pC = var12;
               var8.A.addAll((Collection)var11.getValue());
            } else {
               boolean var13 = var12.equals(var8.pC);
               if (var8.wS) {
                  if (var13) {
                     var8.A.addAll((Collection)var11.getValue());
                  } else {
                     var8.wS = false;
                     var8.kS.addAll((Collection)var11.getValue());
                  }
               } else if (var13) {
                  var8.UT = (Long)var11.getKey();
                  var9.add(var8);
                  var8 = new eW.Av();
                  var8.pC = var12;
                  var8.A.addAll((Collection)var11.getValue());
               } else {
                  var8.kS.addAll((Collection)var11.getValue());
               }
            }

            boolean var18 = this.pC(var8.pC());
            if (!var18 && var10 + 1 < var7.size()) {
               Long var14 = this.pC
                  .pC(
                     var8.pC(),
                     (com.pnfsoftware.jeb.corei.parsers.arm.rQ)this.sY.get(var10),
                     (com.pnfsoftware.jeb.corei.parsers.arm.rQ)this.sY.get(var10 + 1),
                     (Long)var11.getKey()
                  );
               if (var14 != null) {
                  var11 = (Entry)var7.get(++var10);
                  var18 = this.pC(var8.pC());
               }
            }

            if (var18) {
               SortedMap var19 = this.E.tailMap((Long)var11.getKey() + 1L);
               var8.UT = var19.isEmpty() ? var5 : (Long)var19.firstKey();
               var9.add(var8);
               var8 = null;
            }
         }
      }

      if (var8 != null && var8.pC != null) {
         var8.UT = var5;
         var9.add(var8);
      }

      ArrayList var15 = new ArrayList();

      for (eW.Av var17 : var9) {
         if (var17.kS.isEmpty()) {
            this.pC.RW.pC(var2.size() + var15.size(), var15, var17.pC, var17.A, var17.UT, false, this.pC.getProgramCounter());
         } else {
            this.pC.RW.pC(var2.size() + var15.size(), var15, var17.pC, var17.A, var17.kS, var17.UT, false, this.pC.getProgramCounter());
         }
      }

      var15.add(var1.createBranchAssign(this.pC.getProgramCounter(), var1.createImm(var5, this.pC.fI), false));
      EUtil.setLowerLevelAddress(var3, var15);
      var2.addAll(var15);
   }

   private boolean pC(List var1) {
      for (IEStatement var3 : var1) {
         if (var3 instanceof IEAssign) {
            IEGeneric var4 = ((IEAssign)var3).getLeftOperand();
            if (var4.getBitsize() == 1 && (var4.equals(this.pC.RW.pC) || var4.equals(this.pC.RW.A) || var4.equals(this.pC.RW.kS) || var4.equals(this.pC.RW.wS))
               )
             {
               return true;
            }
         }
      }

      return false;
   }

   public static eW pC(HE var0, BasicBlock var1, int var2, long var3, int var5, IEGeneric var6) {
      com.pnfsoftware.jeb.corei.parsers.arm.rQ var7 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(var2);
      ArrayList var8 = new ArrayList();
      int var9 = var7.UT().A();
      int var10 = var9 & 65534;
      int var11 = var10 | ((var7.UT().A() & 1) == 0 ? 1 : 0);
      boolean var12 = true;
      zj var13 = var7.UT();

      while (var13.sY() && (var13.A() & 65534) == var10 && var8.size() <= var5) {
         if (var12) {
            var8.add(var0.RW.pC(var13.A(), var6));
            if (var13.A() == var11) {
               var12 = false;
            }
         } else {
            if (var13.A() == var9) {
               break;
            }

            var8.add(var0.RW.pC(var13.A(), var6));
         }

         if (++var2 >= var1.size()) {
            break;
         }

         com.pnfsoftware.jeb.corei.parsers.arm.rQ var14 = (com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1.get(var2);
         zj var15 = var14.UT();
         if (var7.wS().kS() || var7.pC().isPCUpdated() && (!var12 || var15.A() != var11)) {
            break;
         }

         var7 = var14;
         var13 = var15;
      }

      if (var8.size() > 1) {
         eW var16 = new eW(var0, var8, var3);
         var16.UT = true;
         return var16;
      } else {
         return null;
      }
   }

   private static class Av {
      IEGeneric pC;
      List A = new ArrayList();
      List kS = new ArrayList();
      boolean wS = true;
      long UT;

      List pC() {
         return this.wS ? this.A : this.kS;
      }
   }
}
