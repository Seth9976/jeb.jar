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
public class ajp extends ala implements IEGroup {
   @SerId(1)
   private int pC;
   @SerId(2)
   private String A;
   @SerId(3)
   private int kS;
   @SerId(4)
   private IEGeneric[] wS;

   public ajp(String var1, int var2, IEGeneric[] var3) {
      if (var2 > 0 && var3 != null) {
         this.pC = 0;
         this.A = var1;
         this.kS = var2;

         for (IEGeneric var7 : var3) {
            if (var7 == null) {
               throw new NullPointerException("Null group element");
            }

            if (var7.getBitsize() != var2) {
               throw new IllegalArgumentException("Unexpected bitsize for group element: " + var7.getBitsize());
            }
         }

         this.wS = var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + this.kS;
      var1 = 31 * var1 + Arrays.hashCode((Object[])this.wS);
      var1 = 31 * var1 + this.pC;
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
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
         ajp var3 = (ajp)var1;
         if (this.kS != var3.kS) {
            return false;
         } else if (!pC(this.wS, var3.wS, var2)) {
            return false;
         } else if (this.pC != var3.pC) {
            return false;
         } else {
            if (this.A == null) {
               if (var3.A != null) {
                  return false;
               }
            } else if (!this.A.equals(var3.A)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public String getName() {
      return this.A;
   }

   @Override
   public int getBitsize() {
      return this.kS * this.wS.length;
   }

   @Override
   public int getElementsCount() {
      return this.wS.length;
   }

   @Override
   public int getElementBitsize() {
      return this.kS;
   }

   @Override
   public List getElements() {
      return ArrayUtil.asView(this.wS);
   }

   @Override
   public IEGeneric getElement(int var1) {
      return this.wS[var1];
   }

   @Override
   public int getPriority() {
      return 0;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      for (IEGeneric var5 : this.wS) {
         var5.getUsed(var1);
      }
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      for (IEGeneric var5 : this.wS) {
         var5.getDefinedOrUsedAsDestination(var1);
      }
   }

   @Override
   public boolean accessesMemory() {
      for (IEGeneric var4 : this.wS) {
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

   public ajp pC() {
      ala[] var1 = new ala[this.wS.length];
      int var2 = 0;

      for (IEGeneric var6 : this.wS) {
         var1[var2++] = var6.duplicate();
      }

      return (ajp)this.pC(new ajp(this.A, this.kS, var1));
   }

   @Override
   public IEImm evaluate(EState var1) {
      throw new RuntimeException("Cannot be evaluated");
   }

   @Override
   public ICElement generateC(IERoutineContext var1, ICMethod var2) {
      IWildcardType var3 = this.getSafeType(var1.getWildcardTypeManager());
      ICType var4 = var2.getTypeFactory().create(var3);
      return var2.getIdentifierManager().create(this.pC, var4, this.A, CIdentifierClass.SPECIAL, -1, null, null).getIdentifier();
   }

   @Override
   public void pC(akz var1) {
      String var2 = Strings.ff("g%d:%s", this.kS, this.A);
      var1.append(var2, ItemClassIdentifiers.ARTIFACT);
   }
}
