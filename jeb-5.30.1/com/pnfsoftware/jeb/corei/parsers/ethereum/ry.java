package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ry {
   private static final ILogger xK = GlobalLog.getLogger(ry.class);
   private PY Dw;
   private CFG Uv;
   private Deque oW = new ArrayDeque();
   Map q = new HashMap();
   private Map gO = new HashMap();
   int RF;

   public ry(PY var1, CFG var2) {
      this.Dw = var1;
      this.Uv = var2;
   }

   public int q() {
      return this.RF;
   }

   public boolean q(int var1) {
      int var2 = -var1;
      long var3 = this.Uv.getEntryAddress();
      Object[] var10000 = new Object[]{var3};
      this.q(var3, 0);

      while (!this.oW.isEmpty()) {
         Couple var5 = (Couple)this.oW.remove();
         BasicBlock var6 = this.Uv.getBlockAt((Long)var5.getFirst());
         int var7 = (Integer)var5.getSecond();
         var10000 = new Object[]{var6, var7};
         Integer var8 = (Integer)this.q.get(var6.getBase());
         if (var8 != null) {
            if (var8 == var7) {
               var10000 = new Object[0];
               continue;
            }

            String var9 = Strings.ff("Stack discrepancy at block 0x%X: actual=%d, expected_top=%d", var6.getBase(), var8, var7);
            xK.debug(var9);
            if (!this.q(var6.getBase())) {
               return false;
            }

            if (var8 > var7) {
               continue;
            }
         }

         for (vX var10 : var6) {
            int var11 = var7 - var10.nf.Dw;
            if (var11 < var2) {
               return false;
            }

            int var12 = var11 + var10.nf.Uv;
            var10000 = new Object[]{var10.zz, var7, var12, var10};
            var7 = this.q(var10.zz, var7, var12);
         }

         if (var6.getLast2().getRoutineCall().isBroken()) {
            vX var18 = (vX)var6.getLast();
            if (var18.q() == 86) {
               if (var18.HF == null) {
                  return false;
               }

               qx var20 = (qx)this.Dw.gO.za.get(var18.HF);
               if (!(var20 instanceof Fw)) {
                  return false;
               }

               int var22 = ((Fw)var20).Uv();
               int var23 = ((Fw)var20).oW();
               var7 = this.RF(var18.zz, var23 - var22 - 1);
            }
         }

         for (BasicBlock var21 : var6.getOutputBlocks()) {
            this.q(var21.getBase(), var7);
         }
      }

      for (Integer var15 : this.q.values()) {
         if (var15 < var2) {
            return false;
         }
      }

      for (Integer var16 : this.gO.values()) {
         if (var16 < var2) {
            return false;
         }
      }

      return this.RF >= var2;
   }

   private void q(long var1, int var3) {
      Object[] var10000 = new Object[]{var1, var3};
      this.oW.add(new Couple(var1, var3));
   }

   private int q(long var1, int var3, int var4) {
      this.q.put(var1, var3);
      this.gO.put(var1, var4);
      if (var4 > this.RF) {
         this.RF = var4;
      }

      return var4;
   }

   private int RF(long var1, int var3) {
      int var4 = (Integer)this.gO.get(var1) + var3;
      this.gO.put(var1, var4);
      if (var4 > this.RF) {
         this.RF = var4;
      }

      return var4;
   }

   private boolean q(long var1) {
      ArrayDeque var3 = new ArrayDeque();
      var3.add(var1);
      HashMap var4 = new HashMap();
      var4.put(var1, 0);
      HashSet var5 = new HashSet();
      var5.add(var1);

      while (!var3.isEmpty()) {
         long var6 = (Long)var3.remove();
         int var8 = (Integer)var4.get(var6);
         BasicBlock var9 = this.Uv.getBlockAt(var6);

         for (vX var11 : var9) {
            var8 -= var11.nf.Dw;
            if (var8 < 0) {
               return false;
            }

            var8 += var11.nf.Uv;
         }

         if (var9.getLast2().getRoutineCall().isBroken()) {
            vX var17 = (vX)var9.getLast();
            if (var17.q() == 86) {
               if (var17.HF == null) {
                  return false;
               }

               qx var19 = (qx)this.Dw.gO.za.get(var17.HF);
               if (!(var19 instanceof Fw)) {
                  return false;
               }

               int var12 = ((Fw)var19).Uv();
               int var13 = ((Fw)var19).oW();
               var8 = var8 - var12 - 1;
               if (var8 < 0) {
                  return false;
               }

               var8 += var13;
            }
         }

         for (BasicBlock var20 : var9.getOutputBlocks()) {
            var5.addAll(var20.getInputOffsets());
            long var21 = var20.getBase();
            Integer var14 = (Integer)var4.get(var21);
            if (var14 == null || var8 < var14) {
               var4.put(var21, var8);
               var3.add(var21);
            }
         }
      }

      return true;
   }
}
