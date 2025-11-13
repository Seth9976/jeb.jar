package com.pnfsoftware.jeb.core.output.text.impl;

import com.pnfsoftware.jeb.core.output.text.ITextMark;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;

@Ser
public class TextMark implements ITextMark {
   @SerId(1)
   private int offset;
   @SerId(2)
   private String name;
   @SerTransient
   private Object object;

   public TextMark(int var1, String var2, Object var3) {
      if (var2 == null) {
         throw new IllegalArgumentException("A mark must have a non-null name");
      } else {
         this.offset = var1;
         this.name = var2;
         this.object = var3;
      }
   }

   @Override
   public int getOffset() {
      return this.offset;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public Object getObject() {
      return this.object;
   }
}
