package com.pnfsoftware.jeb.core.output.code;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;

public interface ICodeDocument {
   void append(String var1);

   void append(String var1, ItemClassIdentifiers var2);

   default void appendKeyword(String var1) {
      this.append(var1, ItemClassIdentifiers.KEYWORD);
   }

   void paren();

   void parenClose();

   void brace();

   void braceClose();

   void bracket();

   void bracketClose();
}
