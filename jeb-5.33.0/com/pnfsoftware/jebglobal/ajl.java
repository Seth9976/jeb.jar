package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperator;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperatorFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Ser
public class ajl extends ala implements IECompose {
   @SerId(1)
   IEGeneric[] pC;
   @SerId(2)
   private int A;

   public ajl(IEGeneric... var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else if (var1.length < 2) {
         throw new IllegalArgumentException("A composition requires at least two parts");
      } else {
         for (IEGeneric var5 : var1) {
            if (var5 == null) {
               throw new NullPointerException("Null part in composition is not allowed");
            }
         }

         this.pC = var1;
      }
   }

   public ajl(Collection var1) {
      this((IEGeneric[])var1.toArray(new ala[var1.size()]));
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + Arrays.hashCode((Object[])this.pC);
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         ajl var3 = (ajl)var1;
         return pC(this.pC, var3.pC, var2);
      }
   }

   @Override
   public int getPartsCount() {
      return this.pC.length;
   }

   IEGeneric[] pC() {
      return this.pC;
   }

   @Override
   public List getParts() {
      return ArrayUtil.asView(this.pC);
   }

   @Override
   public IEGeneric getPart(int var1) {
      return this.pC[var1];
   }

   @Override
   public IEGeneric getLowPart() {
      return this.pC[0];
   }

   @Override
   public IEGeneric getHighPart() {
      return this.pC[this.pC.length - 1];
   }

   @Override
   public synchronized int getBitsize() {
      if (this.A == 0) {
         for (IEGeneric var4 : this.pC) {
            this.A = this.A + var4.getBitsize();
         }
      }

      return this.A;
   }

   @Override
   public int getPriority() {
      return 30;
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      for (IEGeneric var5 : this.pC) {
         var5.getDefinedOrUsedAsDestination(var1);
      }
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      for (IEGeneric var5 : this.pC) {
         var5.getUsed(var1);
      }
   }

   @Override
   public boolean accessesMemory() {
      for (IEGeneric var4 : this.pC) {
         if (var4.accessesMemory()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;

      for (int var4 = 0; var4 < this.pC.length; var4++) {
         IEGeneric var5 = this.pC[var4];
         if (var5 == var1) {
            A(var5, var2);
            this.pC[var4] = var2.duplicate();
            var3++;
         } else {
            var3 += var5.replaceVar(var1, var2);
         }
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      for (IEGeneric var5 : this.pC) {
         var1.add(var5);
      }
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      pC(var1, var2);

      for (int var3 = 0; var3 < this.pC.length; var3++) {
         if (this.pC[var3] == var1) {
            A(this.pC[var3], var2);
            this.pC[var3] = var2;
            return true;
         }
      }

      return false;
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      for (IEGeneric var5 : this.pC) {
         var5.updateTypes(var1);
      }
   }

   @Override
   public void replacePart(int var1, IEGeneric var2) {
      pC(var2);
      A(this.pC[var1], var2);
      this.pC[var1] = var2;
   }

   public ajl kS() {
      IEGeneric[] var1 = new IEGeneric[this.pC.length];
      int var2 = 0;

      for (IEGeneric var6 : this.pC) {
         var1[var2++] = var6.duplicate();
      }

      return (ajl)this.pC(new ajl(var1));
   }

   @Override
   public IEImm evaluate(EState var1) {
      int var2 = this.getBitsize();
      IEGeneric var3 = this.pC[0];
      IEImm var4 = var3.evaluate(var1);
      if (var4 == null) {
         return null;
      } else {
         var4 = var4.expand(var2);
         int var5 = var3.getBitsize();

         for (int var6 = 1; var6 < this.pC.length; var6++) {
            var3 = this.pC[var6];
            IEImm var7 = var3.evaluate(var1);
            if (var7 == null) {
               return null;
            }

            var7 = var7.expand(var2);
            var4 = var7._shl(var5)._or(var4);
            var5 += var3.getBitsize();
         }

         return var4;
      }
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      IEVar var3 = new alp(this).pC(var1, var2);
      if (var3 != null) {
         return var3.generateC(var1, var2);
      } else {
         ICTypeFactory var4 = var2.getTypeFactory();
         ICOperatorFactory var5 = var2.getOperatorFactory();
         ICConstantFactory var6 = var2.getConstantFactory();
         ICElementFactory var7 = var2.getElementFactory();
         IWildcardTypeManager var9 = var1.getWildcardTypeManager();
         IWildcardType var10 = var9.createWithMaximumBitsize(this.getBitsize()).updateGroup(IWildcardType.Group.UINT);
         ICOperator var8 = var5.createCastOperator(var4.create(var10));
         ICOperation var11 = null;
         int var12 = 0;

         for (IEGeneric var16 : this.pC) {
            ICExpression var17 = (ICExpression)var16.generateC(var1, var2);
            ICOperation var18 = var7.createOperation(var8, var17);
            if (var12 != 0) {
               var18 = var7.createOperation(COperatorType.SHL, var18, var6.createInt32(var12));
            }

            if (var11 != null) {
               var11 = var7.createOperation(COperatorType.OR, var11, var18);
            } else {
               var11 = var18;
            }

            var12 += var16.getBitsize();
         }

         return var11;
      }
   }

   @Override
   public void pC(akz var1) {
      var1.appendKeyword("C");
      var1.paren();
      int var2 = 0;

      for (IEGeneric var6 : this.pC) {
         if (var2++ >= 1) {
            var1.append(", ");
         }

         var1.pC(var6);
      }

      var1.parenClose();
      var1.A(this);
   }

   @Override
   public IEGeneric rightShift(int var1, int var2) {
      ArrayList var3 = new ArrayList();
      int var4 = 0;

      for (IEGeneric var8 : this.pC) {
         if (var8.getBitsize() <= var1 - var4) {
            var4 += var8.getBitsize();
         } else if (var4 == var1) {
            var3.add(var8);
         } else {
            var3.add(var8.slice(var1 - var4, var8.getBitsize()));
            var4 = var1;
         }
      }

      if (var3.isEmpty()) {
         return EUtil.zero(var2);
      } else {
         var3.add(ajr.pC(0L, var1));
         return new ajl(var3).zeroExtend(var2);
      }
   }

   @Override
   public Iterator iterator() {
      return new ajl.Av();
   }

   private class Av implements Iterator {
      private int A = 0;

      @Override
      public boolean hasNext() {
         return this.A < ajl.this.pC.length;
      }

      public IEGeneric pC() {
         return ajl.this.pC[this.A++];
      }
   }
}
