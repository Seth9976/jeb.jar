package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.text.IActionableTextItem;
import com.pnfsoftware.jeb.core.output.text.ILine;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;

@Ser
@SerVersion(1)
public class TextItem implements IActionableTextItem {
   @SerId(1)
   private int offset;
   @SerId(2)
   private int length;
   @SerId(3)
   private ItemClassIdentifiers classId;
   @SerId(4)
   private long itemId;
   @SerId(5)
   private int flags;
   @SerId(value = 6, version = 1)
   private ILine line;

   public TextItem(int var1, int var2) {
      this(var1, var2, null, 0L, 0);
   }

   public TextItem(int var1, int var2, ItemClassIdentifiers var3) {
      this(var1, var2, var3, 0L, 0);
   }

   public TextItem(int var1, int var2, ItemClassIdentifiers var3, long var4, int var6) {
      this.offset = var1;
      this.length = var2;
      this.classId = var3;
      this.itemId = var4;
      this.flags = var6;
   }

   @Override
   public int getOffset() {
      return this.offset;
   }

   public void setOffset(int var1) {
      this.offset = var1;
   }

   @Override
   public int getLength() {
      return this.length;
   }

   public void setLength(int var1) {
      this.length = var1;
   }

   @Override
   public int getOffsetEnd() {
      return this.getOffset() + this.getLength();
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

   @Override
   public ILine getLine() {
      return this.line;
   }

   public void setLine(ILine var1) {
      if (this.line != null && this.line != var1) {
         throw new IllegalStateException("This text item already belong to the line: " + this.line);
      } else {
         this.line = var1;
      }
   }

   @Override
   public String getText() {
      return this.line == null ? null : this.line.getText().subSequence(this.offset, this.offset + this.length).toString();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("[id=").append(Long.toHexString(this.itemId).toUpperCase());
      var1.append("h,(o=").append(this.offset);
      var1.append(",l=").append(this.length);
      var1.append("),cid=").append(this.classId);
      var1.append(",flags=").append(Long.toHexString(this.flags).toUpperCase()).append("h]");
      return var1.toString();
   }
}
