package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class axn extends axg implements INativeInstructionItem {
   public static final String q = "insnHints";
   @SerId(1)
   private long RF;
   @SerId(2)
   private IInstruction Dw;

   public axn(long var1, IInstruction var3) {
      super(0);
      this.RF = var1;
      this.Dw = var3;
   }

   @Override
   public long getMemoryAddress() {
      return this.RF;
   }

   @Override
   public long getMemorySize() {
      return this.Dw.getSize();
   }

   @Override
   public IInstruction getInstruction() {
      return this.Dw;
   }

   @Override
   public InstructionHints getHints(boolean var1) {
      InstructionHints var2 = (InstructionHints)this.getAttribute("insnHints", InstructionHints.class);
      if (var2 == null) {
         if (!var1) {
            return null;
         }

         var2 = new InstructionHints();
         this.setAttribute("insnHints", var2);
      }

      return var2;
   }

   @Override
   public String toString() {
      return Strings.ff("InstructionItem@%X(%s)", this.getMemoryAddress(), this.Dw.format(this.getMemoryAddress()));
   }

   public int Uv() {
      InstructionHints var1 = this.getHints(false);
      return var1 != null ? var1.getSwitchDispatcher() : -1;
   }

   public void q(int var1) {
      InstructionHints var2 = this.getHints(true);
      var2.setSwitchDispatcher(var1);
   }
}
