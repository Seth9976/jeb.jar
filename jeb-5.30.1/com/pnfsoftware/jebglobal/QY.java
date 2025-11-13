package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

class QY {
   uq q;
   List RF;
   int xK = 0;
   final long Dw;
   boolean Uv = false;
   SortedMap oW = new TreeMap();
   List gO = new ArrayList();

   public QY(uq var1, fA var2, long var3) {
      this(var1, q(var1.Of, var2), var3);
   }

   public QY(uq var1, List var2, long var3) {
      this.q = var1;
      this.RF = var2;
      this.xK = var2.size();
      this.Dw = var3;
   }

   public void q(long var1, List var3, fA var4) {
      if (this.q()) {
         throw new IllegalStateException("IT Block can not retrieve more instructions");
      } else {
         this.xK--;
         this.oW.put(var1, new ArrayList(var3));
         this.gO.add(var4);
         if (this.xK != 0) {
            for (IEStatement var6 : var3) {
               if (var6 instanceof IEAssign var7 && (var7.isRoutineCall() || var7.isBreakingFlow())) {
                  throw new IllegalConversionException("Illegal Jump in the middle of an IT Block");
               }
            }
         }
      }
   }

   private static List q(ym var0, fA var1) {
      ArrayList var2 = new ArrayList();
      Deque var3 = Lf.RF(var1.xK());
      int var4 = Lf.q(var1.xK());
      IEGeneric var5 = var0.q(var4);
      IEGeneric var6 = var0.q((var4 & 1) == 0 ? var4 | 1 : var4 & 14);
      var2.add(var5);

      while (!var3.isEmpty()) {
         boolean var7 = (Boolean)var3.pop();
         var2.add(var7 ? var5 : var6);
      }

      return var2;
   }

   public boolean q() {
      return this.xK == 0;
   }

   public void q(IERoutineContext var1, List var2, long var3) {
      this.q(var1, var2, this.Dw, var3);
      ArrayList var5 = new ArrayList(this.oW.entrySet());

      for (int var6 = 0; var6 < var5.size(); var6++) {
         Entry var7 = (Entry)var5.get(var6);
         if (this.Uv) {
            if (var6 != 0) {
               this.q(var1, var2, (Long)var7.getKey(), var3);
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

   private void q(IERoutineContext var1, List var2, long var3, long var5) {
      ArrayList var7 = new ArrayList(this.oW.entrySet());
      QY.eo var8 = null;
      ArrayList var9 = new ArrayList();

      for (int var10 = 0; var10 < var7.size(); var10++) {
         Entry var11 = (Entry)var7.get(var10);
         if (var3 <= (Long)var11.getKey()) {
            IEGeneric var12 = (IEGeneric)this.RF.get(var10);
            if (var8 == null) {
               var8 = new QY.eo();
               var8.q = var12;
               var8.RF.addAll((Collection)var11.getValue());
            } else {
               boolean var13 = var12.equals(var8.q);
               if (var8.Dw) {
                  if (var13) {
                     var8.RF.addAll((Collection)var11.getValue());
                  } else {
                     var8.Dw = false;
                     var8.xK.addAll((Collection)var11.getValue());
                  }
               } else if (var13) {
                  var8.Uv = (Long)var11.getKey();
                  var9.add(var8);
                  var8 = new QY.eo();
                  var8.q = var12;
                  var8.RF.addAll((Collection)var11.getValue());
               } else {
                  var8.xK.addAll((Collection)var11.getValue());
               }
            }

            boolean var18 = this.q(var8.q());
            if (!var18 && var10 + 1 < var7.size()) {
               Long var14 = this.q.q(var8.q(), (fA)this.gO.get(var10), (fA)this.gO.get(var10 + 1), (Long)var11.getKey());
               if (var14 != null) {
                  var11 = (Entry)var7.get(++var10);
                  var18 = this.q(var8.q());
               }
            }

            if (var18) {
               SortedMap var19 = this.oW.tailMap((Long)var11.getKey() + 1L);
               var8.Uv = var19.isEmpty() ? var5 : (Long)var19.firstKey();
               var9.add(var8);
               var8 = null;
            }
         }
      }

      if (var8 != null && var8.q != null) {
         var8.Uv = var5;
         var9.add(var8);
      }

      ArrayList var15 = new ArrayList();

      for (QY.eo var17 : var9) {
         if (var17.xK.isEmpty()) {
            this.q.Of.q(var2.size() + var15.size(), var15, var17.q, var17.RF, var17.Uv, false, this.q.getProgramCounter());
         } else {
            this.q.Of.q(var2.size() + var15.size(), var15, var17.q, var17.RF, var17.xK, var17.Uv, false, this.q.getProgramCounter());
         }
      }

      var15.add(var1.createBranchAssign(this.q.getProgramCounter(), var1.createImm(var5, this.q.JF), false));
      EUtil.setLowerLevelAddress(var3, var15);
      var2.addAll(var15);
   }

   private boolean q(List var1) {
      for (IEStatement var3 : var1) {
         if (var3 instanceof IEAssign) {
            IEGeneric var4 = ((IEAssign)var3).getLeftOperand();
            if (var4.getBitsize() == 1 && (var4.equals(this.q.Of.q) || var4.equals(this.q.Of.RF) || var4.equals(this.q.Of.xK) || var4.equals(this.q.Of.Dw))) {
               return true;
            }
         }
      }

      return false;
   }

   public static QY q(uq var0, BasicBlock var1, int var2, long var3, int var5, IEGeneric var6) {
      fA var7 = (fA)var1.get(var2);
      ArrayList var8 = new ArrayList();
      int var9 = var7.Uv().RF();
      int var10 = var9 & 65534;
      int var11 = var10 | ((var7.Uv().RF() & 1) == 0 ? 1 : 0);
      boolean var12 = true;
      mZ var13 = var7.Uv();

      while (var13.gO() && (var13.RF() & 65534) == var10 && var8.size() <= var5) {
         if (var12) {
            var8.add(var0.Of.q(var13.RF(), var6));
            if (var13.RF() == var11) {
               var12 = false;
            }
         } else {
            if (var13.RF() == var9) {
               break;
            }

            var8.add(var0.Of.q(var13.RF(), var6));
         }

         if (++var2 >= var1.size()) {
            break;
         }

         fA var14 = (fA)var1.get(var2);
         mZ var15 = var14.Uv();
         if (var7.Dw().xK() || var7.q().isPCUpdated() && (!var12 || var15.RF() != var11)) {
            break;
         }

         var7 = var14;
         var13 = var15;
      }

      if (var8.size() > 1) {
         QY var16 = new QY(var0, var8, var3);
         var16.Uv = true;
         return var16;
      } else {
         return null;
      }
   }

   private static class eo {
      IEGeneric q;
      List RF = new ArrayList();
      List xK = new ArrayList();
      boolean Dw = true;
      long Uv;

      List q() {
         return this.Dw ? this.RF : this.xK;
      }
   }
}
