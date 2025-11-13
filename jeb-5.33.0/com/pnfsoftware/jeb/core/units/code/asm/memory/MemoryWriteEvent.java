package com.pnfsoftware.jeb.core.units.code.asm.memory;

public class MemoryWriteEvent extends MemoryEvent {
   byte[] buffer;
   int bufferOffset;

   public MemoryWriteEvent(long var1, int var3, byte[] var4, int var5) {
      super(var1, var3, 0);
   }

   public byte[] getBuffer() {
      return this.buffer;
   }

   public int getBufferOffset() {
      return this.bufferOffset;
   }
}
