package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IDFA;
import com.pnfsoftware.jeb.core.units.code.android.controlflow.CFG;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class bwo {
   private static final ILogger q = GlobalLog.getLogger(bwo.class);
   private CFG RF;
   private Map xK = new HashMap();

   public bwo(CFG var1) {
      this.RF = var1;
   }

   public CFG q() {
      return this.RF;
   }

   public bwo.eo q(long var1, int var3) {
      Couple var4 = new Couple(var1, var3);
      bwo.eo var5 = (bwo.eo)this.xK.get(var4);
      if (var5 == null) {
         var5 = new bwo.eo(var1, var3);
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

      public eo(long var2, int var4) {
         this.RF = var2;
         this.xK = var4;
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
            IDFA var1 = bwo.this.RF.doDataFlowAnalysis();
            Collection var2 = var1.getUseDefs(this.RF, this.xK);
            if (var2 != null) {
               for (long var4 : var2) {
                  if (var4 < 0L) {
                     if (var4 == -1L) {
                        Object[] var10000 = new Object[]{var4, this.xK};
                     }

                     this.oW = false;
                  } else {
                     IDInstruction var6 = (IDInstruction)bwo.this.RF.getInstruction(var4);
                     if (!var6.isAssign()) {
                        Object[] var16 = new Object[]{var4, this.xK};
                        this.oW = false;
                     } else {
                        IDExpression var7 = var6.getAssignDestination();
                        if (!var7.isVar(this.xK)) {
                           Object[] var17 = new Object[]{var4, this.xK};
                           this.oW = false;
                        } else {
                           IDExpression var8 = var6.getAssignSource();
                           if (var8 instanceof IDImm var9) {
                              this.Uv.add(var9);
                           } else if (var8 instanceof IDOperation var10 && var10.isConditional()) {
                              if (var10.getLeft() instanceof IDImm var13) {
                                 this.Uv.add(var13);
                              } else {
                                 Object[] var19 = new Object[]{var4, this.xK};
                                 this.oW = false;
                              }

                              if (var10.getRight() instanceof IDImm var14) {
                                 this.Uv.add(var14);
                              } else {
                                 Object[] var20 = new Object[]{var4, this.xK};
                                 this.oW = false;
                              }
                           } else if (var8 instanceof IDVar var11) {
                              this.q(var11, var4);
                           } else {
                              Object[] var18 = new Object[]{var4, this.xK};
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

      void q(IDVar var1, long var2) {
         if (this.gO >= 5) {
            Object[] var10000 = new Object[]{var2};
            this.oW = false;
         } else {
            bwo.eo var4 = bwo.this.q(var2, var1.getId());
            if (var4.Dw != 1) {
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

      @Override
      public String toString() {
         return Strings.ff("Collector: addr=0x%X, varid=%d", this.RF, this.xK);
      }
   }
}
