package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.ICodePointer;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Ser
public class ajy extends akm implements IEJumpFar {
   @SerId(1)
   IEGeneric pC;
   @SerId(2)
   IEGeneric A;
   @SerId(3)
   List kS;

   public ajy(IERoutineContext var1, IEGeneric var2, IEGeneric var3) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException("A jump site is required");
      } else {
         this.pC = var2;
         this.A = var3;
      }
   }

   public ajy(IERoutineContext var1, IEGeneric var2) {
      this(var1, var2, null);
   }

   private ajy(ajy var1) {
      super(var1.wS);
      this.pC = var1.pC.duplicate();
      this.A = var1.A == null ? null : var1.A.duplicate();
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.pC == null ? 0 : this.pC.hashCode());
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      return 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
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
         ajy var5 = (ajy)var1;
         if (this.pC == null) {
            if (var5.pC != null) {
               return false;
            }
         } else if (!this.pC.equalsEx(var5.pC, var2)) {
            return false;
         }

         if (this.A == null) {
            if (var5.A != null) {
               return false;
            }
         } else if (!this.A.equalsEx(var5.A, var2)) {
            return false;
         }

         if (this.kS == null) {
            if (var5.kS != null) {
               return false;
            }
         } else if (!this.kS.equals(var5.kS)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public IEGeneric getJumpsite() {
      return this.pC;
   }

   @Override
   public void setJumpsite(IEGeneric var1) {
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
      return this.A == null ? new IEGeneric[]{this.pC} : new IEGeneric[]{this.pC, this.A};
   }

   @Override
   public void setPossibleTargets(List var1) {
      this.kS = var1;
   }

   @Override
   public List getPossibleTargets() {
      return this.kS == null ? Collections.emptyList() : this.kS;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      HashSet var3 = new HashSet();
      FlowInformation var4 = new FlowInformation();
      if (this.A != null) {
         long var5 = var1 + this.getSize();
         var3.add(var5);
         var4.addTarget(new CodePointer(var5));
      }

      boolean var9 = false;
      if (this.kS != null) {
         for (ICodePointer var7 : this.kS) {
            if (var7.isUnknownAddress()) {
               var9 = true;
            } else {
               Long var8 = this.wS.convertNativeAddress(var7.getAddress());
               if (var8 == null) {
                  var9 = true;
               } else if (var3.add(var8)) {
                  var4.addTarget(new CodePointer(var8));
               }
            }
         }

         if (var9) {
            var4.addTarget(CodePointer.createUnknown());
         }
      }

      return var4;
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
   public Collection getInstructionFlags() {
      return (Collection)(this.A == null ? Arrays.asList(InstructionFlags.ROUTINE_TERMINATOR) : Collections.emptySet());
   }

   @Override
   public void getDefUse(EDefUseInfo var1) {
      this.pC.getUsed(var1);
      if (this.A != null) {
         this.A.getUsed(var1);
      }
   }

   @Override
   public int getBitsize() {
      throw new RuntimeException("No bitsize");
   }

   @Override
   public boolean accessesMemory() {
      return this.pC.accessesMemory() ? true : this.A != null && this.A.accessesMemory();
   }

   @Override
   public boolean writesMemory() {
      return false;
   }

   @Override
   public void collectSubExpressions(Collection var1, Boolean var2) {
      if (var2 == null || var2) {
         var1.add(this.pC);
         if (this.A != null) {
            var1.add(this.A);
         }
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      var1.add(new Couple(this, this.pC));
      if (this.A != null) {
         var1.add(new Couple(this, this.A));
      }
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) {
      pC(var1, var2);
      if (this.pC == var1) {
         A(this.pC, var2);
         this.pC = var2;
         return true;
      } else if (this.A != null && this.A == var1) {
         this.A = var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) {
      int var4 = 0;
      if (this.pC == var1) {
         A(this.pC, var2);
         this.pC = var2.duplicate();
         var4++;
      } else {
         var4 += this.pC.replaceVar(var1, var2);
      }

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
   public void updateTypes(ETypeInfo var1) {
      this.pC.updateTypes(var1);
      if (this.A != null) {
         this.A.updateTypes(var1);
      }
   }

   public ajy kS() {
      ajy var1 = new ajy(this);
      if (this.kS != null) {
         var1.setPossibleTargets(new ArrayList(this.kS));
      }

      return (ajy)super.pC(var1);
   }

   public ajr pC(EState var1) {
      if (this.A != null) {
         IEImm var3 = this.A.evaluate(var1);
         if (var3 == null) {
            return null;
         } else if (var3._signum() == 0) {
            int var2 = var1.getVirtualPC() + this.getSize();
            var1.setVirtualPC(var2);
            Long var4 = this.wS.convertIntermediateOffset(var1.getVirtualPC());
            if (var4 == null) {
               var1.removeValue(this.wS.getProgramCounterId());
            } else {
               var1.setValue(this.wS.getProgramCounterId(), var4);
            }

            return ajr.pC(var2, 32);
         } else {
            throw new RuntimeException("TBI: jump out");
         }
      } else {
         throw new RuntimeException("TBI: jump out");
      }
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.wS, "Illegal IR context");
      ICElementFactory var3 = var2.getElementFactory();
      ICExpression var4 = (ICExpression)this.pC.generateC(var1, var2);
      ICJumpFar var5 = var3.createJumpFar(var4);
      if (this.A == null) {
         return (ICStatement)this.pC(var5);
      } else {
         ICPredicate var6 = var3.createPredicate((ICExpression)this.A.generateC(var1, var2));
         ICIfStm var7 = var3.createIfStm(var6, var5);
         return (ICStatement)this.pC(var7);
      }
   }

   @Override
   public void pC(akz var1) {
      if (this.A != null) {
         var1.appendKeyword("if");
         var1.append(" ");
         var1.pC(this.A);
         var1.append(" ");
      }

      var1.appendKeyword("jump");
      var1.append(" ");
      var1.pC(this.pC);
   }
}
