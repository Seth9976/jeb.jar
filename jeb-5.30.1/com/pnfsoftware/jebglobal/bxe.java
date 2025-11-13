package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.DVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public class bxe {
   private static final ILogger q = GlobalLog.getLogger(bxe.class);
   private final bxc.eo RF;
   private final IDMethodContext xK;
   private final CFG Dw;
   private final IDExpression Uv;
   private boolean oW = true;
   private Map gO;

   public bxe(bxc.eo var1, CFG var2, IDMethodContext var3) {
      this.RF = var1;
      this.Dw = var2;
      this.xK = var3;
      this.Uv = null;
   }

   public bxe(bxc.eo var1, IDExpression var2, IDMethodContext var3) {
      this.RF = var1;
      this.xK = var3;
      this.Uv = var2;
      this.Dw = null;
   }

   public bxc.eo q() {
      return this.RF;
   }

   public IDMethodContext RF() {
      return this.xK;
   }

   public CFG xK() {
      return this.Dw;
   }

   public IDExpression Dw() {
      return this.Uv;
   }

   public boolean Uv() {
      return this.oW;
   }

   public void q(boolean var1) {
      this.oW = var1;
   }

   public void q(Map var1) {
      this.gO = var1;
   }

   public Map oW() {
      return this.gO;
   }

   @Override
   public String toString() {
      return Strings.ff("Matcher for: pattern=%s, CFG=%s", this.RF, this.Dw);
   }

   public bxe.eo gO() {
      return this.q(null);
   }

   private int q(AtomicInteger var1) {
      if (var1 != null) {
         int var2 = var1.intValue();
         if (var2 >= 0) {
            var1.set(-1);
            return var2;
         }
      }

      return 0;
   }

   private bxe.eo xK(bxe.eo var1) {
      AtomicInteger var2;
      AtomicInteger var3;
      AtomicInteger var4;
      if (var1 == null) {
         var2 = new AtomicInteger();
         var3 = new AtomicInteger();
         var4 = new AtomicInteger();
      } else {
         var2 = new AtomicInteger(var1.Uv);
         var3 = new AtomicInteger(var1.oW);
         var4 = new AtomicInteger(var1.gO + 1);
      }

      for (int var5 = this.q(var2); var5 < this.RF.Uv().size(); var5++) {
         bxc.eo.eo var6 = (bxc.eo.eo)this.RF.Uv().get(var5);
         bxc.eo.eo.eo var7 = var6.Dw();
         bxj var8 = var7.RF;
         bxj var9 = var7.xK;

         for (int var10 = this.q(var3); var10 < this.Dw.size(); var10++) {
            BasicBlock var11 = this.Dw.get(var10);
            if (var11.size() >= var6.xK()) {
               int var12 = var6.Uv();
               if (var12 >= 0) {
                  int var13 = var11.size() - var6.xK() + var12;
                  if (var13 >= 0) {
                     for (int var14 = var12 + this.q(var4); var14 <= var13; var14++) {
                        IDInstruction var15 = (IDInstruction)var11.get(var14);
                        int var16 = var14 - var12;
                        HashMap var18 = new HashMap();
                        IDExpression[] var19 = new IDExpression[2];
                        boolean var17;
                        if (!this.RF.xK() && !this.RF.Dw()) {
                           if (var15 == null || !var15.isAssign()) {
                              continue;
                           }

                           IDExpression var30 = var15.getAssignDestination();
                           IDExpression var33 = var15.getAssignSource();
                           bxa var22 = new bxa(this.xK, var8, var18);
                           var22.q(this.oW);
                           var22.q(this.gO);
                           if (!var22.q(var30)) {
                              continue;
                           }

                           var22 = new bxa(this.xK, var9, var18);
                           var22.q(this.oW);
                           var22.q(this.gO);
                           if (!var22.q(var33)) {
                              continue;
                           }

                           var17 = true;
                        } else {
                           DVisitResults var20 = new DVisitResults(1);
                           var20.setVisitResult(false);
                           bxa var21 = new bxa(this.xK, var8, var18);
                           var21.q(this.oW);
                           var21.q(this.gO);
                           var17 = var15.visitDepthPost(new bxf(this, var21, var19), null, var20);
                        }

                        if (var17 && !this.RF.xK()) {
                           int var31 = var16 + var6.xK();
                           int var34 = var16;

                           for (int var36 = 0; var34 < var31; var36++) {
                              if (var36 != var12) {
                                 IDInstruction var23 = (IDInstruction)var11.get(var34);
                                 if (var23 == null || !var23.isAssign()) {
                                    var17 = false;
                                    break;
                                 }

                                 IDExpression var24 = var23.getAssignDestination();
                                 IDExpression var25 = var23.getAssignSource();
                                 bxc.eo.eo.eo var26 = var6.q(var36);
                                 bxj var27 = var26.RF;
                                 bxj var28 = var26.xK;
                                 bxa var29 = new bxa(this.xK, var27, var18);
                                 var29.q(this.oW);
                                 var29.q(this.gO);
                                 if (!var29.q(var24)) {
                                    var17 = false;
                                    break;
                                 }

                                 var29 = new bxa(this.xK, var28, var18);
                                 var29.q(this.oW);
                                 var29.q(this.gO);
                                 if (!var29.q(var25)) {
                                    var17 = false;
                                    break;
                                 }
                              }

                              var34++;
                           }
                        }

                        if (var17 && ((var6.q() & 1) == 0 || !this.RF(var18)) && ((var6.q() & 2) == 0 || this.xK(var18))) {
                           bxe.eo var32 = new bxe.eo(this.xK, this.Dw, this.Uv, this.RF, var5, var18);
                           var32.gP = var11.getAddressOfInstruction(var16);
                           var32.oW = var10;
                           var32.Uv = var5;
                           var32.gO = var14 - var12;
                           var32.za = var19[0];
                           var32.lm = var19[1];
                           if (this.RF.nf() == null || this.RF.nf().q(this, var32)) {
                              return var32;
                           }
                        }
                     }
                     continue;
                  }
               }
            }
         }
      }

      return null;
   }

   private bxe.eo Dw(bxe.eo var1) {
      if (!this.RF.xK()) {
         return null;
      } else {
         AtomicInteger var2 = new AtomicInteger();
         if (var1 != null) {
            var2 = new AtomicInteger(var1.Uv);
         }

         IDExpression var3 = this.Uv;
         if (var1 != null) {
            var3 = var1.HF;
         }

         for (int var4 = this.q(var2); var4 < this.RF.Uv().size(); var4++) {
            bxc.eo.eo var5 = (bxc.eo.eo)this.RF.Uv().get(var4);
            bxc.eo.eo.eo var6 = var5.Dw();
            bxj var7 = var6.RF;
            HashMap var8 = new HashMap();
            IDExpression[] var9 = new IDExpression[2];
            DVisitResults var10 = new DVisitResults(1);
            var10.setVisitResult(false);
            bxa var11 = new bxa(this.xK, var7, var8);
            var11.q(this.oW);
            var11.q(this.gO);
            if (var3.visitDepthPost(new bxg(this, var11, var9), null, var10)
               && ((var5.q() & 1) == 0 || !this.RF(var8))
               && ((var5.q() & 2) == 0 || this.xK(var8))) {
               bxe.eo var12 = new bxe.eo(this.xK, this.Dw, this.Uv, this.RF, var4, var8);
               var12.Uv = var4;
               var12.za = var9[0];
               var12.lm = var9[1];
               if (this.RF.nf() == null || this.RF.nf().q(this, var12)) {
                  return var12;
               }
            }
         }

         return null;
      }
   }

   private boolean RF(Map var1) {
      HashSet var2 = new HashSet();

      for (Entry var4 : var1.entrySet()) {
         if (var4.getValue() instanceof IDVar var6 && !var2.add(var6.getId())) {
            return true;
         }
      }

      return false;
   }

   private boolean xK(Map var1) {
      int var2 = -1;

      for (Entry var4 : var1.entrySet()) {
         Object var5 = var4.getValue();
         if (var5 instanceof IDExpression) {
            int var6 = bto.RF((IDExpression)var5);
            if (var2 < 0) {
               var2 = var6;
            } else if (var6 != var2) {
               return false;
            }
         }
      }

      return true;
   }

   public bxe.eo q(bxe.eo var1) {
      if (!this.RF.lm()) {
         throw new IllegalArgumentException("Pattern is not compiled");
      } else if (this.Dw != null) {
         return this.xK(var1);
      } else if (this.Uv != null) {
         return this.Dw(var1);
      } else {
         throw new RuntimeException();
      }
   }

   public bxe.eo nf() {
      return this.RF(null);
   }

   public bxe.eo RF(bxe.eo var1) {
      bxe.eo var2 = this.q(var1);
      if (var2 == null) {
         return null;
      } else {
         var2.JY = this.q(var2, true);
         return var2;
      }
   }

   public boolean q(bxe.eo var1, boolean var2) {
      if (!var2 && this.RF.nf != null) {
         Boolean var3 = this.RF.nf.q(this, var1);
         if (var3 != null) {
            if (!var3) {
               return false;
            }

            return true;
         }
      }

      bxc.eo.eo var20 = this.RF.gO();
      if (var20 == null) {
         throw new IllegalArgumentException("No replacement pattern provided");
      } else if (!this.RF.xK() && !this.RF.Dw()) {
         bxc.eo.eo var21 = this.RF.q(var1.Uv());
         int var22 = var21.nf();
         int var6 = 0;
         long var7 = this.q(var1, var21.oW());
         Couple var9 = this.Dw.getInstructionLocation(var7);
         BasicBlock var10 = (BasicBlock)var9.getFirst();
         int var11 = (Integer)var9.getSecond();
         int var12 = var11 + var22;

         for (int var13 = var11; var13 < var12; var13++) {
            var6 += ((IDInstruction)var10.get(var13)).getSize();
         }

         int var23 = var20.xK();
         if (var6 < var23) {
            return false;
         } else {
            ArrayList var14 = new ArrayList();

            for (int var15 = 0; var15 < var23; var15++) {
               bxc.eo.eo.eo var16 = var20.q(var15);
               IDExpression var17 = new bwy(this.xK, var16.xK).q(var1.zz);
               IDExpression var18 = new bwy(this.xK, var16.RF).q(var1.zz);
               IDInstruction var19 = this.xK.createAssign(var18, var17);
               var14.add(var19);
            }

            int var24 = var14.size() - 1;
            ((IDInstruction)var14.get(var24)).setSize(var6 - var24);
            if (!this.Dw.replaceInstructionsInBlock(var7, var22, var14)) {
               return false;
            } else {
               this.Dw.invalidateDataFlowAnalysis();
               return true;
            }
         }
      } else {
         bxj var4 = ((bxc.eo.eo.eo)var20.Dw.get(0)).RF;
         IDExpression var5 = new bwy(this.xK, var4).q(var1.zz, bto.RF(var1.za));
         if (var1.za.getType() != null) {
            var5.setType(var1.za.getType());
         }

         if (var1.lm == null) {
            var1.HF = var5;
            return true;
         } else if (var1.lm.replaceSubExpression(var1.za, var5)) {
            var1.HF = var1.xK;
            return true;
         } else {
            return false;
         }
      }
   }

   public int gP() {
      return this.q(null);
   }

   public int q(bxe.eo[] var1) {
      int var2 = 0;
      bxe.eo var3 = null;

      while (true) {
         var3 = this.RF(var3);
         if (var3 == null) {
            return var2;
         }

         if (var1 != null) {
            var1[0] = var3;
         }

         var2++;
      }
   }

   public long q(bxe.eo var1, int var2) {
      bxc.eo.eo var3 = this.RF.q(var1.Uv());
      if (var2 < 0) {
         var2 += var3.xK();
      }

      Couple var4 = this.Dw.getInstructionLocation(var1.nf());
      int var5 = (Integer)var4.getSecond() + var2;
      return ((BasicBlock)var4.getFirst()).getAddressOfInstruction(var5);
   }

   public static class eo {
      private IDMethodContext q;
      private CFG RF;
      private IDExpression xK;
      private bxc.eo Dw;
      private int Uv;
      private int oW;
      private int gO;
      private int nf;
      private long gP;
      private IDExpression za;
      private IDExpression lm;
      private Map zz;
      private boolean JY;
      private IDExpression HF;

      eo(IDMethodContext var1, CFG var2, IDExpression var3, bxc.eo var4, int var5, Map var6) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
         this.nf = var5;
         this.zz = var6;
      }

      public IDMethodContext q() {
         return this.q;
      }

      public CFG RF() {
         return this.RF;
      }

      public IDExpression xK() {
         return this.xK;
      }

      public bxc.eo Dw() {
         return this.Dw;
      }

      public int Uv() {
         return this.nf;
      }

      public IDExpression oW() {
         return this.za;
      }

      public IDExpression gO() {
         return this.lm;
      }

      public long nf() {
         return this.gP;
      }

      public Map gP() {
         return this.zz;
      }

      public Object q(int var1, Class var2) {
         return this.zz.get(var1);
      }

      public IDExpression q(int var1) {
         return (IDExpression)this.zz.get(var1);
      }

      public boolean za() {
         return this.JY;
      }

      public IDExpression lm() {
         return this.HF;
      }

      @Override
      public String toString() {
         return Strings.ff("Input #%d found at address 0x%X, matchMap: %s", this.nf, this.gP, this.zz);
      }
   }
}
