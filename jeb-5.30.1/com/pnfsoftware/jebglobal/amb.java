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
public class amb extends amp implements IEJumpFar {
   @SerId(1)
   IEGeneric q;
   @SerId(2)
   IEGeneric RF;
   @SerId(3)
   List xK;

   public amb(IERoutineContext var1, IEGeneric var2, IEGeneric var3) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException("A jump site is required");
      } else {
         this.q = var2;
         this.RF = var3;
      }
   }

   public amb(IERoutineContext var1, IEGeneric var2) {
      this(var1, var2, null);
   }

   private amb(amb var1) {
      super(var1.Dw);
      this.q = var1.q.duplicate();
      this.RF = var1.RF == null ? null : var1.RF.duplicate();
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
      var1 = 31 * var1 + (this.RF == null ? 0 : this.RF.hashCode());
      return 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
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
         amb var5 = (amb)var1;
         if (this.q == null) {
            if (var5.q != null) {
               return false;
            }
         } else if (!this.q.equalsEx(var5.q, var2)) {
            return false;
         }

         if (this.RF == null) {
            if (var5.RF != null) {
               return false;
            }
         } else if (!this.RF.equalsEx(var5.RF, var2)) {
            return false;
         }

         if (this.xK == null) {
            if (var5.xK != null) {
               return false;
            }
         } else if (!this.xK.equals(var5.xK)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public IEGeneric getJumpsite() {
      return this.q;
   }

   @Override
   public void setJumpsite(IEGeneric var1) {
      this.q = var1;
   }

   @Override
   public IEGeneric getCondition() {
      return this.RF;
   }

   @Override
   public void setCondition(IEGeneric var1) {
      this.RF = var1;
   }

   @Override
   public String getMnemonic() {
      return "jmp";
   }

   public IEGeneric[] q() {
      return this.RF == null ? new IEGeneric[]{this.q} : new IEGeneric[]{this.q, this.RF};
   }

   @Override
   public void setPossibleTargets(List var1) {
      this.xK = var1;
   }

   @Override
   public List getPossibleTargets() {
      return this.xK == null ? Collections.emptyList() : this.xK;
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      HashSet var3 = new HashSet();
      FlowInformation var4 = new FlowInformation();
      if (this.RF != null) {
         long var5 = var1 + this.getSize();
         var3.add(var5);
         var4.addTarget(new CodePointer(var5));
      }

      boolean var9 = false;
      if (this.xK != null) {
         for (ICodePointer var7 : this.xK) {
            if (var7.isUnknownAddress()) {
               var9 = true;
            } else {
               Long var8 = this.Dw.convertNativeAddress(var7.getAddress());
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
      return (Collection)(this.RF == null ? Arrays.asList(InstructionFlags.ROUTINE_TERMINATOR) : Collections.emptySet());
   }

   @Override
   public void getDefUse(EDefUseInfo var1) {
      this.q.getUsed(var1);
      if (this.RF != null) {
         this.RF.getUsed(var1);
      }
   }

   @Override
   public int getBitsize() {
      throw new RuntimeException("No bitsize");
   }

   @Override
   public boolean accessesMemory() {
      return this.q.accessesMemory() ? true : this.RF != null && this.RF.accessesMemory();
   }

   @Override
   public boolean writesMemory() {
      return false;
   }

   @Override
   public void collectSubExpressions(Collection var1, Boolean var2) {
      if (var2 == null || var2) {
         var1.add(this.q);
         if (this.RF != null) {
            var1.add(this.RF);
         }
      }
   }

   @Override
   public void collectUsedExpressions(Collection var1) {
      var1.add(new Couple(this, this.q));
      if (this.RF != null) {
         var1.add(new Couple(this, this.RF));
      }
   }

   @Override
   public boolean replaceSubExpression(IEGeneric var1, IEGeneric var2) {
      q(var1, var2);
      if (this.q == var1) {
         RF(this.q, var2);
         this.q = var2;
         return true;
      } else if (this.RF != null && this.RF == var1) {
         this.RF = var2;
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2, boolean var3) {
      int var4 = 0;
      if (this.q == var1) {
         RF(this.q, var2);
         this.q = var2.duplicate();
         var4++;
      } else {
         var4 += this.q.replaceVar(var1, var2);
      }

      if (this.RF != null) {
         if (this.RF == var1) {
            this.RF = var2.duplicate();
            var4++;
         } else {
            var4 += this.RF.replaceVar(var1, var2);
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
      this.q.updateTypes(var1);
      if (this.RF != null) {
         this.RF.updateTypes(var1);
      }
   }

   public amb xK() {
      amb var1 = new amb(this);
      if (this.xK != null) {
         var1.setPossibleTargets(new ArrayList(this.xK));
      }

      return (amb)super.q(var1);
   }

   public alu q(EState var1) {
      if (this.RF != null) {
         IEImm var3 = this.RF.evaluate(var1);
         if (var3 == null) {
            return null;
         } else if (var3._signum() == 0) {
            int var2 = var1.getVirtualPC() + this.getSize();
            var1.setVirtualPC(var2);
            Long var4 = this.Dw.convertIntermediateOffset(var1.getVirtualPC());
            if (var4 == null) {
               var1.removeValue(this.Dw.getProgramCounterId());
            } else {
               var1.setValue(this.Dw.getProgramCounterId(), var4);
            }

            return alu.q(var2, 32);
         } else {
            throw new RuntimeException("TBI: jump out");
         }
      } else {
         throw new RuntimeException("TBI: jump out");
      }
   }

   @Override
   public ICStatement generateC(IERoutineContext var1, ICMethod var2) {
      Assert.a(var1 == this.Dw, "Illegal IR context");
      ICElementFactory var3 = var2.getElementFactory();
      ICExpression var4 = (ICExpression)this.q.generateC(var1, var2);
      ICJumpFar var5 = var3.createJumpFar(var4);
      if (this.RF == null) {
         return (ICStatement)this.q(var5);
      } else {
         ICPredicate var6 = var3.createPredicate((ICExpression)this.RF.generateC(var1, var2));
         ICIfStm var7 = var3.createIfStm(var6, var5);
         return (ICStatement)this.q(var7);
      }
   }

   @Override
   public void q(and var1) {
      if (this.RF != null) {
         var1.appendKeyword("if");
         var1.append(" ");
         var1.q(this.RF);
         var1.append(" ");
      }

      var1.appendKeyword("jump");
      var1.append(" ");
      var1.q(this.q);
   }
}
