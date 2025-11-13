package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class asl {
   private static final ILogger pC = GlobalLog.getLogger(asl.class);
   private CFG A;
   private Map kS = new HashMap();

   public asl(CFG var1) {
      this.A = var1;
   }

   public asl.Av pC(long var1, IEVar var3) {
      Couple var4 = new Couple(var1, var3.getId());
      asl.Av var5 = (asl.Av)this.kS.get(var4);
      if (var5 == null) {
         var5 = new asl.Av(var1, var3);
         this.kS.put(var4, var5);
      }

      return var5;
   }

   public class Av {
      private long A;
      private int kS;
      private int wS;
      private Collection UT;
      private boolean E;
      private int sY;

      public Av(long var2, IEVar var4) {
         this.A = var2;
         this.kS = var4.getId();
      }

      public Collection pC() {
         if (this.wS == 0) {
            this.kS();
         }

         if (this.wS == 1) {
            return null;
         } else if (this.wS == 2) {
            return this.UT;
         } else {
            throw new RuntimeException();
         }
      }

      public boolean A() {
         this.pC();
         return this.E;
      }

      private void kS() {
         if (this.wS != 0) {
            throw new IllegalStateException();
         } else {
            this.UT = new HashSet();
            this.E = true;
            this.wS = 1;
            IDFA var1 = asl.this.A.doDataFlowAnalysis();
            Collection var2 = var1.getUseDefs(this.A, this.kS);
            if (var2 != null) {
               for (long var4 : var2) {
                  if (var4 < 0L) {
                     this.E = false;
                  } else {
                     IEStatement var6 = (IEStatement)asl.this.A.getInstruction(var4);
                     if (!var6.isAssign()) {
                        this.E = false;
                     } else {
                        IEGeneric var7 = ((IEAssign)var6).getDstOperand();
                        if (!var7.isVar(this.kS)) {
                           this.E = false;
                        } else {
                           IEGeneric var8 = ((IEAssign)var6).getSrcOperand();
                           if (var8.isImm()) {
                              this.UT.add(var8.asImm().getValue());
                           } else if (var8.isCond()) {
                              IECond var9 = var8.asCond();
                              IEGeneric var10 = var9.getExpressionTrue();
                              if (var10.isImm()) {
                                 this.UT.add(var10.asImm().getValue());
                              } else {
                                 this.E = false;
                              }

                              IEGeneric var11 = var9.getExpressionFalse();
                              if (var11.isImm()) {
                                 this.UT.add(var11.asImm().getValue());
                              } else {
                                 this.E = false;
                              }
                           } else if (var8.isVar()) {
                              this.pC(var8.asVar(), var4);
                           } else {
                              this.E = false;
                           }
                        }
                     }
                  }
               }
            }

            this.wS = 2;
         }
      }

      void pC(IEVar var1, long var2) {
         if (this.sY >= 5) {
            this.E = false;
         } else {
            asl.Av var4 = asl.this.pC(var2, var1);
            var4.sY = this.sY + 1;
            Collection var5 = var4.pC();
            if (var5 == null) {
               this.E = false;
            } else {
               if (!var4.A()) {
                  this.E = false;
               }

               this.UT.addAll(var5);
            }
         }
      }
   }
}
