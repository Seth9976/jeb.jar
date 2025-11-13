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

public class Sb {
   private static final ILogger kS = GlobalLog.getLogger(Sb.class);
   private yt wS;
   private CFG UT;
   private Deque E = new ArrayDeque();
   Map pC = new HashMap();
   private Map sY = new HashMap();
   int A;

   public Sb(yt var1, CFG var2) {
      this.wS = var1;
      this.UT = var2;
   }

   public int pC() {
      return this.A;
   }

   public boolean pC(int var1) {
      int var2 = -var1;
      long var3 = this.UT.getEntryAddress();
      Object[] var10000 = new Object[]{var3};
      this.pC(var3, 0);

      while (!this.E.isEmpty()) {
         Couple var5 = (Couple)this.E.remove();
         BasicBlock var6 = this.UT.getBlockAt((Long)var5.getFirst());
         int var7 = (Integer)var5.getSecond();
         var10000 = new Object[]{var6, var7};
         Integer var8 = (Integer)this.pC.get(var6.getBase());
         if (var8 != null) {
            if (var8 == var7) {
               var10000 = new Object[0];
               continue;
            }

            String var9 = Strings.ff("Stack discrepancy at block 0x%X: actual=%d, expected_top=%d", var6.getBase(), var8, var7);
            kS.debug(var9);
            if (!this.pC(var6.getBase())) {
               return false;
            }

            if (var8 > var7) {
               continue;
            }
         }

         for (Mh var10 : var6) {
            int var11 = var7 - var10.pC.wS;
            if (var11 < var2) {
               return false;
            }

            int var12 = var11 + var10.pC.UT;
            var10000 = new Object[]{var10.UT, var7, var12, var10};
            var7 = this.pC(var10.UT, var7, var12);
         }

         if (var6.getLast2().getRoutineCall().isBroken()) {
            Mh var18 = (Mh)var6.getLast();
            if (var18.pC() == 86) {
               if (var18.sY == null) {
                  return false;
               }

               eW var20 = (eW)this.wS.E.gp.get(var18.sY);
               if (!(var20 instanceof nA)) {
                  return false;
               }

               int var22 = ((nA)var20).kS();
               int var23 = ((nA)var20).wS();
               var7 = this.A(var18.UT, var23 - var22 - 1);
            }
         }

         for (BasicBlock var21 : var6.getOutputs()) {
            this.pC(var21.getBase(), var7);
         }
      }

      for (Integer var15 : this.pC.values()) {
         if (var15 < var2) {
            return false;
         }
      }

      for (Integer var16 : this.sY.values()) {
         if (var16 < var2) {
            return false;
         }
      }

      return this.A >= var2;
   }

   private void pC(long var1, int var3) {
      Object[] var10000 = new Object[]{var1, var3};
      this.E.add(new Couple(var1, var3));
   }

   private int pC(long var1, int var3, int var4) {
      this.pC.put(var1, var3);
      this.sY.put(var1, var4);
      if (var4 > this.A) {
         this.A = var4;
      }

      return var4;
   }

   private int A(long var1, int var3) {
      int var4 = (Integer)this.sY.get(var1) + var3;
      this.sY.put(var1, var4);
      if (var4 > this.A) {
         this.A = var4;
      }

      return var4;
   }

   private boolean pC(long var1) {
      ArrayDeque var3 = new ArrayDeque();
      var3.add(var1);
      HashMap var4 = new HashMap();
      var4.put(var1, 0);
      HashSet var5 = new HashSet();
      var5.add(var1);

      while (!var3.isEmpty()) {
         long var6 = (Long)var3.remove();
         int var8 = (Integer)var4.get(var6);
         BasicBlock var9 = this.UT.getBlockAt(var6);

         for (Mh var11 : var9) {
            var8 -= var11.pC.wS;
            if (var8 < 0) {
               return false;
            }

            var8 += var11.pC.UT;
         }

         if (var9.getLast2().getRoutineCall().isBroken()) {
            Mh var17 = (Mh)var9.getLast();
            if (var17.pC() == 86) {
               if (var17.sY == null) {
                  return false;
               }

               eW var19 = (eW)this.wS.E.gp.get(var17.sY);
               if (!(var19 instanceof nA)) {
                  return false;
               }

               int var12 = ((nA)var19).kS();
               int var13 = ((nA)var19).wS();
               var8 = var8 - var12 - 1;
               if (var8 < 0) {
                  return false;
               }

               var8 += var13;
            }
         }

         for (BasicBlock var20 : var9.getOutputs()) {
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
