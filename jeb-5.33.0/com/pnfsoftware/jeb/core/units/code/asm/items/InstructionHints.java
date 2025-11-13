package com.pnfsoftware.jeb.core.units.code.asm.items;

import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class InstructionHints {
   @SerId(1)
   Integer spDelta;
   @SerId(2)
   IPrototypeItem callsiteProto;
   @SerId(3)
   boolean isTailCall;
   @SerId(4)
   int switchDispatcher;
   @SerId(5)
   boolean isFakeCall;
   @SerId(6)
   boolean isCondTailCall;
   @SerId(7)
   boolean isActualCall;

   public void setStackPointerDelta(Integer var1) {
      this.spDelta = var1;
   }

   public Integer getStackPointerDelta() {
      return this.spDelta;
   }

   public void setCallsitePrototype(IPrototypeItem var1) {
      this.callsiteProto = var1;
   }

   public IPrototypeItem getCallsitePrototype() {
      return this.callsiteProto;
   }

   public boolean isTailCall() {
      return this.isTailCall;
   }

   public void setTailCall(boolean var1) {
      this.isTailCall = var1;
   }

   public boolean isCondTailCall() {
      return this.isCondTailCall;
   }

   public void setCondTailCall(boolean var1) {
      this.isCondTailCall = var1;
   }

   public int getSwitchDispatcher() {
      return this.switchDispatcher;
   }

   public void setSwitchDispatcher(int var1) {
      this.switchDispatcher = var1;
   }

   public boolean isFakeCall() {
      return this.isFakeCall;
   }

   public void setFakeCall(boolean var1) {
      this.isFakeCall = var1;
   }

   public boolean isActualCall() {
      return this.isActualCall;
   }

   public void setActualCall(boolean var1) {
      this.isActualCall = var1;
   }

   public boolean isCustomBranchingInstruction() {
      return this.getSwitchDispatcher() != 0 || this.isTailCall() || this.isCondTailCall() || this.isActualCall() || this.isFakeCall();
   }
}
