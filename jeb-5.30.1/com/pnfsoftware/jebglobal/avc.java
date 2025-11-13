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

public class avc {
   private static final ILogger q = GlobalLog.getLogger(avc.class);
   private CFG RF;
   private Map xK = new HashMap();

   public avc(CFG var1) {
      this.RF = var1;
   }

   public CFG q() {
      return this.RF;
   }

   public avc.eo q(long var1, IEVar var3) {
      Couple var4 = new Couple(var1, var3.getId());
      avc.eo var5 = (avc.eo)this.xK.get(var4);
      if (var5 == null) {
         var5 = new avc.eo(var1, var3);
         this.xK.put(var4, var5);
      }

      return var5;
   }

   public class eo {
      private long RF;
      private int xK;
      private int Dw;
      private Collection Uv;
      private boolean oW;
      private int gO;

      public eo(long var2, IEVar var4) {
         this.RF = var2;
         this.xK = var4.getId();
      }

      public long q() {
         return this.RF;
      }

      public int RF() {
         return this.xK;
      }

      public Collection xK() {
         if (this.Dw == 0) {
            this.Uv();
         }

         if (this.Dw == 1) {
            return null;
         } else if (this.Dw == 2) {
            return this.Uv;
         } else {
            throw new RuntimeException();
         }
      }

      public boolean Dw() {
         this.xK();
         return this.oW;
      }

      private void Uv() {
         if (this.Dw != 0) {
            throw new IllegalStateException();
         } else {
            this.Uv = new HashSet();
            this.oW = true;
            this.Dw = 1;
            IDFA var1 = avc.this.RF.doDataFlowAnalysis();
            Collection var2 = var1.getUseDefs(this.RF, this.xK);
            if (var2 != null) {
               for (long var4 : var2) {
                  if (var4 < 0L) {
                     this.oW = false;
                  } else {
                     IEStatement var6 = (IEStatement)avc.this.RF.getInstruction(var4);
                     if (!var6.isAssign()) {
                        this.oW = false;
                     } else {
                        IEGeneric var7 = ((IEAssign)var6).getDstOperand();
                        if (!var7.isVar(this.xK)) {
                           this.oW = false;
                        } else {
                           IEGeneric var8 = ((IEAssign)var6).getSrcOperand();
                           if (var8.isImm()) {
                              this.Uv.add(var8.asImm().getValue());
                           } else if (var8.isCond()) {
                              IECond var9 = var8.asCond();
                              IEGeneric var10 = var9.getExpressionTrue();
                              if (var10.isImm()) {
                                 this.Uv.add(var10.asImm().getValue());
                              } else {
                                 this.oW = false;
                              }

                              IEGeneric var11 = var9.getExpressionFalse();
                              if (var11.isImm()) {
                                 this.Uv.add(var11.asImm().getValue());
                              } else {
                                 this.oW = false;
                              }
                           } else if (var8.isVar()) {
                              this.q(var8.asVar(), var4);
                           } else {
                              this.oW = false;
                           }
                        }
                     }
                  }
               }
            }

            this.Dw = 2;
         }
      }

      void q(IEVar var1, long var2) {
         if (this.gO >= 5) {
            this.oW = false;
         } else {
            avc.eo var4 = avc.this.q(var2, var1);
            var4.gO = this.gO + 1;
            Collection var5 = var4.xK();
            if (var5 == null) {
               this.oW = false;
            } else {
               if (!var4.Dw()) {
                  this.oW = false;
               }

               this.Uv.addAll(var5);
            }
         }
      }
   }
}
