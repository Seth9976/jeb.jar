package com.pnfsoftware.jeb.core.units.code.asm.memory;

class MemoryChangesRecorder$1 implements IMemoryAllocListener {
   MemoryChangesRecorder$1(MemoryChangesRecorder var1) {
      this.this$0 = var1;
   }

   @Override
   public void onAllocEvent(MemoryAllocEvent var1) {
      long var2 = this.this$0.vm.roundToPage(var1.getAddress());
      this.this$0.mc.allocPages.add(var2);
   }
}
