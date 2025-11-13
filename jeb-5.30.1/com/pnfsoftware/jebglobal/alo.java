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
public class alo extends ane implements IECompose {
   @SerId(1)
   IEGeneric[] q;
   @SerId(2)
   private int RF;

   public alo(IEGeneric... var1) {
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

         this.q = var1;
      }
   }

   public alo(Collection var1) {
      this((IEGeneric[])var1.toArray(new ane[var1.size()]));
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      return 31 * var1 + Arrays.hashCode((Object[])this.q);
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
         alo var3 = (alo)var1;
         return q(this.q, var3.q, var2);
      }
   }

   @Override
   public int getPartsCount() {
      return this.q.length;
   }

   IEGeneric[] q() {
      return this.q;
   }

   @Override
   public List getParts() {
      return ArrayUtil.asView(this.q);
   }

   @Override
   public IEGeneric getPart(int var1) {
      return this.q[var1];
   }

   @Override
   public IEGeneric getLowPart() {
      return this.q[0];
   }

   @Override
   public IEGeneric getHighPart() {
      return this.q[this.q.length - 1];
   }

   @Override
   public synchronized int getBitsize() {
      if (this.RF == 0) {
         for (IEGeneric var4 : this.q) {
            this.RF = this.RF + var4.getBitsize();
         }
      }

      return this.RF;
   }

   @Override
   public int getPriority() {
      return 30;
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      for (IEGeneric var5 : this.q) {
         var5.getDefinedOrUsedAsDestination(var1);
      }
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      for (IEGeneric var5 : this.q) {
         var5.getUsed(var1);
      }
   }

   @Override
   public boolean accessesMemory() {
      for (IEGeneric var4 : this.q) {
         if (var4.accessesMemory()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      int var3 = 0;

      for (int var4 = 0; var4 < this.q.length; var4++) {
         IEGeneric var5 = this.q[var4];
         if (var5 == var1) {
            RF(var5, var2);
            this.q[var4] = var2.duplicate();
            var3++;
         } else {
            var3 += var5.replaceVar(var1, var2);
         }
      }

      return var3;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      for (IEGeneric var5 : this.q) {
         var1.add(var5);
      }
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      q(var1, var2);

      for (int var3 = 0; var3 < this.q.length; var3++) {
         if (this.q[var3] == var1) {
            RF(this.q[var3], var2);
            this.q[var3] = var2;
            return true;
         }
      }

      return false;
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      for (IEGeneric var5 : this.q) {
         var5.updateTypes(var1);
      }
   }

   @Override
   public void replacePart(int var1, IEGeneric var2) {
      q(var2);
      RF(this.q[var1], var2);
      this.q[var1] = var2;
   }

   public alo xK() {
      IEGeneric[] var1 = new IEGeneric[this.q.length];
      int var2 = 0;

      for (IEGeneric var6 : this.q) {
         var1[var2++] = var6.duplicate();
      }

      return (alo)this.q(new alo(var1));
   }

   @Override
   public IEImm evaluate(EState var1) {
      int var2 = this.getBitsize();
      IEGeneric var3 = this.q[0];
      IEImm var4 = var3.evaluate(var1);
      if (var4 == null) {
         return null;
      } else {
         var4 = var4.expand(var2);
         int var5 = var3.getBitsize();

         for (int var6 = 1; var6 < this.q.length; var6++) {
            var3 = this.q[var6];
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
      IEVar var3 = new anu(this).q(var1, var2);
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

         for (IEGeneric var16 : this.q) {
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
   public void q(and var1) {
      var1.appendKeyword("C");
      var1.paren();
      int var2 = 0;

      for (IEGeneric var6 : this.q) {
         if (var2++ >= 1) {
            var1.append(", ");
         }

         var1.q(var6);
      }

      var1.parenClose();
      var1.RF(this);
   }

   @Override
   public IEGeneric rightShift(int var1, int var2) {
      ArrayList var3 = new ArrayList();
      int var4 = 0;

      for (IEGeneric var8 : this.q) {
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
         var3.add(alu.q(0L, var1));
         return new alo(var3).zeroExtend(var2);
      }
   }

   @Override
   public Iterator iterator() {
      return new alo.eo();
   }

   private class eo implements Iterator {
      private int RF = 0;

      @Override
      public boolean hasNext() {
         return this.RF < alo.this.q.length;
      }

      public IEGeneric q() {
         return alo.this.q[this.RF++];
      }
   }
}
