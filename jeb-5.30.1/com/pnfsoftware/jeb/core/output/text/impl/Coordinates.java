package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.output.text.ICoordinates;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Coordinates implements ICoordinates {
   @SerId(1)
   private long anchorId;
   @SerId(2)
   private int lineDelta;
   @SerId(3)
   private int columnOffset;

   public Coordinates(long var1, int var3, int var4) {
      this.anchorId = var1;
      this.lineDelta = var3;
      this.columnOffset = var4;
   }

   public Coordinates(long var1, int var3) {
      this.anchorId = var1;
      this.lineDelta = var3;
   }

   public Coordinates(long var1) {
      this.anchorId = var1;
   }

   public void setAnchorId(int var1) {
      this.anchorId = var1;
   }

   @Override
   public long getAnchorId() {
      return this.anchorId;
   }

   public void setLineDelta(int var1) {
      this.lineDelta = var1;
   }

   @Override
   public int getLineDelta() {
      return this.lineDelta;
   }

   public void setColumnOffset(int var1) {
      this.columnOffset = var1;
   }

   @Override
   public int getColumnOffset() {
      return this.columnOffset;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.anchorId ^ this.anchorId >>> 32);
      var1 = 31 * var1 + this.columnOffset;
      return 31 * var1 + this.lineDelta;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         Coordinates var2 = (Coordinates)var1;
         if (this.anchorId != var2.anchorId) {
            return false;
         } else {
            return this.columnOffset != var2.columnOffset ? false : this.lineDelta == var2.lineDelta;
         }
      }
   }

   @Override
   public String toString() {
      return "(" + this.anchorId + "," + this.lineDelta + "," + this.columnOffset + ")";
   }
}
