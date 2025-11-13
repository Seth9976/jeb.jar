package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.output.text.IAnchor;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Anchor implements IAnchor {
   @SerId(1)
   private long id;
   @SerId(2)
   private int lineindex;

   public Anchor(long var1, int var3) {
      this.id = var1;
      this.lineindex = var3;
   }

   @Override
   public long getIdentifier() {
      return this.id;
   }

   public void setIdentifier(long var1) {
      this.id = var1;
   }

   @Override
   public int getLineIndex() {
      return this.lineindex;
   }

   public void setLineIndex(int var1) {
      this.lineindex = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("anchor=%d,lineindex=%d", this.getIdentifier(), this.getLineIndex());
   }
}
