package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class SwitchInformation {
   private List switchCases = new ArrayList();
   private List jumpTables = new ArrayList();

   public void addJumpTable(SwitchInformation.JumpTableInformation var1) {
      this.jumpTables.add(var1);
   }

   public List getJumpTables() {
      return this.jumpTables;
   }

   public List getCases() {
      return this.switchCases;
   }

   public void addCase(SwitchInformation.SwitchCaseInformation var1) {
      this.switchCases.add(var1);
   }

   public boolean isEmpty() {
      return this.switchCases.size() == 0;
   }

   public static class JumpTableInformation {
      final long startAddress;
      final int entrySize;
      final boolean isSecondary;
      long endAddress;

      public JumpTableInformation(long var1, int var3, boolean var4) {
         this.entrySize = var3;
         this.startAddress = var1;
         this.isSecondary = var4;
      }

      public JumpTableInformation(long var1, int var3) {
         this(var1, var3, false);
      }

      public void setEndAddress(long var1) {
         this.endAddress = var1;
      }

      public long getStartAddress() {
         return this.startAddress;
      }

      public int getEntrySize() {
         return this.entrySize;
      }

      public long getEndAddress() {
         return this.endAddress;
      }

      public boolean isSecondary() {
         return this.isSecondary;
      }
   }

   public static class SwitchCaseInformation {
      private CodePointer caseHandler;
      private long jumpTableEntryAddress = -1L;
      private int jumpTableEntrySize = 0;

      public boolean isJumpTableBased() {
         return this.jumpTableEntryAddress != -1L && this.jumpTableEntrySize != 0;
      }

      public CodePointer getCaseHandler() {
         return this.caseHandler;
      }

      public void setCaseHandler(CodePointer var1) {
         this.caseHandler = var1;
      }

      public long getJumpTableEntryAddress() {
         return this.jumpTableEntryAddress;
      }

      public void setJumpTableEntryAddress(long var1) {
         this.jumpTableEntryAddress = var1;
      }

      public int getJumpTableEntrySize() {
         return this.jumpTableEntrySize;
      }

      public void setJumpTableEntrySize(int var1) {
         this.jumpTableEntrySize = var1;
      }

      @Override
      public String toString() {
         return Strings.ff("SwitchCaseInformation [%xh (%d) -> %s]", this.jumpTableEntryAddress, this.jumpTableEntrySize, this.caseHandler);
      }
   }
}
