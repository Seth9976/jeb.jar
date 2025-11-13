package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESlice;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;

public class ano {
   private static final StructuredLogger q = aeg.q(ano.class);
   private IEGeneric RF;
   private int xK;
   private IEVar Dw;
   private int Uv;
   private IdentityHashMap oW = new IdentityHashMap();
   private IEImm gO;

   public static IEVar q(IEGeneric var0) {
      ano var1 = new ano(var0);
      if (!var1.q()) {
         return null;
      } else {
         return !var1.Uv() ? null : var1.xK();
      }
   }

   public ano(IEGeneric var1, IEVar var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.RF = var1;
         this.Dw = var2;
      }
   }

   public ano(IEGeneric var1) {
      this(var1, null);
   }

   public boolean q() {
      List var1 = this.gO();
      return var1 == null ? false : this.RF(var1);
   }

   private List gO() {
      ArrayList var1 = new ArrayList();
      return !this.q(var1) ? null : var1;
   }

   private boolean q(List var1) {
      return this.q(this.RF, var1);
   }

   private boolean q(IEGeneric var1, List var2) {
      if (var1 instanceof IEVar || var1 instanceof IEImm) {
         var2.add(new ano.eo(var1));
         return true;
      } else if (var1 instanceof IEMem || var1 instanceof IECompose || var1 instanceof IESlice) {
         var2.add(new ano.eo(var1));
         return true;
      } else if (!(var1 instanceof IEOperation var3)) {
         Object[] var10000 = new Object[]{var1};
         return false;
      } else {
         OperationType var4 = var3.getOperationType();
         if (var4 == OperationType.ADD) {
            IEGeneric var9 = var3.getOperand1();
            IEGeneric var11 = var3.getOperand2();
            return this.q(var9, var2) && this.q(var11, var2);
         } else if (var4 == OperationType.SUB) {
            IEGeneric var8 = var3.getOperand1();
            IEGeneric var10 = var3.getOperand2();
            return !(var10 instanceof IEImm) ? false : this.q(var8, var2) && this.q(((IEImm)var10)._neg(), var2);
         } else {
            if (var4 == OperationType.MUL) {
               IEGeneric var5 = var3.getOperand1();
               IEGeneric var6 = var3.getOperand2();
               if (var5 instanceof IEImm) {
                  if (var6 instanceof IEImm) {
                     IEImm var12 = EUtil.evaluate_preVerified(var3);
                     var2.add(new ano.eo(var12));
                  } else {
                     var2.add(new ano.eo(var6, (IEImm)var5));
                  }

                  return true;
               }

               if (var6 instanceof IEImm) {
                  if (var5 instanceof IEImm) {
                     IEImm var7 = EUtil.evaluate_preVerified(var3);
                     var2.add(new ano.eo(var7));
                  } else {
                     var2.add(new ano.eo(var5, (IEImm)var6));
                  }

                  return true;
               }
            }

            return false;
         }
      }
   }

   private boolean RF(List var1) {
      this.xK = ((ano.eo)var1.get(0)).q.getBitsize();

      for (ano.eo var3 : var1) {
         if (var3.q instanceof IEImm) {
            Assert.a(var3.RF == null);
            IEImm var4 = (IEImm)var3.q;
            if (this.gO == null) {
               this.gO = var4;
            } else {
               this.gO = EUtil.evaluate_preVerified(EUtil.add(this.gO, var4));
            }
         } else {
            long var9;
            if (var3.RF == null) {
               var9 = 1L;
            } else {
               if (!var3.RF.canReadAsLong()) {
                  return false;
               }

               var9 = var3.RF.getValueAsLong();
            }

            Long var6 = (Long)this.oW.get(var3.q);
            if (var6 == null) {
               this.oW.put(var3.q, var9);
            } else {
               this.oW.put(var3.q, var6 + var9);
            }
         }
      }

      if (this.Dw != null && !Long.valueOf(1L).equals(this.oW.get(this.Dw))) {
         return false;
      } else if (this.gO != null && !this.gO.canReadAsLong()) {
         Assert.a(this.xK == this.gO.getBitsize());
         return false;
      } else {
         for (IEGeneric var8 : this.oW.keySet()) {
            if ((Long)this.oW.get(var8) == 0L) {
               return false;
            }
         }

         this.Uv = 1;
         return true;
      }
   }

   private void nf() {
      if (this.Uv != 1) {
         throw new IllegalStateException("The expression was not normalized");
      }
   }

   public IEGeneric RF() {
      return this.RF;
   }

   public IEVar xK() {
      this.nf();
      return this.Dw;
   }

   public List Dw() {
      this.nf();
      ArrayList var1 = null;

      for (IEGeneric var3 : this.oW.keySet()) {
         if ((var3 instanceof IEImm || var3 instanceof IEVar) && Long.valueOf(1L).equals(this.oW.get(var3))) {
            if (var1 == null) {
               var1 = new ArrayList(1);
            }

            var1.add(var3);
         }
      }

      return (List)(var1 == null ? Collections.emptyList() : var1);
   }

   public boolean Uv() {
      this.nf();
      if (this.Dw != null) {
         throw new IllegalStateException("A base is already set: " + this.Dw);
      } else {
         List var1 = this.Dw();
         if (var1.isEmpty()) {
            return false;
         } else if (var1.size() == 1) {
            if (!(var1.get(0) instanceof IEVar)) {
               return false;
            } else {
               IEVar var6 = (IEVar)var1.get(0);
               if (!var6.isReference() && var6.getType() != null && !var6.getType().isPointer()) {
                  return false;
               } else {
                  this.Dw = var6;
                  return true;
               }
            }
         } else {
            IEVar var2 = null;

            for (IEGeneric var4 : var1) {
               if (!(var4 instanceof IEVar var5)) {
                  return false;
               }

               if (var5.isReference() || var5.getType() != null && var5.getType().isPointer()) {
                  if (var2 != null) {
                     return false;
                  }

                  var2 = var5;
               }
            }

            if (var2 == null) {
               for (IEGeneric var8 : var1) {
                  IEVar var9 = (IEVar)var8;
                  if (var9.getType() == null) {
                     if (var2 != null) {
                        return false;
                     }

                     var2 = var9;
                  }
               }

               if (var2 == null) {
                  return false;
               }
            }

            this.Dw = var2;
            return true;
         }
      }
   }

   public long oW() {
      this.nf();
      return this.gO == null ? 0L : this.gO.getValueAsLong();
   }

   public long RF(IEGeneric var1) {
      Long var2 = (Long)this.oW.get(var1);
      return var2 == null ? 0L : var2;
   }

   public void q(IEVar var1, Long var2) {
      this.nf();
      if (var1 == null) {
         throw new NullPointerException();
      } else if (this.oW.containsKey(var1)) {
         throw new IllegalArgumentException("The new symbol-base is already part of the expression!");
      } else {
         if (this.Dw != null) {
            this.oW.remove(this.Dw);
         }

         this.oW.put(var1, 1L);
         if (var2 != null) {
            if (var2 == 0L) {
               this.gO = null;
            } else {
               this.gO = alu.q(var2, this.xK);
            }
         }
      }
   }

   public IEGeneric q(IERoutineContext var1) {
      return this.q(var1, true);
   }

   public IEGeneric q(IERoutineContext var1, boolean var2) {
      this.nf();
      Object var3 = this.oW.keySet();
      if (var2) {
         ArrayList var4 = new ArrayList(this.oW.keySet());
         var4.sort(new amh(var1));
         var3 = var4;
      }

      Object var10 = null;

      for (IEGeneric var6 : var3) {
         long var7 = (Long)this.oW.get(var6);
         Assert.a(var7 >= 1L, "Illegal count: " + var7);
         Object var9;
         if (var7 == 1L) {
            var9 = var6;
         } else {
            var9 = EUtil.mul(var6, alu.q(var7, var6.getBitsize()));
         }

         if (var10 == null) {
            var10 = var9;
         } else {
            var10 = EUtil.add((IEGeneric)var10, (IEGeneric)var9);
         }
      }

      if (this.gO != null) {
         var10 = EUtil.add((IEGeneric)var10, this.gO);
      }

      return (IEGeneric)var10;
   }

   @Override
   public String toString() {
      return Strings.ff("Parsed:\"%s\"", this.RF());
   }

   private static class eo {
      private IEGeneric q;
      private IEImm RF;

      public eo(IEGeneric var1) {
         this(var1, null);
      }

      public eo(IEGeneric var1, IEImm var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public String toString() {
         return Strings.ff("%s x \"%s\"", this.RF, this.q);
      }
   }
}
