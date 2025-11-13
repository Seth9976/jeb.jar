package com.pnfsoftware.jeb.core.output.code;

import com.pnfsoftware.jeb.core.output.text.IAnchor;
import com.pnfsoftware.jeb.util.format.Strings;

public class CodeAnchor implements IAnchor {
   private long id;
   private int lineindex;
   private String name;

   CodeAnchor(long var1, int var3, String var4) {
      this.id = var1;
      this.lineindex = var3;
      this.name = var4;
   }

   @Override
   public long getIdentifier() {
      return this.id;
   }

   @Override
   public int getLineIndex() {
      return this.lineindex;
   }

   void setLineindex(int var1) {
      this.lineindex = var1;
   }

   void adjustLineindex(int var1) {
      this.lineindex += var1;
   }

   public String getName() {
      return this.name;
   }

   @Override
   public String toString() {
      return Strings.ff("(%s)anchor=%d,lineindex=%d", Strings.safe(this.getName(), "N/A"), this.getIdentifier(), this.getLineIndex());
   }
}
