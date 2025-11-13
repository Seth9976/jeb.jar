package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.InstructionHints;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class aus extends aul implements INativeInstructionItem {
   @SerId(1)
   private long pC;
   @SerId(2)
   private IInstruction kS;

   public aus(long var1, IInstruction var3) {
      super(0);
      this.pC = var1;
      this.kS = var3;
   }

   @Override
   public long getMemoryAddress() {
      return this.pC;
   }

   @Override
   public long getMemorySize() {
      return this.kS.getSize();
   }

   @Override
   public IInstruction getInstruction() {
      return this.kS;
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
      return Strings.ff("InstructionItem@%X(%s)", this.getMemoryAddress(), this.kS.format(this.getMemoryAddress()));
   }

   public int UT() {
      InstructionHints var1 = this.getHints(false);
      return var1 != null ? var1.getSwitchDispatcher() : -1;
   }

   public void pC(int var1) {
      InstructionHints var2 = this.getHints(true);
      var2.setSwitchDispatcher(var1);
   }
}
