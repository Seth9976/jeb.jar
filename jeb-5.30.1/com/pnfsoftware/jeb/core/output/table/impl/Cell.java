package com.pnfsoftware.jeb.core.output.table.impl;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.table.IActionableCell;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Cell implements IActionableCell {
   @SerId(1)
   private String label;
   @SerId(2)
   private ItemClassIdentifiers classId;
   @SerId(3)
   private long itemId;
   @SerId(4)
   private int flags;

   public Cell(String var1) {
      this(var1, null, 0L, 0);
   }

   public Cell(String var1, ItemClassIdentifiers var2) {
      this(var1, var2, 0L, 0);
   }

   public Cell(String var1, ItemClassIdentifiers var2, long var3, int var5) {
      this.label = var1;
      this.classId = var2;
      this.itemId = var3;
      this.flags = var5;
   }

   @Override
   public String getLabel() {
      return this.label;
   }

   public void setLabel(String var1) {
      this.label = var1;
   }

   @Override
   public ItemClassIdentifiers getClassId() {
      return this.classId;
   }

   public void setClassId(ItemClassIdentifiers var1) {
      this.classId = var1;
   }

   @Override
   public long getItemId() {
      return this.itemId;
   }

   public void setItemId(long var1) {
      this.itemId = var1;
   }

   @Override
   public int getItemFlags() {
      return this.flags;
   }

   public void setItemFlags(int var1) {
      this.flags = var1;
   }
}
