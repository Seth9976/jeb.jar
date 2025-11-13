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

public class alj {
   private static final StructuredLogger pC = aco.pC(alj.class);
   private IEGeneric A;
   private int kS;
   private IEVar wS;
   private int UT;
   private IdentityHashMap E = new IdentityHashMap();
   private IEImm sY;

   public static IEVar pC(IEGeneric var0) {
      alj var1 = new alj(var0);
      if (!var1.pC()) {
         return null;
      } else {
         return !var1.UT() ? null : var1.kS();
      }
   }

   public alj(IEGeneric var1, IEVar var2) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.A = var1;
         this.wS = var2;
      }
   }

   public alj(IEGeneric var1) {
      this(var1, null);
   }

   public boolean pC() {
      List var1 = this.sY();
      return var1 == null ? false : this.A(var1);
   }

   private List sY() {
      ArrayList var1 = new ArrayList();
      return !this.pC(var1) ? null : var1;
   }

   private boolean pC(List var1) {
      return this.pC(this.A, var1);
   }

   private boolean pC(IEGeneric var1, List var2) {
      if (var1 instanceof IEVar || var1 instanceof IEImm) {
         var2.add(new alj.Av(var1));
         return true;
      } else if (var1 instanceof IEMem || var1 instanceof IECompose || var1 instanceof IESlice) {
         var2.add(new alj.Av(var1));
         return true;
      } else if (!(var1 instanceof IEOperation var3)) {
         Object[] var10000 = new Object[]{var1};
         return false;
      } else {
         OperationType var4 = var3.getOperationType();
         if (var4 == OperationType.ADD) {
            IEGeneric var11 = var3.getOperand1();
            IEGeneric var13 = var3.getOperand2();
            return this.pC(var11, var2) && this.pC(var13, var2);
         } else if (var4 == OperationType.SUB) {
            IEGeneric var10 = var3.getOperand1();
            return !(var3.getOperand2() instanceof IEImm var14) ? false : this.pC(var10, var2) && this.pC(var14._neg(), var2);
         } else {
            if (var4 == OperationType.MUL) {
               IEGeneric var5 = var3.getOperand1();
               IEGeneric var6 = var3.getOperand2();
               if (var5 instanceof IEImm var7) {
                  if (var6 instanceof IEImm) {
                     IEImm var15 = EUtil.evaluate_preVerified(var3);
                     var2.add(new alj.Av(var15));
                  } else {
                     var2.add(new alj.Av(var6, var7));
                  }

                  return true;
               }

               if (var6 instanceof IEImm var8) {
                  if (var5 instanceof IEImm) {
                     IEImm var9 = EUtil.evaluate_preVerified(var3);
                     var2.add(new alj.Av(var9));
                  } else {
                     var2.add(new alj.Av(var5, var8));
                  }

                  return true;
               }
            }

            return false;
         }
      }
   }

   private boolean A(List var1) {
      this.kS = ((alj.Av)var1.get(0)).pC.getBitsize();

      for (alj.Av var3 : var1) {
         if (var3.pC instanceof IEImm var4) {
            Assert.a(var3.A == null);
            if (this.sY == null) {
               this.sY = var4;
            } else {
               this.sY = EUtil.evaluate_preVerified(EUtil.add(this.sY, var4));
            }
         } else {
            long var10;
            if (var3.A == null) {
               var10 = 1L;
            } else {
               if (!var3.A.canReadAsLong()) {
                  return false;
               }

               var10 = var3.A.getValueAsLong();
            }

            Long var7 = (Long)this.E.get(var3.pC);
            if (var7 == null) {
               this.E.put(var3.pC, var10);
            } else {
               this.E.put(var3.pC, var7 + var10);
            }
         }
      }

      if (this.wS != null && !Long.valueOf(1L).equals(this.E.get(this.wS))) {
         return false;
      } else if (this.sY != null && !this.sY.canReadAsLong()) {
         Assert.a(this.kS == this.sY.getBitsize());
         return false;
      } else {
         for (IEGeneric var9 : this.E.keySet()) {
            if ((Long)this.E.get(var9) == 0L) {
               return false;
            }
         }

         this.UT = 1;
         return true;
      }
   }

   private void ys() {
      if (this.UT != 1) {
         throw new IllegalStateException("The expression was not normalized");
      }
   }

   public IEGeneric A() {
      return this.A;
   }

   public IEVar kS() {
      this.ys();
      return this.wS;
   }

   public List wS() {
      this.ys();
      ArrayList var1 = null;

      for (IEGeneric var3 : this.E.keySet()) {
         if ((var3 instanceof IEImm || var3 instanceof IEVar) && Long.valueOf(1L).equals(this.E.get(var3))) {
            if (var1 == null) {
               var1 = new ArrayList(1);
            }

            var1.add(var3);
         }
      }

      return (List)(var1 == null ? Collections.emptyList() : var1);
   }

   public boolean UT() {
      this.ys();
      if (this.wS != null) {
         throw new IllegalStateException("A base is already set: " + this.wS);
      } else {
         List var1 = this.wS();
         if (var1.isEmpty()) {
            return false;
         } else if (var1.size() == 1) {
            if (var1.get(0) instanceof IEVar var6) {
               if (!var6.isReference() && var6.getType() != null && !var6.getType().isPointer()) {
                  return false;
               } else {
                  this.wS = var6;
                  return true;
               }
            } else {
               return false;
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
               for (IEGeneric var9 : var1) {
                  IEVar var10 = (IEVar)var9;
                  if (var10.getType() == null) {
                     if (var2 != null) {
                        return false;
                     }

                     var2 = var10;
                  }
               }

               if (var2 == null) {
                  return false;
               }
            }

            this.wS = var2;
            return true;
         }
      }
   }

   public long E() {
      this.ys();
      return this.sY == null ? 0L : this.sY.getValueAsLong();
   }

   public void pC(IEVar var1, Long var2) {
      this.ys();
      if (var1 == null) {
         throw new NullPointerException();
      } else if (this.E.containsKey(var1)) {
         throw new IllegalArgumentException("The new symbol-base is already part of the expression!");
      } else {
         if (this.wS != null) {
            this.E.remove(this.wS);
         }

         this.E.put(var1, 1L);
         if (var2 != null) {
            if (var2 == 0L) {
               this.sY = null;
            } else {
               this.sY = ajr.pC(var2, this.kS);
            }
         }
      }
   }

   public IEGeneric pC(IERoutineContext var1) {
      return this.pC(var1, true);
   }

   public IEGeneric pC(IERoutineContext var1, boolean var2) {
      this.ys();
      Object var3 = this.E.keySet();
      if (var2) {
         ArrayList var4 = new ArrayList(this.E.keySet());
         var4.sort(new ake(var1));
         var3 = var4;
      }

      Object var10 = null;

      for (IEGeneric var6 : var3) {
         long var7 = (Long)this.E.get(var6);
         Assert.a(var7 >= 1L, "Illegal count: " + var7);
         Object var9;
         if (var7 == 1L) {
            var9 = var6;
         } else {
            var9 = EUtil.mul(var6, ajr.pC(var7, var6.getBitsize()));
         }

         if (var10 == null) {
            var10 = var9;
         } else {
            var10 = EUtil.add((IEGeneric)var10, (IEGeneric)var9);
         }
      }

      if (this.sY != null) {
         var10 = EUtil.add((IEGeneric)var10, this.sY);
      }

      return (IEGeneric)var10;
   }

   @Override
   public String toString() {
      return Strings.ff("Parsed:\"%s\"", this.A());
   }

   private static class Av {
      private IEGeneric pC;
      private IEImm A;

      public Av(IEGeneric var1) {
         this(var1, null);
      }

      public Av(IEGeneric var1, IEImm var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public String toString() {
         return Strings.ff("%s x \"%s\"", this.A, this.pC);
      }
   }
}
