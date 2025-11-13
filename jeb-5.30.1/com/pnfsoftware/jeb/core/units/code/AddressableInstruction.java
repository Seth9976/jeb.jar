package com.pnfsoftware.jeb.core.units.code;

import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.List;

@Ser
public class AddressableInstruction implements ILocatedInstruction {
   @SerId(1)
   private long address;
   @SerId(2)
   private IInstruction insn;

   public AddressableInstruction(long var1, IInstruction var3) {
      if (var3 instanceof ILocatedInstruction) {
         Assert.a(var1 == ((ILocatedInstruction)var3).getOffset());
      }

      this.address = var1;
      this.insn = var3;
   }

   public IInstruction getInstruction() {
      return this.insn;
   }

   @Override
   public long getOffset() {
      return this.address;
   }

   @Override
   public int getProcessorMode() {
      return this.insn.getProcessorMode();
   }

   @Override
   public int getSize() {
      return this.insn.getSize();
   }

   @Override
   public byte[] getCode() {
      return this.insn.getCode();
   }

   @Override
   public String getPrefix() {
      return this.insn.getPrefix();
   }

   @Override
   public String getMnemonic() {
      return this.insn.getMnemonic();
   }

   @Override
   public IInstructionOperand[] getOperands() {
      return this.insn.getOperands();
   }

   @Override
   public IFlowInformation getBreakingFlow(long var1) {
      return this.insn.getBreakingFlow(var1);
   }

   @Override
   public IFlowInformation getBreakingFlow() {
      return this.getBreakingFlow(this.getOffset());
   }

   @Override
   public IFlowInformation getRoutineCall(long var1) {
      return this.insn.getRoutineCall(var1);
   }

   @Override
   public IFlowInformation getRoutineCall() {
      return this.insn.getRoutineCall(this.getOffset());
   }

   @Override
   public IFlowInformation collectIndirectCallReferences(long var1) {
      return this.insn.collectIndirectCallReferences(var1);
   }

   @Override
   public IFlowInformation collectIndirectCallReferences() {
      return this.insn.collectIndirectCallReferences(this.getOffset());
   }

   @Override
   public long getPrimaryBranchAddress() {
      return this.insn.getPrimaryBranchAddress(this.getOffset());
   }

   @Override
   public void getDefUse(List var1, List var2, Object var3) {
      this.insn.getDefUse(var1, var2, var3);
   }

   @Override
   public Collection getInstructionFlags() {
      return this.insn.getInstructionFlags();
   }

   @Override
   public boolean canThrow() {
      return this.insn.canThrow();
   }

   @Override
   public String format(Object var1) {
      return this.insn.format(var1);
   }

   @Override
   public String toString() {
      return Strings.ff("%Xh:%s", this.address, this.insn);
   }
}
