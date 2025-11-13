package com.pnfsoftware.jeb.core.units.code.asm.memory;

class MemoryChangesRecorder$2 implements IMemoryWriteListener {
   MemoryChangesRecorder$2(MemoryChangesRecorder var1) {
      this.this$0 = var1;
   }

   @Override
   public void onMemoryWriteEvent(MemoryWriteEvent var1) {
      long var2 = this.this$0.vm.roundToPage(var1.getAddress());
      if (!this.this$0.mc.allocPages.contains(var2)) {
         if (this.this$0.mc.writtenPages.add(var2)) {
            int var4 = this.this$0.vm.getPageSize();
            byte[] var5 = new byte[var4];

            try {
               this.this$0.vm.read(var2, var4, var5, 0, true);
            } catch (MemoryException var6) {
            }

            this.this$0.mc.writtenPagesData.put(var2, var5);
         }
      }
   }
}
