package com.pnfsoftware.jeb.util.interpreter;

import com.pnfsoftware.jeb.util.collect.ItemHistory;
import com.pnfsoftware.jeb.util.format.IAsciiable;
import com.pnfsoftware.jeb.util.format.Strings;

public class InterpreterHistory implements IAsciiable {
   ItemHistory h = new ItemHistory(500, false);

   public InterpreterHistory() {
   }

   public InterpreterHistory(String var1) {
      this.h.addAll(Strings.decodeList(var1));
   }

   @Override
   public String encode() {
      return Strings.encodeList(this.h.get());
   }

   public ItemHistory get() {
      return this.h;
   }
}
