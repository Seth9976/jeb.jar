package com.pnfsoftware.jeb.core.units.code.asm.memory;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MemoryChangesRecorder {
   private IVirtualMemory vm;
   private MemoryChangesRecorder.Results mc;
   private IMemoryAllocListener memAllocListener = new MemoryChangesRecorder$1(this);
   private IMemoryWriteListener memPreWriteListener = new MemoryChangesRecorder$2(this);

   public MemoryChangesRecorder(IVirtualMemory var1) {
      this.vm = var1;
   }

   public MemoryChangesRecorder.Results getMemoryChanges() {
      if (this.mc == null) {
         throw new IllegalStateException();
      } else {
         return this.mc;
      }
   }

   public void setup() {
      if (this.mc != null) {
         throw new IllegalStateException();
      } else {
         this.mc = new MemoryChangesRecorder.Results();
         this.vm.addAllocListener(this.memAllocListener);
         this.vm.addPreWriteListener(this.memPreWriteListener);
      }
   }

   public void teardown() {
      this.vm.removeAllocListener(this.memAllocListener);
      this.vm.removePreWriteListener(this.memPreWriteListener);
   }

   public static class Results {
      Set allocPages = new HashSet();
      Set writtenPages = new HashSet();
      Map writtenPagesData = new HashMap();

      public Set getAllocatedPages() {
         return this.allocPages;
      }

      public Set getWrittenPages() {
         return this.writtenPages;
      }

      public byte[] getPreWritePageData(long var1) {
         return (byte[])this.writtenPagesData.get(var1);
      }

      @Override
      public String toString() {
         return Strings.ff("%d allocated pages, %d written pages", this.allocPages.size(), this.writtenPages.size());
      }
   }
}
