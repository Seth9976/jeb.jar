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

public class bsq {
   private static final ILogger pC = GlobalLog.getLogger(bsq.class);
   private final bso.Av A;
   private final IDMethodContext kS;
   private final CFG wS;
   private final IDExpression UT;
   private boolean E = true;
   private Map sY;

   public bsq(bso.Av var1, CFG var2, IDMethodContext var3) {
      this.A = var1;
      this.wS = var2;
      this.kS = var3;
      this.UT = null;
   }

   public bso.Av pC() {
      return this.A;
   }

   public void pC(Map var1) {
      this.sY = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("Matcher for: pattern=%s, CFG=%s", this.A, this.wS);
   }

   private int pC(AtomicInteger var1) {
      if (var1 != null) {
         int var2 = var1.intValue();
         if (var2 >= 0) {
            var1.set(-1);
            return var2;
         }
      }

      return 0;
   }

   private bsq.Av A(bsq.Av var1) {
      AtomicInteger var2;
      AtomicInteger var3;
      AtomicInteger var4;
      if (var1 == null) {
         var2 = new AtomicInteger();
         var3 = new AtomicInteger();
         var4 = new AtomicInteger();
      } else {
         var2 = new AtomicInteger(var1.UT);
         var3 = new AtomicInteger(var1.E);
         var4 = new AtomicInteger(var1.sY + 1);
      }

      for (int var5 = this.pC(var2); var5 < this.A.kS().size(); var5++) {
         bso.Av.Av var6 = (bso.Av.Av)this.A.kS().get(var5);
         bso.Av.Av.Av var7 = var6.kS();
         bsv var8 = var7.A;
         bsv var9 = var7.kS;

         for (int var10 = this.pC(var3); var10 < this.wS.size(); var10++) {
            BasicBlock var11 = this.wS.get(var10);
            if (var11.size() >= var6.A()) {
               int var12 = var6.wS();
               if (var12 >= 0) {
                  int var13 = var11.size() - var6.A() + var12;
                  if (var13 >= 0) {
                     for (int var14 = var12 + this.pC(var4); var14 <= var13; var14++) {
                        IDInstruction var15 = (IDInstruction)var11.get(var14);
                        int var16 = var14 - var12;
                        HashMap var18 = new HashMap();
                        IDExpression[] var19 = new IDExpression[2];
                        boolean var17;
                        if (!this.A.pC() && !this.A.A()) {
                           if (var15 == null || !var15.isAssign()) {
                              continue;
                           }

                           IDExpression var30 = var15.getAssignDestination();
                           IDExpression var33 = var15.getAssignSource();
                           bsm var22 = new bsm(this.kS, var8, var18);
                           var22.pC(this.E);
                           var22.pC(this.sY);
                           if (!var22.pC(var30)) {
                              continue;
                           }

                           var22 = new bsm(this.kS, var9, var18);
                           var22.pC(this.E);
                           var22.pC(this.sY);
                           if (!var22.pC(var33)) {
                              continue;
                           }

                           var17 = true;
                        } else {
                           DVisitResults var20 = new DVisitResults(1);
                           var20.setVisitResult(false);
                           bsm var21 = new bsm(this.kS, var8, var18);
                           var21.pC(this.E);
                           var21.pC(this.sY);
                           var17 = var15.visitDepthPost(new bsr(this, var21, var19), null, var20);
                        }

                        if (var17 && !this.A.pC()) {
                           int var31 = var16 + var6.A();
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
                                 bso.Av.Av.Av var26 = var6.pC(var36);
                                 bsv var27 = var26.A;
                                 bsv var28 = var26.kS;
                                 bsm var29 = new bsm(this.kS, var27, var18);
                                 var29.pC(this.E);
                                 var29.pC(this.sY);
                                 if (!var29.pC(var24)) {
                                    var17 = false;
                                    break;
                                 }

                                 var29 = new bsm(this.kS, var28, var18);
                                 var29.pC(this.E);
                                 var29.pC(this.sY);
                                 if (!var29.pC(var25)) {
                                    var17 = false;
                                    break;
                                 }
                              }

                              var34++;
                           }
                        }

                        if (var17 && ((var6.pC() & 1) == 0 || !this.A(var18)) && ((var6.pC() & 2) == 0 || this.kS(var18))) {
                           bsq.Av var32 = new bsq.Av(this.kS, this.wS, this.UT, this.A, var5, var18);
                           var32.ld = var11.getAddressOfInstruction(var16);
                           var32.E = var10;
                           var32.UT = var5;
                           var32.sY = var14 - var12;
                           var32.gp = var19[0];
                           var32.oT = var19[1];
                           if (this.A.UT() == null || this.A.UT().pC(this, var32)) {
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

   private bsq.Av kS(bsq.Av var1) {
      if (!this.A.pC()) {
         return null;
      } else {
         AtomicInteger var2 = new AtomicInteger();
         if (var1 != null) {
            var2 = new AtomicInteger(var1.UT);
         }

         IDExpression var3 = this.UT;
         if (var1 != null) {
            var3 = var1.WR;
         }

         for (int var4 = this.pC(var2); var4 < this.A.kS().size(); var4++) {
            bso.Av.Av var5 = (bso.Av.Av)this.A.kS().get(var4);
            bso.Av.Av.Av var6 = var5.kS();
            bsv var7 = var6.A;
            HashMap var8 = new HashMap();
            IDExpression[] var9 = new IDExpression[2];
            DVisitResults var10 = new DVisitResults(1);
            var10.setVisitResult(false);
            bsm var11 = new bsm(this.kS, var7, var8);
            var11.pC(this.E);
            var11.pC(this.sY);
            if (var3.visitDepthPost(new bss(this, var11, var9), null, var10)
               && ((var5.pC() & 1) == 0 || !this.A(var8))
               && ((var5.pC() & 2) == 0 || this.kS(var8))) {
               bsq.Av var12 = new bsq.Av(this.kS, this.wS, this.UT, this.A, var4, var8);
               var12.UT = var4;
               var12.gp = var9[0];
               var12.oT = var9[1];
               if (this.A.UT() == null || this.A.UT().pC(this, var12)) {
                  return var12;
               }
            }
         }

         return null;
      }
   }

   private boolean A(Map var1) {
      HashSet var2 = new HashSet();

      for (Entry var4 : var1.entrySet()) {
         if (var4.getValue() instanceof IDVar var6 && !var2.add(var6.getId())) {
            return true;
         }
      }

      return false;
   }

   private boolean kS(Map var1) {
      int var2 = -1;

      for (Entry var4 : var1.entrySet()) {
         Object var5 = var4.getValue();
         if (var5 instanceof IDExpression) {
            int var6 = bpl.A((IDExpression)var5);
            if (var2 < 0) {
               var2 = var6;
            } else if (var6 != var2) {
               return false;
            }
         }
      }

      return true;
   }

   public bsq.Av pC(bsq.Av var1) {
      if (!this.A.E()) {
         throw new IllegalArgumentException("Pattern is not compiled");
      } else if (this.wS != null) {
         return this.A(var1);
      } else if (this.UT != null) {
         return this.kS(var1);
      } else {
         throw new RuntimeException();
      }
   }

   public boolean pC(bsq.Av var1, boolean var2) {
      if (!var2 && this.A.E != null) {
         Boolean var3 = this.A.E.pC(this, var1);
         if (var3 != null) {
            if (!var3) {
               return false;
            }

            return true;
         }
      }

      bso.Av.Av var20 = this.A.wS();
      if (var20 == null) {
         throw new IllegalArgumentException("No replacement pattern provided");
      } else if (!this.A.pC() && !this.A.A()) {
         bso.Av.Av var21 = this.A.pC(var1.pC());
         int var22 = var21.E();
         int var6 = 0;
         long var7 = this.pC(var1, var21.UT());
         Couple var9 = this.wS.getInstructionLocation(var7);
         BasicBlock var10 = (BasicBlock)var9.getFirst();
         int var11 = (Integer)var9.getSecond();
         int var12 = var11 + var22;

         for (int var13 = var11; var13 < var12; var13++) {
            var6 += ((IDInstruction)var10.get(var13)).getSize();
         }

         int var23 = var20.A();
         if (var6 < var23) {
            return false;
         } else {
            ArrayList var14 = new ArrayList();

            for (int var15 = 0; var15 < var23; var15++) {
               bso.Av.Av.Av var16 = var20.pC(var15);
               IDExpression var17 = new bsk(this.kS, var16.kS).pC(var1.fI);
               IDExpression var18 = new bsk(this.kS, var16.A).pC(var1.fI);
               IDInstruction var19 = this.kS.createAssign(var18, var17);
               var14.add(var19);
            }

            int var24 = var14.size() - 1;
            ((IDInstruction)var14.get(var24)).setSize(var6 - var24);
            if (!this.wS.replaceInstructionsInBlock(var7, var22, var14)) {
               return false;
            } else {
               this.wS.invalidateDataFlowAnalysis();
               return true;
            }
         }
      } else {
         bsv var4 = ((bso.Av.Av.Av)var20.wS.get(0)).A;
         IDExpression var5 = new bsk(this.kS, var4).pC(var1.fI, bpl.A(var1.gp));
         if (var1.gp.getType() != null) {
            var5.setType(var1.gp.getType());
         }

         if (var1.oT == null) {
            var1.WR = var5;
            return true;
         } else if (var1.oT.replaceSubExpression(var1.gp, var5)) {
            var1.WR = var1.kS;
            return true;
         } else {
            return false;
         }
      }
   }

   public long pC(bsq.Av var1, int var2) {
      bso.Av.Av var3 = this.A.pC(var1.pC());
      if (var2 < 0) {
         var2 += var3.A();
      }

      Couple var4 = this.wS.getInstructionLocation(var1.A());
      int var5 = (Integer)var4.getSecond() + var2;
      return ((BasicBlock)var4.getFirst()).getAddressOfInstruction(var5);
   }

   public static class Av {
      private IDMethodContext pC;
      private CFG A;
      private IDExpression kS;
      private bso.Av wS;
      private int UT;
      private int E;
      private int sY;
      private int ys;
      private long ld;
      private IDExpression gp;
      private IDExpression oT;
      private Map fI;
      private IDExpression WR;

      Av(IDMethodContext var1, CFG var2, IDExpression var3, bso.Av var4, int var5, Map var6) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
         this.ys = var5;
         this.fI = var6;
      }

      public int pC() {
         return this.ys;
      }

      public long A() {
         return this.ld;
      }

      public Map kS() {
         return this.fI;
      }

      public Object pC(int var1, Class var2) {
         return this.fI.get(var1);
      }

      @Override
      public String toString() {
         return Strings.ff("Input #%d found at address 0x%X, matchMap: %s", this.ys, this.ld, this.fI);
      }
   }
}
