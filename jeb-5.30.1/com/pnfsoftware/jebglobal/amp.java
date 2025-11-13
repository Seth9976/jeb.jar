package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalIntermediateExpressionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EDefUseInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.ETypeInfo;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

@Ser
public abstract class amp extends ane implements IEStatement {
   private static final StructuredLogger q = aeg.q(amp.class);
   private static final byte[] RF = new byte[0];
   @SerId(1)
   protected IERoutineContext Dw;
   @SerId(2)
   protected int Uv;
   @SerId(3)
   protected LinkedHashSet oW = new LinkedHashSet();
   @SerId(4)
   protected Integer gO;

   protected amp(IERoutineContext var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var1;
         this.Uv = 1;
      }
   }

   @Override
   public IERoutineContext getContext() {
      return this.Dw;
   }

   @Override
   public IEStatement withLowerLevelAddress(long var1) {
      this.setLowerLevelAddress(var1);
      return this;
   }

   @Override
   public IEStatement withLowerLevelAddresses(Collection var1) {
      this.setLowerLevelAddresses(var1);
      return this;
   }

   @Override
   public void setLowerLevelAddresses(Collection var1) {
      this.oW.clear();
      this.oW.addAll(var1);
   }

   @Override
   public void setLowerLevelAddress(long var1) {
      this.oW.clear();
      this.oW.add(var1);
   }

   @Override
   public void addLowerLevelAddress(long var1) {
      this.oW.add(var1);
   }

   @Override
   public void addLowerLevelAddresses(Collection var1) {
      this.oW.addAll(var1);
   }

   @Override
   public void removeLowerLevelAddress(long var1) {
      this.oW.remove(var1);
   }

   @Override
   public void resetLowerLevelAddresses() {
      this.oW.clear();
   }

   @Override
   public void copyLowerLevelAddresses(IEStatement var1) {
      this.addLowerLevelAddresses(var1.getLowerLevelAddresses());
   }

   @Override
   public Collection getLowerLevelAddresses() {
      return Collections.unmodifiableCollection(this.oW);
   }

   @Override
   public Long getPrimaryLowerLevelAddress() {
      return this.oW.isEmpty() ? null : (Long)this.oW.iterator().next();
   }

   @Override
   public void setPrimaryLowerLevelAddress(long var1) {
      if (this.oW.isEmpty()) {
         this.oW.add(var1);
      } else if ((Long)this.oW.iterator().next() != var1) {
         LinkedHashSet var3 = new LinkedHashSet(this.oW);
         this.oW.clear();
         this.oW.add(var1);
         this.oW.addAll(var3);
      }
   }

   @Override
   public void setSPDelta(Integer var1) {
      this.gO = var1;
   }

   @Override
   public Integer getSPDelta() {
      return this.gO;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
      return 31 * var1 + this.Uv;
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, true, true, true);
   }

   @Override
   public final boolean equalsEx(Object var1, boolean var2) {
      return this.equalsEx(var1, var2, true, true);
   }

   @Override
   public final boolean equalsEx(Object var1, boolean var2, boolean var3) {
      return this.equalsEx(var1, var2, var3, true);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2, boolean var3, boolean var4) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         amp var5 = (amp)var1;
         if (var3) {
            if (this.Dw == null) {
               if (var5.Dw != null) {
                  return false;
               }
            } else if (this.Dw != var5.Dw) {
               return false;
            }
         }

         return !var4 || this.Uv == var5.Uv;
      }
   }

   @Override
   public int getProcessorMode() {
      return 0;
   }

   @Override
   public int getSize() {
      return this.Uv;
   }

   @Override
   public void setSize(int var1) {
      if (var1 <= 0) {
         throw new RuntimeException("Illegal length: " + var1);
      } else {
         this.Uv = var1;
      }
   }

   @Override
   public void adjustSize(int var1) {
      int var2 = this.Uv + var1;
      if (var2 <= 0) {
         throw new RuntimeException("Illegal length: " + var2);
      } else {
         this.Uv = var2;
      }
   }

   @Override
   public byte[] getCode() {
      return RF;
   }

   @Override
   public String getPrefix() {
      return null;
   }

   @Override
   public Collection getInstructionFlags() {
      return Collections.emptySet();
   }

   @Override
   public boolean canThrow() {
      return false;
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
      var1.clear();
      var2.clear();
      EDefUseInfo var4 = new EDefUseInfo(2, this.Dw);
      this.getDefUse(var4);
      if (((aml)this.Dw).nf()) {
         var4.getDef().collectBits(var1);
         var4.getUse().collectBits(var2);
      } else {
         var4.getDef().collectVarIds(var1);
         var4.getUse().collectVarIds(var2);
      }
   }

   @Override
   public EDefUseInfo getDefUseInfo(long var1, int var3) {
      EDefUseInfo var4 = new EDefUseInfo(var3, this.Dw);
      var4.setInstructionAddress(var1);
      this.getDefUse(var4);
      return var4;
   }

   @Override
   public void getUsed(EDefUseInfo var1) {
      this.getDefUse(var1);
   }

   @Override
   public void getDefinedOrUsedAsDestination(EDefUseInfo var1) {
      throw new RuntimeException("Do not call getDefinedOrUsedAsDestination() on IR statements");
   }

   @Override
   public boolean writesMemory() {
      return false;
   }

   @Override
   public int getPriority() {
      return 0;
   }

   @Override
   public int replaceVar(IEVar var1, IEGeneric var2) throws IllegalIntermediateExpressionException {
      return this.replaceVar(var1, var2, false);
   }

   protected final amp q(amp var1) {
      var1.Uv = this.Uv;
      var1.addLowerLevelAddresses(this.oW);
      var1.gO = this.gO;
      return (amp)super.q((ane)var1);
   }

   @Override
   public void copyProperties(IEStatement var1) {
      this.Uv = var1.getSize();
      this.addLowerLevelAddresses(var1.getLowerLevelAddresses());
      this.gO = var1.getSPDelta();
      this.gP = var1.getFlags();
      this.za = var1.getType();
   }

   @Override
   public void preUpdateTypes(ETypeInfo var1) {
   }

   @Override
   public void postUpdateTypes(ETypeInfo var1) {
   }

   @Override
   public abstract ICStatement generateC(IERoutineContext var1, ICMethod var2);

   protected ICElement q(ICElement var1) {
      var1.addPhysicalOffsets(this.oW);
      return var1;
   }

   @Override
   public String format(Object var1) {
      if (var1 == null) {
         var2 = new alq();
      } else if (!(var1 instanceof alq var2)) {
         var2 = new alq();
      }

      this.q(var2);
      return var2.toString();
   }

   @Override
   public void verify() throws IllegalIntermediateExpressionException {
      if (!this.visitDepthPre(new amq(this))) {
         throw new IllegalIntermediateExpressionException("Nested statement: " + this);
      }
   }
}
