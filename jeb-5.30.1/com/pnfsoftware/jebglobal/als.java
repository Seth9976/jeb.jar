package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGroup;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Ser
public class als extends ane implements IEGroup {
   @SerId(1)
   private int q;
   @SerId(2)
   private String RF;
   @SerId(3)
   private int xK;
   @SerId(4)
   private IEGeneric[] Dw;

   public als(String var1, int var2, IEGeneric[] var3) {
      if (var2 > 0 && var3 != null) {
         this.q = 0;
         this.RF = var1;
         this.xK = var2;

         for (IEGeneric var7 : var3) {
            if (var7 == null) {
               throw new NullPointerException("Null group element");
            }

            if (var7.getBitsize() != var2) {
               throw new IllegalArgumentException("Unexpected bitsize for group element: " + var7.getBitsize());
            }
         }

         this.Dw = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + this.xK;
      var1 = 31 * var1 + Arrays.hashCode((Object[])this.Dw);
      var1 = 31 * var1 + this.q;
      return 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
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
         als var3 = (als)var1;
         if (this.xK != var3.xK) {
            return false;
         } else if (!q(this.Dw, var3.Dw, var2)) {
            return false;
         } else if (this.q != var3.q) {
            return false;
         } else {
            if (this.RF == null) {
               if (var3.RF != null) {
                  return false;
               }
            } else if (!this.RF.equals(var3.RF)) {
               return false;
            }

            return true;
         }
      }
   }

   public int q() {
      return this.q;
   }

   @Override
   public String getName() {
      return this.RF;
   }

   @Override
   public int getBitsize() {
      return this.xK * this.Dw.length;
   }

   @Override
   public int getElementsCount() {
      return this.Dw.length;
   }

   @Override
   public int getElementBitsize() {
      return this.xK;
   }

   IEGeneric[] xK() {
      return this.Dw;
   }

   @Override
   public List getElements() {
      return ArrayUtil.asView(this.Dw);
   }

   @Override
   public IEGeneric getElement(int var1) {
      return this.Dw[var1];
   }

   @Override
   public int getPriority() {
      return 0;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      for (IEGeneric var5 : this.Dw) {
         var5.getUsed(var1);
      }
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      for (IEGeneric var5 : this.Dw) {
         var5.getDefinedOrUsedAsDestination(var1);
      }
   }

   @Override
   public boolean accessesMemory() {
      for (IEGeneric var4 : this.Dw) {
         if (var4.accessesMemory()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      return 0;
   }

   @Override
   public void collectSubExpressions(Collection var1) {
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      return false;
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
   }

   public als Dw() {
      ane[] var1 = new ane[this.Dw.length];
      int var2 = 0;

      for (IEGeneric var6 : this.Dw) {
         var1[var2++] = var6.duplicate();
      }

      return (als)this.q(new als(this.RF, this.xK, var1));
   }

   @Override
   public IEImm evaluate(EState var1) {
      throw new RuntimeException("Cannot be evaluated");
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      IWildcardType var3 = this.getSafeType(var1.getWildcardTypeManager());
      ICType var4 = var2.getTypeFactory().create(var3);
      return var2.getIdentifierManager().create(this.q, var4, this.RF, CIdentifierClass.SPECIAL, -1, null, null).getIdentifier();
   }

   @Override
   public void q(and var1) {
      String var2 = Strings.ff("g%d:%s", this.xK, this.RF);
      var1.append(var2, ItemClassIdentifiers.ARTIFACT);
   }
}
