package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;

@Ser
public class ajx extends akm implements IEJump {
   @SerId(1)
   private int pC;
   @SerId(2)
   private IEGeneric A;

   public ajx(IERoutineContext var1, int var2, IEGeneric var3) {
      super(var1);
      this.setBranchAddress(var2);
      this.setCondition(var3);
   }

   public ajx(IERoutineContext var1, int var2) {
      this(var1, var2, null);
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + this.pC;
      return 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2, boolean var3, boolean var4) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2, var3, var4)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         ajx var5 = (ajx)var1;
         if (this.pC != var5.pC) {
            return false;
         } else {
            if (this.A == null) {
               if (var5.A != null) {
                  return false;
               }
            } else if (!this.A.equalsEx(var5.A, var2)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public int getFallthroughAddress(long var1) {
      return (int)(var1 + this.getSize());
   }

   @Override
   public int getBranchAddress() {
      return this.pC;
   }

   @Override
   public void setBranchAddress(int var1) {
      this.pC = var1;
   }

   @Override
   public IEGeneric getCondition() {
      return this.A;
   }

   @Override
   public void setCondition(IEGeneric var1) {
      this.A = var1;
   }

   @Override
   public String getMnemonic() {
      return "jmp";
   }

   public IEGeneric[] pC() {
      return this.A == null ? new IEGeneric[0] : new IEGeneric[]{this.A};
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      FlowInformation var3 = new FlowInformation();
      if (this.A != null) {
         long var4 = var1 + this.getSize();
         var3.addTarget(new CodePointer(var4));
         if (var4 != this.pC) {
            var3.addTarget(new CodePointer(this.pC));
         }
      } else {
         var3.addTarget(new CodePointer(this.pC));
      }

      return var3;
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return FlowInformation.NONE;
   }

   @Override
   public void getDefUse(EDefUseInfo var1) {
      if (this.A != null) {
         this.A.getUsed(var1);
      }
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) throws IllegalIntermediateExpressionException {
      int var4 = 0;
      if (this.A != null) {
         if (this.A == var1) {
            this.A = var2.duplicate();
            var4++;
         } else {
            var4 += this.A.replaceVar(var1, var2);
         }
      }

      return var4;
   }

   @Override
   public int replaceDefinedVar(IEVar var1, IEGeneric var2) {
      return 0;
   }

   @Override
   public int getBitsize() {
      throw new RuntimeException("No bitsize");
   }

   @Override
   public boolean accessesMemory() {
      return this.A != null ? this.A.accessesMemory() : false;
   }

   @Override
   public void collectSubExpressions(Collection var1, Boolean var2) {
      if (var2 == null || var2) {
         if (this.A != null) {
            var1.add(this.A);
         }
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      if (this.A != null) {
         var1.add(new Couple(this, this.A));
      }
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      pC(var1, var2);
      if (var1 == this.A) {
         this.A = var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public void updateTypes(ETypeInfo var1) {
      if (this.A != null) {
         this.A.updateTypes(var1);
      }
   }

   public ajx kS() {
      ajx var1 = new ajx(this.wS, this.pC, this.A != null ? this.A.duplicate() : null);
      this.pC(var1);
      return var1;
   }

   @Override
   public void pC(akz var1) {
      if (this.A != null) {
         var1.appendKeyword("if");
         var1.append(" ");
         var1.pC(this.A);
         var1.append(" ");
      }

      var1.appendKeyword("goto");
      var1.append(" ");
      var1.pC(this.pC);
   }

   @Override
   public IEImm evaluate(EState var1) {
      int var2 = this.pC;
      if (this.A != null) {
         IEImm var3 = this.A.evaluate(var1);
         if (var3 == null) {
            return null;
         }

         if (var3._signum() == 0) {
            var2 = var1.getVirtualPC() + this.getSize();
         }
      }

      var1.setVirtualPC(var2);
      Long var4 = this.wS.convertIntermediateOffset(var1.getVirtualPC());
      if (var4 == null) {
         var1.removeValue(this.wS.getProgramCounterId());
      } else {
         var1.setValue(this.wS.getProgramCounterId(), var4);
      }

      return ajr.pC(var2, 32);
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.wS, "Illegal IR context");
      ICElementFactory var3 = var2.getElementFactory();
      ICGoto var4 = var3.createGoto(var2.getLabelFactory().create(this.pC));
      if (this.A == null) {
         return (ICStatement)this.pC(var4);
      } else {
         ICPredicate var5 = var3.createPredicate((ICExpression)this.A.generateC(var1, var2));
         ICIfStm var6 = var3.createIfStm(var5, var4);
         return (ICStatement)this.pC(var6);
      }
   }
}
